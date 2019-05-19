package BLL;

import DAL.ConectorDAL;
import DAL.DataModel;
import DAL.Roles;
import DAL.OBJECTDB.Usuario;

public class ConectorBLL {
	
	private static Usuario usuarioActual;
	
	public static Roles IniciarSesion(String usuario, String password) 
			throws Exception {
		usuarioActual = UsuariosBLL.GetActual().Get(usuario);
		
		if(usuarioActual != null && usuarioActual.getPassword().equals(password)) {
			return usuarioActual.getRol();
		}
		else {
			usuarioActual = null;
			return Roles.BLOQUEADO;
		}
	}
	
	public static String GetUsuarioActual() {
		if (usuarioActual != null) {
			return usuarioActual.getUsuario();
		}
		else {
			return "";
		}
	}
	
	public static Roles GetRolActual() {
		if (usuarioActual != null) {
			return usuarioActual.getRol();
		}
		else {
			return Roles.BLOQUEADO;
		}
	}
	
	public static void CerrarSesion() {
		usuarioActual = null;
	}

	public static void ModificarUsuario(String original, String usuario, String password, Roles rol) throws Exception {
		if (original.equals(usuarioActual.getUsuario()) && (!usuario.equals(original) || rol != usuarioActual.getRol())) {
			throw new Exception("No puedes modificar tu nombre ni tu rol.");
		}
		
		UsuariosBLL.GetActual().Editar(original, usuario, password, rol);
	}

	public static void CrearUsuario(String usuario, String password, Roles rol) throws Exception {
		UsuariosBLL.GetActual().Crear(usuario, password, rol);
	}

	public static void BorrarUsuario(Usuario usuario) throws Exception {
		if (usuario.getUsuario().equals(usuarioActual.getUsuario())) {
			throw new Exception("No puedes borrar tu usuario");
		}
		else if (usuario.getRol() == Roles.ADMINISTRADOR){
			 int conteo = 0;
			 for (DataModel user : ConectorDAL.GetActual().getTodo(new Usuario())) {
				 if (((Usuario)user).getRol() == Roles.ADMINISTRADOR) {
					 conteo++;
				 }
			 }
			 
			 if (conteo < 2) {
					throw new Exception("Tiene que haber al menus 1 ADMINISTRADOR.");
			 }
		}
		
		UsuariosBLL.GetActual().Borrar(usuario.getUsuario());
	}
}
