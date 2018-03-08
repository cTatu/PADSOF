
public abstract class Cliente  extends Usuario{

	private String tarjetaCredito, rol;
	
	public Cliente(String nombre, String NIF, String apellido, 
			String contraseña,String tarjetaCredito,String rol) {
		super(nombre, NIF, apellido, contraseña);
		
		this.tarjetaCredito = tarjetaCredito;
		this.rol = rol;
	}

	
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
	}
}
