package cliente;
import java.io.Serializable;

/**
 * Implementacion de Cliente que contiene informacion sobre estos
 * @author David Pascual y Cristian Tatu
 */
public class Cliente implements Serializable{

	private String tarjetaCredito, nombres, apellidos, contraseña, NIF;
	public Ofertante rolOfertante;
	public Demandante rolDemandante;
	public boolean gerente;
	private boolean bloqueado;
	
	/**
	 * Constructor
	 * @param nombres 
	 * @param NIF 
	 * @param apellidos 
	 * @param contraseña 
	 * @param tarjetaCredito 
	 * @param rolOfertante 
	 * @param rolDemandante 
	 */
	public Cliente(String nombres, String NIF, String apellidos, 
				String contraseña,String tarjetaCredito,Ofertante rolOfertante, Demandante rolDemandante) {		
		this.rolOfertante = rolOfertante;
		this.rolDemandante = rolDemandante; 
		this.nombres = nombres;
		this.NIF = NIF;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
		this.tarjetaCredito = tarjetaCredito;
		this.bloqueado = false;
	}
	
	/**
	 * @return boolean, si un cliente está bloqueado
	 */
	public boolean isBloqueado() {
		return bloqueado;
	}
	
	/**
	 * @param bloqueado 
	 */
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	/**
	 * Cambia la tarjeta y desbloquea al cliente
	 * @param tarjetaNueva 
	 */
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
		this.bloqueado = false;
	}
	
	/**
	 * @return Tarjeta de credito
	 */
	public String getTarjetaCredito() {
		return this.tarjetaCredito;
	}
	
	/**
	 * @return NIF
	 */
	public String getNIF() {
		return this.NIF;
	}
	
	/**
	 * @return contraseña
	 */
	public String getContraseña() {
		return this.contraseña;
	}
	
	/**
	 * @param contraseña 
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String rol;
		
		if (rolDemandante != null) {
			if (rolOfertante != null)
				rol = "OD";
			else
				rol = "D";
		}else if (rolOfertante != null)
			rol = "O";
		else if (gerente == true)
			rol = "gerente";
		else
			rol = "invitado";
		
		return ( "Rol: " + rol + "\n" +
				 "NIF: " + NIF + "\n" +
				 "Contraseña: " + contraseña				
				);
	}
}