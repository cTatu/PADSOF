import java.util.*;

public class Ofertante extends Cliente{

	private List<Oferta> ofertas;
	
	public Ofertante(String NIF,String apellidos,String nombres,
			String contraseña, String tarjetaCredito) {
		super(nombres, NIF, apellidos, contraseña, tarjetaCredito, "O");
		ofertas = new ArrayList<Oferta>();
	}
	
	public void añadirOfertas(Oferta of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}

}
