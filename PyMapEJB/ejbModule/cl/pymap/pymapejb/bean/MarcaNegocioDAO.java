package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.MarcaNegocio;

/**
 * Session Bean implementation class MarcaNegocioDAO
 */
@Stateless
public class MarcaNegocioDAO implements MarcaNegocioDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public MarcaNegocioDAO() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public boolean save(MarcaNegocio mn) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(mn);
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
	public List<MarcaNegocio> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<MarcaNegocio> marcasNegocio = em.createNamedQuery("MarcaNegocio.getAll", MarcaNegocio.class).getResultList();
			return marcasNegocio;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Object[]> getAllFK(String columnaMarca, String columnaNegocio) {
		EntityManager em = this.emf.createEntityManager();
		
		if (columnaMarca == null) {
			columnaMarca = "id";
		}
		
		if (columnaNegocio == null) {
			columnaNegocio = "id";
		}
		
		TypedQuery<Object[]> query = em.createQuery("SELECT mn.id, m." + columnaMarca + ", n." + columnaNegocio + " FROM MarcaNegocio mn INNER JOIN Marca m ON mn.marcaFK = m.id INNER JOIN Negocio n ON mn.negocioFK = n.rut", Object[].class);
		List<Object[]> marcasNegocio = query.getResultList();
		
		return marcasNegocio;
	}

	@Override
	public MarcaNegocio find(int id) {
		EntityManager em = this.emf.createEntityManager();
		MarcaNegocio mn = new MarcaNegocio();
		try {
			mn = em.find(MarcaNegocio.class, id);
			return mn;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<MarcaNegocio> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<MarcaNegocio> query = em.createQuery("SELECT mn FROM MarcaNegocio mn WHERE " + "mn." + columna + " = " + valor, MarcaNegocio.class);
			List<MarcaNegocio> marcasNegocio = query.getResultList();
			
			return marcasNegocio;
		}
		else {
			TypedQuery<MarcaNegocio> query = em.createQuery("SELECT mn FROM MarcaNegocio mn WHERE " + "mn." + columna + " = " + "'" + valor + "'", MarcaNegocio.class);
			List<MarcaNegocio> marcasNegocio = query.getResultList();
			
			return marcasNegocio;
		}
	}
	
	@Override
	public boolean update(MarcaNegocio mn) {
		EntityManager em = this.emf.createEntityManager();
		MarcaNegocio marcaNegocioBD = new MarcaNegocio();
		try {
			marcaNegocioBD = em.find(MarcaNegocio.class, mn.getId());
			marcaNegocioBD.setId(mn.getId());
			marcaNegocioBD.setMarcaFK(mn.getMarcaFK());
			marcaNegocioBD.setNegocioFK(mn.getNegocioFK());
			em.merge(marcaNegocioBD);
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
				Query query = em.createQuery("UPDATE MarcaNegocio SET " + columna + " = " + valor + " WHERE id" + " = " + id); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		else {
			try {
				Query query = em.createQuery("UPDATE MarcaNegocio SET " + columna + " = " + "'" + valor + "'" + " WHERE id" + " = " + id); 
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
		MarcaNegocio mn = em.find(MarcaNegocio.class, id);
		
		try {
			mn = em.find(MarcaNegocio.class, id);
			em.remove(mn);
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
