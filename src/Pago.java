import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

public class Pago {
	private final int ID;
	static int LastID = 0;
	
	private Double cantidad;
	private Cliente demandante;
	private Cliente ofertante;
	private Oferta oferta;
	
	public Pago(Cliente demandante, Cliente ofertante, Oferta oferta) {
		ID = LastID;
		LastID++;
		
		this.cantidad = oferta.precio;
		this.ofertante = ofertante;
		this.demandante = demandante;
	}
	
	public boolean efectuarPago() {
		
		if (demandante.isBloqueado())
			return false;
		
		if (!TeleChargeAndPaySystem.isValidCardNumber(demandante.getTarjetaCredito())) {
			demandante.setBloqueado(true);
			return false;
		}
		
		if (!TeleChargeAndPaySystem.isValidCardNumber(ofertante.getTarjetaCredito())){
			ofertante.setBloqueado(true);
			ofertante.rolOfertante.setSaldoPendiente(cantidad);
			return false;
		}				
		
		try {
			TeleChargeAndPaySystem.charge(demandante.getTarjetaCredito(), "Cobro", -cantidad);
		}catch (FailedInternetConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrderRejectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			TeleChargeAndPaySystem.charge(ofertante.getTarjetaCredito(), "Pago", cantidad*0.);
		}catch (FailedInternetConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrderRejectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
