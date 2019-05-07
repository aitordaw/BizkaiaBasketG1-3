package GUI;

import java.sql.SQLException;
import java.util.Scanner;

import javax.persistence.PersistenceException;

import DAL.Mysqldal;
import DAL.Objectdbdal;
import DAL.OBJECTDB.Usuario;

public class Inicio {
		
	private static Usuario login;
	static String u;
	static String p;
	
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
			System.exit(0);
		}
		// Si surje algun error durante la conexi�n con objectDB:
		catch (PersistenceException e) {
			System.out.println("objectDB Conexi�n incorrecta.");
			e.printStackTrace();
			System.exit(0);		
		}
		
		finally {
			
			while (login == null) {
			
				Scanner teclado = new Scanner(System.in);
				System.out.println("Ingrese nombre de usuario:");
				u = teclado.nextLine();
				System.out.println("Ingrese contrase�a:");
				p = teclado.nextLine();
				teclado.close();
				
				login = new Usuario(u, p);
			
			// System.out.println(u + " " + p);
				System.out.println(login);
				
			}
		}
	}
}