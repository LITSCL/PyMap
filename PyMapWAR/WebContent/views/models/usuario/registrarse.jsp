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
if (session.getAttribute("renderizarVista") == "registrarse") {
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
		<div class="m-5 w-50 mx-auto">
			<%
			if (session.getAttribute("registro") == "Exitoso") {
			%>
				<div class="alert alert-success">Registro realizado correctamente</div>
			<%
			}
			if (session.getAttribute("registro") == "Fallido") {	
			%>
				<div class="alert alert-danger">Hubo un error al registrarse</div>
			<%		
			}
			%>
			<form action="<%=request.getContextPath()%>/Usuario" method="POST">
				<div class="card">
					<div class="card-header">
						<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<h3>Registrarse</h3>
			    				</div>
		    				</div>
		    			</div>
					</div>
		    		<div class="card-body">
	    				<div class="form-group">
							<label for="correo">Correo</label>
							<input type="email" class="form-control" name="correo" required/>
						</div>
						<div class="form-group">
							<label for="clave">Contraseña</label>
							<input type="password" class="form-control" name="clave" required/>
						</div>
						<div class="form-group">
							<label for="primerNombre">Primer Nombre</label>
							<input type="text" class="form-control" name="primerNombre" required/>
						</div>
						<div class="form-group">
							<label for="segundoNombre">Segundo Nombre</label>
							<input type="text" class="form-control" name="segundoNombre" required/>
						</div>
						<div class="form-group">
							<label for="apellidoPaterno">Apellido Paterno</label>
							<input type="text" class="form-control" name="apellidoPaterno" required/>
						</div>
						<div class="form-group">
							<label for="apellidoMaterno">Apellido Materno</label>
							<input type="text" class="form-control" name="apellidoMaterno" required/>
						</div>
		    		</div>
		    		<div class="card-footer">
		    			<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<button class="btn btn-primary" type="submit" name="opcion" value="1">Registrarse</button>
			    				</div>
		    				</div>
		    			</div>
		    		</div>
	    		</div>
	    	</form>
		</div>
    	
  </div>
<%
	session.removeAttribute("registro");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Usuario?vista=registrarse");
}
%>
</body>
</html>