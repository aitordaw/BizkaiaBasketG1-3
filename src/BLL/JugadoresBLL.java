package BLL;

import DAL.ConectorDAL;
import DAL.MYSQL.Jugador;

public class JugadoresBLL extends BaseBLL<Jugador>  {
	
private static JugadoresBLL actual;
	
	public static JugadoresBLL GetActual() {
		if (actual == null) {
			actual = new JugadoresBLL();
		}
		
		return actual;
	}
	
	@Override
	public Jugador Get(String busqueda) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void Editar(String original, String dni, String nombre, String apellidos, String cod_equipo) 
			throws Exception {		
		ConectorDAL.GetActual().editar(new Jugador(), 
				String.format("dni = '%s', nombre = '%s', apellidos = '%s', cod_equipo = '%s'", 
						dni, nombre, apellidos, cod_equipo), original, null);
	}

	public void Crear(String dni, String nombre, String apellidos, String cod_equipo) throws Exception {
		ConectorDAL.GetActual().crear(new Jugador(dni, nombre, apellidos, cod_equipo));
	}

	public void Borrar(String jugador) throws Exception {
		ConectorDAL.GetActual().borrar(new Jugador(), jugador);
	}
}
