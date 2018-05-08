package gui.vista.publicar;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import gui.vista.Gui;

public class CrearOfertaPanel extends JPanel{

	private Gui gui;
	
	private PublicarOfertaPanel panelPublicarOferta;
	private SeleccionarInmueblePanel panelSeleccionarInmueble;
	
	public CrearOfertaPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new FlowLayout());
		
		panelPublicarOferta = new PublicarOfertaPanel(gui);
		panelPublicarOferta.setVisible(false);
		panelSeleccionarInmueble = new SeleccionarInmueblePanel(gui);
		panelSeleccionarInmueble.setVisible(true);
		
		this.add(panelSeleccionarInmueble);
		this.add(panelPublicarOferta);		
	}

	public SeleccionarInmueblePanel getPanelSeleccionarInmueble() {
		return panelSeleccionarInmueble;
	}

	public PublicarOfertaPanel getPanelPublicarOferta() {
		return panelPublicarOferta;
	}

}
