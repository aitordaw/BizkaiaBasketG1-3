package DAL.MYSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAL.DataModel;
import DAL.OrigenDatos;

public abstract class MySqlDataModel extends DataModel {
	
	protected MySqlDataModel() {
		origen = OrigenDatos.MYSQL;
	}

	@Override
	public ArrayList<DataModel> crearPorDefecto() {
		return null;
	}

	public abstract MySqlDataModel crearDesdeBdd(ResultSet rs) throws SQLException;

	public abstract String crearParametrosBdd() throws SQLException;
}
