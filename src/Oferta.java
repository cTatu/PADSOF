import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public abstract class Oferta implements Serializable{
	
	protected Double precio;
	private LocalDate fechaInicio;
	private Boolean moderada; /* Sirve para diferenciar una oferta rechazada y una no moderada */
	private Boolean aprobada;
	protected Boolean reservada;
	private Boolean contratada;
	private String descripcion;
	private Map<String, String> modificaciones;
	protected Reserva reserva;
	private List<Opinion> opiniones;
	private Ofertante ofertante;
	protected Demandante demandante;
	
	public abstract double calcularComision();
	public abstract boolean expirar();
	public abstract boolean reservar(Demandante demandante);
	public abstract boolean cancelarReserva();	
	
	public Oferta(Double precio, LocalDate fechaInicio, String descripcion) {
		this.precio = precio;
		this.fechaInicio = fechaInicio;
		this.moderada = false;
		this.aprobada = false;
		this.reservada = false;
		this.contratada = false;
		this.descripcion = descripcion;
		this.modificaciones = null;
		this.reserva = null;
		this.opiniones = new ArrayList<Opinion>();
		this.ofertante = null;
		this.demandante = null;
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
	
	public void añadirRectificacion(Map<String, String> rectificacion) {
		this.modificaciones = rectificacion;
	}
	
	public void mostrarModificaciones() {
		if (this.modificaciones != null)
			System.out.println(modificaciones);
	}
	
	public double contratar(Demandante demandante) {
		
	}
	
	public boolean añadirOpinion(Opinion opinion) {
		opiniones.add(opinion);
		return true;
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
	
	public Ofertante getOfertante() {
		return ofertante;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
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
