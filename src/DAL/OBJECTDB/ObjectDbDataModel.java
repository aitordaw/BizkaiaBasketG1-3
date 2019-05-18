package DAL.OBJECTDB;

import java.io.Serializable;
import java.util.ArrayList;

import DAL.DataModel;
import DAL.OrigenDatos;

@SuppressWarnings("serial")
public class ObjectDbDataModel extends DataModel implements Serializable {

	protected ObjectDbDataModel() {
		origen = OrigenDatos.OBJECTDB;
	}

	@Override
	public ArrayList<DataModel> crearPorDefecto() {
		// TODO Auto-generated method stub
		return null;
	}
}
