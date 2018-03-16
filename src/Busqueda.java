import java.io.Serializable;
import java.time.LocalDate;

import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

public abstract class Busqueda implements Serializable{
	
	private int codigoPostal;
	private double valoracion;
	private LocalDate fechaInicio, fechaFin;
	private TipoOferta tipo;
	
	public Busqueda(int codigoPostal, double valoracion, LocalDate fechaInicio, LocalDate fechaFin,
				TipoOferta tipo) {
		this.codigoPostal = codigoPostal;
		this.valoracion = valoracion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipo = tipo;
	}
	
	public int getCodigoPostal;
	
	public double getValoracion;
	
	public LocalDate getFechaInicio;
	
	public LocalDate getFechaFin;
	
	public TipoOferta getTipo;
}
