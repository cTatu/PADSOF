import java.io.Serializable;
import java.time.LocalDate;

public abstract class OfertaAlquiler extends Oferta implements Serializable{

	private Integer duracionMeses;
	
	public OfertaAlquiler(Integer precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses) {
		super(precio,fechaInicio,descripcion);
		this.duracionMeses = duracionMeses;
	}
}
