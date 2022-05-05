package es.unican.is2.gestionTienda;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VendedorEnPracticasTest {
	
	private static VendedorEnPracticas sut;

	@Before
	public void setUp(){
		sut = new VendedorEnPracticas("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sut.getId(), "1");
		assertEquals(sut.getNombre(), "Ana");
		assertEquals(sut.getDni(), "11111111A");
		assertTrue(sut.getTotalVentas()==0.0);
	}
	
	@Test
	public void testSetTotalVentas() {
		sut.setTotalVentas(100);
		assertTrue(sut.getTotalVentas()==100.0);
		
		sut.setTotalVentas(230);
		assertTrue(sut.getTotalVentas()==230.0);
		
		sut.setTotalVentas(0);
		assertTrue(sut.getTotalVentas()==0.0);
	}

	@Test
	public void testAnhadeVenta() {
		sut.anhadeVenta(200);
		assertTrue(sut.getTotalVentas() == 200.0);
		
		sut.anhadeVenta(300);
		assertTrue(sut.getTotalVentas() == 500.0);	
		
		sut.anhadeVenta(0);
		assertTrue(sut.getTotalVentas() == 500.0);
		
		
	}
	
	@Test
	public void testEquals() {
		VendedorEnPracticas igual = new VendedorEnPracticas("Ana", "1", "11111111A");
		VendedorEnPracticas distintoId = new VendedorEnPracticas("Ana", "2", "11111111A");
		VendedorEnPracticas distintoNombre = new VendedorEnPracticas("Pepe", "1", "222222222A");
		
		assertTrue(sut.equals(igual));
		assertFalse(sut.equals(distintoId));
		assertFalse(sut.equals(distintoNombre));
	}
	
	
	
}
