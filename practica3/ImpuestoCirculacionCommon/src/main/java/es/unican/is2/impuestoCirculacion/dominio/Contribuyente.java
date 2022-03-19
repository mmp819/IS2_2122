package es.unican.is2.impuestoCirculacion.dominio;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")

/**
 * Contribuyente del ayuntamiento.
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public class Contribuyente implements Serializable {

    private List<Vehiculo> vehiculos;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
	
	/**
	 * Crea un contribuyente sin vehiculos.
	 * @param nombre Nombre.
	 * @param apellido1 Primer apellido.
	 * @param apellido2 Segundo apellido.
	 * @param dni DNI.
	 */
	public Contribuyente(String nombre, String apellido1, String apellido2, String dni) {
		this.vehiculos = new LinkedList<Vehiculo>();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
	}

	/**
     * Retorna el total a pagar por el impuesto.
     * de circulacion de todos sus vehiculos.
     * @return Valor del impuesto a pagar.
     */
    public double totalAPagar() {
    	double acumulado = 0.0;
    	
    	// Calcula la suma de los impuestos de todos los vehiculos.
    	for (Vehiculo v:this.vehiculos) {
    		acumulado += v.precioImpuesto();
    	}
    	
    	return acumulado;
    }
    
    /**
     * Retorna la lista de vehiculos del contribuyente.
     * @return lista de vehiculos del contribuyente.
     */
	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	
	/**
	 * Retorna el valor del atributo nombre.
	 * @return nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna el valor del atributo apellido1.
	 * @return primer apellido.
	 */
	public String getApellido1() {
		return apellido1;
	}
	
	/**
	 * Retorna el valor del atributo apellido2.
	 * @return segundo apellido.
	 */
	public String getApellido2() {
		return apellido2;
	}
	
	/**
	 * Retorna el valor del dni del contribuyente
	 * @return dni
	 */
	public String getDni() {
		return dni;
	}
}
