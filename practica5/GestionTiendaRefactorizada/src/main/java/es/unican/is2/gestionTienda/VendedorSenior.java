package es.unican.is2.gestionTienda;

public class VendedorSenior extends VendedorEnPlantilla {

	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorSenior(String nombre, String id, String dni) {
		super(nombre, id, dni);
	}
}
