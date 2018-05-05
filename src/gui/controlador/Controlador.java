package gui.controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.InmaculadApp;
import busqueda.Busqueda;
import busqueda.BusquedaVacacional;
import busqueda.BusquedaVivienda;
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
	
	public Controlador(Gui gui, InmaculadApp app) {
		this.gui = gui; this.app = app;
	}
	
	public void login(String NIF, String password) {
		this.gui.loginResult( this.app.iniciarSesion( NIF, password ) );
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
	
	public void cerrarSesion(boolean GuardarNoGuardar) {
		this.gui.cerrarSesionResult( this.app.cerrarSesion( true ));
	}

	public void buscar(BusquedaVacacional criterios) {
		//this.gui.		
	}
	
	public boolean aniadirOfertaVivienda(Double precio, LocalDate fechaInicio, 
			String descripcion, Integer duracionMeses, Integer ID, Double fianza) {
		
		return this.app.aniadirOfertaVivienda(precio, fechaInicio, descripcion, duracionMeses, ID, fianza);
	}
	
	public boolean aniadirOfertaVacacional(Double precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin, Integer ID) {
		
		return this.app.aniadirOfertaVacacional(precio, fechaInicio, descripcion, fechaFin, ID);
	}
	
	public void buscar(Integer codigoPostal, LocalDate fechaInicio1, LocalDate fechaInicio2, LocalDate fechaFin,
			Integer duracionMeses, String tipoDisponibilidad, String tipoOfertaString, Double valoracion) {
		Busqueda criteriosBusqueda;
		TipoDisponibilidad tipoDisp;
		TipoOferta tipoOferta;
		
		if (tipoDisponibilidad.equals("DISPONIBLE"))
			tipoDisp = TipoDisponibilidad.DISPONIBLE;
		else if (tipoDisponibilidad.equals("CONTRATADO"))
			tipoDisp = TipoDisponibilidad.CONTRATADO;
		else
			tipoDisp = TipoDisponibilidad.RESERVADO;
		
		if (tipoOfertaString.equals("vacacional"))
			tipoOferta = TipoOferta.VACACIONAL;
		else
			tipoOferta = TipoOferta.VIVIENDA;
		
		if (tipoOferta.equals(TipoOferta.VACACIONAL))
			criteriosBusqueda = new BusquedaVacacional(codigoPostal, valoracion, fechaInicio1, fechaInicio2, tipoDisp, fechaFin);
		else
			criteriosBusqueda = new BusquedaVivienda(codigoPostal, valoracion, fechaInicio1, fechaInicio2, tipoDisp, duracionMeses);
		
		for (Oferta oferta : app.buscarOfertas(criteriosBusqueda, TipoOrdenar.FECHA)) {
			List<Object> camposOferta = new ArrayList<>();

			camposOferta.add(oferta.getFechaInicio());
			camposOferta.add(oferta.getDescripcion());
			if (tipoOferta.equals(TipoOferta.VACACIONAL))
				camposOferta.add(((OfertaVacacional) oferta).getFechaFin());
			else
				camposOferta.add(((OfertaVivienda) oferta).getDuracionMeses());
			camposOferta.add(oferta.getPrecio());
			
			this.gui.addOfertaTablaBusqueda(camposOferta.toArray());
		}
	}

}
