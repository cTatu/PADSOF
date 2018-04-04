import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
class Comentario extends Opinion {

	private String texto;
	private List<Opinion> opiniones;
	
	/**
	 * 
	 *
	 * @param demandante 
	 * @param texto 
	 */
	public Comentario(Demandante demandante, String texto) {
		super(demandante);
		this.texto = texto;
		this.opiniones = new ArrayList<Opinion>();
	}

	/* (non-Javadoc)
	 * @see Opinion#opinar(Opinion)
	 */
	@Override
	public boolean opinar(Opinion o) {
		opiniones.add(o);
		return true;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public double calcularMedia() {
		double total=0.0, media=0.0;
		int n=0;
		
		for(Opinion o: opiniones) {
			if(o instanceof Valoracion) {
				total += ((Valoracion)o).getNumero();
				n++;
			}
		}
		media = total / n;
		
		return media;
	}

}
