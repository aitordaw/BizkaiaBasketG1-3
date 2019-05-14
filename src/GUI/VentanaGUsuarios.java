package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class VentanaGUsuarios extends JFrame {

	/**
	 * 
	 */
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

	// Definir la tabla
	DefaultTableModel tblgu = new DefaultTableModel();
	
	// Definir Arraylist
	ArrayList<GUsuarios> usuarios;
	
	// Definir Necesidades Previas
	int row;
	
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
	public VentanaGUsuarios() {
		//Array
		usuarios = new ArrayList<>();
		// Dise�o de la ventana
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
		panelFondo.setBounds(0, 0, 604, 481);
		contentPane.add(panelFondo);

		lblGUsuarios = new JLabel("Gesti\u00F3n de Usuarios");
		lblGUsuarios.setForeground(Color.WHITE);
		lblGUsuarios.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGUsuarios.setBounds(129, 44, 220, 39);
		panelFondo.add(lblGUsuarios);

		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 15));
		lblNombre.setBounds(216, 425, 188, 28);
		panelFondo.add(lblNombre);

		btnVolver = new JButton("");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnVolver();
			}
		});
		btnVolver.setIcon(new ImageIcon(VentanaGUsuarios.class.getResource("/IMG/arrowleft.png")));
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(0, 0, 48, 36);
		panelFondo.add(btnVolver);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(216, 453, 188, 28);
		panelFondo.add(txtUsuario);

		scpGUsuarios = new JScrollPane();
		scpGUsuarios.setEnabled(false);
		scpGUsuarios.setBounds(53, 99, 845, 257);
		panelFondo.add(scpGUsuarios);

		tblGUsuarios = new JTable();
		tblGUsuarios.setFont(new Font("Arial", Font.PLAIN, 12));
		tblGUsuarios.setShowHorizontalLines(false);
		scpGUsuarios.setViewportView(tblGUsuarios);
		tblGUsuarios.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblGUsuarios.setBackground(new Color(233, 150, 122));
		tblgu.addColumn("Usuario");
		tblgu.addColumn("Contrase�a");
		tblGUsuarios.setModel(tblgu);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaGUsuarios.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelFondo.add(lblFondo);
		
				txtPassword = new JTextField();
				txtPassword.setBounds(577, 453, 188, 28);
				contentPane.add(txtPassword);
				txtPassword.setColumns(10);
				
						btnEliminar = new JButton("Eliminar");
						btnEliminar.setBounds(628, 534, 89, 39);
						contentPane.add(btnEliminar);
						btnEliminar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								BtnEliminar();
							}
						});
						btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
						btnEliminar.setBackground(Color.WHITE);
						
								btnModificar = new JButton("Modificar");
								btnModificar.setBounds(454, 534, 89, 39);
								contentPane.add(btnModificar);
								btnModificar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
									}
								});
								btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
								btnModificar.setBackground(Color.WHITE);
								
										btnCrear = new JButton("Crear");
										btnCrear.setBounds(266, 534, 95, 39);
										contentPane.add(btnCrear);
										btnCrear.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent c) {
												BtnGUCrear();
											}
										});
										btnCrear.setFont(new Font("Arial", Font.BOLD, 12));
										btnCrear.setBackground(Color.WHITE);
										
												lblPassword = new JLabel("Contrase\u00F1a");
												lblPassword.setBounds(577, 431, 188, 27);
												contentPane.add(lblPassword);
												lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
												lblPassword.setForeground(Color.WHITE);
												lblPassword.setFont(new Font("Arial", Font.BOLD, 15));
		
	}

	
	private void BtnGUCrear() {
		// Necesidades previas
		String user = txtUsuario.getText();
		String password = txtPassword.getText();
		
		// A�adir datos al arraylist
		usuarios.add(new GUsuarios(user,password));
		
		// Poner el contador de filas a 0
		tblgu.setRowCount(0);
		
		// Insertar los datos
		for (int i =0; i < usuarios.size(); i++) {
			Object[] per = {usuarios.get(i).usuario, usuarios.get(i).password};
			tblgu.addRow(per);
		}
		// Limpiar campos
		clearField();
	}
	
	private void BtnEliminar() {
		// Recoger los datos de los campos a eliminar
		int eli = tblGUsuarios.getSelectedRow();
		if (eli >= 0) {
			tblgu.removeRow(eli);
		}
		else {
			JOptionPane.showMessageDialog(null,"No hay datos a eliminar");
		}
	}

	private void BtnVolver() {
		BLL.AbrirVentanas.vePAdmin();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)
	}
	
	private void clearField() {
		txtUsuario.setText("");
		txtPassword.setText("");
	}
	
}
