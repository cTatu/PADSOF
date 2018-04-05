package busqueda;
import java.io.Serializable;
import java.time.LocalDate;

import oferta.Oferta;
import oferta.OfertaVivienda;
import sun.swing.text.CountingPrintable;
import tipos.TipoDisponibilidad;
import tipos.TipoOferta;

/**
 * Implentacion de BusquedaVivienda que hereda de busqueda y usa el campo duracionMeses
 * @author David Pascual y Cristian Tatu
 */
public class BusquedaVivienda extends Busqueda implements Serializable{
	
	private Integer duracionMeses;
	
	/**
	 * Constructor
	 * @param codigoPostal 
	 * @param valoracion 
	 * @param fechaInicio 
	 * @param tipoOferta 
	 * @param tipoDisponibilidad 
	 * @param duracionMeses 
	 */
	public BusquedaVivienda(Integer codigoPostal, double valoracion, LocalDate fechaInicio1, 
			LocalDate fechaInicio2, TipoDisponibilidad tipoDisponibilidad, Integer duracionMeses) {
		super(codigoPostal, valoracion, fechaInicio1,fechaInicio2, tipoDisponibilidad);
		this.duracionMeses = duracionMeses;
	}	
	
	/**
	 * @return duracionMeses
	 */
	public Integer getDuracionMeses() {
		return this.duracionMeses;
	}
	
	/**
	 * Implementacion de busqueda para vivienda
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
			if (super.tipoDisponibilidad.equals(TipoDisponibilidad.CONTRATADO) && oferta.getContratada())
				cumple = true;
			else if (super.tipoDisponibilidad.equals(TipoDisponibilidad.RESERVADO) && oferta.getReservada())
				cumple = true;
			
			if (oferta.calcularMedia() >= valoracion)
				cumple = true;
			if(((OfertaVivienda) oferta).getDuracionMeses() >= duracionMeses)
				cumple = true;
		}

		return cumple;
	}


}