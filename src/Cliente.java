import java.io.Serializable;

public class Cliente implements Serializable{

	private String tarjetaCredito, nombres, apellidos, contraseña, NIF;
	public Ofertante rolOfertante;
	public Demandante rolDemandante;
	public boolean gerente;
	
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
	
	public String getNIF() {
		return this.NIF;
	}
	
	public String getContraseña() {
		return this.contraseña;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
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