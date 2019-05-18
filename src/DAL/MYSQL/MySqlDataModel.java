package DAL.MYSQL;

import java.util.ArrayList;

import DAL.DataModel;
import DAL.OrigenDatos;

public class MySqlDataModel extends DataModel {
	
	protected MySqlDataModel() {
		origen = OrigenDatos.MYSQL;
	}

	@Override
	public ArrayList<DataModel> crearPorDefecto() {
		return null;
	}
}
