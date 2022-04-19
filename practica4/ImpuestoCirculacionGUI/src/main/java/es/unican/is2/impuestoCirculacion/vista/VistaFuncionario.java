package es.unican.is2.impuestoCirculacion.vista;

// Imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JButton;

import es.unican.is2.impuestoCirculacion.business.IGestionContribuyentes;
import es.unican.is2.impuestoCirculacion.business.IGestionVehiculos;
import es.unican.is2.impuestoCirculacion.business.IInfoImpuestoCirculacion;

import es.unican.is2.impuestoCirculacion.dominio.Vehiculo;
import es.unican.is2.impuestoCirculacion.dominio.Contribuyente;

/**
 * Clase que implementa la vista del funcionario dentro de la capa de
 * presentacion de la aplicacion usando Swing
 */
@SuppressWarnings("serial")
public class VistaFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtDniContribuyente;
	private JTextField txtTotalContribuyente;
	private JTextField txtNombreContribuyente;
	private JList<String> listMatriculasVehiculos;
	private DefaultListModel<String> listModel;
	private JButton btnBuscar;

	@SuppressWarnings("unused")
	private IGestionContribuyentes contribuyentes;
	@SuppressWarnings("unused")
	private IGestionVehiculos vehiculos;
	private IInfoImpuestoCirculacion info;

	/**
	 * Create the frame.
	 */
	public VistaFuncionario(IGestionContribuyentes contribuyentes, IGestionVehiculos vehiculos,
			IInfoImpuestoCirculacion info) {

		this.contribuyentes = contribuyentes;
		this.vehiculos = vehiculos;
		this.info = info;
		init();
	}

	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		listModel = new DefaultListModel<String>();

		txtTotalContribuyente = new JTextField();
		txtTotalContribuyente.setBounds(230, 251, 86, 20);
		contentPane.add(txtTotalContribuyente);
		txtTotalContribuyente.setColumns(10);
		txtTotalContribuyente.setName("txtTotalContribuyente");

		JLabel lblTotalContribuyente = new JLabel("Total A Pagar");
		lblTotalContribuyente.setBounds(137, 254, 99, 14);
		contentPane.add(lblTotalContribuyente);
		lblTotalContribuyente.setName("lblTotalContribuyente");

		listMatriculasVehiculos = new JList<String>();
		listMatriculasVehiculos.setBounds(230, 98, 121, 116);
		contentPane.add(listMatriculasVehiculos);
		listMatriculasVehiculos.setBorder(new LineBorder(new Color(0, 0, 0)));
		listMatriculasVehiculos.setModel(listModel);
		listMatriculasVehiculos.setName("listMatriculasVehiculos");

		JLabel lblVehiculos = new JLabel("Vehiculos");
		lblVehiculos.setBounds(149, 93, 65, 14);
		contentPane.add(lblVehiculos);
		lblVehiculos.setName("lblVehiculos");

		JLabel lblNombreContribuyente = new JLabel("Nombre");
		lblNombreContribuyente.setBounds(155, 54, 65, 14);
		contentPane.add(lblNombreContribuyente);
		lblNombreContribuyente.setName("lblNombreContribuyente");

		txtNombreContribuyente = new JTextField();
		txtNombreContribuyente.setBounds(230, 51, 121, 20);
		contentPane.add(txtNombreContribuyente);
		txtNombreContribuyente.setColumns(10);
		txtNombreContribuyente.setName("txtNombreContribuyente");

		JLabel lblDatosContribuyente = new JLabel("Datos Contribuyente");
		lblDatosContribuyente.setBounds(230, 11, 149, 14);
		contentPane.add(lblDatosContribuyente);
		lblDatosContribuyente.setName("lblDatosContribuyente");

		txtDniContribuyente = new JTextField();
		txtDniContribuyente.setBounds(10, 51, 113, 20);
		contentPane.add(txtDniContribuyente);
		txtDniContribuyente.setColumns(10);
		txtDniContribuyente.setName("txtDniContribuyente");

		JLabel lblDniContribuyente = new JLabel("DNI Contribuyente");
		lblDniContribuyente.setBounds(21, 27, 139, 14);
		contentPane.add(lblDniContribuyente);
		lblDniContribuyente.setName("lblDniContribuyente");

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rellenaDatosContribuyente(txtDniContribuyente.getText());
			}
		});
		btnBuscar.setBounds(21, 122, 89, 23);
		contentPane.add(btnBuscar);
		btnBuscar.setName("btnBuscar");
		listMatriculasVehiculos.setVisible(true);
		this.setVisible(true);
	}

	/**
	 * Rellena la ventana con los datos pertenecientes al contribuyente cuyo DNI se pasa
	 * como parametro.
	 * @param dni DNI del contribuyente a buscar.
	 */
	private void rellenaDatosContribuyente(String dni) {
		Contribuyente c = info.contribuyente(dni);
		if (c != null) {
			txtNombreContribuyente.setText(c.getNombre() + " " + c.getApellido1() + " " + c.getApellido2());
			txtTotalContribuyente.setText(String.valueOf(c.totalAPagar()));
			listModel.removeAllElements();
			for (int i = 0; i < c.getVehiculos().size(); i++) {
				Vehiculo v = c.getVehiculos().get(i);
				listModel.addElement(v.getMatricula());
			}
		} else {
			txtNombreContribuyente.setText("DNI No Válido");
			txtTotalContribuyente.setText("0");
			listModel.removeAllElements();
		}

	}
}
