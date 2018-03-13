import java.io.Serializable;
import java.time.LocalDate;

public class Busqueda implements Serializable{
	
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
	
	public int getCodigoPostal() {
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
	
	public TipoOferta getTipo() {
		return tipo;
	}
}
