import java.io.Serializable;
import java.util.*;

public class Demandante implements Serializable{
	
	private List<OfertaAlquiler> ofertasContratadas;
	
	public Demandante() {

		ofertasContratadas = new ArrayList<>();
	}		
	
	public void añadirOfertaContratada(OfertaAlquiler ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}

}
