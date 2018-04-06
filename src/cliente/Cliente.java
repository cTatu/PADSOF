/*
 * @author David Pascual y Cristian Tatu
 */
package cliente;

import java.io.Serializable;

/**
 * Implementacion de Cliente que contiene informacion sobre estos.
 */
public class Cliente implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2221416256357316348L;
	
	private String tarjetaCredito, nombres, apellidos, contrase�a, NIF;
	public Ofertante rolOfertante;
	public Demandante rolDemandante;
	public boolean gerente;
	private boolean bloqueado;
	
	/**
	 * Constructor.
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
		this.setNombres(nombres);
		this.NIF = NIF;
		this.setApellidos(apellidos);
		this.contrase�a = contrase�a;
		this.tarjetaCredito = tarjetaCredito;
		this.bloqueado = false;
	}
	
	/**
	 * Checks if is bloqueado.
	 *
	 * @return boolean, si un cliente est� bloqueado
	 */
	public boolean isBloqueado() {
		return bloqueado;
	}
	
	/**
	 * Sets the bloqueado.
	 *
	 * @param bloqueado the new bloqueado
	 */
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	/**
	 * Cambia la tarjeta y desbloquea al cliente.
	 *
	 * @param tarjetaNueva the tarjeta nueva
	 */
	public void cambiarTarjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
		this.bloqueado = false;
	}
	
	/**
	 * Gets the tarjeta credito.
	 *
	 * @return Tarjeta de credito
	 */
	public String getTarjetaCredito() {
		return this.tarjetaCredito;
	}
	
	/**
	 * Gets the nif.
	 *
	 * @return NIF
	 */
	public String getNIF() {
		return this.NIF;
	}
	
	/**
	 * Gets the contrase�a.
	 *
	 * @return contrase�a
	 */
	public String getContrase�a() {
		return this.contrase�a;
	}
	
	/**
	 * Sets the contrase�a.
	 *
	 * @param contrase�a the new contrase�a
	 */
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
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

	/**
	 * Gets the nombres.
	 *
	 * @return el nombre del cliente
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * Sets the nombres.
	 *
	 * @param nuevo nombre
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * Gets the apellidos.
	 *
	 * @return apellidos del cliente
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Sets the apellidos.
	 *
	 * @param apellidos the new apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Gets the rol demandante.
	 *
	 * @return the rolDemandante
	 */
	public Demandante getRolDemandante() {
		return rolDemandante;
	}
}