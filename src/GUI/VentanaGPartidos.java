package GUI;

import java.awt.BorderLayout;
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
import BLL.EquiposTableModel;
import BLL.JugadoresTableModel;
import BLL.PartidosTableModel;
import DAL.Roles;
import DAL.MYSQL.Equipo;
import DAL.MYSQL.Partido;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class VentanaGPartidos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelFondo;
	private JTable tblGPartidos;
	private JScrollPane scpGPartidos;
	private JTextField txtCodigo;
	private JTextField txtEquipo1;
	private JTextField txtEquipo2;
	private JTextField txtPtosLoc;
	private JTextField txtPuntosVis;
	private JLabel lblGPartidos;
	private JLabel lblCodigo;
	private JLabel lblEquipo1;
	private JLabel lblEquipo2;
	private JLabel lblTemporada;
	private JLabel lblFondo;
	private JLabel lblFecha;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnCrear;
	private JButton btnVolver;
	private JLabel lblMensaje;
	private JTextField txtFaltasLoc;
	private JTextField txtFaltasVis;
	private JTextField txtTemporada;
	private JTextField txtFecha;

	/**
	 * Create the frame.
	 */
	public VentanaGPartidos() {
		setTitle("Gesti\u00F3n Partidos");
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

		lblGPartidos = new JLabel("Gesti\u00F3n de Partidos");
		lblGPartidos.setForeground(Color.WHITE);
		lblGPartidos.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGPartidos.setBounds(71, 10, 220, 39);
		panelFondo.add(lblGPartidos);

		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setFont(new Font("Arial", Font.BOLD, 15));
		lblCodigo.setBounds(83, 574, 107, 28);
		panelFondo.add(lblCodigo);

		lblEquipo1 = new JLabel("Eq. Local");
		lblEquipo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo1.setForeground(Color.WHITE);
		lblEquipo1.setFont(new Font("Arial", Font.BOLD, 15));
		lblEquipo1.setBounds(255, 574, 106, 28);
		panelFondo.add(lblEquipo1);

		lblEquipo2 = new JLabel("Eq. Visitante");
		lblEquipo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo2.setForeground(Color.WHITE);
		lblEquipo2.setFont(new Font("Arial", Font.BOLD, 15));
		lblEquipo2.setBounds(422, 574, 107, 28);
		panelFondo.add(lblEquipo2);
		
		JLabel lblPuntosLoc = new JLabel("Faltas Local");
		lblPuntosLoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntosLoc.setForeground(Color.WHITE);
		lblPuntosLoc.setFont(new Font("Arial", Font.BOLD, 15));
		lblPuntosLoc.setBounds(593, 574, 106, 28);
		panelFondo.add(lblPuntosLoc);
		
		JLabel lblPuntosVis = new JLabel("Faltas Visitante");
		lblPuntosVis.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntosVis.setForeground(Color.WHITE);
		lblPuntosVis.setFont(new Font("Arial", Font.BOLD, 15));
		lblPuntosVis.setBounds(756, 574, 129, 28);
		panelFondo.add(lblPuntosVis);
		
		JLabel lblFaltasLoc = new JLabel("Faltas Local");
		lblFaltasLoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblFaltasLoc.setForeground(Color.WHITE);
		lblFaltasLoc.setFont(new Font("Arial", Font.BOLD, 15));
		lblFaltasLoc.setBounds(255, 647, 106, 28);
		panelFondo.add(lblFaltasLoc);
		
		JLabel lblFaltasVis = new JLabel("Faltas Visitante");
		lblFaltasVis.setHorizontalAlignment(SwingConstants.CENTER);
		lblFaltasVis.setForeground(Color.WHITE);
		lblFaltasVis.setFont(new Font("Arial", Font.BOLD, 15));
		lblFaltasVis.setBounds(411, 648, 129, 28);
		panelFondo.add(lblFaltasVis);

		lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Arial", Font.BOLD, 15));
		lblFecha.setBounds(764, 648, 107, 27);
		panelFondo.add(lblFecha);

		lblTemporada = new JLabel("Temporada");
		lblTemporada.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemporada.setForeground(Color.WHITE);
		lblTemporada.setFont(new Font("Arial", Font.BOLD, 15));
		lblTemporada.setBounds(592, 648, 107, 27);
		panelFondo.add(lblTemporada);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(57, 604, 166, 28);
		panelFondo.add(txtCodigo);

		txtEquipo1 = new JTextField(); 
		txtEquipo1.setColumns(10);
		txtEquipo1.setBounds(223, 604, 166, 28);
		panelFondo.add(txtEquipo1);

		txtEquipo2 = new JTextField();
		txtEquipo2.setColumns(10);
		txtEquipo2.setBounds(387, 604, 172, 28);
		panelFondo.add(txtEquipo2);

		txtPtosLoc = new JTextField();
		txtPtosLoc.setColumns(10);
		txtPtosLoc.setBounds(559, 604, 166, 28);
		panelFondo.add(txtPtosLoc);

		txtPuntosVis = new JTextField();
		txtPuntosVis.setColumns(10);
		txtPuntosVis.setBounds(725, 604, 183, 28);
		panelFondo.add(txtPuntosVis);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnEliminar();
			}
		});
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(593, 745, 89, 39);
		panelFondo.add(btnEliminar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnModificar();
			}
		});
		btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setBounds(451, 745, 89, 39);
		panelFondo.add(btnModificar);

		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnCrear();
			}
		});
		btnCrear.setFont(new Font("Arial", Font.BOLD, 12));
		btnCrear.setBackground(Color.WHITE);
		btnCrear.setBounds(296, 745, 95, 39);
		panelFondo.add(btnCrear);

		btnVolver = new JButton("");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnVolver();
			}
		});
		btnVolver.setIcon(new ImageIcon(VentanaGPartidos.class.getResource("/IMG/arrowleft.png")));
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

		scpGPartidos = new JScrollPane();
		scpGPartidos.setBounds(57, 120, 850, 437);
		panelFondo.add(scpGPartidos);

		tblGPartidos = new JTable();
		tblGPartidos.setModel(new PartidosTableModel());
		tblGPartidos.setEnabled(false);
		tblGPartidos.setRowSelectionAllowed(false);
		tblGPartidos.setShowHorizontalLines(false);
		scpGPartidos.setViewportView(tblGPartidos);
		tblGPartidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblGPartidos.setBackground(new Color(233, 150, 122));
		tblGPartidos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					Partido partido = ((PartidosTableModel)tblGPartidos.getModel()).getElementoEn(tblGPartidos.getSelectedRow());
					
					if (partido == null) // SI no hay fila seleccionada o no se encuentra el usuario
					{
						clearFields();
					} else {
						setFields(partido);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});

		txtFaltasLoc = new JTextField();  
		txtFaltasLoc.setColumns(10);
		txtFaltasLoc.setBounds(223, 682, 166, 28);
		panelFondo.add(txtFaltasLoc);
		
		txtFaltasVis = new JTextField();
		txtFaltasVis.setColumns(10);
		txtFaltasVis.setBounds(387, 682, 172, 28);
		panelFondo.add(txtFaltasVis);
		
		txtTemporada = new JTextField();
		txtTemporada.setColumns(10);
		txtTemporada.setBounds(559, 682, 166, 28);
		panelFondo.add(txtTemporada);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(725, 682, 183, 28);
		panelFondo.add(txtFecha);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaGPartidos.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelFondo.add(lblFondo);
	}

	private void BtnVolver() {

		if (ConectorBLL.GetRolActual() == Roles.ADMINISTRADOR) {
			BLL.AbrirVentanas.vePAdmin();
			dispose(); // Elimina el objeto en memoria (cierra la ventana)
		} else if (ConectorBLL.GetRolActual() == Roles.USUARIO) {
			BLL.AbrirVentanas.vePUsuario();
			dispose(); // Elimina el objeto en memoria (cierra la ventana)
		}
	}
	
	private void BtnEliminar() {
		try {
			Partido equipo = ((PartidosTableModel)tblGPartidos.getModel()).getElementoEn(tblGPartidos.getSelectedRow());
			if (equipo != null) {
				ConectorBLL.BorrarPartido(equipo);
				((PartidosTableModel)tblGPartidos.getModel()).Actualizar();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void BtnModificar() {
		if (tblGPartidos.getSelectedRow() != -1 && validarFormulario()) {
			try {
				Partido partido = ((PartidosTableModel)tblGPartidos.getModel()).getElementoEn(tblGPartidos.getSelectedRow());
				
				ConectorBLL.ModificarPartido(partido.getCodigo(), txtCodigo.getText(), txtEquipo1.getText(), txtEquipo2.getText(), Integer.parseInt(txtPtosLoc.getText()), 
						Integer.parseInt(txtPuntosVis.getText()), Integer.parseInt(txtFaltasLoc.getText()), Integer.parseInt(txtFaltasVis.getText()), txtTemporada.getText(), 
							txtFecha.getText());
				((PartidosTableModel)tblGPartidos.getModel()).Actualizar();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected void BtnCrear() {
		try {
			if (validarFormulario()) {
				ConectorBLL.CrearPartido(txtCodigo.getText(), txtEquipo1.getText(), txtEquipo2.getText(), Integer.parseInt(txtPtosLoc.getText()), 
						Integer.parseInt(txtPuntosVis.getText()), Integer.parseInt(txtFaltasLoc.getText()), Integer.parseInt(txtFaltasVis.getText()), 
							txtTemporada.getText(), txtFecha.getText());
				((PartidosTableModel)tblGPartidos.getModel()).Actualizar();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean validarFormulario() {
		lblMensaje.setText("");
		if(txtCodigo.getText().isEmpty()) {
			lblMensaje.setText("El codigo de partido es obligatorio.");
		} else if (txtEquipo1.getText().isEmpty()) {
			lblMensaje.setText("El nombre del equipo local es obligatorio.");
		} else if (txtEquipo2.getText().isEmpty()) {
			lblMensaje.setText("El nombre del equipo visitante es obligatorio.");
		}else if (txtPtosLoc.getText().isEmpty() || txtPuntosVis.getText().isEmpty()) {
			lblMensaje.setText("Los puntos son obligatorios.");	
		}else if (txtTemporada.getText().isEmpty()) {
			lblMensaje.setText("La temporada es obligatoria.");
		}else if (txtFecha.getText().isEmpty()) {
			lblMensaje.setText("La fecha es obligatoria.");
		}
		else {
			return true;
		}
		
		return false;
	}
	
	private void setFields(Partido partido) {
		txtCodigo.setText(partido.getCodigo());
		txtEquipo1.setText(partido.getEqLocal());
		txtEquipo2.setText(partido.getEqVisitante());
		txtPtosLoc.setText(String.valueOf(partido.getPtosLocal()));
		txtPuntosVis.setText(String.valueOf(partido.getPtosVisitante()));
		txtFaltasLoc.setText(String.valueOf(partido.getFaltLocal()));
		txtFaltasVis.setText(String.valueOf(partido.getFaltVisitante()));
		txtTemporada.setText(String.valueOf(partido.getCod_liga()));
		txtFecha.setText(String.valueOf(partido.getFecha()));
		
		        
	}

	private void clearFields() {
		
		txtCodigo.setText("");
		txtEquipo1.setText("");
		txtEquipo2.setText("");
		txtPtosLoc.setText("");
		txtPuntosVis.setText("");
		txtFaltasLoc.setText("");
		txtFaltasVis.setText("");
		txtTemporada.setText("");
		txtFecha.setText("");
	}
}