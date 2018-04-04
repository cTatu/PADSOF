package comparator;

import java.util.Comparator;

import oferta.Oferta;

/**
 * Comparator para ordenar las búsquedas según el precio (menor a mayor)
 * @author David Pascual y Cristian Tatu
 */
public class CompararPrecio implements Comparator<Oferta>{
	
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Oferta a, Oferta b) {
		if(a.getPrecio() > b.getPrecio()) return 1;
		else if(a.getPrecio() < b.getPrecio()) return -1;
		return 0;
	}
}
