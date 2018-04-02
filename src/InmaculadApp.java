import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.RejectedExecutionException;

import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import fechasimulada.FechaSimulada;

public class InmaculadApp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Cliente> clientes; 
	private List<Inmueble> inmuebles;
	private List<Pago> pagos;
	private String contrase�aGerente;
	private Cliente clienteConectado;	

	private static InmaculadApp iApp = null;
	
	public static InmaculadApp getInstancia(String filename, String constrase�aGerente) {
		if (iApp == null) {
			iApp = new InmaculadApp(filename, constrase�aGerente);
			try {
				if(!iApp.cargarApp())
					iApp.cargarClientes(filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return iApp;
	}
	
	private InmaculadApp(String filename, String constrase�aGerente) {
		clientes = new ArrayList<>();
		inmuebles = new ArrayList<>();
		this.contrase�aGerente = constrase�aGerente;
		this.clienteConectado = new Cliente("", "", "", "", "", null, null);
	}
	
	public boolean conectarCliente(String NIF, String contrase�a) {
		if (NIF.isEmpty() && contrase�a.equals(contrase�aGerente)) {
			clienteConectado.setContrase�a(contrase�a);
			clienteConectado.gerente = true;
			return true;
		}
		
		for (Cliente cliente : clientes) {
			if (cliente.getNIF().equals(NIF) && cliente.getContrase�a().equals(contrase�a)) {
				clienteConectado = cliente;
				return true;
			}
		}
		
		return false;
	}
	
	private void cargarClientes(String filename) throws Exception {
		
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
	
	private boolean cargarApp() {
		if (new File("APP.bin").exists()) {
			try {
			FileInputStream fin = new FileInputStream("APP.bin");
			ObjectInputStream ois = new ObjectInputStream(fin);
			InmaculadApp app = (InmaculadApp) ois.readObject();
			
			ois.close();
			
			iApp.clientes = app.clientes;
			iApp.contrase�aGerente = app.contrase�aGerente;
			iApp.clienteConectado = app.clienteConectado;
			
			return true;
			}catch (Exception e) {
				System.out.println(e);
				return false;
			}
		}
		
		return false;
	}
	
	
	public static void main(String... args) {
		InmaculadApp app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		
		System.out.println(app.clientes);
	}
}









