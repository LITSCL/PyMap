<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file='views/includes/head_styles.jsp' %>
<%@ include file='views/includes/head_scripts.jsp' %>
<%@ include file='views/includes/head_random.jsp' %>
</head>
<body>
<% 
if (session.getAttribute("renderizarVista") == "index") {
%>
	<%@ include file='views/includes/header.jsp' %>
		
	<div class="container">
		
		<h1 class="m-5">Estas en Index</h1>
		
	</div>
<%
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Index");
}
%>
</body>
</html>