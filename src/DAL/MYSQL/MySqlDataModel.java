package DAL.MYSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAL.DataModel;
import DAL.OrigenDatos;
// Clase base abtracta para todos los modelos de datos con origen MySQL
public abstract class MySqlDataModel extends DataModel {
	
	protected MySqlDataModel() {
		origen = OrigenDatos.MYSQL;
	}
	// Metodo para crear datos por defecto
	@Override
	public ArrayList<DataModel> crearPorDefecto() {
		return null;
	}
	// Metodo para generar una entidad a partir de los datos de MySQL
	public abstract MySqlDataModel crearDesdeBdd(ResultSet rs) throws SQLException;
	// Metodo para generar cvalores para las consultas
	public abstract String crearParametrosBdd() throws SQLException;
}
