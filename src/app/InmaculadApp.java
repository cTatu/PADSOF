/*
 * @author David Pascual y Cristian Tatu
 */
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

// TODO: Auto-generated Javadoc
/**
 * Implementacion de la clase de la App.
 */
public class InmaculadApp implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The clientes. */
	private List<Cliente> clientes; 
	
	/** The inmuebles. */
	private List<Inmueble> inmuebles;
	
	/** The ofertas contratadas. */
	private List<Oferta> ofertasContratadas;
	
	/** The contrase�a gerente. */
	private String contrase�aGerente;
	
	/** The cliente conectado. */
	private Cliente clienteConectado;	
	
	/** The i app. */
	private static InmaculadApp iApp = null;
	
	/**
	 * Gets the instancia.
	 *
	 * @param filename the filename
	 * @param constrase�aGerente the constrase�a gerente
	 * @return the instancia
	 */
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
	
	/**
	 * Gets the instancia.
	 *
	 * @return the instancia
	 */
	public static InmaculadApp getInstancia() {
		return iApp;
	}
	
	/**
	 * Instantiates a new inmaculad app.
	 *
	 * @param filename the filename
	 * @param constrase�aGerente the constrase�a gerente
	 */
	private InmaculadApp(String filename, String constrase�aGerente) {
		clientes = new ArrayList<Cliente>();
		inmuebles = new ArrayList<Inmueble>();
		setOfertasContratadas(new ArrayList<Oferta>());
		this.contrase�aGerente = constrase�aGerente;
		this.clienteConectado = new Cliente("", "", "", "", "", null, null);
	}
	
	/**
	 * Crear inmueble.
	 *
	 * @param CP the cp
	 * @param localizacion the localizacion
	 * @param caracteristicas the caracteristicas
	 * @return true, if successful
	 */
	public boolean crearInmueble(int CP, String localizacion, Map<String,String> caracteristicas) {
		
		if (clienteConectado.rolOfertante == null)
			return false;
		
		Inmueble inmueble = new Inmueble(CP, localizacion, caracteristicas);
		inmuebles.add(inmueble);
		return clienteConectado.rolOfertante.a�adirInmuebles(inmueble);
	}
	
	/**
	 * Efectuar pagos pendientes.
	 *
	 * @return true, if successful
	 */
	public boolean efectuarPagosPendientes() {
		for (Cliente cliente : clientes) {
			if (cliente.rolOfertante != null && !cliente.rolOfertante.getSaldoPendiente().equals(0.0) && !cliente.isBloqueado()) {
				Double cantidad = cliente.rolOfertante.getSaldoPendiente();
				return transaccionACliente(cantidad, cliente);
			}
		}
		
		return false;
	}
	
	/**
	 * Transaccion A cliente.
	 *
	 * @param cantidad the cantidad
	 * @param cliente the cliente
	 * @return true, if successful
	 */
	public boolean transaccionACliente(Double cantidad,Cliente cliente) {
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
		
		return false;
	}
	
	/**
	 * Modificar tarjeta credito.
	 *
	 * @param cliente the cliente
	 * @param tarjetaNueva the tarjeta nueva
	 * @return true, if successful
	 */
	public boolean modificarTarjetaCredito(Cliente cliente,String tarjetaNueva) {
		if (!clienteConectado.gerente)
			return false;
		
		cliente.cambiarTarjeta(tarjetaNueva);
		efectuarPagosPendientes();
		return true;
	}
	
	/**
	 * A�adir oferta vivienda.
	 *
	 * @param precio the precio
	 * @param fechaInicio the fecha inicio
	 * @param descripcion the descripcion
	 * @param duracionMeses the duracion meses
	 * @param ID the id
	 * @param fianza the fianza
	 * @return true, if successful
	 */
	public boolean a�adirOfertaVivienda(Double precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses, Integer ID, Double fianza) {
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
	 * A�adir oferta vacacional.
	 *
	 * @param precio the precio
	 * @param fechaInicio the fecha inicio
	 * @param descripcion the descripcion
	 * @param fechaFin the fecha fin
	 * @param ID the id
	 * @return true, if successful
	 */
	public boolean a�adirOfertaVacacional(Double precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin, Integer ID) {
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
	 * Eliminar ofertas expiradas.
	 *
	 * @return the integer
	 */
	public Integer eliminarOfertasExpiradas() {
		Integer ofExp = 0;
		for (Inmueble inmueble : inmuebles) {
			ofExp += inmueble.eliminarOfertasExpiradas();
		}
		
		return ofExp;
	}
	

	
	/**
	 * Aprobar oferta.
	 *
	 * @param oferta the oferta
	 * @return true, if successful
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
	 * Adds the rectificacion.
	 *
	 * @param oferta the oferta
	 * @param rectif the rectif
	 * @return true, if successful
	 */
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
	 * Rechazar oferta.
	 *
	 * @param oferta the oferta
	 * @return true, if successful
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
	 * Contratar oferta.
	 *
	 * @param oferta the oferta
	 * @return true, if successful
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
		
		if (!transaccionACliente(-oferta.calcularComision(), clienteConectado))
			return false;
		
		clienteConectado.rolDemandante.a�adirOfertaContratada(oferta);
		
		return transaccionACliente(oferta.getPrecio(), oferta.getOfertante());
	}
	
	/**
	 * Contratar reserva.
	 *
	 * @param tipo the tipo
	 * @return true, if successful
	 */
	public boolean contratarReserva(TipoOferta tipo) {
		Reserva reserva = clienteConectado.rolDemandante.getReserva(tipo);
		
		if (reserva != null) {
			if (reserva.expirada()) {
				eliminarOfertasExpiradas();
				return false;
			}
			if (contratarOferta(reserva.getOferta()))
				return this.clienteConectado.rolDemandante.eliminarReserva(reserva.getTipo());
		}
		return false;
	}

	/**
	 * Reservar oferta.
	 *
	 * @param oferta the oferta
	 * @return true, if successful
	 */
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
	 * Buscar ofertas.
	 *
	 * @param criteriosBusqueda the criterios busqueda
	 * @param metodo the metodo
	 * @return the list
	 */
	public List<Oferta> buscarOfertas(Busqueda criteriosBusqueda,TipoOrdenar metodo){
		if(clienteConectado.rolDemandante == null)
			return null;
		
		if(clienteConectado.getContrase�a().isEmpty())
			criteriosBusqueda.setToDisponible();
		
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
	 * Iniciar sesion.
	 *
	 * @param NIF the nif
	 * @param contrase�a the contrase�a
	 * @return true, if successful
	 */
	public boolean iniciarSesion(String NIF, String contrase�a) {
		if (NIF.isEmpty() && contrase�a.equals(contrase�aGerente)) {
			this.clienteConectado = new Cliente("", "", "", "", "", null, null);
			clienteConectado.setContrase�a(contrase�a);
			clienteConectado.gerente = true;
			eliminarOfertasExpiradas();
			return true;
		}
		
		for (Cliente cliente : clientes) {
			if (cliente.getNIF().equals(NIF) && cliente.getContrase�a().equals(contrase�a)) {
				clienteConectado = cliente;
				eliminarOfertasExpiradas();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Cerrar sesion.
	 *
	 * @param GuardarNoGuardar Si es true la aplicacion se guarda en el disco, si es false no.
	 * @return true, if successful
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
	 * Cargar clientes.
	 *
	 * @param filename the filename
	 * @throws Exception the exception
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
	        String contrase�a = input.next();
	        String tarjetaCredito = input.next();
	        tarjetaCredito = tarjetaCredito.replace("\r", "");
	        
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
	
	/**
	 * Guardar app.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void guardarApp() throws IOException {
		FileOutputStream fout = new FileOutputStream("APP.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(this);
		
		oos.close();
	}
	
	/**
	 * Cargar app.
	 *
	 * @return true, if successful
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
	 * Gets the inmuebles.
	 *
	 * @return the inmuebles
	 */
	public List<Inmueble> getInmuebles(){
		return Collections.unmodifiableList(inmuebles);
	}
	
	/**
	 * Gets the ofertas pendientes.
	 *
	 * @return the ofertas pendientes
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
	 * Gets the ofertas.
	 *
	 * @return the ofertas
	 */
	public List<Oferta> getOfertas(){
		List<Oferta> of = new ArrayList<Oferta>();
		for (Inmueble inmueble: inmuebles) {
			of.addAll(inmueble.getOfertas());
		}
			
		return Collections.unmodifiableList(of);
	}
	
	/**
	 * Gets the inmueble by ID.
	 *
	 * @param ID the id
	 * @return the inmueble by ID
	 */
	public Inmueble getInmuebleByID(Integer ID) {
		for (Inmueble inmueble: inmuebles) {
			if (inmueble.getID().equals(ID))
				return inmueble;
		}
		
		return null;
	}
	
	/**
	 * Cliente conectado.
	 *
	 * @return the cliente
	 */
	public Cliente clienteConectado() {
		return clienteConectado;
	}

	/**
	 * Gets the ofertas contratadas.
	 *
	 * @return the ofertas contratadas
	 */
	public List<Oferta> getOfertasContratadas() {
		return ofertasContratadas;
	}

	/**
	 * Sets the ofertas contratadas.
	 *
	 * @param ofertasContratadas the new ofertas contratadas
	 */
	public void setOfertasContratadas(List<Oferta> ofertasContratadas) {
		this.ofertasContratadas = ofertasContratadas;
	}
}









