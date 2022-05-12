package es.unican.is2.gestiontienda;
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
		assertEquals("1", sut.getId());
		assertEquals("Ana", sut.getNombre());
		assertEquals("11111111A", sut.getDni());
		assertEquals(0.0, sut.getTotalVentas(), 0);
	}
	
	@Test
	public void testSetTotalVentas() {
		sut.setTotalVentas(100);
		assertEquals(100.0, sut.getTotalVentas(), 0);
		
		sut.setTotalVentas(230);
		assertEquals(230.0, sut.getTotalVentas(), 0);
		
		sut.setTotalVentas(0);
		assertEquals(0.0, sut.getTotalVentas(), 0);
	}

	@Test
	public void testAnhadeVenta() {
		sut.anhadeVenta(200);
		assertEquals(200.0, sut.getTotalVentas(), 0);
		
		sut.anhadeVenta(300);
		assertEquals(500.0, sut.getTotalVentas(), 0);	
		
		sut.anhadeVenta(0);
		assertEquals(500.0, sut.getTotalVentas(), 0);
		
		
	}
	
	@Test
	public void testEquals() {
		VendedorEnPracticas igual = new VendedorEnPracticas("Ana", "1", "11111111A");
		VendedorEnPracticas distintoId = new VendedorEnPracticas("Ana", "2", "11111111A");
		VendedorEnPracticas distintoNombre = new VendedorEnPracticas("Pepe", "1", "222222222A");
		
		assertEquals(igual, sut);
		assertNotEquals(distintoId, sut);
		assertNotEquals(distintoNombre, sut);
	}
	
	
	
}
