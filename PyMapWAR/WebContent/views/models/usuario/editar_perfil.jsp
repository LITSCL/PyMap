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
if (session.getAttribute("renderizarVista") == "editarPerfil") {
	Usuario u = (Usuario)session.getAttribute("usuario");
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
		<div class="m-5 w-50 mx-auto">
			<%
			if (session.getAttribute("editarPerfil") == "Exitoso") {
			%>
				<div class="alert alert-success">Perfil editado correctamente</div>
			<%
			}
			if (session.getAttribute("editarPerfil") == "Fallido") {	
			%>
				<div class="alert alert-danger">Hubo un error al editar el perfil</div>
			<%		
			}
			%>
			<form action="<%=request.getContextPath()%>/Usuario" method="POST" enctype="multipart/form-data">
				<div class="card">
					<div class="card-header">
						<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<h3>Editar Perfil</h3>
			    				</div>
		    				</div>
		    			</div>
					</div>
		    		<div class="card-body">
	    				<div class="form-group">
							<label for="correo">Correo</label>
							<input type="email" class="form-control" name="correo" value="<%=u.getCorreo()%>" required/>
						</div>
						<div class="form-group">
							<label for="clave">Contraseña</label>
							<input type="password" class="form-control" name="clave" value="Clave no cambiada" required/>
						</div>
						<div class="form-group">
							<label for="primerNombre">Primer Nombre</label>
							<input type="text" class="form-control" name="primerNombre" value="<%=u.getPrimerNombre()%>" required/>
						</div>
						<div class="form-group">
							<label for="segundoNombre">Segundo Nombre</label>
							<input type="text" class="form-control" name="segundoNombre" value="<%=u.getSegundoNombre()%>" required/>
						</div>
						<div class="form-group">
							<label for="apellidoPaterno">Apellido Paterno</label>
							<input type="text" class="form-control" name="apellidoPaterno" value="<%=u.getApellidoPaterno()%>" required/>
						</div>
						<div class="form-group">
							<label for="apellidoMaterno">Apellido Materno</label>
							<input type="text" class="form-control" name="apellidoMaterno" value="<%=u.getApellidoMaterno()%>" required/>
						</div>
						<div class="form-group">
							<label for="imagen">Imagen</label>
							<input class="form-control-file" type="file" name="imagen" style="cursor: pointer"/>
							<img class="mt-3" src="<%=request.getContextPath() + "/uploads/models/usuario/images/" + u.getImagen()%>" style="width: 180px"/>
						</div>
		    		</div>
		    		<div class="card-footer">
		    			<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<button class="btn btn-primary" type="submit" name="opcion" value="3">Editar</button>
			    				</div>
		    				</div>
		    			</div>
		    		</div>
	    		</div>
	    	</form>
		</div>
		
	</div>
<%
	session.removeAttribute("editarPerfil");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Usuario?vista=editar_perfil");
}
%>
</body>
</html>