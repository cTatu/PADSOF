import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

/**
 * 
 */
public class Pago {
	private final int ID;
	static int LastID = 0;
	
	private Double cantidadCobro, cantidadPago;
	private Cliente demandante, ofertante;
	
	/**
	 * 
	 *
	 * @param demandante 
	 * @param ofertante 
	 * @param oferta 
	 */
	public Pago(Cliente demandante, Cliente ofertante, Oferta oferta) {
		ID = LastID;
		LastID++;
		
		this.cantidadCobro = oferta.calcularComision();
		this.cantidadPago = oferta.precio;
		
		this.ofertante = ofertante;
		this.demandante = demandante;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public int getID() {
		return this.ID;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public boolean efectuarPago() {
		
		
	}
}
