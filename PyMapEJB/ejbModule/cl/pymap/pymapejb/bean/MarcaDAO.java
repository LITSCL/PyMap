package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.Marca;

/**
 * Session Bean implementation class MarcaDAO
 */
@Stateless
public class MarcaDAO implements MarcaDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public MarcaDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean save(Marca m) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(m);
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
	public List<Marca> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<Marca> marcas = em.createNamedQuery("Marca.getAll", Marca.class).getResultList();
			return marcas;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}

	@Override
	public Marca find(int id) {
		EntityManager em = this.emf.createEntityManager();
		Marca m = new Marca();
		try {
			m = em.find(Marca.class, id);
			return m;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Marca> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<Marca> query = em.createQuery("SELECT m FROM Marca m WHERE " + "m." + columna + " = " + valor, Marca.class);
			List<Marca> marcas = query.getResultList();
			
			return marcas;
		}
		else {
			TypedQuery<Marca> query = em.createQuery("SELECT m FROM Marca m WHERE " + "m." + columna + " = " + "'" + valor + "'", Marca.class);
			List<Marca> marcas = query.getResultList();
			
			return marcas;
		}
	}

	@Override
	public boolean update(Marca m) {
		EntityManager em = this.emf.createEntityManager();
		Marca marcaBD = new Marca();
		try {
			marcaBD = em.find(Marca.class, m.getId());
			marcaBD.setId(m.getId());
			marcaBD.setNombre(m.getNombre());
			marcaBD.setLogo(m.getLogo());
			em.merge(marcaBD);
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
		Marca m = em.find(Marca.class, id);
		
		try {
			m = em.find(Marca.class, id);
			em.remove(m);
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
