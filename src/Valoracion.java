
/**
 * 
 */
public class Valoracion extends Opinion {
	
	private double Numero;
	
	/**
	 * 
	 *
	 * @param demandante 
	 * @param numero 
	 */
	public Valoracion(Demandante demandante, double numero) {
		super(demandante);
		this.Numero = numero;
	}

	/* (non-Javadoc)
	 * @see Opinion#opinar(Opinion)
	 */
	@Override
	public boolean opinar(Opinion o) { 
		return false;
	}

	/**
	 * 
	 *
	 * @return 
	 */
	public double getNumero() {
		return Numero;
	}
}
