import java.io.Serializable;
import java.util.*;

public class Demandante implements Serializable{
	
	private List<OfertaVivienda> ofertasContratadas;
	private ReservaVacacional rVacacional;
	private ReservaVivienda rVivienda;
	
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
	
	public void eliminarReservaVivienda() {
		rVivienda = null;
	}
	
	public boolean getStatusVacacional() { 
		if(rVacacional==null)
			return false;
		else
			return true;
	}
	
	public boolean getStatusVivienda() { 
		if(rVivienda==null)
			return false;
		else
			return true;
	}

	public void setrVacacional(ReservaVacacional rVacacional) {
		this.rVacacional = rVacacional;
	}

	public void setrVivienda(ReservaVivienda rAlquiler) {
		this.rVivienda = rAlquiler;
	}
}
