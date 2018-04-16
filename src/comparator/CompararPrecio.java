/*
 * @author David Pascual y Cristian Tatu
 */
package comparator;

import java.util.Comparator;

import oferta.Oferta;

/**
 * Comparator para ordenar las bnisquedas segnin el precio (menor a mayor).
 */
public class CompararPrecio implements Comparator<Oferta>{
	
	/**
	 * Comparator
	 *
	 * @param oferta a
	 * @param oferta b
	 * @return comparacion
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Oferta a, Oferta b) {
		if(a.getPrecio() < b.getPrecio()) 
			return 1;
		else if(a.getPrecio() > b.getPrecio()) 
			return -1;
		return 0;
	}
}
