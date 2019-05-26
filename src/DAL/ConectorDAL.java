package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		// Creamos la conexion con objectDB que utilizaremos a lo largo de la ejecucion
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
	
	// Devuelve tipo EntityManager
	private EntityManager getConexionObjectDbActual() 
			throws PersistenceException {
		// Inicializamos el entitymanager
		EntityManager result = objectDbFactoryActual.createEntityManager();
		
		try	{// iniciamos la transacción
			result.getTransaction().begin();
			return result;
		}
		catch (PersistenceException pe)
		{
			throw pe;
		}
	}	
	// Devuelve una una conexion con MySQL
	private Connection getConexionMySqlActual() throws Exception {
		// SI no existe una conexion abierta
		if (mySqlActual == null) {
			try {// Creamos la conexion.
				mySqlActual = DriverManager.getConnection("jdbc:mysql://" + urlMySql, usuarioMySql, passwordMySql);
			}
			catch (SQLException e) {
				throw new Exception("No se ha podido conectar con MySQL: \r\n" + e.getMessage());
			}
		}
		// DEvolvemos la conexion
		return mySqlActual;
	}
	// Metodo para obtener un elemento de objectDB
	private DataModel getObjectDb(DataModel obj, String valorBusqueda) 
			throws Exception {
		return unicoObjectDb(obj, 
				String.format("SELECT p FROM %s p WHERE %s = '%s'", obj.getClass().getSimpleName(), obj.campoBusqueda, valorBusqueda));
	}
	// Metodo para obtener todos los elementos de objectDB
	private ArrayList<DataModel> getListaObjectDb(DataModel obj) 
			throws Exception {
		return listaObjectDb(obj, String.format("SELECT p FROM %s p", obj.getClass().getSimpleName()));
	}
	// Metodo para obtener un elemento de MySQL
	private MySqlDataModel getMySql(MySqlDataModel obj, String valorBusqueda) throws Exception {
		try {// Creamos el statement y la consulta
			Statement st = getConexionMySqlActual().createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s WHERE %s = '%s'", obj.getClass().getSimpleName(), obj.campoBusqueda, valorBusqueda));
			
			MySqlDataModel result = null;
			// Si existe algun elemento lo devolvemos
			// Si no se devolvera null.
			if (rs.next()) {
				result = obj.crearDesdeBdd(rs);  
			}
			
			rs.close();
			st.close();
			// Devolvemos el objeto encontrado o null
			return result;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	// Metodo para obtener todos los elemenots MySQL
	private ArrayList<DataModel> getListaMySql(MySqlDataModel obj) throws Exception {
		try {// Creamos el statement y la consulta
			Statement st = getConexionMySqlActual().createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s", obj.getClass().getSimpleName()));
			// CReamos la lista que vamos a devolver
			ArrayList<DataModel> result = new ArrayList<DataModel>();
			// Poblamos la lista con los elementos devueltos por la consulta.
			while(rs.next()) {
				result.add(obj.crearDesdeBdd(rs));
			}
			
			rs.close();
			st.close();
			// Devolvemos la lista de elementos (Vacia si no se ha encontrado ninguno).
			return result;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	// Metodo para editar ObjectDB
	private void editarObjectDb(String queryString, Object enumerated) throws Exception {
		try {// Obtenemos ENtityManager
			EntityManager em = getConexionObjectDbActual();
			// Generamos la consulta
			Query query = em.createQuery(queryString);
			// Si la consulta contiene algun elemento enumerado
			if (enumerated != null) {
				// Añadimos este como un objeto en lugar de como un string
				query = query.setParameter("enum", enumerated);
			}
			// Ejecutamos la consulta
			query.executeUpdate();
			em.getTransaction().commit();
			em.close();
		}
		catch (PersistenceException pe) {
			throw new Exception(pe.getMessage());
		}
	}
	// Metodo para crear los elementos por defecto de un tipo dado
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
	// Metodo para obtener un unico objeto de objectDB
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
	// Metodo para obtener todos los elementos de OBjectDB
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
			if (!pe.getMessage().equals("No matching results for a unique query")) {
				crearPorDefecto(em, obj);
				result.addAll(query.getResultList());
			}
		}
		
		em.close();
		
		return result;
	}
	// Metodo para crear un elemento en objectDB
	private void crearObjectDb(DataModel obj) {
		EntityManager em = getConexionObjectDbActual();
		// Le decimos a em que persista el objeto dado
		em.persist(obj);
		// Le decimos a em que efectue todos los cambios
		em.getTransaction().commit();
		em.close();
	}
	// Metodo para crear elementos 
	public void crear(DataModel obj) throws Exception {
		// Si el modelo a crear hereda de MySqlDatamodel
		if (obj instanceof MySqlDataModel) {
			// Redirigmos al metodo de crear una entidad en SQL
			crearMySql((MySqlDataModel) obj);
		}// SI no si el modelo a crearhereda de ObjectDbDataModel
		else if (obj instanceof ObjectDbDataModel){
			// Redirigimos el metodo de crear una entidad en ObjectDB
			crearObjectDb(obj);
		}
		else {
			// Si no se reconoce el tipo de enlace:
			throw new Exception("Enlace de datos desconocido.");
		}
	}
	// Metodo para crear un elemento en SQL
	private void crearMySql(MySqlDataModel obj) throws Exception {
		try {// Inicializamos statement
			Statement st = getConexionMySqlActual().createStatement();
			// Generamos y ejecutamos la consulta de insercion
			int count = st.executeUpdate(String.format("INSERT INTO %s values %s", obj.getClass().getSimpleName(), obj.crearParametrosBdd()));
			st.close();

			if (count == 0) {// Si no se ha creado ningun elemento lanzamos excepcion
				throw new Exception("No se pudo crear ningun elemento.");
			}
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	// Metodo para obtener un elemento
	public DataModel get(DataModel obj, String valorBusqueda) 
			throws Exception {
		// Dependiendo del tipo de enlace del modelo de datos (DataModel.java):
		// Si es MySQL
		if (obj instanceof MySqlDataModel) {
// ENviamos la solicitud al metodo de obtener de MySQL
			return getMySql((MySqlDataModel)obj, valorBusqueda);
		}// SI no si es ObjectDB
		else if (obj instanceof ObjectDbDataModel){
			// ENviamos la solicitud al metodo de obtener de ObjectDB
			return getObjectDb(obj, valorBusqueda);
		}
		else {
			// Si no se reconoce el tipo de enlace:
			throw new Exception("Enlace de datos desconocido.");
		}
	}	
	// Metodo para obtener todos los datos y devolverlos en una lista
	public ArrayList<DataModel> getTodo(DataModel obj) throws Exception{
		// Dependiendo del tipo de enlace del modelo de datos (DataModel.java):
		if (obj instanceof MySqlDataModel) {
			// Si es MySQL:
			return getListaMySql((MySqlDataModel)obj);
		}
		else if (obj instanceof ObjectDbDataModel){
			return getListaObjectDb(obj);
		}
		else {
			// Si no se reconoce el tipo de enlace:
			throw new Exception("Enlace de datos desconocido.");
		}
	}
	// Metodo para editar un elemento
	public void editar(DataModel obj, String set, String valorBusqueda, Object enumerated) throws Exception {
		// Dependiendo del tipo de enlace del modelo de datos (DataModel.java):
				if (obj instanceof MySqlDataModel) {
					// Generamos parte de la consulta MySQL
					editarMySql(String.format("UPDATE %s SET %s WHERE %s = '%s'",
							obj.getClass().getSimpleName(), set, obj.campoBusqueda, valorBusqueda));
				}
				else if (obj instanceof ObjectDbDataModel){
					// Generamos parte de la consulta ObjectDb (Datamodel.java)
					editarObjectDb(String.format("UPDATE %s SET %s WHERE %s = '%s'",
							obj.getClass().getSimpleName(), set, obj.campoBusqueda, valorBusqueda), enumerated);
				}
				else {
					// Si no se reconoce el tipo de enlace:
					throw new Exception("Enlace de datos desconocido.");
				}
	}
	// Metodo para editar un elemento en MySQL
	private void editarMySql(String query) throws Exception {
		try {// Obtenemos la conexion
			Statement st = getConexionMySqlActual().createStatement();
			// Efectuamos la consulta
			int count = st.executeUpdate(query);
			st.close();
			// Si no se ha editado nada
			if (count == 0) {// SE lanza excepcion
				throw new Exception("No se pudo editar ningun elemento.");
			}
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	// Metodo para borrado
	public void borrar(DataModel obj, String valorBusqueda) throws Exception {
		// Generamos la consulta
		String query = String.format("DELETE FROM %s WHERE %s = '%s'", 
				obj.getClass().getSimpleName(), obj.campoBusqueda, valorBusqueda);
		// Dependiendo del tipo de enlace del modelo de datos (DataModel.java):
		if (obj instanceof MySqlDataModel) {
			// Si es MySQL:
			borrarMySql(query);
		}
		else if (obj instanceof ObjectDbDataModel){
			borrarObjectDb(query);
		}
		else {
			// Si no se reconoce el tipo de enlace:
			throw new Exception("Enlace de datos desconocido.");
		}
	}
	// MEtodo pra borrar un elemento de ObejctDB
	private void borrarObjectDb(String query) throws Exception {
		try {// Obtenemos la conexion
			EntityManager em = getConexionObjectDbActual();
			// Efectuamos los cambios
			em.createQuery(query).executeUpdate();
			
			em.getTransaction().commit();
			em.close();
		}
		catch (PersistenceException pe) {
			throw new Exception(pe.getMessage());
		}
	}
	// Metodo para borrar un elemento de MySQL
	private void borrarMySql(String query) throws Exception {
		try {// Obtenemos la conexion
			Statement st = getConexionMySqlActual().createStatement();
			// Ejecutamos la consulta
			int count = st.executeUpdate(query);
			st.close();
			// SI no se ha borrado ningun elemento
			if (count == 0) {// LAnzamos excepcion
				throw new Exception("No se pudo borrar ningun elemento.");
			}
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}