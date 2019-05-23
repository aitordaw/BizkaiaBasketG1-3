package BLL;

import DAL.ConectorDAL;
import DAL.MYSQL.Temporada;

public class TemporadaBLL extends BaseBLL<Temporada> {
	
	private static TemporadaBLL actual;
	
	public static TemporadaBLL GetActual() {
		if (actual == null) {
			actual = new TemporadaBLL();
		}
		
		return actual;
	}
	
	@Override
	public Temporada Get(String busqueda) throws Exception {
		return (Temporada) ConectorDAL.GetActual().get(new Temporada(), busqueda);
	}

}
