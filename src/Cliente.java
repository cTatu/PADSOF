
public abstract class Cliente  extends Usuario{

	private String tarjetaCredito, rol;
	
	public Cliente(String nombre, String NIF, String apellido, 
			String contrase�a,String tarjetaCredito,String rol) {
		super(nombre, NIF, apellido, contrase�a);
		
		this.tarjetaCredito = tarjetaCredito;
		this.rol = rol;
	}

	
	public void cambiarTartjeta(String tarjetaNueva) {
		this.tarjetaCredito = tarjetaNueva;
	}
}
