import java.io.Serializable;

/**
 * 
 */
public class Cliente implements Serializable{

	private String tarjetaCredito, nombres, apellidos, contrase�a, NIF;
	protected Ofertante rolOfertante;
	protected Demandante rolDemandante;
	protected boolean gerente;
	private boolean bloqueado;
	
	/**
	 * 
	 *
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
	 * 
	 *
	 * @return 
	 */
	public boolean isBloqueado() {
		return bloqueado;
	}
	
	/**
	 * 
	 *
	 * @param bloqueado 
	 */
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	/**
	 * 
	 *
	 * @param tarjetaNueva 
	 */
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public String getTarjetaCredito() {
		return this.tarjetaCredito;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public String getNIF() {
		return this.NIF;
	}
	
	/**
	 * 
	 *
	 * @return 
	 */
	public String getContrase�a() {
		return this.contrase�a;
	}
	
	/**
	 * 
	 *
	 * @param contrase�a 
	 */
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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