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
 * 
 */
public class Inmueble implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4351471372769065186L;
	private Integer codigoPostal;
	private static Integer LastId = 1;
	private final Integer ID;
	private String localizacion;
	private Map<String,String> caracteristicas;
	private List<Oferta> ofertas;
	
	/**
	 * 
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
	 * 
	 *
	 * @return 
	 */
	public Integer getID() {
		return ID;
	}

	/**
	 * 
	 *
	 * @param ofertante 
	 * @param fechaInicio 
	 * @return 
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
	 * 
	 *
	 * @param oferta 
	 * @return 
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
	
	public boolean addRectificacion(Oferta oferta, Map<String, String> rectif) {
		for (Oferta ofertaLista : ofertas) {
			if (ofertaLista.equals(oferta)) {
				oferta.añadirRectificacion(rectif);
				return true;
			}
		}
		return false;
	}
	
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
	 * 
	 *
	 * @param oferta 
	 * @return 
	 */
	public boolean rechazarOferta(Oferta oferta) {
		if (!oferta.getAprobada())
			return ofertas.remove(oferta);
		return false;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public List<Oferta> getOfertas(){
		return Collections.unmodifiableList(ofertas);
	}

	/**
	 * 
	 *
	 * @param oferta 
	 * @return 
	 */
	public boolean addOfertas(Oferta oferta) {
		return ofertas.add(oferta);
	}

	/**
	 * 
	 *
	 * @return 
	 */
	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public Map<String,String> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(Map<String,String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
}