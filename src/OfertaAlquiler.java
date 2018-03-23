import java.time.LocalDate;

public abstract class OfertaAlquiler extends Oferta{

	private boolean reservada,contratada, aprobada;
	private Integer precio;
	private float comision;
	private LocalDate fechaInicio;
	private String descripcion;
	
	public OfertaAlquiler(Integer precio, float comision, LocalDate fechaInicio, String descripcion) {
		this.precio = precio;
		this.comision = comision;
		this.fechaInicio = fechaInicio;
		this.descripcion = descripcion;
		
		this.reservada = false;
		this.aprobada = false;
		this.contratada = false;
	}

}
