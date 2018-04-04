package busqueda;
import java.io.Serializable;
import java.time.LocalDate;

import oferta.Oferta;
import oferta.OfertaVacacional;
import tipos.TipoDisponibilidad;
import tipos.TipoOferta;

/**
 * Implentacion de BusquedaVacacional que hereda de busqueda y usa el campo fechaFin
 * @author David Pascual y Cristian Tatu
 */
public class BusquedaVacacional extends Busqueda implements Serializable{
	
	private LocalDate fechaFin;

	/**
	 * Constructor
	 * @param codigoPostal 
	 * @param valoracion 
	 * @param fechaInicio 
	 * @param tipoOferta 
	 * @param tipoDisponibilidad 
	 * @param fechaFin 
	 */
	public BusquedaVacacional(Integer codigoPostal, double valoracion, LocalDate fechaInicio, 
			TipoOferta tipoOferta, TipoDisponibilidad tipoDisponibilidad, LocalDate fechaFin) {
		super(codigoPostal, valoracion, fechaInicio, tipoOferta, tipoDisponibilidad);
		this.fechaFin = fechaFin;
	}	
	
	/**
	 * @return fechaFin 
	 */
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	/**
	 * Implementacion de busqueda para vacacional
	 * @see Busqueda#comprobarOferta(Oferta, java.lang.Integer)
	 */
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
