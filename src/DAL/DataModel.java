package DAL;

import java.util.ArrayList;
// Clase base para los mdoelos de datos
public abstract class DataModel {
	// Define el origen de los datos (MySql/ObjectDB)
	protected OrigenDatos origen; 
	// Define el campo que se utilizara para las consultas
	protected String campoBusqueda;
	// Metodo para crear los objetos por defecto
	public abstract ArrayList<DataModel> crearPorDefecto();
}
