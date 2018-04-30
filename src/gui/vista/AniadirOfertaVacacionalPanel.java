package gui.vista;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AniadirOfertaVacacionalPanel extends AniadirOfertaPanel {
	
	private JLabel etiquetaFechaFin = new JLabel("Fecha Fin: (DD.MM.YYYY)");
	private JTextField fechaFin = new JTextField();
	
	public AniadirOfertaVacacionalPanel(Gui gui) {
		super(gui);
		
		this.add(etiquetaFechaFin, 8);
		this.add(fechaFin, 9);
	}

}
