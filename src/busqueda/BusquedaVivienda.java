/*
 * @author David Pascual y Cristian Tatu
 */
package busqueda;
import java.io.Serializable;
import java.time.LocalDate;

import oferta.Oferta;
import oferta.OfertaVivienda;
import tipos.TipoDisponibilidad;

/**
 * Implentacion de BusquedaVivienda que hereda de busqueda y usa el campo duracionMeses.
 */
public class BusquedaVivienda extends Busqueda implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 28767109159527037L;
	
	private Integer duracionMeses;
	
	/**
	 * Constructor.
	 *
	 * @param codigoPostal
	 * @param valoracion 
	 * @param fechaInicio1 
	 * @param fechaInicio2 
	 * @param tipoDisponibilidad 
	 * @param duracionMeses 
	 */
	public BusquedaVivienda(Integer codigoPostal, Double valoracion, LocalDate fechaInicio1, 
			LocalDate fechaInicio2, TipoDisponibilidad tipoDisponibilidad, Integer duracionMeses) {
		super(codigoPostal, valoracion, fechaInicio1,fechaInicio2, tipoDisponibilidad);
		this.duracionMeses = duracionMeses;
	}	
	
	/**
	 * Gets the duracion meses.
	 *
	 * @return duracionMeses
	 */
	public Integer getDuracionMeses() {
		return this.duracionMeses;
	}
	
	/**
	 * Implementacion de busqueda para vivienda.
	 *
	 * @param oferta 
	 * @return true si se cumplen los criterios, false de lo contrario
	 * @see Busqueda#comprobarOferta(Oferta, java.lang.Integer)
	 */
	@Override
	public boolean comprobarOferta(Oferta oferta) {
		boolean cumple = false;
		
		if(oferta instanceof OfertaVivienda) {
			if ((oferta.getFechaInicio().isAfter(super.getFechaInicio1()) ||  oferta.getFechaInicio().isEqual(super.getFechaInicio1()))
					&& 
					(oferta.getFechaInicio().isBefore(super.getFechaInicio2()) || oferta.getFechaInicio().isEqual(super.getFechaInicio2()) ))
				cumple = true;
			cumple = cumple && (super.tipoDisponibilidad.equals(TipoDisponibilidad.CONTRATADO) && oferta.getContratada());
			cumple = cumple || (super.tipoDisponibilidad.equals(TipoDisponibilidad.RESERVADO) && oferta.getReservada());
			cumple = cumple || (super.tipoDisponibilidad.equals(TipoDisponibilidad.DISPONIBLE) && !oferta.getContratada() && !oferta.getReservada());
			
			cumple = cumple && (oferta.calcularMedia() >= valoracion);
			
			cumple = cumple && (((OfertaVivienda) oferta).getDuracionMeses() >= duracionMeses);
		}

		return cumple;
	}


}