package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.Negocio;

/**
 * Session Bean implementation class NegocioDAO
 */
@Stateless
public class NegocioDAO implements NegocioDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");
	
    /**
     * Default constructor. 
     */
    public NegocioDAO() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public boolean save(Negocio n) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(n);
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
	public List<Negocio> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<Negocio> negocios = em.createNamedQuery("Negocio.getAll", Negocio.class).getResultList();
			return negocios;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Object[]> getAllFK(String columnaUsuario, String columnaTipoNegocio) {
		EntityManager em = this.emf.createEntityManager();
		
		if (columnaTipoNegocio == null) {
			columnaTipoNegocio = "id";
		}
		
		if (columnaUsuario == null) {
			columnaUsuario = "correo";
		}
		
		TypedQuery<Object[]> query = em.createQuery("SELECT n.rut, n.nombre, n.ciudad, n.comuna, n.calle, n.diasAtencion, n.metodosPago, n.estado, n.logo, tp." + columnaTipoNegocio + ", u." + columnaUsuario + " FROM Negocio n INNER JOIN TipoNegocio tp ON n.tipoNegocioFK = tp.id INNER JOIN Usuario u ON n.usuarioFK = u.correo", Object[].class);
		List<Object[]> negocios = query.getResultList();
		
		return negocios;
	}

	@Override
	public Negocio find(String rut) {
		EntityManager em = this.emf.createEntityManager();
		Negocio n = new Negocio();
		try {
			n = em.find(Negocio.class, rut);
			return n;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Negocio> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<Negocio> query = em.createQuery("SELECT n FROM Negocio n WHERE " + "n." + columna + " = " + valor, Negocio.class);
			List<Negocio> negocios = query.getResultList();
			
			return negocios;
		}
		else {
			TypedQuery<Negocio> query = em.createQuery("SELECT n FROM Negocio n WHERE " + "n." + columna + " = " + "'" + valor + "'", Negocio.class);
			List<Negocio> negocios = query.getResultList();
			
			return negocios;
		}
	}

	@Override
	public boolean update(Negocio n) {
		EntityManager em = this.emf.createEntityManager();
		Negocio negocioBD = new Negocio();
		try {
			negocioBD = em.find(Negocio.class, n.getRut());
			negocioBD.setRut(n.getRut());
			negocioBD.setNombre(n.getNombre());
			negocioBD.setCiudad(n.getCiudad());
			negocioBD.setComuna(n.getComuna());
			negocioBD.setCalle(n.getCalle());
			negocioBD.setTipoNegocioFK(n.getTipoNegocioFK());
			negocioBD.setDiasAtencion(n.getDiasAtencion());
			negocioBD.setMetodosPago(n.getMetodosPago());
			negocioBD.setEstado(n.getEstado());
			negocioBD.setLogo(n.getLogo());
			em.merge(negocioBD);
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
	public boolean updateOne(String rut, String columna, String valor) {
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
				Query query = em.createQuery("UPDATE Negocio SET " + columna + " = " + valor + " WHERE rut" + " = " + "'" + rut + "'"); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		else {
			try {
				Query query = em.createQuery("UPDATE Negocio SET " + columna + " = " + "'" + valor + "'" + " WHERE rut" + " = " + "'" + rut + "'"); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
	}
	
	@Override
	public boolean delete(String rut) {
		EntityManager em = this.emf.createEntityManager();
		Negocio n = em.find(Negocio.class, rut);
		
		try {
			n = em.find(Negocio.class, rut);
			em.remove(n);
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
