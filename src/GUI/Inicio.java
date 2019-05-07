package GUI;

import java.sql.SQLException;
import javax.persistence.PersistenceException;

import DAL.Mysqldal;
import DAL.Objectdbdal;

public class Inicio {
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// Iniciamos la conexión con mySQL.
			Mysqldal.connect("localhost/bizkaiabasket", "root", "");
			System.out.println("MySQL Conexión correcta.");
			// Iniciamos la conexión con objectDB 
			// (y, además generamos un usuario por defecto de ser necesario).
			Objectdbdal.connect("localhost/bizkaiaBasket.odb", "admin", "admin");
			System.out.println("objectDB Conexión correcta.");

		}
		// Si surje algun error durante la conexión con mySQL:
		catch (SQLException e) {
			System.out.println("MySQL Conexión incorrecta.");
			e.printStackTrace();
			System.exit(0);
		}
		// Si surje algun error durante la conexión con objectDB:
		catch (PersistenceException e) {
			System.out.println("objectDB Conexión incorrecta.");
			e.printStackTrace();
			System.exit(0);		
		}
		
		finally {
			
			Objectdbdal.recogerLogin();
			
		}
	}
}