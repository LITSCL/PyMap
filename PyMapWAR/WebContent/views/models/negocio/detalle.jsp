<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file='../../includes/head_styles.jsp' %>
<%@ include file='../../includes/head_scripts.jsp' %>
<%@ include file='../../includes/head_random.jsp' %>
</head>
<body>
<% 
if (session.getAttribute("renderizarVista") == "detalle") {
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
		<section class="row m-0 mt-5 p-0">
		<%
		if (session.getAttribute("registros") == "Pagina Inexistente") {
		%>
			<div class="alert col-8 m-2 alert-warning">No se han encontrado resultados en esta página</div>
		<%
		}
		else if (session.getAttribute("registros") == "Sin Registros") {
		%>
			<div class="alert col-8 m-2 alert-warning">Este negocio no tiene productos publicados</div>
		<%
		}
		else {
		%>
			<%=session.getAttribute("registros")%>
		<%
		}
		%>	
		</section>
		<section class="row m-0 p-0">
			<%=session.getAttribute("numeros")%>
		</section>
    	
	</div>
<%
	session.removeAttribute("registros");
	session.removeAttribute("numeros");
	session.removeAttribute("renderizarVista");
}
else {
	String negocio = request.getParameter("negocio");
	response.sendRedirect(request.getContextPath() + "/Negocio?vista=detalle&negocio=" + negocio + "&pagina=" + request.getParameter("pagina"));
}
%>
</body>
</html>