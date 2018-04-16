/*
 * @author David Pascual y Cristian Tatu
 */
package inmueble;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cliente.Cliente;
import oferta.Oferta;

/**
 * Implementacinin de Inmueble y manejo de sus caracternisticas
 */
public class Inmueble implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4351471372769065186L;
	
	private Integer codigoPostal;
	private static Integer LastId = 1;
	private final Integer ID;
	private String localizacion;
	private Map<String,String> caracteristicas;
	private List<Oferta> ofertas;
	
	/**
	 * Constructor
	 *
	 * @param codigoPostal
	 * @param localizacion 
	 * @param caracteristicas
	 */
	public Inmueble(Integer codigoPostal, String localizacion, Map<String,String> caracteristicas) {
		this.ID = LastId;
		LastId ++;
		this.codigoPostal = codigoPostal;
		this.setLocalizacion(localizacion);
		this.setCaracteristicas(caracteristicas);
		this.ofertas = new ArrayList<Oferta>();
	}
	
	/**
	 * Devuelve el ID del inmueble.
	 *
	 * @return ID Integer
	 */
	public Integer getID() {
		return ID;
	}

	/**
	 * Cancelar oferta de este imnueble.
	 *
	 * @param ofertante de la oferta
	 * @param fechaInicio 
	 * @return true si se elimina, false de los contrario
	 */
	public boolean cancelarOferta(Cliente ofertante, LocalDate fechaInicio) {
		boolean status;
		for (Oferta oferta : ofertas) {
			if (oferta.getOfertante().equals(ofertante) && oferta.getFechaInicio().isEqual(fechaInicio)) {
				ofertas.remove(oferta);
				status = ofertante.rolOfertante.cancelarOferta(fechaInicio);
				return status && true;
			}
		}
		return false;
	}
	
	/**
	 * Mnitodo para que el gerente pueda aprobar una oferta pasada por parnimetro
	 *
	 * @param oferta a moderar
	 * @return true si encuentra la oferta, false de lo contrario
	 */
	public boolean aprobarOferta(Oferta oferta) {
		for (Oferta ofertaLista : ofertas) {
			if (ofertaLista.equals(oferta)) {
				oferta.setAprobada(true);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Aniade una/s rectificacion/es a la ofertaa pasada por parametro
	 *
	 * @param oferta the oferta
	 * @param rectif the rectif
	 * @return true si se han aniadido, false si no ha encontrado la oferta
	 */
	public boolean addRectificacion(Oferta oferta, Map<String, String> rectif) {
		for (Oferta ofertaLista : ofertas) {
			if (ofertaLista.equals(oferta)) {
				oferta.aniadirRectificacion(rectif);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Elimina ofertas expiradas.
	 *
	 * @return numero de ofertas eliminadas
	 */
	public Integer eliminarOfertasExpiradas() {
		Integer ofExp = 0;
		
		for (Iterator<Oferta> iterator = ofertas.iterator(); iterator.hasNext(); ) {
		    Oferta oferta = iterator.next();
		    if (oferta.expirar()) {
		        iterator.remove();
		        ofExp++;
		    }
		}
		return ofExp;	
	}
	
	
	/**
	 * Mnitodo para que el gerente pueda rechazar una oferta pasada por parnimetro
	 *
	 * @param oferta a rechazar
	 * @return true si se rechaza, false si la oferta no se ha encontrado
	 */
	public boolean rechazarOferta(Oferta oferta) {
		if (!oferta.getAprobada())
			return ofertas.remove(oferta);
		return false;
	}
	
	/* Getters y Setters */
	
	/**
	 * Gets the ofertas.
	 *
	 * @return the ofertas
	 */
	public List<Oferta> getOfertas(){
		return Collections.unmodifiableList(ofertas);
	}

	/**
	 * Adds the ofertas.
	 *
	 * @param oferta the oferta
	 * @return true, if successful
	 */
	public boolean addOfertas(Oferta oferta) {
		if (!ofertas.contains(oferta))
			return ofertas.add(oferta);
		return false;
	}

	/**
	 * Gets the codigo postal.
	 *
	 * @return the codigo postal
	 */
	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Gets the caracteristicas.
	 *
	 * @return the caracteristicas
	 */
	public Map<String,String> getCaracteristicas() {
		return caracteristicas;
	}

	/**
	 * Sets the caracteristicas.
	 *
	 * @param caracteristicas the caracteristicas
	 */
	public void setCaracteristicas(Map<String,String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	/**
	 * Gets the localizacion.
	 *
	 * @return the localizacion
	 */
	public String getLocalizacion() {
		return localizacion;
	}

	/**
	 * Sets the localizacion.
	 *
	 * @param localizacion the new localizacion
	 */
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
}