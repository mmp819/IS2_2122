package es.unican.is2.gestiontienda;

/**
 * Clase abstracta para los vendedores pertenecientes a la plantilla.
 * 
 * @author Mario Martin Perez
 * @version 1.0
 */
public abstract class VendedorEnPlantilla extends Vendedor {
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre Nombre del vendedor.
	 * @param id ID del vendedor.
	 * @param dni DNI del vendedor.
	 */
	protected VendedorEnPlantilla(String nombre, String id, String dni) { // WMC + 1
		super(nombre, id, dni);
	}
	
	@Override
	public void anhadeVenta(double importe) {  // WMC + 1
		super.anhadeVenta(importe * (100.0 + getComision()) / 100.0);
	}
	
	/**
	 * Los vendedores de la plantilla deberan poder retornar la comision de sus ventas.
	 * @return porcentaje de comision por venta.
	 */
	public abstract double getComision();  
}
