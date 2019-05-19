package BLL;

import javax.swing.table.AbstractTableModel;

import DAL.ConectorDAL;
import DAL.OBJECTDB.Usuario;

public class UsuariosTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -2051863362929544334L;

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		try {
			return ConectorDAL.GetActual().getTodo(new Usuario()).size();
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
				return "Usuario";
			case 1:
				return "Contraseña";
			case 2: 
				return "Rol";
		}
		return null;
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		try {
			Usuario usuario = (Usuario) ConectorDAL.GetActual().getTodo(new Usuario()).get(fila);
			
			switch (columna) {
				case 0: // Recoge el dato de la columna 1 (El nombre de usuario).
					return usuario.getUsuario();
				case 1: // Recoge el dato de la columna 2 (La contraseña de usuario).
					return usuario.getPassword();
				case 2: // Recoge el dato de la columna 3 (El tipo de usuario).
					return usuario.getRol();
				default:
					return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Usuario getElementoEn(int indice) throws Exception {
		if (indice == -1) {
			return null;
		}

		return (Usuario) ConectorDAL.GetActual().getTodo(new Usuario()).get(indice);
	}
	
	public void Actualizar() {
		fireTableDataChanged();
	}
}
