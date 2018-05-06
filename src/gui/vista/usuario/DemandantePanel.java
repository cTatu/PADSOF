package gui.vista.usuario;

import javax.swing.JPanel;

import gui.vista.Gui;
import gui.vista.oferta.DetallesPanelOferta;
import gui.vista.usuario.demandante.DetallesPanelOfertaDemandante;

public class DemandantePanel extends JPanel{

	private Gui gui;

	public DemandantePanel(Gui gui) {
		this.gui = gui;
	}

	public void showInfoOferta(String atributoUnico, Object... detallesOferta) {
		this.remove(0);
		
		this.add(new DetallesPanelOfertaDemandante(gui, atributoUnico, detallesOferta));
		this.repaint();
		this.revalidate();
	}

}
