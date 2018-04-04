import java.io.Serializable;
import java.time.LocalDate;

import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

/**
 * 
 */
public abstract class Busqueda implements Serializable{
	
	protected Integer codigoPostal;
	protected double valoracion;
	protected LocalDate fechaInicio;
	protected TipoOferta tipoOferta;
	protected TipoDisponibilidad tipoDisponibilidad;
	
	/**
	 * 
	 *
	 * @param codigoPostal 
	 * @param valoracion 
	 * @param fechaInicio 
	 * @param tipoOferta 
	 * @param tipoDisponibilidad 
	 */
	public Busqueda(Integer codigoPostal, double valoracion, LocalDate fechaInicio, 
				TipoOferta tipoOferta, TipoDisponibilidad tipoDisponibilidad) {
		this.codigoPostal = codigoPostal;
		this.valoracion = valoracion;
		this.fechaInicio = fechaInicio;
		this.tipoOferta = tipoOferta;
		this.tipoDisponibilidad = tipoDisponibilidad;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public Integer getCodigoPostal() {
		return this.codigoPostal;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public double getValoracion() {
		return this.valoracion;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public TipoOferta getTipoOferta() {
		return this.tipoOferta;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public TipoDisponibilidad getTipoDisponibilidad() {
		return this.tipoDisponibilidad;
	}
	
	/**
	 * 
	 *
	 * @param oferta 
	 * @param CP 
	 * @return 
	 */
	public abstract boolean comprobarOferta(Oferta oferta, Integer CP);
}


