import java.io.Serializable;
import java.time.LocalDate;

public class OfertaVacacional extends Oferta implements Serializable{
	
	private LocalDate fechaFin;
	private static final Double comision = 2.0;  // Porcentaje
	
	public OfertaVacacional(Integer precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin) {
		super(precio,fechaInicio,descripcion);
		this.fechaFin = fechaFin;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	@Override
	public double calcularComision() {
		double comisionEuros = 0.0;
		comisionEuros = (comision * precio) / 100;
		return comisionEuros;
	}
}
