package BLL;

import DAL.ConectorDAL;
import DAL.MYSQL.Partido;

public class PartidosBLL extends BaseBLL<Partido> {
	
private static PartidosBLL actual;
	
	public static PartidosBLL GetActual() {
		if (actual == null) {
			actual = new PartidosBLL();
		}
		
		return actual;
	}
	
	@Override
	public Partido Get(String busqueda) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void Editar(String original, String codigo, String eqLocal, String eqVisitante, int ptosLocal, int ptosVisitante, int faltLocal, int faltVisitante, String cod_liga, String fecha) 
			throws Exception {		
		ConectorDAL.GetActual().editar(new Partido(), 
				String.format("cod_partido = '%s', equipoloc = '%s', equipovis = '%s', puntosloc = %s, puntosvis = %s, faltasloc = %s, faltasvis = %s, cod_liga = '%s', fecha = '%s'", 
					 codigo, eqLocal, eqVisitante, ptosLocal, ptosVisitante, faltLocal, faltVisitante, cod_liga, fecha), original, null);
	}

	public void Crear(String codigo, String eqLocal, String eqVisitante, int ptosLocal, int ptosVisitante, int faltLocal, int faltVisitante, String cod_liga, String fecha) throws Exception {
		ConectorDAL.GetActual().crear(new Partido(codigo, eqLocal, eqVisitante, ptosLocal, ptosVisitante, faltLocal, faltVisitante, cod_liga, fecha));
	}

	public void Borrar(String partido) throws Exception {
		ConectorDAL.GetActual().borrar(new Partido(), partido);
	}

}
