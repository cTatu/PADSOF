
public enum TipoOpinion {
	COMENTARIO("Comentario"), 
	VALORACION("Valoracion");
	
	private final String tipoOpinion;

	TipoOpinion(String tipo) {
        this.tipoOpinion = tipo;
    }

    public String getOferta() {
        return tipoOpinion;
    }
}
