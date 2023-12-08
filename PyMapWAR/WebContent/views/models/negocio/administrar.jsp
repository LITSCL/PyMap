<%@page import="cl.pymap.pymapejb.model.Negocio"%>
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
if (session.getAttribute("renderizarVista") == "administrar") {
	String negocio = request.getParameter("negocio");
	String seleccion = request.getParameter("seleccion");
	Negocio n = (Negocio)session.getAttribute("negocio");
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
	<%
	if (seleccion.equals("1")) {
	%>
		<div class="row mt-5 pt-2 w-100 mx-auto border-top border-bottom">
			<div class="col-6 text-white">
				<div class="container-fluid h-100">
    				<div class="row w-100 m-0 p-0 align-items-center">
	    				<div class="col text-center">
							<h3><a class="text-warning" href="<%=request.getContextPath()%>/Negocio?vista=administrar&negocio=<%=negocio%>&seleccion=1">INFORMACIÓN</a></h3>
	    				</div>
    				</div>
    			</div>
 			</div>
     		
			<div class="col-6 text-white">
				<div class="container-fluid h-100">
    				<div class="row w-100 m-0 p-0 align-items-center">
	    				<div class="col text-center">
							<h3><a class="text-dark" href="<%=request.getContextPath()%>/Negocio?vista=administrar&negocio=<%=negocio%>&seleccion=2">PRODUCTOS</a></h3>
	    				</div>
    				</div>
    			</div>
 			</div>
		</div>
		<div class="mt-3 w-50 mx-auto">
		<%
		if (session.getAttribute("modificarNegocio") == "Exitoso") {
		%>
			<div class="alert alert-success">Negocio modificado correctamente</div>
		<%
		}
		if (session.getAttribute("modificarNegocio") == "Fallido") {	
		%>
			<div class="alert alert-danger">Hubo un error al modificar el negocio</div>
		<%		
		}
		%>
		</div>
		<div class="mt-3 w-50 mx-auto">
			<form action="<%=request.getContextPath()%>/Negocio" method="POST" enctype="multipart/form-data">
				<div class="card">
					<div class="card-header">
						<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<h3>Modificar Información</h3>
			    				</div>
		    				</div>
		    			</div>
					</div>
		    		<div class="card-body">
		    			<div class="form-group">
							<input type="hidden" class="form-control" name="rut" value="<%=n.getRut()%>" required/>
						</div>
						<div class="form-group">
							<label for="nombre">Nombre</label>
							<input type="text" class="form-control" name="nombre" value="<%=n.getNombre()%>" required/>
						</div>
						<div class="form-group">
							<label for="ciudad">Ciudad</label>
							<input type="text" class="form-control" name="ciudad" value="<%=n.getCiudad()%>" required/>
						</div>
						<div class="form-group">
							<label for="comuna">Comuna</label>
							<input type="text" class="form-control" name="comuna" value="<%=n.getComuna()%>" required/>
						</div>
						<div class="form-group">
							<label for="calle">Calle</label>
							<input type="text" class="form-control" name="calle" value="<%=n.getCalle()%>" required/>
						</div>
						<%
						String[] diasAtencionSeparados = n.getDiasAtencion().split(";");
						boolean lunes = false, martes = false, miercoles = false, jueves = false, viernes = false, sabado = false, domingo = false;
						for (int i = 0; i < diasAtencionSeparados.length; i++) {
							if (diasAtencionSeparados[i].equals("Lunes")) {
								lunes = true;
							}
							else if (diasAtencionSeparados[i].equals("Martes")) {
								martes = true;
							}
							else if (diasAtencionSeparados[i].equals("Miércoles")) {
								miercoles = true;
							}
							else if (diasAtencionSeparados[i].equals("Jueves")) {
								jueves = true;
							}
							else if (diasAtencionSeparados[i].equals("Viernes")) {
								viernes = true;
							}
							else if (diasAtencionSeparados[i].equals("Sábado")) {
								sabado = true;
							}
							else if (diasAtencionSeparados[i].equals("Domingo")) {
								domingo = true;
							}
							else {
								//
							}
						}
						%>
						<div class="form-group">
						    <label for="diasAtencion">Dias de atención</label>
						    <select class="form-control" name="diasAtencion" multiple>
						    	<option <% if (lunes == true) { out.print("selected"); } %>>Lunes</option>
						      	<option <% if (martes == true) { out.print("selected"); } %>>Martes</option>
						      	<option <% if (miercoles == true) { out.print("selected"); } %>>Miércoles</option>
						      	<option <% if (jueves == true) { out.print("selected"); } %>>Jueves</option>
						      	<option <% if (viernes == true) { out.print("selected"); } %>>Viernes</option>
						      	<option <% if (sabado == true) { out.print("selected"); } %>>Sábado</option>
						      	<option <% if (domingo == true) { out.print("selected"); } %>>Domingo</option>
						    </select>
						</div>
						<%
						String[] metodosPagoSeparados = n.getMetodosPago().split(";");
						boolean debito = false, credito = false, transferencia = false, efectivo = false;
						for (int i = 0; i < metodosPagoSeparados.length; i++) {
							if (metodosPagoSeparados[i].equals("Débito")) {
								debito = true;
							}
							else if (metodosPagoSeparados[i].equals("Crédito")) {
								credito = true;
							}
							else if (metodosPagoSeparados[i].equals("Transferencia")) {
								transferencia = true;
							}
							else if (diasAtencionSeparados[i].equals("Efectivo")) {
								efectivo = true;
							}
							else {
								//
							}
						}
						%>
						<div class="form-group">
						    <label for="metodosPago">Métodos de pago</label>
							<div class="form-group">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="1" name="metodosPago" value="Débito" <% if (debito == true) { out.print("checked"); } %> style="cursor: pointer">
									<label class="form-check-label" for="1" style="cursor: pointer">Débito</label>
								</div>
								<div class="form-check form-check-inline">
								  	<input class="form-check-input" type="checkbox" id="2" name="metodosPago" value="Crédito" <% if (credito == true) { out.print("checked"); } %> style="cursor: pointer">
								  	<label class="form-check-label" for="2" style="cursor: pointer">Crédito</label>
								</div>
								<div class="form-check form-check-inline">
								  	<input class="form-check-input" type="checkbox" id="3" name="metodosPago" value="Transferencia" <% if (transferencia == true) { out.print("checked"); } %> style="cursor: pointer">
								  	<label class="form-check-label" for="3" style="cursor: pointer">Transferencia</label>
								</div>
								<div class="form-check form-check-inline">
								  	<input class="form-check-input" type="checkbox" id="4" name="metodosPago" value="Efectivo" <% if (efectivo == true) { out.print("checked"); } %> style="cursor: pointer">
								  	<label class="form-check-label" for="4" style="cursor: pointer">Efectivo</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="logo">Logo</label>
							<input class="form-control-file" type="file" name="logo" style="cursor: pointer"/>
							<img class="mt-3" src="<%=request.getContextPath() + "/uploads/models/negocio/images/" + n.getLogo()%>" style="width: 180px"/>
						</div>
		    		</div>
		    		<div class="card-footer">
		    			<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<button class="btn btn-primary" type="submit" name="opcion" value="4">Modificar</button>
			    				</div>
		    				</div>
		    			</div>
		    		</div>
	    		</div>
	    	</form>
		</div>
	<%
	}
	else if (seleccion.equals("2")) {
	%>
		<div class="row mt-5 pt-2 w-100 mx-auto border-top border-bottom">
			<div class="col-6 text-white">
				<div class="container-fluid h-100">
    				<div class="row w-100 m-0 p-0 align-items-center">
	    				<div class="col text-center">
							<h3><a class="text-dark" href="<%=request.getContextPath()%>/Negocio?vista=administrar&negocio=<%=negocio%>&seleccion=1">INFORMACIÓN</a></h3>
	    				</div>
    				</div>
    			</div>
 			</div>
     		
			<div class="col-6 text-white">
				<div class="container-fluid h-100">
    				<div class="row w-100 m-0 p-0 align-items-center">
	    				<div class="col text-center">
							<h3><a class="text-warning" href="<%=request.getContextPath()%>/Negocio?vista=administrar&negocio=<%=negocio%>&seleccion=2">PRODUCTOS</a></h3>
	    				</div>
    				</div>
    			</div>
 			</div>
		</div>
		<div class="mt-3 w-100 mx-auto">
			<section class="row m-0 p-0">
			<%
			if (session.getAttribute("registros") == "Pagina Inexistente") {
			%>
				<div class="alert col-8 m-2 alert-warning">No se han encontrado resultados en esta página</div>
			<%
			}
			else if (session.getAttribute("registros") == "Sin Registros") {
			%>
				<div class="alert col-8 m-2 alert-warning">No existen marcas para este tipo de negocio</div>
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
	}
	%>
    	
	</div>
<%
	session.removeAttribute("registros");
	session.removeAttribute("numeros");
	session.removeAttribute("negocio");
	session.removeAttribute("modificarNegocio");
	session.removeAttribute("renderizarVista");
}
else {
	String negocio = request.getParameter("negocio");
	String seleccion = request.getParameter("seleccion");
	response.sendRedirect(request.getContextPath() + "/Negocio?vista=administrar&negocio=" + negocio + "&seleccion=" + seleccion);
}
%>
</body>
</html>