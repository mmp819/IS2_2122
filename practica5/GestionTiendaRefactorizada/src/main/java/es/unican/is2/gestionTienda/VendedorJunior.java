package es.unican.is2.gestionTienda;

/**
 * Vendedor Junior perteneciente a la plantilla.
 * 
 * @author Mario Martin Perez
 * @version 1.0
 */
public class VendedorJunior extends VendedorEnPlantilla {
	
	private static final double COMISION = 0.5;
	
	/**
	 * Retorna un nuevo vendedor en plantilla de tipo junior.
	 * @param nombre Nombre del vendedor.
	 * @param id ID del vendedor.
	 * @param dni DNI del vendedor.
	 */
	public VendedorJunior(String nombre, String id, String dni) {  // WMC + 1
		super(nombre, id, dni);
	}
	
	/**
	 * Retorna la comision por venta obtenida por el vendedor junior.
	 * @return comision por venta realizada.
	 */
	public double getComision() {  // WMC + 1
		return COMISION;
	}

}
