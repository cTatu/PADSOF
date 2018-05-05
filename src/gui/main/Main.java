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
		/*28845  01/05/2018 10/05/2018  26/05/2018*/
		Gui gui = new Gui("InmaculadApp");
		
		Controlador  controlador = new Controlador(gui, app);
		
		gui.setControlador( controlador );		
	}
}
