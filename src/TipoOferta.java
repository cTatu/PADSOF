
public enum TipoOferta {
	VACACIONAL("Vacacional"), 
	VIVIENDA("Vivienda");
	
	private final String tipoOf;

	TipoOferta(String tipo) {
        this.tipoOf = tipo;
    }

    public String getOferta() {
        return tipoOf;
    }
}