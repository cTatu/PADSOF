/*
 * @author David Pascual y Cristian Tatu
 */
package oferta;
import java.io.Serializable;
import java.time.LocalDate;

import cliente.Cliente;
import reserva.Reserva;
import reserva.ReservaVivienda;
import tipos.TipoOferta;

/**
 * Clase OfertaVivienda que hereda de la clase Oferta abstracta
 */
public class OfertaVivienda extends Oferta implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3472125510702214429L;

	private Integer duracionMeses;
	public static final Double COMISION = 1.001; // Porcentaje
	private Double fianza;
	
	/**
	 * Constructor
	 *
	 * @param precio 
	 * @param fechaInicio 
	 * @param descripcion
	 * @param duracionMeses
	 * @param ofertante
	 * @param fianza
	 */
	public OfertaVivienda(Double precio, LocalDate fechaInicio, String descripcion, Integer duracionMeses, Cliente ofertante, Double fianza) {
		super(precio,fechaInicio,descripcion, ofertante);
		this.duracionMeses = duracionMeses;
		this.setFianza(fianza);
	}
	
	/**
	 * Gets the duracion meses.
	 *
	 * @return the duracion meses
	 */
	public Integer getDuracionMeses() {
		return this.duracionMeses;
	}

	/**
	 * @see Oferta#calcularComision()
	 */
	@Override
	public Double calcularComision() {
		return precio*COMISION;
	}

	/**
	 * @see Oferta#expirar()
	 */
	@Override
	public boolean expirar() {
		return false;
	}
	
	/**
	 * @see Oferta#reservar(Cliente)
	 */
	@Override
	public boolean reservar(Reserva reserva, Cliente demandante) {
		if (reservada == false && demandante.rolDemandante.getStatusVivienda() == false ) {
			reservada = true;
			this.demandante = demandante;
			demandante.rolDemandante.setrVivienda((ReservaVivienda)reserva);
			return true;
		}
		
		return false;
	}
	
	/**
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

	/**
	 * Gets the fianza.
	 *
	 * @return the fianza
	 */
	public Double getFianza() {
		return fianza;
	}

	/**
	 * Sets the fianza.
	 *
	 * @param fianza the new fianza
	 */
	public void setFianza(Double fianza) {
		this.fianza = fianza;
	}

	@Override
	public TipoOferta getTipo() {
		return TipoOferta.VIVIENDA;
	}

	@Override
	public boolean isVacacional() {
		return false;
	}
}
