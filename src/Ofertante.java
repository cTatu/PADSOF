import java.io.Serializable;
import java.util.*;

public class Ofertante implements Serializable{

	private List<OfertaAlquiler> ofertas;
	
	public Ofertante() {
		ofertas = new ArrayList<OfertaAlquiler>();
	}
	
	public void a�adirOfertas(OfertaAlquiler of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}

}
