import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class BusquedaVivienda extends Busqueda implements Serializable{
	
	private Integer duracionMeses;
	
	/**
	 * 
	 *
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
	 * 
	 *
	 * @return 
	 */
	public Integer getDuracionMeses() {
		return this.duracionMeses;
	}
	
	/**
	 * 
	 *
	 * @param o 
	 * @return 
	 */
	public boolean cumpleCriterios(Object o) {
		if(o instanceof Oferta) {
			OfertaVivienda oferta = (OfertaVivienda)o;
			return (oferta.calcularMedia() >= valoracion) && (oferta.getFechaInicio().equals(fechaInicio)) 
					&& (oferta.getDuracionMeses() == duracionMeses);						
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see Busqueda#comprobarOferta(Oferta, java.lang.Integer)
	 */
	@Override
	public boolean comprobarOferta(Oferta oferta, Integer CP) {
		return this.cumpleCriterios(oferta) && (codigoPostal == CP);
	}


}