package es.unican.is2.impuestoCirculacion.dominio;

import java.time.LocalDate;

import es.unican.is2.impuestoCirculacion.business.OperacionNoValida;

@SuppressWarnings("serial")

/**
 * Vehiculo de tipo motocicleta.
 * 
 * @author Mario Martin Perez <mmp819@alumnos.unican.es>
 * @version 1.0
 */
public class Motocicleta extends Vehiculo
{
	// Cilindradas
	private static final int CC_TRAMO_1 = 125;
	private static final int CC_TRAMO_2 = 250;
	private static final int CC_TRAMO_3 = 500;
	private static final int CC_TRAMO_4 = 1000;
	
	// Tarifas base
	private static final double TAR_TRAMO_1 = 8.84;
	private static final double TAR_TRAMO_2 = 15.14;
	private static final double TAR_TRAMO_3 = 30.30;
	private static final double TAR_TRAMO_4 = 60.58;
	private static final double TAR_TRAMO_5 = 121.16;
		
	// Exencion de matricula
	private static final int EX_MATRICULA = 25; // Anhos
	
	private int cilindrada;

	/**
	 * Construye un vehiculo de tipo motocicleta.
	 * 
	 * @param matricula Matricula.
	 * @param fechaMatriculacion Fecha de matriculacion.
	 * @param cilindrada Cilindrada en centimetros cubicos.
	 */
	public Motocicleta(String matricula, LocalDate fechaMatriculacion, int cilindrada) 
			throws OperacionNoValida {
		super(matricula, fechaMatriculacion);
		
		if (cilindrada <= 0) {
			throw new OperacionNoValida("Potencial igual o menor a 0.0");
		}
		this.cilindrada = cilindrada;
	}
	
    /**
     * Retorna la cilindrada de la motocicleta.
     * @return cilindrada.
     */
    public int getCilindrada() {
        return cilindrada;
    }
    
  
	@Override
    public double precioImpuesto() {
		double impuesto;
		
		// Determina el impuesto
		if (getFechaMatriculacion().isBefore(LocalDate.now().minusYears(EX_MATRICULA))) {
			impuesto = 0.0;
		} else if (cilindrada < CC_TRAMO_1) {
			impuesto = TAR_TRAMO_1;
		} else if (cilindrada < CC_TRAMO_2) {
			impuesto = TAR_TRAMO_2;
		} else if (cilindrada < CC_TRAMO_3) {
			impuesto = TAR_TRAMO_3;
		} else if (cilindrada < CC_TRAMO_4) {
			impuesto = TAR_TRAMO_4;
		} else {
			impuesto = TAR_TRAMO_5;
		}
    	return impuesto;
    }
}
