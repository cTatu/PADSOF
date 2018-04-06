/*
 * @author David Pascual y Cristian Tatu
 */
package opinion;
import java.io.Serializable;

import cliente.Demandante;

// TODO: Auto-generated Javadoc
/**
 * Implementacion de la clase valoracion.
 *
 * @author David Pascual y Cristian Tatu
 */
public class Valoracion extends Opinion implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1213187773390893092L;
	
	/** The Numero. */
	private Double Numero;
	
	/**
	 * Constructor.
	 *
	 * @param demandante the demandante
	 * @param numero the numero
	 */
	public Valoracion(Demandante demandante, Double numero) {
		super(demandante);
		if (numero > 5)
			this.Numero = 5.0;
		else
			this.Numero = numero;
	}

	/**
	 * Opinar.
	 *
	 * @param o the o
	 * @return true, if successful
	 * @see Opinion#opinar(Opinion)
	 */
	@Override
	public boolean opinar(Opinion o) { 
		return false;
	}

	/**
	 * Gets the numero.
	 *
	 * @return  numero (valoracion)
	 */
	public Double getNumero() {
		return Numero;
	}
}
