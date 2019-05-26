package BLL;

import DAL.ConectorDAL;
import DAL.DataModel;
import DAL.Roles;
import DAL.MYSQL.Equipo;
import DAL.MYSQL.Jugador;
import DAL.MYSQL.Partido;
import DAL.OBJECTDB.Usuario;

// BLL principal que enviara cada consulta done corresponde.
public class ConectorBLL {
	// Usuario activo de la aplicacion.
	private static Usuario usuarioActual;
	// Meteodo para inciiar sesion.
	public static Roles IniciarSesion(String usuario, String password) 
			throws Exception {
		// Precarga del usuario activo si existe.
		usuarioActual = UsuariosBLL.GetActual().Get(usuario);
		// Existe exsiste el usuario con el que se intenta iniciar sesion y la contraseña es correcta
		if(usuarioActual != null && usuarioActual.getPassword().equals(password)) {
			// Devolvemos el rol del usuario actual
			return usuarioActual.getRol();
		}// Si no borramos el usuario precargado y devolvemos el rol BLOQUEADO.
		else {
			usuarioActual = null;
			return Roles.BLOQUEADO;
		}
	}
	// Devuelve el nombre dle usuario actual.
	public static String GetUsuarioActual() {
		// Si hay un usuario activo devolvemos su nombre.
		if (usuarioActual != null) {
			return usuarioActual.getUsuario();
		}// S no, devolvemos una cadena vacía.
		else {
			return "";
		}
	}
	// Devuelve el rol del usuario actual
	public static Roles GetRolActual() {
		// Si hay un usuario activo devolvemos su rol.
		if (usuarioActual != null) {
			return usuarioActual.getRol();
		}// Si no devolvemos el rol BLOQUEADO
		else {
			return Roles.BLOQUEADO;
		}
	}
	// Método para cerrar sesion borrando el usuario actual.
	public static void CerrarSesion() {
		usuarioActual = null;
	}
	// Metodo para modificar un usuario de la base de datos.
	public static void ModificarUsuario(String original, String usuario, String password, Roles rol) throws Exception {
		// Si el usuario actual trata de modificarse a el mismo y cambia el nombre o el rol.
		if (original.equals(usuarioActual.getUsuario()) && (!usuario.equals(original) || rol != usuarioActual.getRol())) {
			// Lanzamos una excepcion ya que no se puede modificar ni el nombre ni el rol de la cuenta en uso.
			throw new Exception("No puedes modificar tu nombre ni tu rol.");
		}// Si no existe ningún otro usuario con el mismo nombre
		if (checkNombreValido(original, usuario)) {
			// Modificamos el usuario.
			UsuariosBLL.GetActual().Editar(original, usuario, password, rol);
		}
	}
	// Comprueba que no exista otro nombre igual en la base de datos
	private static boolean checkNombreValido(String original, String usuario) throws Exception {
		int conteo = 0;
		// Para cada usuario en la base de datos
		 for (DataModel user : ConectorDAL.GetActual().getTodo(new Usuario())) {
			 // Si el nombre de usuario coincide con el que se quiere crear
			 if (((Usuario)user).getUsuario().equals(usuario)) {
				 // Sumamos uno al conteo.
				 conteo++;
			 }
		 }
		 // Si se esta creando o se está modificando y existe otro usuario con el mismo nombre
		 if ((original == null || (original != null && !original.equals(usuario))) && conteo > 0) {
			 	//Lanzamos una excepcion porque dos usuarios no pueden tener el mismo nombre
				throw new Exception("Ya existe un usuario con este nombre.");
		 }
		 // Devolvemos verdadero.
		 return true;
	}
	// Metodo para crear usuarios.
	public static void CrearUsuario(String usuario, String password, Roles rol) throws Exception {
		// Si el nombre es correcto.
		if (checkNombreValido(null, usuario)) {
			//Creamos el usuario
			UsuariosBLL.GetActual().Crear(usuario, password, rol);
		}
	}
	
	// Metodo para borrar usuarios de la BBDD objectdb.
	public static void BorrarUsuario(Usuario usuario) throws Exception {
		// Si se está intentando borrar el usuario actual.
		if (usuario.getUsuario().equals(usuarioActual.getUsuario())) {
			// Lanzamos una excepción porque no se puede borrar el usuario activo
			throw new Exception("No puedes borrar tu usuario");
		}// Si no si el usuario a borrar es un ADMINISTRADOR
		else if (usuario.getRol() == Roles.ADMINISTRADOR){
			 int conteo = 0;
			 // Comprobamos cuantos administradores existen
			 for (DataModel user : ConectorDAL.GetActual().getTodo(new Usuario())) {
				 if (((Usuario)user).getRol() == Roles.ADMINISTRADOR) {
					 conteo++;
				 }
			 }
			 // Si conteo es menor a 2
			 if (conteo < 2) {
				 	// Lanzamos exccepcion porque no se pueden borrar todos los administradores
					throw new Exception("Tiene que haber al menus 1 ADMINISTRADOR.");
			 }
		}
		// Si todo es correto borramos usuario
		UsuariosBLL.GetActual().Borrar(usuario.getUsuario());
	}
	
	// Método para crear modelo relleno de los datos necesarios para enviar al conectorDAL la orden de generar la inserción de fila en la tabla equipos en MySQL
	public static void CrearEquipo(String codigo, String nombre, String municipio, String email, String terreno) throws Exception {
		EquiposBLL.GetActual().Crear(codigo, nombre, municipio, email, terreno);
	}
	// Método para crear modelo semi vacío pasando únicamente el dato almacenado en 
	// cod_equipo para enviar al conectorDAL la orden de borrar la fila en la tabla equipos en MySQL
	public static void BorrarEquipo(Equipo equipo) throws Exception {
		EquiposBLL.GetActual().Borrar(equipo.getCodigo());
	}
	// Método para crear modelo relleno de los datos necesarios para enviar al conectorDAL la orden de modificar la fila en la tabla equipos en MySQL
	public static void ModificarEquipo(String original, String codigo, String nombre, String municipio, String email, String terreno) throws Exception {
			EquiposBLL.GetActual().Editar(original, codigo, nombre, municipio, email, terreno);
	}
	
	// Método para crear modelo relleno de los datos necesarios para enviar al conectorDAL la orden de generar la inserción de fila en la tabla jugadores en MySQL
	public static void CrearJugador(String dni, String nombre, String apellidos, String cod_equipo) throws Exception {
		JugadoresBLL.GetActual().Crear(dni, nombre, apellidos, cod_equipo);
	}
	// Método para crear modelo semi vacío pasando únicamente el dato almacenado en 
	// cod_jugador para enviar al conectorDAL la orden de borrar la fila en la tabla jugadores en MySQL
	public static void BorrarJugador(Jugador jugador) throws Exception {
		JugadoresBLL.GetActual().Borrar(jugador.getDni());
	}
	// Método para crear modelo relleno de los datos necesarios para enviar al conectorDAL la orden de modificar la fila en la tabla jugadores en MySQL
	public static void ModificarJugador(String original, String dni, String nombre, String apellidos, String cod_equipo) throws Exception {
		JugadoresBLL.GetActual().Editar(original, dni, nombre, apellidos, cod_equipo);
	}
	
	public static void CrearPartido(String codigo, String eqLocal,  String eqVisitante, int ptosLocal, int ptosVisitante, int faltLocal, int faltVisitante, String cod_liga, String fecha) throws Exception {
		PartidosBLL.GetActual().Crear(codigo, eqLocal, eqVisitante, ptosLocal, ptosVisitante, faltLocal, faltVisitante, cod_liga, fecha);
	}
	// Método para crear modelo semi vacío pasando únicamente el dato almacenado en 
	// cod_partido para enviar al conectorDAL la orden de borrar la fila en la tabla partidos en MySQL
	public static void BorrarPartido(Partido partido) throws Exception {
		PartidosBLL.GetActual().Borrar(partido.getCodigo());
	}
	// Método para crear modelo relleno de los datos necesarios para enviar al conectorDAL la orden de modificar la fila en la tabla equipos en MySQL
	public static void ModificarPartido(String original, String codigo, String eqLocal, String eqVisitante, int ptosLocal, int ptosVisitante, int faltLocal, int faltVisitante, String cod_liga, String fecha) throws Exception {
		PartidosBLL.GetActual().Editar(original, codigo, eqLocal, eqVisitante, ptosLocal, ptosVisitante, faltLocal, faltVisitante, cod_liga, fecha);
	}
}


