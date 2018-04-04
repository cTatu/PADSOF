package busqueda;
import java.io.Serializable;
import java.time.LocalDate;

import oferta.Oferta;
import tipos.TipoDisponibilidad;
import tipos.TipoOferta;

/**
 * Implementacion (abstracta) de la clase busqueda
 * @author David Pascual y Cristian Tatu
 */
public abstract class Busqueda implements Serializable{
	
	protected Integer codigoPostal;
	protected double valoracion;
	protected LocalDate fechaInicio;
	protected TipoOferta tipoOferta;
	protected TipoDisponibilidad tipoDisponibilidad;
	
	/**
	 * Constructor
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
	 * @return Código Postal
	 */
	public Integer getCodigoPostal() {
		return this.codigoPostal;
	}
	
	/**
	 * @return Valoracion
	 */
	public double getValoracion() {
		return this.valoracion;
	}
	
	/**
	 * @return Fecha Inicio
	 */
	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	/**
	 * @return Tipo Oferta
	 */
	public TipoOferta getTipoOferta() {
		return this.tipoOferta;
	}
	
	/**
	 * @return Tipo disponibilidad
	 */
	public TipoDisponibilidad getTipoDisponibilidad() {
		return this.tipoDisponibilidad;
	}
	
	/**
	 * 
	 * @param oferta 
	 * @param CP 
	 * @return Boolean, true si entra en los criterios de busqueda, false de lo contrario
	 */
	public abstract boolean comprobarOferta(Oferta oferta, Integer CP);
}


