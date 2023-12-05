package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.ProductoNegocio;

@Local
public interface ProductoNegocioDAOLocal {
	public boolean save(ProductoNegocio pn);
	public List<ProductoNegocio> getAll();
	public List<Object[]> getAllFK(String columnaProducto, String columnaNegocio);
	public ProductoNegocio find(int id);
	public List<ProductoNegocio> findAll(String columna, String valor);
	public boolean update(ProductoNegocio pn);
	public boolean updateOne(int id, String columna, String valor);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}
