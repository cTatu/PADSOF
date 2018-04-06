package cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import oferta.Oferta;
import reserva.Reserva;
import reserva.ReservaVacacional;
import reserva.ReservaVivienda;
import tipos.TipoOferta;

/**
 * Implementación de la clase demandate y sus funcionalidades
 * @author David Pascual y Cristian Tatu
 */
public class Demandante implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -197674966444958454L;
	private List<Oferta> ofertasContratadas;
	private ReservaVacacional rVacacional;
	private ReservaVivienda rVivienda;
	private List<Reserva> historialReservas;
	
	/**
	 * Constructor
	 */
	public Demandante() {
		ofertasContratadas = new ArrayList<>();
		historialReservas = new ArrayList<>();
	}		
	
	/**
	 * Añade una oferta que ha sido contratada por este demandante a la lista de sus ofertas constratadas
	 *
	 * @param ofertaContratada 
	 */
	public void añadirOfertaContratada(Oferta ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}
	
	public boolean puedeReservar(Reserva reserva) {
		for (Reserva reservaHistorial : historialReservas) {
			if (reserva.equals(reservaHistorial))
				return false;				
		}
		return true;
	}
	
	public boolean añadirReserva(Reserva reserva) {
		if (reserva instanceof ReservaVacacional)
			rVacacional = (ReservaVacacional) reserva;
		else
			rVivienda = (ReservaVivienda) reserva;
		
		return historialReservas.add(reserva);
	}
	
	/**
	 * Elimina la reserva en funcion del tipo
	 */
	public void eliminarReserva(TipoOferta tipo) {
		if (tipo.equals(TipoOferta.VACACIONAL))
			rVacacional = null;
		else if (tipo.equals(TipoOferta.VIVIENDA))
			rVivienda = null;			
	}
	
	/**
	 * Comprueba si tiene alguna reserva vacacional en curso
	 *
	 * @return boolean
	 */
	public boolean getStatusVacacional() { 
		if(rVacacional==null)
			return false;
		else
			return true;
	}
	
	/**
	 * Comprueba si tiene alguna reserva de vivienda en curso
	 *
	 * @return boolean
	 */
	public boolean getStatusVivienda() { 
		if(rVivienda==null)
			return false;
		else
			return true;
	}
	
	public Reserva getReserva(TipoOferta tipo) {
		if (tipo.equals(TipoOferta.VACACIONAL))
			return rVacacional;
		else
			return rVivienda;
	}

	/**
	 * Establece una nueva reserva vacional del demandante
	 *
	 * @param rVacacional 
	 */
	public void setrVacacional(ReservaVacacional rVacacional) {
		this.rVacacional = rVacacional;
	}

	/**
	 * Establece una nueva reserva de vivienda del demandante
	 *
	 * @param rAlquiler 
	 */
	public void setrVivienda(ReservaVivienda rAlquiler) {
		this.rVivienda = rAlquiler;
	}
	
	public ReservaVacacional getReservaVacacional() {
		return rVacacional;
	}
	
	public ReservaVivienda getReservaVivienda() {
		return rVivienda;
	}

	public boolean puedeContratar(Oferta oferta) {
		for (Oferta ofertaContratada : ofertasContratadas) {
			if (ofertaContratada.equals(oferta))
				return false;
		}
		return true;
	}
}
