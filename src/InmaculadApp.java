import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.RejectedExecutionException;

import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import fechasimulada.FechaSimulada;

public class InmaculadApp implements Serializable{
	
	private List<Cliente> clientes; 
	private String contrase�aGerente;
	private Cliente usuarioConectado;
	
	private static InmaculadApp iApp = null;
	
	public static InmaculadApp getInstancia(String filename, String constrase�aGerente) {
		if (iApp == null)
			iApp = new InmaculadApp(filename, constrase�aGerente);
		
		return iApp;
	}
	
	private InmaculadApp(String filename, String constrase�aGerente) {
		clientes = new ArrayList<>();
		this.contrase�aGerente = constrase�aGerente;
		this.usuarioConectado = new Cliente("", "", "", "", "", null, null);
		
		try {
			cargarApp();
			if (iApp == null)
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
	
	public void guardarApp() throws IOException {
		FileOutputStream fout = new FileOutputStream("APP.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(this);
		
		oos.close();
	}
	
	private void cargarApp() {
		if (new File("APP.bin").exists()) {
			try {
			FileInputStream fin = new FileInputStream("APP.bin");
			ObjectInputStream ois = new ObjectInputStream(fin);
			iApp = (InmaculadApp) ois.readObject();
			
			ois.close();
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	
	public static void main(String... args) {
		if (new File("APP.bin").exists()) {
			try {
			FileInputStream fin = new FileInputStream("APP.bin");
			ObjectInputStream ois = new ObjectInputStream(fin);
			InmaculadApp iApp = (InmaculadApp) ois.readObject();
			
			System.out.println(iApp.clientes);
			
			ois.close();
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}









