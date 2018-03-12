import java.io.Serializable;
import java.util.*;

public class Demandante implements Serializable{
	
	private List<Oferta> ofertasContratadas;
	
	public Demandante() {

		ofertasContratadas = new ArrayList<>();
	}		
	
	public void a�adirOfertaContratada(Oferta ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}

}
