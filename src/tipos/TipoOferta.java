/*
 * @author David Pascual y Cristian Tatu
 */
package tipos;

// TODO: Auto-generated Javadoc
/**
 * Tipos de oferta.
 *
 * @author David Pascual y Cristian Tatu
 */
public enum TipoOferta {
	
	/** The vacacional. */
	VACACIONAL, 
	
	/** The vivienda. */
	VIVIENDA;
	
	public static TipoOferta parseString(String texto) {
		texto = texto.toUpperCase();
		
		if (texto.equals("VACACIONAL"))
			return VACACIONAL;
		else
			return VIVIENDA;
	}
}