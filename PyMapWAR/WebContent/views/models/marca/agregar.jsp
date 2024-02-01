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
if (session.getAttribute("renderizarVista") == "agregarMarca") {
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
		<div class="m-5 w-50 mx-auto">
			<%
			if (session.getAttribute("agregarMarca") == "Exitoso") {
			%>
				<div class="alert alert-success">Marca agregada correctamente</div>
			<%
			}
			if (session.getAttribute("agregarMarca") == "Fallido") {	
			%>
				<div class="alert alert-danger">Hubo un error al agregar la marca</div>
			<%		
			}
			%>
			<form action="<%=request.getContextPath()%>/Marca" method="POST" enctype="multipart/form-data">
				<div class="card">
					<div class="card-header">
						<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<h3>Agregar Marca</h3>
			    				</div>
		    				</div>
		    			</div>
					</div>
		    		<div class="card-body">
	    				<div class="form-group">
							<label for="nombre">Nombre</label>
							<input type="text" class="form-control" name="nombre" required/>
						</div>
						<div class="form-group">
							<label for="logo">Logo</label>
							<input class="form-control-file" type="file" name="logo" style="cursor: pointer"/>
						</div>
		    		</div>
		    		<div class="card-footer">
		    			<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<button class="btn btn-primary" type="submit" name="opcion" value="1">Agregar</button>
			    				</div>
		    				</div>
		    			</div>
		    		</div>
	    		</div>
	    	</form>
		</div>
    	
	</div>
<%
	session.removeAttribute("agregarMarca");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Marca?vista=agregar");
}
%>
</body>
</html>