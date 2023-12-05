package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.Compra;

/**
 * Session Bean implementation class CompraDAO
 */
@Stateless
public class CompraDAO implements CompraDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public CompraDAO() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public boolean save(Compra c) {
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
	public List<Compra> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<Compra> compras = em.createNamedQuery("Compra.getAll", Compra.class).getResultList();
			return compras;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Object[]> getAllFK(String columnaUsuario, String columnaNegocio) {
		EntityManager em = this.emf.createEntityManager();
		
		if (columnaUsuario == null) {
			columnaUsuario = "correo";
		}
		
		if (columnaNegocio == null) {
			columnaNegocio = "rut";
		}
		
		TypedQuery<Object[]> query = em.createQuery("SELECT c.id, c.coste, c.estado, c.fecha, c.hora, u." + columnaUsuario + ", n." + columnaNegocio + " FROM Compra c INNER JOIN Usuario u ON c.usuarioFK = u.correo INNER JOIN Negocio n ON c.negocioFK = n.rut", Object[].class);
		List<Object[]> compras = query.getResultList();
		
		return compras;
	}

	@Override
	public Compra find(int id) {
		EntityManager em = this.emf.createEntityManager();
		Compra c = new Compra();
		try {
			c = em.find(Compra.class, id);
			return c;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Compra> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<Compra> query = em.createQuery("SELECT c FROM Compra c WHERE " + "c." + columna + " = " + valor, Compra.class);
			List<Compra> compras = query.getResultList();
			
			return compras;
		}
		else {
			TypedQuery<Compra> query = em.createQuery("SELECT c FROM Compra c WHERE " + "c." + columna + " = " + "'" + valor + "'", Compra.class);
			List<Compra> compras = query.getResultList();
			
			return compras;
		}
	}
	
	@Override
	public boolean update(Compra c) {
		EntityManager em = this.emf.createEntityManager();
		Compra compraBD = new Compra();
		try {
			compraBD = em.find(Compra.class, c.getId());
			compraBD.setId(c.getId());
			compraBD.setCoste(c.getCoste());
			compraBD.setEstado(c.getEstado());
			compraBD.setFecha(c.getFecha());
			compraBD.setHora(c.getHora());
			compraBD.setUsuarioFK(c.getUsuarioFK());
			compraBD.setNegocioFK(c.getNegocioFK());
			em.merge(compraBD);
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
				Query query = em.createQuery("UPDATE Compra SET " + columna + " = " + valor + " WHERE id" + " = " + id); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		else {
			try {
				Query query = em.createQuery("UPDATE Compra SET " + columna + " = " + "'" + valor + "'" + " WHERE id" + " = " + id); 
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
		Compra c = em.find(Compra.class, id);
		
		try {
			c = em.find(Compra.class, id);
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
