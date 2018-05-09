/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.publicar;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import gui.vista.Gui;

public class CaracteristicasPanel extends JPanel{
	
	private GridBagConstraints c = new GridBagConstraints();
	
	private Map<JTextField, JTextField> caracteristicas;
	private Gui gui;
	
	/**
	 * Instantiates a new caracteristicas panel.
	 *
	 * @param gui
	 *            the gui
	 * @param rectificaciones
	 *            the rectificaciones
	 */
	public CaracteristicasPanel(Gui gui, Map<JTextField, JTextField> rectificaciones) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());
		this.caracteristicas = rectificaciones;
		
		this.setBorder(BorderFactory.createTitledBorder("Caracteristicas"));
		
		c.gridx = 0; c.gridy = 0;
		this.add(new JLabel("Cualidad "));
		c.gridx = 1; c.gridy = 0;
		this.add(new JLabel("Informacion "));
		
		c.gridx = 0; c.gridy = 2; c.gridwidth = 1;
	}

	/**
	 * Aniadir fila.
	 *
	 * @param docList
	 *            the doc list
	 */
	public void aniadirFila(DocumentListener docList) {
		JTextField asunto = new JTextField();
		asunto.setPreferredSize(new Dimension(150, 25));
		JTextField observacion = new JTextField();
		observacion.setPreferredSize(new Dimension(150, 25));
		
		if(caracteristicas.size() == 0) {
			observacion.getDocument().addDocumentListener(docList);
			asunto.getDocument().addDocumentListener(docList);
		}
		
		Rectangle rect = this.gui.getBounds();
		this.gui.setBounds(new Rectangle(rect.x, rect.y, rect.width, rect.height+25)); 
		
		c.gridx = 0; c.gridy = this.getComponentCount()-1;
		this.add(asunto, c);
		c.gridx = 1; c.gridy = this.getComponentCount()-2;
		this.add(observacion, c);
		
		this.revalidate();
		this.repaint();
		
		caracteristicas.put(asunto, observacion);
		
		this.revalidate();
		this.repaint();
	}
}