package busqueda;
import java.io.Serializable;
import java.time.LocalDate;

import oferta.Oferta;
import oferta.OfertaVacacional;
import tipos.TipoDisponibilidad;

/**
 * Implentacion de BusquedaVacacional que hereda de busqueda y usa el campo fechaFin
 * @author David Pascual y Cristian Tatu
 */
public class BusquedaVacacional extends Busqueda implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1284017795159016673L;
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
	public BusquedaVacacional(Integer codigoPostal, Double valoracion, LocalDate fechaInicio1,
			LocalDate fechaInicio2, TipoDisponibilidad tipoDisponibilidad, LocalDate fechaFin) {
		super(codigoPostal, valoracion, fechaInicio1, fechaInicio2, tipoDisponibilidad);
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
	public boolean comprobarOferta(Oferta oferta) {
		boolean cumple = false;
		
		if(oferta instanceof OfertaVacacional) {
			if ((oferta.getFechaInicio().isAfter(super.getFechaInicio1()) ||  oferta.getFechaInicio().isEqual(super.getFechaInicio1()))
					&& 
					(oferta.getFechaInicio().isBefore(super.getFechaInicio2()) || oferta.getFechaInicio().isEqual(super.getFechaInicio2()) ))
				cumple = true;
			cumple = cumple && (super.tipoDisponibilidad.equals(TipoDisponibilidad.CONTRATADO) && oferta.getContratada());
			cumple = cumple || (super.tipoDisponibilidad.equals(TipoDisponibilidad.RESERVADO) && oferta.getReservada());
			
			cumple = cumple && (oferta.calcularMedia() >= valoracion);			
		}

		return cumple;
	}

}
