
public abstract class Usuario {
	private String nombre, NIF, apellido, tarjetaCredito;

	public Usuario(String nombre, String NIF, String apellido, String tarjetaCredito) {
		this.nombre = nombre;
		this.NIF = NIF;
		this.apellido = apellido;
		this.tarjetaCredito = tarjetaCredito;
	}
}
