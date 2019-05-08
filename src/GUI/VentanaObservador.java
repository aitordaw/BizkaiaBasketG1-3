package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class VentanaObservador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelObservador;
	private JButton btnEquipos;
	private JButton btnJugadores;
	private JButton btnPartidos;
	private JButton btnTemporada;
	private JButton btnCSesion;
	private JLabel lblFondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaObservador() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelObservador = new JPanel();
		panelObservador.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelObservador, BorderLayout.CENTER);
		panelObservador.setLayout(null);
		
		btnEquipos = new JButton("Equipos");
		btnEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnEquipos();
			}
		});		
		btnEquipos.setForeground(Color.BLACK);
		btnEquipos.setBackground(Color.WHITE);
		btnEquipos.setFont(new Font("Arial", Font.BOLD, 20));
		btnEquipos.setBounds(71, 185, 150, 77);
		panelObservador.add(btnEquipos);
		
		btnJugadores = new JButton("Jugadores");
		btnJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnJugadores();
			}
		});
		btnJugadores.setBackground(Color.WHITE);
		btnJugadores.setFont(new Font("Arial", Font.BOLD, 20));
		btnJugadores.setBounds(286, 185, 150, 77);
		panelObservador.add(btnJugadores);
		
		btnPartidos = new JButton("Partidos");
		btnPartidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnPartidos();
			}
		});		
		btnPartidos.setBackground(Color.WHITE);
		btnPartidos.setFont(new Font("Arial", Font.BOLD, 20));
		btnPartidos.setBounds(71, 301, 150, 76);
		panelObservador.add(btnPartidos);
		
		btnTemporada = new JButton("Temporada");
		btnTemporada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnTemporada();
			}
		});	
		btnTemporada.setBackground(Color.WHITE);
		btnTemporada.setFont(new Font("Arial", Font.BOLD, 20));
		btnTemporada.setBounds(287, 302, 150, 77);
		panelObservador.add(btnTemporada);
		
		btnCSesion = new JButton("Cerrar Sesion");
		btnCSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnCerrar();
			}
		});		
		btnCSesion.setBackground(Color.WHITE);
		btnCSesion.setFont(new Font("Arial", Font.BOLD, 12));
		btnCSesion.setForeground(Color.BLACK);
		btnCSesion.setBounds(485, 437, 113, 38);
		panelObservador.add(btnCSesion);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaObservador.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelObservador.add(lblFondo);
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

		BLL.AbrirVentanas.veVPartidos();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
	
	private void BtnTemporada() {

		BLL.AbrirVentanas.veTemporada();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
	
	private void BtnCerrar() {

		BLL.AbrirVentanas.cerrar();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
}

