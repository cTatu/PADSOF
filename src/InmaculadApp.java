import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.RejectedExecutionException;

import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import fechasimulada.FechaSimulada;

public class InmaculadApp {
	
	private List<Cliente> clientes; 
	private String contrase�aGerente;
	private Cliente usuarioConectado;
	
	private InmaculadApp(String filename, String constrase�aGerente) {
		clientes = new ArrayList<>();
		this.contrase�aGerente = constrase�aGerente;
		this.usuarioConectado = new Cliente("", "", "", "", "", null, null);
		
		try {
			cargarClientes(filename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean conectarCliente(String NIF, String contrase�a) {
		if (NIF.isEmpty() && contrase�a.equals(contrase�aGerente)) {
			usuarioConectado.setContrase�a(contrase�a);
			usuarioConectado.gerente = true;
			return true;
		}
		
		for (Cliente cliente : clientes) {
			if (cliente.getNIF().equals(NIF) && cliente.getContrase�a().equals(contrase�a)) {
				usuarioConectado = cliente;
				return true;
			}
		}
		
		return false;
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
	        String contrase�a = input.next();
	        String tarjetaCredito = input.next();

	        switch (rol) {
			case "O":
	        	clientes.add(new Cliente(nombreCompleto.split(",")[0], NIF, nombreCompleto.split(",")[1], contrase�a, tarjetaCredito, new Ofertante(), null));
				break;
			case "D":
	        	clientes.add(new Cliente(nombreCompleto.split(",")[0], NIF, nombreCompleto.split(",")[1], contrase�a, tarjetaCredito, null, new Demandante()));
				break;
				
			case "OD":
	        	clientes.add(new Cliente(nombreCompleto.split(",")[0], NIF, nombreCompleto.split(",")[1], contrase�a, tarjetaCredito, new Ofertante(), new Demandante()));
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
		
		System.out.println(iaApp.conectarCliente("", "BD911"));
		System.out.println(iaApp.usuarioConectado);
	}
}









