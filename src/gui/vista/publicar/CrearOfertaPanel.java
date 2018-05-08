package gui.vista.publicar;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import gui.vista.Gui;
import gui.vista.aniadirOferta.AniadirOfertaPanel;
import gui.vista.aniadirOferta.AniadirOfertaVacacionalPanel;
import gui.vista.aniadirOferta.AniadirOfertaViviendaPanel;

public class CrearOfertaPanel extends JPanel implements ActionListener{

	private GridBagConstraints c = new GridBagConstraints();
	private ButtonGroup grupoRadioButton = new ButtonGroup();
	private JRadioButton OpcionOfertaVacacional = new JRadioButton("Vacacional");
	private JRadioButton OpcionOfertaVivienda = new JRadioButton("Vivienda");
	
	private Gui gui;
	
	private AniadirOfertaPanel panelActivo;
	private AniadirOfertaVacacionalPanel panelAniadirVacacional;
	private AniadirOfertaViviendaPanel panelAniadirVivienda;
	
	public CrearOfertaPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());
		
		c.gridx = 0;
		
		panelAniadirVacacional = new AniadirOfertaVacacionalPanel(this.gui);
		panelAniadirVivienda = new AniadirOfertaViviendaPanel(this.gui);
		
		panelActivo = panelAniadirVacacional;
		
		grupoRadioButton.add(OpcionOfertaVacacional);
		grupoRadioButton.add(OpcionOfertaVivienda);
		
		grupoRadioButton.setSelected(OpcionOfertaVacacional.getModel(), true);
		
		JPanel panelRadioBotones = new JPanel(new GridLayout(0,2));
			panelRadioBotones.add(OpcionOfertaVacacional);
			panelRadioBotones.add(OpcionOfertaVivienda);
		
		this.add(panelRadioBotones, c);
		this.add(panelActivo, c);	
		
		OpcionOfertaVacacional.addActionListener(this);
		OpcionOfertaVivienda.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.remove(1);
		
		if (grupoRadioButton.getSelection().equals(OpcionOfertaVacacional.getModel()))
	    	this.add(panelAniadirVacacional, c);
	    else
	    	this.add(panelAniadirVivienda, c);
		
		panelActivo = (AniadirOfertaPanel) this.getComponent(1);
		
	    this.revalidate();
		this.repaint();
	}


}
