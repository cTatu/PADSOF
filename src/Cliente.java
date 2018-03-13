import java.io.Serializable;

public class Cliente implements Serializable{

	private String tarjetaCredito, nombres, apellidos, contraseņa, NIF;
	public Ofertante rolOfertante;
	public Demandante rolDemandante;
	
	public Cliente(String nombres, String NIF, String apellidos, 
				String contraseņa,String tarjetaCredito,Ofertante rolOfertante, Demandante rolDemandante) {
		
		this.rolOfertante = rolOfertante;
		this.rolDemandante = rolDemandante; 
		this.nombres = nombres;
		this.NIF = NIF;
		this.apellidos = apellidos;
		this.contraseņa = contraseņa;
		this.tarjetaCredito = tarjetaCredito;
	}

	
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
	}
}