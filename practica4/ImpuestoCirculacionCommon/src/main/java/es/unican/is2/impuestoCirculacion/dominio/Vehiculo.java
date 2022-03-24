package es.unican.is2.impuestoCirculacion.dominio;

import java.io.Serializable;
import java.time.LocalDate;


@SuppressWarnings("serial")

/**
 * Clase abstracta con las caracteristicas y metodos generales de un vehiculo.
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public abstract class Vehiculo implements Serializable{
  
    private String matricula;
	private LocalDate fechaMatriculacion;	

	/**
	 * Crea un vehiculo generico.
	 * 
	 * @param matricula Matricula del vehiculo.
	 * @param fechaMatriculacion Fecha de matriculacion del vehiculo.
	 */
	public Vehiculo(String matricula, LocalDate fechaMatriculacion) {
		this.matricula = matricula;
		this.fechaMatriculacion = fechaMatriculacion;
	}
	
	/**
     * Retorna el valor del impuesto de circulacion.
     *  @return valor del impuesto circulacion.
     */
	public abstract double precioImpuesto (); 


	/**
	 * Retorna la matricula del vehiculo.
	 * @return matricula.
	 */
    public String getMatricula() {
		return matricula;
	}

    /**
     * Retorna la fecha de matriculacion del vehiculo.
     * @return fecha de matriculacion.
     */
	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}
	
	@Override
	public boolean equals(Object o) {
		Vehiculo v = (Vehiculo) o;
		if (matricula == v.getMatricula()) {
			return true;
		}
		return false;
	}

}
