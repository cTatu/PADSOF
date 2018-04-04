import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Ofertante implements Serializable{

	private List<Oferta> ofertas;
	private Double saldoPendiente = 0.0;
	
	/**
	 * 
	 */
	public Ofertante() {
		ofertas = new ArrayList<Oferta>();
	}
	
	/**
	 * 
	 *
	 * @param of 
	 */
	public void añadirOfertas(OfertaVivienda of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public Double getSaldoPendiente() {
		return saldoPendiente;
	}
	
	/**
	 * 
	 *
	 * @param saldoPendiente 
	 */
	public void setSaldoPendiente(Double saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}
	
	/**
	 * 
	 *
	 * @param fechaInicio 
	 * @return 
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

}
