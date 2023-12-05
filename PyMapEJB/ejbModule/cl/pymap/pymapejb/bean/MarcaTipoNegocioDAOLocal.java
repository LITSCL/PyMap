package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.MarcaTipoNegocio;

@Local
public interface MarcaTipoNegocioDAOLocal {
	public boolean save(MarcaTipoNegocio mtn);
	public List<MarcaTipoNegocio> getAll();
	public List<Object[]> getAllFK(String columnaMarca, String columnaTipoNegocio);
	public MarcaTipoNegocio find(int id);
	public List<MarcaTipoNegocio> findAll(String columna, String valor);
	public boolean update(MarcaTipoNegocio mtn);
	public boolean updateOne(int id, String columna, String valor);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}
