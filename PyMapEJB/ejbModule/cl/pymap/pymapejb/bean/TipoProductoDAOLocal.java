package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.TipoProducto;

@Local
public interface TipoProductoDAOLocal {
	public boolean save(TipoProducto tp);
	public List<TipoProducto> getAll();
	public List<Object[]> getAllFK(String columnaCategoria);
	public TipoProducto find(int id);
	public List<TipoProducto> findAll(String columna, String valor);
	public boolean update(TipoProducto tp);
	public boolean updateOne(int id, String columna, String valor);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}
