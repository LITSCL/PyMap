package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.MarcaTipoProducto;

@Local
public interface MarcaTipoProductoDAOLocal {
	public boolean save(MarcaTipoProducto mtp);
	public List<MarcaTipoProducto> getAll();
	public List<Object[]> getAllFK(String columnaMarca, String columnaTipoProducto);
	public MarcaTipoProducto find(int id);
	public List<MarcaTipoProducto> findAll(String columna, String valor);
	public boolean update(MarcaTipoProducto mtp);
	public boolean updateOne(int id, String columna, String valor);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}
