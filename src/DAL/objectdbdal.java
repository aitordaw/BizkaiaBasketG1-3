package DAL;

import javax.persistence.*;
import java.util.*;

public class objectdbdal {

	private static EntityManagerFactory odb;

	// M�todo pra comexi�n con objectDB
	public static void connect(String url, String usuario, String password) throws Exception {
		// Prueba de conexi�n a BBDD point.odb
		odb = Persistence.createEntityManagerFactory("objectdb://"+url+";user="+usuario+";password="
				+password); 

		EntityManager em = odb.createEntityManager();

		try {
			em.getTransaction().begin(); // Se necesit una transacci�n para validar y probar la conexi�n
		}
		catch (Exception e) {
			// Lanzar la excepci�n para controlarla en inicio
			throw e; 
		}
		finally {
			em.close();
		}

	}

}
