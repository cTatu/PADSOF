package gui.controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import app.InmaculadApp;
import busqueda.Busqueda;
import busqueda.BusquedaVacacional;
import busqueda.BusquedaVivienda;
import cliente.Cliente;
import gui.vista.Gui;
import oferta.Oferta;
import oferta.OfertaVacacional;
import oferta.OfertaVivienda;
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
		this.gui.cerrarSesionResult( this.app.cerrarSesion( true ));
	}

	public boolean aniadirOfertaVivienda(Double precio, LocalDate fechaInicio, 
			String descripcion, Integer duracionMeses, Integer ID, Double fianza) {
		
		return this.app.aniadirOfertaVivienda(precio, fechaInicio, descripcion, duracionMeses, ID, fianza);
	}
	
	public boolean aniadirOfertaVacacional(Double precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin, Integer ID) {
		
		return this.app.aniadirOfertaVacacional(precio, fechaInicio, descripcion, fechaFin, ID);
	}
	
	public void buscarCriterios(Busqueda criteriosBusqueda) {
		ofertasTabla = new HashMap<>();

		int i = 0;
		for (Oferta oferta : app.buscarOfertas(criteriosBusqueda, TipoOrdenar.FECHA)) {
			List<Object> camposOferta = new ArrayList<>();

			camposOferta.add(oferta.getFechaInicio());
			camposOferta.add(oferta.getDescripcion());
			if (criteriosBusqueda.getTipoOferta().equals(TipoOferta.VACACIONAL))
				camposOferta.add(((OfertaVacacional) oferta).getFechaFin());
			else
				camposOferta.add(((OfertaVivienda) oferta).getDuracionMeses());
			camposOferta.add(oferta.getPrecio());
			
			ofertasTabla.put(i, oferta);
			this.gui.addOfertaTablaBusqueda(camposOferta.toArray());
		}
	}
	
	public void buscar(Integer codigoPostal, LocalDate fechaInicio1, LocalDate fechaInicio2, LocalDate fechaFin,
			Integer duracionMeses, String tipoDisponibilidad, String tipoOferta, Double valoracion) {
		
		if (TipoOferta.parseString(tipoOferta).equals(TipoOferta.VACACIONAL))
			buscarCriterios(new BusquedaVacacional(codigoPostal, valoracion, fechaInicio1, fechaInicio2, 
				TipoDisponibilidad.parseString(tipoDisponibilidad), fechaFin));
		else
			buscarCriterios(new BusquedaVivienda(codigoPostal, valoracion, fechaInicio1, fechaInicio2, 
					TipoDisponibilidad.parseString(tipoDisponibilidad), duracionMeses));
		
	}
	
	public void rellenarTablaTarjetas() {
		for (Cliente cliente : this.app.getClientes()) {
			List<Object> listaTarjetas = new ArrayList<>();
				listaTarjetas.add(cliente.getNIF());
				listaTarjetas.add(cliente.getNombres());
				listaTarjetas.add(cliente.getTarjetaCredito());
			this.gui.addTarjetaTabla(listaTarjetas.toArray());
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
			this.gui.addOfertaPendienteTabla(listaOfertaPendientes.toArray());
		}
	}
	
	public void showInfoOferta(int fila) {
		ofertaSeleccionada = ofertasTabla.get(fila);
		
		List<Object> detallesOferta = new ArrayList<>();
			detallesOferta.add(ofertaSeleccionada.getOfertante().getNIF());
			detallesOferta.add(ofertaSeleccionada.getOfertante().getNombres());
			detallesOferta.add(ofertaSeleccionada.getFechaInicio());
			detallesOferta.add(ofertaSeleccionada.getPrecio());
			detallesOferta.add(ofertaSeleccionada.getTipo().toString());
			detallesOferta.add(ofertaSeleccionada.getDescripcion());
		this.gui.showInfoOferta(detallesOferta.toArray());
	}

	public void cambiarTarjeta(String usuarioNIF, String nuevaTarjeta) {
		this.gui.cambiarTarjetaResult(this.app.modificarTarjetaCredito(usuarioNIF, nuevaTarjeta), nuevaTarjeta);
	}

	public boolean isGerente() {
		if( this.app.getClienteConectado().gerente ) {
			return true;
		}
		else {
			return false;
		}
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

	public void aceptarOferta(boolean b) {
		this.gui.moderarStatus(this.app.aprobarOferta(ofertaSeleccionada));
	}

	public void enviarRectificacion(Map<String, String> rectificaciones) {
		this.gui.moderarStatus(this.app.addRectificacion(ofertaSeleccionada, rectificaciones));
	}

}
