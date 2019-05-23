package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import BLL.TemporadaTableModel;

import javax.swing.JScrollPane;

public class VentanaTemporada extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblClasificacion;
	private JPanel panelTemporada;
	private JButton btnVolver;
	private JLabel lblClayEst;
	private JLabel lblFondo;
	private JScrollPane scpVClasificacion;

	/**
	 * Create the frame.
	 */
	public VentanaTemporada() {
		setTitle("Ventana Temporada");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelTemporada = new JPanel();
		panelTemporada.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelTemporada, BorderLayout.CENTER);
		panelTemporada.setLayout(null);

		btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon(VentanaTemporada.class.getResource("/IMG/arrowleft.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnVolver();
			}
		});
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(0, 0, 48, 36);
		panelTemporada.add(btnVolver);

		lblClayEst = new JLabel("Clasificaci\u00F3n");
		lblClayEst.setHorizontalAlignment(SwingConstants.CENTER);
		lblClayEst.setForeground(Color.WHITE);
		lblClayEst.setFont(new Font("Arial", Font.BOLD, 20));
		lblClayEst.setBackground(Color.WHITE);
		lblClayEst.setBounds(71, 10, 145, 39);
		panelTemporada.add(lblClayEst);

		scpVClasificacion = new JScrollPane();
		scpVClasificacion.setBounds(57, 120, 850, 600);
		panelTemporada.add(scpVClasificacion);

		tblClasificacion = new JTable();
		tblClasificacion.setEnabled(false);
		scpVClasificacion.setViewportView(tblClasificacion);
		tblClasificacion.setModel(new TemporadaTableModel());
		tblClasificacion.getColumnModel().getColumn(0).setPreferredWidth(67);
		tblClasificacion.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblClasificacion.getColumnModel().getColumn(2).setPreferredWidth(105);
		tblClasificacion.getColumnModel().getColumn(3).setPreferredWidth(159);
		tblClasificacion.getColumnModel().getColumn(4).setPreferredWidth(162);
		tblClasificacion.setShowVerticalLines(true);
		tblClasificacion.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblClasificacion.setBackground(new Color(233, 150, 122));

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaTemporada.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelTemporada.add(lblFondo);
	}

	private void BtnVolver() {

		BLL.AbrirVentanas.vePObservador();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
}
