package DAL;

import java.util.ArrayList;

public abstract class DataModel {
	protected OrigenDatos origen;
	protected String campoBusqueda;
	
	public abstract ArrayList<DataModel> crearPorDefecto();
}
