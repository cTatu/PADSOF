package gui.vista;

import javax.swing.JLabel;
import javax.swing.JSpinner;

public class BusquedaViviendaPanel extends BusquedaPanel {
	private JLabel etiquetaMeses = new JLabel("Duracion Meses:");
	private JSpinner duracionMeses = new JSpinner();
	
	public BusquedaViviendaPanel() {
		super();
		
		this.add(etiquetaMeses, 8);
		this.add(duracionMeses, 9);
	}
}
