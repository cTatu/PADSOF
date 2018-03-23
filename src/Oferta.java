import java.time.LocalDate;

public abstract class Oferta {
	
	private Integer precio;
	private LocalDate fechaInicio;
	private Boolean reservada;
	private Boolean aprobada;
	private Boolean contratada;
	private Float comision;
	private String descripcion;
	
	public Oferta(Integer precio, LocalDate fechaInicio, Boolean reservada, Boolean aprobada, Boolean contratada, String descripcion) {
		this.precio = precio;
		this.fechaInicio = fechaInicio;
		this.reservada = reservada;
		this.aprobada = aprobada;
		this.contratada = contratada;
		this.descripcion = descripcion;
	}
	
	public boolean aprobar() {
		
	}
	
	public boolean reservar(Usuario usuario) {
		
	}

	public boolean rechazar() {
		
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
