import java.util.Comparator;

//valoraciones mas altas primero

/**
 * 
 */
public class CompararValoracion implements Comparator<Oferta>{
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Oferta a, Oferta b) {
		if(a.calcularMedia() > b.calcularMedia()) return -1;
		else if(a.calcularMedia() < b.calcularMedia()) return 1;
		return 0;
	}
}