import java.util.*;

public class Ofertante {

	private List<Oferta> ofertas;
	
	public Ofertante(String NIF,String apellidos,String nombres,
			String contrase�a, String tarjetaCredito) {
		ofertas = new ArrayList<Oferta>();
	}
	
	public void a�adirOfertas(Oferta of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}

}
