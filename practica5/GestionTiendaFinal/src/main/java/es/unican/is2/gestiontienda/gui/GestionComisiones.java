package es.unican.is2.gestiontienda.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.unican.is2.gestiontienda.Tienda;
import es.unican.is2.gestiontienda.Vendedor;
import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Gestión de las comisiones de vendedores de una tienda
 * 
 * @author Mario Martin Perez
 * @version 1.0
 */
public class GestionComisiones {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {  // WMC + 1
		// opciones del menu
		final int NUEVA_VENTA = 0;
		final int VENDEDOR_DEL_MES = 1;
		final int VENDEDORES = 2;

		// variables auxiliares
		String dni;
		Lectura lect;

		List<Vendedor> vendedores;
		List<Vendedor> resultado;
		StringBuilder msj = new StringBuilder();

		// crea la tienda
		Tienda tienda = new Tienda("C:\\Temp\\datosTienda.txt");

		// crea la ventana de menu
		Menu menu = new Menu("Comisiones tienda");
		menu.insertaOpcion("Añadir venta", NUEVA_VENTA);
		menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
		menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {  // WMC + 1   // CCog + 1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {  // CCog + 2
			case NUEVA_VENTA: // WMC + 1
				lect = new Lectura("Datos Venta");
				lect.creaEntrada("Id Vendedor", "");
				lect.creaEntrada("Importe", "");
				lect.esperaYCierra();
				dni = lect.leeString("Id Vendedor");
				double importe = lect.leeDouble("Importe");
				try {
					if (!tienda.anhadeVenta(dni, importe)) {  // WMC + 1   // CCog + 3
						mensaje("ERROR", "El vendedor no existe");
					}
				} catch (IOException e) {  // CCog + 3
					mensaje("ERROR", "No se pudo guardar el cambio");
				}
				break;

			case VENDEDOR_DEL_MES:  // WMC + 1

				resultado = tienda.vendedoresMes();

				for (Vendedor vn : resultado) {  // WMC + 1  // CCog + 3
					msj.append(vn.getNombre());
					msj.append("\n");
				}
				mensaje("VENDEDORES DEL MES", msj.toString());
				break;

			
			case VENDEDORES:  // WMC + 1
	
				vendedores = tienda.vendedores();
				Collections.sort(vendedores, new ComparadorVendedorVentas());			
				
				for (Vendedor vn : vendedores) {  // WMC + 1   // CCog + 3
					msj.append(vn.getNombre());
					msj.append(" ");
					msj.append(vn.getTotalVentas());
					msj.append("\n");
				}
				mensaje("VENDEDORES", msj.toString());
				break;
			
			default:
				msj.append("");
				mensaje("OPCION NO RECONOCIDA", msj.toString());
			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo Titulo de la ventana
	 * @param txt Texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {  // WMC + 1
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}
	
	public static class ComparadorVendedorVentas implements Comparator<Vendedor>  {

		public int compare(Vendedor v1, Vendedor v2) {  // WMC + 1
			if (v1.getTotalVentas()>v2.getTotalVentas())  // WMC + 1  // CCog + 1
				return -1;
			else if (v1.getTotalVentas()<v2.getTotalVentas())  // WMC + 1  // CCog + 1
				return 1;
			return 0;
		}
		
	}
	
	
}
