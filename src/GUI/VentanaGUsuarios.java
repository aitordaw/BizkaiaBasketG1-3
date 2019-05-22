package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import BLL.ConectorBLL;
import BLL.UsuariosTableModel;
import DAL.Roles;
import DAL.OBJECTDB.Usuario;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class VentanaGUsuarios extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelFondo;
	private JTable tblGUsuarios;
	private JLabel lblGUsuarios;
	private JLabel lblPassword;
	private JLabel lblNombre;
	private JButton btnCrear;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JTextField txtUsuario;
	private JTextField txtPassword;
	private JLabel lblFondo;
	private JButton btnVolver;
	private JScrollPane scpGUsuarios;
	private JComboBox comboBox;
		
	// Definir Necesidades Previas
	int row;
	private JLabel lblMensaje;
	
	/**
	 * Create the frame.
	 */
	public VentanaGUsuarios() {
		//Array
//		usuarios = new ArrayList<>();
		// Diseño de la ventana
		setResizable(false);
		setTitle("Gesti\u00F3n Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelFondo = new JPanel();
		panelFondo.setLayout(null);
		panelFondo.setBounds(0, 0, 974, 811);
		contentPane.add(panelFondo);

		lblGUsuarios = new JLabel("Gesti\u00F3n de Usuarios");
		lblGUsuarios.setForeground(Color.WHITE);
		lblGUsuarios.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGUsuarios.setBounds(71, 10, 220, 39);
		panelFondo.add(lblGUsuarios);

		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 15));
		lblNombre.setBounds(330, 515, 188, 28);
		panelFondo.add(lblNombre);

		btnVolver = new JButton("");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnVolver();
			}
		});
		
		lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(330, 597, 188, 27);
		panelFondo.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 15));
		btnVolver.setIcon(new ImageIcon(VentanaGUsuarios.class.getResource("/IMG/arrowleft.png")));
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(0, 0, 48, 36);
		panelFondo.add(btnVolver);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(330, 556, 188, 28);
		panelFondo.add(txtUsuario);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(330, 637, 188, 28);
		panelFondo.add(txtPassword);
		txtPassword.setColumns(10);

		scpGUsuarios = new JScrollPane();
		scpGUsuarios.setEnabled(false);
		scpGUsuarios.setBounds(57, 112, 850, 395);
		panelFondo.add(scpGUsuarios);

		tblGUsuarios = new JTable();
		tblGUsuarios.setFont(new Font("Arial", Font.PLAIN, 12));
		tblGUsuarios.setShowHorizontalLines(false);
		scpGUsuarios.setViewportView(tblGUsuarios);
		tblGUsuarios.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblGUsuarios.setBackground(new Color(233, 150, 122));
		tblGUsuarios.setModel(new UsuariosTableModel());
		// Listener para comprobar si hay alguna fila seleccionada
		tblGUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					Usuario user = ((UsuariosTableModel)tblGUsuarios.getModel()).getElementoEn(tblGUsuarios.getSelectedRow());
					
					if (user == null) // SI no hay fila seleccionada o no se encuentra el usuario
					{
						clearFields();
					} else {
						setFields(user.getUsuario(), user.getPassword(), user.getRol());
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
								
		btnCrear = new JButton("Crear");
		btnCrear.setBounds(621, 539, 95, 39);
		panelFondo.add(btnCrear);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
					BtnGUCrear();
					}
			});
		btnCrear.setFont(new Font("Arial", Font.BOLD, 12));
		btnCrear.setBackground(Color.WHITE);
				
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(621, 732, 95, 39);
		panelFondo.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					BtnEliminar();
				}
			});
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.setBackground(Color.WHITE);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(621, 639, 95, 39);
		panelFondo.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						if (tblGUsuarios.getSelectedRow() != -1 && validarFormulario()) {
							try {
								Usuario user = ((UsuariosTableModel)tblGUsuarios.getModel()).getElementoEn(tblGUsuarios.getSelectedRow());
								
								ConectorBLL.ModificarUsuario(user.getUsuario(), txtUsuario.getText(), txtPassword.getText(), (Roles)comboBox.getSelectedItem());
								((UsuariosTableModel)tblGUsuarios.getModel()).Actualizar();
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
		btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
		btnModificar.setBackground(Color.WHITE);
				
				JLabel lblRol = new JLabel("Rol");
				lblRol.setHorizontalAlignment(SwingConstants.CENTER);
				lblRol.setForeground(Color.WHITE);
				lblRol.setFont(new Font("Arial", Font.BOLD, 15));
				lblRol.setBounds(330, 678, 188, 27);
				panelFondo.add(lblRol);
				
				comboBox = new JComboBox(Roles.values());
				comboBox.setSelectedItem(null);
				comboBox.setBounds(330, 718, 188, 28);
				panelFondo.add(comboBox);
				
				lblMensaje = new JLabel("");
				lblMensaje.setForeground(Color.WHITE);
				lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblMensaje.setBounds(57, 759, 461, 39);
				panelFondo.add(lblMensaje);
		
				lblFondo = new JLabel("");
				lblFondo.setIcon(new ImageIcon(VentanaGUsuarios.class.getResource("/IMG/Fondo-tr.png")));
				lblFondo.setBounds(0, 0, 974, 811);
				panelFondo.add(lblFondo);
		
	}

	
	private void BtnGUCrear() {
	try {
		if (validarFormulario()) {
			ConectorBLL.CrearUsuario(txtUsuario.getText(), txtPassword.getText(), (Roles)comboBox.getSelectedItem());
			((UsuariosTableModel)tblGUsuarios.getModel()).Actualizar();
		}
	} catch (Exception ex) {
		JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}
	
	private void BtnEliminar() {
		try {
			Usuario usuario = ((UsuariosTableModel)tblGUsuarios.getModel()).getElementoEn(tblGUsuarios.getSelectedRow());
			if (usuario != null) {
				ConectorBLL.BorrarUsuario(usuario);
				((UsuariosTableModel)tblGUsuarios.getModel()).Actualizar();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void BtnVolver() {
		BLL.AbrirVentanas.vePAdmin();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)
	}
	
	private void clearFields() {
		txtUsuario.setText(""); // ELiminamos el contenido de la entrada de usuario
		txtPassword.setText("");
		comboBox.setSelectedItem(null);
	}
	
	private void setFields(String usuario, String password, Roles rol) {
		txtUsuario.setText(usuario); // ELiminamos el contenido de la entrada de usuario
		txtPassword.setText(password);
		comboBox.setSelectedItem(rol);
	}
	
	private boolean validarFormulario() {
		lblMensaje.setText("");
		if(txtUsuario.getText().isEmpty()) {
			lblMensaje.setText("El nombre de usuario es obligatorio.");
		} else if (comboBox.getSelectedItem() == null) {
			lblMensaje.setText("El rol de usuario es obligatorio.");
		} else if (comboBox.getSelectedItem() != Roles.OBSERVADOR && txtPassword.getText().isEmpty()) {
			lblMensaje.setText("La contraseña es obligatoria.");
		}
		else {
			return true;
		}
		
		return false;
	}
}
