package DAL;

import java.sql.Connection;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import DAL.MYSQL.MySqlDataModel;
import DAL.OBJECTDB.ObjectDbDataModel;

// Esta clase gestionarça todos los enlaces de datos, cualquier solicitud pasará por aquí.
public class ConectorDAL {
	// Esta variable almacena el único objeto de este tipo que debe existir.
	private static ConectorDAL actual;
	// Almacenamos en variables las conexiones que usará nuestro conector.
	@SuppressWarnings("unused")
	private static Connection mySqlActual;
	// Almacenamos en variables los datos de conexion.
	@SuppressWarnings("unused")
	private String urlMySql;
	@SuppressWarnings("unused")
	private String usuarioMySql;
	@SuppressWarnings("unused")
	private String passwordMySql;
	
	private EntityManagerFactory objectDbFactoryActual;
	
	// Al crear un constructor por defecto privado, evitamos que este objeto pueda ser instanciado fuera de esta clase.
	private ConectorDAL() {}
	// Creamos el constructor que usaremos para iniciar nuestro conector.
	private ConectorDAL(String pUrlMySql, String pUsuarioMySql, String pPasswordMySql, String pUrlObjectDb, String pUsuarioObjectDb, String pPasswordObjectDb) {
		urlMySql = pUrlMySql;
		usuarioMySql = pUsuarioMySql;
		passwordMySql = pPasswordMySql;
		
		objectDbFactoryActual = Persistence.createEntityManagerFactory("objectdb://" + pUrlObjectDb + ";user=" 
				+ pUsuarioObjectDb + ";password=" + pPasswordObjectDb);
	}
	// Obtenemos la instancia de nuestro conector.
	// Si no se ha llamado al metodo Iniciar() lanzará una excepción.
	public static ConectorDAL GetActual() 
			throws Exception {
		if (actual == null) {
			throw new Exception("No se ha iniciado el Conector de datos.");
		}
		
		return actual;
	}
	// Iniciamos nuestra instancia con su configuración.
	public static void Iniciar(String pUrlMySql, String pUsuarioMySql, String pPasswordMySql, String pUrlObjectDb, String pUsuarioObjectDb, String pPasswordObjectDb) {
		actual = new ConectorDAL(pUrlMySql, pUsuarioMySql, pPasswordMySql, pUrlObjectDb, pUsuarioObjectDb, pPasswordObjectDb);
	}
	
	private EntityManager getConexionObjectDbActual() 
			throws PersistenceException {
		EntityManager result = objectDbFactoryActual.createEntityManager();
		
		try	{
			result.getTransaction().begin();
			return result;
		}
		catch (PersistenceException pe)
		{
			throw pe;
		}
	}
	
	public DataModel Get(DataModel obj, String valorBusqueda) 
			throws Exception {
		// Creamos un valor de retorno vacio.
		DataModel result = null;
		
		// Dependiendo del tipo de enlace del modelo de datos (DataModel.java):
		if (obj instanceof MySqlDataModel) {
			// Si es MySQL:
			// TODO: Conectar con mysql.
			throw new Exception("Funcion no implementada.");
		}
		else if (obj instanceof ObjectDbDataModel){
			EntityManager em;
			Query queryConteo;
			Query query;
			try {
				// Comenzamos la transaccion.
				em = getConexionObjectDbActual();
			}
			catch (PersistenceException pe) {
				throw new Exception("No se ha podido conectar con ObjectDB: \r\n" + pe.getMessage());
			}
			
			// Comprobamos que exista algun elemento.
			try {
				// Creamos una consulta para obtener el número de usuarios registrados en el sistema.
				queryConteo = em.createQuery("SELECT COUNT(" + obj.campoBusqueda + ") FROM " + obj.getClass().getSimpleName());
				queryConteo.getSingleResult();
			}
			catch (PersistenceException pe) {
				// Si no existen datos:
				
				// Creamos los datos por defecto definidos en el modelo de datos.
				ArrayList<DataModel> porDefecto = obj.crearPorDefecto();
				
				// Marcamos cada elemento por defecto para almacenarlo en la base de datos.
				for (DataModel elemento : porDefecto) {
					em.persist(elemento);
				}
				
				// Realizamos todas las transacciones marcadas.
				em.getTransaction().commit();
			}
			finally {
				// Creamos nuestra consulta.
				query = em.createQuery("SELECT p FROM " + obj.getClass().getSimpleName() + " p WHERE " + obj.campoBusqueda + " = '" + valorBusqueda + "'");
				
				// Si se encuentra un objeto, se asigna como valor de retorno.
				try {
					result = (DataModel)query.getSingleResult();
					em.close();
				}
				catch (PersistenceException pe) {
					// Si no se encuentra ningun objeto, devolvemos null.
					return null;
				}
			}
		}
		else {
			// Si no se reconoce el tipo de enlace:
			throw new Exception("Enlace de datos desconocido.");
		}
		
		// Devolvemos nuestro valor de retorno.
		return result;
	}	
}
