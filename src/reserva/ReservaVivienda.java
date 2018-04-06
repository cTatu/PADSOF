/*
 * @author David Pascual y Cristian Tatu
 */
package reserva;

import java.io.Serializable;

import oferta.Oferta;

/**
 * Clase heredada para poder instanciar las reservas de tipo vivienda.
 */
public class ReservaVivienda extends Reserva implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4186862651014541404L;

	/**
	 * Constructor.
	 *
	 * @param oferta
	 */
	public ReservaVivienda(Oferta oferta) {
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
