package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.MarcaNegocio;

@Local
public interface MarcaNegocioDAOLocal {
	public boolean save(MarcaNegocio mn);
	public List<MarcaNegocio> getAll();
	public List<Object[]> getAllFK(String columnaMarca, String columnaNegocio);
	public MarcaNegocio find(int id);
	public List<MarcaNegocio> findAll(String columna, String valor);
	public boolean update(MarcaNegocio mn);
	public boolean updateOne(int id, String columna, String valor);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}
