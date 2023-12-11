package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cl.pymap.pymapejb.model.MarcaTipoProducto;

/**
 * Session Bean implementation class MarcaTipoProductoDAO
 */
@Stateless
public class MarcaTipoProductoDAO implements MarcaTipoProductoDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PyMapEJB");

    /**
     * Default constructor. 
     */
    public MarcaTipoProductoDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean save(MarcaTipoProducto mtp) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(mtp);
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
	public List<MarcaTipoProducto> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<MarcaTipoProducto> marcasTipoProducto = em.createNamedQuery("MarcaTipoProducto.getAll", MarcaTipoProducto.class).getResultList();
			return marcasTipoProducto;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<Object[]> getAllFK(String columnaMarca, String columnaTipoProducto) {
		EntityManager em = this.emf.createEntityManager();
		
		if (columnaMarca == null) {
			columnaMarca = "id";
		}
		
		if (columnaTipoProducto == null) {
			columnaTipoProducto = "id";
		}
		
		TypedQuery<Object[]> query = em.createQuery("SELECT mtp.id, m." + columnaMarca + ", tp." + columnaTipoProducto + " FROM MarcaTipoProducto mtp INNER JOIN Marca m ON mtp.marcaFK = m.id INNER JOIN TipoProducto tp ON mtp.tipoProductoFK = tp.id", Object[].class);
		List<Object[]> marcasTipoProducto = query.getResultList();
		
		return marcasTipoProducto;
	}

	@Override
	public MarcaTipoProducto find(int id) {
		EntityManager em = this.emf.createEntityManager();
		MarcaTipoProducto mtp = new MarcaTipoProducto();
		try {
			mtp = em.find(MarcaTipoProducto.class, id);
			return mtp;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
	
	@Override
	public List<MarcaTipoProducto> findAll(String columna, String valor) {
		EntityManager em = this.emf.createEntityManager();
		
		boolean numerico;
		try {
			Integer.parseInt(valor);
			numerico = true;
		} catch (Exception ex) {
			numerico = false;
		}
		
		if (numerico == true) {
			TypedQuery<MarcaTipoProducto> query = em.createQuery("SELECT mtp FROM MarcaTipoProducto mtp WHERE " + "mtp." + columna + " = " + valor, MarcaTipoProducto.class);
			List<MarcaTipoProducto> marcasTipoProducto = query.getResultList();
			
			return marcasTipoProducto;
		}
		else {
			TypedQuery<MarcaTipoProducto> query = em.createQuery("SELECT mtp FROM MarcaTipoProducto mtp WHERE " + "mtp." + columna + " = " + "'" + valor + "'", MarcaTipoProducto.class);
			List<MarcaTipoProducto> marcasTipoProducto = query.getResultList();
			
			return marcasTipoProducto;
		}
	}
	
	@Override
	public boolean update(MarcaTipoProducto mtp) {
		EntityManager em = this.emf.createEntityManager();
		MarcaTipoProducto marcaTipoProductoBD = new MarcaTipoProducto();
		try {
			marcaTipoProductoBD = em.find(MarcaTipoProducto.class, mtp.getId());
			marcaTipoProductoBD.setId(mtp.getId());
			marcaTipoProductoBD.setMarcaFK(mtp.getMarcaFK());
			marcaTipoProductoBD.setTipoProductoFK(mtp.getTipoProductoFK());
			em.merge(marcaTipoProductoBD);
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
				Query query = em.createQuery("UPDATE MarcaTipoProducto SET " + columna + " = " + valor + " WHERE id" + " = " + id); 
				query.executeUpdate();
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		else {
			try {
				Query query = em.createQuery("UPDATE MarcaTipoProducto SET " + columna + " = " + "'" + valor + "'" + " WHERE id" + " = " + id); 
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
		MarcaTipoProducto mtp = em.find(MarcaTipoProducto.class, id);
		
		try {
			mtp = em.find(MarcaTipoProducto.class, id);
			em.remove(mtp);
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
