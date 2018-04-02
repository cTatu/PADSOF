import java.time.LocalDate;

public abstract class Reserva {
	Boolean expirado;
	LocalDate fechaInicio;
	
	public Reserva(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
		this.expirado = false;
	}
	
	public boolean expirada() {
		return this.expirado;
	}	
	
	public int diasRestantes() {
		/*fecha simulada*/
		return 5;
	}
	
}
