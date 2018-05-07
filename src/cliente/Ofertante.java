/*
 * @author David Pascual y Cristian Tatu
 */
package cliente;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import inmueble.Inmueble;
import oferta.Oferta;

/**
 * Implementacion de la clase ofertante y sus funcionalidades.
 */
public class Ofertante implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6258867457223383549L;
	
	private List<Oferta> ofertas;
	private List<Inmueble> inmuebles;
	private Double saldoPendiente = 0.0;
	
	/**
	 * Constructor.
	 */
	public Ofertante() {
		ofertas = new ArrayList<Oferta>();
		inmuebles = new ArrayList<Inmueble>();
	}
	
	/**
	 * Aniade una nueva oferta a su lista de ofertas publicadas.
	 *
	 * @param oferta a aniadir
	 */
	public void aniadirOfertas(Oferta of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}
	
	/**
	 * Aniade un nuevo inmueble a la lista.
	 *
	 * @param inmueble Objeto inmueble creado
	 * @return true, if successful
	 */
	public boolean aniadirInmuebles(Inmueble inmueble) {
		if ( ! inmuebles.contains(inmueble))
			return inmuebles.add(inmueble);
		return false;
	}
	
	/**
	 * Gets the saldo pendiente.
	 *
	 * @return saldoPendiente (Pago pendiente por recibir)
	 */
	public Double getSaldoPendiente() {
		return saldoPendiente;
	}
	
	/**
	 * Establece nuevo saldo pendiente por recibir.
	 *
	 * @param saldoPendiente (Pago pendiente por recibir)
	 */
	public void setSaldoPendiente(Double saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}
	
	/**
	 * Cancela una oferta borrnindola de la lista de ofertas publicadas.
	 *
	 * @param fechaInicio de la oferta
	 * @return boolean
	 */
	public boolean cancelarOferta(LocalDate fechaInicio) {
		for (Oferta oferta : ofertas) {
			if (oferta.getFechaInicio().isEqual(fechaInicio)) {
				ofertas.remove(oferta);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the ofertas.
	 *
	 * @return lista de ofertas del ofertante
	 */
	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

}
