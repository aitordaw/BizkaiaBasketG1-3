package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysqldal {
	
	private static Connection conexion;
	
	// M�todo pra comexi�n con BDD MySQL
	public static void connect(String url, String usuario, String password) throws SQLException {
		
		try{  

			conexion = DriverManager.getConnection("jdbc:mysql://"+url, usuario, password); // Estos datos hay que pasarlos como par�metros ya que de eso se encarga la BLL

			} 

			catch (SQLException e){  

							
			throw e; // Lanzar la excepci�n para controlarla en inicio
			
		}
		
	}

}
