package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.TipoNegocio;

@Local
public interface TipoNegocioDAOLocal {
	public boolean save(TipoNegocio tn);
	public List<TipoNegocio> getAll();
	public TipoNegocio find(int id);
	public List<TipoNegocio> findAll(String columna, String valor);
	public boolean update(TipoNegocio tn);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}