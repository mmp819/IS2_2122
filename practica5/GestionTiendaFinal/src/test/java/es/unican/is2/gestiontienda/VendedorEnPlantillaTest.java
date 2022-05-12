package es.unican.is2.gestiontienda;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VendedorEnPlantillaTest {
	
	private static VendedorJunior sutJunior;
	private static VendedorSenior sutSenior;

	
	@Before
	public void setUp(){
		sutJunior = new VendedorJunior("Ana", "1", "11111111A");
		sutSenior = new VendedorSenior("Pepe", "2", "222222222A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals("1", sutJunior.getId());
		assertEquals("11111111A", sutJunior.getDni());
		assertEquals("Ana", sutJunior.getNombre());
		assertTrue(sutJunior instanceof VendedorJunior);
		assertTrue(sutSenior instanceof VendedorSenior);
	}

	@Test
	public void testAnhadeVenta() {
		
		sutJunior.anhadeVenta(200);
		assertEquals(201, sutJunior.getTotalVentas(), 0);
		
		sutJunior.anhadeVenta(300);
		assertEquals(502.5, sutJunior.getTotalVentas(), 0);
		
		sutSenior.anhadeVenta(300);
		assertEquals(303, sutSenior.getTotalVentas(), 0);
		
		sutSenior.anhadeVenta(300);
		assertEquals(606, sutSenior.getTotalVentas(), 0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutJunior.setTotalVentas(2000);
		assertEquals(2000, sutJunior.getTotalVentas(), 0);	
		sutJunior.setTotalVentas(4000);
		assertEquals(4000, sutJunior.getTotalVentas(), 0);	
		sutJunior.setTotalVentas(0);
		assertEquals(0, sutJunior.getTotalVentas(), 0);
		
		sutSenior.setTotalVentas(4500);
		assertEquals(4500, sutSenior.getTotalVentas(), 0);		
		sutSenior.setTotalVentas(4000);
		assertEquals(4000, sutSenior.getTotalVentas(), 0);
		sutJunior.setTotalVentas(0);
		assertEquals(0, sutJunior.getTotalVentas(), 0);	
		
	}

	
	@Test
	public void testEquals() {
		VendedorJunior igualJunior = new VendedorJunior("Ana", "1", "11111111A");
		VendedorJunior distintoIdJunior = new VendedorJunior("Ana", "2", "11111111A");
		VendedorJunior distintoDNIJunior = new VendedorJunior("Ana", "1", "222222222A");
		
		assertEquals(igualJunior, sutJunior);
		assertNotEquals(distintoIdJunior, sutJunior);
		assertNotEquals(distintoDNIJunior, sutJunior);
		
		
		VendedorSenior igualSenior = new VendedorSenior("Pepe", "2", "222222222A");
		VendedorSenior distintoIdSenior = new VendedorSenior("Pepe", "3", "222222222A");
		VendedorSenior distintoDNISenior = new VendedorSenior("Pepe", "2", "33333333A");
		
		assertEquals(igualSenior, sutSenior);
		assertNotEquals(distintoIdSenior, sutSenior);
		assertNotEquals(distintoDNISenior, sutSenior);
	}	
}
