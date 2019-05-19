package GUI;

import BLL.AbrirVentanas;
import DAL.ConectorDAL;

public class BizkaiaBasket {
			
	/*  */
	public static void main(String[] args) {
		ConectorDAL.Iniciar("localhost/bizkaiabasket", "root", "", "localhost/bizkaiaBasket.odb", "admin", "admin");

		try {
			AbrirVentanas.veLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//
		// PRUEBAS
		/*try {
			JOptionPane.showMessageDialog(null, UsuariosBLL.GetActual().Get("admin").toString(), "OK", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			ConectorDAL.GetActual().Get(new Jugador(), "001");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}*/
	}
}