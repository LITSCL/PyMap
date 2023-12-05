package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.ProductoCompra;

@Local
public interface ProductoCompraDAOLocal {
	public boolean save(List<Object[]> carrito, int idCompra);
	public List<ProductoCompra> getAll();
	public List<Object[]> getAllFK(String columnaCompra, String columnaProducto);
	public ProductoCompra find(int id);
	public List<ProductoCompra> findAll(String columna, String valor);
	public boolean update(ProductoCompra pc);
	public boolean updateOne(int id, String columna, String valor);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}
