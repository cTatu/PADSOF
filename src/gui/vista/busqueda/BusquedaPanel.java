package gui.vista.busqueda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import gui.layout.RelativeLayout;
import gui.vista.Gui;

public class BusquedaPanel extends JPanel implements ActionListener {
	
private ButtonGroup grupoRadioButton = new ButtonGroup();
	
	private JRadioButton OpcionBusquedaVivienda = new JRadioButton("Vivienda");
	private JRadioButton OpcionBusquedaVacacional = new JRadioButton("Vacacional");
	
	private BusquedaVacacionalPanel bsqdVac;
	private BusquedaViviendaPanel bsqdViv;
	
	private Gui gui;

	public BusquedaPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new RelativeLayout(RelativeLayout.Y_AXIS));
		
		bsqdVac = new BusquedaVacacionalPanel(gui);
		bsqdViv = new BusquedaViviendaPanel(gui);
		grupoRadioButton.add(OpcionBusquedaVacacional);
		grupoRadioButton.add(OpcionBusquedaVivienda);
		
		grupoRadioButton.setSelected(OpcionBusquedaVacacional.getModel(), true);

		JPanel panelRadioBotones = new JPanel(new GridLayout(0,2));
			panelRadioBotones.add(OpcionBusquedaVacacional);
			panelRadioBotones.add(OpcionBusquedaVivienda);
					
		OpcionBusquedaVacacional.addActionListener(this);
		OpcionBusquedaVivienda.addActionListener(this);
		
		this.add(panelRadioBotones);
		this.add(bsqdVac);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.remove(1);
		
		if(grupoRadioButton.getSelection().equals(OpcionBusquedaVacacional.getModel()))
			this.add(bsqdVac);
		else
			this.add(bsqdViv);
		
		this.revalidate();
		this.repaint();
	}
	
	

}
