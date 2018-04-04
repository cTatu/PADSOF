import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 */
public class OfertaVivienda extends Oferta implements Serializable{

	private Integer duracionMeses;
	private static final Double COMISION = 0.1; // Porcentaje
	
	/**
	 * 
	 *
	 * @param precio 
	 * @param fechaInicio 
	 * @param descripcion 
	 * @param duracionMeses 
	 * @param ofertante 
	 */
	public OfertaVivienda(Double precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses, Cliente ofertante) {
		super(precio,fechaInicio,descripcion, ofertante);
		this.duracionMeses = duracionMeses;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public Integer getDuracionMeses() {
		return this.duracionMeses;
	}

	/* (non-Javadoc)
	 * @see Oferta#calcularComision()
	 */
	@Override
	public double calcularComision() {
		double comisionEuros = 0.0;
		comisionEuros = (COMISION * precio) / 100;
		return comisionEuros;
	}

	/* (non-Javadoc)
	 * @see Oferta#expirar()
	 */
	@Override
	public boolean expirar() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see Oferta#reservar(Cliente)
	 */
	@Override
	public boolean reservar(Cliente demandante) {
		if (reservada == false && demandante.rolDemandante.getStatusVivienda() == false ) {
			reservada = true;
			ReservaVivienda reserva = new ReservaVivienda(java.time.LocalDate.now()); // fecha simulada?
			this.demandante = demandante;
			this.demandante.rolDemandante.setrVivienda(reserva);
			return true;
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see Oferta#cancelarReserva()
	 */
	@Override
	public boolean cancelarReserva() {		
		if(reservada==true && demandante.rolDemandante.getStatusVivienda() == true) {
			demandante.rolDemandante.eliminarReservaVivienda();
			demandante = null;
			reserva = null;
			reservada = false;
			return true;
		}

		return false;
	}
}
