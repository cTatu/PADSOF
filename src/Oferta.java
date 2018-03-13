import java.time.LocalDate;

public abstract class Oferta {

	private boolean reservada,contratada, aprobada;
	private int precio;
	private float comision;
	private LocalDate fechaInicio;
	private String descripcion;
	
	public Oferta(int precio, float comision, LocalDate fechaInicio, String descripcion) {
		this.precio = precio;
		this.comision = comision;
		this.fechaInicio = fechaInicio;
		this.descripcion = descripcion;
		
		this.reservada = false;
		this.aprobada = false;
		this.contratada = false;
	}

}
