import java.io.*;
import java.util.*;

import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

public class InmaculadApp {
	
	private List<Cliente> clientes; 
	private String contraseñaGerente;
	
	private InmaculadApp(String filename, String constraseñaGerente) {
		clientes = new ArrayList<>();
		try {
			cargarClientes(filename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cargarClientes(String filename) throws Exception {
		if (new File("listUsuarios.bin").exists()) {
			FileInputStream fin = new FileInputStream("listUsuarios.bin");
			ObjectInputStream ois = new ObjectInputStream(fin);
			clientes = (List<Cliente>) ois.readObject();
  
			ois.close();
			return;
		}
		
		Scanner input = new Scanner(new File(filename));
	    input.useDelimiter(";|\n");
	    input.nextLine();
	    
	    while(input.hasNext()) {
	        String rol = input.next();
	        if (rol.equals("\r")) {
	        	input.close();
	        	return;	        	
	        }
	        String NIF = input.next();
	        String nombreCompleto = input.next();
	        String contraseña = input.next();
	        String tarjetaCredito = input.next();

	        switch (rol) {
			case "O":
	        	clientes.add(new Cliente(nombreCompleto.split(",")[0], NIF, nombreCompleto.split(",")[1], contraseña, tarjetaCredito, new Ofertante(), null));
				break;
			case "D":
	        	clientes.add(new Cliente(nombreCompleto.split(",")[0], NIF, nombreCompleto.split(",")[1], contraseña, tarjetaCredito, null, new Demandante()));
				break;
				
			case "OD":
	        	clientes.add(new Cliente(nombreCompleto.split(",")[0], NIF, nombreCompleto.split(",")[1], contraseña, tarjetaCredito, new Ofertante(), new Demandante()));
				break;
			default:
				break;
			}
	    }		
	    
	    input.close();
	}
	
	public void guardarClientes() throws IOException {
		FileOutputStream fout = new FileOutputStream("listUsuarios.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(clientes);
		
		oos.close();
	}
	
	
	public static void main(String... args) {
		InmaculadApp iaApp = new InmaculadApp("Recursos\\ClientesEjemplo.txt", "BD911");
		
	}
}









