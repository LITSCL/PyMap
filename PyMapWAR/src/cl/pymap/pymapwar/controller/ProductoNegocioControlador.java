package cl.pymap.pymapwar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import cl.pymap.pymapejb.bean.ProductoNegocioDAOLocal;
import cl.pymap.pymapejb.model.ProductoNegocio;

/**
 * Servlet implementation class ProductoNegocioControlador
 */
@WebServlet("/ProductoNegocio")
public class ProductoNegocioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
     @Inject
     ProductoNegocioDAOLocal daoProductoNegocio;
     
     ProductoNegocio pn = new ProductoNegocio();
     
     private int id;
     private double precio;
     private int productoFK;
     private String negocioFK;
     
 	private String jpql;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoNegocioControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false);
		
		String vista = request.getParameter("vista");
		String opcion = request.getParameter("opcion");
		String asincrono = request.getParameter("asincrono");
		
		if (vista == null) {
			vista = "";
		}
		if (opcion == null) {
			opcion = "";
		}
		if (asincrono == null) {
			asincrono = "";
		}
		
		switch (vista) { //Renderización de vistas.
		case "*": {
			//
			break;
		}
		default: 
			break;
		}
		
		switch (opcion) { //Acceso a datos GET.
		case "1": { //Agregar.
			try {
				this.precio = Double.parseDouble(request.getParameter("precio"));
			} catch (Exception ex) {
				this.precio = -1;
			}
			
			try {
				this.productoFK = Integer.parseInt(request.getParameter("producto"));
			} catch (Exception ex) {
				this.productoFK = -1;
			}
			
			this.negocioFK = request.getParameter("negocio");
			
			JsonObjectBuilder builder = Json.createObjectBuilder();
			JsonObject objetoJSON = null;
			
			if ((this.precio != -1 || this.precio == -1) && this.productoFK != -1 && negocioFK.equalsIgnoreCase(this.negocioFK) == true) {
				if (precio != -1) {
					this.pn.setPrecio(precio);
				}
				else {
					this.pn.setPrecio(0);
				}

				this.pn.setProductoFK(productoFK);
				this.pn.setNegocioFK(negocioFK);
				
				if (daoProductoNegocio.save(pn)) {
					List<ProductoNegocio> productosNegocio = daoProductoNegocio.findAll("negocioFK", this.negocioFK);
					int idProductoNegocio = (productosNegocio.get(productosNegocio.size() - 1)).getId();
					objetoJSON = builder.add("mensaje", "SERVIDOR: Producto agregado correctamente").add("idProductoNegocio", idProductoNegocio).build();
				}
				else {
					objetoJSON = builder.add("mensaje", "SERVIDOR: Error al agregar el producto").build();
				}
			}
			else {
				objetoJSON = builder.add("mensaje", "SERVIDOR: Error al agregar el producto").build();
			}
			
			response.setContentType(MediaType.APPLICATION_JSON);
			
			try (PrintWriter pw = new PrintWriter(response.getOutputStream())) {
				pw.println(objetoJSON.toString());
			}
			break;
		}
		case "2": { //Modificar.
			try {
				this.id = Integer.parseInt(request.getParameter("id"));
			} catch (Exception ex) {
				this.id = -1;
			}
			
			try {
				this.precio = Integer.parseInt(request.getParameter("precio"));
			} catch (Exception ex) {
				this.precio = -1;
			}
			
			JsonObjectBuilder builder = Json.createObjectBuilder();
			JsonObject objetoJSON = null;
			
			if ((this.id != -1 && this.precio != -1 || this.precio == -1)) {
				ProductoNegocio productoNegocio = daoProductoNegocio.find(this.id);
				
				if (productoNegocio != null) {
					if (precio != -1) {
						productoNegocio.setPrecio(precio);
					}
					else {
						productoNegocio.setPrecio(0);	
					}
					
					if (daoProductoNegocio.update(productoNegocio)) {
						objetoJSON = builder.add("mensaje", "SERVIDOR: Producto modificado correctamente").build();
					}
					else {
						objetoJSON = builder.add("mensaje", "SERVIDOR: Error al modificar el producto").build();
					}
				}
				else {
					objetoJSON = builder.add("mensaje", "SERVIDOR: Error al modificar el producto").build();
				}
			}
			else {
				objetoJSON = builder.add("mensaje", "SERVIDOR: Error al modificar el producto").build();
			}
			
			response.setContentType(MediaType.APPLICATION_JSON);
			
			try (PrintWriter pw = new PrintWriter(response.getOutputStream())) {
				pw.println(objetoJSON.toString());
			}
			break;
		}
		case "3": { //Eliminar
			try {
				this.id = Integer.parseInt(request.getParameter("productoNegocio"));
			} catch (Exception ex) {
				this.id = -1;
			}
			
			JsonObjectBuilder builder = Json.createObjectBuilder();
			JsonObject objetoJSON = null;
			
			if (this.id != -1) {
				if (daoProductoNegocio.delete(this.id) == true) {
					objetoJSON = builder.add("mensaje", "SERVIDOR: Producto eliminado correctamente").build();
				}
				else {
					objetoJSON = builder.add("mensaje", "SERVIDOR: Error al eliminar el producto").build();
				}	
			}
			else {
				objetoJSON = builder.add("mensaje", "SERVIDOR: Error al eliminar el producto").build();
			}
			
			response.setContentType(MediaType.APPLICATION_JSON);
			
			try (PrintWriter pw = new PrintWriter(response.getOutputStream())) {
				pw.println(objetoJSON.toString());
			}
			break;
		}
		default:
			break;
		}
		
		switch (asincrono) { //Peticiones REST.
		case "1": {
			//
			break;
		}
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false);
		
		String opcion = request.getParameter("opcion");
		
		switch (opcion) { //Acceso a datos POST.
		case "1": {
			//
			break;
		}
		default: 
			break;
		}
	}

}
