package BLL;

import GUI.VentanaAdmin;
import GUI.VentanaGEquipos;
import GUI.VentanaGJugadores;
import GUI.VentanaGPartidos;
import GUI.VentanaGUsuarios;
import GUI.VentanaLogin;
import GUI.VentanaObservador;
import GUI.VentanaTemporada;
import GUI.VentanaUsuario;
import GUI.VentanaVEquipos;
import GUI.VentanaVJugadores;
import GUI.VentanaVPartidos;

public class AbrirVentanas {

	public static void vePAdmin() {
		VentanaAdmin vePAdmin = new VentanaAdmin();
		vePAdmin.setTitle("Bienvenido Admin");
		vePAdmin.setVisible(true); // Mostrar la pantalla de c�rculo
	}
	
	public static void vePUsuario() {
		VentanaUsuario vePUsuario = new VentanaUsuario();
		vePUsuario.setVisible(true); // Mostrar la pantalla de c�rculo
		vePUsuario.setTitle("Bienvenido " + VentanaLogin.getUsuario());
	}

	public static void vePObservador() {
		VentanaObservador vePObservador = new VentanaObservador();
		vePObservador.setVisible(true); // Mostrar la pantalla de c�rculo
		vePObservador.setTitle("Bienvenido Observador");
	}
	
	public static void veGPartidos() {
		VentanaGPartidos veGPartidos = new VentanaGPartidos();
		veGPartidos.setVisible(true); // Mostrar la pantalla de c�rculo
	}
	
	public static void veVPartidos() {
		VentanaVPartidos veVPartidos = new VentanaVPartidos();
		veVPartidos.setVisible(true); // Mostrar la pantalla de c�rculo
	}
	
	
	public static void veGEquipos() {
		VentanaGEquipos veGEquipos = new VentanaGEquipos();
		veGEquipos.setVisible(true); // Mostrar la pantalla de c�rculo
	}
	
	
	public static void veVEquipos() {
		VentanaVEquipos veVEquipos = new VentanaVEquipos();
		veVEquipos.setVisible(true); // Mostrar la pantalla de c�rculo
	}
	
	
	public static void veGJugadores() {
		VentanaGJugadores veGJugadores = new VentanaGJugadores();
		veGJugadores.setVisible(true); // Mostrar la pantalla de c�rculo
	}
	
	public static void veVJugadores() {
		VentanaVJugadores veVJugadores = new VentanaVJugadores();
		veVJugadores.setVisible(true); // Mostrar la pantalla de c�rculo
	}
	
	
	public static void veGUsuarios() {
		VentanaGUsuarios veGUsuarios = new VentanaGUsuarios();

		veGUsuarios.setVisible(true); // Mostrar la pantalla de c�rculo
	}
	
	public static void veTemporada() {
		VentanaTemporada veTemporada = new VentanaTemporada();

		veTemporada.setVisible(true); // Mostrar la pantalla de c�rculo
	}
	
	public static void cerrar() {
		VentanaLogin veLogin = new VentanaLogin();

		veLogin.setVisible(true); // Mostrar la pantalla de c�rculo
	}
	

}

