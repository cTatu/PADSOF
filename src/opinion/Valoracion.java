/*
 * @author David Pascual y Cristian Tatu
 */
package opinion;
import java.io.Serializable;

import cliente.Demandante;

/**
 * Implementacion de la clase valoracion que hereda de la clase abstracta Opinion.
 */
public class Valoracion extends Opinion implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1213187773390893092L;

	private Double Numero;
	
	/**
	 * Constructor.
	 *
	 * @param demandante 
	 * @param numero 
	 */
	public Valoracion(Demandante demandante, Double numero) {
		super(demandante);
		if (numero > 5)
			this.Numero = 5.0;
		else
			this.Numero = numero;
	}

	/**
	 * @param opinion
	 * @return false ya que no se pueden opinar las valoraciones
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
