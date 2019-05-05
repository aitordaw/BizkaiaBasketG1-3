package GUI;

import java.sql.SQLException;

import DAL.mysqldal;
import DAL.objectdbdal;

public class inicio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			mysqldal.connect("localhost/bizkaiabasket", "root", "");// Si es correto con estos datos pasados a mysqldal.conect()
			
			System.out.println("MySQL Conexión correcta.");

			
		}
		
		catch (SQLException e) {
			
			
			System.out.println("MySQL Conexión incorrecta.");

			e.printStackTrace();
			
		}
		
try {
			
			objectdbdal.connect("localhost/bizkaiaBasket.odb", "admin", "admin");// Si es correto
			
			System.out.println("objectDB Conexión correcta.");

			
		}
		
		catch (Exception e) {
			
			
			System.out.println("objectDB Conexión incorrecta.");

			e.printStackTrace();
			
		}
		

	}

}
