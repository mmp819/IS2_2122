package es.unican.is2.gestionTienda;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase que representa una tienda con un conjunto de vendedores.
 * Gestiona las ventas realizadas y las comisiones asignadas a cada
 * vendedor. Los datos de la tienda se almacenan en un fichero de texto
 * que se pasa como parámetro al crear la tienda
 * 
 * @author Mario Martin Perez
 * @version 1.0
 */
public class Tienda {
	
	private LinkedList<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * 
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) {  // WMC + 1
		this.datos = datos;
		leeFichero(lista);
	}

	/**
	 * Retorna la dirección de la tienda
	 * @return Dirección de la tienda
	 */
	public String direccion() {  // WMC + 1
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String nombre() {  // WMC + 1
		return nombre;
	}

	/**
	 * Añade un nuevo vendedor a la tienda
	 * 
	 * @param nuevoVendedor Vendedor a añadir a la tienda.
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya había un vendedor con el mismo id
	 */
	public boolean anhadeVendedor(Vendedor nuevoVendedor) throws IOException {  // WMC + 1
		Vendedor v = buscaVendedor(nuevoVendedor.getId());
		if (v != null) {  // WMC + 1   // CCog + 1
			return false;
		}
		lista.add(nuevoVendedor);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como parámetro
	 * 
	 * @param id id del vendedor que se pasa como parámetro.
	 * @return true si se elimina el vendedor 
	 *         false si no existe ningún vendedor con el id indicado
	 */
	public boolean eliminaVendedor(String id) throws IOException {  // WMC + 1
		Vendedor v = buscaVendedor(id);
		if (v == null) {  // WMC + 1   // CCog + 1
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Añade una venta a un vendedor
	 * @param id Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se añade la venta 
	 *         false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws IOException {  // WMC + 1
		Vendedor v = buscaVendedor(id);
		if (v == null) {  // WMC + 1   // CCog + 1
			return false;
		}

		v.anhadeVenta(importe);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese id o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) {  // WMC + 1
		for (Vendedor v : lista) {  // WMC + 1     // CCog + 1
			if (v.getId().equals(id)) {  // WMC + 1    // CCog + 2
				return v; 
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() {  // WMC + 1
		return lista;

	}
	
	/**
	 * Retorna una lista con el vendedor o los vendedores del mes.
	 * @return lista con los vendedores del mes
	 */
	public List<Vendedor> vendedoresMes() {  // WMC + 1
		List<Vendedor> resultado = new LinkedList<Vendedor>();
		double maxVentas = 0.0;
		
		for (Vendedor v : lista) {  // WMC + 1   // CCog + 1
			if (v.getTotalVentas() > maxVentas) {  // WMC + 1   // CCog + 2
				maxVentas = v.getTotalVentas();
				resultado.clear();
				resultado.add(v);
				
			} else if (v.getTotalVentas() == maxVentas) { // WMC + 1   // CCog + 1
				resultado.add(v);
			}
		}
		return resultado;
	}

	/**
	 * Método que actualiza el fichero datosTienda.txt 
	 * con los datos actualizados de los vendedores
	 */
	private void vuelcaDatos() throws IOException {  // WMC + 1
		PrintWriter out = null;
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : lista) {  // WMC + 1    // CCog + 1
			if (v instanceof VendedorEnPracticas) {  // WMC + 1   // CCog + 2
				practicas.add(v);
			} else if (v instanceof VendedorEnPlantilla) {  // WMC + 1  // CCog + 1
				VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
				if (vp instanceof VendedorJunior) { // WMC + 1  // CCog + 3
					junior.add(vp);
				}
				else {  // CCog + 1
					senior.add(vp);
				}
			}
		}

		try {
			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println("Senior");
			vuelcaListaVendedores(senior, out);
			
			out.println();
			out.println("Junior");
			vuelcaListaVendedores(junior, out);
			
			out.println();
			out.println("Prácticas");
			vuelcaListaVendedores(practicas, out);
		} finally {
			if (out != null) // WMC + 1   // CCog + 1
				out.close();
		}
	}
	
	/**
	 * Carga una nueva lista de vendedores.
	 */
	public void cargaNuevaLista() {  // WMC + 1
		leeFichero(lista);
	}
	
	/**
	 * Lee un fichero de texto con los diferentes tipos de vendedores que componen la tienda.
	 * 
	 * @param listaVendedores Lista en la que realizar lectura.
	 */
	private void leeFichero(List<Vendedor> listaVendedores) {  // WMC + 1
		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			// configura el formato de números
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();

			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals("Junior")) { // WMC + 1   // CCog + 1
				
				leeVendedor(in, TipoVendedor.SENIOR, listaVendedores);
			}
			
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Prácticas")) {  // WMC + 1    // CCog + 1
				leeVendedor(in, TipoVendedor.JUNIOR, listaVendedores);
			}
			while (in.hasNext()) {  // WMC + 1    // CCog + 1
				in.next();
				leeVendedor(in, TipoVendedor.PRACTICAS, listaVendedores);
			}
		} catch (FileNotFoundException e) {  // CCog + 1

		} finally {
			if (in != null) {  // WMC + 1   // CCog + 1
				in.close();
			}
		} // try
	}
	
	/**
	 * Lee un vendedor en una entrada de texto.
	 * 
	 * @param in Escaner de texto a utilizar.
	 * @param tipo Tipo de vendedor a agregar.
	 * @param listaVendedores Lista en la que realizar lectura.
	 */
	private void leeVendedor(Scanner in, TipoVendedor tipo, List<Vendedor> listaVendedores) { // WMC + 1
		String nombre = in.next();
		in.next();
		String idIn = in.next();
		in.next();
		String dni= in.next();
		in.next();
		double totalVentas = in.nextDouble();
		Vendedor ven = null;
		
		switch(tipo) {  // CCog + 1
			case SENIOR: // WMC + 1
				ven = new VendedorSenior(nombre, idIn, dni);
				break;
			case JUNIOR: // WMC + 1
				ven = new VendedorJunior(nombre, idIn, dni);
				break;
			case PRACTICAS: // WMC + 1
				ven = new VendedorEnPracticas(nombre, idIn, dni);
				break;
		}

		ven.setTotalVentas(totalVentas);
		listaVendedores.add(ven);
	}
	
	/**
	 * Vuelca lista de vendedores en un fichero de texto.
	 * 
	 * @param vendedores Lista de vendedores a volcar.
	 * @param out Salida en la que escribir.
	 */
	private void vuelcaListaVendedores(List<Vendedor> vendedores, PrintWriter out) { // WMC + 1
		for (Vendedor v : vendedores) { // WMC + 1  // CCog + 1
			out.println(v.toString());
		}
	}

}
