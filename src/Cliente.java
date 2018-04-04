import java.io.Serializable;

/**
 * 
 */
public class Cliente implements Serializable{

	private String tarjetaCredito, nombres, apellidos, contraseña, NIF;
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
	public String getContraseña() {
		return this.contraseña;
	}
	
	/**
	 * 
	 *
	 * @param contraseña 
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
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
				 "Contraseña: " + contraseña				
				);
	}
}