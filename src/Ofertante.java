import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Ofertante implements Serializable{

	private List<Oferta> ofertas;
	private Double saldoPendiente = 0.0;
	
	public Ofertante() {
		ofertas = new ArrayList<Oferta>();
	}
	
	public void añadirOfertas(OfertaVivienda of) {
		if ( ! ofertas.contains(of))
			ofertas.add(of);
	}
	
	public Double getSaldoPendiente() {
		return saldoPendiente;
	}
	
	public void setSaldoPendiente(Double saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}
	
	public boolean cancelarOferta(LocalDate fechaInicio) {
		for (Oferta oferta : ofertas) {
			if (oferta.getFechaInicio().isEqual(fechaInicio)) {
				ofertas.remove(oferta);
				return true;
			}
		}
		return false;
	}

}
