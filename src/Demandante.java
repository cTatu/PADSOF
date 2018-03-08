import java.util.*;

public class Demandante extends Cliente{
	
	private List<Oferta> ofertasContratadas;
 
	public Demandante(String NIF,String apellidos,String nombres,
			String contraseña, String tarjetaCredito) {
		super(nombres, NIF, apellidos, contraseña, tarjetaCredito, "D");
		ofertasContratadas = new ArrayList<>();
	}
	
	public void añadirOfertaContratada(Oferta ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}

}
