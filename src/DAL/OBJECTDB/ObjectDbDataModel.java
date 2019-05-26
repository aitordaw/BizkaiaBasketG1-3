package DAL.OBJECTDB;

import java.io.Serializable;
import java.util.ArrayList;

import DAL.DataModel;
import DAL.OrigenDatos;
//Clase base abtracta para todos los modelos de datos con origen ObjectDB
@SuppressWarnings("serial")
public class ObjectDbDataModel extends DataModel implements Serializable {

	protected ObjectDbDataModel() {
		origen = OrigenDatos.OBJECTDB;
	}
	// Metodo para crear elementos por defecto
	@Override
	public ArrayList<DataModel> crearPorDefecto() {
		// TODO Auto-generated method stub
		return null;
	}
}
