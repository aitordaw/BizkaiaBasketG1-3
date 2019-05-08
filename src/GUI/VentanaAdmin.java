package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelAdmin;
	private JButton btnEquipos;
	private JButton btnJugadores;
	private JButton btnPartidos;
	private JButton btnUsuarios;
	private JButton btnCSesion;
	private JLabel lblFondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// CAMBIAR MAIN DESPUES DE PROBAR TODOS LOS BOTONES
					VentanaAdmin frame = new VentanaAdmin();
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
	public VentanaAdmin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelAdmin = new JPanel();
		panelAdmin.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelAdmin, BorderLayout.CENTER);
		panelAdmin.setLayout(null);

		btnEquipos = new JButton("Equipos");
		btnEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnEquipos();
			}
		});
		btnEquipos.setForeground(Color.BLACK);
		btnEquipos.setFont(new Font("Arial", Font.BOLD, 20));
		btnEquipos.setBackground(Color.WHITE);
		btnEquipos.setBounds(67, 180, 150, 77);
		panelAdmin.add(btnEquipos);

		btnJugadores = new JButton("Jugadores");
		btnJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnJugadores();
			}
		});
		btnJugadores.setFont(new Font("Arial", Font.BOLD, 20));
		btnJugadores.setBackground(Color.WHITE);
		btnJugadores.setBounds(282, 180, 150, 77);
		panelAdmin.add(btnJugadores);

		btnPartidos = new JButton("Partidos");
		btnPartidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnPartidos();
			}
		});
		btnPartidos.setFont(new Font("Arial", Font.BOLD, 20));
		btnPartidos.setBackground(Color.WHITE);
		btnPartidos.setBounds(67, 296, 150, 76);
		panelAdmin.add(btnPartidos);

		btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnUsuarios();
			}
		});
		btnUsuarios.setFont(new Font("Arial", Font.BOLD, 20));
		btnUsuarios.setBackground(Color.WHITE);
		btnUsuarios.setBounds(283, 297, 150, 77);
		panelAdmin.add(btnUsuarios);

		btnCSesion = new JButton("Cerrar Sesion");
		btnCSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnCerrar();
			}
		});
		btnCSesion.setForeground(Color.BLACK);
		btnCSesion.setFont(new Font("Arial", Font.BOLD, 12));
		btnCSesion.setBackground(Color.WHITE);
		btnCSesion.setBounds(481, 432, 113, 38);
		panelAdmin.add(btnCSesion);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaAdmin.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelAdmin.add(lblFondo);
	}

	// METODOS

	private void BtnEquipos() {

		BLL.AbrirVentanas.veGEquipos();

		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
	
	private void BtnJugadores() {

		BLL.AbrirVentanas.veGJugadores();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
	
	
	private void BtnPartidos() {

		BLL.AbrirVentanas.veGPartidos();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
	
	private void BtnUsuarios() {

		BLL.AbrirVentanas.veGUsuarios();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
	
	private void BtnCerrar() {

		BLL.AbrirVentanas.cerrar();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}

}