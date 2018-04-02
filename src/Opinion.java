

public abstract class Opinion {
	
	private Demandante demandante;
	
	public Opinion(Demandante demandante) {
		this.demandante = demandante;
	}
	
	public abstract boolean opinar(Opinion o);
}
