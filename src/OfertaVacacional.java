import java.io.Serializable;
import java.time.LocalDate;

public class OfertaVacacional extends Oferta implements Serializable{
	
	private LocalDate fechaFin;
	
	public OfertaVacacional(Integer precio, LocalDate fechaInicio, String descripcion, LocalDate fechafin) {
		super(precio,fechaInicio,descripcion);
		this.fechaFin = fechaFin;
	}
}
