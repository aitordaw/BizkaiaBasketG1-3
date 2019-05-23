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
	}
}