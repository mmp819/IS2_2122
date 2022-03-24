package es.unican.is2.impuestoCirculacion.rellenaAyuntamiento;

// Imports
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import es.unican.is2.impuestoCirculacion.dominio.*;


public class RellenaAyuntamiento {
	private static final String FILE_PATH = "C:\\Temp\\Ayuntamiento.out";

	public static void main(String args[]) {
		
		
		// Primer contribuyente
		Contribuyente c1 = new Contribuyente("Pepe", "López", "Martínez", "11111111A");
		Turismo t1 = new Turismo("1111-AAA", LocalDate.now().minusYears(5), 20.0);
		Furgoneta f1 = new Furgoneta("1111-BBB", LocalDate.now().minusMonths(15), 50.0);
		c1.getVehiculos().add(t1);
		c1.getVehiculos().add(f1);
			
		// Segundo contribuyente
		Contribuyente c2 = new Contribuyente("Ana", "Pérez", "López", "22222222B");
		Turismo t2 = new Turismo("2222-AAA", LocalDate.now().minusYears(30), 70);
		Motocicleta m2 = new Motocicleta("2222-BBB", LocalDate.now().minusDays(100), 50);
		c2.getVehiculos().add(t2);
		c2.getVehiculos().add(m2);	
			
		// Tercer contribuyente
		Contribuyente c3 = new Contribuyente("Luis", "Toca", "Pérez", "33333333C");
		Turismo t3 = new Turismo("3333-AAA", LocalDate.now().minusYears(4).minusMonths(3).minusDays(23), 100);
		Turismo t3b = new Turismo("3333-BBB", LocalDate.now().minusYears(9).minusMonths(10).minusDays(3), 5);
		c3.getVehiculos().add(t3);
		c3.getVehiculos().add(t3b);	
		
		Ayuntamiento ay= new Ayuntamiento();
		ay.getContribuyentes().add(c1);
		ay.getContribuyentes().add(c2);
		ay.getContribuyentes().add(c3);
		
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
		out.writeObject(ay);
		} 
		catch (IOException i) 
		{}
			
		
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
			Ayuntamiento ay2 = (Ayuntamiento) in.readObject();
			// verify the object state
			muestraContribuyentes(ay2);
			
		} catch (IOException ioe) 
    	{
			ioe.printStackTrace();
    	} 
    	catch (ClassNotFoundException cnfe) 
    	{
    		cnfe.printStackTrace();
    	}
	}
	
	private static void muestraContribuyentes(Ayuntamiento ayun) {
		for (Contribuyente c:ayun.getContribuyentes()) {
			System.out.println(c.getNombre()+" "+c.getApellido1()+" "+c.getApellido2());
			System.out.println(c.getDni());
			for (Vehiculo v:c.getVehiculos()) {
				System.out.println(v.toString());
			}
		}
	}

}
