package opinion;
import cliente.Demandante;

/**
 * Implementacion de la clase valoracion
 * @author David Pascual y Cristian Tatu
 */
public class Valoracion extends Opinion {
	
	private double Numero;
	
	/**
	 * Constructor
	 *
	 * @param demandante 
	 * @param numero 
	 */
	public Valoracion(Demandante demandante, double numero) {
		super(demandante);
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
	public double getNumero() {
		return Numero;
	}
}
