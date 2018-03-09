import java.util.*;

public class Ofertante {

	private List<Oferta> ofertas;
	
	public Ofertante(String NIF,String apellidos,String nombres,
			String contraseña, String tarjetaCredito) {
		ofertas = new ArrayList<Oferta>();
	}
	
	public void añadirOfertas(Oferta of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}

}
