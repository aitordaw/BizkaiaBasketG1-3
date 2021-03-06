package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import BLL.AbrirVentanas;
import BLL.ConectorBLL;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaUsuario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelUsuario;
	private JButton btnJugadores;
	private JButton btnPartidos;
	private JButton btnCSesion;
	private JButton btnEquipos;
	private JLabel lblFondo;

	/**
	 * Create the frame.
	 */
	public VentanaUsuario() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelUsuario = new JPanel();
		panelUsuario.setBackground(Color.LIGHT_GRAY);
		panelUsuario.setBorder(null);
		contentPane.add(panelUsuario, BorderLayout.CENTER);
		panelUsuario.setLayout(null);
		
		btnEquipos = new JButton("Equipos");
		btnEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BtnEquipos();
			}
		});
		btnEquipos.setForeground(Color.BLACK);
		btnEquipos.setFont(new Font("Arial", Font.BOLD, 20));
		btnEquipos.setBackground(Color.WHITE);
		btnEquipos.setBounds(285, 534, 396, 77);
		panelUsuario.add(btnEquipos);
		
		btnJugadores = new JButton("Jugadores");
		btnJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnJugadores();
			}
		});
		btnJugadores.setFont(new Font("Arial", Font.BOLD, 20));
		btnJugadores.setBackground(Color.WHITE);
		btnJugadores.setBounds(285, 336, 396, 77);
		panelUsuario.add(btnJugadores);
		
		btnPartidos = new JButton("Partidos");
		btnPartidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnPartidos();
			}
		});
		
		btnPartidos.setFont(new Font("Arial", Font.BOLD, 20));
		btnPartidos.setBackground(Color.WHITE);
		btnPartidos.setBounds(285, 435, 396, 77);
		panelUsuario.add(btnPartidos);
		
		btnCSesion = new JButton("Cerrar Sesion");
		btnCSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnCerrar();
			}
		});
		btnCSesion.setForeground(Color.BLACK);
		btnCSesion.setFont(new Font("Arial", Font.BOLD, 12));
		btnCSesion.setBackground(Color.WHITE);
		btnCSesion.setBounds(392, 722, 186, 38);
		panelUsuario.add(btnCSesion);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaUsuario.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelUsuario.add(lblFondo);
	}
	
	private void BtnEquipos() {

		BLL.AbrirVentanas.veVEquipos();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
	
	private void BtnJugadores() {

		BLL.AbrirVentanas.veVJugadores();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
	
	private void BtnPartidos() {

		BLL.AbrirVentanas.veGPartidos();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
	
	private void BtnCerrar() {
		ConectorBLL.CerrarSesion();
		AbrirVentanas.veLogin();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)
	}
	
}
