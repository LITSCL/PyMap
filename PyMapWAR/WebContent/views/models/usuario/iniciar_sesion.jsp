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
if (session.getAttribute("renderizarVista") == "iniciarSesion") {
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
		<div class="m-5 w-50 mx-auto">
			<%
			if (session.getAttribute("errorLogin") == "Credenciales incorrectas") {
			%>
				<div class="alert alert-danger"><%=session.getAttribute("errorLogin")%></div>
			<%
			}
			%>
			<form action="<%=request.getContextPath()%>/Usuario" method="POST">
				<div class="card">
					<div class="card-header">
						<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<h3>Iniciar Sesión</h3>
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
		    		</div>
		    		<div class="card-footer">
		    			<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<button class="btn btn-primary" type="submit" name="opcion" value="2">Iniciar Sesión</button>
			    				</div>
		    				</div>
		    			</div>
		    		</div>
	    		</div>
	    	</form>
		</div>
		
	</div>
<%
	session.removeAttribute("errorLogin");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Usuario?vista=iniciar_sesion");
}
%>
</body>
</html>