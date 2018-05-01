package gui.vista;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JSpinner;

public class BusquedaViviendaPanel extends BusquedaPanelDemandante {
	
	private JLabel etiquetaMeses = new JLabel("Duracion Meses:");
	private JSpinner duracionMeses = new JSpinner();
	
	public BusquedaViviendaPanel(Gui gui) {
		super(gui);
		
		this.add(etiquetaMeses, 8);
		this.add(duracionMeses, 9);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
