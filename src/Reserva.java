import java.time.LocalDate;

/**
 * 
 */
public abstract class Reserva {
	Boolean expirado;
	LocalDate fechaInicio;
	
	/**
	 * 
	 *
	 * @param fechaInicio 
	 */
	public Reserva(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
		this.expirado = false;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public boolean expirada() {
		return this.expirado;
	}	
	
	/**
	 * 
	 *
	 * @return 
	 */
	public int diasRestantes() {
		/*fecha simulada*/
		return 5;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public boolean contratar() {
		
	}
	
}
