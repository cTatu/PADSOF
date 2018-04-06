/*
 * @author David Pascual y Cristian Tatu
 */
package reserva;

import java.io.Serializable;

import oferta.Oferta;

// TODO: Auto-generated Javadoc
/**
 * Clase heredada para poder instanciar las reservar de tipo vacacional.
 *
 * @author David Pascual y Cristian Tatu
 */
public class ReservaVacacional extends Reserva implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4145931949347555616L;

	/**
	 * Constructor.
	 *
	 * @param oferta the oferta
	 */
	public ReservaVacacional(Oferta oferta) {
		super(oferta);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Reserva reserva) {
		return super.fechaReserva.compareTo(reserva.fechaReserva);
	}

}
