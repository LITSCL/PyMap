<%@page import="cl.pymap.pymapejb.model.Producto"%>
<%@page import="java.text.DecimalFormat"%>
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
if (session.getAttribute("renderizarVista") == "realizada") {
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
		<div class="m-5 w-100 mx-auto">
		<%
		if (session.getAttribute("crearCompra") == "Exitoso") {
		%>
			<strong class="alert alert-success">Compra realizada correctamente</strong>
			<br/>
			<br/>
			<p>
				Tu pago ya fue procesado, ya puedes retirar tus productos en el local comercial.
			</p>
			<%
			if (session.getAttribute("datosCompra") != null) {
				Object[] datosCompra = (Object[])session.getAttribute("datosCompra");
				List<Object[]> productosCompra = (List<Object[]>)session.getAttribute("productosCompra");
				List<Double> precios = (List<Double>)session.getAttribute("precios");
			%>
				<br/>
				<h3>Datos de la compra:</h3>
				<div class="">
					Numero de compra: <%=datosCompra[0]%>
					<br/>
					Total a pagar: $<%=new DecimalFormat().format(datosCompra[1])%>
					<br/>
					Estado de la compra: <%=datosCompra[2]%>
					<br/>
					Productos:
					<table class="table table-bordered">
						<thead class='thead-light'>
							<tr>
								<th>Imagen</th>
								<th>Nombre</th>
								<th>Precio</th>
								<th>Unidades</th>
							</tr>
						</thead>
					<%
					int contador = 0;
					for (Object[] o : productosCompra) {
					%>
						<tr>
							<td>
								<img src="<%=request.getContextPath() +  "/uploads/models/producto/images/" + ((Producto)o[0]).getImagen()%>" style="width: 80px"/>
							</td>
							<td>
								<%=((Producto)o[0]).getNombre()%>
							</td>
							<td>
								$<%=new DecimalFormat().format(precios.get(contador))%>
							</td>
							<td>
								<%=o[1]%>
							</td>
						</tr>
					<%
					contador++;
					}
					%>
					</table>
				</div>
			<%
			}
			%>
		<%
		}
		if (session.getAttribute("crearCompra") == "Fallido") {
		%>
			<strong class="alert alert-danger">Error al realizar la compra</strong>
			<p>
				Por favor, intenta realizar la compra mas tarde.
			</p>
		<%
		}
		%>
		</div>
    	
  	</div>
<%
	session.removeAttribute("crearCompra");
	session.removeAttribute("datosCompra");
	session.removeAttribute("productosCompra");
	session.removeAttribute("precios");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Compra?vista=realizada");
}
%>
</body>
</html>