package BLL;

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
			return Roles.ERRONEO;
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
			return Roles.ERRONEO;
		}
	}
	
	public static void CerrarSesion() {
		usuarioActual = null;
	}
}
