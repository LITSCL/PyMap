<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
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