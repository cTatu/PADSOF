import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.RejectedExecutionException;

import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import fechasimulada.FechaSimulada;

public class InmaculadApp implements Serializable{
	
	private List<Cliente> clientes; 
	private String contraseñaGerente;
	private Cliente usuarioConectado;
	
	private static InmaculadApp iApp = null;
	
	public static InmaculadApp getInstancia(String filename, String constraseñaGerente) {
		if (iApp == null)
			iApp = new InmaculadApp(filename, constraseñaGerente);
		
		return iApp;
	}
	
	private InmaculadApp(String filename, String constraseñaGerente) {
		clientes = new ArrayList<>();
		this.contraseñaGerente = constraseñaGerente;
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
	
	public boolean conectarCliente(String NIF, String contraseña) {
		if (NIF.isEmpty() && contraseña.equals(contraseñaGerente)) {
			usuarioConectado.setContraseña(contraseña);
			usuarioConectado.gerente = true;
			return true;
		}
		
		for (Cliente cliente : clientes) {
			if (cliente.getNIF().equals(NIF) && cliente.getContraseña().equals(contraseña)) {
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









