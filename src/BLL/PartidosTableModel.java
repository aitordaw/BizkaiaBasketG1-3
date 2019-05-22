package BLL;

import javax.swing.table.AbstractTableModel;

import DAL.ConectorDAL;
import DAL.MYSQL.Partido;

public class PartidosTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 6589486074984205526L;

	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public int getRowCount() {
		try {
			return ConectorDAL.GetActual().getTodo(new Partido()).size();
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
				return "Eq. Local";
			case 2: 
				return "Eq. Visitante";
			case 3:
				return "Ptos. Local";
			case 4: 
				return "Ptos. Visitante";
			case 5:
				return "Faltas Local";
			case 6: 
				return "Faltas Visitante";
			case 7:
				return "Temporada";
			case 8: 
				return "Fecha";
		}
		return null;
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		try {
			Partido partido = (Partido) ConectorDAL.GetActual().getTodo(new Partido()).get(fila);
			
			switch (columna) {
				case 0: 
					return partido.getCodigo();
				case 1: 
					return partido.getEqLocal();
				case 2: 
					return partido.getEqVisitante();
				case 3:
					return partido.getPtosLocal();
				case 4: 
					return partido.getPtosVisitante();
				case 5: 
					return partido.getFaltLocal();
				case 6: 
					return partido.getFaltVisitante();
				case 7:
					return partido.getCod_liga();
				case 8: 
					return partido.getFecha();
				default:
					return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Partido getElementoEn(int indice) throws Exception {
		if (indice == -1) {
			return null;
		}

		return (Partido) ConectorDAL.GetActual().getTodo(new Partido()).get(indice);
	}
	
	public void Actualizar() {
		fireTableDataChanged();
	}

}
