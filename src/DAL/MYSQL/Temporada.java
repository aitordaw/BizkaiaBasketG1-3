package DAL.MYSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Temporada extends MySqlDataModel{
	private String codigo, equipo;
	private int puntos;
		
		public Temporada() {
			super();
			campoBusqueda = "cod_equipo";
		}
		
		@Override
		public Temporada crearDesdeBdd(ResultSet rs) throws SQLException {
			Temporada obj = new Temporada ();
			obj.setCodigo(rs.getString("cod_equipo"));
			obj.setEquipo(rs.getString("nom_equipo"));
			obj.setPuntos(rs.getInt("puntos"));
			
			return obj;
		}

		public Temporada(String codigo, String equipo, int puntos) {
			this();
			this.codigo = codigo;
			this.equipo = equipo;
			this.puntos = puntos;
		}
		
		@Override
		public String crearParametrosBdd() {
			return String.format("('%s','%s','%s')", codigo, equipo, puntos);
		}


		public String getCodigo() {
			return codigo;
		}


		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}


		public String getEquipo() {
			return equipo;
		}


		public void setEquipo(String equipo) {
			this.equipo = equipo;
		}


		public int getPuntos() {
			return puntos;
		}


		public void setPuntos(int puntos) {
			this.puntos = puntos;
		}
	}
