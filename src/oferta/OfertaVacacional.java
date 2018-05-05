/*
 * @author David Pascual y Cristian Tatu
 */
package oferta;
import java.io.Serializable;
import java.time.LocalDate;

import cliente.Cliente;
import fechasimulada.FechaSimulada;
import reserva.Reserva;
import reserva.ReservaVacacional;
import tipos.TipoOferta;

/**
 * Clase OfertaVacacional que hereda de la clase abstracta Oferta
 */
public class OfertaVacacional extends Oferta implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9106231765182642664L;
	
	private LocalDate fechaFin;
	public static final Double COMISION = 1.02; 
	
	/**
	 * Constructor
	 *
	 * @param precio 
	 * @param fechaInicio 
	 * @param descripcion 
	 * @param fechaFin
	 * @param ofertante 
	 */
	public OfertaVacacional(Double precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin, Cliente ofertante) {
		super(precio,fechaInicio,descripcion, ofertante);
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	/**
	 * @see Oferta#calcularComision()
	 */
	@Override
	public Double calcularComision() {
		return precio*COMISION;
	}

	/**
	 * @see Oferta#expirar()
	 */
	@Override
	public boolean expirar() {
		return FechaSimulada.getHoy().isAfter(fechaFin);
	}
	
	/**
	 * @see Oferta#reservar(Cliente)
	 */
	@Override
	public boolean reservar(Reserva reserva, Cliente demandante) {
		if (reservada == false && demandante.rolDemandante.getStatusVacacional() == false ) {
			reservada = true;
			this.demandante = demandante;
			demandante.rolDemandante.setrVacacional((ReservaVacacional)reserva);
			return true;
		}
		
		return false;
	}
	
	/**
	 * @see Oferta#cancelarReserva()
	 */
	@Override
	public boolean cancelarReserva() {		
		if(reservada && demandante.rolDemandante.getStatusVacacional()) {
			demandante.rolDemandante.eliminarReserva(TipoOferta.VACACIONAL);
			demandante = null;
			reserva = null;
			reservada = false;
			return true;
		}

		return false;
	}

	/**
	 * Gets the comision.
	 *
	 * @return the comision
	 */
	public static Double getComision() {
		return COMISION;
	}

	@Override
	public TipoOferta getTipo() {
		return TipoOferta.VACACIONAL;
	}
}
