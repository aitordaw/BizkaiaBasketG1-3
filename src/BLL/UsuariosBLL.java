package BLL;

import DAL.ConectorDAL;
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
		return (Usuario) ConectorDAL.GetActual().Get(new Usuario(), busqueda);
	}
	
}
