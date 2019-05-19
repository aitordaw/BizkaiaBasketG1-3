package BLL;

import javax.swing.table.AbstractTableModel;

import DAL.ConectorDAL;
import DAL.MYSQL.Equipo;

public class EquiposTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -2051863362929544334L;

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		try {
			return ConectorDAL.GetActual().getTodo(new Equipo()).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public String getColumnName(int columna) { // Recoge el título de cada columna por posición 0-3

		switch (columna) {
		
			case 0:
				return "Código";
			case 1:
				return "Nombre de equipo";
			case 2: 
				return "Municipio";
			case 3:
				return "e-mail";
			case 4: 
				return "Terreno";
		}
		return null;
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		try {
			Equipo equipo = (Equipo) ConectorDAL.GetActual().getTodo(new Equipo()).get(fila);
			
			switch (columna) {
				case 0: 
					return equipo.getCodigo();
				case 1: 
					return equipo.getNombre();
				case 2: 
					return equipo.getMunicipio();
				case 3:
					return equipo.getEmail();
				case 4: 
					return equipo.getTerreno();
				default:
					return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Equipo getElementoEn(int indice) throws Exception {
		if (indice == -1) {
			return null;
		}

		return (Equipo) ConectorDAL.GetActual().getTodo(new Equipo()).get(indice);
	}
	
	public void Actualizar() {
		fireTableDataChanged();
	}
}
