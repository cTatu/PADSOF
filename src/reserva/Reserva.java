/*
 * @author David Pascual y Cristian Tatu
 */
package reserva;

import java.io.Serializable;
import java.time.LocalDate;

import cliente.Cliente;
import fechasimulada.FechaSimulada;
import oferta.Oferta;
import tipos.TipoOferta;

/**
 * Clase que sirve para manejar las reservas.
 */

public abstract class Reserva implements Comparable<Reserva>, Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8811063928953223555L;
	
	protected LocalDate fechaReserva;
	private Oferta ofertaReservada;
	
	/**
	 * Constructor.
	 *
	 * @param ofertaReservada
	 */
	public Reserva(Oferta ofertaReservada) {
		this.fechaReserva = FechaSimulada.getHoy(); 
		this.ofertaReservada = ofertaReservada;
	}
	
	/**
	 * Estado de la expiración de la resera.
	 *
	 * @return boolean
	 */
	public boolean expirada() {
		return FechaSimulada.getHoy().isAfter(fechaReserva.plusDays(5));
	}	
	
	/**
	 * Override de equals para comparar reservas
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		Reserva r = (Reserva) o;
		
		return r.ofertaReservada == this.ofertaReservada;
	}
	
	/**
	 * Sets the fecha reserva.
	 *
	 * @param fechaReserva the fechaReserva to set
	 */
	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	/**
	 * Contrata una reserva.
	 *
	 * @param demandante de la reserva
	 * @return true si fue exitoso
	 */
	public boolean contratar(Cliente demandante) {
		return ofertaReservada.contratar(demandante);
	}
	
	/**
	 * Gets the oferta.
	 *
	 * @return oferta reservada
	 */
	public Oferta getOferta() {
		return ofertaReservada;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public TipoOferta getTipo() {
		if (this instanceof ReservaVacacional)
			return TipoOferta.VACACIONAL;
		else 
			return TipoOferta.VIVIENDA;
	}
}
