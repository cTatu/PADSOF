package gui.main;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import app.InmaculadApp;
import gui.controlador.Controlador;
import gui.vista.Gui;

public class Main {
	public static void main(String[] args) {
		InmaculadApp app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		app.iniciarSesion("51999111X", "pezEspada"); // como ofertante
		Map<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Soleado", "Mucho");
		caracteristicas.put("Superfiice", "50m2");
		caracteristicas.put("Habitaciones", "3");
		
		app.crearInmueble(28845, "Barcelona", caracteristicas);
		app.aniadirOfertaVacacional(880.0, LocalDate.now(), "Descripcion", LocalDate.now().plusDays(20), 1);
		
		Gui gui = new Gui("InmaculadApp");
		
		Controlador  controlador = new Controlador(gui, app);
		
		gui.setControlador( controlador );		
	}
}
