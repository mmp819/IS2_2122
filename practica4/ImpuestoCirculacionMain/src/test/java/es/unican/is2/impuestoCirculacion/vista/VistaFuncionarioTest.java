package es.unican.is2.impuestoCirculacion.vista;

import static org.junit.Assert.assertEquals;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.unican.is2.impuestoCirculacion.business.GestionImpuestoCirculacion;
import es.unican.is2.impuestoCirculacion.dao.ContribuyentesDAO;
import es.unican.is2.impuestoCirculacion.dao.VehiculosDAO;

/**
 * Test de integracion para comprobar el funcionamiento conjunto de la clase VistaFuncionario, con
 * el resto de capas de la aplicacion.
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public class VistaFuncionarioTest {

	private FrameFixture demo;
	
	// Componentes capa DAO
	ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
	VehiculosDAO vehiculosDAO = new VehiculosDAO();
			
	// Componentes capa negocio
	GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, 
			vehiculosDAO);
	
	/**
	 * Prepara interfaz para el test.
	 */
	@Before
	public void setUp() {
		VistaFuncionario gui = new VistaFuncionario(negocio, negocio, negocio);
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}
	
	/**
	 * Finaliza test, limpiando recursos de la interfaz.
	 */
	@After
	public void tearDown() {
		demo.cleanUp();
	}
	
	/**
	 * Comprueba que la apariencia de la interfaz es correcta.
	 */
	@Test
	public void testApariencia() {
		// Comprobar que la interfaz cumple con el aspecto deseado.
		demo.button("btnBuscar").requireText("Buscar");
		demo.button("btnBuscar").requireEnabled();
		
		demo.label("lblTotalContribuyente").requireText("Total A Pagar");
		
		demo.label("lblVehiculos").requireText("Vehiculos");
		
		demo.label("lblNombreContribuyente").requireText("Nombre");
		
		demo.label("lblDatosContribuyente").requireText("Datos Contribuyente");
		
		demo.label("lblDniContribuyente").requireText("DNI Contribuyente");
		
		demo.textBox("txtDniContribuyente").requireEnabled();
	}
	
	/**
	 * Comprueba que la funcionalidad de la interfaz y la integracion son correctas para 
	 * contribuyentes registrados con vehiculos.
	 */
	@Test
	public void testFuncionalidadRegistradoConVehiculos() {
		// Probar DNI registrado - 1
		demo.textBox("txtDniContribuyente").enterText("11111111A");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Pepe López Martínez");
		
		String listaMatriculas = "";
		for (String matricula:demo.list("listMatriculasVehiculos").contents()) {
			listaMatriculas += matricula ;
		}
		assertEquals("1111-AAA1111-BBB", listaMatriculas);
		
		demo.textBox("txtTotalContribuyente").requireText("448.0");
		
		demo.textBox("txtDniContribuyente").deleteText();
		
		// Probar DNI registrado - 2
		demo.textBox("txtDniContribuyente").enterText("22222222B");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Ana Pérez López");
				
		listaMatriculas = "";
		for (String matricula:demo.list("listMatriculasVehiculos").contents()) {
			listaMatriculas += matricula ;
		}
		assertEquals("2222-AAA2222-BBB", listaMatriculas);
				
		demo.textBox("txtTotalContribuyente").requireText("8.84");
		
		demo.textBox("txtDniContribuyente").deleteText();
		
		// Probar DNI registrado - 3
		demo.textBox("txtDniContribuyente").enterText("33333333C");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Luis Toca Pérez");
				
		listaMatriculas = "";
		for (String matricula:demo.list("listMatriculasVehiculos").contents()) {
			listaMatriculas += matricula ;
		}
		assertEquals("3333-AAA3333-BBB", listaMatriculas);
				
		demo.textBox("txtTotalContribuyente").requireText("249.24");
	}
	
	/**
	 * Comprueba que la funcionalidad de la interfaz y la integracion son correctas para 
	 * contribuyentes registrados sin vehiculos.
	 */
	@Test
	public void testFuncionalidadRegistradoSinVehiculos() {
		demo.textBox("txtDniContribuyente").enterText("44444444D");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Eugenia López Suárez");
				
		String listaMatriculas = "";
		for (String matricula:demo.list("listMatriculasVehiculos").contents()) {
			listaMatriculas += matricula ;
		}
		assertEquals("", listaMatriculas);
				
		demo.textBox("txtTotalContribuyente").requireText("0.0");
	}
	
	/**
	 * Comprueba que la funcionalidad de la interfaz y la integracion son correctas ante casos
	 * no validos.
	 */
	@Test
	public void testFuncionalidadNoValidos() {
		// DNI no registrado
		demo.textBox("txtDniContribuyente").enterText("55555555E");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("DNI No Válido");
				
		String listaMatriculas = "";
		for (String matricula:demo.list("listMatriculasVehiculos").contents()) {
			listaMatriculas += matricula ;
		}
		assertEquals("", listaMatriculas);
				
		demo.textBox("txtTotalContribuyente").requireText("0");
		
		demo.textBox("txtDniContribuyente").deleteText();
		
		// Campo de DNI en blanco.
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("DNI No Válido");
				
		listaMatriculas = "";
		for (String matricula:demo.list("listMatriculasVehiculos").contents()) {
			listaMatriculas += matricula ;
		}
		assertEquals("", listaMatriculas);
				
		demo.textBox("txtTotalContribuyente").requireText("0");
	}
}