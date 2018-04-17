import controlador.Controlador;
import modelo.Aplicacion;
import gui.Gui;

public class Main {
	public static void main(String[] args) {
		Gui gui = new Gui("Una GUI muy simple");
		Aplicacion app = new Aplicacion();
		Controlador  controlador = new Controlador(gui, app);
		gui.setControlador( controlador );
	}

}
