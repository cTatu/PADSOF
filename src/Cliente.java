import java.io.Serializable;

public class Cliente implements Serializable{

	private String tarjetaCredito, nombres, apellidos, contraseña, NIF;
	public Ofertante rolOfertante;
	public Demandante rolDemandante;
	
	public Cliente(String nombres, String NIF, String apellidos, 
				String contraseña,String tarjetaCredito,Ofertante rolOfertante, Demandante rolDemandante) {
		
		this.rolOfertante = rolOfertante;
		this.rolDemandante = rolDemandante; 
		this.nombres = nombres;
		this.NIF = NIF;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
		this.tarjetaCredito = tarjetaCredito;
	}

	
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
	}
}