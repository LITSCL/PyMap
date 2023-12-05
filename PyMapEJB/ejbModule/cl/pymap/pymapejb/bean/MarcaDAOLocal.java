package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.Marca;

@Local
public interface MarcaDAOLocal {
	public boolean save(Marca m);
	public List<Marca> getAll();
	public Marca find(int id);
	public List<Marca> findAll(String columna, String valor);
	public boolean update(Marca m);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}
