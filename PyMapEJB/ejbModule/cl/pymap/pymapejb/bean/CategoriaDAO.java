package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.Categoria;

/**
 * Session Bean implementation class CategoriaDAO
 */
@Stateless
public class CategoriaDAO implements CategoriaDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public CategoriaDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean save(Categoria c) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(c);
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
	public List<Categoria> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<Categoria> categorias = em.createNamedQuery("Categoria.getAll", Categoria.class).getResultList();
			return categorias;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}

	@Override
	public Categoria find(int id) {
		EntityManager em = this.emf.createEntityManager();
		Categoria c = new Categoria();
		try {
			c = em.find(Categoria.class, id);
			return c;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Categoria> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c WHERE " + "c." + columna + " = " + valor, Categoria.class);
			List<Categoria> categorias = query.getResultList();
			
			return categorias;
		}
		else {
			TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c WHERE " + "c." + columna + " = " + "'" + valor + "'", Categoria.class);
			List<Categoria> categorias = query.getResultList();
			
			return categorias;
		}
	}

	@Override
	public boolean update(Categoria c) {
		EntityManager em = this.emf.createEntityManager();
		Categoria categoriaBD = new Categoria();
		try {
			categoriaBD = em.find(Categoria.class, c.getId());
			categoriaBD.setId(c.getId());
			categoriaBD.setNombre(c.getNombre());
			em.merge(categoriaBD);
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
		Categoria c = em.find(Categoria.class, id);
		
		try {
			c = em.find(Categoria.class, id);
			em.remove(c);
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
