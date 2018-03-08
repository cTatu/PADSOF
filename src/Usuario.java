
public abstract class Usuario {
	private String nombres, NIF, apellidos, contraseña;

	public Usuario(String nombres, String NIF, String apellidos,String contraseña) {
		this.nombres = nombres;
		this.NIF = NIF;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
	}
}
