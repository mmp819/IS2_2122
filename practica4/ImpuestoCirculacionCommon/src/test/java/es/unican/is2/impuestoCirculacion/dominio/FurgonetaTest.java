package es.unican.is2.impuestoCirculacion.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import org.junit.Test;

import es.unican.is2.impuestoCirculacion.business.OperacionNoValida;

public class FurgonetaTest {
	@Test
	public void testPrecioImpuesto() {
		Furgoneta sut;
		
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
		
		try {
			sut = new Furgoneta("1234-FNX", null, 10.0, true);
			fail();
		} catch(OperacionNoValida e) {
		}
		
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 20), 0.0, true);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(1997, 6, 1), -10.0, true);
			fail();
		} catch (OperacionNoValida e) {
		}
	}
	
	@Test
	public void testConstructorFurgoneta() {
		Furgoneta sut;
		
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(1990, 3, 1), 0.1, true);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(1990, 3, 1));
			assertEquals(sut.getPotencia(), 0.1, 0.0001);
			assertEquals(sut.getComercial(), true);
			
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 24), 50, false);
			assertEquals(sut.getMatricula(), "1234-FNX");
			assertEquals(sut.getFechaMatriculacion(), LocalDate.of(2022, 3, 24));
			assertEquals(sut.getPotencia(), 50, 0.0001);
			assertEquals(sut.getComercial(), false);
		} catch (OperacionNoValida e) {
			fail();
		}
		
		try {
			sut = new Furgoneta(null, LocalDate.of(2022, 3, 24), 0.1, true);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 6, 1), 0.1, false);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(2050, 1, 1), 50.0, true);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Furgoneta("1234-FNX", null, 50.0, true);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(1990, 3, 1), 0.0, false);
			fail();
		} catch (OperacionNoValida e) {
		}
		
		try {
			sut = new Furgoneta("1234-FNX", LocalDate.of(2022, 3, 24), -100, true);
			fail();
		} catch (OperacionNoValida e) {
		}
	}
}
