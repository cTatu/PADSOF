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
		this.setDemandante(demandante);
	}
	
	/**
	 * Genera una nueva opini�n
	 *
	 * @param o 
	 * @return 
	 */
	public abstract boolean opinar(Opinion o);

	public Demandante getDemandante() {
		return demandante;
	}

	public void setDemandante(Demandante demandante) {
		this.demandante = demandante;
	}
}
