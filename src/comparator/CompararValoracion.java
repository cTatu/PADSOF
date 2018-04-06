package comparator;

import java.util.Comparator;

import oferta.Oferta;

/**
 * Comparator para ordenar las búsquedas según las valoraciones (de mayor a menor)
 * @author David Pascual y Cristian Tatu
 */
public class CompararValoracion implements Comparator<Oferta>{
	
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Oferta a, Oferta b) {
		return b.calcularMedia().compareTo(a.calcularMedia());
	}
}