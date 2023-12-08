<%@page import="cl.pymap.pymapwar.util.CarritoUtil"%>
<%@page import="cl.pymap.pymapejb.model.Producto"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
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
if (session.getAttribute("renderizarVista") == "misCarritos") {
	List<String[]> carritos = (List<String[]>)session.getAttribute("carritos");
%>

	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
		
	<%
	if (carritos == null) {
	%>
		<h1 class="m-5">El carrito esta vacío</h1>
	<%
	}
	else if (carritos.size() == 1) {
		String negocio = carritos.get(0)[0];
		String nombreCarrito = carritos.get(0)[2];
	%>
		<div class="m-5 w-100 mx-auto">
			<div class="container-fluid h-100">
				<div class="row w-100 m-0 p-0 align-items-center">
					<div class="col text-center mb-2">
						<a href="<%=request.getContextPath()%>/negocio/detalle?negocio=<%=negocio%>">Dirigirse al Negocio</a>
					</div>
				</div>
			</div>
		<%
		if (session.getAttribute("carrito" + "->" + negocio) != null && ((List<Object[]>)session.getAttribute("carrito" + "->" + negocio)).size() >= 1) {
			List<Object[]> carrito = (List<Object[]>)session.getAttribute("carrito" + "->" + negocio);
		%>
			<table class="table table-bordered">
				<thead class='thead-light'>
					<tr>
						<th>Imagen</th>
						<th>Nombre</th>
						<th>Precio</th>
						<th>Unidades</th>
						<th>Acción</th>
					</tr>
				</thead>
			<%
			for (int i = 0; i < carrito.size(); i++) {
			%>
				<tr>
					<td>
						<img src="<%=request.getContextPath() + "/uploads/models/producto/images/" + ((Producto)(carrito.get(i)[3])).getImagen()%>" style="width: 80px"/>
					</td>
					<td>
						<h5><%=((Producto)(carrito.get(i)[3])).getNombre()%></h5>
					</td>
					<td>
						<%="$" + new DecimalFormat().format((carrito.get(i)[1]))%>
					</td>
					<td>
						<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center">
			    					<%=carrito.get(i)[2]%>
			    				</div>
		    				</div>
		    			</div>
						<div class="row w-100 ml-1">
							<div class="col-6">
								<a class="btn btn-success float-right" href="<%=request.getContextPath() + "/Carrito?opcion=4" + "&negocio=" + negocio + "&indice=" + i%>">+</a>
							</div>
							<div class="col-6">
								<a class="btn btn-danger float-left" href="<%=request.getContextPath() + "/Carrito?opcion=5" + "&negocio=" + negocio + "&indice=" + i%>">-</a>
							</div>
						</div>			
					</td>
					<td>
						<div class="container-fluid h-100">
		    				<div class="row w-100 m-0 p-0 align-items-center">
			    				<div class="col text-center mt-4">
			    					<a class="btn btn-danger" href="<%=request.getContextPath() + "/Carrito?opcion=2" + "&negocio=" + negocio + "&indice=" + i%>">Eliminar</a>
			    				</div>
		    				</div>
		    			</div>
					</td>
				</tr>
			<%
			}
			%>
			</table>
			
			<div class="row">
				<div class="col-6">
					<a class="btn btn-danger" href="<%=request.getContextPath() + "/Carrito?opcion=3&negocio=" + negocio%>">Vaciar carrito</a>
				</div>
				<div class="col-6">
					<a class="btn btn-primary float-right" href="<%=request.getContextPath()%>/Compra?opcion=1&negocio=<%=negocio%>&carrito=<%="carrito" + "->" + negocio%>">Realizar Compra</a>
					<h3 class="mr-3 mt-2 float-right ">Total: $<%=new DecimalFormat().format((new CarritoUtil().obtenerTotal(carrito)))%></h3>
				</div>
			</div>
				
		</div>
		<%
		}
		%>
	<%
	}
	else {
	%>
		<table class="table table-bordered m-5">
			<thead class='thead-light'>
				<tr>
					<th>Negocio</th>
					<th>Acción 1</th>
					<th>Acción 2</th>
				</tr>
			</thead>
		<%
		for (String[] carrito : carritos) {
		%>
			<tr>
				<td>
					<%=carrito[1]%>
				</td>
				<td>
					<div class="container-fluid h-100">
	    				<div class="row w-100 m-0 p-0 align-items-center">
		    				<div class="col text-center">
		    					<a class="btn btn-primary" href="<%=request.getContextPath()%>/Carrito?vista=mostrar&negocio=<%=carrito[0]%>">Mostrar</a>
		    				</div>
	    				</div>
	    			</div>
				</td>
				<td>
					<div class="container-fluid h-100">
	    				<div class="row w-100 m-0 p-0 align-items-center">
		    				<div class="col text-center">
		    					<a class="btn btn-danger" href="<%=request.getContextPath()%>/Carrito?opcion=3&negocio=<%=carrito[0]%>">Eliminar</a>
		    				</div>
	    				</div>
	    			</div>
				</td>
			</tr>
		<%
		}
		%>	
		</table>
	<%
	}
	%>
	
	</div>
    	
<%
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Carrito?vista=mis_carritos");
}
%>
</body>
</html>