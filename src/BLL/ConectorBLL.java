package BLL;

import DAL.ConectorDAL;
import DAL.DataModel;
import DAL.Roles;
import DAL.MYSQL.Equipo;
import DAL.MYSQL.Jugador;
import DAL.MYSQL.Partido;
import DAL.OBJECTDB.Usuario;

public class ConectorBLL {
	
	private static Usuario usuarioActual;
	
	public static Roles IniciarSesion(String usuario, String password) 
			throws Exception {
		usuarioActual = UsuariosBLL.GetActual().Get(usuario);
		
		if(usuarioActual != null && usuarioActual.getPassword().equals(password)) {
			return usuarioActual.getRol();
		}
		else {
			usuarioActual = null;
			return Roles.BLOQUEADO;
		}
	}
	
	public static String GetUsuarioActual() {
		if (usuarioActual != null) {
			return usuarioActual.getUsuario();
		}
		else {
			return "";
		}
	}
	
	public static Roles GetRolActual() {
		if (usuarioActual != null) {
			return usuarioActual.getRol();
		}
		else {
			return Roles.BLOQUEADO;
		}
	}
	
	public static void CerrarSesion() {
		usuarioActual = null;
	}

	public static void ModificarUsuario(String original, String usuario, String password, Roles rol) throws Exception {
		if (original.equals(usuarioActual.getUsuario()) && (!usuario.equals(original) || rol != usuarioActual.getRol())) {
			throw new Exception("No puedes modificar tu nombre ni tu rol.");
		}
		if (checkNombreValido(original, usuario)) {
			
			UsuariosBLL.GetActual().Editar(original, usuario, password, rol);
		}
	}
	
	private static boolean checkNombreValido(String original, String usuario) throws Exception {
		int conteo = 0;
		 for (DataModel user : ConectorDAL.GetActual().getTodo(new Usuario())) {
			 if (((Usuario)user).getUsuario().equals(usuario)) {
				 conteo++;
			 }
		 }
		 
		 if ((original == null || (original != null && !original.equals(usuario))) && conteo > 0) {
				throw new Exception("Ya existe un usuario con este nombre.");
		 }
		 
		 return true;
	}

	public static void CrearUsuario(String usuario, String password, Roles rol) throws Exception {
		if (checkNombreValido(null, usuario)) {
			UsuariosBLL.GetActual().Crear(usuario, password, rol);
		}
	}
	
	//método para borrar usuarios de la BBDD objectdb
	public static void BorrarUsuario(Usuario usuario) throws Exception {
		if (usuario.getUsuario().equals(usuarioActual.getUsuario())) {
			throw new Exception("No puedes borrar tu usuario");
		}
		else if (usuario.getRol() == Roles.ADMINISTRADOR){
			 int conteo = 0;
			 for (DataModel user : ConectorDAL.GetActual().getTodo(new Usuario())) {
				 if (((Usuario)user).getRol() == Roles.ADMINISTRADOR) {
					 conteo++;
				 }
			 }
			 // Si conteo es menos a 2
			 if (conteo < 2) {
				 	// Lanzamos mensaje
					throw new Exception("Tiene que haber al menus 1 ADMINISTRADOR.");
			 }
		}
		
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


