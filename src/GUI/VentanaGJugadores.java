package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BLL.ConectorBLL;
import BLL.EquiposTableModel;
import BLL.JugadoresTableModel;
import DAL.MYSQL.Equipo;
import DAL.MYSQL.Jugador;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class VentanaGJugadores extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelFondo;
	private JTable tblGJugadores;
	private JScrollPane scpGJugadores;
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEquipo;
	private JLabel lblEquipo;
	private JLabel lblApellidos;
	private JLabel lblNombre;
	private JLabel lblDni;
	private JLabel lblGJugadores;
	private JLabel lblFondo;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnCrear;
	private JButton btnVolver;
	private JComboBox comboEquipo;
	private JLabel lblMensaje;

	/**
	 * Create the frame.
	 */
	public VentanaGJugadores() {
		setTitle("Gesti\u00F3n Jugadores");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelFondo = new JPanel();
		panelFondo.setLayout(null);
		contentPane.add(panelFondo, BorderLayout.CENTER);

		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(87, 637, 150, 28);
		panelFondo.add(txtDNI);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(297, 637, 150, 28);
		panelFondo.add(txtNombre);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(517, 637, 150, 28);
		panelFondo.add(txtApellidos);

		txtEquipo = new JTextField();
		txtEquipo.setColumns(10);
		txtEquipo.setBounds(722, 637, 150, 28);
		panelFondo.add(txtEquipo);

		lblEquipo = new JLabel("Equipo");
		lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo.setForeground(Color.WHITE);
		lblEquipo.setFont(new Font("Arial", Font.BOLD, 15));
		lblEquipo.setBounds(744, 611, 107, 27);
		panelFondo.add(lblEquipo);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Arial", Font.BOLD, 15));
		lblApellidos.setBounds(530, 610, 110, 28);
		panelFondo.add(lblApellidos);

		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 15));
		lblNombre.setBounds(318, 610, 107, 28);
		panelFondo.add(lblNombre);

		lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setForeground(Color.WHITE);
		lblDni.setFont(new Font("Arial", Font.BOLD, 15));
		lblDni.setBounds(108, 610, 107, 28);
		panelFondo.add(lblDni);

		lblGJugadores = new JLabel("Gesti\u00F3n de Jugadores");
		lblGJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblGJugadores.setForeground(Color.WHITE);
		lblGJugadores.setFont(new Font("Arial", Font.BOLD, 20));
		lblGJugadores.setBounds(71, 10, 220, 39);
		panelFondo.add(lblGJugadores);

		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnCrear();
			}
		});
		btnCrear.setFont(new Font("Arial", Font.BOLD, 12));
		btnCrear.setBackground(Color.WHITE);
		btnCrear.setBounds(222, 698, 95, 39);
		panelFondo.add(btnCrear);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnModificar();
			}
		});
		btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setBounds(438, 698, 89, 39);
		panelFondo.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnEliminar();
			}
		});
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(658, 698, 89, 39);
		panelFondo.add(btnEliminar);

		scpGJugadores = new JScrollPane();
		scpGJugadores.setBounds(57, 120, 850, 480);
		panelFondo.add(scpGJugadores);

		tblGJugadores = new JTable();
		tblGJugadores.setRowSelectionAllowed(true);
		scpGJugadores.setViewportView(tblGJugadores);
		tblGJugadores.setShowHorizontalLines(false);
		tblGJugadores.setModel(new JugadoresTableModel());
		tblGJugadores.getColumnModel().getColumn(0).setPreferredWidth(104);
		tblGJugadores.getColumnModel().getColumn(1).setPreferredWidth(103);
		tblGJugadores.getColumnModel().getColumn(2).setPreferredWidth(110);
		tblGJugadores.getColumnModel().getColumn(3).setPreferredWidth(94);
		tblGJugadores.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblGJugadores.setBackground(new Color(233, 150, 122));
		tblGJugadores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					Jugador jugador = ((JugadoresTableModel)tblGJugadores.getModel()).getElementoEn(tblGJugadores.getSelectedRow());
					
					if (jugador == null) // SI no hay fila seleccionada o no se encuentra el usuario
					{
						clearFields();
					} else {
						setFields(jugador);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});


		btnVolver = new JButton("");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnVolver();
			}
		});
		btnVolver.setIcon(new ImageIcon(VentanaGJugadores.class.getResource("/IMG/arrowleft.png")));
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(0, 0, 48, 36);
		panelFondo.add(btnVolver);
		
		lblMensaje = new JLabel("");
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setForeground(Color.WHITE);
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMensaje.setBounds(57, 759, 850, 39);
		panelFondo.add(lblMensaje);

		
		comboEquipo = new JComboBox();
		comboEquipo.setBounds(804, 698, 166, 28);
		panelFondo.add(comboEquipo);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaGJugadores.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelFondo.add(lblFondo);
	}
	
	protected void BtnModificar() {
		if (tblGJugadores.getSelectedRow() != -1 && validarFormulario()) {
			try {
				Jugador jugador = ((JugadoresTableModel)tblGJugadores.getModel()).getElementoEn(tblGJugadores.getSelectedRow());
				
				ConectorBLL.ModificarJugador(jugador.getDni(), txtDNI.getText(), txtNombre.getText(), txtApellidos.getText(), txtEquipo.getText());
				((JugadoresTableModel)tblGJugadores.getModel()).Actualizar();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected void BtnCrear() {
		try {
			if (validarFormulario()) {
				ConectorBLL.CrearJugador(txtDNI.getText(), txtNombre.getText(), txtApellidos.getText(), txtEquipo.getText());
				((JugadoresTableModel)tblGJugadores.getModel()).Actualizar();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean validarFormulario() {
		lblMensaje.setText("");
		if(txtDNI.getText().isEmpty()) {
			lblMensaje.setText("El DNI del jugador es obligatorio.");
		} else if (txtNombre.getText().isEmpty()) {
			lblMensaje.setText("El nombre del Jugador es obligatorio.");
		} else if (txtApellidos.getText().isEmpty()) {
			lblMensaje.setText("Los apellidos del jugador son obligatorios.");
		}else if (txtEquipo.getText().isEmpty()) {
			lblMensaje.setText("El equipo del jugador es obligatoria.");
		}
		else {
			return true;
		}
		
		return false;
	}

	private void BtnVolver() {

		BLL.AbrirVentanas.vePAdmin();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
	
	private void BtnEliminar() {
		try {
			Jugador jugador = ((JugadoresTableModel)tblGJugadores.getModel()).getElementoEn(tblGJugadores.getSelectedRow());
			if (jugador != null) {
				ConectorBLL.BorrarJugador(jugador);
				((JugadoresTableModel)tblGJugadores.getModel()).Actualizar();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void setFields(Jugador jugador) {
		txtDNI.setText(jugador.getDni());
		txtNombre.setText(jugador.getNombre());
		txtApellidos.setText(jugador.getApellidos());
		txtEquipo.setText(jugador.getCod_equipo());
	}

	private void clearFields() {
		txtDNI.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		txtEquipo.setText("");
	}
}
