package cl.pymap.pymapejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.pymap.pymapejb.model.Usuario;

@Local
public interface UsuarioDAOLocal {
	public boolean save(Usuario u);
	public List<Usuario> getAll();
	public Usuario find(String correo);
	public List<Usuario> findAll(String columna, String valor);
	public boolean update(Usuario u);
	public boolean delete(String correo);
	public List<Object[]> customQuery(String jpql);
}
