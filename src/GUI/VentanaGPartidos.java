package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import BLL.ConectorBLL;
import DAL.Roles;

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
	private JTextField txtFecha;
	private JTextField txtResultado;
	private JLabel lblGPartidos;
	private JLabel lblCodigo;
	private JLabel lblEquipo1;
	private JLabel lblEquipo2;
	private JLabel lblResultado;
	private JLabel lblFondo;
	private JLabel lblFecha;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnCrear;
	private JButton btnVolver;
	private JComboBox comboLiga;
	private JComboBox comboEquipoVis;

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
		lblCodigo.setBounds(82, 642, 107, 28);
		panelFondo.add(lblCodigo);

		lblEquipo1 = new JLabel("Eq. Local");
		lblEquipo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo1.setForeground(Color.WHITE);
		lblEquipo1.setFont(new Font("Arial", Font.BOLD, 15));
		lblEquipo1.setBounds(254, 642, 106, 28);
		panelFondo.add(lblEquipo1);

		lblEquipo2 = new JLabel("Eq. Visitante");
		lblEquipo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo2.setForeground(Color.WHITE);
		lblEquipo2.setFont(new Font("Arial", Font.BOLD, 15));
		lblEquipo2.setBounds(421, 642, 107, 28);
		panelFondo.add(lblEquipo2);

		lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Arial", Font.BOLD, 15));
		lblFecha.setBounds(577, 643, 107, 27);
		panelFondo.add(lblFecha);

		lblResultado = new JLabel("Resultado");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setForeground(Color.WHITE);
		lblResultado.setFont(new Font("Arial", Font.BOLD, 15));
		lblResultado.setBounds(761, 643, 107, 27);
		panelFondo.add(lblResultado);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(56, 672, 166, 28);
		panelFondo.add(txtCodigo);

		txtEquipo1 = new JTextField();
		txtEquipo1.setColumns(10);
		txtEquipo1.setBounds(222, 672, 166, 28);
		panelFondo.add(txtEquipo1);

		txtEquipo2 = new JTextField();
		txtEquipo2.setColumns(10);
		txtEquipo2.setBounds(386, 672, 172, 28);
		panelFondo.add(txtEquipo2);

		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(558, 672, 166, 28);
		panelFondo.add(txtFecha);

		txtResultado = new JTextField();
		txtResultado.setColumns(10);
		txtResultado.setBounds(724, 672, 183, 28);
		panelFondo.add(txtResultado);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(597, 712, 89, 39);
		panelFondo.add(btnEliminar);

		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setBounds(451, 712, 89, 39);
		panelFondo.add(btnModificar);

		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Arial", Font.BOLD, 12));
		btnCrear.setBackground(Color.WHITE);
		btnCrear.setBounds(299, 711, 95, 39);
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

		scpGPartidos = new JScrollPane();
		scpGPartidos.setBounds(57, 120, 850, 480);
		panelFondo.add(scpGPartidos);

		tblGPartidos = new JTable();
		tblGPartidos.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "Codigo", "Equipo1", "Equipo2", "Fecha", "Resultado" }));
		tblGPartidos.setEnabled(false);
		tblGPartidos.setRowSelectionAllowed(false);
		tblGPartidos.setShowHorizontalLines(false);
		scpGPartidos.setViewportView(tblGPartidos);
		tblGPartidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblGPartidos.setBackground(new Color(233, 150, 122));
		
		comboLiga = new JComboBox();
		comboLiga.setToolTipText("Liga");
		comboLiga.setBounds(56, 611, 166, 28);
		panelFondo.add(comboLiga);
		
		JComboBox comboEquipoLocal = new JComboBox();
		comboEquipoLocal.setToolTipText("Liga");
		comboEquipoLocal.setBounds(222, 611, 166, 28);
		panelFondo.add(comboEquipoLocal);
		
		comboEquipoVis = new JComboBox();
		comboEquipoVis.setToolTipText("Liga");
		comboEquipoVis.setBounds(386, 611, 172, 28);
		panelFondo.add(comboEquipoVis);

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
}