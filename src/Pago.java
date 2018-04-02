import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

public class Pago {
	private final int ID;
	static int LastID = 0;
	
	private Double cantidadCobro, cantidadPago;
	private Cliente demandante, ofertante;
	
	public Pago(Cliente demandante, Cliente ofertante, Oferta oferta) {
		ID = LastID;
		LastID++;
		
		this.cantidadCobro = oferta.calcularComision();
		this.cantidadPago = oferta.precio;
		
		this.ofertante = ofertante;
		this.demandante = demandante;
	}
	
	public int getID() {
		return this.ID;
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
			ofertante.rolOfertante.setSaldoPendiente(cantidadPago);
			return false;
		}				
		
		for (int i = 0; i < 5; i++) {
			try {
				TeleChargeAndPaySystem.charge(demandante.getTarjetaCredito(), "Cobro: " + -cantidadCobro, -cantidadCobro);
				break;
			}catch (FailedInternetConnectionException e) {
				if (i == 4)
					e.printStackTrace();
				continue;
			} catch (OrderRejectedException e) {
				demandante.setBloqueado(true);
				return false;
			}
		}
		
		for (int i = 0; i < 5; i++) {
			try {
				TeleChargeAndPaySystem.charge(ofertante.getTarjetaCredito(), "Pago: " + cantidadPago, cantidadPago);
				return true;
			}catch (FailedInternetConnectionException e) {
				if (i == 4)
					return false;
				continue;
			} catch (OrderRejectedException e) {
				ofertante.setBloqueado(true);
				return false;
			}
		}
		
		return false;
	}
}
