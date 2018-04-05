package app;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.junit.runners.MethodSorters;

import busqueda.*;
import fechasimulada.FechaSimulada;
import jdk.vm.ci.meta.Local;
import oferta.Oferta;
import opinion.Comentario;
import opinion.Valoracion;
import tipos.TipoDisponibilidad;
import tipos.TipoOferta;
import tipos.TipoOrdenar;

/**
 * Clase con main para demo de la aplicaci�n
 * @author David Pascual y Cristian Tatu
 */
public class DemoInmaculadApp{

	/**
	 * Demostrador de funcionamiento de la aplicaci�n InmaculadApp
	 *
	 * @param args 
	 */
	public static void main(String[] args) {
		InmaculadApp app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		
		System.out.println(app.iniciarSesion("51999111X", "pezEspada")); // como ofertante
		Map<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Soleado", "Mucho");
		caracteristicas.put("Superfiice", "50m2");
		caracteristicas.put("Habitaciones", "3");
		
		System.out.println(app.crearInmueble(28845, "Barcelona", caracteristicas));
		
		FechaSimulada.fijarFecha(11, 05, 2015);
		
		System.out.println(app.a�adirOfertaVacacional(550.0, FechaSimulada.getHoy().minusDays(5),
							"Perfecto para vacaciones", 
							FechaSimulada.getHoy().plusDays(10), 1));
		
		System.out.println(app.a�adirOfertaVacacional(120.0, FechaSimulada.getHoy().minusDays(50),
				"Es mejor que la anterior", 
				FechaSimulada.getHoy().plusDays(10), 1));
		
		System.out.println(app.a�adirOfertaVivienda(800.0, FechaSimulada.getHoy().minusDays(30), 
							"Perfecto para vivir", 24, 1, 300.0));
		
		System.out.println(app.getInmuebles().size());

		app.cerrarSesion(false);
		app.iniciarSesion("", "BD911"); 	// como gerente
		
		System.out.println(app.getOfertasPendientes().size());
		
		Oferta aux = app.getOfertasPendientes().get(0);
		aux.a�adirRectificacion(
						new HashMap<String, String>(Map.of("FechaInicio", "Demasiado tarde"))
						
				);
		
		aux.setFechaInicio(FechaSimulada.getHoy());
		
		app.aprobarOferta(app.getOfertasPendientes().get(0));
		
		app.getOfertasPendientes().size();
		
		app.aprobarOferta(app.getOfertasPendientes().get(0));
		
		app.aprobarOferta(app.getOfertasPendientes().get(0));
		
		app.cerrarSesion(false);
		app.iniciarSesion("55555111Z", "NoSeSaBe");  // como demandante
		
		app.reservarOferta(app.getOfertas().get(0));
		
		FechaSimulada.avanzar(4);
		
		System.out.println("RESERVA-VACACIONAL: " + app.contratarReserva(TipoOferta.VACACIONAL));
		
		System.out.println("OFERTA-VACACIONAL: " + app.contratarOferta(app.getOfertas().get(0)));
		
		Comentario comentario = new Comentario(app.clienteConectado().rolDemandante, "El agua esta fria");
		app.getOfertas().get(0).a�adirOpinion(comentario);
		app.getOfertas().get(0).a�adirOpinion(new Valoracion(app.clienteConectado().rolDemandante, 4.5));
		app.getOfertas().get(0).a�adirOpinion(new Valoracion(app.clienteConectado().rolDemandante, 1.5));
		
		System.out.println(app.getOfertas().get(0).calcularMedia());
		
		app.cerrarSesion(false);
		app.iniciarSesion("X1130055", "secreta");  // como otro usuario
		
		Comentario comentario2 = new Comentario(app.clienteConectado().rolDemandante, "Me ha ofendido tu comentario");
		System.out.println(app.getOfertas().get(0).getComentarios().get(0).opinar(comentario2));
		System.out.println(app.getOfertas().get(0).getComentarios().get(0).opinar(new Valoracion(app.clienteConectado().rolDemandante, 0.5)));
	
		// la oferta creada anteriormente tiene fecha inicio 2015 05 6
		LocalDate fecha1 = LocalDate.of(2010, 05, 1);
		LocalDate fecha2 = LocalDate.of(2020, 05, 8);
		LocalDate fechaFin = LocalDate.of(2015, 05, 21);
		
		Busqueda criterios = new BusquedaVacacional(28845, 5, fecha1, fecha2, TipoDisponibilidad.CONTRATADO, fechaFin);
		
		System.out.println(
				app.buscarOfertas(criterios, TipoOrdenar.FECHA)
				);
	}

}