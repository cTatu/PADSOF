import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Inmueble implements Serializable {
	
	private int codigoPostal;
	private static int LastId;
	private final int id;
	private String localizacion;
	private Map<String,String> caracteristicas;
	private List<Oferta> ofertas;
	
	public Inmueble(int codigoPostal, String localizacion) {
		this.id = LastId;
		LastId ++;
		this.codigoPostal = codigoPostal;
		this.localizacion = localizacion;
		this.caracteristicas = new HashMap<String, String>();
		this.ofertas = new ArrayList<Oferta>();
	}
	
	public boolean añadirOferta(int precio, LocalDate fechaInicio, String descripcion) {
		
	}
}