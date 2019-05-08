package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class VentanaLogin extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pwdPassword;
	private JPanel panelLogin;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblFondo;
	private JLabel lblMensaje;
	protected static String cusuario;
	protected static String usuario;

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
	public VentanaLogin() {
		setBackground(Color.DARK_GRAY);
		setResizable(false);
		setTitle("�Bienvenido a la aplicaci\u00F3n BizkaiaBasket!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelLogin = new JPanel();
		panelLogin.setBackground(Color.LIGHT_GRAY);
		panelLogin.setBorder(null);
		contentPane.add(panelLogin, BorderLayout.CENTER);

		lblUsuario = new JLabel("USUARIO");
		lblUsuario.setForeground(Color.LIGHT_GRAY);
		lblUsuario.setBounds(312, 173, 138, 33);
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 30));

		lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(Color.LIGHT_GRAY);
		lblPassword.setBounds(506, 168, 183, 43);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 30));

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsuario.addActionListener(this);
		txtUsuario.setBounds(291, 211, 176, 43);
		txtUsuario.setColumns(10);

		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pwdPassword.addActionListener(this);
		pwdPassword.setBounds(506, 211, 176, 43);
		panelLogin.setLayout(null);
		panelLogin.add(lblPassword);
		panelLogin.add(lblUsuario);
		panelLogin.add(pwdPassword);
		panelLogin.add(txtUsuario);

		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Arial", Font.BOLD, 20));
		lblMensaje.setEnabled(false);
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setBackground(Color.GRAY);
		lblMensaje.setBounds(60, 421, 466, 33);
		panelLogin.add(lblMensaje);

		lblFondo = new JLabel("");
		lblFondo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFondo.setIcon(new ImageIcon(VentanaLogin.class.getResource("/IMG/Fondo.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelLogin.add(lblFondo);
	}

	@Override
	public void actionPerformed(ActionEvent o) {
		// TODO Auto-generated method stub
		usuario = txtUsuario.getText();
		String password = new String(pwdPassword.getPassword());
		String mensaje = "";

		if (usuario.equals("Admin") && password.equals("admin")) {
			// si los datos coinciden
			cusuario = "a";
			BLL.AbrirVentanas.vePAdmin();
			dispose(); // Elimina el objeto en memoria (cierra la ventana)
		}

		else if (usuario.equals("Usuario") && password.equals("usuario")) {
			// si los datos coinciden
			cusuario = "u";
			BLL.AbrirVentanas.vePUsuario();
			dispose(); // Elimina el objeto en memoria (cierra la ventana)
		}

		else if (usuario.equals("Observador") && password.equals("")) {
			// si los datos coinciden
			cusuario = "o";
			BLL.AbrirVentanas.vePObservador();
			dispose(); // Elimina el objeto en memoria (cierra la ventana)
		}

		else {
			// si los datos no coinciden
			mensaje = "Usuario y/o Contrase�a incorrectos.";
		}
		lblMensaje.setText(mensaje);
	}

	public static String getUsuario() {

		return usuario;
	}
}