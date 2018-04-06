package opinion;
import java.util.ArrayList;
import java.util.List;

import cliente.Demandante;

/**
 * Implementacion de comentario que hereda de Opinion y guarda a su vez las opiniones de los comentarios
 * @author David Pascual y Cristian Tatu
 */
public class Comentario extends Opinion {

	private String texto;
	private List<Opinion> opiniones;
	
	/**
	 * Constructor
	 *
	 * @param demandante 
	 * @param texto 
	 */
	public Comentario(Demandante demandante, String texto) {
		super(demandante);
		this.texto = texto;
		this.opiniones = new ArrayList<Opinion>();
	}

	/**
	 * @see Opinion#opinar(Opinion)
	 */
	@Override
	public boolean opinar(Opinion o) {
		opiniones.add(o);
		return true;
	}

	/**
	 * Calcula la media de las valoraciones de este comentario. Si no existen valoraciones devuelve un -1.
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
		
		if(n==0) return -1;
		
		media = total / n;
		
		return media;
	}

	/**
	 * @return las opiniones
	 */
	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	/**
	 * @return texto
	 */
	public String getTexto() {
		return texto;
	}

}
