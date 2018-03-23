import java.io.Serializable;
import java.time.LocalDate;

public class BusquedaVacacional extends Busqueda implements Serializable{

	public BusquedaVacacional(Integer codigoPostal, double valoracion, LocalDate fechaInicio, LocalDate fechaFin,
			TipoOferta tipoOferta, TipoDisponibilidad tipoDisponibilidad) {
		super(codigoPostal, valoracion, fechaInicio, fechaFin, tipoOferta, tipoDisponibilidad);
		// TODO Auto-generated constructor stub
	}	
}
