package Part01.ch00.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import Part01.ch00.entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		List<Notice> list = new ArrayList<>();
		
		String url = "jdbc:oracle:thin:@203.251.151.149:1521:HISTMA";
		String sql = "select * from z_notice";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "masm","hi$tmasm");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
			
				 int id = rs.getInt("ID");
				 String title = rs.getString("TITLE");
				 String writerId = rs.getString("WRITER_ID");
				 Date regdate = rs.getDate("REGDATE");		
				 String hit = rs.getString("HIT");
				 String files = rs.getString("FILES");
				 String content = rs.getString("CONTENT");
				 Notice notice = new Notice(
							id, 
							title,
							writerId,
							regdate,
							hit,
							files,
							content
							);
				 list.add(notice);
			}		
			rs.close();
		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		
		//forward
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
		
		
	}
	
}
