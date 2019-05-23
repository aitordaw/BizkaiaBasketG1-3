package BLL;

import javax.swing.table.AbstractTableModel;

import DAL.ConectorDAL;
import DAL.MYSQL.Temporada;

public class TemporadaTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 6830592030136827069L;

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		try {
			return ConectorDAL.GetActual().getTodo(new Temporada()).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public String getColumnName(int columna) { // Recoge el título de cada columna por posición 0-9

		switch (columna) {
		
			case 0:
				return "Código";
			case 1:
				return "Equipo";
			case 2: 
				return "Puntos totales";
		}
		return null;
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		try {
			Temporada temporada = (Temporada)ConectorDAL.GetActual().getTodo(new Temporada()).get(fila);
			
			switch (columna) {
				case 0: 
					return temporada.getCodigo();
				case 1: 
					return temporada.getEquipo();
				case 2: 
					return temporada.getPuntos();
				default:
					return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Temporada getElementoEn(int indice) throws Exception {
		if (indice == -1) {
			return null;
		}

		return (Temporada) ConectorDAL.GetActual().getTodo(new Temporada()).get(indice);
	}
	
	public void Actualizar() {
		fireTableDataChanged();
	}

}
