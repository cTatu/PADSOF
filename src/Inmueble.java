import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Inmueble implements Serializable {
	
	private Integer codigoPostal;
	private static Integer LastId = 1;
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
	
	public boolean añadirOfertaVivienda(Double precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses ) {
		ofertas.add(new OfertaVivienda(precio, fechaInicio, descripcion, duracionMeses));
		return true;
	}
	
	public boolean añadirOfertaVacacional(Double precio, LocalDate fechaInicio, String descripcion, LocalDate fechaFin ) {
		ofertas.add(new OfertaVacacional(precio, fechaInicio, descripcion, fechaFin));
		return true;
	}
	
	public boolean cancelarOferta(Ofertante ofertante, LocalDate fechaInicio) {
		boolean status;
		for (Oferta oferta : ofertas) {
			if (oferta.getOfertante().equals(ofertante) && oferta.getFechaInicio().isEqual(fechaInicio)) {
				ofertas.remove(oferta);
				status = ofertante.cancelarOferta(fechaInicio);
				return status && true;
			}
		}
		return false;
	}
}