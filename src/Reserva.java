import java.time.LocalDate;

public abstract class Reserva {
	Boolean expirado = false;
	LocalDate fechaInicio;
	
	public Reserva(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public boolean expirada() {
		return this.expirado;
	}	
	
	public int diasRestantes() {
		/*fecha simulada*/
		return 5;
	}
	
}
