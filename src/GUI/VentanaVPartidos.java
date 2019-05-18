package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class VentanaVPartidos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblVPartidos;
	private JPanel panelVPartidos;
	private JButton btnVolver;
	private JLabel lblPartidos;
	private JScrollPane scpVPartidos;
	private JLabel lblFondo;

	/**
	 * Create the frame.
	 */
	public VentanaVPartidos() {
		setTitle("Ventana Vista Partidos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 840);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelVPartidos = new JPanel();
		contentPane.add(panelVPartidos, BorderLayout.CENTER);
		panelVPartidos.setLayout(null);

		btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon(VentanaVPartidos.class.getResource("/IMG/arrowleft.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnVolver();
			}
		});
		btnVolver.setFont(new Font("Arial", Font.BOLD, 20));
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBounds(0, 0, 48, 36);
		panelVPartidos.add(btnVolver);

		lblPartidos = new JLabel("Partidos");
		lblPartidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartidos.setForeground(Color.WHITE);
		lblPartidos.setFont(new Font("Arial", Font.BOLD, 20));
		lblPartidos.setBackground(Color.WHITE);
		lblPartidos.setBounds(21, 48, 375, 24);
		panelVPartidos.add(lblPartidos);

		scpVPartidos = new JScrollPane();
		scpVPartidos.setBounds(53, 99, 499, 332);
		panelVPartidos.add(scpVPartidos);

		tblVPartidos = new JTable();
		scpVPartidos.setViewportView(tblVPartidos);
		tblVPartidos.setRowSelectionAllowed(false);
		tblVPartidos.setShowGrid(false);
		tblVPartidos.setEnabled(false);
		tblVPartidos.setShowVerticalLines(true);
		tblVPartidos.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, },
				new String[] { "Codigo", "Equipo1", "Equipo2", "Fecha", "Resultado" }));
		tblVPartidos.getColumnModel().getColumn(1).setPreferredWidth(139);
		tblVPartidos.getColumnModel().getColumn(2).setPreferredWidth(130);
		tblVPartidos.getColumnModel().getColumn(3).setPreferredWidth(149);
		tblVPartidos.getColumnModel().getColumn(4).setPreferredWidth(83);
		tblVPartidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblVPartidos.setBackground(new Color(233, 150, 122));

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VentanaVPartidos.class.getResource("/IMG/Fondo-tr.png")));
		lblFondo.setBounds(0, 0, 974, 811);
		panelVPartidos.add(lblFondo);
	}

	private void BtnVolver() {
		BLL.AbrirVentanas.vePObservador();
		dispose(); // Elimina el objeto en memoria (cierra la ventana)

	}
}
