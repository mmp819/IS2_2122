package es.unican.is2.gestiontienda;

/**
 * Vendedor en practicas.
 * 
 * @author Mario Martin Perez
 * @version 1.0
 *
 */
public class VendedorEnPracticas extends Vendedor {
	
	/**
	 * Retorna un nuevo vendedor en practicas.
	 * 
	 * @param nombre Nombre del vendedor.
	 * @param id ID del vendedor.
	 * @param dni DNI del vendedor.
	 */
	public VendedorEnPracticas(String nombre, String id, String dni) {  // WMC + 1
		super(nombre, id, dni);
	}
}
