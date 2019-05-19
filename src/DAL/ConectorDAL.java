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
import DAL.OBJECTDB.Usuario;

public class ConectorDAL {

	// Esta variable almacena el único objeto de este tipo que debe existir.
	private static ConectorDAL actual;
	
	// Almacenamos en variables las conexiones que usará nuestro conector.
	@SuppressWarnings("unused")
	private static Connection mySqlActual;
	private static EntityManagerFactory objectDbFactoryActual;
	
	// Almacenamos en variables los datos de conexion.
	@SuppressWarnings("unused")
	private String urlMySql;
	@SuppressWarnings("unused")
	private String usuarioMySql;
	@SuppressWarnings("unused")
	private String passwordMySql;

	// Al crear un constructor por defecto privado, evitamos que este objeto pueda ser instanciado fuera de esta clase.
	private ConectorDAL() {}
	// Creamos el constructor que usaremos para iniciar nuestro conector.
	private ConectorDAL(String pUrlMySql, String pUsuarioMySql, String pPasswordMySql, String pUrlObjectDb, String pUsuarioObjectDb, String pPasswordObjectDb) {
		urlMySql = pUrlMySql;
		usuarioMySql = pUsuarioMySql;
		passwordMySql = pPasswordMySql;
		
		objectDbFactoryActual = Persistence.createEntityManagerFactory(
				String.format(Constantes.CADENA_OBJECTDB, pUrlObjectDb, pUsuarioObjectDb, pPasswordObjectDb));
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
	
	private DataModel getObjectDb(DataModel obj, String valorBusqueda) 
			throws Exception {
		return unicoObjectDb(obj, 
				String.format("SELECT p FROM %s p WHERE %s = '%s'", obj.getClass().getSimpleName(), obj.campoBusqueda, valorBusqueda));
	}
	
	private ArrayList<DataModel> getListaObjectDb(DataModel obj) 
			throws Exception {
		return listaObjectDb(obj, String.format("SELECT p FROM %s p", obj.getClass().getSimpleName()));
	}

	private DataModel getMySql(DataModel obj, String valorBusqueda) {
		return null;
	}

	private ArrayList<DataModel> getListaMySql(DataModel obj) {
		
		return new ArrayList<DataModel>();
	}
	
	private void editarObjectDb(String queryString, Object enumerated) throws Exception {
		try {
			EntityManager em = getConexionObjectDbActual();

			Query query = em.createQuery(queryString);
			
			if (enumerated != null) {
				query = query.setParameter("enum", enumerated);
			}
			
			query.executeUpdate();
			em.getTransaction().commit();
			em.close();
		}
		catch (PersistenceException pe) {
			throw new Exception(pe.getMessage());
		}
	}
	
	private void crearPorDefecto(EntityManager em, DataModel obj) {
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
	
	private DataModel unicoObjectDb(DataModel obj, String queryString) throws Exception {
		EntityManager em;
		DataModel result = null;
		
		try {
			// Comenzamos la transaccion.
			em = getConexionObjectDbActual();
		}
		catch (PersistenceException pe) {
			throw new Exception("No se ha podido conectar con ObjectDB: \r\n" + pe.getMessage());
		}
		// Creamos nuestra consulta.
		Query query = em.createQuery(queryString);
			
		// Comprobamos que exista algun elemento.
		try {
			result = (DataModel)query.getSingleResult();
		}
		catch (PersistenceException pe) {
			if (!pe.getMessage().equals("No matching results for a unique query")) {
				crearPorDefecto(em, obj);
				result = (DataModel)query.getSingleResult();
			}
		}
		
		em.close();
		
		return result;
	}
	
	private ArrayList<DataModel> listaObjectDb(DataModel obj, String queryString) throws Exception {
		EntityManager em;
		ArrayList<DataModel> result = new ArrayList<DataModel>();
		
		try {
			// Comenzamos la transaccion.
			em = getConexionObjectDbActual();
		}
		catch (PersistenceException pe) {
			throw new Exception("No se ha podido conectar con ObjectDB: \r\n" + pe.getMessage());
		}

		// Creamos nuestra consulta.
		Query query = em.createQuery(queryString);
			
		// Comprobamos que exista algun elemento.
		try {
			result.addAll(query.getResultList());
		}
		catch (PersistenceException pe) {
			crearPorDefecto(em, obj);
			result.addAll(query.getResultList());
		}
		
		em.close();
		
		return result;
	}
	
	private void crearObjectDb(DataModel obj) {
		EntityManager em = getConexionObjectDbActual();

		em.persist(obj);
		em.getTransaction().commit();
		em.close();
	}
	
	public void crear(DataModel obj) throws Exception {
		if (obj instanceof MySqlDataModel) {

		}
		else if (obj instanceof ObjectDbDataModel){
			crearObjectDb(obj);
		}
		else {
			// Si no se reconoce el tipo de enlace:
			throw new Exception("Enlace de datos desconocido.");
		}
	}
	
	public DataModel get(DataModel obj, String valorBusqueda) 
			throws Exception {
		// Dependiendo del tipo de enlace del modelo de datos (DataModel.java):
		if (obj instanceof MySqlDataModel) {
			// Si es MySQL:
			// TODO: Conectar con mysql.
			return getMySql(obj, valorBusqueda);
		}
		else if (obj instanceof ObjectDbDataModel){
			return getObjectDb(obj, valorBusqueda);
		}
		else {
			// Si no se reconoce el tipo de enlace:
			throw new Exception("Enlace de datos desconocido.");
		}
	}	
	
	public ArrayList<DataModel> getTodo(DataModel obj) throws Exception{
		// Dependiendo del tipo de enlace del modelo de datos (DataModel.java):
		if (obj instanceof MySqlDataModel) {
			// Si es MySQL:
			return getListaMySql(obj);
		}
		else if (obj instanceof ObjectDbDataModel){
			return getListaObjectDb(obj);
		}
		else {
			// Si no se reconoce el tipo de enlace:
			throw new Exception("Enlace de datos desconocido.");
		}
	}
	
	public void editar(DataModel obj, String set, String valorBusqueda, Object enumerated) throws Exception {
		// Dependiendo del tipo de enlace del modelo de datos (DataModel.java):
				if (obj instanceof MySqlDataModel) {

				}
				else if (obj instanceof ObjectDbDataModel){
					editarObjectDb(String.format("UPDATE %s SET %s WHERE %s = '%s'",
							obj.getClass().getSimpleName(), set, obj.campoBusqueda, valorBusqueda), enumerated);
				}
				else {
					// Si no se reconoce el tipo de enlace:
					throw new Exception("Enlace de datos desconocido.");
				}
	}
	
	public void borrar(DataModel obj, String valorBusqueda) throws Exception {
		try {
			EntityManager em = getConexionObjectDbActual();

			em.createQuery(String.format("DELETE FROM %s p WHERE %s = '%s'", 
					obj.getClass().getSimpleName(), obj.campoBusqueda, valorBusqueda)).executeUpdate();
			
			em.getTransaction().commit();
			em.close();
		}
		catch (PersistenceException pe) {
			throw new Exception(pe.getMessage());
		}
	}
}