/*
 * @author David Pascual y Cristian Tatu
 */
package oferta;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cliente.Cliente;
import opinion.Comentario;
import opinion.Opinion;
import opinion.Valoracion;
import reserva.Reserva;

/**
 * Implementacion de oferta (abstracta) y métodos para manejarlas.
 */
public abstract class Oferta implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 805522050049933034L;
	
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
	 * Calcula el precio total de la oferta incluyendo la comision
	 *
	 * @return Comision calculada sobre el precio
	 */
	public abstract Double calcularComision();
	
	/**
	 * Comprueba si una oferta esta expira
	 *
	 * @return true, si lo esta
	 */
	public abstract boolean expirar();
	
	/**
	 * Pone la oferta en reservado con los respectivos cambios que supone hacer
	 *
	 * @param reserva 
	 * @param demandante de la oferta
	 * @return true si se ha reservado, false si ya esta reservada o el demandante ya tiene una reserva en marcha
	 */
	public abstract boolean reservar(Reserva reserva, Cliente demandante);
	
	/**
	 * Cancela la reserva de una oferta
	 *
	 * @return true si se cancela, false si no estaba reservada
	 */
	public abstract boolean cancelarReserva();	
	
	/**
	 * Constructor
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
		this.demandante = null;
	}

	
	/**
	 * Añade rectificacion a la oferta
	 *
	 * @param rectificacion 
	 * @return true
	 */
	public boolean añadirRectificacion(Map<String, String> rectificacion) {
		this.rectificaciones = rectificacion;
		this.moderada = true;
		return true;
	}
	
	/**
	 * Muestra las rectificaciones por pantalla
	 */
	public void mostrarRectificaciones() {
		if (this.rectificaciones != null)
			System.out.println(rectificaciones);
	}
	
	/**
	 * Cambia el estado de una oferta a contratado, con los cambios que lleva hacer
	 *
	 * @param demandante que contrata
	 * @return true 
	 */
	public boolean contratar(Cliente demandante) {
		demandante.rolDemandante.añadirOfertaContratada(this);
		contratada = true;
		this.demandante = demandante;
		
		return true;
	}
	
	/**
	 * Añadie una opinion a la oferta.
	 *
	 * @param opinion 
	 * @return true
	 */
	public boolean añadirOpinion(Opinion opinion) {
		opiniones.add(opinion);
		return true;
	}
	
	/**
	 * Calcula la media de las valoraciones de la oferta.
	 *
	 * @return media calculada
	 */
	public Double calcularMedia() {
		Double total=0.0, media=0.0;
		Integer n=0;
		
		for(Opinion o: opiniones) {
			if(o instanceof Valoracion) {
				total += ((Valoracion)o).getNumero();
				n++;
			}
		}
		if (n.equals(0))
			return 0.0;
		
		media = total / n;
		
		return media;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String print = (
				"\nOferta\n" + 
				"\t" + "Precio: " + precio + "\n" +
				"\t" + "FechaInicio: " + fechaInicio + "\n" +
				"\t" + "Tipo: "
				);
		if (this instanceof OfertaVacacional) {
			print = print.concat("Vacacional\n");
			print = print.concat("\tFechaFin: " + ((OfertaVacacional)this).getFechaFin());
		}else {
			print = print.concat("Vivienda\n");
			print = print.concat("\tDuracionMeses: " + ((OfertaVivienda)this).getDuracionMeses());
		}
		return print;
	}
	
	/*Getters y Setters*/
	
	/**
	 * Gets the ofertante.
	 *
	 * @return the ofertante
	 */
	public Cliente getOfertante() {
		return ofertante;
	}
	
	/**
	 * Gets the precio.
	 *
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 *
	 * @param precio the new precio
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
		this.moderada = false;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio the new fecha inicio
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
		this.moderada = false;
	}

	/**
	 * Gets the moderada.
	 *
	 * @return the moderada
	 */
	public Boolean getModerada() {
		return moderada;
	}

	/**
	 * Sets the moderada.
	 *
	 * @param moderada the new moderada
	 */
	public void setModerada(Boolean moderada) {
		this.moderada = moderada;
	}

	/**
	 * Gets the aprobada.
	 *
	 * @return the aprobada
	 */
	public Boolean getAprobada() {
		return aprobada;
	}

	/**
	 * Sets the aprobada.
	 *
	 * @param aprobada the new aprobada
	 */
	public void setAprobada(Boolean aprobada) {
		this.aprobada = aprobada;
	}
	
	/**
	 * Gets the reservada.
	 *
	 * @return the reservada
	 */
	public Boolean getReservada() {
		return reservada;
	}

	/**
	 * Sets the reservada.
	 *
	 * @param reservada the new reservada
	 */
	public void setReservada(Boolean reservada) {
		this.reservada = reservada;
	}

	/**
	 * Gets the contratada.
	 *
	 * @return the contratada
	 */
	public Boolean getContratada() {
		return contratada;
	}

	/**
	 * Sets the contratada.
	 *
	 * @param contratada the new contratada
	 */
	public void setContratada(Boolean contratada) {
		this.contratada = contratada;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		this.moderada = false;
	}
	
	/**
	 * Gets the comentarios.
	 *
	 * @return the comentarios
	 */
	public List<Comentario> getComentarios(){
		List<Comentario> comentarios = new ArrayList<Comentario>();
		for (Opinion opinion : opiniones) {
			if(opinion instanceof Comentario)
				comentarios.add((Comentario) opinion);
		}
		
		return Collections.unmodifiableList(comentarios);
	}

	/**
	 * Gets the demandante.
	 *
	 * @return the demandante
	 */
	public Cliente getDemandante() {
		return demandante;
	}

	/**
	 * Gets the opiniones.
	 *
	 * @return the opiniones
	 */
	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	/**
	 * Sets the demandante.
	 *
	 * @param demandante the demandante to set
	 */
	public void setDemandante(Cliente demandante) {
		this.demandante = demandante;
	}

	/**
	 * Gets the reserva.
	 *
	 * @return the reserva
	 */
	public Reserva getReserva() {
		return reserva;
	}
}
