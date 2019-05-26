package BLL;

import DAL.ConectorDAL;
import DAL.MYSQL.Equipo;
// Clase que hereda de su base y gestiona los equipos
public class EquiposBLL extends BaseBLL<Equipo> {
	// Controla que solo exista una instancia de este tipo de objeto (SINGLETON)
	private static EquiposBLL actual;
	
	public static EquiposBLL GetActual() {
		if (actual == null) {
			actual = new EquiposBLL();
		}
		
		return actual;
	}
	// Método para obtener un equipo - No implementado
	@Override
	public Equipo Get(String busqueda) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	// método para editar un equipo
	public void Editar(String original, String codigo, String nombre, String municipio, String email, String terreno) 
			throws Exception {	
		// Generamos parte dela consulta SQL y enviamos la solicitud a la DAL
		ConectorDAL.GetActual().editar(new Equipo(), 
				String.format("cod_equipo = '%s', nom_equipo = '%s', municipio= '%s', email = '%s', terreno = '%s'", 
						codigo, nombre, municipio, email, terreno), original, null);
	}
	
	// Metodo para crear un equipo
	public void Crear(String codigo, String nombre, String municipio, String email, String terreno) throws Exception {
		// ENviamos la consulta a  la DAL
		ConectorDAL.GetActual().crear(new Equipo(codigo, nombre, municipio, email, terreno));
	}

	// Método para borrar un equipo
	public void Borrar(String equipo) throws Exception {
		// ENviamos la consulta a  la DAL
		ConectorDAL.GetActual().borrar(new Equipo(), equipo);
	}
}