/*
 * @author David Pascual y Cristian Tatu
 */
package gui.controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

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
	 * Acciona el inicio de sesion. Si es correcto notifica
	 * al GUI para mostrar los paneles pertinentes. Si es
	 * incorrecto se mostrara un dialogo de error.
	 *
	 * @param NIF
	 *            el NIF del usuario
	 * @param password
	 *            la contrasenia
	 */
	public void login(String NIF, String password) {
		this.gui.loginResult( this.app.iniciarSesion( NIF, password ) );
	}
	
	
	/**
	 * Hace la accion de cerrar la sesion abierta por el usuario
	 * y le lleva al panel inicial de login.
	 *
	 * @param GuardarNoGuardar
	 *            Si es True el programa guarda su estado, si es Falso no.
	 */
	public void cerrarSesion(boolean GuardarNoGuardar) {
		this.gui.cerrarSesionResult( this.app.cerrarSesion( GuardarNoGuardar ));
	}
	
	/**
	 * Rellenar la tabla de mis inmuebles.
	 * Con el codigo postal y la localizacion
	 *
	 * @return true si ha podido introducir al menos un elemento en la tabla. Falso si no hay inmuebles
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
	 * Recopila la informacion de un inmueble.
	 * Como el codigo postal, la localizacion y las caracteristicas.
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
	 * Muestra un panel con la informacion del inmueble selccionado.
	 *
	 * @param fila
	 *           la fila de la tabla que se ha pinchado
	 */
	public void showInfoInmueble(int fila) {
		inmuebleSeleccionado = inmueblesTabla.get(fila);

		if (inmuebleSeleccionado == null)
			return;

		this.gui.showInfoInmueble(getInfoInmueble(inmuebleSeleccionado).toArray());
	}

	/**
	 * Aniade una oferta de tipo Vivienda al sistema.
	 *
	 * @param precio
	 *            el precio
	 * @param fechaInicio
	 *            la fecha de inicio
	 * @param descripcion
	 *            la descripcion
	 * @param duracionMeses
	 *            la duracion de meses de la oferta
	 * @param fianza
	 *            la fianza
	 * @return true si se ha producido con exito, falso en caso contrario
	 */
	public boolean aniadirOfertaVivienda(Double precio, LocalDate fechaInicio, 
			String descripcion, Integer duracionMeses, Double fianza) {
		
		return this.app.aniadirOfertaVivienda(precio, fechaInicio, descripcion, duracionMeses, inmuebleSeleccionado.getID(), fianza);
	}
	
	/**
	 * Aniade una oferta de tipo Vacacional al sistema.
	 *
	 * @param precio
	 *            el precio
	 * @param fechaInicio
	 *            la fecha de inicio
	 * @param descripcion
	 *            la descripcion
	 * @param fechaFin
	 *            la fecha de fin
	 * @return true si se ha producido con exito, falso en caso contrario
	 */
	public boolean aniadirOfertaVacacional(Double precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin) {
		
		return this.app.aniadirOfertaVacacional(precio, fechaInicio, descripcion, fechaFin, inmuebleSeleccionado.getID());
	}
	
	/**
	 *  Aniade un inmueble al sistema.
	 *  Intenta parsear los parametros introducidos, si alguno fallase
	 *  mostraria un mensaje de error.
	 *
	 * @param CP
	 *           el codigo postal
	 * @param localizacion
	 *            la localizacion
	 * @param caracteristicas
	 *            el mapa de caracteristicas
	 */
	public void aniadirInmueble(String CP, String localizacion, Map<String,String> caracteristicas) {
		
		try {
		this.gui.aniadirInmuebleResult( this.app.crearInmueble(Integer.parseInt(CP), localizacion, caracteristicas));
		}catch (NumberFormatException e) {
			gui.mensajeInfo("Debes rellenar todos los campos para publicar un inmueble", "Campos vacios", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Realiza una busqueda de ofertas en el sistema con los criterios
	 * de busqueda propocionados. Puede ser de tipo Vacacional o Vivienda.
	 * Las ofertas que encuentra las aniade a la tabla de busqueda.
	 * Si no encuentra ninguna oferta lanza un dialogo mostrando la falta de resultados.
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
	 * Realiza una busqueda de ofertas en el sistema
	 * parseando los parametros de entrada y creando un objeto de criterio
	 * de busqueda. Este objeto se lo pasa a 'buscarCriterios' para
	 * introducir en la tabla los resultados.
	 * Si alguna de las fechas tiene un valor o formato erroneo
	 * o si la valoracion tiene un valor invalido
	 * muestra un mensaje de error con la fecha en cuestion.
	 * 
	 * 
	 *
	 * @param codigoPostal
	 *            el codigo postal
	 * @param fechaInicio1
	 *            el extremo bajo del rango de fechas de incio
	 * @param fechaInicio2
	 *            el extremo alto del rango de fechas de incio
	 * @param fechaFin
	 *            la fecha fin de la oferta vacacional
	 * @param duracionMeses
	 *            la duracion en meses de la oferta Vivienda
	 * @param tipoDisponibilidad
	 *            el tipo de  disponibilidad
	 * @param tipoOferta
	 *            el tipo de oferta
	 * @param valoracion
	 *            la valoracion
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
		
    	try {
		if (TipoOferta.parseString(String.valueOf(tipoOferta)).equals(TipoOferta.VACACIONAL))
			buscarCriterios(new BusquedaVacacional(codigoPostal, Double.parseDouble(valoracion), fecha1, fecha2, 
				TipoDisponibilidad.parseString(String.valueOf(tipoDisponibilidad)), fechafin));
		else
			buscarCriterios(new BusquedaVivienda(codigoPostal, Double.parseDouble(valoracion), fecha1, fecha2, 
					TipoDisponibilidad.parseString(String.valueOf(tipoDisponibilidad)), duracionMeses));
    	}catch (NumberFormatException e) {
    		this.gui.mensajeInfo("La valoracion introducida no es valida", "Valoracion invalida", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Rellenar la tabla del gerente con las tarjetas de credito de los clientes para que las pueda cambiar.
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
	 * Rellena la tabla de Mis Ofertas.
	 * Dependiendo de si el rol es de ofertante
	 * o demandante muestra las ofertas creadas o
	 * contratadas o reservadas respectivamente.
	 *
	 * @return true si el cliente tiene al menos una oferta, false de lo contrario
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
	 * Rellena la tabla de ofertas pendientes para el gerente.
	 * 
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
	 * Devuelve una lista con los detalles del inmueble
	 * al que pertenece la oferta seleccionada.
	 * Detalles como el codigo postal, la localizacion
	 * y las caracteristicas.
	 *
	 * @param ofertaSeleccionada
	 *            la oferta seleccionada de la tabla
	 * @return lista con el codigo postal, la localizacion
	 * y las caracteristicas.
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
	 * Devuelve una lista con los detalles del ofertante
	 * que creo la oferta seleccionada.
	 *
	 * @param ofertaSeleccionada
	 *            la oferta seleccionada de la tabla
	 * @return el NIF y el nombre de ofertante propietario de la oferta
	 */
	private List<Object> getInfoOfertaCliente(Oferta ofertaSeleccionada){
		List<Object> lista = new ArrayList<>();
			lista.add(ofertaSeleccionada.getOfertante().getNIF());
			lista.add(ofertaSeleccionada.getOfertante().getNombres());
		return lista;
	}
	
	/**
	 * Envia una lista de detalles con los campos de 
	 * la oferta seleccionada de una tabla en la fila proporcionada
	 * por parametro.
	 * Muestra detalles como el tipo de oferta,el precio, las fechas,
	 * la descripcion.
	 *
	 * @param fila
	 *          la fila de la tabla que se pincho
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
	 * Muestra un panel con la informacion de un comentario.
	 * La informacion es el texto del comentario y la valoracion que tiene.
	 *
	 * @param comentario
	 *            el comentario del que se quiere ver los detalles
	 */
	private void showInfoComentario(Comentario comentario) {
		gui.showInfoComentario(new Object[] {comentario.getTexto(), comentario.calcularMedia(), comentario.getID()});
	}
	
	/**
	 * Muestra todos los comentarios de una oferta.
	 * Creando de cada uno un panel disinto donde guardar sus detalles.
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
	 * Si comentarioID es negativo, aniade el comentario
	 * a la oferta seleccionada, si es positivo aniade el comentario
	 * como una respuesta al comentario que tiene ese ID.
	 *
	 * @param comentarioID
	 *            el ID del comentario al que se quiere responder. <0 si es para la oferta.
	 * @param textoComentario
	 *            el texto del nuevo comentario que se quiere aniadir
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
	 * Aniade una respuesta a un comentario y ademas valora el comentario.
	 * Los dos ultimos parametros son opcionales independientemetne, pero es obligatorio
	 * rellenar al menos uno de ellos.
	 *
	 * @param comentarioID
	 *            el ID del comentario al que se quiere opinar
	 * @param textoRespuesta
	 *            l texto del nuevo comentario que se quiere aniadir
	 * @param textoValoracion
	 *            la valoracion que se quiere dar al comentario 
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
	 * Muestra los comentarios que tiene un cometario.
	 * Si el comentario con el ID proporciondo no tiene
	 * ningun comentario se muestra un dialogo de informacion.
	 *
	 * @param ID
	 *        el ID del comentario del que se quieren ver las respuestas
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
	 * Cambia la tarjeta de credito del usuario con el NIF proporicionado
	 * por la nueva tarjeta en el segundo parametro.
	 *
	 * @param usuarioNIF
	 *            el NIF del usaurio al que se quiere cambiar la tarjeta
	 * @param nuevaTarjeta
	 *            la nueva tarjeta
	 */
	public void cambiarTarjeta(String usuarioNIF, String nuevaTarjeta) {
		this.gui.cambiarTarjetaResult(this.app.modificarTarjetaCredito(usuarioNIF, nuevaTarjeta), nuevaTarjeta);
	}


	/**
	 * Acepta o rechaza la oferta seleccionada por el gerente.
	 *
	 * @param aceptar
	 *            si es true acepta la oferta, de lo contrario la rechaza
	 */
	public void aceptarOferta(boolean aceptar) {
		if(aceptar)
			this.gui.moderarStatus(this.app.aprobarOferta(ofertaSeleccionada));
		else
			this.gui.moderarStatus(this.app.rechazarOferta(ofertaSeleccionada));
	}
	
	/**
	 * Muestra un mensaje de aviso si alguna oferta tiene una rectificacion pendiente
	 */
	public void checkRectificaciones() {
		
		for (Oferta oferta : app.getOfertasOfertante()) {
			if (oferta.tieneRectificaciones())
				gui.mensajeInfo("Hay una oferta con una nueva rectificacion.", "Oferta con rectificacion", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	/**
	 * El gerente envia una rectificacion a la oferta seleccionada.
	 *
	 * @param rectificaciones
	 *            el mapa con las rectificaciones
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
