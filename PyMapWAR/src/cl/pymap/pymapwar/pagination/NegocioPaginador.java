package cl.pymap.pymapwar.pagination;

import java.util.ArrayList;
import java.util.List;

import cl.pymap.pymapejb.fk.NegocioFK;
import cl.pymap.pymapejb.model.Negocio;

public class NegocioPaginador {
	private int paginaActual = 0;
	private int totalPaginas = 0;
	private int totalResultados = 0;
	private int resultadosPorPagina = 0;
	private int indice = 0;
	
	private List<Negocio> negocios = new ArrayList<Negocio>();
	
	public NegocioPaginador(int paginaActual, int resultadosPorPagina, List<Negocio> negocios) {
		this.paginaActual = paginaActual;
		this.totalResultados = negocios.size();
		this.resultadosPorPagina = resultadosPorPagina;
		this.totalPaginas = totalResultados / resultadosPorPagina;
		this.indice = (this.paginaActual - 1) * (this.resultadosPorPagina);
		
		if (this.totalResultados > this.totalPaginas * this.resultadosPorPagina) {
			this.totalPaginas++;
		}
		
		this.negocios = negocios;
	}
	
	public String generarRegistros(String formato, String raiz, String servlet, String vista) {
		String resultado = "";
		
		if (negocios.size() != 0) {
			if (this.paginaActual <= this.totalPaginas) {
				if (formato.equalsIgnoreCase("tarjeta")) {
					int contador = 0;
					for (Negocio n : this.negocios) {	
						if (contador >= this.indice) {
							if (contador - this.indice == this.resultadosPorPagina) {
								break;
							}
							resultado+="<div class='card m-2 p-2' style='min-width: 320px; max-width: 320px'>";
							resultado+=    "<div class='card-header'>";
							resultado+=        "<div class='container-fluid h-100'>";
							resultado+=            "<div class='row w-100 m-0 p-0 align-items-center'>";
							resultado+=                "<div class='col text-center'>";
							resultado+=                    "<h5>" + n.getNombre() + "</h5>";
							resultado+=                "</div>";
							resultado+=            "</div>";
							resultado+=        "</div>";
							resultado+=    "</div>";
							resultado+=    "<div class='card-body'>";
							resultado+=        "<a href='" + raiz + "/negocio/detalle?negocio=" + n.getRut() + "'><img class='card-img-top' src='" + raiz + "/uploads/models/negocio/images/" + n.getLogo() + "'></a>";
							resultado+=    "</div>";
							resultado+=    "<div class='card-footer'>";
							resultado+=        "<p class='card-text'>" + new NegocioFK().getTipoNegocioNombre(n) + "; " + n.getCiudad() + ", " + n.getComuna() + ", " + n.getCalle() + "." + "</p>";
							if (vista.equals("mis_negocios")) {
								resultado+=        "<div class='container-fluid h-100'>";
								resultado+=            "<div class='row w-100 m-0 p-0 align-items-center'>";
								resultado+=                "<div class='col text-center'>";
								resultado+= 			       "<a class='btn btn-success' href='" + raiz + "/negocio/administrar?negocio=" + n.getRut() + "'>Administrar</a>";
								resultado+=                "</div>";
								resultado+=            "</div>";
								resultado+=        "</div>";
							}
							resultado+=    "</div>";
							resultado+="</div>";

						}
						contador++;	
					}
					return resultado;
				}
				else if (formato.equalsIgnoreCase("tabla")) {
					//
					//
					int contador = 0;
					for (Negocio n : this.negocios) {
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