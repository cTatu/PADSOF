/*
 * @author David Pascual y Cristian Tatu
 */
package busqueda;
import java.io.Serializable;
import java.time.LocalDate;

import oferta.Oferta;
import oferta.OfertaVacacional;
import tipos.TipoDisponibilidad;

/**
 * Implentacion de BusquedaVacacional que hereda de busqueda y usa el campo fechaFin.
 *
 * @author David Pascual y Cristian Tatu
 */
public class BusquedaVacacional extends Busqueda implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1284017795159016673L;
	
	private LocalDate fechaFin;

	/**
	 * Constructor.
	 *
	 * @param codigoPostal 
	 * @param valoracion 
	 * @param fechaInicio1 
	 * @param fechaInicio2
	 * @param tipoDisponibilidad 
	 * @param fechaFin 
	 */
	public BusquedaVacacional(Integer codigoPostal, Double valoracion, LocalDate fechaInicio1,
			LocalDate fechaInicio2, TipoDisponibilidad tipoDisponibilidad, LocalDate fechaFin) {
		super(codigoPostal, valoracion, fechaInicio1, fechaInicio2, tipoDisponibilidad);
		this.fechaFin = fechaFin;
	}	
	
	/**
	 * Gets the fecha fin.
	 *
	 * @return fechaFin
	 */
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	/**
	 * Implementacion de busqueda para vacacional.
	 *
	 * @param oferta 
	 * @return true, if successful
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
			cumple = cumple || (super.tipoDisponibilidad.equals(TipoDisponibilidad.DISPONIBLE) && !oferta.getContratada() && !oferta.getReservada());
			
			cumple = cumple && (oferta.calcularMedia() >= valoracion);			
		}

		return cumple;
	}

}
