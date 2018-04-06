package reserva;

import java.time.LocalDate;

import cliente.Cliente;
import fechasimulada.FechaSimulada;
import oferta.Oferta;

/**
 * Clase que sirve para manejar las reservas
 * @author David Pascual y Cristian Tatu
 */

public abstract class Reserva implements Comparable<Reserva>{
	private LocalDate fechaInicio, fechaReserva;
	private Oferta ofertaReservada;
	/**
	 * Constructor
	 *
	 * @param fechaInicio 
	 */
	public Reserva(Oferta ofertaReservada) {
		this.fechaInicio = ofertaReservada.getFechaInicio();
		this.fechaReserva = FechaSimulada.getHoy(); 
		this.ofertaReservada = ofertaReservada;
	}
	
	/**
	 * Estado de la expiración de la resera
	 *
	 * @return boolean
	 */
	public boolean expirada() {
		if (FechaSimulada.getHoy().isAfter(fechaReserva.plusDays(5)))
			return true;
		
		return false;
	}	
	
	public int compareTo(Reserva reserva) {
		if (reserva.ofertaReservada.equals(this.ofertaReservada))
			return 0;
		return this.fechaInicio.compareTo(reserva.fechaInicio);
	}
	
	/**
	 * @return boolean
	 */
	public boolean contratar(Cliente demandante) {
		return ofertaReservada.contratar(demandante);
	}
	
	/**
	 * @return oferta reservada
	 */
	public Oferta getOferta() {
		return ofertaReservada;
	}
	
}
