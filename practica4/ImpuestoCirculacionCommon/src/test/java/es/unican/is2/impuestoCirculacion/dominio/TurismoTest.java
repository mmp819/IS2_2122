package es.unican.is2.impuestoCirculacion.dominio;

// Imports
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import org.junit.*;

import es.unican.is2.impuestoCirculacion.business.OperacionNoValida;

public class TurismoTest {
	
	@Test
	public void testPrecioImpuesto() {
		Turismo sut;
		
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(1980, 1, 1), 0.1);
			assertEquals(sut.precioImpuesto(), 0, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1997, 1, 1), 10.0);
			assertEquals(sut.precioImpuesto(), 0, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1997, 6, 1), 4.0);
			assertEquals(sut.precioImpuesto(), 25.24, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 8.0);
			assertEquals(sut.precioImpuesto(), 68.16, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1980, 1, 1), 7.9);
			assertEquals(sut.precioImpuesto(), 0, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 11.9);
			assertEquals(sut.precioImpuesto(), 68.16, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1980, 1, 1), 12.0);
			assertEquals(sut.precioImpuesto(), 0, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 14.0);
			assertEquals(sut.precioImpuesto(), 143.88, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1980, 1, 1), 15.9);
			assertEquals(sut.precioImpuesto(), 0, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 16.0);
			assertEquals(sut.precioImpuesto(), 179.22, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(1980, 1, 1), 18.0);
			assertEquals(sut.precioImpuesto(), 0, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 19.9);
			assertEquals(sut.precioImpuesto(), 179.22, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 20), 20.0);
			assertEquals(sut.precioImpuesto(), 224.00, 0.001);
			
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3,20), 50.0);
			assertEquals(sut.precioImpuesto(), 224.00, 0.001);	
		} catch (OperacionNoValida e) {
			fail();
		}
	}
	
	@Test
	public void testConstructorTurismo() {
		Turismo sut = null;
		
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(1990, 3, 1), 0.1);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(1990, 3, 1));
			assertEquals(sut.getPotencia(), 0.1, 0.001);
		} catch (OperacionNoValida e) {
			fail();
		}
		
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 24), 100.0);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(2022, 3, 24));
			assertEquals(sut.getPotencia(), 100.0, 0.001);
		} catch (OperacionNoValida e) {
			fail();
		}
		
		try {
			sut = new Turismo(null, LocalDate.of(2022, 3, 24), 5.0);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 5, 1), 5.0);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(2050, 1, 1), 5.0);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(1990, 3, 1), 0.0);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Turismo("1234-FNX", null, 5.0);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Turismo("1234-FNX", LocalDate.of(2022, 3, 24), -100);
			fail();
		} catch (OperacionNoValida e) {
		}

	}
}
