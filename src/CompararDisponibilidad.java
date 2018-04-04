import java.util.Comparator;

/**
 * 
 */
public class CompararDisponibilidad implements Comparator<Oferta>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Oferta a, Oferta b) {
		TipoDisponibilidad disp_A, disp_B;
		
		if(a.getReservada()==null && a.getContratada()==false) 
			disp_A = TipoDisponibilidad.DISPONIBLE;
		else if(a.getReservada()==true && a.getContratada()==false)
			disp_A = TipoDisponibilidad.RESERVADO;
		else
			disp_A = TipoDisponibilidad.CONTRATADO;
		if(b.getReservada()==null && b.getContratada()==false)
			disp_B = TipoDisponibilidad.DISPONIBLE;
		else if(b.getReservada()==true && b.getContratada()==false)
			disp_B = TipoDisponibilidad.RESERVADO;
		else
			disp_B = TipoDisponibilidad.CONTRATADO;
		
		switch(disp_A) {
		case DISPONIBLE :
			if(disp_B.equals(TipoDisponibilidad.DISPONIBLE)) return 0;
			else return -1;
		case RESERVADO :
			if(disp_B.equals(TipoDisponibilidad.RESERVADO)) return 0;
			else if(disp_B.equals(TipoDisponibilidad.DISPONIBLE)) return 1;
			else return -1;
		case CONTRATADO : 
			if(disp_B.equals(TipoDisponibilidad.CONTRATADO)) return 0;
			else return 1;
		}
		
		return 0;
	}			
}
