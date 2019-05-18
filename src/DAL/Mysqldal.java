/*package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysqldal {

	private static Connection conexion;

	// Método para conexión con BDD MySQL
	public static void connect(String url, String usuario, String password) 
			throws SQLException {
		try {
			// Inicializamos la conexión con mySQL.
			conexion = DriverManager.getConnection("jdbc:mysql://" + url, usuario, password); 
		}
		// Si surje alguna excepción durante la conexión con mySQL:
		catch (SQLException e) {
			// Lanzar la excepción para controlarla en la siguiente capa.
			throw e; 
		}
	}
}*/