package es.unican.is2.impuestoCirculacion.business;

// Imports
import es.unican.is2.impuestoCirculacion.dominio.Contribuyente;
import es.unican.is2.impuestoCirculacion.dominio.Vehiculo;

import es.unican.is2.impuestoCirculacion.dao.IContribuyentesDAO;
import es.unican.is2.impuestoCirculacion.dao.IVehiculosDAO;

/**
 * Clase que implementa la capa de negocio de la aplicacion
 */
public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {
	
	private IContribuyentesDAO contribuyentes;
	private IVehiculosDAO vehiculos;
	
	public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentes, IVehiculosDAO vehiculos) {
		this.contribuyentes = contribuyentes;
		this.vehiculos = vehiculos;
	}
	
	public Contribuyente altaContribuyente(Contribuyente c) {
		// TODO
		return null;
	}

	
	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValida {
		// TODO
		return null;		
	 }
	
	public Contribuyente contribuyente(String dni) {
		// TODO
		return null;
	}

	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValida {
		// TODO
		return null;
	}

	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValida {
		// TODO
		return null;
	}

	public Vehiculo vehiculo(String matricula) {
		// TODO
		return null;
	}	
}

