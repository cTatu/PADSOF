package paneles;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class BusquedaVacacionalPanel extends BusquedaPanel {

	private JLabel etiquetaFechaFin = new JLabel("Fecha Fin: (DD.MM.YYYY)");
	private JTextField fechaFin = new JTextField();
	
	public BusquedaVacacionalPanel() {
		super();
		
		this.add(etiquetaFechaFin, 8);
		this.add(fechaFin, 9);
	}
}
