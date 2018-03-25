import java.io.Serializable;
import java.time.LocalDate;

import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

public abstract class Busqueda implements Serializable{
	
	private Integer codigoPostal;
	private double valoracion;
	private LocalDate fechaInicio;
	private TipoOferta tipoOferta;
	private TipoDisponibilidad tipoDisponibilidad;
	
	public Busqueda(Integer codigoPostal, double valoracion, LocalDate fechaInicio, 
				TipoOferta tipoOferta, TipoDisponibilidad tipoDisponibilidad) {
		this.codigoPostal = codigoPostal;
		this.valoracion = valoracion;
		this.fechaInicio = fechaInicio;
		this.tipoOferta = tipoOferta;
		this.tipoDisponibilidad = tipoDisponibilidad;
	}
	
	public Integer getCodigoPostal() {
		return this.codigoPostal;
	}
	
	public double getValoracion() {
		return this.valoracion;
	}
	
	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	public TipoOferta getTipoOferta() {
		return this.tipoOferta;
	}
	
	public TipoDisponibilidad getTipoDisponibilidad() {
		return this.tipoDisponibilidad;
	}
}


