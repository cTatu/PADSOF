package reserva;

import oferta.Oferta;

/**
 * Clase heredada para poder instanciar las reservar de tipo vacacional
 * @author David Pascual y Cristian Tatu
 */
public class ReservaVacacional extends Reserva {

	/**
	 * Constructor
	 *
	 * @param fechaInicio 
	 */
	public ReservaVacacional(Oferta oferta) {
		super(oferta);
	}

	@Override
	public int compareTo(Reserva reserva) {
		return super.fechaReserva.compareTo(reserva.fechaReserva);
	}

}
