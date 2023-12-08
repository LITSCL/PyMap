<%@page import="cl.pymap.pymapejb.model.Compra"%>
<%@page import="cl.pymap.pymapejb.model.Producto"%>
<%@page import="java.text.DecimalFormat"%>
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
if (session.getAttribute("renderizarVista") == "misCompras") {
	List<Compra> compras = (List<Compra>)session.getAttribute("compras");
%>
	<%@ include file='../../includes/header.jsp' %>
		
	<div class="container">
	
	<%
	if (compras.size() >= 1) {
	%>
		<div class="m-5 w-100 mx-auto">
			<table class="table table-bordered">
				<thead class='thead-light'>
					<tr>
						<th>N° Compra</th>
						<th>Coste</th>
						<th>Fecha</th>
						<th>Negocio</th>
					</tr>
				</thead>
			<%
			for (Compra c : compras) {
			%>
				<tr>
					<td>
						<%=c.getId()%>
					</td>
					<td>
						$<%=new DecimalFormat().format(c.getCoste())%>
					</td>
					<td>
						<%=c.getFecha()%>
					</td>
					<td>
						<%=c.getNegocioFK()%>
					</td>
				</tr>
			<%
			}
			%>
			</table>
		</div>
	<%
	}
	else {
	%>
		<section class="row m-0 mt-5 p-0">
			<div class="alert col-8 m-2 alert-warning">No has realizado compras</div>
		</section>	
	<%
	}
	%>
    	
  	</div>
<%
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Compra?vista=mis_compras");
}
%>
</body>
</html>