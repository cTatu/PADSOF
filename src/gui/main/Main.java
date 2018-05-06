package gui.main;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import app.InmaculadApp;
import fechasimulada.FechaSimulada;
import gui.controlador.Controlador;
import gui.vista.Gui;

public class Main {
	public static void main(String[] args) {
		InmaculadApp app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		/*28845  01/05/2018 10/05/2018  26/05/2018               5/04/2015 15/04/2015*/
		app.iniciarSesion("51999111X", "pezEspada"); // como ofertante
		
		Map<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Soleado", "Mucho");
		caracteristicas.put("Superfiice", "50m2");
		caracteristicas.put("Habitaciones", "3");
		
		app.crearInmueble(28845, "Barcelona", caracteristicas);
		
		FechaSimulada.fijarFecha(11, 05, 2015);
		
		app.aniadirOfertaVacacional(550.0, FechaSimulada.getHoy().minusDays(5),
							"Perfecto para vacaciones", 
							FechaSimulada.getHoy().plusDays(10), 1);
		
		app.aniadirOfertaVacacional(1200.0, FechaSimulada.getHoy().minusDays(50),
				"Es mejor que la anterior", 
				FechaSimulada.getHoy().plusDays(10), 1);
		
		app.aniadirOfertaVivienda(800.0, FechaSimulada.getHoy().minusDays(30), 
				"Perfecto para vivir", 24, 1, 300.0);

		app.cerrarSesion(true);
		
		Gui gui = new Gui("InmaculadApp");
		
		Controlador  controlador = new Controlador(gui, app);
		
		gui.setControlador( controlador );		
	}
}
