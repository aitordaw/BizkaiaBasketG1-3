package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;


import BLL.ConectorBLL;
import BLL.JugadoresTableModel;
import DAL.Roles;


import javax.swing.JScrollPane;

public class VentanaVJugadores extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVolver;
	private JLabel lblJugadores;
	private JPanel panelVJugadores;
	private JTable tblVJugadores;
	private JLabel lblFondo;
	private JScrollPane scpVJugadores;

	/**
	 * Create the frame.
	 */
	public VentanaVJugadores() {
		setResizable(false);
		setTitle("Ventana Vista Jugadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelVJugadores = new JPanel();
		contentPane.add(panelVJugadores, BorderLayout.CENTER);
		panelVJugadores.setLayout(null);

		btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon(VentanaVJugadores.class.getResource("/IMG/arrowleft.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnVolver();
			}
		});
		btnVolver.setBounds(0, 0, 48, 36);
		panelVJugadores.add(btnVolver);
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));

		lblJugadores = new JLabel("Jugadores");
		lblJugadores.setForeground(Color.WHITE);
		lblJugadores.setBackground(Color.WHITE);
		lblJugadores.setBounds(71, 10, 220, 39);
		panelVJugadores.add(lblJugadores);
		lblJugadores.setFont(new Font("Arial", Font.BOLD, 20));
		lblJugadores.setHorizontalAlignment(SwingConstants.CENTER);

		scpVJugadores = new JScrollPane();
		scpVJugadores.setBounds(57, 120, 850, 600);
		panelVJugadores.add(scpVJugadores);

		tblVJugadores = new JTable();
		scpVJugadores.setViewportView(tblVJugadores);
		tblVJugadores.setShowGrid(false);
		tblVJugadores.setEnabled(false);
		tblVJugadores.setRowSelectionAllowed(false);
		tblVJugadores.setToolTipText("");
		tblVJugadores.setShowVerticalLines(true);
		tblVJugadores.setModel(new JugadoresTableModel());
		tblVJugadores.getColumnModel().getColumn(0).setPreferredWidth(103);
		tblVJugadores.getColumnModel().getColumn(1).setPreferredWidth(115);
		tblVJugadores.getColumnModel().getColumn(2).setPreferredWidth(111);
		tblVJugadores.getColumnModel().getColumn(3).setPreferredWidth(98);
		tblVJugadores.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblVJugadores.setBackground(new Color(233, 150, 122));


		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaVJugadores.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelVJugadores.add(lblFondo);
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
