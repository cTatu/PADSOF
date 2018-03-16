import java.io.Serializable;
import java.time.LocalDate;

public class BusquedaVacacional extends Busqueda implements Serializable{

	public BusquedaVacacional(int codigoPostal, double valoracion, LocalDate fechaInicio, LocalDate fechaFin,
			TipoOferta tipoOferta, TipoDisponibilidad tipoDisponibilidad) {
		super(codigoPostal, valoracion, fechaInicio, fechaFin, tipoOferta, tipoDisponibilidad);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCodigoPostal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValoracion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LocalDate getFechaInicio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate getFechaFin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoOferta getTipoOferta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoDisponibilidad getTipoDisponibilidad() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
