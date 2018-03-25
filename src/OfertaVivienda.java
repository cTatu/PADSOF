import java.io.Serializable;
import java.time.LocalDate;

public class OfertaVivienda extends Oferta implements Serializable{

	private Integer duracionMeses;
	private static final Double comision = 0.1; // Porcentaje
	
	public OfertaVivienda(Integer precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses) {
		super(precio,fechaInicio,descripcion);
		this.duracionMeses = duracionMeses;
	}
	
	public Integer getDuracionMeses() {
		return this.duracionMeses;
	}

	@Override
	public double calcularComision() {
		double comisionEuros = 0.0;
		comisionEuros = (comision * precio) / 100;
		return comisionEuros;
	}
}
