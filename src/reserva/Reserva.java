package reserva;

import java.time.LocalDate;

import cliente.Cliente;
import fechasimulada.FechaSimulada;
import oferta.Oferta;
import tipos.TipoOferta;

/**
 * Clase que sirve para manejar las reservas
 * @author David Pascual y Cristian Tatu
 */

public abstract class Reserva implements Comparable<Reserva>{
	protected LocalDate fechaReserva;
	private Oferta ofertaReservada;
	/**
	 * Constructor
	 *
	 * @param fechaInicio 
	 */
	public Reserva(Oferta ofertaReservada) {
		this.fechaReserva = FechaSimulada.getHoy(); 
		this.ofertaReservada = ofertaReservada;
	}
	
	/**
	 * Estado de la expiración de la resera
	 *
	 * @return boolean
	 */
	public boolean expirada() {
		return FechaSimulada.getHoy().isAfter(fechaReserva.plusDays(5));
	}	
	
	@Override
	public boolean equals(Object o) {
		Reserva r = (Reserva) o;
		
		return r.ofertaReservada == this.ofertaReservada;
	}
	
	/**
	 * @param fechaReserva the fechaReserva to set
	 */
	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
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

	public TipoOferta getTipo() {
		if (this instanceof ReservaVacacional)
			return TipoOferta.VACACIONAL;
		else 
			return TipoOferta.VIVIENDA;
	}
}
