import java.util.*;

public class Demandante extends Cliente{
	
	private List<Oferta> ofertasContratadas;
 
	public Demandante(String NIF,String apellidos,String nombres,
			String contrase�a, String tarjetaCredito) {
		super(nombres, NIF, apellidos, contrase�a, tarjetaCredito, "D");
		ofertasContratadas = new ArrayList<>();
	}
	
	public void a�adirOfertaContratada(Oferta ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}

}
