import java.io.Serializable;
import java.time.LocalDate;

import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

public abstract class Busqueda implements Serializable{
	
	private Integer codigoPostal;
	private double valoracion;
	private LocalDate fechaInicio, fechaFin;
	private TipoOferta tipoOferta;
	private TipoDisponibilidad tipoDisponibilidad;
	
	public Busqueda(Integer codigoPostal, double valoracion, LocalDate fechaInicio, LocalDate fechaFin,
				TipoOferta tipoOferta, TipoDisponibilidad tipoDisponibilidad) {
		this.codigoPostal = codigoPostal;
		this.valoracion = valoracion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoOferta = tipoOferta;
		this.tipoDisponibilidad = tipoDisponibilidad;
	}
	
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	
	public double getValoracion() {
		return valoracion;
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	
	public TipoOferta getTipoOferta() {
		return tipoOferta;
	}
	
	public TipoDisponibilidad getTipoDisponibilidad() {
		return tipoDisponibilidad;
	}
}


