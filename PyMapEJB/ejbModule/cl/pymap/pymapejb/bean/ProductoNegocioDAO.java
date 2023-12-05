package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.ProductoNegocio;

/**
 * Session Bean implementation class ProductoNegocioDAO
 */
@Stateless
public class ProductoNegocioDAO implements ProductoNegocioDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public ProductoNegocioDAO() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public boolean save(ProductoNegocio pn) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(pn);
			em.flush();
			return true;
		} catch (Exception ex) {
			return false;
		}
		finally {
			em.close();
		}
	}

	@Override
	public List<ProductoNegocio> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<ProductoNegocio> productosNegocio = em.createNamedQuery("ProductoNegocio.getAll", ProductoNegocio.class).getResultList();
			return productosNegocio;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Object[]> getAllFK(String columnaProducto, String columnaNegocio) {
		EntityManager em = this.emf.createEntityManager();
		
		if (columnaProducto == null) {
			columnaProducto = "id";
		}
		
		if (columnaNegocio == null) {
			columnaNegocio = "rut";
		}
		
		TypedQuery<Object[]> query = em.createQuery("SELECT pn.id, pn.precio, p." + columnaProducto + ", n." + columnaNegocio + " FROM ProductoNegocio pn INNER JOIN Producto p ON pn.productoFK = p.id INNER JOIN Negocio n ON pn.negocioFK = n.rut", Object[].class);
		List<Object[]> productosNegocio = query.getResultList();
		
		return productosNegocio;
	}

	@Override
	public ProductoNegocio find(int id) {
		EntityManager em = this.emf.createEntityManager();
		ProductoNegocio pn = new ProductoNegocio();
		try {
			pn = em.find(ProductoNegocio.class, id);
			return pn;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<ProductoNegocio> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<ProductoNegocio> query = em.createQuery("SELECT pn FROM ProductoNegocio pn WHERE " + "pn." + columna + " = " + valor, ProductoNegocio.class);
			List<ProductoNegocio> productosNegocio = query.getResultList();
			
			return productosNegocio;
		}
		else {
			TypedQuery<ProductoNegocio> query = em.createQuery("SELECT pn FROM ProductoNegocio pn WHERE " + "pn." + columna + " = " + "'" + valor + "'", ProductoNegocio.class);
			List<ProductoNegocio> productosNegocio = query.getResultList();
			
			return productosNegocio;
		}
	}
	
	@Override
	public boolean update(ProductoNegocio pn) {
		EntityManager em = this.emf.createEntityManager();
		ProductoNegocio productoNegocioBD = new ProductoNegocio();
		try {
			productoNegocioBD = em.find(ProductoNegocio.class, pn.getId());
			productoNegocioBD.setId(pn.getId());
			productoNegocioBD.setPrecio(pn.getPrecio());
			productoNegocioBD.setProductoFK(pn.getProductoFK());
			productoNegocioBD.setNegocioFK(pn.getNegocioFK());
			em.merge(productoNegocioBD);
			em.flush();
			return true;
		} catch (Exception ex) {
			return false;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public boolean updateOne(int id, String columna, String valor) {
		EntityManager em = this.emf.createEntityManager(); 	
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			try {
				Query query = em.createQuery("UPDATE ProductoNegocio SET " + columna + " = " + valor + " WHERE id" + " = " + id); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		else {
			try {
				Query query = em.createQuery("UPDATE ProductoNegocio SET " + columna + " = " + "'" + valor + "'" + " WHERE id" + " = " + id); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
	}
	
	@Override
	public boolean delete(int id) {
		EntityManager em = this.emf.createEntityManager();
		ProductoNegocio pn = em.find(ProductoNegocio.class, id);
		
		try {
			pn = em.find(ProductoNegocio.class, id);
			em.remove(pn);
			em.flush();
			return true;
		} catch (Exception ex) {
			return false;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Object[]> customQuery(String jpql) {
		EntityManager em = this.emf.createEntityManager(); 
		try {
			TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class); 
			List<Object[]> resultado = query.getResultList(); 
			
			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

}
