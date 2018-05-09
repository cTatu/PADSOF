/*
 * @author David Pascual y Cristian Tatu
 */
package gui.controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.tree.DefaultMutableTreeNode;

import app.InmaculadApp;
import busqueda.Busqueda;
import busqueda.BusquedaVacacional;
import busqueda.BusquedaVivienda;
import cliente.Cliente;
import gui.vista.Gui;
import inmueble.Inmueble;
import oferta.Oferta;
import oferta.OfertaVacacional;
import oferta.OfertaVivienda;
import opinion.Comentario;
import opinion.Opinion;
import tipos.TipoDisponibilidad;
import tipos.TipoOferta;
import tipos.TipoOrdenar;


public class Controlador {
	
	private Gui gui;
	private InmaculadApp app;
	private Map<Integer, Oferta> ofertasTabla;
	private Map<Integer, Inmueble> inmueblesTabla;
	private Oferta ofertaSeleccionada;
	private Inmueble inmuebleSeleccionado;
	private boolean isPublicandoOferta;
	
	/**
	 * Instantiates a new controlador.
	 *
	 * @param gui
	 *            the gui
	 * @param app
	 *            the app
	 */
	public Controlador(Gui gui, InmaculadApp app) {
		this.gui = gui; this.app = app;
	}
	
	/**
	 * Login.
	 *
	 * @param NIF
	 *            the nif
	 * @param password
	 *            the password
	 */
	public void login(String NIF, String password) {
		this.gui.loginResult( this.app.iniciarSesion( NIF, password ) );
	}
	
	
	/**
	 * Cerrar sesion.
	 *
	 * @param GuardarNoGuardar
	 *            the guardar no guardar
	 */
	public void cerrarSesion(boolean GuardarNoGuardar) {
		this.gui.cerrarSesionResult( this.app.cerrarSesion( GuardarNoGuardar ));
	}
	
	/**
	 * Rellenar mis inmuebles.
	 *
	 * @return true, if successful
	 */
	public boolean rellenarMisInmuebles() {
		List<Inmueble> inmuebles = this.app.getInmueblesOfertante();
		inmueblesTabla = new HashMap<>();
		
		if (inmuebles.isEmpty())
			return false;
		
		inmueblesTabla = IntStream.range(0, inmuebles.size()).boxed().collect(Collectors.toMap(Function.identity(), inmuebles::get));
	
		for (Inmueble inmueble : inmuebles) {
			List<Object> listaInmuebles = new ArrayList<>();
				listaInmuebles.add(inmueble.getCodigoPostal());
				listaInmuebles.add(inmueble.getLocalizacion());
			this.gui.addMisInmuebles(listaInmuebles.toArray());
		}
		return true;
	}
	
	/**
	 * Gets the info inmueble.
	 *
	 * @param inmueble
	 *            the inmueble
	 * @return the info inmueble
	 */
	private List<Object> getInfoInmueble(Inmueble inmueble){
		List<Object> lista = new ArrayList<>();
			lista.add(inmueble.getCodigoPostal());
			lista.add(inmueble.getLocalizacion());
			inmueble.getCaracteristicas().forEach((c, v) -> {lista.add(c); lista.add(v);});
		return lista;
	}
	
	/**
	 * Show info inmueble.
	 *
	 * @param fila
	 *            the fila
	 */
	public void showInfoInmueble(int fila) {
		inmuebleSeleccionado = inmueblesTabla.get(fila);

		if (inmuebleSeleccionado == null)
			return;

		this.gui.showInfoInmueble(getInfoInmueble(inmuebleSeleccionado).toArray());
	}

	/**
	 * Aniadir oferta vivienda.
	 *
	 * @param precio
	 *            the precio
	 * @param fechaInicio
	 *            the fecha inicio
	 * @param descripcion
	 *            the descripcion
	 * @param duracionMeses
	 *            the duracion meses
	 * @param fianza
	 *            the fianza
	 * @return true, if successful
	 */
	public boolean aniadirOfertaVivienda(Double precio, LocalDate fechaInicio, 
			String descripcion, Integer duracionMeses, Double fianza) {
		
		return this.app.aniadirOfertaVivienda(precio, fechaInicio, descripcion, duracionMeses, inmuebleSeleccionado.getID(), fianza);
	}
	
	/**
	 * Aniadir oferta vacacional.
	 *
	 * @param precio
	 *            the precio
	 * @param fechaInicio
	 *            the fecha inicio
	 * @param descripcion
	 *            the descripcion
	 * @param fechaFin
	 *            the fecha fin
	 * @return true, if successful
	 */
	public boolean aniadirOfertaVacacional(Double precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin) {
		
		return this.app.aniadirOfertaVacacional(precio, fechaInicio, descripcion, fechaFin, inmuebleSeleccionado.getID());
	}
	
	/**
	 * Aniadir inmueble.
	 *
	 * @param CP
	 *            the cp
	 * @param localizacion
	 *            the localizacion
	 * @param caracteristicas
	 *            the caracteristicas
	 */
	public void aniadirInmueble(int CP, String localizacion, Map<String,String> caracteristicas) {
		
		this.gui.aniadirInmuebleResult( this.app.crearInmueble(CP, localizacion, caracteristicas));
	}
	
	/**
	 * ***********************QUITAR******************.
	 */
	
	public void addTodasOfertas() {
		ofertasTabla = new HashMap<>();
		
		int i = 0;
		for (Oferta oferta : app.getOfertas()) {
			List<Object> camposOferta = new ArrayList<>();

			camposOferta.add(oferta.getFechaInicio());
			camposOferta.add(oferta.getDescripcion());
			if (oferta.isVacacional())
				camposOferta.add(((OfertaVacacional) oferta).getFechaFin());
			else
				camposOferta.add(((OfertaVivienda) oferta).getDuracionMeses());
			camposOferta.add(oferta.getPrecio());
			
			ofertasTabla.put(i, oferta);
			i++;
			this.gui.addOfertaTablaBusqueda(camposOferta.toArray());
		}	
	}
	
	/**
	 * ***********************QUITAR******************.
	 *
	 * @param criteriosBusqueda
	 *            the criterios busqueda
	 */
	
	public void buscarCriterios(Busqueda criteriosBusqueda) {
		ofertasTabla = new HashMap<>();

		int i = 0;
		for (Oferta oferta : app.buscarOfertas(criteriosBusqueda, TipoOrdenar.FECHA)) {
			List<Object> camposOferta = new ArrayList<>();

			camposOferta.add(oferta.getFechaInicio());
			camposOferta.add(oferta.getDescripcion());
			if (oferta.isVacacional())
				camposOferta.add(((OfertaVacacional) oferta).getFechaFin());
			else
				camposOferta.add(((OfertaVivienda) oferta).getDuracionMeses());
			camposOferta.add(oferta.getPrecio());
			
			ofertasTabla.put(i, oferta);
			i++;
			this.gui.addOfertaTablaBusqueda(camposOferta.toArray());
		}
		if (i == 0)
			this.gui.mensajeInfo("La busqueda no ha tenido ningun resultado", "Busqueda Fallida", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Buscar.
	 *
	 * @param codigoPostal
	 *            the codigo postal
	 * @param fechaInicio1
	 *            the fecha inicio 1
	 * @param fechaInicio2
	 *            the fecha inicio 2
	 * @param fechaFin
	 *            the fecha fin
	 * @param duracionMeses
	 *            the duracion meses
	 * @param tipoDisponibilidad
	 *            the tipo disponibilidad
	 * @param tipoOferta
	 *            the tipo oferta
	 * @param valoracion
	 *            the valoracion
	 */
	public void buscar(Integer codigoPostal, String fechaInicio1, String fechaInicio2, String fechaFin,
			Integer duracionMeses, Object tipoDisponibilidad, Object tipoOferta, String valoracion) {
		LocalDate fecha1 = null, fecha2 = null, fechafin = null;
		String fechaError = null;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		if (tipoDisponibilidad == null)
			tipoDisponibilidad = "DISPONIBLE";
		if (valoracion == null ||valoracion.isEmpty())
			valoracion = "0.0";
		
    	try {
    		fechaError = "Fecha Inicio 1";
    		fecha1 = LocalDate.parse(fechaInicio1, format);
    		fechaError = "Fecha Inicio 2";
    		fecha2 = LocalDate.parse(fechaInicio2, format);
    		fechaError = "Fecha Fin";
    		if (fechaFin != null)
    			fechafin = LocalDate.parse(fechaFin, format);
    	}catch (DateTimeParseException exception) {
    		this.gui.mensajeInfo(fechaError + " no tienen un formato valido!", "Fecha Incorrecta", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
		
		if (TipoOferta.parseString(String.valueOf(tipoOferta)).equals(TipoOferta.VACACIONAL))
			buscarCriterios(new BusquedaVacacional(codigoPostal, Double.parseDouble(valoracion), fecha1, fecha2, 
				TipoDisponibilidad.parseString(String.valueOf(tipoDisponibilidad)), fechafin));
		else
			buscarCriterios(new BusquedaVivienda(codigoPostal, Double.parseDouble(valoracion), fecha1, fecha2, 
					TipoDisponibilidad.parseString(String.valueOf(tipoDisponibilidad)), duracionMeses));
	}
	
	/**
	 * Rellenar tabla tarjetas.
	 */
	public void rellenarTablaTarjetas() {
		for (Cliente cliente : this.app.getClientes()) {
			List<Object> listaTarjetas = new ArrayList<>();
				listaTarjetas.add(cliente.getNIF());
				listaTarjetas.add(cliente.getNombres());
				listaTarjetas.add(cliente.getTarjetaCredito());
			this.gui.addTarjetasTablaGerente(listaTarjetas.toArray());
		}
	}
	

	/**
	 * Rellenar mis ofertas.
	 *
	 * @return true, if successful
	 */
	public boolean rellenarMisOfertas() {
		ofertasTabla = new HashMap<>();
		List<Oferta> ofertas;
		if (gui.getPanelActivo().isDemandante())
			ofertas = app.getOfertasDemandante();
		else
			ofertas = app.getOfertasOfertante();
		
		if (ofertas.isEmpty())
			return false;
		
		ofertasTabla = IntStream.range(0, ofertas.size()).boxed().collect(Collectors.toMap(Function.identity(), ofertas::get));

		for (Oferta oferta : ofertas) {
			List<Object> misOfertas = new ArrayList<>();
				misOfertas.add(oferta.getFechaInicio());
				misOfertas.add(oferta.getPrecio());
				misOfertas.add(oferta.getTipo());
				misOfertas.add(oferta.getDisponibilidad());
			if (gui.getPanelActivo().isDemandante())
				this.gui.addMisOfertasDemandante(misOfertas.toArray());
			else
				this.gui.addMisOfertasOfertante(misOfertas.toArray());
		}
		
		return true;
	}
	
	/**
	 * Rellenar tabla ofertas pendientes.
	 */
	public void rellenarTablaOfertasPendientes() {
		List<Oferta> ofPendientes = this.app.getOfertasPendientes();
		
		ofertasTabla = IntStream.range(0, ofPendientes.size()).boxed().collect(Collectors.toMap(Function.identity(), ofPendientes::get));
	
		for (Oferta oferta : ofPendientes) {
			List<Object> listaOfertaPendientes = new ArrayList<>();
				listaOfertaPendientes.add(oferta.getOfertante().getNombres());
				listaOfertaPendientes.add(oferta.getFechaInicio());
				listaOfertaPendientes.add(oferta.getPrecio());
				listaOfertaPendientes.add(oferta.getTipo());
			this.gui.addOfertasTablaGerente(listaOfertaPendientes.toArray());
		}
	}
	
	/**
	 * Gets the info oferta inmueble.
	 *
	 * @param ofertaSeleccionada
	 *            the oferta seleccionada
	 * @return the info oferta inmueble
	 */
	private List<Object> getInfoOfertaInmueble(Oferta ofertaSeleccionada){
		List<Object> lista = new ArrayList<>();
		Inmueble inmueble = app.getInmuebleByOferta(ofertaSeleccionada);
			lista.add(inmueble.getCodigoPostal());
			lista.add(inmueble.getLocalizacion());
			inmueble.getCaracteristicas().forEach((c, v) -> {lista.add(c); lista.add(v);});
		return lista;
	}
	
	/**
	 * Gets the info oferta cliente.
	 *
	 * @param ofertaSeleccionada
	 *            the oferta seleccionada
	 * @return the info oferta cliente
	 */
	private List<Object> getInfoOfertaCliente(Oferta ofertaSeleccionada){
		List<Object> lista = new ArrayList<>();
			lista.add(ofertaSeleccionada.getOfertante().getNIF());
			lista.add(ofertaSeleccionada.getOfertante().getNombres());
		return lista;
	}
	
	/**
	 * Show info oferta.
	 *
	 * @param fila
	 *            the fila
	 */
	public void showInfoOferta(int fila) {
		ofertaSeleccionada = ofertasTabla.get(fila);
		Object[] detallesExtra = null;
		String atributoUnico;
		
		if (ofertaSeleccionada == null)
			return;
		
		if (app.isGerente())
			detallesExtra = getInfoOfertaCliente(ofertaSeleccionada).toArray();
		else
			detallesExtra = getInfoOfertaInmueble(ofertaSeleccionada).toArray();
		
		List<Object> detallesOferta = new ArrayList<>();
			detallesOferta.add(ofertaSeleccionada.getFechaInicio());
			if (ofertaSeleccionada.isVacacional()) {
				detallesOferta.add(((OfertaVacacional)ofertaSeleccionada).getFechaFin());
				atributoUnico = "Fecha Fin";
			}else {
				detallesOferta.add(((OfertaVivienda)ofertaSeleccionada).getDuracionMeses());
				atributoUnico = "Duracion Meses";
			}
			detallesOferta.add(ofertaSeleccionada.getPrecio());
			detallesOferta.add(ofertaSeleccionada.getTipo().toString());
			detallesOferta.add(ofertaSeleccionada.getDescripcion());
		this.gui.showInfoOferta(atributoUnico, detallesExtra, detallesOferta.toArray());
	}
	
	/**
	 * Show info comentario.
	 *
	 * @param comentario
	 *            the comentario
	 */
	private void showInfoComentario(Comentario comentario) {
		gui.showInfoComentario(new Object[] {comentario.getTexto(), comentario.calcularMedia(), comentario.getID()});
	}
	
	/**
	 * Show comentarios oferta.
	 */
	public void showComentariosOferta() {
		List<Comentario> comentarios = ofertaSeleccionada.getComentarios();
		if (comentarios.isEmpty()) {
			gui.comentarioOfertaResult(false);
			return;
		}
		
		comentarios.forEach(this::showInfoComentario);
		gui.comentarioOfertaResult(true);
	}
	
	/**
	 * Adds the comentario.
	 *
	 * @param comentarioID
	 *            the comentario ID
	 * @param textoComentario
	 *            the texto comentario
	 */
	public void addComentario(Integer comentarioID, String textoComentario) {
		if (comentarioID.equals(-1))
			app.opinar(ofertaSeleccionada, textoComentario);
		else {
			Comentario comentario = app.getTodosComentarios().get(comentarioID);
			app.opinar(comentario, textoComentario);
		}
	}
	
	/**
	 * Adds the comentario.
	 *
	 * @param comentarioID
	 *            the comentario ID
	 * @param textoRespuesta
	 *            the texto respuesta
	 * @param textoValoracion
	 *            the texto valoracion
	 */
	public void addComentario(Integer comentarioID, String textoRespuesta, String textoValoracion) {
		if (!textoRespuesta.isEmpty()) {
			addComentario(comentarioID, textoRespuesta);
		}
		
		if (!textoValoracion.isEmpty()) {
			Double valoracion;
			try {
				valoracion = Double.parseDouble(textoValoracion);
			}catch (NumberFormatException e) {
				gui.mensajeInfo("La valoracion introducida no es valida", "Formato valoracion invalida", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Comentario comentario = app.getTodosComentarios().get(comentarioID);
			app.opinar(comentario, valoracion);
		}
		if (textoRespuesta.isEmpty() && textoValoracion.isEmpty()) {
			gui.mensajeInfo("Debes rellenar al menos un campo para opinar.", "Campos Vacios", JOptionPane.ERROR_MESSAGE);
			return;
		}
		gui.mensajeInfo("Tu opinion ha sido registrada correctamente.", "Opinion enviada", JOptionPane.INFORMATION_MESSAGE);
		showComentariosOferta();
	}
	
	/**
	 * Show sub comentarios.
	 *
	 * @param ID
	 *            the id
	 */
	public void showSubComentarios(Integer ID) {
		if (ID.equals(-1)) {
			showComentariosOferta();
			return;
		}
		
		List<Comentario> comentarios = app.getTodosComentarios().get(ID).getComentarios();
		if (comentarios.isEmpty()) {
			gui.subComentariosResult(false);
			return;
		}
		comentarios.forEach(this::showInfoComentario);
		gui.subComentariosResult(true);
	}

	/**
	 * Cambiar tarjeta.
	 *
	 * @param usuarioNIF
	 *            the usuario NIF
	 * @param nuevaTarjeta
	 *            the nueva tarjeta
	 */
	public void cambiarTarjeta(String usuarioNIF, String nuevaTarjeta) {
		this.gui.cambiarTarjetaResult(this.app.modificarTarjetaCredito(usuarioNIF, nuevaTarjeta), nuevaTarjeta);
	}


	/**
	 * Aceptar oferta.
	 *
	 * @param aceptar
	 *            the aceptar
	 */
	public void aceptarOferta(boolean aceptar) {
		if(aceptar)
			this.gui.moderarStatus(this.app.aprobarOferta(ofertaSeleccionada));
		else
			this.gui.moderarStatus(this.app.rechazarOferta(ofertaSeleccionada));
	}
	
	/**
	 * Check rectificaciones.
	 */
	public void checkRectificaciones() {
		
		for (Oferta oferta : app.getOfertasOfertante()) {
			if (oferta.tieneRectificaciones())
				gui.mensajeInfo("En la oferta " + oferta.GET+ " tiene una nueva rectificacion.", "Oferta con rectidicacion", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	/**
	 * Enviar rectificacion.
	 *
	 * @param rectificaciones
	 *            the rectificaciones
	 */
	public void enviarRectificacion(Map<String, String> rectificaciones) {
		BiPredicate<String, String> vacio = (key,valor)-> key.isEmpty() && valor.isEmpty();
		rectificaciones.forEach(new BiConsumer<String, String>() {
			public void accept(String t, String u) {
				if (vacio.test(t, u)) {
					gui.mensajeInfo("Los campos de rectificacion no pueden estar vacios", "Campos Vacios", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		this.gui.moderarStatus(this.app.addRectificacion(ofertaSeleccionada, rectificaciones));
	}

	/**
	 * Checks if is gerente.
	 *
	 * @return true, if is gerente
	 */
	public boolean isGerente() {
		if(this.app.getClienteConectado().gerente)
			return true;
		
		return false;
	}
	
	/**
	 * Checks if is ofertante.
	 *
	 * @return true, if is ofertante
	 */
	public boolean isOfertante() {
		return this.app.getClienteConectado().isOfertante();
	}
	
	/**
	 * Checks if is cliente dual.
	 *
	 * @return true, if is cliente dual
	 */
	public boolean isClienteDual() {
		return ( this.app.getClienteConectado().isDemandante() &&  this.app.getClienteConectado().isOfertante());
	}
	
	/**
	 * Checks if is demandante.
	 *
	 * @return true, if is demandante
	 */
	public boolean isDemandante() {
		return this.app.getClienteConectado().isDemandante();
	}

	/**
	 * Checks if is oferta reservable.
	 *
	 * @return true, if is oferta reservable
	 */
	public boolean isOfertaReservable() {
		return this.app.sePuedeReservar(ofertaSeleccionada);
	}
	
	/**
	 * Checks if is oferta contratable.
	 *
	 * @return true, if is oferta contratable
	 */
	public boolean isOfertaContratable() {
		return this.app.sePuedeContratar(ofertaSeleccionada);
	}

	/**
	 * Contratar oferta.
	 */
	public void contratarOferta() {
		gui.contratarResult(this.app.contratarOferta(ofertaSeleccionada));
	}

	/**
	 * Reservar oferta.
	 */
	public void reservarOferta() {
		gui.reservarResult(this.app.reservarOferta(ofertaSeleccionada));
	}

	/**
	 * Sets the publicar oferta.
	 *
	 * @param b
	 *            the new publicar oferta
	 */
	public void setPublicarOferta(boolean b) {
		
		this.isPublicandoOferta = b;
	}

	/**
	 * Checks if is publicando oferta.
	 *
	 * @return true, if is publicando oferta
	 */
	public boolean isPublicandoOferta() {
		return isPublicandoOferta;
	}

	/**
	 * Sets the inmueble seleccionado.
	 *
	 * @param fila
	 *            the new inmueble seleccionado
	 */
	public void setInmuebleSeleccionado(int fila) {
		this.inmuebleSeleccionado = inmueblesTabla.get(fila);
	}


}
