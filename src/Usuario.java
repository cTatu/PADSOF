
public abstract class Usuario {
	private String nombre, NIF, apellido, contraseña;

	public Usuario(String nombre, String NIF, String apellido,String contraseña) {
		this.nombre = nombre;
		this.NIF = NIF;
		this.apellido = apellido;
		this.contraseña = contraseña;
	}
}
