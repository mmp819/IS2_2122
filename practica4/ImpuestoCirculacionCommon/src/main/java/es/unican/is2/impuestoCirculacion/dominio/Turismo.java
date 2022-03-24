package es.unican.is2.impuestoCirculacion.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")

/**
 * Vehiculo de tipo turismo.
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public class Turismo extends Vehiculo implements Serializable
{
	// Potencias
	private static final double POT_TRAMO_1 = 8;
	private static final double POT_TRAMO_2 = 12;
	private static final double POT_TRAMO_3 = 16;
	private static final double POT_TRAMO_4 = 20;
	
	// Tarifas base
	private static final double TAR_TRAMO_1 = 25.24;
	private static final double TAR_TRAMO_2 = 68.16;
	private static final double TAR_TRAMO_3 = 143.88;
	private static final double TAR_TRAMO_4 = 179.22;
	private static final double TAR_TRAMO_5 = 224;
	
	// Exencion de matricula
	private static final int EX_MATRICULA = 25; // Anhos
	
	private double potencia;
	
	/**
	 * Construye un vehiculo de tipo turismo.
	 * 
	 * @param matricula Matricula.
	 * @param fechaMatriculacion Fecha de matriculacion.
	 * @param potencia Potencia en caballos fiscales.
	 */
	public Turismo(String matricula, LocalDate fechaMatriculacion, double potencia) {
		super(matricula, fechaMatriculacion);
		this.potencia = potencia;
	}
	
	/**
	 * Retorna la potencia del turismo.
	 * @return potencia en caballos fiscales.
	 */
    public double getPotencia() {
        return potencia;
    }
       
    
    /**
     * Retorna el precio del impuesto a pagar.
     *  @return precio.
     */
	@Override
    public double precioImpuesto() {
		double impuesto;
		
		// Determina el impuesto
		if (ChronoUnit.YEARS.between(getFechaMatriculacion(), LocalDate.now())
				> EX_MATRICULA) {
			impuesto = 0.0;
		} else if (potencia < POT_TRAMO_1) {
			impuesto = TAR_TRAMO_1;
		} else if (potencia >= POT_TRAMO_1 && potencia < POT_TRAMO_2) {
			impuesto = TAR_TRAMO_2;
		} else if (potencia >= POT_TRAMO_2 && potencia < POT_TRAMO_3) {
			impuesto = TAR_TRAMO_3;
		} else if (potencia >= POT_TRAMO_3 && potencia < POT_TRAMO_4) {
			impuesto = TAR_TRAMO_4;
		} else {
			impuesto = TAR_TRAMO_5;
		}
    	return impuesto;
    }
    
}
