import java.io.Serializable;

public class Cliente implements Serializable{

	private String tarjetaCredito, nombres, apellidos, contrase�a, NIF;
	public Ofertante rolOfertante;
	public Demandante rolDemandante;
	public boolean gerente;
	
	public Cliente(String nombres, String NIF, String apellidos, 
				String contrase�a,String tarjetaCredito,Ofertante rolOfertante, Demandante rolDemandante) {
		
		this.rolOfertante = rolOfertante;
		this.rolDemandante = rolDemandante; 
		this.nombres = nombres;
		this.NIF = NIF;
		this.apellidos = apellidos;
		this.contrase�a = contrase�a;
		this.tarjetaCredito = tarjetaCredito;
	}

	
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
	}
	
	public String getNIF() {
		return this.NIF;
	}
	
	public String getContrase�a() {
		return this.contrase�a;
	}
	
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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
				 "Contrase�a: " + contrase�a				
				);
	}
}