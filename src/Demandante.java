import java.io.Serializable;
import java.util.*;

public class Demandante implements Serializable{
	
	private List<OfertaVivienda> ofertasContratadas;
	
	public Demandante() {

		ofertasContratadas = new ArrayList<>();
	}		
	
	public void aņadirOfertaContratada(OfertaVivienda ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}
}
