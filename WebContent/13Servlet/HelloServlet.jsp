<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloServler.jsp</title>
</head>
<body>
	<!-- 
	서블릿 작성순서
	
	 -->
	<h2>서블릿(Servler) 만들기</h2>
	
	<h3>First Servlet</h3>
	<!-- 
		서블릿을 호출하기 위한 요청명을 먼저 결정한다.
		아래 경로는 상대경로이므로 컨텍스트 루트를 신경쓸 필요가 없다.
	 -->
	<!-- 상대경로 -->
	<a href="./NoJSPServlet.do">
		JSP파일 없이 화면에 결과 출력하기(Servlet에서 바로 출력) - 상대경로
	</a>
	
	<br />
	
	<!-- 절대경로 -->
	<a href="/K07JSPServlet/13Servlet/NoJSPServlet.do">
		JSP파일 없이 화면에 결과 출력하기(Servlet에서 바로 출력) - 절대경로
	</a>
	
	
	<h3>HelloServlet</h3>
	<h4>${message } - ${HELLO }</h4>
	<h4><%=request.getAttribute("message") %></h4>
	<a href="HelloServlet.do">
		JSP파일 (View)에서 화면 출력하기
	</a>
	
	 
	 <h3>WebServlet - 어노테이션으로 서블릿 매핑하기</h3>
	 <a href="AnnoWebServlet.do">
	 	AnnoWebServlet.do 바로가기
	 </a>
	 
	 
</body>
</html>






















