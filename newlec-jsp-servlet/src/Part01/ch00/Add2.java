package Part01.ch00;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add2")
public class Add2 extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String[] num_ = request.getParameterValues("num");
		
		int result = 0;
		
		for(int i = 0; i < num_.length; i++) {
			int num = 0;
			if (!num_.equals("") && isNumeric(num_[i])) {
				num = Integer.parseInt(num_[i]);
				result += num;
			}
		}
		
				
		/*
		 * if (!x_.equals("")) x = Integer.parseInt(x_); if (!y_.equals("")) y =
		 * Integer.parseInt(y_);
		 * 
		 * 
		 * int result = x + y;
		 */
		
		out.printf("result is %d\n " , result  );
	}
	
	private boolean isNumeric(String num_) {
		try {
	     	Double.parseDouble(num_);
	    	return true;
	    } catch(NumberFormatException e) {  //문자열이 나타내는 숫자와 일치하지 않는 타입의 숫자로 변환 시 발생
	    	return false;
	    }
	}

}
