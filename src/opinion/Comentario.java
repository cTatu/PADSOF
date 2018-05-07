/*
 * @author David Pascual y Cristian Tatu
 */
package opinion;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import cliente.Demandante;

/**
 * Implementacion de comentario que hereda de Opinion y guarda a su vez las opiniones de los comentarios.
 */
public class Comentario extends Opinion implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7106442786135209718L;
	private static Integer LastId = 1;
	private final Integer ID;
	private final static Map<Integer,Comentario> todosComentarios = new HashMap<>();
	
	private Comentario padre = null;
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
		this.ID = LastId;
		Comentario.todosComentarios.put(this.ID,this);
		this.texto = texto;
		this.opiniones = new ArrayList<Opinion>();
		LastId ++;
	}

	/**
	 * Añade opinion la opinion
	 * @param opinion a aniadir
	 * @return true
	 * @see Opinion#opinar(Opinion)
	 */
	@Override
	public boolean opinar(Opinion o) {
		if (o.isComentario())
			((Comentario)o).setPadre(this);
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
	 * Gets the comentarios.
	 *
	 * @return los comentarios
	 */
	public List<Comentario> getComentarios() {
		return Collections.unmodifiableList(opiniones.stream()
                .filter(o -> o.isComentario())
                .map(o -> (Comentario) o)
                .collect(Collectors.toList()));
	}
	
	/**
	 * Getter del texto del comentario
	 *
	 * @return texto
	 */
	public String getTexto() {
		return texto;
	}

	@Override
	public boolean isComentario() {
		return true;
	}

	public Integer getID() {
		return ID;
	}

	public Comentario getPadre() {
		return padre;
	}
	
	public void setPadre(Comentario comentario) {
		this.padre = comentario;
	}

	public static Map<Integer,Comentario> getTodosComentarios() {
	    return Collections.unmodifiableMap(todosComentarios);
	}

}
