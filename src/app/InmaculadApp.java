package app;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import busqueda.Busqueda;
import busqueda.BusquedaVacacional;
import busqueda.BusquedaVivienda;
import cliente.Cliente;
import cliente.Demandante;
import cliente.Ofertante;
import comparator.CompararDisponibilidad;
import comparator.CompararFecha;
import comparator.CompararPrecio;
import comparator.CompararValoracion;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import inmueble.Inmueble;
import oferta.Oferta;
import oferta.OfertaVacacional;
import oferta.OfertaVivienda;
import reserva.Reserva;
import reserva.ReservaVacacional;
import reserva.ReservaVivienda;
import tipos.TipoOferta;
import tipos.TipoOrdenar;

/**
 * 
 */
public class InmaculadApp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Cliente> clientes; 
	private List<Inmueble> inmuebles;
	private List<Oferta> ofertasContratadas;
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
		clientes = new ArrayList<Cliente>();
		inmuebles = new ArrayList<Inmueble>();
		ofertasContratadas = new ArrayList<Oferta>();
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
	
	
	public boolean efectuarPagosPendientes() {
		for (Cliente cliente : clientes) {
			if (!cliente.rolOfertante.getSaldoPendiente().equals(0.0) && !cliente.isBloqueado()) {
				Double cantidad = cliente.rolOfertante.getSaldoPendiente();
				for (int i = 0; i < 5; i++) {
					try {
						TeleChargeAndPaySystem.charge(cliente.getTarjetaCredito(), "Pago: " + cantidad, cantidad);
						return true;
					}catch (FailedInternetConnectionException e) {
						if (i == 4)
							e.printStackTrace();
						continue;
					} catch (OrderRejectedException e) {
						clienteConectado.setBloqueado(true);
						return false;
					}
				}
			}
		}
		
		return false;
	}
	
	
	public boolean modificarTarjetaCredito(Cliente cliente,String tarjetaNueva) {
		if (!clienteConectado.gerente)
			return false;
		
		cliente.cambiarTartjeta(tarjetaNueva);
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
	public boolean añadirOfertaVivienda(Double precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses, Integer ID, double fianza) {
		if (clienteConectado.rolOfertante == null)
			return false;
		
		for (Inmueble inmueble : inmuebles) {
			if (inmueble.getID().equals(ID)) {
				Oferta nuevaOferta = new OfertaVivienda(precio, fechaInicio, descripcion, duracionMeses, clienteConectado, fianza);
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
	
	public Integer eliminarOfertasExpiradas() {
		Integer ofExp = 0;
		for (Inmueble inmueble : inmuebles) {
			ofExp += inmueble.eliminarOfertasExpiradas();
		}
		
		return ofExp;
	}
	
	public boolean añadirRectificacion(Oferta oferta, Map<String, String> rectificacion) {
		if (!this.clienteConectado.gerente)
			return false;
		return oferta.añadirRectificacion(rectificacion);
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
	
	public boolean addRectificacion(Oferta oferta, Map<String, String> rectif) {
		if (!clienteConectado.gerente)
			return false;
		
		for (Inmueble inmueble : inmuebles) {
			if (inmueble.addRectificacion(oferta, rectif))
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
		if (clienteConectado.isBloqueado() || clienteConectado.rolDemandante == null)
			return false;
		
		if (!clienteConectado.rolDemandante.puedeContratar(oferta))
			return false;
		
		if (!TeleChargeAndPaySystem.isValidCardNumber(clienteConectado.getTarjetaCredito())) {
			clienteConectado.setBloqueado(true);
			return false;
		}
		
		if (!TeleChargeAndPaySystem.isValidCardNumber(oferta.getOfertante().getTarjetaCredito())){
			clienteConectado.setBloqueado(true);
			oferta.getOfertante().rolOfertante.setSaldoPendiente(oferta.getPrecio());
			return false;
		}				
		
		for (int i = 0; i < 5; i++) {
			try {
				TeleChargeAndPaySystem.charge(clienteConectado.getTarjetaCredito(), "Cobro", -oferta.calcularComision(), true);
				break;
			}catch (FailedInternetConnectionException e) {
				if (i == 4) {
					e.printStackTrace();
				return false;
				}
				continue;
			} catch (OrderRejectedException e) {
				clienteConectado.setBloqueado(true);
				return false;
			}
		}
		
		for (int i = 0; i < 5; i++) {
			try {
				TeleChargeAndPaySystem.charge(oferta.getOfertante().getTarjetaCredito(), "Pago", oferta.getPrecio(), true);
				if (oferta.contratar(clienteConectado)) {
					ofertasContratadas.add(oferta);
					return true;
				}					
				return false;
			}catch (FailedInternetConnectionException e) {
				if (i == 4) {
					e.printStackTrace();
					return false;
				}
				continue;
			} catch (OrderRejectedException e) {
				oferta.getOfertante().setBloqueado(true);
				oferta.getOfertante().rolOfertante.setSaldoPendiente(oferta.getPrecio());
				return false;
			}
	 	}
		
		return false;
	}
	
	public boolean contratarReserva(TipoOferta tipo) {
		Reserva reserva = clienteConectado.rolDemandante.getReserva(tipo);
		
		if (reserva != null) {
			if (reserva.expirada()) {
				eliminarOfertasExpiradas();
				return false;
			}
			return contratarOferta(reserva.getOferta());
		}
		return false;
	}

	public boolean reservarOferta(Oferta oferta) {
		if (oferta.getReservada() || clienteConectado.rolDemandante == null)
			return false;
		
		Reserva reserva;
		if (oferta instanceof OfertaVacacional)
			reserva = new ReservaVacacional(oferta);
		else
			reserva = new ReservaVivienda(oferta);
		
		if (clienteConectado.rolDemandante.puedeReservar(reserva)) {
			return oferta.reservar(reserva, clienteConectado);
		}else
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
	public List<Oferta> buscarOfertas(Busqueda criteriosBusqueda,TipoOrdenar metodo){
		List<Oferta> resultados = new ArrayList<>();
		
		for(Inmueble inmueble: inmuebles) {
			if (inmueble.getCodigoPostal().equals(criteriosBusqueda.getCodigoPostal())) {
				for(Oferta oferta: inmueble.getOfertas()) {
					if(oferta.getAprobada() 
							&& 
					criteriosBusqueda.comprobarOferta(oferta)) {
						resultados.add(oferta);
					}
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
			eliminarOfertasExpiradas();
			return true;
		}
		
		for (Cliente cliente : clientes) {
			if (cliente.getNIF().equals(NIF) && cliente.getContraseña().equals(contraseña)) {
				clienteConectado = cliente;
				eliminarOfertasExpiradas();
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
	        tarjetaCredito = tarjetaCredito.replace("\r", "");
	        
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
	public List<Inmueble> getInmuebles(){
		return Collections.unmodifiableList(inmuebles);
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
					if (!oferta.getAprobada() && !oferta.getModerada())
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
	public List<Oferta> getOfertas(){
		List<Oferta> of = new ArrayList<Oferta>();
		for (Inmueble inmueble: inmuebles) {
			of.addAll(inmueble.getOfertas());
		}
			
		return Collections.unmodifiableList(of);
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
	
	public Cliente clienteConectado() {
		return clienteConectado;
	}
}









