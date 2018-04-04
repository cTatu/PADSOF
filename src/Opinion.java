

/**
 * 
 */
public abstract class Opinion {
	
	private Demandante demandante;
	
	/**
	 * 
	 *
	 * @param demandante 
	 */
	public Opinion(Demandante demandante) {
		this.demandante = demandante;
	}
	
	/**
	 * 
	 *
	 * @param o 
	 * @return 
	 */
	public abstract boolean opinar(Opinion o);
}
