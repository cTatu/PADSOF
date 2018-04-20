/*
 * @author David Pascual y Cristian Tatu
 */
package opinion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cliente.Demandante;

/**
 * Implementacion de comentario que hereda de Opinion y guarda a su vez las opiniones de los comentarios.
 */
public class Comentario extends Opinion implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7106442786135209718L;

	private String texto;
	private List<Opinion> opiniones;
	
	/**
	 * Constructor.
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
	 * Añade opinion la opinion
	 * @param opinion a aniadir
	 * @return true
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
	 * @return media calculada
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
		return Collections.unmodifiableList(opiniones);
	}

	/**
	 * Getter del texto del comentario
	 *
	 * @return texto
	 */
	public String getTexto() {
		return texto;
	}

}
