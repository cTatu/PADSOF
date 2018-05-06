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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;
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
	private Oferta ofertaSeleccionada;
	
	public Controlador(Gui gui, InmaculadApp app) {
		this.gui = gui; this.app = app;
	}
	
	public void login(String NIF, String password) {
		this.gui.loginResult( this.app.iniciarSesion( NIF, password ) );
	}
	
	
	public void cerrarSesion(boolean GuardarNoGuardar) {
		this.gui.cerrarSesionResult( this.app.cerrarSesion( GuardarNoGuardar ));
	}

	public boolean aniadirOfertaVivienda(Double precio, LocalDate fechaInicio, 
			String descripcion, Integer duracionMeses, Integer ID, Double fianza) {
		
		return this.app.aniadirOfertaVivienda(precio, fechaInicio, descripcion, duracionMeses, ID, fianza);
	}
	
	public boolean aniadirOfertaVacacional(Double precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin, Integer ID) {
		
		return this.app.aniadirOfertaVacacional(precio, fechaInicio, descripcion, fechaFin, ID);
	}
	
	public void aniadirInmueble(int CP, String localizacion, Map<String,String> caracteristicas) {
		
		this.gui.aniadirInmuebleResult( this.app.crearInmueble(CP, localizacion, caracteristicas));
	}
	/*************************QUITAR*******************/
	
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
	/*************************QUITAR*******************/
	
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
	
	public void rellenarTablaTarjetas() {
		for (Cliente cliente : this.app.getClientes()) {
			List<Object> listaTarjetas = new ArrayList<>();
				listaTarjetas.add(cliente.getNIF());
				listaTarjetas.add(cliente.getNombres());
				listaTarjetas.add(cliente.getTarjetaCredito());
			this.gui.addTarjetasTablaGerente(listaTarjetas.toArray());
		}
	}
	
	private DefaultMutableTreeNode arbol(List<Opinion> opiniones) {
		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode();
		for (Opinion opns : opiniones) {
			if (opns.isComentario()) {
				Comentario comentario = (Comentario) opns;
				nodo = new DefaultMutableTreeNode(comentario.getTexto() + comentario.calcularMedia());
				nodo.add(arbol(comentario.getOpiniones()));
			}
		}
		return nodo;
	}
	
	public DefaultMutableTreeNode getComentariosOferta() {
		return arbol(ofertaSeleccionada.getOpiniones());
	}
	
	public void rellenarMisOfertas() {
		List<Oferta> ofertas = app.getOfertasContratadas();

		for (Oferta oferta : ofertas) {
			List<Object> misOfertas = new ArrayList<>();
				misOfertas.add(oferta.getFechaInicio());
				misOfertas.add(oferta.getPrecio());
				misOfertas.add(oferta.getTipo());
				misOfertas.add(oferta.getDisponibilidad());
			this.gui.addMisOfertas(misOfertas.toArray());
		}
	}
	
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
	
	private List<Object> getInfoOfertaInmueble(Oferta ofertaSeleccionada){
		List<Object> lista = new ArrayList<>();
		Inmueble inmueble = app.getInmuebleByOferta(ofertaSeleccionada);
			lista.add(inmueble.getCodigoPostal());
			lista.add(inmueble.getLocalizacion());
			inmueble.getCaracteristicas().forEach((c, v) -> {lista.add(c); lista.add(v);});
		return lista;
	}
	
	private List<Object> getInfoOfertaCliente(Oferta ofertaSeleccionada){
		List<Object> lista = new ArrayList<>();
			lista.add(ofertaSeleccionada.getOfertante().getNIF());
			lista.add(ofertaSeleccionada.getOfertante().getNombres());
		return lista;
	}
	
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
	
	public void showInfoComentario(Object textoComentario) {
		String texto = String.valueOf(textoComentario);
		String valoracion = texto.substring(texto.length()-3, texto.length());
		
		texto = texto.substring(0, texto.length() - 3);
		
		List<Object> detallesComentario = new ArrayList<>();
			detallesComentario.add(texto);
			detallesComentario.add(valoracion);
		gui.showInfoComentario(detallesComentario.toArray());
	}

	public void cambiarTarjeta(String usuarioNIF, String nuevaTarjeta) {
		this.gui.cambiarTarjetaResult(this.app.modificarTarjetaCredito(usuarioNIF, nuevaTarjeta), nuevaTarjeta);
	}


	public void aceptarOferta(boolean aceptar) {
		if(aceptar)
			this.gui.moderarStatus(this.app.aprobarOferta(ofertaSeleccionada));
		else
			this.gui.moderarStatus(this.app.rechazarOferta(ofertaSeleccionada));
	}

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

	public boolean isGerente() {
		if(this.app.getClienteConectado().gerente)
			return true;
		
		return false;
	}
	
	public boolean isClienteDual() {
		if( this.app.getClienteConectado().isDemandante() && this.app.getClienteConectado().isOfertante() ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isDemandante() {
		if( this.app.getClienteConectado().isDemandante() ) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isOfertaReservable() {
		return this.app.sePuedeReservar(ofertaSeleccionada);
	}

	public void contratarOferta() {
		gui.contratarResult(this.app.contratarOferta(ofertaSeleccionada));
	}

	public void reservarOferta() {
		gui.reservarResult(this.app.reservarOferta(ofertaSeleccionada));
	}

}
