import java.io.Serializable;
import java.util.*;

public class Demandante implements Serializable{
	
	private List<OfertaVivienda> ofertasContratadas;
	private ReservaVacacional rVacacional;
	private ReservaAlquiler rAlquiler;
	
	public Demandante() {
		ofertasContratadas = new ArrayList<>();
	}		
	
	public void añadirOfertaContratada(OfertaVivienda ofertaContratada) {
		if ( ! ofertasContratadas.contains(ofertaContratada))
			ofertasContratadas.add(ofertaContratada);
	}
	
	public void eliminarReservaVacacional() {
		rVacacional = null;
	}
	
	public void eliminarReservaAlquiler() {
		rAlquiler = null;
	}

	public void setrVacacional(ReservaVacacional rVacacional) {
		this.rVacacional = rVacacional;
	}

	public void setrAlquiler(ReservaAlquiler rAlquiler) {
		this.rAlquiler = rAlquiler;
	}
}
