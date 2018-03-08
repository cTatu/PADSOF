import java.io.Serializable;

public abstract class Cliente  extends Usuario implements Serializable{

	private String tarjetaCredito, rol;
	
	public Cliente(String nombres, String NIF, String apellidos, 
			String contraseña,String tarjetaCredito,String rol) {
		super(nombres, NIF, apellidos, contraseña);
		
		this.tarjetaCredito = tarjetaCredito;
		this.rol = rol;
	}

	
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
	}
}
