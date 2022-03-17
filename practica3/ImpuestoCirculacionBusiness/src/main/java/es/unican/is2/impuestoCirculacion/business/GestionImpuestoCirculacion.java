package es.unican.is2.impuestoCirculacion.business;

// Imports
import es.unican.is2.impuestoCirculacion.dominio.Contribuyente;
import es.unican.is2.impuestoCirculacion.dominio.Vehiculo;

import es.unican.is2.impuestoCirculacion.dao.IContribuyentesDAO;
import es.unican.is2.impuestoCirculacion.dao.IVehiculosDAO;

import java.util.List;

/**
 * Clase que implementa la capa de negocio de la aplicacion
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, 
	IInfoImpuestoCirculacion {
	
	private IContribuyentesDAO contribuyentes;
	private IVehiculosDAO vehiculos;
	
	/**
	 * Crea el componente de la capa de negocio.
	 * @param contribuyentes DAO de contribuyentes.
	 * @param vehiculos DAO de vehiculos.
	 */
	public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentes, IVehiculosDAO vehiculos) {
		this.contribuyentes = contribuyentes;
		this.vehiculos = vehiculos;
	}
	
	/**
	 * Anhade un nuevo contribuyente.
	 * @param c Contribuyente que desea anhadir.
	 * @return El contribuyente anhadido.
	 * 		   null si no se anhade porque ya existe.
	 */
	public Contribuyente altaContribuyente(Contribuyente c) {
		return contribuyentes.creaContribuyente(c);
	}

	/**
	 * Elimina el contribuyente cuyo dni se pasa como parametro.
	 * @param dni DNI del cliente que se quiere eliminar.
	 * @return El contribuyente eliminado.
	 * 		   null si no se elimina porque no se encuentra.
	 * @throws OperacionNoValida si el contribuyente existe. 
	 *         pero tiene vehiculos a su nombre.
	 */
	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValida {
		Contribuyente c = contribuyentes.contribuyente(dni);
		
		if (c != null) { // Si se encuentra el contribuyente.
			
			if (c.getVehiculos().size() > 0) {
				throw new OperacionNoValida("El contribuyente tiene veh\u00edculos a su nombre.");
			} 
			contribuyentes.eliminaContribuyente(dni);
		}
		
		return c;		
	 }
	
	/**
	 * Retorna el contribuyente cuyo dni se pasa como parametro.
	 * @param dni DNI del contribuyente buscado.
	 * @return El contribuyente.
	 * 		   null si no existe.
	 */
	public Contribuyente contribuyente(String dni) {
		return contribuyentes.contribuyente(dni);
	}

	/**
	 * Anhade un nuevo vehiculo al contribuyente cuyo dni se pasa
	 * como parametro.
	 * @param v Vehiculo que desea anhadir.
	 * @param dni DNI del contribuyente.
	 * @return El vehiculo anhadido.
	 * 		   null si no se anhade porque no se encuentra el contribuyente.
	 * @throws OperacionNoValida si el vehiculo ya existe.
	 */
	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValida {
		Contribuyente c = contribuyente(dni);
		
		if (c != null) { // Si se encuentra al contribuyente.
			
			if (vehiculos.creaVehiculo(v) == null) { // Si el vehiculo ya existe
				throw new OperacionNoValida("Ya existe un veh\u00edculo con esa matr\u00edla.");
			}
			
			// Anhade el vehiculo al contribuyente.
			List<Vehiculo> vehiculos = c.getVehiculos();
			vehiculos.add(v);
			contribuyentes.actualizaContribuyente(c);
		} else { // Si no se encuentra al contribuyente.
			return null;
		}
		return v;
		
	}

	/**
	 * Elimina el vehiculo cuya matricula se pasa como parametro y 
	 * que pertenece al contribuyente cuyo dni se pasa como parametro
	 * @param matricula Matricula del coche a eliminar.
	 * @param dni DNI del propietario del vehiculo.
 	 * @return El vehiculo eliminado.
 	 *         null si el vehiculo o el propietario no existen.
 	 * @throws OperacionNoValida si el vehiculo no pertenece al dni indicado.
	 */
	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValida {
		Contribuyente c = contribuyente(dni);
		Vehiculo v = vehiculo(matricula);
		
		if (c != null && v != null) { // Si existen el vehiculo y el propietario.
			
			if (!c.getVehiculos().contains(v)) { // Si el vehiculo ya esta asignado al propietario.
				throw new OperacionNoValida("Ya existe un veh\u00edculo con esa matr\u00edla.");
			}
			
			// Elimina el vehiculo del contribuyente.
			List<Vehiculo> vehiculos = c.getVehiculos();
			vehiculos.remove(v);
			contribuyentes.actualizaContribuyente(c);
			
			// Elimina el vehiculo del registro.
			this.vehiculos.eliminaVehiculo(matricula);
		} else { // Si no se encuentran el vehiculo o el propietario.
			return null;
		}
		return v;
	}

	/**
	 * Retorna el vehiculo cuya matricula se pasa como parametro.
	 * @param matricula.
	 * @return El vehiculo indicado.
	 * 	       null si no existe.
	 */
	public Vehiculo vehiculo(String matricula) {
		return vehiculos.vehiculo(matricula);
	}	
}

