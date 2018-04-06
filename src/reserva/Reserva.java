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

// TODO: Auto-generated Javadoc
/**
 * Clase que sirve para manejar las reservas.
 *
 * @author David Pascual y Cristian Tatu
 */

public abstract class Reserva implements Comparable<Reserva>, Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8811063928953223555L;
	
	/** The fecha reserva. */
	protected LocalDate fechaReserva;
	
	/** The oferta reservada. */
	private Oferta ofertaReservada;
	
	/**
	 * Constructor.
	 *
	 * @param ofertaReservada the oferta reservada
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
	
	/* (non-Javadoc)
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
	 * Contratar.
	 *
	 * @param demandante the demandante
	 * @return boolean
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
