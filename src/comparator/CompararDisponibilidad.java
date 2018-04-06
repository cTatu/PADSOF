/*
 * @author David Pascual y Cristian Tatu
 */
package comparator;

import java.util.Comparator;

import oferta.Oferta;
import tipos.TipoDisponibilidad;

/**
 * Comparator para ordenar las búsquedas según la disponibilidad.
 */
public class CompararDisponibilidad implements Comparator<Oferta>{

	/**
	 * Comparator
	 *
	 * @param oferta a 
	 * @param oferta b
	 * @return comparacion
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Oferta a, Oferta b) {
		TipoDisponibilidad disp_A, disp_B;
		
		if(!a.getReservada() && !a.getContratada()) 
			disp_A = TipoDisponibilidad.DISPONIBLE;
		else if(a.getReservada() && !a.getContratada())
			disp_A = TipoDisponibilidad.RESERVADO;
		else
			disp_A = TipoDisponibilidad.CONTRATADO;
		if(!b.getReservada() && !b.getContratada())
			disp_B = TipoDisponibilidad.DISPONIBLE;
		else if(b.getReservada() && !b.getContratada())
			disp_B = TipoDisponibilidad.RESERVADO;
		else
			disp_B = TipoDisponibilidad.CONTRATADO;
		
		switch(disp_A) {
		case DISPONIBLE :
			if(disp_B.equals(TipoDisponibilidad.DISPONIBLE)) 
				return 0;
			else 
				return -1;
		case RESERVADO :
			if(disp_B.equals(TipoDisponibilidad.RESERVADO)) 
				return 0;
			else if(disp_B.equals(TipoDisponibilidad.DISPONIBLE)) 
				return 1;
			else 
				return -1;
		case CONTRATADO : 
			if(disp_B.equals(TipoDisponibilidad.CONTRATADO)) 
				return 0;
			else 
				return 1;
		}
		
		return 0;
	}			
}
