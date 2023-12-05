package cl.pymap.pymapwar.pagination;

import java.util.ArrayList;
import java.util.List;

import cl.pymap.pymapejb.fk.NegocioFK;
import cl.pymap.pymapejb.fk.ProductoFK;
import cl.pymap.pymapejb.model.Producto;
import cl.pymap.pymapejb.model.ProductoNegocio;

public class ProductoPaginador {
	private int paginaActual = 0;
	private int totalPaginas = 0;
	private int totalResultados = 0;
	private int resultadosPorPagina = 0;
	private int indice = 0;
	
	private List<Producto> productos = new ArrayList<Producto>();
	
	public ProductoPaginador(int paginaActual, int resultadosPorPagina, List<Producto> productos) {
		this.paginaActual = paginaActual;
		this.totalResultados = productos.size();
		this.resultadosPorPagina = resultadosPorPagina;
		this.totalPaginas = totalResultados / resultadosPorPagina;
		this.indice = (this.paginaActual - 1) * (this.resultadosPorPagina);
		
		if (this.totalResultados > this.totalPaginas * this.resultadosPorPagina) {
			this.totalPaginas++;
		}
		
		this.productos = productos;
	}
	
	public String generarRegistros(String formato, String raiz, String servlet, String vista, List<ProductoNegocio> productosNegocio) {
		String resultado = "";
		
		if (productos.size() != 0) {
			if (this.paginaActual <= this.totalPaginas) {
				if (formato.equalsIgnoreCase("tarjeta")) {	
					int contador = 0;
					for (Producto p : this.productos) {	
						if (contador >= this.indice) {
							if (contador - this.indice == this.resultadosPorPagina) {
								break;
							}
							resultado+="<div class='card m-2 p-2' style='min-width: 320px; max-width: 320px'>";
							resultado+=    "<div class='card-header'>";
							resultado+=        "<div class='container-fluid h-100'>";
							resultado+=            "<div class='row w-100 m-0 p-0 align-items-center'>";
							resultado+=                "<div class='col text-center'>";
							resultado+=                    "<h5>" + p.getNombre() + "</h5>";
							resultado+=                "</div>";
							resultado+=            "</div>";
							resultado+=        "</div>";
							resultado+=    "</div>";
							resultado+=    "<div class='card-body'>";
							resultado+=        "<img class='card-img-top' src='" + raiz + "/uploads/models/producto/images/" + p.getImagen() + "'>";
							resultado+=    "</div>";
							resultado+=    "<div class='card-footer'>";
							int precio = 0;
							for (ProductoNegocio pn : productosNegocio) {
								if (pn.getProductoFK() == p.getId()) {
									if (pn.getPrecio() != 0) {
										resultado+="<p class='card-text'>" + "Precio: $" + (int)pn.getPrecio() + "</p>";
										precio = (int)pn.getPrecio();
									}
									else {
										resultado+="<p class='card-text'>Precio: Sin precio asignado</p>";
									}
								}
							}
							resultado+=        "<div class='container-fluid h-100'>";
							resultado+=            "<div class='row w-100 m-0 p-0 align-items-center'>";
							resultado+=                "<div class='col text-center'>";
							if (precio != 0) {
								resultado+= 		       "<a class='btn btn-success' href='" + raiz + "/Carrito?opcion=1" + "&id=" + p.getId() + "&negocio=" + productosNegocio.get(0).getNegocioFK() + "'>Agregar al carrito</a>";
							}
							else {
								resultado+= 			   "<a class='btn btn-success disabled' href='" + raiz + "#" + "'>Agregar al carrito</a>";
							}
							resultado+=                "</div>";
							resultado+=            "</div>";
							resultado+=        "</div>";
							resultado+=    "</div>";
							resultado+="</div>";
						}
						contador++;	
					}					
					return resultado;
				}
				else if (formato.equalsIgnoreCase("tabla")) {
					resultado+= "<table class='table table-bordered'>";
				    resultado+=     "<thead class='thead-light'>";
				    resultado+=    		"</tr>";	
					resultado+=         	"<th>Nombre</th>";
				    resultado+=         	"<th>Marca</th>";
					resultado+=         	"<th>Precio</th>";
					resultado+=         	"<th>Imagen</th>";
					resultado+=         	"<th>Opción 1</th>";
					resultado+=         	"<th>Opción 2</th>";
				    resultado+=			"</tr>";
				    resultado+=		"</thead>";
				    resultado+=		"<tbody>";
					
					int contador = 0;
					for (Producto p : this.productos) {
						if (contador >= this.indice) {
							if (contador - this.indice == this.resultadosPorPagina) {
								break;
							}
							resultado+="<tr>";
							resultado+=    "<th scope='row'>";
							resultado+=	       "<div class='container-fluid h-100 m-0 p-0 mt-2'>";
							resultado+=	           "<div class='row w-100 m-0 p-0 align-items-center'>";
							resultado+=	               "<div class='col text-center'>";
							resultado+=    			       p.getNombre();
							resultado+=    			   "</div>";
							resultado+=            "</div>";
							resultado+=        "</div>";
							resultado+=    "</th>";
							resultado+=    "<td>";
							resultado+=	       "<div class='container-fluid h-100 m-0 p-0 mt-2'>";
							resultado+=	           "<div class='row w-100 m-0 p-0 align-items-center'>";
							resultado+=	               "<div class='col text-center'>";
							resultado+=    			       new ProductoFK().getMarcaNombre(p);
							resultado+=    			   "</div>";
							resultado+=            "</div>";
							resultado+=        "</div>";
							resultado+=    "</td>";
							resultado+=    "<td>";
							resultado+=	       "<div class='container-fluid h-100 m-0 p-0 mt-2'>";
							resultado+=	           "<div class='row w-100 m-0 p-0 align-items-center'>";
							resultado+=	               "<div class='col text-center'>";
							boolean productoConPrecio = false;
							int precio = 0;
							for (ProductoNegocio pn : productosNegocio) {
								if (pn.getProductoFK() == p.getId()) {
									if (pn.getPrecio() != 0) {
										precio = (int)pn.getPrecio();
										productoConPrecio = true;
									}	
								}
							}
							if (productoConPrecio == true) {
								resultado+=                "<input id='" + p.getId() + "'" + " class='form-control text-success' type='number' name='precio' value='" + precio + "'" + " placeholder='EJ: $5000' style='width: 115px'>";
							}
							else {
								resultado+=    			   "<input id='" + p.getId() + "'" + " class='form-control' type='number' name='precio' placeholder='EJ: $5000' style='width: 115px'>";
							}
							resultado+=    			   "</div>";
							resultado+=            "</div>";
							resultado+=        "</div>";
							resultado+=    "</td>";
							resultado+=    "<td>";
							resultado+=	       "<div class='container-fluid h-100 m-0 p-0 mt-2'>";
							resultado+=	           "<div class='row w-100 m-0 p-0 align-items-center'>";
							resultado+=	               "<div class='col text-center'>";
							resultado+=            	       "<img src='" + raiz + "/uploads/models/producto/images/" + p.getImagen() + "'" + " style='width: 100px'/>";
							resultado+=    			   "</div>";
							resultado+=            "</div>";
							resultado+=        "</div>";
							resultado+=    "</td>";
							boolean productoYaAgregado = false;
							ProductoNegocio productoNegocioExistente = new ProductoNegocio();
							for (ProductoNegocio pn : productosNegocio) {
								if (pn.getProductoFK() == p.getId()) {
									resultado+=    "<td>";
									resultado+=	       "<div class='container-fluid h-100 m-0 p-0 mt-2'>";
									resultado+=	           "<div class='row w-100 m-0 p-0 align-items-center'>";
									resultado+=	               "<div class='col text-center'>";
									resultado+=            	       "<a class='btn btn-danger text-white boton-rojo' data-idProducto='" + p.getId() + "'" + " data-idProductoNegocio='" + pn.getId() + "'" + ">Eliminar</a>";
									resultado+=    			   "</div>";
									resultado+=            "</div>";
									resultado+=        "</div>";
									resultado+=    "</td>";
									productoNegocioExistente = pn;
									productoYaAgregado = true;
								}
							}
							if (productoYaAgregado == false) {
								resultado+=    "<td>";
								resultado+=	       "<div class='container-fluid h-100 m-0 p-0 mt-2'>";
								resultado+=	           "<div class='row w-100 m-0 p-0 align-items-center'>";
								resultado+=	               "<div class='col text-center'>";
								resultado+=            	       "<a class='btn btn-success text-white boton-verde' data-idProducto='" + p.getId() + "'" + ">Agregar</a>";
								resultado+=    			   "</div>";
								resultado+=            "</div>";
								resultado+=        "</div>";
								resultado+=    "</td>";
							}
							resultado+=    "<td>";
							resultado+=	       "<div class='container-fluid h-100 m-0 p-0 mt-2'>";
							resultado+=	           "<div class='row w-100 m-0 p-0 align-items-center'>";
							resultado+=	               "<div class='col text-center'>";
							if (productoYaAgregado == false) {
								resultado+=            	       "<a class='btn btn-warning disabled boton-amarillo' data-idProducto='" + p.getId() + "'" + " data-idProductoNegocio='" + productoNegocioExistente.getId() + "'" + ">Modificar</a>";
							}
							else {
								resultado+=            	       "<a class='btn btn-warning boton-amarillo' data-idProducto='" + p.getId() + "'" + " data-idProductoNegocio='" + productoNegocioExistente.getId() + "'" + ">Modificar</a>";
							}
							resultado+=    			   "</div>";
							resultado+=            "</div>";
							resultado+=        "</div>";
							resultado+=    "</td>";
							resultado+="</tr>";
						}
						contador++;				
					}
				    resultado+=		"</tbody>";
					resultado+="</table>";
					
					return resultado;
				}
				else {
					return resultado;
				}
			}
			else {
				resultado = "Pagina Inexistente";
				return resultado;
			}
		}
		else {
			resultado = "Sin Registros";
			return resultado;
		}
	}
	
	public String generarNumeros(String raiz, String opcion, String servlet, String vista, String negocio, String seleccion) {
		String resultado = "";
		String actual = "";
		
		if (opcion.equals("1")) {
			if (this.paginaActual <= this.totalPaginas) {
				resultado+= "<ul class='pagination pagination-md'>";
				for (int i = 1; i <= this.totalPaginas; i++) {
					if (i == this.paginaActual) {
						actual = "class='page-item active'";
					}
					else {
						actual = "class='page-item'";
					}	
					resultado+="<li " + actual + ">" + "<a class='page-link'" + " href='" + raiz + "/" + servlet + "?vista=" + vista + "&negocio=" + negocio + "&seleccion=" + seleccion + "&pagina=" + i + "'>" + i + "</a></li>";
				}
				resultado+= "</ul>";
			}
		}
		
		if (opcion.equals("2")) {
			if (this.paginaActual <= this.totalPaginas) {
				resultado+= "<ul class='pagination pagination-md'>";
				for (int i = 1; i <= this.totalPaginas; i++) {
					if (i == this.paginaActual) {
						actual = "class='page-item active'";
					}
					else {
						actual = "class='page-item'";
					}	
					resultado+="<li " + actual + ">" + "<a class='page-link'" + " href='" + raiz + "/" + servlet + "?vista=" + vista + "&negocio=" + negocio + "&pagina=" + i + "'>" + i + "</a></li>";
				}
				resultado+= "</ul>";
			}
		}

		return resultado;
	}
}