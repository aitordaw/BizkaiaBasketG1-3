package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import BLL.ConectorBLL;
import BLL.EquiposTableModel;
import BLL.UsuariosTableModel;
import DAL.Roles;
import DAL.MYSQL.Equipo;
import DAL.OBJECTDB.Usuario;

import javax.swing.SwingConstants;

public class VentanaGEquipos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelFondo;
	private JScrollPane scpGEquipos;
	private JTable tblGEquipos;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtMunicipio;
	private JTextField txtEmail;
	private JTextField txtTj;
	private JLabel lblCodigo;
	private JLabel lblEquipo;
	private JLabel lblEquipo_1;
	private JLabel lblGEquipos;
	private JLabel lblFecha;
	private JLabel lblResultado;
	private JLabel lblFondo;
	private JButton btnEliminar;
	private JButton btnCrear;
	private JButton btnModificar;
	private JButton btnVolver;
	private JLabel lblMensaje;

	/**
	 * Create the frame.
	 */
	public VentanaGEquipos() {
		setTitle("Gesti\u00F3n Equipos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelFondo = new JPanel();
		contentPane.add(panelFondo, BorderLayout.CENTER);
		panelFondo.setLayout(null);

		lblResultado = new JLabel("T. Juego");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setFont(new Font("Arial", Font.BOLD, 15));
		lblResultado.setForeground(SystemColor.controlLtHighlight);
		lblResultado.setBounds(751, 643, 107, 27);
		panelFondo.add(lblResultado);

		lblFecha = new JLabel("E-Mail");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Arial", Font.BOLD, 15));
		lblFecha.setForeground(SystemColor.controlLtHighlight);
		lblFecha.setBounds(553, 643, 107, 27);
		panelFondo.add(lblFecha);

		lblEquipo_1 = new JLabel("Municipio");
		lblEquipo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblEquipo_1.setForeground(SystemColor.controlLtHighlight);
		lblEquipo_1.setBounds(370, 642, 107, 28);
		panelFondo.add(lblEquipo_1);

		lblEquipo = new JLabel("Nombre");
		lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo.setForeground(SystemColor.controlLtHighlight);
		lblEquipo.setFont(new Font("Arial", Font.BOLD, 15));
		lblEquipo.setBounds(206, 642, 107, 28);
		panelFondo.add(lblEquipo);

		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Arial", Font.BOLD, 15));
		lblCodigo.setForeground(SystemColor.controlLtHighlight);
		lblCodigo.setBounds(71, 643, 107, 28);
		panelFondo.add(lblCodigo);

		lblGEquipos = new JLabel("Gesti\u00F3n de Equipos");
		lblGEquipos.setForeground(SystemColor.textHighlightText);
		lblGEquipos.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGEquipos.setBounds(71, 10, 220, 39);
		panelFondo.add(lblGEquipos);

		txtTj = new JTextField();
		txtTj.setBounds(701, 670, 206, 28);
		panelFondo.add(txtTj);
		txtTj.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(505, 670, 201, 28);
		panelFondo.add(txtEmail);
		txtEmail.setColumns(10);

		txtMunicipio = new JTextField();
		txtMunicipio.setBounds(346, 670, 162, 28);
		panelFondo.add(txtMunicipio);
		txtMunicipio.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(176, 670, 170, 28);
		panelFondo.add(txtNombre);
		txtNombre.setColumns(10);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(57, 670, 121, 28);
		panelFondo.add(txtCodigo);
		txtCodigo.setColumns(10);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnEliminar();
			}
		});
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(571, 713, 89, 39);
		panelFondo.add(btnEliminar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnModificar();
			}
		});
		btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setBounds(421, 713, 89, 39);
		panelFondo.add(btnModificar);

		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnCrear();
			}
		});
		btnCrear.setFont(new Font("Arial", Font.BOLD, 12));
		btnCrear.setBackground(Color.WHITE);
		btnCrear.setBounds(269, 713, 95, 39);
		panelFondo.add(btnCrear);

		scpGEquipos = new JScrollPane();
		scpGEquipos.setBounds(57, 120, 850, 480);
		panelFondo.add(scpGEquipos);

		tblGEquipos = new JTable();
		tblGEquipos.setRowSelectionAllowed(true);
		tblGEquipos.setShowHorizontalLines(false);
		tblGEquipos.setModel(new EquiposTableModel());
		tblGEquipos.getColumnModel().getColumn(0).setPreferredWidth(63);
		tblGEquipos.getColumnModel().getColumn(1).setPreferredWidth(103);
		tblGEquipos.getColumnModel().getColumn(2).setPreferredWidth(102);
		tblGEquipos.getColumnModel().getColumn(3).setPreferredWidth(134);
		tblGEquipos.getColumnModel().getColumn(4).setPreferredWidth(125);
		scpGEquipos.setViewportView(tblGEquipos);
		tblGEquipos.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblGEquipos.setBackground(new Color(233, 150, 122));
		tblGEquipos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					Equipo equipo = ((EquiposTableModel)tblGEquipos.getModel()).getElementoEn(tblGEquipos.getSelectedRow());
					
					if (equipo == null) // SI no hay fila seleccionada o no se encuentra el usuario
					{
						clearFields();
					} else {
						setFields(equipo);
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
		btnVolver.setIcon(new ImageIcon(VentanaGEquipos.class.getResource("/IMG/arrowleft.png")));
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

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaGEquipos.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelFondo.add(lblFondo);
	}

	protected void BtnModificar() {
		if (tblGEquipos.getSelectedRow() != -1 && validarFormulario()) {
			try {
				Equipo equipo = ((EquiposTableModel)tblGEquipos.getModel()).getElementoEn(tblGEquipos.getSelectedRow());
				
				ConectorBLL.ModificarEquipo(equipo.getCodigo(), txtCodigo.getText(), txtNombre.getText(), txtMunicipio.getText(), txtEmail.getText(), txtTj.getText());
				((EquiposTableModel)tblGEquipos.getModel()).Actualizar();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected void BtnCrear() {
		try {
			if (validarFormulario()) {
				ConectorBLL.CrearEquipo(txtCodigo.getText(), txtNombre.getText(), txtMunicipio.getText(), txtEmail.getText(), txtTj.getText());
				((EquiposTableModel)tblGEquipos.getModel()).Actualizar();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean validarFormulario() {
		lblMensaje.setText("");
		if(txtCodigo.getText().isEmpty()) {
			lblMensaje.setText("El codigo de equipo es obligatorio.");
		} else if (txtNombre.getText().isEmpty()) {
			lblMensaje.setText("El nombre del equipo es obligatorio.");
		} else if (txtMunicipio.getText().isEmpty()) {
			lblMensaje.setText("El municipio es obligatorio.");
		}else if (txtEmail.getText().isEmpty()) {
			lblMensaje.setText("La dirección de e-mail es obligatoria.");
		}else if (txtTj.getText().isEmpty()) {
			lblMensaje.setText("El terreno de juego es obligatorio.");
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
			Equipo equipo = ((EquiposTableModel)tblGEquipos.getModel()).getElementoEn(tblGEquipos.getSelectedRow());
			if (equipo != null) {
				ConectorBLL.BorrarEquipo(equipo);
				((EquiposTableModel)tblGEquipos.getModel()).Actualizar();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void setFields(Equipo equipo) {
		txtCodigo.setText(equipo.getCodigo());
		txtNombre.setText(equipo.getNombre());
		txtMunicipio.setText(equipo.getMunicipio());
		txtEmail.setText(equipo.getEmail());
		txtTj.setText(equipo.getTerreno());
	}

	private void clearFields() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtMunicipio.setText("");
		txtEmail.setText("");
		txtTj.setText("");
	}
}