import java.util.*;

public class Demandante{
	
	private List<Oferta> ofertasContratadas;
 
	public Demandante(String NIF,String apellidos,String nombres,
			String contraseña, String tarjetaCredito) {
		ofertasContratadas = new ArrayList<>();
	}
	
	public void añadirOfertaContratada(Oferta ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}

}
