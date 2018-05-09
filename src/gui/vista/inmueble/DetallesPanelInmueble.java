/*
 * Clase panel que sirve para mostrar los detalles de un inmueble dado
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.inmueble;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.vista.Gui;

public class DetallesPanelInmueble extends JPanel{

	private static final long serialVersionUID = -1403346748362439301L;
	@SuppressWarnings("unused")
	private Gui gui;
	protected GridBagConstraints c = new GridBagConstraints();
	
	/**
	 * Contructor
	 *
	 * @param gui
	 *           
	 * @param detallesInmueble, detalles a mostrar
	 *            
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
