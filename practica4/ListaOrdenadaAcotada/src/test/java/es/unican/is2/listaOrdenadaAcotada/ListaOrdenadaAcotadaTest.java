package es.unican.is2.listaOrdenadaAcotada;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase de pruebas para ListaOrdenadaAcotada.
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public class ListaOrdenadaAcotadaTest {
	
	private ListaOrdenadaAcotada<Integer> sut;
	
	/**
	 * Prepara el test.
	 */
	@Before
	public void preparacion() {
		sut = new ListaOrdenadaAcotada<Integer>();
	}
	
	/**
	 * Comprueba que el constructor crea una lista vacia.
	 */
	@Test
	public void testConstructor() {
		assertTrue(sut.size()==0);
	}
	
	/**
	 * Comprueba que se anhaden y obtienen elementos correctamente.
	 */
	@Test
	public void testAddYGet() {
		try {
			sut.get(0);
			fail("Debería enviar IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
		}
		
		try {
			sut.add(null);
			fail("Debería enviar IllegalStateException.");
		} catch (NullPointerException e) {
		}
		
		try {
			sut.add(1);
			assertTrue(sut.get(0) == 1);
		} catch (IndexOutOfBoundsException e) {
			fail("Debería retornar correctamente 1 para el índice 0.");
		} catch (IllegalStateException e) {
			fail("Debería agregar correctamente el elemento.");
		}
		
		try {
			sut.add(2);
			assertTrue(sut.get(1) == 2);
			assertTrue(sut.get(0) == 1);
		} catch (IndexOutOfBoundsException e) {
			fail("Debería retornar correctamente 2 para ID 1 y 1 para ID 0.");
		} catch (IllegalStateException e) {
			fail("Debería agregar correctamente el elemento.");
		}
		
		try {
			sut.add(4);
			sut.add(5);
			sut.add(3);
			sut.add(6);
			sut.add(7);
			sut.add(10);
			sut.add(8);
			sut.add(9);
			assertTrue(sut.get(2) == 3);
			assertTrue(sut.get(9) == 10);
			assertTrue(sut.get(0) == 1);
		} catch (IndexOutOfBoundsException e) {
			fail ("Debería retornar correctamente índices en los extremos y en el centro.");
		} catch (IllegalStateException e) {
			fail ("No se debería haber sobrepasado el máximo de la lista.");
		}
		
		try {
			sut.add(11);
			fail("Debería haber lanzado IllegalStateException.");
		} catch (IllegalStateException e) {
		}
		
		try {
			sut.get(-1);
			fail("Debería haber lanzado IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException e) {
		}
		
		try {
			sut.get(10);
			fail("Debería haber lanzado IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException e) {
		}
	}
	
	/**
	 * Comprueba que se eliminan elementos correctamente.
	 */
	@Test
	public void testRemove() {
		
		try {
			sut.add(1);
		} catch (IllegalStateException e) {
			fail();
		}
		
		try {
			assertTrue(sut.remove(0) == 1);
			assertTrue(sut.size() == 0);
		} catch (IndexOutOfBoundsException e) {
			fail("No deberían existir fallos en los índices.");
		}
		
		try {
			sut.remove(0);
			fail("Debería haber lanzado IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException e) {
		}
		
		
		try {
			sut.add(1);
			sut.add(2);
			sut.add(4);
			sut.add(5);
			sut.add(3);
			sut.add(6);
			sut.add(7);
			sut.add(10);
			sut.add(8);
			sut.add(9);
		} catch (IllegalStateException e) {
			fail();
		}
		
		try {
			sut.remove(-1);
			fail("Debería haber lanzado IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException e) {
		}
		
		try {
			sut.remove(10);
			fail("Debería haber lanzado IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException e) {
		}
		
		try {
			assertTrue(sut.remove(0) == 1);
			assertTrue(sut.size() == 9);
			assertTrue(sut.remove(3) == 5);
			assertTrue(sut.size() == 8);
			assertTrue(sut.remove(7) == 10);
			assertTrue(sut.size() == 7);
		} catch (IndexOutOfBoundsException e) {
			fail("No deberían existir fallos en los índices.");
		}
	}
	
	/**
	 * Comprueba que se vacia la lista, o se obtiene su tamanho correctamente.
	 */
	@Test
	public void testClearYSize() {
		sut.add(2);
		sut.add(4);
		sut.add(3);
		sut.add(6);
		sut.add(7);
		sut.add(8);
		sut.add(9);
		assertTrue(sut.size() == 7);
		
		sut.add(1);
		sut.add(5);
		sut.add(10);
		
		assertTrue(sut.size() == 10);
		
		sut.clear();
		assertTrue(sut.size() == 0);
		
		sut.clear();
		assertTrue(sut.size() == 0);
		
		sut.add(1);
		assertTrue(sut.size() == 1);
		
		sut.clear();
		assertTrue(sut.size() == 0);
		
		sut.add(1);
		assertTrue(sut.size() == 1);
		
		sut.add(2);
		sut.add(3);
		sut.add(4);
		sut.add(5);
		assertTrue(sut.size() == 5);
		
		sut.clear();
		assertTrue(sut.size() == 0);
	}
}  
