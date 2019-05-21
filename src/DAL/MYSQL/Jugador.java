package DAL.MYSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Jugador extends MySqlDataModel {
	
private String dni, nombre, apellidos, cod_equipo;
	
	public Jugador() {
		super();
		campoBusqueda = "dni";
	}
	
	
	public Jugador(String dni, String nombre, String apellidos, String cod_equipo) {
		this();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.cod_equipo = cod_equipo;
	}

	@Override
	public Jugador crearDesdeBdd(ResultSet rs) throws SQLException {
		Jugador obj = new Jugador ();
		obj.setDni(rs.getString("dni"));
		obj.setNombre(rs.getString("nombre"));
		obj.setApellidos(rs.getString("apellidos"));
		obj.setCod_equipo(rs.getString("cod_equipo"));
		
		return obj;
	}


	@Override
	public String crearParametrosBdd() {
		return String.format("('%s','%s','%s','%s')", dni, nombre, apellidos, cod_equipo);
	}
	
	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getCod_equipo() {
		return cod_equipo;
	}


	public void setCod_equipo(String cod_equipo) {
		this.cod_equipo = cod_equipo;
	}

}
