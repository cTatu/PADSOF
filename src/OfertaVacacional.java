import java.io.Serializable;
import java.time.LocalDate;

public class OfertaVacacional extends Oferta implements Serializable{
	
	private LocalDate fechaFin;
	private static final Double COMISION = 2.0;  // Porcentaje
	
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
		
	}
	
	@Override
	public boolean reservar(Demandante demandante) {
		if (reservada == false && demandante.getStatusVacacional() == false ) {
			reservada = true;
			reserva = new ReservaVacacional(java.time.LocalDate.now()); // fecha simulada?
			this.demandante = demandante;
			this.demandante.setrVacacional((ReservaVacacional)reserva);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean cancelarReserva() {		
		if(reservada==true && demandante.getStatusVacacional() == true) {
			demandante.eliminarReservaVacacional();
			demandante = null;
			reserva = null;
			reservada = false;
			return true;
		}

		return false;
	}
}
