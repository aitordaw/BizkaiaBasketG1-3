package DAL.MYSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Partido extends MySqlDataModel {
private String codigo, eqLocal, eqVisitante, temporada, fecha;
int faltVisitante, faltLocal, ptosLocal, ptosVisitante;
	
	public Partido() {
		super();
		campoBusqueda = "cod_partido";
	}
	
	
	public Partido(String codigo, String eqLocal, String eqVisitante, int ptosLocal, int ptosVisitante, int faltLocal, int faltVisitante, String temporada, String fecha) {
		this();
		this.codigo = codigo;
		this.eqLocal = eqLocal;
		this.eqVisitante = eqVisitante;
		this.ptosLocal = ptosLocal;
		this.ptosVisitante = ptosVisitante;
		this.faltLocal = faltLocal;
		this.faltVisitante = faltVisitante;
		this.temporada = temporada;
		this.fecha = fecha;
	}
	
	@Override
	public Partido crearDesdeBdd(ResultSet rs) throws SQLException {
		Partido obj = new Partido();
		obj.setCodigo(rs.getString("cod_partido"));
		obj.setEqLocal(rs.getString("equipoloc"));
		obj.setEqVisitante(rs.getString("equipovis"));
		obj.setPtosLocal(rs.getInt("puntosloc"));
		obj.setPtosVisitante(rs.getInt("puntosvis"));
		obj.setFaltLocal(rs.getInt("faltasloc"));
		obj.setFaltVisitante(rs.getInt("faltasvis"));
		obj.setTemporada(rs.getString("temporada"));
		obj.setFecha(rs.getString("fecha"));
		
		return obj;
	}

	@Override
	public String crearParametrosBdd() {
		return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s')", codigo, eqLocal, eqVisitante, ptosLocal, ptosVisitante, faltLocal, faltVisitante, temporada, fecha);
	}

	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getEqLocal() {
		return eqLocal;
	}


	public void setEqLocal(String eqLocal) {
		this.eqLocal = eqLocal;
	}


	public String getEqVisitante() {
		return eqVisitante;
	}

 
	public void setEqVisitante(String eqVisitante) { 
		this.eqVisitante = eqVisitante;
	}


	public String getTemporada() {
		return temporada;
	}


	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public int getFaltVisitante() {
		return faltVisitante;
	}


	public void setFaltVisitante(int faltVisitante) {
		this.faltVisitante = faltVisitante;
	}


	public int getFaltLocal() {
		return faltLocal;
	}


	public void setFaltLocal(int faltLocal) {
		this.faltLocal = faltLocal;
	}


	public int getPtosLocal() {
		return ptosLocal;
	}


	public void setPtosLocal(int ptosLocal) {
		this.ptosLocal = ptosLocal;
	}


	public int getPtosVisitante() {
		return ptosVisitante;
	}


	public void setPtosVisitante(int ptosVisitante) {
		this.ptosVisitante = ptosVisitante;
	}
	
	@Override
	public String toString() {
		return "Partido [faltVisitante=" + faltVisitante + ", faltLocal=" + faltLocal + ", ptosLocal=" + ptosLocal
				+ ", ptosVisitante=" + ptosVisitante + "]";
	}
}
