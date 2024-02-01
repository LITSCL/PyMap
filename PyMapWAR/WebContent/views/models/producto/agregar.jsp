<%@page import="cl.pymap.pymapejb.model.TipoProducto"%>
<%@page import="cl.pymap.pymapejb.model.Marca"%>
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
if (session.getAttribute("renderizarVista") == "agregarProducto") {
	List<Marca> marcas = (List<Marca>)session.getAttribute("marcas");
	List<TipoProducto> tiposProducto = (List<TipoProducto>)session.getAttribute("tiposProducto");
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
		<div class="m-5 w-50 mx-auto">
			<%
			if (session.getAttribute("agregarProducto") == "Exitoso") {
			%>
				<div class="alert alert-success">Producto agregado correctamente</div>
			<%
			}
			if (session.getAttribute("agregarProducto") == "Fallido") {	
			%>
				<div class="alert alert-danger">Hubo un error al agregar el producto</div>
			<%		
			}
			%>
			<form action="<%=request.getContextPath()%>/Producto" method="POST" enctype="multipart/form-data">
				<div class="card">
					<div class="card-header">
						<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<h3>Agregar Producto</h3>
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
							<label for="descripcion">Descripción</label>
						    <textarea class="form-control" rows="3" name="descripcion"></textarea>
						</div>
						<div class="form-group">
							<label for="precio">Precio</label>
							<input type="number" class="form-control" name="precio"/>
						</div>
						<div class="form-group">
							<label for="imagen">Imagen</label>
							<input class="form-control-file" type="file" name="imagen" required style="cursor: pointer"/>
						</div>
						<div class="form-group">
							<label for="marca">Marca</label>
							<select id="marca" class="form-control" name="marca" >
							<% 
							for (Marca m : marcas) { 
							%>
						    	<option value="<%=m.getId()%>"><%=m.getNombre()%></option>
					      	<%
							}
					      	%>
						    </select>
						</div>
						<div class="form-group">
							<label for="tipoProducto">Tipo de producto</label>
							<select id="tipoProducto" class="form-control" name="tipoProducto" >
							<% 
							for (TipoProducto tp : tiposProducto) { 
							%>
						    	<option class="opcionTipoProducto" value="<%=tp.getId()%>"><%=tp.getNombre()%></option>
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
	session.removeAttribute("marcas");
	session.removeAttribute("tiposProducto");
	session.removeAttribute("agregarProducto");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Producto?vista=agregar");
}
%>
</body>
</html>