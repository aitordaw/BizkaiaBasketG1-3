package DAL;

import javax.persistence.*;
import java.util.*;

public class objectdbdal {

	private static EntityManagerFactory odb;

	// Método pra comexión con objectDB
	public static void connect(String url, String usuario, String password) throws Exception {
		// Prueba de conexión a BBDD point.odb
		odb = Persistence.createEntityManagerFactory("objectdb://"+url+";user="+usuario+";password="
				+password); 

		EntityManager em = odb.createEntityManager();

		try {
			em.getTransaction().begin(); // Se necesit una transacción para validar y probar la conexión
		}
		catch (Exception e) {
			// Lanzar la excepción para controlarla en inicio
			throw e; 
		}
		finally {
			em.close();
		}

	}

}
