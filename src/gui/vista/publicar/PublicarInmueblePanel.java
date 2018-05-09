/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.publicar;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;

public class PublicarInmueblePanel extends JPanel implements ActionListener, DocumentListener{
	
	private JLabel CP = new JLabel("Codigo Postal: ");
	private JTextField campoCP = new JTextField();
	
	private JLabel localizacion = new JLabel("Localizacion: ");
	private JTextField campoLocalizacion = new JTextField();
	
	private CaracteristicasPanel panelCaracteristicas;
	private Map<JTextField, JTextField> caracteristicas = new HashMap<>();
	private JButton nuevaLinea = new JButton("Agregar Linea");
	
	private JLabel hueco = new JLabel(" ");
	protected JButton botonGuardar = new JButton("\nGuardar");
	
	protected Gui gui;
	private GridBagConstraints c = new GridBagConstraints();
	
	private DefaultTableModel model;
	
	/**
	 * Instantiates a new publicar inmueble panel.
	 *
	 * @param gui
	 *            the gui
	 */
	public PublicarInmueblePanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());
		c.gridx = 0;	
		
		panelCaracteristicas = new CaracteristicasPanel(gui, caracteristicas);
	
		campoCP.setPreferredSize( new Dimension( 250, 24 ) );
		campoLocalizacion.setPreferredSize( new Dimension( 250, 24 ) );
		
		c.gridx = 0; c.gridy = 0; 
		this.add(CP, c);
		c.gridx = 1; c.gridy = 0; 
		this.add(campoCP, c);
		c.gridx = 0; c.gridy = 1; 
		this.add(localizacion, c);
		c.gridx = 1; c.gridy = 1; 
		this.add(campoLocalizacion, c);
		
		c.gridx = 1; c.gridy = 2;
		this.add(panelCaracteristicas, c);
		c.gridx = 1; c.gridy = 3;
		this.add(nuevaLinea, c);
		
		c.gridx = 1; c.gridy = 4;
		this.add(hueco, c);
		c.gridx = 1; c.gridy = 5;
		this.add(botonGuardar, c);
		
		botonGuardar.addActionListener( this );
		
		nuevaLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCaracteristicas.aniadirFila(PublicarInmueblePanel.this);
			}
		});
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, String> mapaCaracteristicas;
		mapaCaracteristicas = this.getCaracteristicas();
		
		this.gui.getControlador().aniadirInmueble( Integer.parseInt(this.campoCP.getText()), 
				this.campoLocalizacion.getText(), 
				mapaCaracteristicas);
		
		this.limpiarTabla();
		this.gui.getControlador().rellenarMisInmuebles();
	}
	
	/**
	 * Limpiar tabla.
	 */
	public void limpiarTabla() {
		
	}	
	
	/**
	 * Gets the caracteristicas.
	 *
	 * @return the caracteristicas
	 */
	private Map<String, String> getCaracteristicas() {
		Map<String, String> mapaCaracteristicas = new HashMap<>();
		
		for (JTextField field : caracteristicas.keySet())
			mapaCaracteristicas.put(field.getText(), caracteristicas.get(field).getText());
		
		return mapaCaracteristicas;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		//rectificar.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {}
	
	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {}

}
