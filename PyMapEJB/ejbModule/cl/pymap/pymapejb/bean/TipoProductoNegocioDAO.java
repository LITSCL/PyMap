package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.TipoProductoNegocio;

/**
 * Session Bean implementation class TipoProductoNegocioDAO
 */
@Stateless
public class TipoProductoNegocioDAO implements TipoProductoNegocioDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public TipoProductoNegocioDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean save(TipoProductoNegocio ntp) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(ntp);
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
	public List<TipoProductoNegocio> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<TipoProductoNegocio> tiposProductoNegocio = em.createNamedQuery("TipoProductoNegocio.getAll", TipoProductoNegocio.class).getResultList();
			return tiposProductoNegocio;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Object[]> getAllFK(String columnaTipoProducto, String columnaNegocio) {
		EntityManager em = this.emf.createEntityManager();
		
		if (columnaNegocio == null) {
			columnaNegocio = "rut";
		}
		
		if (columnaTipoProducto == null) {
			columnaTipoProducto = "id";
		}
		
		TypedQuery<Object[]> query = em.createQuery("SELECT tpn.id, tp." + columnaTipoProducto + ", n." + columnaNegocio + " FROM TipoProductoNegocio tpn INNER JOIN TipoProducto tp ON tpn.tipoProductoFK = tp.id INNER JOIN Negocio n ON tpn.negocioFK = n.rut", Object[].class);
		List<Object[]> tiposProductoNegocio = query.getResultList();
		
		return tiposProductoNegocio;
	}

	@Override
	public TipoProductoNegocio find(int id) {
		EntityManager em = this.emf.createEntityManager();
		TipoProductoNegocio ntp = new TipoProductoNegocio();
		try {
			ntp = em.find(TipoProductoNegocio.class, id);
			return ntp;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<TipoProductoNegocio> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<TipoProductoNegocio> query = em.createQuery("SELECT tpn FROM TipoProductoNegocio tpn WHERE " + "tpn." + columna + " = " + valor, TipoProductoNegocio.class);
			List<TipoProductoNegocio> tiposProductoNegocio = query.getResultList();
			
			return tiposProductoNegocio;
		}
		else {
			TypedQuery<TipoProductoNegocio> query = em.createQuery("SELECT tpn FROM TipoProductoNegocio tpn WHERE " + "tpn." + columna + " = " + "'" + valor + "'", TipoProductoNegocio.class);
			List<TipoProductoNegocio> tiposProductoNegocio = query.getResultList();
			
			return tiposProductoNegocio;
		}
	}
	
	@Override
	public boolean update(TipoProductoNegocio tpn) {
		EntityManager em = this.emf.createEntityManager();
		TipoProductoNegocio tipoProductoNegocioBD = new TipoProductoNegocio();
		try {
			tipoProductoNegocioBD = em.find(TipoProductoNegocio.class, tpn.getId());
			tipoProductoNegocioBD.setId(tpn.getId());
			tipoProductoNegocioBD.setTipoProductoFK(tpn.getTipoProductoFK());
			tipoProductoNegocioBD.setNegocioFK(tpn.getNegocioFK());
			em.merge(tipoProductoNegocioBD);
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
				Query query = em.createQuery("UPDATE TipoProductoNegocio SET " + columna + " = " + valor + " WHERE id" + " = " + id); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		else {
			try {
				Query query = em.createQuery("UPDATE TipoProductoNegocio SET " + columna + " = " + "'" + valor + "'" + " WHERE id" + " = " + id); 
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
		TipoProductoNegocio tpn = em.find(TipoProductoNegocio.class, id);
		
		try {
			tpn = em.find(TipoProductoNegocio.class, id);
			em.remove(tpn);
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
