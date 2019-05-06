package DAL;

import javax.persistence.*;

import DAL.OBJECTDB.Usuario;

import java.util.*;

public class Objectdbdal {

	private static EntityManagerFactory odb;

	// Método para conectar con objectDB: Pede lanzar excepciones
	public static void connect(String url, String usuario, String password) 
			throws PersistenceException 
	{
		// Configura el Factory para conexiones de persistencia de datos.
		odb = Persistence.createEntityManagerFactory("objectdb://" + url + ";user=" 
				+ usuario + ";password=" + password);

		// Creamos una conexion con objectDB.
		EntityManager em = odb.createEntityManager();

		try {
			// Iniciamos una transacción de datos con objectDB (Si esto genera una excepcion es 
			// porque la configuración proporcionada es incorrecta.
			em.getTransaction().begin();
			
			// Creamos una consulta para obtener el número de usuarios registrados en el sistema.
			Query numeroUsuariosQuery = em.createQuery("SELECT COUNT(usuario) FROM Usuario usuario");
			
			
			try {
				// Ejecutamos la consulta y guardamos su resultado en una variable.
				long numeroUsuarios = (long) numeroUsuariosQuery.getSingleResult();
			
				// Si no existe ningun usuario registrado en el sistema:
				if (numeroUsuarios == 0) {
					// Creamos la cuenta por defecto.
					Usuario admin = new Usuario("admin", "admin");
					// Ponemos en cola la persistencia del nuevo usuario.
					em.persist(admin);
					// Almacenamos en la base de datos los cambios preparados en la transacción. 
					em.getTransaction().commit();
				} 
				// SOLO PARA PRUEBA: Si existe algun usuario:
				else {
					// Muestra la cantidad de usuarios registrados en el sistema.
					System.out.println("Usuarios registrados: " + numeroUsuarios);
			}

		
			// Si surje alguna excepcion durante la conexión con objectDB:
			} catch (PersistenceException e) {
		
					// Creamos la cuenta por defecto.
					Usuario admin = new Usuario("admin", "admin");
					// Ponemos en cola la persistencia del nuevo usuario.
					em.persist(admin);
					// Almacenamos en la base de datos los cambios preparados en la transacción. 
					em.getTransaction().commit();
				
			// Finalmente:
			}
			
		// Si surje alguna excepcion durante la conexión con objectDB:	
		} catch (PersistenceException e) {
			// Lanzar la excepción para controlarla en la siguiente capa.
			throw e;
			
		// Finalmente:
		} finally {
			// Cerramos el enlace con objectDB.
			em.close();
		}
	}
}