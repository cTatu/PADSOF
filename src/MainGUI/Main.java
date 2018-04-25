package MainGUI;
import app.InmaculadApp;
import paneles.Gui;

public class Main {
	public static void main(String[] args) {
		Gui gui = new Gui("Una GUI muy simple");
		InmaculadApp app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		gui.setApp( app );
	}

}
