package busqueda;
import java.io.Serializable;
import java.time.LocalDate;

import oferta.Oferta;
import oferta.OfertaVivienda;
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
	public BusquedaVivienda(Integer codigoPostal, double valoracion, LocalDate fechaInicio, 
			TipoOferta tipoOferta, TipoDisponibilidad tipoDisponibilidad, Integer duracionMeses) {
		super(codigoPostal, valoracion, fechaInicio, tipoOferta, tipoDisponibilidad);
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
	public boolean comprobarOferta(Oferta oferta, Integer CP) {
		boolean cumple = false;
		
		if(oferta instanceof OfertaVivienda) {
			// pulir
			if (super.tipoDisponibilidad.equals(TipoDisponibilidad.CONTRATADO) && oferta.getContratada())
				cumple = true;
			else if (super.tipoDisponibilidad.equals(TipoDisponibilidad.RESERVADO) && oferta.getReservada())
				cumple = true;
			
			cumple = cumple && (oferta.calcularMedia() >= valoracion) && (oferta.getFechaInicio().equals(fechaInicio)) 
					&& (((OfertaVivienda) oferta).getDuracionMeses()>duracionMeses);						
		}

		return cumple && super.codigoPostal.equals(CP);
	}


}