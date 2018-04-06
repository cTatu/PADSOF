package cliente;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import oferta.Oferta;

/**
 * Implementacion de la clase ofertante y sus funcionalidades
 * @author David Pascual y Cristian Tatu
 */
public class Ofertante implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6258867457223383549L;
	private List<Oferta> ofertas;
	private Double saldoPendiente = 0.0;
	
	/**
	 * Constructor
	 */
	public Ofertante() {
		ofertas = new ArrayList<Oferta>();
	}
	
	/**
	 * Añade una nueva oferta a su lista de ofertas publicadas
	 *
	 * @param oferta de vivienda
	 */
	public void añadirOfertas(Oferta of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}
	
	/**
	 * @return saldoPendiente (Pago pendiente por recibir)
	 */
	public Double getSaldoPendiente() {
		return saldoPendiente;
	}
	
	/**
	 * Establece nuevo saldo pendiente por recibir
	 *
	 * @param saldoPendiente (Pago pendiente por recibir)
	 */
	public void setSaldoPendiente(Double saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}
	
	/**
	 * Cancela una oferta borrándola de la lista de ofertas publicadas
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
	 * @return lista de ofertas del ofertante
	 */
	public List<Oferta> getOfertas() {
		return ofertas;
	}

}
