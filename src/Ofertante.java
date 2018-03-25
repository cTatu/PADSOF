import java.io.Serializable;
import java.util.*;

public class Ofertante implements Serializable{

	private List<OfertaVivienda> ofertas;
	
	public Ofertante() {
		ofertas = new ArrayList<OfertaVivienda>();
	}
	
	public void a�adirOfertas(OfertaVivienda of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}

}
