package es.unican.is2.impuestoCirculacion.dominio;

// Imports
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import org.junit.Test;

import es.unican.is2.impuestoCirculacion.business.OperacionNoValida;

/**
 * Test para vehiculos de tipo Turismo.
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public class TurismoTest {
	
	Turismo sut;
	
	/**
	 * Comprueba que se retorna el precio correcto para diferentes turismos con diversos
	 * parametros.
	 */
	@Test
	public void testPrecioImpuesto() {
		
		// Comprueba el retorno del precio correcto para diferentes parametros.
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(1980, 1, 1), 0.1);
			assertEquals(sut.precioImpuesto(), 0, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1997, 1, 1), 10.0);
			assertEquals(sut.precioImpuesto(), 0, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1997, 6, 1), 4.0);
			assertEquals(sut.precioImpuesto(), 25.24, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 8.0);
			assertEquals(sut.precioImpuesto(), 68.16, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1980, 1, 1), 7.9);
			assertEquals(sut.precioImpuesto(), 0, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 11.9);
			assertEquals(sut.precioImpuesto(), 68.16, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1980, 1, 1), 12.0);
			assertEquals(sut.precioImpuesto(), 0, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 14.0);
			assertEquals(sut.precioImpuesto(), 143.88, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1980, 1, 1), 15.9);
			assertEquals(sut.precioImpuesto(), 0, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 16.0);
			assertEquals(sut.precioImpuesto(), 179.22, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1980, 1, 1), 18.0);
			assertEquals(sut.precioImpuesto(), 0, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 19.9);
			assertEquals(sut.precioImpuesto(), 179.22, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 20.0);
			assertEquals(sut.precioImpuesto(), 224.00, 0.0001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3,20), 50.0);
			assertEquals(sut.precioImpuesto(), 224.00, 0.0001);	
			
		} catch (OperacionNoValida e) {
			fail("Ha ocurrido una operación no válida, cuando deberían ser correctas.");
		}
	}
	
	/**
	 * Comprueba que el metodo constructor de la clase Turismo funciona correctamente.
	 */
	@Test
	public void testConstructorTurismo() {
		sut = null;
		
		// Caso valido 1
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(1990, 3, 1), 0.1);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(1990, 3, 1));
			assertEquals(sut.getPotencia(), 0.1, 0.0001);
		} catch (OperacionNoValida e) {
			fail("Ha ocurrido una operación no válida, cuando debería ser correcta.");
		}
		
		// Caso valido 2
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 24), 100.0);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(2022, 3, 24));
			assertEquals(sut.getPotencia(), 100.0, 0.0001);
		} catch (OperacionNoValida e) {
			fail("Ha ocurrido una operación no válida, cuando debería ser correcta.");
		}
		
		// Caso no valido. No debe permitir turismos sin matricula.
		try {
			sut = new Turismo(null, LocalDate.of(2022, 3, 24), 100.0);
			fail("Se ha permitido crear un turismo sin matrícula.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No debe permitir turismos con potencia igual o menor a cero.
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(1990, 3, 1), 0.0);
			fail("Se ha permitido crear un turismo con potencia igual a cero.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No debe permitir turismos sin fecha de matriculacion.
		try {
			sut = new Turismo("1234-FNX", null, 100.0);
			fail("Se ha permitido crear un turismo sin fecha de matriculación.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No debe permitir turismos con potencia negativa.
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 24), -100.0);
			fail("Se ha permitido crear un turismo con potencia negativa.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No debe permitir turismos con fechas de matriculacion futuras.
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 6, 1), 0.1);
			fail("Se ha permitido crear un turismo con fecha de matriculación posterior.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No debe permitir turismos con fechas de matriculacion futuras.
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(2050, 1, 1), 100.0);
			fail("Se ha permitido crear un turismo con fecha de matriculación posterior.");
		} catch (OperacionNoValida e) {
		}
	}
	
	/**
	 * Comprueba el funcionamiento del metodo equals.
	 */
	@Test
	public void testEquals() {
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 1, 1), 100.0);
			Turismo sut2 = new Turismo("1234-FNX", LocalDate.of(2022, 1, 1), 100.0);
			assertTrue(sut.equals(sut2));
			
			sut2 = new Turismo("1234-FNZ", LocalDate.of(2022, 1, 1), 100.0);
			assertFalse(sut.equals(sut2));
		} catch (OperacionNoValida e) {
			fail("Se ha producido una operación no válida, cuando debería ser correcta.");
		}
	}
}
