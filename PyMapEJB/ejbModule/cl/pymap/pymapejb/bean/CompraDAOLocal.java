package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.Compra;

@Local
public interface CompraDAOLocal {
	public boolean save(Compra c);
	public List<Compra> getAll();
	public List<Object[]> getAllFK(String columnaUsuario, String columnaNegocio);
	public Compra find(int id);
	public List<Compra> findAll(String columna, String valor);
	public boolean update(Compra c);
	public boolean updateOne(int id, String columna, String valor);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}
