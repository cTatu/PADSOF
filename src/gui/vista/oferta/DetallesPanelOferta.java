/**
 * Clase panel que sirve para mostrar los detalles de una oferta dada
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.oferta;

import java.awt.GridBagConstraints;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.vista.Gui;

public abstract class DetallesPanelOferta extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 3428702706684214942L;
	
	private GridBagConstraints c = new GridBagConstraints();	
	protected Gui gui;
	
	/**
	 * Constructor
	 *
	 * @param gui
	 *          
	 * @param atributoUnico, fechaFIn o duracionMeses
	 *         
	 * @param detallesOferta, detalles a mostrar
	 *          
	 */
	public DetallesPanelOferta(Gui gui, String atributoUnico, Object... detallesOferta) {
		this.gui = gui;
		this.setLayout(new GridLayout(0, 1));
		c.gridx = 0;
		
		JPanel oferta = new JPanel(new GridLayout(0, 2));
			oferta.setBorder(BorderFactory.createTitledBorder("Oferta"));
				
			oferta.add(new JLabel("Fecha Inicio:"));
			oferta.add(new JLabel(String.valueOf(detallesOferta[0])));
				
			oferta.add(new JLabel(atributoUnico + ":"));
			oferta.add(new JLabel(String.valueOf(detallesOferta[1])));
				
			oferta.add(new JLabel("Precio:"));
			oferta.add(new JLabel(String.valueOf(detallesOferta[2])));
				
			oferta.add(new JLabel("Tipo:"));
			oferta.add(new JLabel(String.valueOf(detallesOferta[3])));
				
			oferta.add(new JLabel("Descripcion:"));
			oferta.add(new JLabel(String.valueOf(detallesOferta[4])));
			
			this.add(oferta);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public abstract void actionPerformed(ActionEvent e);
	

	
}
