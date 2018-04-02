import java.io.Serializable;
import java.time.LocalDate;

public class OfertaVivienda extends Oferta implements Serializable{

	private Integer duracionMeses;
	private static final Double COMISION = 0.1; // Porcentaje
	
	public OfertaVivienda(Double precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses) {
		super(precio,fechaInicio,descripcion);
		this.duracionMeses = duracionMeses;
	}
	
	public Integer getDuracionMeses() {
		return this.duracionMeses;
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
		if (reservada == false && demandante.getStatusVivienda() == false ) {
			reservada = true;
			reserva = new ReservaVivienda(java.time.LocalDate.now()); // fecha simulada?
			this.demandante = demandante;
			this.demandante.setrVivienda((ReservaVivienda)reserva);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean cancelarReserva() {		
		if(reservada==true && demandante.getStatusVivienda() == true) {
			demandante.eliminarReservaVivienda();
			demandante = null;
			reserva = null;
			reservada = false;
			return true;
		}

		return false;
	}
}
