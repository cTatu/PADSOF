package gui.main;
import app.InmaculadApp;
import gui.controlador.Controlador;
import gui.vista.Gui;

public class Main {
	public static void main(String[] args) {
		Gui gui = new Gui("InmaculadApp");
		InmaculadApp app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		Controlador  controlador = new Controlador(gui, app);
		gui.setControlador( controlador );		
	}
}
