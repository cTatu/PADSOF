import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public abstract class Oferta implements Serializable{
	
	protected Integer precio;
	private LocalDate fechaInicio;
	private Boolean moderada; /* Sirve para saber si la oferta ha sido moderada, asi diferenciar una oferta rechazada y una no moderada */
	private Boolean aprobada;
	private Boolean reservada;
	private Boolean contratada;
	private String descripcion;
	private Ofertante ofertante;
	private Map<String, String> modificaciones;
	private List<Reserva> reservas;
	private List<Opinion> opiniones;
	
	public abstract double calcularComision();
	
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
	
	public Ofertante getOfertante() {
		return ofertante;
	}
	
	public void mostrarModificaciones() {
		if (this.modificaciones != null)
			System.out.println(modificaciones);
	}
	
	public boolean reservar(Cliente cliente) {

	}
	
	public double contratar(Usuario usuario) {
		
	}
	
	public boolean expirar() {
		
	}
	
	public boolean añadirOpinion(Opinion opinion) {
		opiniones.add(opinion);
		return true;
	}
	
	public boolean añadirRectificacion(String rectificacion) {
		
	}
	
	public double calcularMedia() {
		double total=0.0, media=0.0;
		int n=0;
		
		for(Opinion o: opiniones) {
			if(o instanceof Valoracion) {
				total += ((Valoracion)o).getNumero();
				n++;
			}
		}
		media = total / n;
		
		return media;
	}

	
	/*Getters y Setters*/
	
	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Boolean getModerada() {
		return moderada;
	}

	public void setModerada(Boolean moderada) {
		this.moderada = moderada;
	}

	public Boolean getAprobada() {
		return aprobada;
	}

	public void setAprobada(Boolean aprobada) {
		this.aprobada = aprobada;
	}

	public Boolean getReservada() {
		return reservada;
	}

	public void setReservada(Boolean reservada) {
		this.reservada = reservada;
	}

	public Boolean getContratada() {
		return contratada;
	}

	public void setContratada(Boolean contratada) {
		this.contratada = contratada;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setModificaciones(Map<String, String> modificaciones) {
		this.modificaciones = modificaciones;
	}
}
