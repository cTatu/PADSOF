import java.io.Serializable;

public abstract class Cliente  extends Usuario implements Serializable{

	private String tarjetaCredito, rol;
	
	public Cliente(String nombres, String NIF, String apellidos, 
			String contraseņa,String tarjetaCredito,String rol) {
		super(nombres, NIF, apellidos, contraseņa);
		
		this.tarjetaCredito = tarjetaCredito;
		this.rol = rol;
	}

	
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
	}
}
