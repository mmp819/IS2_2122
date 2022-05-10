package es.unican.is2.gestionTienda;

/**
 * Vendedor Senior perteneciente a la plantilla.
 * 
 * @author Mario Martin Perez
 * @version 1.0
 */
public class VendedorSenior extends VendedorEnPlantilla {

	private static final double COMISION = 1.0;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo senior.
	 * @param nombre Nombre del vendedor.
	 * @param id ID del vendedor.
	 * @param dni DNI del vendedor.
	 */
	public VendedorSenior(String nombre, String id, String dni) {  // WMC + 1
		super(nombre, id, dni);
	}
	
	/**
	 * Retorna la comision por venta obtenida por el vendedor senior.
	 * @return comision por venta realizada.
	 */
	public double getComision() {  // WMC + 1
		return COMISION;
	}
}
