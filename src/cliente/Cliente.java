package cliente;
import java.io.Serializable;

/**
 * Implementacion de Cliente que contiene informacion sobre estos
 * @author David Pascual y Cristian Tatu
 */
public class Cliente implements Serializable{

	private String tarjetaCredito, nombres, apellidos, contrase�a, NIF;
	public Ofertante rolOfertante;
	public Demandante rolDemandante;
	public boolean gerente;
	private boolean bloqueado;
	
	/**
	 * Constructor
	 * @param nombres 
	 * @param NIF 
	 * @param apellidos 
	 * @param contrase�a 
	 * @param tarjetaCredito 
	 * @param rolOfertante 
	 * @param rolDemandante 
	 */
	public Cliente(String nombres, String NIF, String apellidos, 
				String contrase�a,String tarjetaCredito,Ofertante rolOfertante, Demandante rolDemandante) {		
		this.rolOfertante = rolOfertante;
		this.rolDemandante = rolDemandante; 
		this.nombres = nombres;
		this.NIF = NIF;
		this.apellidos = apellidos;
		this.contrase�a = contrase�a;
		this.tarjetaCredito = tarjetaCredito;
		this.bloqueado = false;
	}
	
	/**
	 * @return boolean, si un cliente est� bloqueado
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
	 * @return contrase�a
	 */
	public String getContrase�a() {
		return this.contrase�a;
	}
	
	/**
	 * @param contrase�a 
	 */
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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
				 "Contrase�a: " + contrase�a				
				);
	}
}