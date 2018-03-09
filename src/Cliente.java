import java.io.Serializable;

public class Cliente implements Serializable{

	private String tarjetaCredito, nombres, apellidos, contraseña, NIF;
	private Ofertante rolOfertante;
	private Demandante rolDemandante;
	
	public Cliente(String nombres, String NIF, String apellidos, 
			String contraseña,String tarjetaCredito,String rol) {
		
		this.tarjetaCredito = tarjetaCredito;
	}

	
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
	}
}
