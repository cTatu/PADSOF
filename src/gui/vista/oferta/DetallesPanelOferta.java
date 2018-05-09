/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.oferta;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.vista.Gui;
import gui.vista.gerente.PanelRectificaciones;

public abstract class DetallesPanelOferta extends JPanel implements ActionListener{
	
	private GridBagConstraints c = new GridBagConstraints();	
	protected Gui gui;
	
	/**
	 * Instantiates a new detalles panel oferta.
	 *
	 * @param gui
	 *            the gui
	 * @param atributoUnico
	 *            the atributo unico
	 * @param detallesOferta
	 *            the detalles oferta
	 */
	public DetallesPanelOferta(Gui gui, String atributoUnico, Object... detallesOferta) {
		this.gui = gui;
		this.setLayout(new GridLayout(0, 2));
		c.gridx = 0;
		
			this.setBorder(BorderFactory.createTitledBorder("Oferta"));
			
			this.add(new JLabel("Fecha Inicio:"));
			this.add(new JLabel(String.valueOf(detallesOferta[0])));
			
			this.add(new JLabel(atributoUnico + ":"));
			this.add(new JLabel(String.valueOf(detallesOferta[1])));
			
			this.add(new JLabel("Precio:"));
			this.add(new JLabel(String.valueOf(detallesOferta[2])));
			
			this.add(new JLabel("Tipo:"));
			this.add(new JLabel(String.valueOf(detallesOferta[3])));
			
			this.add(new JLabel("Descripcion:"));
			this.add(new JLabel(String.valueOf(detallesOferta[4])));
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public abstract void actionPerformed(ActionEvent e);
	

	
}
