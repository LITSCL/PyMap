package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.Usuario;

/**
 * Session Bean implementation class UsuarioDAO
 */
@Stateless
public class UsuarioDAO implements UsuarioDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public UsuarioDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean save(Usuario u) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(u);
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
	public List<Usuario> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<Usuario> usuarios = em.createNamedQuery("Usuario.getAll", Usuario.class).getResultList();
			return usuarios;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}

	@Override
	public Usuario find(String correo) {
		EntityManager em = this.emf.createEntityManager();
		Usuario u = new Usuario();
		try {
			u = em.find(Usuario.class, correo);
			return u;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Usuario> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE " + "u." + columna + " = " + valor, Usuario.class);
			List<Usuario> usuarios = query.getResultList();
			
			return usuarios;
		}
		else {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE " + "u." + columna + " = " + "'" + valor + "'", Usuario.class);
			List<Usuario> usuarios = query.getResultList();
			
			return usuarios;
		}
	}

	@Override
	public boolean update(Usuario u) {
		EntityManager em = this.emf.createEntityManager();
		Usuario usuarioBD = new Usuario();
		try {
			usuarioBD = em.find(Usuario.class, u.getCorreo());
			usuarioBD.setCorreo(u.getCorreo());
			usuarioBD.setClave(u.getClave());
			usuarioBD.setPrimerNombre(u.getPrimerNombre());
			usuarioBD.setSegundoNombre(u.getSegundoNombre());
			usuarioBD.setApellidoPaterno(u.getApellidoPaterno());
			usuarioBD.setApellidoMaterno(u.getApellidoMaterno());
			usuarioBD.setImagen(u.getImagen());
			em.merge(usuarioBD);
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
	public boolean delete(String correo) {
		EntityManager em = this.emf.createEntityManager();
		Usuario u = em.find(Usuario.class, correo);
		
		try {
			u = em.find(Usuario.class, correo);
			em.remove(u);
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
