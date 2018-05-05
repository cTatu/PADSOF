/*
 * @author David Pascual y Cristian Tatu
 */
package tipos;

/**
 * Tipos de disponibilidad de una oferta.
 */
public enum TipoDisponibilidad {
	
	/** Contratado. */
	CONTRATADO, 
	
	/** Reservado. */
	RESERVADO,
	
	/** Disponible. */
	DISPONIBLE;
	
	public static TipoDisponibilidad parseString(String texto) {
		texto.toUpperCase();
		
		if (texto.equals("CONTRATADO"))
			return CONTRATADO;
		else if(texto.equals("RESERVADO"))
			return RESERVADO;
		else
			return DISPONIBLE;
	}
}
