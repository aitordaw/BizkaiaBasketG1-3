package GUI;

import java.util.ArrayList;

import BLL.AbrirVentanas;
import DAL.ConectorDAL;
import DAL.DataModel;
import DAL.MYSQL.Equipo;

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