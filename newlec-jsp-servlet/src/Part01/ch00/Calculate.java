package Part01.ch00;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add")
public class Calculate extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String x_ = request.getParameter("x");
		String y_ = request.getParameter("y");
		String op = request.getParameter("operator");
		
		int x = 0;
		int y = 0;
				
		if (!x_.equals("")) x = Integer.parseInt(x_);
		if (!y_.equals("")) y = Integer.parseInt(y_);
		
		int result = 0;
		
		if(op.equals("덧셈"))
			result = x + y;
		else
			result = x - y;
		
		out.printf("계산 결과는  %d 입니다. " , result  );
	}
}
