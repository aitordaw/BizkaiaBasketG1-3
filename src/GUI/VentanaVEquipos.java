package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import BLL.ConectorBLL;
import DAL.Roles;

import javax.swing.JScrollPane;

public class VentanaVEquipos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelVEquipos;
	private JButton btnVolver;
	private JLabel lblVEquipos;
	private JTable tblVEquipos;
	private JLabel lblFondo;
	private JScrollPane scpVEquipos;

	/**
	 * Create the frame.
	 */
	public VentanaVEquipos() {
		setResizable(false);
		setTitle("Ventana Ver Equipos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelVEquipos = new JPanel();
		contentPane.add(panelVEquipos, BorderLayout.CENTER);
		panelVEquipos.setLayout(null);

		btnVolver = new JButton("");
		btnVolver.setSelectedIcon(new ImageIcon(VentanaVEquipos.class.getResource("/IMG/arrowleft.png")));
		btnVolver.setIcon(new ImageIcon(VentanaVEquipos.class.getResource("/IMG/arrowleft.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnVolver();
			}
		});
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(0, 0, 48, 36);
		panelVEquipos.add(btnVolver);

		lblVEquipos = new JLabel("Equipos");
		lblVEquipos.setHorizontalAlignment(SwingConstants.CENTER);
		lblVEquipos.setForeground(Color.WHITE);
		lblVEquipos.setFont(new Font("Arial", Font.BOLD, 20));
		lblVEquipos.setBackground(Color.WHITE);
		lblVEquipos.setBounds(71, 10, 220, 39);
		panelVEquipos.add(lblVEquipos);

		scpVEquipos = new JScrollPane();
		scpVEquipos.setBounds(57, 120, 850, 600);
		panelVEquipos.add(scpVEquipos);

		tblVEquipos = new JTable();
		scpVEquipos.setViewportView(tblVEquipos);
		tblVEquipos.setFillsViewportHeight(true);
		tblVEquipos.setShowGrid(false);
		tblVEquipos.setEnabled(false);
		tblVEquipos.setRowSelectionAllowed(false);
		tblVEquipos.setShowVerticalLines(true);
		tblVEquipos.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "Codigo", "Nombre", "Municipio", "E-mail", "Terreno Juego" }));
		tblVEquipos.getColumnModel().getColumn(0).setPreferredWidth(67);
		tblVEquipos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblVEquipos.getColumnModel().getColumn(2).setPreferredWidth(105);
		tblVEquipos.getColumnModel().getColumn(3).setPreferredWidth(159);
		tblVEquipos.getColumnModel().getColumn(4).setPreferredWidth(162);
		tblVEquipos.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblVEquipos.setBackground(new Color(233, 150, 122));

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaVEquipos.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelVEquipos.add(lblFondo);
	}

	private void BtnVolver() {

		if (ConectorBLL.GetRolActual() == Roles.OBSERVADOR) {
			BLL.AbrirVentanas.vePObservador();
			dispose(); // Elimina el objeto en memoria (cierra la ventana)
		} else if (ConectorBLL.GetRolActual() == Roles.USUARIO) {
			BLL.AbrirVentanas.vePUsuario();
			dispose(); // Elimina el objeto en memoria (cierra la ventana)
		}

	}
}
