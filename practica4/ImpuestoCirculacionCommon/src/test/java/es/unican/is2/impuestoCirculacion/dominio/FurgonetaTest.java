package es.unican.is2.impuestoCirculacion.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import org.junit.Test;

import es.unican.is2.impuestoCirculacion.business.OperacionNoValida;

/**
 * Test para vehiculos de tipo Furgoneta.
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public class FurgonetaTest {
	
	Furgoneta sut;
	
	/**
	 * Comprueba que se retorna el precio correcto para diferentes furgonetas con diversos
	 * parametros.
	 */
	@Test
	public void testPrecioImpuesto() {
		
		// Comprueba el retorno del precio correcto para diferentes parametros.
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(1997, 6, 1), 0.1, true);
			assertEquals(sut.precioImpuesto(), 20.192, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(1980,  1, 1), 4.0, false);
			assertEquals(sut.precioImpuesto(), 0.0, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 20), 7.9, true);
			assertEquals(sut.precioImpuesto(), 20.192, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(1997, 6, 1), 8.0, false);
			assertEquals(sut.precioImpuesto(), 68.16, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(1997, 1, 1), 10.0, true);
			assertEquals(sut.precioImpuesto(), 0.0, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 20), 11.9, true);
			assertEquals(sut.precioImpuesto(), 54.528, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(1997, 6, 1), 12.0, true);
			assertEquals(sut.precioImpuesto(), 115.104, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 20), 14.0, false);
			assertEquals(sut.precioImpuesto(), 143.88, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(1997, 6, 1), 15.9, true);
			assertEquals(sut.precioImpuesto(), 115.104, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 20), 16.0, false);
			assertEquals(sut.precioImpuesto(), 179.22, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(1997, 6, 1), 18.0, true);
			assertEquals(sut.precioImpuesto(), 143.376, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 20), 19.9, false);
			assertEquals(sut.precioImpuesto(), 179.22, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(1997, 6, 1), 20.0, true);
			assertEquals(sut.precioImpuesto(), 179.2, 0.0001);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 20), 50.0, false);
			assertEquals(sut.precioImpuesto(), 224.0, 0.0001);
			
		} catch (OperacionNoValida e) {
			fail();
		}
	}
	
	/**
	 * Comprueba que el metodo constructor de la clase Furgoneta funciona correctamente.
	 */
	@Test
	public void testConstructorFurgoneta() {
		sut = null;
		
		try {
			// Caso valido 1 
			sut = new Furgoneta("1234-FNX", LocalDate.of(1990, 3, 1), 0.1, true);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(1990, 3, 1));
			assertEquals(sut.getPotencia(), 0.1, 0.0001);
			assertEquals(sut.getComercial(), true);
			
			// Caso valido 2
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 24), 50.0, false);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(2022, 3, 24));
			assertEquals(sut.getPotencia(), 50.0, 0.0001);
			assertEquals(sut.getComercial(), false);
			
			// Caso valido 3 - Caja blanca
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 24), 50);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(2022, 3, 24));
			assertEquals(sut.getPotencia(), 50, 0.0001);
			assertEquals(sut.getComercial(), false);
		} catch (OperacionNoValida e) {
			fail("Se ha producido una operación no válida, cuando debería ser correcta.");
		}
		
		// Caso no valido. No puede existir una furgoneta sin matricula.
		try {
			sut = new Furgoneta(null, LocalDate.of(2022, 3, 24), 0.1, true);
			fail("Se ha creado una furgoneta sin matrícula.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No puede existir una furgoneta con fecha de matriculacion posterior.
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 6, 1), 0.1, false);
			fail("Se ha creado una furgoneta con fecha de matriculación posterior.");
		} catch (OperacionNoValida e) {
		}
		// Con constructor por defecto. Caja blanca.
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 6, 1), 0.1);
			fail("Se ha creado una furgoneta con fecha de matriculación posterior.");
		} catch (OperacionNoValida e) {
		}
		
		
		// Caso no valido. No puede existir una furgoneta con fecha de matriculacion posterior.
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(2050, 1, 1), 50.0, true);
			fail("Se ha creado una furgoneta con fecha de matriculación posterior.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No puede existir una furgoneta sin fecha de matriculacion.
		try {
			sut = new Furgoneta("1234-FNX", null, 50.0, true);
			fail("Se ha creado una furgoneta sin fecha de matriculación.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No puede existir una furgoneta con potencia igual a cero.
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(1990, 3, 1), 0.0, false);
			fail("Se ha creado una furgoneta con potencia igual a cero.");
		} catch (OperacionNoValida e) {
		}
		// Con constructor por defecto. Caja blanca.
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(1990, 3, 1), 0.0);
			fail("Se ha creado una furgoneta con potencia igual a cero.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No puede existir una furgoneta con potencia negativa.
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 24), -100.0, true);
			fail("Se ha creado una furgoneta con potencia negativa.");
		} catch (OperacionNoValida e) {
		}
	}
	
	/**
	 * Comprueba el funcionamiento del metodo equals.
	 */
	@Test
	public void testEquals() {
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 1, 1), 100.0);
			Turismo sut2 = new Turismo("1234-FNX", LocalDate.of(2022, 1, 1), 100.0);
			assertTrue(sut.equals(sut2));
			
			sut2 = new Furgoneta("1234-FNZ", LocalDate.of(2022, 1, 1), 100.0);
			assertFalse(sut.equals(sut2));
		} catch (OperacionNoValida e) {
			fail("Se ha producido una operación no válida, cuando debería ser correcta.");
		}
	}
}
