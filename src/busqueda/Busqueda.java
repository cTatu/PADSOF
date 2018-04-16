/*
 * @author David Pascual y Cristian Tatu
 */
package busqueda;
import java.io.Serializable;
import java.time.LocalDate;

import oferta.Oferta;
import tipos.TipoDisponibilidad;


/**
 * Implementacion (abstracta) de la clase busqueda.
 *
 * @author David Pascual y Cristian Tatu
 */
public abstract class Busqueda implements Serializable{
	
	private static final long serialVersionUID = -5718937815988103385L;
	protected Integer codigoPostal;
	protected Double valoracion;
	protected LocalDate fechaInicio1, fechaInicio2;
	protected TipoDisponibilidad tipoDisponibilidad;
	
	/**
	 * Constructor.
	 *
	 * @param codigoPostal 
	 * @param valoracion 
	 * @param fechaInicio1 
	 * @param fechaInicio2 // Rango de fechas
	 * @param tipoDisponibilidad 
	 */
	public Busqueda(Integer codigoPostal, Double valoracion, LocalDate fechaInicio1, 
			LocalDate fechaInicio2,TipoDisponibilidad tipoDisponibilidad) {
		this.codigoPostal = codigoPostal;
		this.valoracion = valoracion;
		this.fechaInicio1 = fechaInicio1;
		this.fechaInicio2 = fechaInicio2;
		this.tipoDisponibilidad = tipoDisponibilidad;
	}
	
	/**
	 * Gets the codigo postal.
	 *
	 * @return Cnidigo Postal
	 */
	public Integer getCodigoPostal() {
		return this.codigoPostal;
	}
	
	/**
	 * Gets the valoracion.
	 *
	 * @return Valoracion
	 */
	public Double getValoracion() {
		return this.valoracion;
	}
	
	/**
	 * Gets the fecha inicio 1.
	 *
	 * @return Fecha Inicio
	 */
	public LocalDate getFechaInicio1() {
		return this.fechaInicio1;
	}
	
	/**
	 * Gets the fecha inicio 2.
	 *
	 * @return the fecha inicio 2
	 */
	public LocalDate getFechaInicio2() {
		return this.fechaInicio2;
	}
	
	/**
	 * Gets the tipo disponibilidad.
	 *
	 * @return Tipo disponibilidad
	 */
	public TipoDisponibilidad getTipoDisponibilidad() {
		return this.tipoDisponibilidad;
	}
	
	/**
	 * Cambia la disponibilidad a disponible.
	 */
	public void setToDisponible() {
		tipoDisponibilidad = TipoDisponibilidad.DISPONIBLE;
	}
	
	/**
	 * Comprueba si la oferta pasada por agumento cumple los criterios.
	 *
	 * @param oferta 
	 * @return Boolean, true si entra en los criterios de busqueda, false de lo contrario
	 */
	public abstract boolean comprobarOferta(Oferta oferta);
}


