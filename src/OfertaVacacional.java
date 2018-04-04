import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import fechasimulada.FechaSimulada;

/**
 * 
 */
public class OfertaVacacional extends Oferta implements Serializable{
	
	private LocalDate fechaFin;
	private static final Double COMISION = 2.0;  // Porcentaje
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
		double comisionEuros = 0.0;
		comisionEuros = (COMISION * precio) / 100;
		return comisionEuros;
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
	public boolean reservar(Cliente demandante) {
		if (reservada == false && demandante.rolDemandante.getStatusVacacional() == false ) {
			reservada = true;
			reserva = new ReservaVacacional(FechaSimulada.getHoy()); // fecha simulada?
			this.demandante = demandante;
			this.demandante.rolDemandante.setrVacacional((ReservaVacacional)reserva);
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
			demandante.rolDemandante.eliminarReservaVacacional();
			demandante = null;
			reserva = null;
			reservada = false;
			return true;
		}

		return false;
	}
}
