<%@page import="java.util.List"%>
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
if (session.getAttribute("renderizarVista") == "publicarPasoTres") {
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
		<div class="m-5 w-75 mx-auto">
			<%
			if (session.getAttribute("publicarNegocio") == "Fallido") {	
			%>
				<div class="alert alert-danger">Error al publicar el negocio</div>
			<%		
			}
			%>
			<form action="<%=request.getContextPath()%>/Negocio" method="POST">
				<div class="card">
					<div class="card-header">
						<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<h3>Publicar Negocio (Paso 3/3)</h3>
			    				</div>
		    				</div>
		    			</div>
					</div>
		    		<div class="card-body"> 		
						<section class="row m-0 p-0">
						<%
						if (session.getAttribute("registros") == "Pagina Inexistente") {
						%>
							<div class="alert col-8 m-2 alert-warning">No se han encontrado resultados en esta página</div>
						<%
						}
						else if (session.getAttribute("registros") == "Sin Registros") {
						%>
							<div class="alert col-8 m-2 alert-warning">No existen productos para las marcas seleccionadas</div>
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
		    		<div class="card-footer">
		    			<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<button class="btn btn-primary" type="submit" name="opcion" value="3">Publicar</button>
			    				</div>
		    				</div>
		    			</div>
		    		</div>
	    		</div>
	    	</form>
		</div>
		
		<a class="btn btn-danger" href="<%=request.getContextPath()%>/Negocio?opcion=1">Anular</a>
    	
	</div>
<%
	session.removeAttribute("registros");
	session.removeAttribute("numeros");
	session.removeAttribute("publicarNegocio");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Negocio?vista=publicar_paso_3&pagina=" + request.getParameter("pagina"));
}
%>
</body>
</html>