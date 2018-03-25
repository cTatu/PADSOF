import java.io.Serializable;
import java.time.LocalDate;

public abstract class Oferta implements Serializable{
	
	private Integer precio;
	private LocalDate fechaInicio;
	private Boolean reservada;
	private Boolean aprobada;
	private Boolean contratada;
	private Float comision;
	private String descripcion;
	
	public Oferta(Integer precio, LocalDate fechaInicio, String descripcion) {
		this.precio = precio;
		this.fechaInicio = fechaInicio;
		this.reservada = false;
		this.aprobada = false;
		this.contratada = false;
		this.descripcion = descripcion;
	}
	
	public boolean aprobar() {
		aprobada = true;
		return true;
	}
	
	public boolean reservar(Usuario usuario) {
		reservada = true;
		return true;
	}

	public boolean rechazar() {
		aprobada = false;
		return true;
	}
	
	public boolean editar() {
		
	}
	
	public boolean añadirOpinion(Opinion opinion) {
		
	}
	
	public boolean cancelar() {
		
	}
	
	public float calcularComision() {
		
	}
	
	public double contratar(Usuario usuario) {
		
	}
	
	public boolean añadirRectificacion(String rectificacion) {
		
	}
	
	public boolean expirar() {
		
	}
}
