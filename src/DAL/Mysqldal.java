package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysqldal {

	private static Connection conexion;

	// M�todo para conexi�n con BDD MySQL
	public static void connect(String url, String usuario, String password) 
			throws SQLException {
		try {
			// Inicializamos la conexi�n con mySQL.
			conexion = DriverManager.getConnection("jdbc:mysql://" + url, usuario, password); 
		}
		// Si surje alguna excepci�n durante la conexi�n con mySQL:
		catch (SQLException e) {
			// Lanzar la excepci�n para controlarla en la siguiente capa.
			throw e; 
		}
	}
}