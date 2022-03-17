package es.unican.is2.impuestoCirculacion.run;

// Imports
import es.unican.is2.impuestoCirculacion.dao.ContribuyentesDAO;
import es.unican.is2.impuestoCirculacion.dao.VehiculosDAO;

import es.unican.is2.impuestoCirculacion.vista.VistaFuncionario;

import es.unican.is2.impuestoCirculacion.business.GestionImpuestoCirculacion;
/**
 * Clase principal que construye la aplicación de tres capas y lanza su ejecución
 */
public class Runner {

	public static void main(String[] args) {
		// Componentes capa DAO
		ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
		VehiculosDAO vehiculosDAO = new VehiculosDAO();
		
		// Componentes capa negocio
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
		
		// Componentes casa presentacion
		VistaFuncionario vista = new VistaFuncionario(negocio, negocio, negocio);
		
		// Lanza ejecución
		vista.setVisible(true);
	}

}
