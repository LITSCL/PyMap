package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.MarcaTipoNegocio;

/**
 * Session Bean implementation class MarcaTipoNegocioDAO
 */
@Stateless
public class MarcaTipoNegocioDAO implements MarcaTipoNegocioDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public MarcaTipoNegocioDAO() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public boolean save(MarcaTipoNegocio mtn) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(mtn);
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
	public List<MarcaTipoNegocio> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<MarcaTipoNegocio> marcasTipoNegocio = em.createNamedQuery("MarcaTipoNegocio.getAll", MarcaTipoNegocio.class).getResultList();
			return marcasTipoNegocio;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Object[]> getAllFK(String columnaMarca, String columnaTipoNegocio) {
		EntityManager em = this.emf.createEntityManager();
		
		if (columnaMarca == null) {
			columnaMarca = "id";
		}
		
		if (columnaTipoNegocio == null) {
			columnaTipoNegocio = "id";
		}
		
		TypedQuery<Object[]> query = em.createQuery("SELECT mtn.id, m." + columnaMarca + ", tn." + columnaTipoNegocio + " FROM MarcaTipoNegocio mtn INNER JOIN Marca m ON mtn.marcaFK = m.id INNER JOIN TipoNegocio tn ON mtn.tipoNegocioFK = tn.id", Object[].class);
		List<Object[]> marcasTipoNegocio = query.getResultList();
		
		return marcasTipoNegocio;
	}

	@Override
	public MarcaTipoNegocio find(int id) {
		EntityManager em = this.emf.createEntityManager();
		MarcaTipoNegocio mtn = new MarcaTipoNegocio();
		try {
			mtn = em.find(MarcaTipoNegocio.class, id);
			return mtn;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<MarcaTipoNegocio> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<MarcaTipoNegocio> query = em.createQuery("SELECT mtn FROM MarcaTipoNegocio mtn WHERE " + "mtn." + columna + " = " + valor, MarcaTipoNegocio.class);
			List<MarcaTipoNegocio> marcasTipoNegocio = query.getResultList();
			
			return marcasTipoNegocio;
		}
		else {
			TypedQuery<MarcaTipoNegocio> query = em.createQuery("SELECT mtn FROM MarcaTipoNegocio mtn WHERE " + "mtn." + columna + " = " + "'" + valor + "'", MarcaTipoNegocio.class);
			List<MarcaTipoNegocio> marcasTipoNegocio = query.getResultList();
			
			return marcasTipoNegocio;
		}
	}
	
	@Override
	public boolean update(MarcaTipoNegocio mtn) {
		EntityManager em = this.emf.createEntityManager();
		MarcaTipoNegocio marcaTipoNegocioBD = new MarcaTipoNegocio();
		try {
			marcaTipoNegocioBD = em.find(MarcaTipoNegocio.class, mtn.getId());
			marcaTipoNegocioBD.setId(mtn.getId());
			marcaTipoNegocioBD.setMarcaFK(mtn.getMarcaFK());
			marcaTipoNegocioBD.setTipoNegocioFK(mtn.getTipoNegocioFK());
			em.merge(marcaTipoNegocioBD);
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
				Query query = em.createQuery("UPDATE MarcaTipoNegocio SET " + columna + " = " + valor + " WHERE id" + " = " + id); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		else {
			try {
				Query query = em.createQuery("UPDATE MarcaTipoNegocio SET " + columna + " = " + "'" + valor + "'" + " WHERE id" + " = " + id); 
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
		MarcaTipoNegocio mtn = em.find(MarcaTipoNegocio.class, id);
		
		try {
			mtn = em.find(MarcaTipoNegocio.class, id);
			em.remove(mtn);
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
