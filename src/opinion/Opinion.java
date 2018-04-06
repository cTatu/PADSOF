/*
 * @author David Pascual y Cristian Tatu
 */
package opinion;
import java.io.Serializable;

import cliente.Demandante;

// TODO: Auto-generated Javadoc
/**
 * Clase super (abstract) que implementa la clase opinion.
 *
 * @author David Pascual y Cristian Tatu
 */
public abstract class Opinion implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 46290625341616983L;
	
	/** The demandante. */
	private Demandante demandante;
	
	/**
	 * Constructor.
	 *
	 * @param demandante the demandante
	 */
	public Opinion(Demandante demandante) {
		this.setDemandante(demandante);
	}
	
	/**
	 * Genera una nueva opinión.
	 *
	 * @param o the o
	 * @return true, if successful
	 */
	public abstract boolean opinar(Opinion o);

	/**
	 * Gets the demandante.
	 *
	 * @return the demandante
	 */
	public Demandante getDemandante() {
		return demandante;
	}

	/**
	 * Sets the demandante.
	 *
	 * @param demandante the new demandante
	 */
	public void setDemandante(Demandante demandante) {
		this.demandante = demandante;
	}
}
