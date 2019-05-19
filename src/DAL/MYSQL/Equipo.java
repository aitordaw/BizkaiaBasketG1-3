package DAL.MYSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipo extends MySqlDataModel {
	private String codigo, nombre, municipio, email, terreno;
	
	public Equipo() {
		super();
		campoBusqueda = "cod_equipo";
	}
	
	
	public Equipo(String codigo, String nombre, String municipio, String email, String terreno) {
		this();
		this.codigo = codigo;
		this.nombre = nombre;
		this.municipio = municipio;
		this.email = email;
		this.terreno = terreno;
	}


	@Override
	public Equipo crearDesdeBdd(ResultSet rs) throws SQLException {
		Equipo obj = new Equipo ();
		obj.setCodigo(rs.getString("cod_equipo"));
		obj.setNombre(rs.getString("nom_equipo"));
		obj.setMunicipio(rs.getString("municipio"));
		obj.setEmail(rs.getString("email"));
		obj.setTerreno(rs.getString("terreno"));
		
		return obj;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	@Override
	public String crearParametrosBdd() {
		return String.format("('%s','%s','%s','%s','%s')", codigo, nombre, municipio, email, terreno);
	}
}
