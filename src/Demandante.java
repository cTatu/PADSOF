import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Demandante implements Serializable{
	
	private List<OfertaVivienda> ofertasContratadas;
	private ReservaVacacional rVacacional;
	private ReservaVivienda rVivienda;
	
	/**
	 * 
	 */
	public Demandante() {
		ofertasContratadas = new ArrayList<>();
	}		
	
	/**
	 * 
	 *
	 * @param ofertaContratada 
	 */
	public void añadirOfertaContratada(OfertaVivienda ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}
	
	/**
	 * 
	 */
	public void eliminarReservaVacacional() {
		rVacacional = null;
	}
	
	/**
	 * 
	 */
	public void eliminarReservaVivienda() {
		rVivienda = null;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public boolean getStatusVacacional() { 
		if(rVacacional==null)
			return false;
		else
			return true;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public boolean getStatusVivienda() { 
		if(rVivienda==null)
			return false;
		else
			return true;
	}

	/**
	 * 
	 *
	 * @param rVacacional 
	 */
	public void setrVacacional(ReservaVacacional rVacacional) {
		this.rVacacional = rVacacional;
	}

	/**
	 * 
	 *
	 * @param rAlquiler 
	 */
	public void setrVivienda(ReservaVivienda rAlquiler) {
		this.rVivienda = rAlquiler;
	}
}
