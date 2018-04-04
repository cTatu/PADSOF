import java.io.*;
import java.time.LocalDate;
import java.util.*;

import com.sun.javafx.collections.UnmodifiableListSet;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

/**
 * 
 */
public class InmaculadApp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Cliente> clientes; 
	private List<Inmueble> inmuebles;
	private String contraseñaGerente;
	private Cliente clienteConectado;	
	private static InmaculadApp iApp = null;
	
	/**
	 * 
	 *
	 * @param filename 
	 * @param constraseñaGerente 
	 * @return 
	 */
	public static InmaculadApp getInstancia(String filename, String constraseñaGerente) {
		if (iApp == null) {
			iApp = new InmaculadApp(filename, constraseñaGerente);
			try {
				if(!iApp.cargarApp())
					iApp.cargarClientes(filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return iApp;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public static InmaculadApp getInstancia() {
		return iApp;
	}
	
	/**
	 * 
	 *
	 * @param filename 
	 * @param constraseñaGerente 
	 */
	private InmaculadApp(String filename, String constraseñaGerente) {
		clientes = new ArrayList<>();
		inmuebles = new ArrayList<>();
		this.contraseñaGerente = constraseñaGerente;
		this.clienteConectado = new Cliente("", "", "", "", "", null, null);
	}
	
	/**
	 * 
	 *
	 * @param CP 
	 * @param localizacion 
	 * @param caracteristicas 
	 * @return 
	 */
	public boolean crearInmueble(int CP, String localizacion, Map<String,String> caracteristicas) {
		
		if (clienteConectado.rolOfertante == null)
			return false;
		
		inmuebles.add(new Inmueble(CP, localizacion, caracteristicas));
		
		return true;
	}
	
	/**
	 * 
	 *
	 * @param precio 
	 * @param fechaInicio 
	 * @param descripcion 
	 * @param duracionMeses 
	 * @param ID 
	 * @return 
	 */
	public boolean añadirOfertaVivienda(Double precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses, Integer ID) {
		if (clienteConectado.rolOfertante == null)
			return false;
		
		for (Inmueble inmueble : inmuebles) {
			if (inmueble.getID().equals(ID)) {
				Oferta nuevaOferta = new OfertaVivienda(precio, fechaInicio, descripcion, duracionMeses, clienteConectado);
				inmueble.addOfertas(nuevaOferta);
				return true;
			}
		}

		return false;
	}
	
	/**
	 * 
	 *
	 * @param precio 
	 * @param fechaInicio 
	 * @param descripcion 
	 * @param fechaFin 
	 * @param ID 
	 * @return 
	 */
	public boolean añadirOfertaVacacional(Double precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin, Integer ID) {
		if (clienteConectado.rolOfertante == null)
			return false;
		
		for (Inmueble inmueble : inmuebles) {
			if (inmueble.getID().equals(ID)) {
				Oferta nuevaOferta = new OfertaVacacional(precio, fechaInicio, descripcion, fechaFin, clienteConectado);
				inmueble.addOfertas(nuevaOferta);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 *
	 * @param oferta 
	 * @return 
	 */
	public boolean aprobarOferta(Oferta oferta) {
		if (!clienteConectado.gerente)
			return false;
		
		for (Inmueble inmueble : inmuebles) {
			if (inmueble.aprobarOferta(oferta))
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * 
	 *
	 * @param oferta 
	 * @return 
	 */
	public boolean rechazarOferta(Oferta oferta) {
		if (!clienteConectado.gerente)
			return false;
		
		for (Inmueble inmueble : inmuebles) {
			if (inmueble.rechazarOferta(oferta))
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * 
	 *
	 * @param oferta 
	 * @return 
	 */
	public boolean contratarOferta(Oferta oferta) {
		if (clienteConectado.isBloqueado())
			return false;
		
		if (!TeleChargeAndPaySystem.isValidCardNumber(clienteConectado.getTarjetaCredito())) {
			clienteConectado.setBloqueado(true);
			return false;
		}
		
		if (!TeleChargeAndPaySystem.isValidCardNumber(clienteConectado.getTarjetaCredito())){
			clienteConectado.setBloqueado(true);
			clienteConectado.rolOfertante.setSaldoPendiente(oferta.getPrecio());
			return false;
		}				
		
		for (int i = 0; i < 5; i++) {
			try {
				TeleChargeAndPaySystem.charge(clienteConectado.getTarjetaCredito(), "Cobro: " + -oferta.calcularComision(), -oferta.calcularComision());
				break;
			}catch (FailedInternetConnectionException e) {
				if (i == 4)
					e.printStackTrace();
				continue;
			} catch (OrderRejectedException e) {
				clienteConectado.setBloqueado(true);
				return false;
			}
		}
		
		for (int i = 0; i < 5; i++) {
			try {
				TeleChargeAndPaySystem.charge(clienteConectado.getTarjetaCredito(), "Pago: " + oferta.getPrecio(), oferta.getPrecio());
				return true;
			}catch (FailedInternetConnectionException e) {
				if (i == 4)
					return false;
				continue;
			} catch (OrderRejectedException e) {
				clienteConectado.setBloqueado(true);
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 *
	 * @param criteriosBusqueda 
	 * @param cliente 
	 * @param metodo 
	 * @return 
	 */
	public List<OfertaVacacional> buscarVacacional(BusquedaVacacional criteriosBusqueda, Cliente cliente, TipoOrdenar metodo){
		List<OfertaVacacional> resultados = new ArrayList<>();
		
		for(Inmueble inmueble: inmuebles) {
			for(Oferta oferta: inmueble.getOfertas()) {
				if(oferta.getAprobada()==false || oferta.getModerada()==false)
					continue;
				if(criteriosBusqueda.comprobarOferta(oferta, inmueble.getCodigoPostal()) == true) {
					resultados.add((OfertaVacacional)oferta);
				}
			}
		}
		
		if(metodo.equals(TipoOrdenar.PRECIO))
			Collections.sort(resultados, new CompararPrecio());
		else if(metodo.equals(TipoOrdenar.FECHA))
			Collections.sort(resultados, new CompararFecha());
		else if(metodo.equals(TipoOrdenar.VALORACION))
			Collections.sort(resultados, new CompararValoracion());
		else if(metodo.equals(TipoOrdenar.DISPONIBILIDAD))
			Collections.sort(resultados, new CompararDisponibilidad());
		
		return resultados;
	}
	
	/**
	 * 
	 *
	 * @param criteriosBusqueda 
	 * @param cliente 
	 * @param metodo 
	 * @return 
	 */
	public List<OfertaVivienda> buscarVivienda(BusquedaVivienda criteriosBusqueda, Cliente cliente, TipoOrdenar metodo){
		List<OfertaVivienda> resultados = new ArrayList<>();
		
		for(Inmueble inmueble: inmuebles) {
			for(Oferta oferta: inmueble.getOfertas()) {
				if(criteriosBusqueda.comprobarOferta(oferta, inmueble.getCodigoPostal()) == true) {
					resultados.add((OfertaVivienda)oferta);
				}
			}
		}
		
		if(metodo.equals(TipoOrdenar.PRECIO))
			Collections.sort(resultados, new CompararPrecio());
		else if(metodo.equals(TipoOrdenar.FECHA))
			Collections.sort(resultados, new CompararFecha());
		else if(metodo.equals(TipoOrdenar.VALORACION))
			Collections.sort(resultados, new CompararValoracion());
		else if(metodo.equals(TipoOrdenar.DISPONIBILIDAD))
			Collections.sort(resultados, new CompararDisponibilidad());
		
		return resultados;
	}
	
	/**
	 * 
	 *
	 * @param NIF 
	 * @param contraseña 
	 * @return 
	 */
	public boolean iniciarSesion(String NIF, String contraseña) {
		if (NIF.isEmpty() && contraseña.equals(contraseñaGerente)) {
			clienteConectado.setContraseña(contraseña);
			clienteConectado.gerente = true;
			return true;
		}
		
		for (Cliente cliente : clientes) {
			if (cliente.getNIF().equals(NIF) && cliente.getContraseña().equals(contraseña)) {
				clienteConectado = cliente;
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 *
	 * @param GuardarNoGuardar 
	 * @return 
	 */
	public boolean cerrarSesion(boolean GuardarNoGuardar) {
		this.clienteConectado = new Cliente("", "", "", "", "", null, null);
		
		if (GuardarNoGuardar) {
			try {
				guardarApp();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 *
	 * @param filename 
	 * @throws Exception 
	 */
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
	
	/**
	 * 
	 *
	 * @throws IOException 
	 */
	public void guardarApp() throws IOException {
		FileOutputStream fout = new FileOutputStream("APP.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(this);
		
		oos.close();
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public boolean cargarApp() {
		if (new File("APP.bin").exists()) {
			try {
			FileInputStream fin = new FileInputStream("APP.bin");
			ObjectInputStream ois = new ObjectInputStream(fin);
			InmaculadApp app = (InmaculadApp) ois.readObject();
			
			ois.close();
			
			iApp = app;
			
			return true;
			}catch (Exception e) {
				System.out.println(e);
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public UnmodifiableListSet<Inmueble> getInmuebles(){
		return new UnmodifiableListSet<Inmueble>(inmuebles);
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public List <Oferta> getOfertasPendientes(){
		if (clienteConectado.gerente) {
			List<Oferta> ofertasPendientes = new ArrayList<Oferta>();
			for (Inmueble inmueble : inmuebles) {
				for (Oferta oferta : inmueble.getOfertas()) {
					if (!oferta.getAprobada())
						ofertasPendientes.add(oferta);
				}
			}
			return ofertasPendientes;
		}
		return null;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public UnmodifiableListSet<Oferta> getOfertas(){
		UnmodifiableListSet<Oferta> of = new UnmodifiableListSet<Oferta>(new ArrayList<Oferta>());
		for (Inmueble inmueble: inmuebles) {
			of.addAll(inmueble.getOfertas());
		}
			
		return of;
	}
	
	/**
	 * 
	 *
	 * @param ID 
	 * @return 
	 */
	public Inmueble getInmuebleByID(Integer ID) {
		for (Inmueble inmueble: inmuebles) {
			if (inmueble.getID().equals(ID))
				return inmueble;
		}
		
		return null;
	}
}









