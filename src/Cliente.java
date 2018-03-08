
public abstract class Cliente  extends Usuario{

	private String tarjetaCredito, rol;
	
	public Cliente(String nombre, String NIF, String apellido, 
			String contraseņa,String tarjetaCredito,String rol) {
		super(nombre, NIF, apellido, contraseņa);
		
		this.tarjetaCredito = tarjetaCredito;
		this.rol = rol;
	}

	
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
	}
}
