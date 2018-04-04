import java.util.Comparator;

//fechas mas cercanas primero

/**
 * 
 */
public class CompararFecha implements Comparator<Oferta>{
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Oferta a, Oferta b) {
		return a.getFechaInicio().compareTo(b.getFechaInicio());
	}
}