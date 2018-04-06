/*
 * @author David Pascual y Cristian Tatu
 */
package reserva;

import java.io.Serializable;

import oferta.Oferta;

/**
 * Clase heredada para poder instanciar las reservas de tipo vacacional.
 *
 * @author David Pascual y Cristian Tatu
 */
public class ReservaVacacional extends Reserva implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4145931949347555616L;

	/**
	 * Constructor.
	 *
	 * @param oferta
	 */
	public ReservaVacacional(Oferta oferta) {
		super(oferta);
	}

	/**
	 * comparable override implementado
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Reserva reserva) {
		return super.fechaReserva.compareTo(reserva.fechaReserva);
	}

}
