

public abstract class Opinion {
	
	private Demandante demandante;
	
	public Opinion(Demandante d) {
		this.demandante = d;
	}
	
	public abstract boolean opinar(Opinion o);
}
