package oferta;
import java.io.Serializable;
import java.time.LocalDate;

import cliente.Cliente;
import reserva.Reserva;
import tipos.TipoOferta;

/**
 * 
 */
public class OfertaVivienda extends Oferta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3472125510702214429L;
	private Integer duracionMeses;
	private static final Double COMISION = 1.001; // Porcentaje
	private Double fianza;
	
	/**
	 * 
	 *
	 * @param precio 
	 * @param fechaInicio 
	 * @param descripcion 
	 * @param duracionMeses 
	 * @param ofertante 
	 */
	public OfertaVivienda(Double precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses, Cliente ofertante, Double fianza) {
		super(precio,fechaInicio,descripcion, ofertante);
		this.duracionMeses = duracionMeses;
		this.setFianza(fianza);
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
	public Double calcularComision() {
		return precio*COMISION;
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
	public boolean reservar(Reserva reserva, Cliente demandante) {
		if (reservada == false && demandante.rolDemandante.getStatusVivienda() == false ) {
			reservada = true;
			this.demandante = demandante;
			demandante.rolDemandante.añadirReserva(reserva);
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
			demandante.rolDemandante.eliminarReserva(TipoOferta.VIVIENDA);
			demandante = null;
			reserva = null;
			reservada = false;
			return true;
		}

		return false;
	}

	public Double getFianza() {
		return fianza;
	}

	public void setFianza(Double fianza) {
		this.fianza = fianza;
	}
}
