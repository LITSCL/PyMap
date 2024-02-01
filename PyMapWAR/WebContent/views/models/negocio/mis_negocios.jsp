<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<%@ include file='../../includes/head_styles.jsp' %>
	<%@ include file='../../includes/head_scripts.jsp' %>
	<%@ include file='../../includes/head_random.jsp' %>
</head>
<body>
<% 
if (session.getAttribute("renderizarVista") == "misNegocios") {
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
			<div class="alert col-8 m-2 alert-warning">Todavía no has publicado ningún negocio</div>
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
	response.sendRedirect(request.getContextPath() + "/Negocio?vista=mis_negocios&pagina=" + request.getParameter("pagina"));
}
%>
</body>
</html>