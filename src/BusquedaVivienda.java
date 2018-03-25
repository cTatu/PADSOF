import java.io.Serializable;
import java.time.LocalDate;

public class BusquedaVivienda extends Busqueda implements Serializable{
	
	private Integer duracionMeses;
	
	public BusquedaVivienda(Integer codigoPostal, double valoracion, LocalDate fechaInicio, 
			TipoOferta tipoOferta, TipoDisponibilidad tipoDisponibilidad, Integer duracionMeses) {
		super(codigoPostal, valoracion, fechaInicio, tipoOferta, tipoDisponibilidad);
		this.duracionMeses = duracionMeses;
	}	
	
	public Integer getDuracionMeses() {
		return this.duracionMeses;
	}
}