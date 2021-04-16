package Part01.ch00;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calccookie")
public class Calc2Cookie extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		ServletContext application = request.getServletContext();
//		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		if (!v_.equals("")) v = Integer.parseInt(v_);
		
		
		//계산
		if(op.equals("=")) {
			
			//int x = (Integer)application.getAttribute("value");
			//int x = (Integer)session.getAttribute("value");
			
			int x = 0;
			for(Cookie c : cookies ) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}	
			}
			
			
			int y = v;
			//String operator = (String)application.getAttribute("op");
			//String operator = (String)session.getAttribute("op");
			
			String operator="";
			for(Cookie c : cookies ) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}	
			}
			
			int result = 0;
			
			if(operator.equals("+"))
				result = x + y;
			else
				result = x - y;
			
			out.printf("result is  %d " , result  );
		}
		//값을 저장
		else {
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);
			
			//쿠키에는 문자열만 저장가능
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2-cookie.html");
			
		}
	}
}
