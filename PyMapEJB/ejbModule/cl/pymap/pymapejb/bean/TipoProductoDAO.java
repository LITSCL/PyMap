package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.TipoProducto;

/**
 * Session Bean implementation class TipoProductoDAO
 */
@Stateless
public class TipoProductoDAO implements TipoProductoDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public TipoProductoDAO() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public boolean save(TipoProducto tp) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(tp);
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
	public List<TipoProducto> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<TipoProducto> tipos = em.createNamedQuery("TipoProducto.getAll", TipoProducto.class).getResultList();
			return tipos;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Object[]> getAllFK(String columnaCategoria) {
		EntityManager em = this.emf.createEntityManager();
		
		if (columnaCategoria == null) {
			columnaCategoria = "id";
		}
		
		TypedQuery<Object[]> query = em.createQuery("SELECT tp.id, tp.nombre, c." + columnaCategoria + " FROM TipoProducto tp INNER JOIN Categoria c ON tp.categoriaFK = c.id", Object[].class);
		List<Object[]> tiposProducto = query.getResultList();
		
		return tiposProducto;
	}

	@Override
	public TipoProducto find(int id) {
		EntityManager em = this.emf.createEntityManager();
		TipoProducto tp = new TipoProducto();
		try {
			tp = em.find(TipoProducto.class, id);
			return tp;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<TipoProducto> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<TipoProducto> query = em.createQuery("SELECT tp FROM TipoProducto tp WHERE " + "tp." + columna + " = " + valor, TipoProducto.class);
			List<TipoProducto> tiposProducto = query.getResultList();
			
			return tiposProducto;
		}
		else {
			TypedQuery<TipoProducto> query = em.createQuery("SELECT tp FROM TipoProducto tp WHERE " + "tp." + columna + " = " + "'" + valor + "'", TipoProducto.class);
			List<TipoProducto> tiposProducto = query.getResultList();
			
			return tiposProducto;
		}
	}

	@Override
	public boolean update(TipoProducto tp) {
		EntityManager em = this.emf.createEntityManager();
		TipoProducto tipoProductoBD = new TipoProducto();
		try {
			tipoProductoBD = em.find(TipoProducto.class, tp.getId());
			tipoProductoBD.setId(tp.getId());
			tipoProductoBD.setNombre(tp.getNombre());
			tipoProductoBD.setCategoriaFK(tp.getCategoriaFK());
			em.merge(tipoProductoBD);
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
				Query query = em.createQuery("UPDATE TipoProducto SET " + columna + " = " + valor + " WHERE id" + " = " + id); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		else {
			try {
				Query query = em.createQuery("UPDATE TipoProducto SET " + columna + " = " + "'" + valor + "'" + " WHERE id" + " = " + id); 
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
		TipoProducto tp = em.find(TipoProducto.class, id);
		
		try {
			tp = em.find(TipoProducto.class, id);
			em.remove(tp);
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
