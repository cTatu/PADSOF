package gui.main;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import app.InmaculadApp;
import fechasimulada.FechaSimulada;
import gui.controlador.Controlador;
import gui.vista.Gui;
import opinion.Comentario;

public class Main {
	public static void main(String[] args) {
		InmaculadApp app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		/*28845  01/05/2010 10/05/2020  21/05/2020               11/04/2015 24*/
		app.iniciarSesion("51999111X", "pezEspada"); // como ofertante
		
		Map<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Soleado", "Mucho");
		caracteristicas.put("Superfiice", "50m2");
		caracteristicas.put("Habitaciones", "3");
		
		app.crearInmueble(28845, "Barcelona", caracteristicas);
		
		FechaSimulada.fijarFecha(11, 05, 2015);
		
		app.aniadirOfertaVacacional(550.0, FechaSimulada.getHoy().minusDays(5),
							"Perfecto para vacaciones", 
							FechaSimulada.getHoy().plusDays(10).plusYears(5), 1);
		
		Comentario comentario = new Comentario(app.clienteConectado().rolDemandante, "El agua esta fria");
		Comentario cm2 = new Comentario(app.clienteConectado().rolDemandante, "Tiene razon");
		comentario.opinar(cm2);
		cm2.opinar(new Comentario(app.clienteConectado().rolDemandante, "Mucho Sol"));
		app.getOfertas().get(0).aniadirOpinion(comentario);
		
		/*comentario.opinar(new Comentario(app.clienteConectado().rolDemandante, "Mucho Sol"));
		app.getOfertas().get(0).aniadirOpinion(comentario);*/
		
		app.aniadirOfertaVacacional(1200.0, FechaSimulada.getHoy().minusDays(50),
				"Es mejor que la anterior", 
				FechaSimulada.getHoy().plusDays(10).plusYears(5), 1);
		
		app.aniadirOfertaVivienda(800.0, FechaSimulada.getHoy().minusDays(30), 
				"Perfecto para vivir", 24, 1, 300.0);

		app.cerrarSesion(false);
		
		Gui gui = new Gui("InmaculadApp");
		
		Controlador  controlador = new Controlador(gui, app);
		
		gui.setControlador( controlador );		
	}
}
