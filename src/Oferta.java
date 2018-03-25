import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Oferta implements Serializable{
	
	protected Integer precio;
	private LocalDate fechaInicio;
	private Boolean moderada; /* Sirve para saber si la oferta ha sido moderada, asi diferenciar una oferta rechazada y una no moderada */
	private Boolean aprobada;
	private Boolean reservada;
	private Boolean contratada;
	private String descripcion;
	private List<Reserva> reservas;
	private List<Opinion> opiniones;
	
	public Oferta(Integer precio, LocalDate fechaInicio, String descripcion) {
		this.precio = precio;
		this.fechaInicio = fechaInicio;
		this.moderada = false;
		this.aprobada = false;
		this.reservada = false;
		this.contratada = false;
		this.descripcion = descripcion;
		this.reservas = new ArrayList<Reserva>();
		this.opiniones = new ArrayList<Opinion>();
	}
	
	public boolean aprobar() {
		aprobada = true;
		moderada = true;
		return true;
	}

	public boolean rechazar() {
		aprobada = false;
		moderada = true;
		return true;
	}
	
	public boolean editar() {
		
	}
	
	public boolean cancelar() {
		
	}
	
	public boolean reservar(Cliente cliente) {

	}
	
	public double contratar(Usuario usuario) {
		
	}
	
	public boolean añadirOpinion(Opinion opinion) {
		
	}
	
	public boolean añadirRectificacion(String rectificacion) {
		
	}
	
	public boolean expirar() {
		
	}
	
	public abstract double calcularComision();
}
