package BLL;

import javax.swing.table.AbstractTableModel;

import DAL.ConectorDAL;
import DAL.MYSQL.Jugador;

public class JugadoresTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 2643425099655688169L;

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		try {
			return ConectorDAL.GetActual().getTodo(new Jugador()).size();
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
				return "DNI";
			case 1:
				return "Nombre";
			case 2: 
				return "Apellidos";
			case 3:
				return "Equipo";

		}
		return null;
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		try {
			Jugador jugador = (Jugador) ConectorDAL.GetActual().getTodo(new Jugador()).get(fila);
			
			switch (columna) {
				case 0: 
					return jugador.getDni();
				case 1: 
					return jugador.getNombre();
				case 2: 
					return jugador.getApellidos();
				case 3:
					return jugador.getCod_equipo();
				default:
					return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Jugador getElementoEn(int indice) throws Exception {
		if (indice == -1) {
			return null;
		}

		return (Jugador) ConectorDAL.GetActual().getTodo(new Jugador()).get(indice);
	}
	
	public void Actualizar() {
		fireTableDataChanged();
	}
}
