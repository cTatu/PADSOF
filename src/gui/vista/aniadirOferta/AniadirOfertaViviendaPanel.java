/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.aniadirOferta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import gui.vista.Gui;

public class AniadirOfertaViviendaPanel extends AniadirOfertaPanel implements ActionListener{
	
	private JLabel etiquetaDuracionMeses = new JLabel("Duracion Meses: ");
	private JSpinner duracionMeses = new JSpinner();
	
	private JLabel etiquetaFianza = new JLabel("Fianza: (Euros) ");
	private JTextField fianza = new JTextField();
	
	protected JButton botonGuardar = new JButton("\nGuardar");
	
	/**
	 * Instantiates a new aniadir oferta vivienda panel.
	 *
	 * @param gui
	 *            the gui
	 */
	public AniadirOfertaViviendaPanel(Gui gui) {
		super(gui);
		
		this.add(etiquetaDuracionMeses, 6);
		this.add(duracionMeses, 7);
		this.add(etiquetaFianza, 8);
		this.add(fianza, 9);
		this.add(botonGuardar);
		
		botonGuardar.addActionListener( this );
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ev) {
		
		super.gui.getControlador().aniadirOfertaVivienda( Double.parseDouble(this.campoPrecio.getText()), 
				LocalDate.parse(super.fechaInicio.getText(), DateTimeFormatter.ofPattern("d/MM/yyyy")), 
				this.campoDescripcion.getText(), 
				(Integer) this.duracionMeses.getValue(), 
				Double.parseDouble(this.fianza.getText()));
	}

}
