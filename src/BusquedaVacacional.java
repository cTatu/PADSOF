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
	
	@Override
	public boolean comprobarOferta(Oferta oferta, Integer CP) {
		boolean cumple = false;
		
		if(oferta instanceof OfertaVacacional) {
			// pulir
			if (super.tipoDisponibilidad.equals(TipoDisponibilidad.CONTRATADO) && oferta.getContratada())
				cumple = true;
			else if (super.tipoDisponibilidad.equals(TipoDisponibilidad.RESERVADO) && oferta.getReservada())
				cumple = true;
			
			cumple = cumple && (oferta.calcularMedia() >= valoracion) && (oferta.getFechaInicio().equals(fechaInicio)) 
					&& (((OfertaVacacional) oferta).getFechaFin().isAfter(fechaFin));						
		}

		return cumple && super.codigoPostal.equals(CP);
	}

}
