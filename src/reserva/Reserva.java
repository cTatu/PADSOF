package reserva;

import java.time.LocalDate;

import cliente.Cliente;
import fechasimulada.FechaSimulada;
import oferta.Oferta;

/**
 * Clase que sirve para manejar las reservas
 * @author David Pascual y Cristian Tatu
 */

public abstract class Reserva {
	private LocalDate fechaReserva;
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
		if (FechaSimulada.getHoy().isAfter(fechaReserva.plusDays(5)))
			return true;
		
		return false;
	}	
	
	@Override
	public boolean equals(Object o) {
		Reserva r = (Reserva) o;
		//if(!(o instanceof Reserva)) return false;
		if (this.ofertaReservada.equals(r.ofertaReservada))
			return true;
		return false;
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
	
}
