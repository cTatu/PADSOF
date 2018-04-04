package oferta;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import cliente.Cliente;
import fechasimulada.FechaSimulada;
import reserva.Reserva;
import reserva.ReservaVacacional;
import tipos.TipoOferta;

/**
 * 
 */
public class OfertaVacacional extends Oferta implements Serializable{
	
	private LocalDate fechaFin;
	private static final Double COMISION = 1.02;  // Porcentaje
	private static final int DIAS_DISPONIBLE = 5;
	
	/**
	 * 
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
	 * 
	 *
	 * @return 
	 */
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	/* (non-Javadoc)
	 * @see Oferta#calcularComision()
	 */
	@Override
	public double calcularComision() {
		return precio*COMISION;
	}

	/* (non-Javadoc)
	 * @see Oferta#expirar()
	 */
	@Override
	public boolean expirar() {
		if (Period.between(FechaSimulada.getHoy(), this.fechaFin.plusDays(DIAS_DISPONIBLE)).isNegative())
			return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see Oferta#reservar(Cliente)
	 */
	@Override
	public boolean reservar(Reserva reserva, Cliente demandante) {
		if (reservada == false && demandante.rolDemandante.getStatusVacacional() == false ) {
			reservada = true;
			this.demandante = demandante;
			demandante.rolDemandante.añadirReserva(reserva);
			return true;
		}
		
		return false;
	}
	
	/* (non-Javadoc)
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
}
