package cl.pymap.pymapwar.pagination;

import java.util.ArrayList;
import java.util.List;

import cl.pymap.pymapejb.fk.TipoProductoFK;
import cl.pymap.pymapejb.model.TipoProducto;

public class TipoProductoPaginador {
	private int paginaActual = 0;
	private int totalPaginas = 0;
	private int totalResultados = 0;
	private int resultadosPorPagina = 0;
	private int indice = 0;
	
	private List<TipoProducto> tiposProducto = new ArrayList<TipoProducto>();
	
	public TipoProductoPaginador(int paginaActual, int resultadosPorPagina, List<TipoProducto> tiposProducto) {
		this.paginaActual = paginaActual;
		this.totalResultados = tiposProducto.size();
		this.resultadosPorPagina = resultadosPorPagina;
		this.totalPaginas = totalResultados / resultadosPorPagina;
		this.indice = (this.paginaActual - 1) * (this.resultadosPorPagina);
		
		if (this.totalResultados > this.totalPaginas * this.resultadosPorPagina) {
			this.totalPaginas++;
		}
		
		this.tiposProducto = tiposProducto;
	}
	
	public String generarRegistros(String formato, String raiz, String servlet, String vista) {
		String resultado = "";
		
		if (tiposProducto.size() != 0) {
			if (this.paginaActual <= this.totalPaginas) {
				if (formato.equalsIgnoreCase("tarjeta")) {	
					int contador = 0;
					for (TipoProducto tp : this.tiposProducto) {	
						if (contador >= this.indice) {
							if (contador - this.indice == this.resultadosPorPagina) {
								break;
							}
							//
							//
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
				    resultado+=         	"<th>Categoria</th>";
					resultado+=         	"<th>Seleccionable</th>";
				    resultado+=			"</tr>";
				    resultado+=		"</thead>";
				    resultado+=		"<tbody>";
					
					int contador = 0;
					for (TipoProducto tp : this.tiposProducto) {
						if (contador >= this.indice) {
							if (contador - this.indice == this.resultadosPorPagina) {
								break;
							} new TipoProductoFK().getCategoriaNombre(tp);
							resultado+="<tr>";
							resultado+=    "<th scope='row'>";
							resultado+=	       "<div class='container-fluid h-100 mt-2'>";
							resultado+=	           "<div class='row w-100 m-0 p-0 align-items-center'>";
							resultado+=	               "<div class='col text-center'>";
							resultado+=    			       tp.getNombre();
							resultado+=    			   "</div>";
							resultado+=            "</div>";
							resultado+=        "</div>";
							resultado+=    "</th>";
							resultado+=    "<td>";
							resultado+=	       "<div class='container-fluid h-100 mt-2'>";
							resultado+=	           "<div class='row w-100 m-0 p-0 align-items-center'>";
							resultado+=	               "<div class='col text-center'>";
							resultado+=    			       tp.getNombre();
							resultado+=    			   "</div>";
							resultado+=            "</div>";
							resultado+=        "</div>";
							resultado+=    "</td>";
							resultado+=    "<td>";
							resultado+=        "<center>";
							resultado+=            "<div class='form-check form-check-inline mt-3'>";
							resultado+=                "<input class='form-check-input' type='checkbox' name='tiposProducto' value='" + tp.getId() + "'" + "style='cursor: pointer'>";
							resultado+=            "</div>";
							resultado+=        "<center>";
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
	
	public String generarNumeros(String raiz, String servlet, String vista) {
		String resultado = "";
		String actual = "";
		
		if (this.paginaActual <= this.totalPaginas) {
			resultado+= "<ul class='pagination pagination-md'>";
			for (int i = 1; i <= this.totalPaginas; i++) {
				if (i == this.paginaActual) {
					actual = "class='page-item active'";
				}
				else {
					actual = "class='page-item'";
				}	
				resultado+="<li " + actual + ">" + "<a class='page-link'" + " href='" + raiz + "/" + servlet + "?vista=" + vista + "&pagina=" + i + "'>" + i + "</a></li>";
			}
			resultado+= "</ul>";
		}
		
		return resultado;
	}
}