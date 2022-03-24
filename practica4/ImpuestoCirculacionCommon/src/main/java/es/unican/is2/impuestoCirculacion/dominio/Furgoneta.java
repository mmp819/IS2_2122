package es.unican.is2.impuestoCirculacion.dominio;

import java.io.Serializable;
import java.time.LocalDate;
@SuppressWarnings("serial")

/**
 * Vehiculo de tipo furgoneta.
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public class Furgoneta extends Turismo implements Serializable
{
    
    private boolean comercial;
    
    /**
     * Crea una furgoneta, con una asignacion determinada con respecto a si es comercial.
     * 
     * @param matricula Matricula.
     * @param fechaMatriculacion Fecha de matriculacion.
     * @param potencia Potencia en caballos fiscales.
     * @param comercial Si la furgoneta tiene un uso comercial.
     */
    public Furgoneta(String matricula, LocalDate fechaMatriculacion, double potencia,
    		boolean comercial) {
    	super(matricula, fechaMatriculacion, potencia);
    	this.comercial = comercial;
    }
    
    /**
     * Crea una furgoneta, por defecto no comercial.
     * @param matricula Matricula.
     * @param fechaMatriculacion Fecha de matriculacion.
     * @param potencia Potencia en caballos fiscales.
     */
    public Furgoneta(String matricula, LocalDate fechaMatriculacion, double potencia) {
    	super(matricula, fechaMatriculacion, potencia);
    	this.comercial = false;
    }
    
   /**
    * Retorna el valor del atributo comercial.
    * @return true si la furgoneta es de uso comercial.
    *         false si no es de uso comercial.
    */
    public boolean getComercial() {
    	return comercial;
    }
    
  
	@Override
    public double precioImpuesto() {
    	double impuesto = super.precioImpuesto();
    	
    	// Comprueba si debe aplicar bonificacion
    	if (comercial) {
    		impuesto -= impuesto * 0.2;
    	}
    		
		return impuesto;
    	
    }
}
