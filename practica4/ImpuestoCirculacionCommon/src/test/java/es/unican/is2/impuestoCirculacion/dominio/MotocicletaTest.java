package es.unican.is2.impuestoCirculacion.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;

import es.unican.is2.impuestoCirculacion.business.OperacionNoValida;

/**
 * Test para vehiculos de tipo Motocicleta.
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public class MotocicletaTest {
	
	Motocicleta sut;
	
	/**
	 * Comprueba que se retorna el precio correcto para diferentes motocicletas con diversos
	 * parametros.
	 */
	@Test
	public void testPrecioImpuesto() {
		
		// Comprueba el retorno del precio correcto para diferentes parametros.
		try {
			sut = new Motocicleta("1234-FNX", LocalDate.of(1997, 6, 1), 1);
			assertEquals(sut.precioImpuesto(), 8.84, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(1980, 1, 1), 60);
			assertEquals(sut.precioImpuesto(), 0.0, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(2022, 3, 20), 124);
			assertEquals(sut.precioImpuesto(), 8.84, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(1997, 6, 1), 125);
			assertEquals(sut.precioImpuesto(), 15.14, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(1997, 1, 1), 175);
			assertEquals(sut.precioImpuesto(), 0, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(1997, 6, 1), 249);
			assertEquals(sut.precioImpuesto(), 15.14, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(2022, 3, 20), 250);
			assertEquals(sut.precioImpuesto(), 30.30, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(2022, 3, 20), 350);
			assertEquals(sut.precioImpuesto(), 30.30, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(1997, 6, 1), 499);
			assertEquals(sut.precioImpuesto(), 30.30, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(1997, 6, 1), 500);
			assertEquals(sut.precioImpuesto(), 60.58, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(1980, 1, 1), 750);
			assertEquals(sut.precioImpuesto(), 0, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(2022, 3, 20), 999);
			assertEquals(sut.precioImpuesto(), 60.58, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(1997, 6, 1), 1000);
			assertEquals(sut.precioImpuesto(), 121.16, 0.0001);
			
			sut = new Motocicleta("1234-FNX", LocalDate.of(1997, 6, 1), 1500);
			assertEquals(sut.precioImpuesto(), 121.16, 0.0001);
			
		} catch (OperacionNoValida e) {
			fail("Se ha producido una operación no válida, cuando debería ser válida.");
		}
	}
	
	/**
	 * Comprueba que el metodo constructor de la clase Motocicleta funciona correctamente.
	 */
	@Test
	public void testConstructor() {
		
		
		try {
			// Caso valido 1 
			sut = new Motocicleta("1234-FNX", LocalDate.of(1990, 3, 1), 1);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(1990, 3, 1));
			assertEquals(sut.getCilindrada(), 1);
			
			// Caso valido 2
			sut = new Motocicleta("1234-FNX", LocalDate.of(2022, 3, 24), 500);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(2022, 3, 24));
			assertEquals(sut.getCilindrada(), 500);
		} catch (OperacionNoValida e) {
			fail("Se ha producido una operación no válida, cuando debería ser válida.");
		}
		
		// Caso no valido. No puede existir una motocicleta sin matricula.
		try {
			sut = new Motocicleta(null, LocalDate.of(2022, 3, 24), 1);
			fail("Se ha creado una motocicleta sin matrícula.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No puede existir una motocicleta con fecha de matriculacion posterior.
		try {
			sut = new Motocicleta("1234-FNX", LocalDate.of(2022, 6, 1), 1);
			fail("Se ha creado una motocicleta con fecha de matriculación posterior.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No puede existir una motocicleta con fecha de matriculacion poserior.
		try {
			sut = new Motocicleta("1234-FNX", LocalDate.of(2050, 1, 1), 500);
			fail("Se ha creado una motocicleta con fecha de matriculación posterior.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No puede exisitr una motocicleta sin fecha de matriculacion.
		try {
			sut = new Motocicleta("1234-FNX", null, 1);
			fail("Se ha creado una motocicleta sin fecha de matriculación.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No puede existir una motocicleta con cilindrada igual a cero.
		try {
			sut = new Motocicleta("1234-FNX", LocalDate.of(1990, 3, 1), 0);
			fail("Se ha creado una motocicleta con cilindrada igual a cero.");
		} catch (OperacionNoValida e) {
		}
		
		// Caso no valido. No puede existir una motocicleta con cilindrada negativa.
		try {
			sut = new Motocicleta("1234-FNX", LocalDate.of(2022, 3, 24), -100);
			fail("Se ha creado una motocicleta con cilindrada negativa.");
		} catch (OperacionNoValida e) {
		}
	}
	
	/**
	 * Comprueba el funcionamiento del metodo equals.
	 */
	@Test
	public void testEquals() {
		try {
			sut = new Motocicleta("1234-FNX", LocalDate.of(2022, 1, 1), 100);
			Motocicleta sut2 = new Motocicleta("1234-FNX", LocalDate.of(2022, 1, 1), 100);
			assertTrue(sut.equals(sut2));
			
			sut2 = new Motocicleta("1234-FNZ", LocalDate.of(2022, 1, 1), 100);
			assertFalse(sut.equals(sut2));
		} catch (OperacionNoValida e) {
			fail();
		}
	}
}
