package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.TipoNegocio;

/**
 * Session Bean implementation class TipoNegocioDAO
 */
@Stateless
public class TipoNegocioDAO implements TipoNegocioDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public TipoNegocioDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean save(TipoNegocio tn) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(tn);
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
	public List<TipoNegocio> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<TipoNegocio> tiposNegocio = em.createNamedQuery("TipoNegocio.getAll", TipoNegocio.class).getResultList();
			return tiposNegocio;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}

	@Override
	public TipoNegocio find(int id) {
		EntityManager em = this.emf.createEntityManager();
		TipoNegocio tn = new TipoNegocio();
		try {
			tn = em.find(TipoNegocio.class, id);
			return tn;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<TipoNegocio> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<TipoNegocio> query = em.createQuery("SELECT tn FROM TipoNegocio tn WHERE " + "tn." + columna + " = " + valor, TipoNegocio.class);
			List<TipoNegocio> tiposNegocio = query.getResultList();
			
			return tiposNegocio;
		}
		else {
			TypedQuery<TipoNegocio> query = em.createQuery("SELECT tn FROM TipoNegocio tp WHERE " + "tn." + columna + " = " + "'" + valor + "'", TipoNegocio.class);
			List<TipoNegocio> tiposNegocio = query.getResultList();
			
			return tiposNegocio;
		}
	}

	@Override
	public boolean update(TipoNegocio tn) {
		EntityManager em = this.emf.createEntityManager();
		TipoNegocio tipoNegocioBD = new TipoNegocio();
		try {
			tipoNegocioBD = em.find(TipoNegocio.class, tn.getId());
			tipoNegocioBD.setId(tn.getId());
			tipoNegocioBD.setNombre(tn.getNombre());
			em.merge(tipoNegocioBD);
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
	public boolean delete(int id) {
		EntityManager em = this.emf.createEntityManager();
		TipoNegocio tn = em.find(TipoNegocio.class, id);
		
		try {
			tn = em.find(TipoNegocio.class, id);
			em.remove(tn);
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
