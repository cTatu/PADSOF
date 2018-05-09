/**
 * @author David Pascual y Cristian Tatu
 */
package gui.main;

import app.InmaculadApp;
import gui.controlador.Controlador;
import gui.vista.Gui;

/**
 * Clase con Metodo main 
 *
 * @param args
 *            argumentos
 */
public class Main {
	
	public static void main(String[] args) {
		InmaculadApp app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		
		Gui gui = new Gui("InmaculadApp");
		
		Controlador  controlador = new Controlador(gui, app);
		
		gui.setControlador( controlador );		
	}
}
