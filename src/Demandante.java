import java.util.*;

public class Demandante extends Cliente{
	
	private List<Oferta> ofertasContratadas;
 
	public Demandante(String NIF,String apellidos,String nombres,
			String contraseņa, String tarjetaCredito) {
		super(nombres, NIF, apellidos, contraseņa, tarjetaCredito, "D");
		ofertasContratadas = new ArrayList<>();
	}
	
	public void aņadirOfertaContratada(Oferta ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}

}
