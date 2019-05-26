package GUI;



import BLL.AbrirVentanas;
import DAL.ConectorDAL;
// Inicio de la aplicacion
public class BizkaiaBasket {
			
	/*  */
	public static void main(String[] args) {
		// Enviamos datos d econexion al metodo iniciar
		ConectorDAL.Iniciar("localhost/bizkaiabasket", "root", "", "localhost/bizkaiaBasket.odb", "admin", "admin");

		try {
			// Inicializamos la ventana de login
			AbrirVentanas.veLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}