/*
 * @author David Pascual y Cristian Tatu
 */
package reserva;

import java.io.Serializable;

import oferta.Oferta;

// TODO: Auto-generated Javadoc
/**
 * Clase heredada para poder instanciar las reservar de tipo vivienda.
 *
 * @author David Pascual y Cristian Tatu
 */
public class ReservaVivienda extends Reserva implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4186862651014541404L;

	/**
	 * Constructor.
	 *
	 * @param oferta the oferta
	 */
	public ReservaVivienda(Oferta oferta) {
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
