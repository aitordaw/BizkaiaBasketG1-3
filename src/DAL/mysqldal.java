package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysqldal {
	
	private static Connection conexion;
	
	// Método pra comexión con BDD MySQL
	public static void connect(String url, String usuario, String password) throws SQLException {
		
		try{  

			conexion = DriverManager.getConnection("jdbc:mysql://"+url, usuario, password); // Estos datos hay que pasarlos como parámetros ya que de eso se encarga la BLL

			} 

			catch (SQLException e){  

							
			throw e; // Lanzar la excepción para controlarla en inicio
			
		}
		
	}

}
