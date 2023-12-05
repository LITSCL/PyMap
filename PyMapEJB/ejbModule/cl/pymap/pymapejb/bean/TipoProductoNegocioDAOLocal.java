package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.TipoProductoNegocio;

@Local
public interface TipoProductoNegocioDAOLocal {
	public boolean save(TipoProductoNegocio tpn);
	public List<TipoProductoNegocio> getAll();
	public List<Object[]> getAllFK(String columnaTipoProducto, String columnaNegocio);
	public TipoProductoNegocio find(int id);
	public List<TipoProductoNegocio> findAll(String columna, String valor);
	public boolean update(TipoProductoNegocio tpn);
	public boolean updateOne(int id, String columna, String valor);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}
