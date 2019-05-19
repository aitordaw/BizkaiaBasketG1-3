package BLL;

import javax.jdo.JDOHelper;

import DAL.ConectorDAL;
import DAL.Roles;
import DAL.OBJECTDB.Usuario;

public class UsuariosBLL extends BaseBLL<Usuario> {

	private static UsuariosBLL actual;
	
	public static UsuariosBLL GetActual() {
		if (actual == null) {
			actual = new UsuariosBLL();
		}
		
		return actual;
	}
	
	@Override
	public Usuario Get(String busqueda) throws Exception {
		return (Usuario) ConectorDAL.GetActual().get(new Usuario(), busqueda);
	}
	
	public void Editar(String original,String usuario, String password, Roles rol) 
			throws Exception {		
		ConectorDAL.GetActual().editar(new Usuario(), 
				String.format("usuario = '%s', password = '%s', rol= :enum", usuario, password), original, rol);
	}

	public void Crear(String usuario, String password, Roles rol) throws Exception {
		ConectorDAL.GetActual().crear(new Usuario(usuario, password, rol));
	}
	
	public void Borrar(String usuario) throws Exception {
		ConectorDAL.GetActual().borrar(new Usuario(), usuario);
	}
}
