<%@page import="java.util.ArrayList"%>
<%@page import="cl.pymap.pymapejb.model.TipoNegocio"%>
<%@page import="java.util.List"%>
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
if (session.getAttribute("renderizarVista") == "publicarPasoUno") {
	List<TipoNegocio> tiposNegocio = (List<TipoNegocio>)session.getAttribute("tiposNegocio");
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
		<div class="m-5 w-50 mx-auto">
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
			    					<h3>Publicar Negocio (Paso 1/3)</h3>
			    				</div>
		    				</div>
		    			</div>
					</div>
		    		<div class="card-body">
	    				<div class="form-group">
							<label for="rut">Rut</label>
							<input type="text" class="form-control" name="rut" required/>
						</div>
						<div class="form-group">
							<label for="nombre">Nombre</label>
							<input type="text" class="form-control" name="nombre" required/>
						</div>
						<div class="form-group">
							<label for="ciudad">Ciudad</label>
							<input type="text" class="form-control" name="ciudad" required/>
						</div>
						<div class="form-group">
							<label for="comuna">Comuna</label>
							<input type="text" class="form-control" name="comuna" required/>
						</div>
						<div class="form-group">
							<label for="calle">Calle</label>
							<input type="text" class="form-control" name="calle" required/>
						</div>
						<div class="form-group">
						    <label for="diasAtencion">Dias de atención</label>
						    <select class="form-control" name="diasAtencion" multiple>
						    	<option>Lunes</option>
						      	<option>Martes</option>
						      	<option>Miércoles</option>
						      	<option>Jueves</option>
						      	<option>Viernes</option>
						      	<option>Sábado</option>
						      	<option>Domingo</option>
						    </select>
						</div>
						<div class="form-group">
						    <label for="metodosPago">Métodos de pago</label>
							<div class="form-group">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="1" name="metodosPago" value="Débito" style="cursor: pointer">
									<label class="form-check-label" for="1" style="cursor: pointer">Débito</label>
								</div>
								<div class="form-check form-check-inline">
								  	<input class="form-check-input" type="checkbox" id="2" name="metodosPago" value="Crédito" style="cursor: pointer">
								  	<label class="form-check-label" for="2" style="cursor: pointer">Crédito</label>
								</div>
								<div class="form-check form-check-inline">
								  	<input class="form-check-input" type="checkbox" id="3" name="metodosPago" value="Transferencia" style="cursor: pointer">
								  	<label class="form-check-label" for="3" style="cursor: pointer">Transferencia</label>
								</div>
								<div class="form-check form-check-inline">
								  	<input class="form-check-input" type="checkbox" id="4" name="metodosPago" value="Efectivo" style="cursor: pointer">
								  	<label class="form-check-label" for="4" style="cursor: pointer">Efectivo</label>
								</div>
							</div>
						</div>
						<div class="form-group">
						    <label for="tipoNegocio">Tipo de negocio</label>
						    <select class="form-control" name="tipoNegocio">
						    <%
						     for (TipoNegocio tn : tiposNegocio) {
						    %>
						    	<option value="<%=tn.getId()%>"><%=tn.getNombre()%></option>
						    <%
						    }
						    %>
						    </select>
						</div>
		    		</div>
		    		<div class="card-footer">
		    			<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<button class="btn btn-primary" type="submit" name="opcion" value="1">Siguiente</button>
			    				</div>
		    				</div>
		    			</div>
		    		</div>
	    		</div>
	    	</form>
		</div>
    	
	</div>
<%
	session.removeAttribute("tiposNegocio");
	session.removeAttribute("publicarNegocio");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Negocio?vista=publicar_paso_1");
}
%>
</body>
</html>