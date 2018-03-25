import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Inmueble implements Serializable {
	
	private Integer codigoPostal;
	private static Integer LastId;
	private final Integer id;
	private String localizacion;
	private Map<String,String> caracteristicas;
	private List<Oferta> ofertas; 
	
	public Inmueble(Integer codigoPostal, String localizacion) {
		this.id = LastId;
		LastId ++;
		this.codigoPostal = codigoPostal;
		this.localizacion = localizacion;
		this.caracteristicas = new HashMap<String, String>();
		this.ofertas = new ArrayList<Oferta>();
	}
	
	public boolean añadirOferta(Integer precio, LocalDate fechaInicio, String descripcion, TipoOferta tipo) {
		boolean no_reservada = false, no_aprobada = false, no_contratada = false;
		
		if(tipo.equals(TipoOferta.VACACIONAL))
			ofertas.add(new OfertaVacacional(precio, fechaInicio, no_reservada, no_aprobada, no_contratada, descripcion));
		
		return true;
	}
}