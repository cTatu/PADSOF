import java.io.Serializable;
import java.time.LocalDate;

import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

public abstract class Busqueda implements Serializable{
	
	private int codigoPostal;
	private double valoracion;
	private LocalDate fechaInicio, fechaFin;
	private TipoOferta tipoOferta;
	private TipoDisponibilidad tipoDisponibilidad;
	
	public Busqueda(int codigoPostal, double valoracion, LocalDate fechaInicio, LocalDate fechaFin,
				TipoOferta tipoOferta, TipoDisponibilidad tipoDisponibilidad) {
		this.codigoPostal = codigoPostal;
		this.valoracion = valoracion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoOferta = tipoOferta;
		this.tipoDisponibilidad = tipoDisponibilidad;
	}
	
	public abstract int getCodigoPostal();
	
	public abstract double getValoracion();
	
	public abstract LocalDate getFechaInicio();
	
	public abstract  LocalDate getFechaFin();
	
	public abstract TipoOferta getTipoOferta();
	
	public abstract TipoDisponibilidad getTipoDisponibilidad();
}


