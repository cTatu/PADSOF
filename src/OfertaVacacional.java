import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import fechasimulada.FechaSimulada;

public class OfertaVacacional extends Oferta implements Serializable{
	
	private LocalDate fechaFin;
	private static final Double COMISION = 2.0;  // Porcentaje
	private static final int DIAS_DISPONIBLE = 5;
	
	public OfertaVacacional(Double precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin) {
		super(precio,fechaInicio,descripcion);
		this.fechaFin = fechaFin;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	@Override
	public double calcularComision() {
		double comisionEuros = 0.0;
		comisionEuros = (COMISION * precio) / 100;
		return comisionEuros;
	}

	@Override
	public boolean expirar() {
		if (Period.between(FechaSimulada.getHoy(), this.fechaFin.plusDays(DIAS_DISPONIBLE)).isNegative())
			return true;
		return false;
	}
	
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
