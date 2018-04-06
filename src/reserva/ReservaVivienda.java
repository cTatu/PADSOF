package reserva;

import oferta.Oferta;

/**
 * Clase heredada para poder instanciar las reservar de tipo vivienda
 * @author David Pascual y Cristian Tatu
 */
public class ReservaVivienda extends Reserva {

	/**
	 * Constructor
	 *
	 * @param fechaInicio 
	 */
	public ReservaVivienda(Oferta oferta) {
		super(oferta);
	}
	
	@Override
	public int compareTo(Reserva reserva) {
		return super.fechaReserva.compareTo(reserva.fechaReserva);
	}

}
