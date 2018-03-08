import java.util.*;

public class Ofertante extends Cliente{

	private List<Oferta> ofertas;
	
	public Ofertante(String NIF,String apellidos,String nombres,
			String contraseņa, String tarjetaCredito) {
		super(nombres, NIF, apellidos, contraseņa, tarjetaCredito, "O");
		ofertas = new ArrayList<Oferta>();
	}
	
	public void aņadirOfertas(Oferta of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}

}
