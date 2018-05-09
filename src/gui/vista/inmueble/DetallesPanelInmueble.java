/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.inmueble;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import gui.vista.Gui;

public class DetallesPanelInmueble extends JPanel{

	private Gui gui;
	protected GridBagConstraints c = new GridBagConstraints();
	
	/**
	 * Instantiates a new detalles panel inmueble.
	 *
	 * @param gui
	 *            the gui
	 * @param detallesInmueble
	 *            the detalles inmueble
	 */
	public DetallesPanelInmueble(Gui gui, Object... detallesInmueble) {
		this.gui = gui;
		this.setLayout(new GridLayout(0, 2));	
		
		this.setBorder(BorderFactory.createTitledBorder("Detalles del Inmueble"));
	
		this.add(new JLabel("CP:"));
		this.add(new JLabel(String.valueOf(detallesInmueble[0])));
			
		this.add(new JLabel("Localizacion:"));
		this.add(new JLabel(String.valueOf(detallesInmueble[1])));
			
		this.add(new JLabel("Caracteristicas:"));
		this.add(new JPanel());
		for (int i = 2; i < detallesInmueble.length; i++) {
			this.add(new JLabel(String.valueOf(detallesInmueble[i])));
		}
	}
}
