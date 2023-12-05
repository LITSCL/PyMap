package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.Negocio;

@Local
public interface NegocioDAOLocal {
	public boolean save(Negocio n);
	public List<Negocio> getAll();
	public List<Object[]> getAllFK(String columnaUsuario, String columnaTipoNegocio);
	public Negocio find(String rut);
	public List<Negocio> findAll(String columna, String valor);
	public boolean update(Negocio n);
	public boolean updateOne(String rut, String columna, String valor);
	public boolean delete(String rut);
	public List<Object[]> customQuery(String jpql);
}
