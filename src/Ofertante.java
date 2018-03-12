import java.io.Serializable;
import java.util.*;

public class Ofertante implements Serializable{

	private List<Oferta> ofertas;
	
	public Ofertante() {
		ofertas = new ArrayList<Oferta>();
	}
	
	public void a�adirOfertas(Oferta of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}

}
