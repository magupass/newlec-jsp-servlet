package Part01.ch00.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Part01.ch00.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));


		//String url = "jdbc:oracle:thin:@203.251.151.149:1521:HISTMA";
		//String sql = "select * from z_notice where id = ?";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String sql = "select * from newlec_notice where id = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Connection con = DriverManager.getConnection(url, "masm","hi$tmasm");
			Connection con = DriverManager.getConnection(url, "scott","tiger");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			rs.next();

			String title = rs.getString("TITLE");
			Date regdate = rs.getDate("REGDATE");
			String writerId = rs.getString("WRITER_ID");
			String  hit = rs.getString("HIT");
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
			
			request.setAttribute("n", notice);

			/*
			 * request.setAttribute("title", title); request.setAttribute("writerId",
			 * writerId); request.setAttribute("regdate", regdate);
			 * request.setAttribute("hit", hit); request.setAttribute("files", files);
			 * request.setAttribute("content", content);
			 */
			
			System.out.println(notice.toString());
			
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
		
		//forward
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
		
	}
}
