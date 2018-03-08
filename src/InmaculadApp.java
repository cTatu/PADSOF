import java.io.*;
import java.util.*;


public class InmaculadApp {
	
	List<Cliente> clientes; 
	
	public InmaculadApp() {
		clientes = new ArrayList<>();
	}
	
	public void cargarClientes(String filename) throws Exception {
		if (new File("listUsuarios.bin").exists()) {
			FileInputStream fin = new FileInputStream("listUsuarios.bin");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object obj = ois.readObject();
			if (obj instanceof ArrayList) {
			    clientes = (ArrayList<Cliente>) obj;
				ois.close();
				return;
			}else {
				ois.close();
				throw new Exception("clientes: " + clientes);			
			}
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
	        	clientes.add(new Ofertante(NIF, nombreCompleto.split(",")[0], nombreCompleto.split(",")[1], contraseña, tarjetaCredito));
				break;
			case "D":
	        	clientes.add(new Demandante(NIF, nombreCompleto.split(",")[0], nombreCompleto.split(",")[1], contraseña, tarjetaCredito));
				break;
				
			case "OD":
	        	clientes.add(new ClienteDual(NIF, nombreCompleto.split(",")[0], nombreCompleto.split(",")[1], contraseña, tarjetaCredito));
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
		InmaculadApp iaApp = new InmaculadApp();
		
		try {
			iaApp.cargarClientes("Recursos\\ClientesEjemplo.txt");
			iaApp.guardarClientes();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}









