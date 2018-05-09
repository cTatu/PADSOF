/**
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.publicar;

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

/**
 * Clase panel que contiene al panel seleccionarInmueble y a publicar oferta
 */
public class CrearOfertaPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 8507840517616278846L;
	private GridBagConstraints c = new GridBagConstraints();
	private ButtonGroup grupoRadioButton = new ButtonGroup();
	private JRadioButton OpcionOfertaVacacional = new JRadioButton("Vacacional");
	private JRadioButton OpcionOfertaVivienda = new JRadioButton("Vivienda");
	
	private Gui gui;
	
	private AniadirOfertaPanel panelActivo;
	private AniadirOfertaVacacionalPanel panelAniadirVacacional;
	private AniadirOfertaViviendaPanel panelAniadirVivienda;
	
	/**
	 * Contructor
	 *
	 * @param gui
	 */
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

	/** 
	 * Boton para alternar entre crear oferta vacacional y oferta vivienda
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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
