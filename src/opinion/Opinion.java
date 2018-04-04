package opinion;
import cliente.Demandante;

/**
 * Clase super (abstract) que implementa la clase opinion
 * @author David Pascual y Cristian Tatu
 */
public abstract class Opinion {
	
	private Demandante demandante;
	
	/**
	 * Constructor
	 * @param demandante 
	 */
	public Opinion(Demandante demandante) {
		this.demandante = demandante;
	}
	
	/**
	 * Genera una nueva opinión
	 *
	 * @param o 
	 * @return 
	 */
	public abstract boolean opinar(Opinion o);
}
