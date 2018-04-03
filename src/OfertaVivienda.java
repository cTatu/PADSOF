import java.io.Serializable;
import java.time.LocalDate;

public class OfertaVivienda extends Oferta implements Serializable{

	private Integer duracionMeses;
	private static final Double COMISION = 0.1; // Porcentaje
	
	public OfertaVivienda(Double precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses, Cliente ofertante) {
		super(precio,fechaInicio,descripcion, ofertante);
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
	public boolean reservar(Cliente demandante) {
		if (reservada == false && demandante.rolDemandante.getStatusVivienda() == false ) {
			reservada = true;
			ReservaVivienda reserva = new ReservaVivienda(java.time.LocalDate.now()); // fecha simulada?
			this.demandante = demandante;
			this.demandante.rolDemandante.setrVivienda(reserva);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean cancelarReserva() {		
		if(reservada==true && demandante.rolDemandante.getStatusVivienda() == true) {
			demandante.rolDemandante.eliminarReservaVivienda();
			demandante = null;
			reserva = null;
			reservada = false;
			return true;
		}

		return false;
	}
}
