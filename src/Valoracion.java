
public class Valoracion extends Opinion {
	
	private double Numero;
	
	public Valoracion(Cliente cliente, double numero) {
		super(cliente);
		this.Numero = numero;
	}

	@Override
	public boolean opinar(Opinion o) { 
		return false;
	}

	public double getNumero() {
		return Numero;
	}
}
