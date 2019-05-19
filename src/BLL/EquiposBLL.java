package BLL;

import DAL.ConectorDAL;
import DAL.Roles;
import DAL.MYSQL.Equipo;
import DAL.OBJECTDB.Usuario;

public class EquiposBLL extends BaseBLL<Equipo> {

	private static EquiposBLL actual;
	
	public static EquiposBLL GetActual() {
		if (actual == null) {
			actual = new EquiposBLL();
		}
		
		return actual;
	}
	
	@Override
	public Equipo Get(String busqueda) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void Editar(String original, String codigo, String nombre, String municipio, String email, String terreno) 
			throws Exception {		
		ConectorDAL.GetActual().editar(new Equipo(), 
				String.format("cod_equipo = '%s', nom_equipo = '%s', municipio= '%s', email = '%s', terreno = '%s'", 
						codigo, nombre, municipio, email, terreno), original, null);
	}

	public void Crear(String codigo, String nombre, String municipio, String email, String terreno) throws Exception {
		ConectorDAL.GetActual().crear(new Equipo(codigo, nombre, municipio, email, terreno));
	}

	public void Borrar(String equipo) throws Exception {
		ConectorDAL.GetActual().borrar(new Equipo(), equipo);
	}
}