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
	protected LocalDate fechaInicio1, fechaInicio2;
	protected TipoDisponibilidad tipoDisponibilidad;
	
	/**
	 * Constructor
	 * @param codigoPostal 
	 * @param valoracion 
	 * @param fechaInicio 
	 * @param tipoOferta 
	 * @param tipoDisponibilidad 
	 */
	public Busqueda(Integer codigoPostal, double valoracion, LocalDate fechaInicio1, 
			LocalDate fechaInicio2,TipoDisponibilidad tipoDisponibilidad) {
		this.codigoPostal = codigoPostal;
		this.valoracion = valoracion;
		this.fechaInicio1 = fechaInicio1;
		this.fechaInicio2 = fechaInicio2;
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
	public LocalDate getFechaInicio1() {
		return this.fechaInicio1;
	}
	
	public LocalDate getFechaInicio2() {
		return this.fechaInicio2;
	}
	
	/**
	 * @return Tipo disponibilidad
	 */
	public TipoDisponibilidad getTipoDisponibilidad() {
		return this.tipoDisponibilidad;
	}
	
	public void setToDisponible() {
		tipoDisponibilidad = TipoDisponibilidad.DISPONIBLE;
	}
	
	/**
	 * 
	 * @param oferta 
	 * @param CP 
	 * @return Boolean, true si entra en los criterios de busqueda, false de lo contrario
	 */
	public abstract boolean comprobarOferta(Oferta oferta);
}


