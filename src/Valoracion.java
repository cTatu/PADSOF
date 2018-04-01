
public class Valoracion extends Opinion {
	
	private double Numero;
	
	public Valoracion(Demandante demandante, double numero) {
		super(demandante);
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
