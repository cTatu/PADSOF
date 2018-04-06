/*
 * @author David Pascual y Cristian Tatu
 */
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
 * Implementaci�n de la clase demandate y sus funcionalidades.
 */
public class Demandante implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -197674966444958454L;
	
	private List<Oferta> ofertasContratadas;
	private ReservaVacacional rVacacional;
	private ReservaVivienda rVivienda;
	private List<Reserva> historialReservas;
	
	/**
	 * Constructor.
	 */
	public Demandante() {
		ofertasContratadas = new ArrayList<>();
		historialReservas = new ArrayList<>();
	}		
	
	/**
	 * A�ade una oferta que ha sido contratada por este demandante a la lista de sus ofertas constratadas.
	 *
	 * @param ofertaContratada a a�adir
	 */
	public void a�adirOfertaContratada(Oferta ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}
	
	/**
	 * Sirve para comprobar que una oferta no fuese previamente contratada
	 *
	 * @param oferta 
	 * @return true si no fue contratada antes, false de lo contrario
	 */
	public boolean puedeContratar(Oferta oferta) {
		for (Oferta ofertaContratada : ofertasContratadas) {
			if (ofertaContratada.equals(oferta))
				return false;
		}
		return true;
	}
	
	/**
	 * A�adir reserva.
	 *
	 * @param reserva a a�adir
	 * @return true si se realiza satifactoriamente, false de lo contario o si no ha pasado los controles
	 */
	public boolean a�adirReserva(Reserva reserva) {
		if (!puedeReservar(reserva))
			return false;
		
		if (reserva instanceof ReservaVacacional)
			rVacacional = (ReservaVacacional) reserva;
		else
			rVivienda = (ReservaVivienda) reserva;
		
		if (!historialReservas.contains(reserva))
			return historialReservas.add(reserva);
		return false;
	}
	
	/**
	 * Sirve para comprobar que una oferta no fuese previamente reservada
	 *
	 * @param reserva a comprobar
	 * @return true si no fue reservada antes, false de lo contrario
	 */
	public boolean puedeReservar(Reserva reserva) {
		for (Reserva reservaHistorial : historialReservas) {
			if (reserva.equals(reservaHistorial))
				return false;				
		}
		return true;
	}
	
	/**
	 * Elimina la reserva en funcion del tipo.
	 *
	 * @param tipo 
	 * @return true si se elimina, false de lo contrario
	 */
	public boolean eliminarReserva(TipoOferta tipo) {
		if (tipo.equals(TipoOferta.VACACIONAL))
			rVacacional = null;
		else if (tipo.equals(TipoOferta.VIVIENDA))
			rVivienda = null;	
		
		return true;
	}
	
	
	/**
	 * Comprueba si tiene alguna reserva vacacional en curso.
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
	 * Comprueba si tiene alguna reserva de vivienda en curso.
	 *
	 * @return boolean
	 */
	public boolean getStatusVivienda() { 
		if(rVivienda==null)
			return false;
		else
			return true;
	}
	
	/**
	 * Gets the reserva.
	 *
	 * @param tipo 
	 * @return the reserva
	 */
	public Reserva getReserva(TipoOferta tipo) {
		if (tipo.equals(TipoOferta.VACACIONAL))
			return rVacacional;
		else
			return rVivienda;
	}

	/**
	 * Establece una nueva reserva vacional del demandante.
	 *
	 * @param nueva reserva
	 */
	public void setrVacacional(ReservaVacacional rVacacional) {
		this.rVacacional = rVacacional;
	}

	/**
	 * Establece una nueva reserva de vivienda del demandante.
	 *
	 * @param nueva reseva
	 */
	public void setrVivienda(ReservaVivienda rAlquiler) {
		this.rVivienda = rAlquiler;
	}
	
	/**
	 * Gets the reserva vacacional.
	 *
	 * @return the reserva vacacional
	 */
	public ReservaVacacional getReservaVacacional() {
		return rVacacional;
	}
	
	/**
	 * Gets the reserva vivienda.
	 *
	 * @return the reserva vivienda
	 */
	public ReservaVivienda getReservaVivienda() {
		return rVivienda;
	}

	/**
	 * Gets the ofertas contratadas.
	 *
	 * @return the ofertasContratadas
	 */
	public List<Oferta> getOfertasContratadas() {
		return ofertasContratadas;
	}

	/**
	 * Gets the historial reservas.
	 *
	 * @return the historialReservas
	 */
	public List<Reserva> getHistorialReservas() {
		return historialReservas;
	}

	/**
	 * Gets the reserva vacacional.
	 *
	 * @return the rVacacional
	 */
	public ReservaVacacional getrVacacional() {
		return rVacacional;
	}

	/**
	 * Gets the r vivienda.
	 *
	 * @return the rVivienda
	 */
	public ReservaVivienda getrVivienda() {
		return rVivienda;
	}
}
