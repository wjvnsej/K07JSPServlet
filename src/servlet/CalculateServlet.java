package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculateServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String fNUm = req.getParameter("firstNum");
		String sNUm = req.getParameter("secondNum");
		
		int fnum = Integer.parseInt(fNUm);
		int snum = Integer.parseInt(sNUm);
		String op = req.getParameter("operator");
		
		int returnValue;
		switch(op) {
		case "+":
			returnValue = fnum + snum;
			break;
		case "-":
			returnValue = fnum - snum;
			break;
		case "*":
			returnValue = fnum * snum;
			break;
		case "/":
			returnValue = fnum / snum;
			break;
		default:
			returnValue = 0;
		}
		
		req.setAttribute("calResult", "결과  : " + returnValue);
		req.getRequestDispatcher("/13Servlet/HelloServlet.jsp")
													.forward(req, resp);
		
	}
	
}
