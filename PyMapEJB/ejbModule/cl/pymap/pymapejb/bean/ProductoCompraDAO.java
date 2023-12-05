package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.Producto;
import cl.pymap.pymapejb.model.ProductoCompra;

/**
 * Session Bean implementation class ProductoCompraDAO
 */
@Stateless
public class ProductoCompraDAO implements ProductoCompraDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public ProductoCompraDAO() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public boolean save(List<Object[]> carrito, int idCompra) {
		EntityManager em = this.emf.createEntityManager();
		try {
			for (Object[] o : carrito) {
				Producto producto = (Producto)o[3];
				
				ProductoCompra pc = new ProductoCompra();
				pc.setCompraFK(idCompra);
				pc.setProductoFK(producto.getId());
				pc.setUnidades((int)o[2]);
				
				em.persist(pc);
				em.flush();
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
		finally {
			em.close();
		}
	}

	@Override
	public List<ProductoCompra> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<ProductoCompra> productosCompra = em.createNamedQuery("ProductoCompra.getAll", ProductoCompra.class).getResultList();
			return productosCompra;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Object[]> getAllFK(String columnaCompra, String columnaProducto) {
		EntityManager em = this.emf.createEntityManager();
		
		if (columnaCompra == null) {
			columnaCompra = "id";
		}
		
		if (columnaProducto == null) {
			columnaProducto = "id";
		}
		
		TypedQuery<Object[]> query = em.createQuery("SELECT pc.id, c." + columnaCompra + ", p." + columnaProducto + ", pc.unidades" + " FROM ProductoCompra pc INNER JOIN Compra c ON pc.compraFK = c.id INNER JOIN Producto p ON pc.productoFK = p.id", Object[].class);
		List<Object[]> productosCompra = query.getResultList();
		
		return productosCompra;
	}

	@Override
	public ProductoCompra find(int id) {
		EntityManager em = this.emf.createEntityManager();
		ProductoCompra pc = new ProductoCompra();
		try {
			pc = em.find(ProductoCompra.class, id);
			return pc;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<ProductoCompra> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<ProductoCompra> query = em.createQuery("SELECT pc FROM ProductoCompra pc WHERE " + "pc." + columna + " = " + valor, ProductoCompra.class);
			List<ProductoCompra> productosCompra = query.getResultList();
			
			return productosCompra;
		}
		else {
			TypedQuery<ProductoCompra> query = em.createQuery("SELECT pc FROM ProductoCompra pc WHERE " + "pc." + columna + " = " + "'" + valor + "'", ProductoCompra.class);
			List<ProductoCompra> productosCompra = query.getResultList();
			
			return productosCompra;
		}
	}
	
	@Override
	public boolean update(ProductoCompra pc) {
		EntityManager em = this.emf.createEntityManager();
		ProductoCompra productoCompraBD = new ProductoCompra();
		try {
			productoCompraBD = em.find(ProductoCompra.class, pc.getId());
			productoCompraBD.setId(pc.getId());
			productoCompraBD.setCompraFK(pc.getCompraFK());
			productoCompraBD.setProductoFK(pc.getProductoFK());
			productoCompraBD.setUnidades(pc.getUnidades());
			em.merge(productoCompraBD);
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
				Query query = em.createQuery("UPDATE ProductoCompra SET " + columna + " = " + valor + " WHERE id" + " = " + id); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		else {
			try {
				Query query = em.createQuery("UPDATE ProductoCompra SET " + columna + " = " + "'" + valor + "'" + " WHERE id" + " = " + id); 
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
		ProductoCompra pc = em.find(ProductoCompra.class, id);
		
		try {
			pc = em.find(ProductoCompra.class, id);
			em.remove(pc);
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
