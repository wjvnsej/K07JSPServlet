<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SimpleMVC.jsp</title>
</head>
<body>

	<h2>간단한 MVC패턴 만들기</h2>
	<!-- 
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
	-->
	<ul>
		<li>
			<a href="SimpleMVC">
			SimpleMVC?type=
			</a>
		</li>
		<li>
			<a href="SimpleMVC?type=greeting&id=test3&pw=3333">
				SimpleMVC?type=greeting&id=test3&pw=3333
			</a>
		</li>
		<li>
			<a href="SimpleMVC?type=greeting&id=test1&pw=1234">
				SimpleMVC?type=greeting&id=test1&pw=1234
			</a>
		</li>
		<li>
			<a href="SimpleMVC?type=date">
				SimpleMVC?type=date
			</a>
		</li>
		<li>
			<a href="SimpleMVC?type=noparam">
				SimpleMVC?type=noparam
			</a>
		</li>
	</ul>
	
	<h3>요청결과</h3>
	<span style="color: red; font-size: 2em;">
		${result }
	</span>
	
	<!-- 
		요청명을 결정할때는 View의 경로와 동일한 Depth(깊이)로
		설정하는것이 좋다. 이미지의 경로, JS, CSS파일을 링크헀을 때
		경로가 깨지는 경우가 발생할 수 있기 때문이다.
		아래 이미지는 요청명이 
			"/13Servlet/SimpleMVC"일때는 정상적으로 출력되나
			"/SimpleMVC"일때는 이미지가 출력되지 않는다.
	 -->
	<h3>이미지와 요청명</h3>
	<img src="../images/3.jpg" alt="하트구름" />
	
</body>
</html>





















