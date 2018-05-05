package gui.vista.aniadirOferta;

import javax.swing.JLabel;
import javax.swing.JSpinner;

import gui.vista.Gui;

public class AniadirOfertaViviendaPanel extends AniadirOfertaPanel {
	
	private JLabel etiquetaDuracionMeses = new JLabel("Duracion Meses: ");
	private JSpinner duracionMeses = new JSpinner();
	
	public AniadirOfertaViviendaPanel(Gui gui) {
		super(gui);
		
		this.add(etiquetaDuracionMeses, 8);
		this.add(duracionMeses, 9);
	}

}
