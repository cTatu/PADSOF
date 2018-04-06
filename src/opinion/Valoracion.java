package opinion;
import cliente.Demandante;

/**
 * Implementacion de la clase valoracion
 * @author David Pascual y Cristian Tatu
 */
public class Valoracion extends Opinion {
	
	private Double Numero;
	
	/**
	 * Constructor
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
	 * @see Opinion#opinar(Opinion)
	 */
	@Override
	public boolean opinar(Opinion o) { 
		return false;
	}

	/**
	 * @return  numero (valoracion)
	 */
	public Double getNumero() {
		return Numero;
	}
}
