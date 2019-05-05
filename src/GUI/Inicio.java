package GUI;

import java.sql.SQLException;

import javax.persistence.PersistenceException;

import DAL.Mysqldal;
import DAL.Objectdbdal;

public class Inicio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// Iniciamos la conexi�n con mySQL.
			Mysqldal.connect("localhost/bizkaiabasket", "root", "");
			System.out.println("MySQL Conexi�n correcta.");
			// Iniciamos la conexi�n con objectDB 
			// (y, adem�s generamos un usuario por defecto de ser necesario).
			Objectdbdal.connect("localhost/bizkaiaBasket.odb", "admin", "admin");
			System.out.println("objectDB Conexi�n correcta.");

		}
		// Si surje algun error durante la conexi�n con mySQL:
		catch (SQLException e) {
			System.out.println("MySQL Conexi�n incorrecta.");
			e.printStackTrace();
		}
		// Si surje algun error durante la conexi�n con objectDB:
		catch (PersistenceException e) {
			System.out.println("objectDB Conexi�n incorrecta.");
			e.printStackTrace();
		}
	}

}
