package oferta;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cliente.Cliente;
import opinion.Opinion;
import opinion.Valoracion;
import reserva.Reserva;

/**
 * Implementacion de oferta (abstracta) y métodos para manejarlas
 * @author David Pascual y Cristian Tatu
 */
public abstract class Oferta implements Serializable{
	
	protected Double precio;
	private LocalDate fechaInicio;
	private Boolean moderada = false; /* Sirve para diferenciar una oferta rechazada y una no moderada */
	private Boolean aprobada = false;
	protected Boolean reservada = false;
	private Boolean contratada = false;
	private String descripcion;
	private Map<String, String> rectificaciones;
	protected Reserva reserva;
	private List<Opinion> opiniones;
	private Cliente ofertante;
	protected Cliente demandante;
	
	/**
	 * 
	 * @return Comision calculada sobre el precio
	 */
	public abstract double calcularComision();
	
	/**
	 * 
	 * @return 
	 */
	public abstract boolean expirar();
	
	/**
	 * 
	 *
	 * @param demandante 
	 * @return 
	 */
	public abstract boolean reservar(Reserva reserva, Cliente demandante);
	
	/**
	 * 
	 *
	 * @return 
	 */
	public abstract boolean cancelarReserva();	
	
	/**
	 * 
	 *
	 * @param precio 
	 * @param fechaInicio 
	 * @param descripcion 
	 * @param ofertante 
	 */
	public Oferta(Double precio, LocalDate fechaInicio, String descripcion, Cliente ofertante) {
		this.precio = precio;
		this.fechaInicio = fechaInicio;
		this.descripcion = descripcion;
		this.opiniones = new ArrayList<Opinion>();
		this.ofertante = ofertante;
	}
	
	
	/**
	 * 
	 *
	 * @param rectificacion 
	 */
	public void añadirRectificacion(Map<String, String> rectificacion) {
		this.rectificaciones = rectificacion;
		this.moderada = true;
	}
	
	/**
	 * 
	 */
	public void mostrarRectificaciones() {
		if (this.rectificaciones != null)
			System.out.println(rectificaciones);
	}
	
	/**
	 * 
	 *
	 * @param demandante 
	 * @return 
	 */
	public boolean contratar(Cliente demandante) {
		demandante.rolDemandante.añadirOfertaContratada(this);
		contratada = true;
		this.demandante = demandante;
		
		return true;
	}
	
	/**
	 * 
	 *
	 * @param opinion 
	 * @return 
	 */
	public boolean añadirOpinion(Opinion opinion) {
		opiniones.add(opinion);
		return true;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
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
	
	/**
	 * 
	 *
	 * @return 
	 */
	public Cliente getOfertante() {
		return ofertante;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * 
	 *
	 * @param precio 
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
		this.moderada = false;
	}

	/**
	 * 
	 *
	 * @return 
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * 
	 *
	 * @param fechaInicio 
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
		this.moderada = false;
	}

	/**
	 * 
	 *
	 * @return 
	 */
	public Boolean getModerada() {
		return moderada;
	}

	/**
	 * 
	 *
	 * @param moderada 
	 */
	public void setModerada(Boolean moderada) {
		this.moderada = moderada;
	}

	/**
	 * 
	 *
	 * @return 
	 */
	public Boolean getAprobada() {
		return aprobada;
	}

	/**
	 * 
	 *
	 * @param aprobada 
	 */
	public void setAprobada(Boolean aprobada) {
		this.aprobada = aprobada;
	}

	/**
	 * 
	 *
	 * @return 
	 */
	public Boolean getReservada() {
		return reservada;
	}

	/**
	 * 
	 *
	 * @param reservada 
	 */
	public void setReservada(Boolean reservada) {
		this.reservada = reservada;
	}

	/**
	 * 
	 *
	 * @return 
	 */
	public Boolean getContratada() {
		return contratada;
	}

	/**
	 * 
	 *
	 * @param contratada 
	 */
	public void setContratada(Boolean contratada) {
		this.contratada = contratada;
	}

	/**
	 * 
	 *
	 * @return 
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 *
	 * @param descripcion 
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		this.moderada = false;
	}
}
