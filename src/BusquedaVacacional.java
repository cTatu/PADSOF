import java.io.Serializable;
import java.time.LocalDate;

public class BusquedaVacacional extends Busqueda implements Serializable{
	
	private LocalDate fechaFin;

	public BusquedaVacacional(Integer codigoPostal, double valoracion, LocalDate fechaInicio, 
			TipoOferta tipoOferta, TipoDisponibilidad tipoDisponibilidad, LocalDate fechaFin) {
		super(codigoPostal, valoracion, fechaInicio, tipoOferta, tipoDisponibilidad);
		this.fechaFin = fechaFin;
	}	
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	public boolean cumpleCriterios(Object o) {
		if(o instanceof Oferta) {
			OfertaVacacional oferta = (OfertaVacacional)o;
			return (oferta.calcularMedia() >= valoracion) && (oferta.getFechaInicio().equals(fechaInicio)) 
					&& (oferta.getFechaFin() == fechaFin);						
		}
		return false;
	}
	
	@Override
	public boolean comprobarOferta(Oferta oferta, int CP) {
		return this.cumpleCriterios(oferta) && (codigoPostal == CP);
	}

}
