<%@page import="cl.pymap.pymapejb.model.Usuario"%>

<header>	
<%
if (session.getAttribute("usuario") == null) {
%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light" style="height: 80px">
		<a class="navbar-brand" href="<%=request.getContextPath()%>">PyMap</a>
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	  	</button>
	  	<div class="collapse navbar-collapse ml-5">
	    	<ul class="navbar-nav row w-50">
	      		<li class="nav-item">
	        		<a class="nav-link <% if (session.getAttribute("renderizarVista") == "verNegocios") { out.print("active"); } %>" href="<%=request.getContextPath()%>/negocio/ver-negocios">Ver Negocios</a>
	      		</li>
	    	</ul>
	    	<ul class="navbar-nav row w-50 justify-content-right">
	      		<li class="nav-item col text-right">
	        		<a class="nav-link <% if (session.getAttribute("renderizarVista") == "iniciarSesion") { out.print("active"); } %>" href="<%=request.getContextPath()%>/usuario/iniciar-sesion">Iniciar Sesión</a>
	      		</li>
	      		<li class="nav-item col text-left">
	        		<a class="nav-link <% if (session.getAttribute("renderizarVista") == "registrarse") { out.print("active"); } %>" href="<%=request.getContextPath()%>/usuario/registrarse">Registrarse</a>
	     		</li>
	    	</ul>
		</div>
	</nav>
<%
}
%>
	
<%
if (session.getAttribute("usuario") != null && ((Usuario)session.getAttribute("usuario")).getTipo().equals("Cliente")) {
%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light" style="height: 80px">
		<a class="navbar-brand" href="<%=request.getContextPath()%>">PyMap</a>
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	  	</button>
	  	<div class="collapse navbar-collapse ml-5">
	    	<ul class="navbar-nav row w-50">
	      		<li class="nav-item">
	        		<a class="nav-link <% if (session.getAttribute("renderizarVista") == "publicarPasoUno" || session.getAttribute("renderizarVista") == "publicarPasoDos" || session.getAttribute("renderizarVista") == "publicarPasoTres") { out.print("active"); } %>" href="<%=request.getContextPath()%>/negocio/publicar-paso-1">Publicar Negocio</a>
	      		</li>
	      		<li class="nav-item">
	        		<a class="nav-link <% if (session.getAttribute("renderizarVista") == "misNegocios") { out.print("active"); } %>" href="<%=request.getContextPath()%>/negocio/mis-negocios">Mis Negocios</a>
	     		</li>
	      		<li class="nav-item">
	        		<a class="nav-link <% if (session.getAttribute("renderizarVista") == "verNegocios") { out.print("active"); } %>" href="<%=request.getContextPath()%>/negocio/ver-negocios">Ver Negocios</a>
	     		</li>
	    	</ul>
			<div class="navbar-collapse collapse w-100 order-3 dual-collapse" style="padding-right: 50px;">
		    	<ul class="navbar-nav ml-auto">
		    		<li class="nav-item" style="margin-top: 12px">
		        		<a class="nav-link <% if (session.getAttribute("renderizarVista") == "mostrar" || session.getAttribute("renderizarVista") == "misCarritos") { out.print("active"); } %>" href="<%=request.getContextPath()%>/carrito/mis-carritos">Carrito</a>
		      		</li>
		      		<li class="nav-item " style="margin-top: 12px">
		        		<a class="nav-link <% if (session.getAttribute("renderizarVista") == "misCompras") { out.print("active"); } %>" href="<%=request.getContextPath()%>/compra/mis-compras">Compras</a>
		      		</li>
		      		<li class="nav-item">
		        		<a class="nav-link" href="<%=request.getContextPath()%>/usuario/editar-perfil"><img src="<%=request.getContextPath()%>/uploads/models/usuario/images/<%=((Usuario)session.getAttribute("usuario")).getImagen()%>" style="width: 50px; border-radius: 999px"/></a>
		     		</li>
		     		<li class="nav-item" style="margin-top: 12px">
		        		<a class="nav-link" href="<%=request.getContextPath()%>/Usuario?opcion=1">Cerrar Sesión</a>
		      		</li>
		    	</ul>
		    </div>
		</div>
	</nav>
<%
}
%>

<%
if (session.getAttribute("usuario") != null && ((Usuario)session.getAttribute("usuario")).getTipo().equals("Administrador")) {
%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light" style="height: 80px">
		<a class="navbar-brand" href="<%=request.getContextPath()%>">PyMap</a>
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	  	</button>
	  	<div class="collapse navbar-collapse ml-5">
	    	<ul class="navbar-nav row w-50">
	      		<li class="nav-item">
	        		<a class="nav-link <% if (session.getAttribute("renderizarVista") == "agregarMarca") { out.print("active"); } %>" href="<%=request.getContextPath()%>/marca/agregar">Agregar Marca</a>
	      		</li>
	      		<li class="nav-item">
	        		<a class="nav-link <% if (session.getAttribute("renderizarVista") == "agregarProducto") { out.print("active"); } %>" href="<%=request.getContextPath()%>/producto/agregar">Agregar Producto</a>
	     		</li>
	    	</ul>
	    	<div class="navbar-collapse collapse w-100 order-3 dual-collapse" style="padding-right: 50px;">
		    	<ul class="navbar-nav ml-auto">
		    		<li class="nav-item">
		        		<a class="nav-link" href="<%=request.getContextPath()%>/usuario/editar-perfil"><img src="<%=request.getContextPath()%>/uploads/models/usuario/images/<%=((Usuario)session.getAttribute("usuario")).getImagen()%>" style="width: 50px; border-radius: 999px"/></a>
		     		</li>
		      		<li class="nav-item" style="margin-top: 12px">
		        		<a class="nav-link" href="<%=request.getContextPath()%>/Usuario?opcion=1">Cerrar Sesión</a>
		      		</li>
		    	</ul>
	    	</div>
		</div>
	</nav>
<%
}
%>
</header>