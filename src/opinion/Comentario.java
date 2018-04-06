/*
 * @author David Pascual y Cristian Tatu
 */
package opinion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cliente.Demandante;

// TODO: Auto-generated Javadoc
/**
 * Implementacion de comentario que hereda de Opinion y guarda a su vez las opiniones de los comentarios.
 *
 * @author David Pascual y Cristian Tatu
 */
public class Comentario extends Opinion implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7106442786135209718L;
	
	/** The texto. */
	private String texto;
	
	/** The opiniones. */
	private List<Opinion> opiniones;
	
	/**
	 * Constructor.
	 *
	 * @param demandante the demandante
	 * @param texto the texto
	 */
	public Comentario(Demandante demandante, String texto) {
		super(demandante);
		this.texto = texto;
		this.opiniones = new ArrayList<Opinion>();
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
		opiniones.add(o);
		return true;
	}

	/**
	 * Calcula la media de las valoraciones de este comentario. Si no existen valoraciones devuelve un -1.
	 *
	 * @return the double
	 */
	public Double calcularMedia() {
		Double total=0.0, media=0.0;
		int n=0;
		
		for(Opinion o: opiniones) {
			if(o instanceof Valoracion) {
				total += ((Valoracion)o).getNumero();
				n++;
			}
		}
		
		if(n==0) 
			return -1.0;
		
		media = total / n;
		
		return media;
	}

	/**
	 * Gets the opiniones.
	 *
	 * @return las opiniones
	 */
	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	/**
	 * Gets the texto.
	 *
	 * @return texto
	 */
	public String getTexto() {
		return texto;
	}

}
