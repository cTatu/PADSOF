package comparator;

import java.util.Comparator;

import oferta.Oferta;

/**
 * Comparator para ordenar las b�squedas seg�n la fecha (m�s cercanas primero)
 * @author David Pascual y Cristian Tatu
 */
public class CompararFecha implements Comparator<Oferta>{
	
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Oferta a, Oferta b) {
		return a.getFechaInicio().compareTo(b.getFechaInicio());
	}
}