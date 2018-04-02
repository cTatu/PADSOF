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
		
		TeleChargeAndPaySystem.charge(demandante.getTarjetaCredito(), "Cobro", -cantidad);
		
		TeleChargeAndPaySystem.charge(ofertante.getTarjetaCredito(), "Pago", cantidad*0.);
	}
}
