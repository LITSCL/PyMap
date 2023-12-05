package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.Categoria;

@Local
public interface CategoriaDAOLocal {
	public boolean save(Categoria c);
	public List<Categoria> getAll();
	public Categoria find(int id);
	public List<Categoria> findAll(String columna, String valor);
	public boolean update(Categoria c);
	public boolean delete(int id);
	public List<Object[]> customQuery(String jpql);
}
