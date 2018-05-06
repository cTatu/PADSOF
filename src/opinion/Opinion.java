/*
 * @author David Pascual y Cristian Tatu
 */
package opinion;
import java.io.Serializable;

import cliente.Demandante;

/**
 * Clase super (abstract) que implementa la clase opinion.
 */
public abstract class Opinion implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 46290625341616983L;

	private Demandante demandante;
	
	/**
	 * Constructor.
	 *
	 * @param demandante 
	 */
	public Opinion(Demandante demandante) {
		this.setDemandante(demandante);
	}
	
	/**
	 * Genera una nueva opininin de una opinion
	 *
	 * @param opinion
	 * @return true si es un comentario, false si es una valoracinin ( no se puede)
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

	public abstract boolean isComentario();
}
