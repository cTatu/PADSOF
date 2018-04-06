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

/**
 * Implementacion de la clase de la App.
 */
public class InmaculadApp implements Serializable{
	
	/** Constante de serializacion serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Lista clientes. */
	private List<Cliente> clientes; 
	
	/** Lista inmuebles. */
	private List<Inmueble> inmuebles;
	
	/** Lista ofertas contratadas. */
	private List<Oferta> ofertasContratadas;
	
	/** Contraseña gerente. */
	private String contraseñaGerente;
	
	/** Cliente conectado. */
	private Cliente clienteConectado;	
	
	/** Singleton */
	private static InmaculadApp iApp = null;
	
	/**
	 * Devuelve la unico instancia que se puede crear en la aplicacion
	 * implementando el patron singleton
	 *
	 * @param filename Fichero de con los clientes
	 * @param constraseñaGerente La constraseña gerente
	 * @return unica instancia singleton
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
	 * Devuelve la instancia sin crear
	 *
	 * @return instancia
	 */
	public static InmaculadApp getInstancia() {
		return iApp;
	}
	
	/**
	 * Constructor de la aplicacion que inicializa los atributos
	 *
	 * @param filename Fichero de con los clientes
	 * @param constraseñaGerente La constraseña gerente
	 */
	private InmaculadApp(String filename, String constraseñaGerente) {
		clientes = new ArrayList<Cliente>();
		inmuebles = new ArrayList<Inmueble>();
		setOfertasContratadas(new ArrayList<Oferta>());
		this.contraseñaGerente = constraseñaGerente;
		this.clienteConectado = new Cliente("", "", "", "", "", null, null);
	}
	
	/**
	 * Crear un nuevo inmueble a partir de los parametros
	 * y lo añade a la lista de los inmuebles de la App y a la del ofertante
	 *
	 * @param CP codigo postal
	 * @param localizacion localizacion del inmueble
	 * @param caracteristicas mapa de caracteristicas del inmueble
	 * @return true si se ha añadido correctamente
	 */
	public boolean crearInmueble(int CP, String localizacion, Map<String,String> caracteristicas) {
		
		if (clienteConectado.rolOfertante == null)
			return false;
		
		Inmueble inmueble = new Inmueble(CP, localizacion, caracteristicas);
		inmuebles.add(inmueble);
		return clienteConectado.rolOfertante.añadirInmuebles(inmueble);
	}
	
	/**
	 * Se comprueban todos los clientes en busca de alguno que tenga un pago pendiente
	 * y se le intenta pagar la cantidad deudada
	 *
	 * @return true si la transaccion ha tenido exito
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
	 * Realiza la operacion con el sistema de pagos TeleChargePay y controlando las
	 * señales de error. Si no hay conexion a internet lo intantara 5 veces antes de 
	 * saltar la excepcion
	 *
	 * @param cantidad cantidad que se va a pagar o cobrar
	 * @param cliente a que cliente
	 * @return true si ha tenido exito el pago
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
	 * Modificar tarjeta credito. Solo el gerente puede hacerlo
	 *
	 * @param cliente el cliente al que se le va a modificar la tarjeta
	 * @param tarjetaNueva el nuevo numero de tarjeta
	 * @return true si es el gerente
	 */
	public boolean modificarTarjetaCredito(Cliente cliente,String tarjetaNueva) {
		if (!clienteConectado.gerente)
			return false;
		
		cliente.cambiarTarjeta(tarjetaNueva);
		efectuarPagosPendientes();
		return true;
	}
	
	/**
	 * Crea una oferta de tipo vivienda y la añade a la lista de las ofertas
	 * del inmueble especificado a traves del ID.
	 *
	 * @param precio el precio de la oferta
	 * @param fechaInicio la fecha inicio
	 * @param descripcion una descripcion
	 * @param duracionMeses la duracion en meses
	 * @param ID el ID del inmueble
	 * @param fianza la fianza
	 * @return true si se ha encontrado el inmueble y ademas si se ha añadido bien a las listas
	 */
	public boolean añadirOfertaVivienda(Double precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses, Integer ID, Double fianza) {
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
	 * Crea una oferta de tipo vacacional y la añade a la lista de las ofertas
	 * del inmueble especificado a traves del ID.
	 *
	 * @param precio el precio
	 * @param fechaInicio la fecha de inicio
	 * @param descripcion una descripcion
	 * @param fechaFin la fecha de fin
	 * @param ID el ID del inmueble
	 * @return true si se ha encontrado el inmueble y ademas si se ha añadido bien a las listas
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
	 * Recorre la lista de todos los inmuebles y elimina las ofertas que hayan
	 * caducado dentro de cada uno.
	 *
	 * @return el numero total de ofertas que se han tenido que borrar
	 */
	public Integer eliminarOfertasExpiradas() {
		Integer ofExp = 0;
		for (Inmueble inmueble : inmuebles) {
			ofExp += inmueble.eliminarOfertasExpiradas();
		}
		
		return ofExp;
	}
	

	
	/**
	 * La aprobacion de la oferta se hace por parte del gerente.
	 * El metodo busca la oferta especifica en todos los inmuebles.
	 *
	 * @param oferta la oferta a aprobar
	 * @return si se ha encontrado la oferta y se ha podido aprobar
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
	 * Añade un mapa de dos String a una oferta representando las rectificaciones
	 * que el gerente ha recomendado hacer.
	 *
	 * @param oferta la oferta a la que se quiere añadir rectificacion
	 * @param rectif el mapa conteniendo las rectificaciones
	 * @return true si es el gerente el que realiza la accion y si ha añadido exitosamente el mapa
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
	 * Rechazar la oferta se hace por el gerente y borra totalmente la oferta
	 * del sistema.
	 *
	 * @param oferta la oferta que se quiere borrar
	 * @return true si se ha encontrado la oferta y si se ha borrado exitosamente
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
	 * Metodo principal para contratar una oferta, compruba que
	 * los dos clientes no tengan una tarjeta de credito invalida y 
	 * si superan la comprobacion se procede al cobro y pago del demandante y 
	 * ofertante respectivamente.
	 *
	 * @param oferta la oferta que se quiere contratar
	 * @return true si el usuario conectado tiene rol de demandante y ademas que las operaciones hayan tenido exito
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
		
		clienteConectado.rolDemandante.añadirOfertaContratada(oferta);
		
		return transaccionACliente(oferta.getPrecio(), oferta.getOfertante());
	}
	
	/**
	 * Contrata la oferta que esta vinculada a la reserva. Primero eliminando cualquier
	 * reserva que haya expirado y despues intentando contratar la oferta.
	 *
	 * @param tipo el tipo de la reserva que se quiere contratar
	 * @return true si el usuario tiene una reserva de ese tipo y ademas se haya podido contratar
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
	 * Metodo principal para la reserva de ofertas, se comprueba que tipo es la oferta
	 * y seguidametne se compruba si el demadnante conectado puede reservarla.
	 *
	 * @param oferta la oferta que se quiere reservar
	 * @return true si el usaurio es demandante y ademas que cumpla los requisitos de la reserva
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
	 * En el objeto de Busqueda se guardan todos los parametros por los que se quiere
	 * buscar una oferta. Recorriendo todas las ofertas se comprueba a traves del metodo
	 * propio de Busqueda si la oferta cumple los requisitos añadiendola a la lista.
	 * Al final se ordena la lista por el orden especificado en TipoOrdenar.
	 *
	 * @param criteriosBusqueda los criterios busqueda para las ofertas
	 * @param metodo el metodo que se usara al ordenar
	 * @return la lista con las ofertas de la busqueda
	 */
	public List<Oferta> buscarOfertas(Busqueda criteriosBusqueda,TipoOrdenar metodo){
		if(clienteConectado.rolDemandante == null)
			return null;
		
		if(clienteConectado.getContraseña().isEmpty())
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
	 * Inicia sesion en la aplicacion comprobando primeramente si la contraseña es del
	 * gerente. Si no, se comprueba en la lista de los clientes con el NIF y la contraseña
	 * en busca de alguna coincidencia.
	 *
	 * @param NIF el NIF para iniciar sesion
	 * @param contraseña la contraseña
	 * @return true si se ha encontrado el cliente buscado o si es el gerente
	 */
	public boolean iniciarSesion(String NIF, String contraseña) {
		if (NIF.isEmpty() && contraseña.equals(contraseñaGerente)) {
			this.clienteConectado = new Cliente("", "", "", "", "", null, null);
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
	 * Al cerrar sesion el cliente conectado se limpia y la aplicaicon guarda una copia 
	 * de seguridad en el disco.
	 *
	 * @param GuardarNoGuardar Si es true la aplicacion se guarda en el disco, si es false no.
	 * @return true o lanza excepcion de IO
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
	 * Lee los usuarios del fichero especificado en el constructor
	 * construyendo para cada linea del ficheor un nuevo cliente con los datos
	 * propios del archivo.
	 *
	 * @param filename el nu¡ombre del fichero texto con los clientes
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
	 * Guarda el obejto de la aplicacion actual con todos los objetos que lo contienen.
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
	 * Lee el fichero que se haya guardado si existe y carga todos los atributos y datos
	 * en el nuevo puntero de la apliacion.
	 *
	 * @return true si se han cargado exitosamente
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
	
	/* Getters y setters */
	
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









