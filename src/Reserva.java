import java.time.LocalDate;

public abstract class Reserva {
	Boolean expirado;
	LocalDate fechaInicio;
	Integer diasRestantes; // final static?????
	
	public Reserva(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
}
