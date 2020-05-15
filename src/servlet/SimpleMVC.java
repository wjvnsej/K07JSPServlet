package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;

/* 
	서블릿 호출을 위한 순서
	1. 요청명을 결정한다.
	2. 요청명에 대한 매핑 처리를 한다. 이때 요청명과
		해당 요청을 처리 할 서블릿 클래스가 매핑된다.
	3. 사용자의 요청이 서블릿(Controller)으로 전달되고 서블릿은 요청을 분석한다.
	4. 분석한 요청에 따라 model(모델)을 사용하여 기능을 수행한다.
	5. 기능을 수행한 후 필요한 데이터를 request 혹은 session영여에
		저장한다.
	6. View에 해당하는 JSP페이지를 찾아 포워드한다.
	7. View에서는 영역에 저장된 데이터를 가져와서 화면에 출력한다.
*/

public class SimpleMVC extends HttpServlet {
	
	/*
		클라이언트의 요청이 get방식이든 post방식이든 하나의 메소드에서
		처리하기 위해 processRequest()메소드로 모든 요청을 전달한다.
	*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		processRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		processRequest(req, resp);
	}
	
	/*
	doGet() 혹은 doPost()의 요청을 그대로 전달받기 위해서는 아래와 같이
	메소드를 기술해야 한다. request, response객체를 매개변수로 사용하고
	두 가지에 대한 예외를 반드시 처리해야 한다.
	*/
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String type = req.getParameter("type");
		
		Object resultobj = null;
		
		if(type == null) {
			resultobj = "파라미터가 없네요.";
		}
		else if(type.equals("greeting")) {
			
			/*
			서블릿 클래스에서 application 내장객체를 사용하기 위해
			getInitParameter()메소드를 이용해서 가져온다.
			*/
			
			ServletContext application = this.getServletContext();
			
			//컨텍스트 초기화 파라미터를 가져온다.
			String drv = application.getInitParameter("JDBCDriver");
			String url = application.getInitParameter("ConnectionURL");
			//MemberDAO 객체 생성
			MemberDAO dao = new MemberDAO(drv,url);
			
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			
			boolean isMember = dao.isMember(id, pw);
			
			if(isMember == true) {
				resultobj = "회원님 환영합니다.";
			}
			else {
				resultobj = "회원이 아닙니다.";
			}
		}
		else if(type.equals("date")) {
			resultobj = new java.util.Date();
		}
		else {
			resultobj = "허~얼~";
		}
		
		req.setAttribute("result", resultobj);
		
		RequestDispatcher dis = 
				req.getRequestDispatcher("/13Servlet/SimpleMVC.jsp");
		dis.forward(req, resp);
		
	}
	
}





















