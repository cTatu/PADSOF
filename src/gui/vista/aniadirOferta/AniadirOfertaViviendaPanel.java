/**
 * Panel en donde tiene un formulario para aniadir una oferta de tipo vivienda
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.aniadirOferta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import gui.vista.Gui;

public class AniadirOfertaViviendaPanel extends AniadirOfertaPanel implements ActionListener{
	
	private static final long serialVersionUID = 4449367185902313770L;
	
	private JLabel etiquetaDuracionMeses = new JLabel("Duracion Meses: ");
	private JSpinner duracionMeses = new JSpinner();
	
	private JLabel etiquetaFianza = new JLabel("Fianza: (Euros) ");
	private JTextField fianza = new JTextField();
	
	protected JButton botonGuardar = new JButton("\nGuardar");
	
	/**
	 * Constructor
	 *
	 * @param gui
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
	
	/**
	 * Envia los datos del formulario para aniadir una nueva oferta
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ev) {
		
		try {
		if(super.gui.getControlador().aniadirOfertaVivienda( Double.parseDouble(this.campoPrecio.getText()), 
				LocalDate.parse(super.fechaInicio.getText(), DateTimeFormatter.ofPattern("d/MM/yyyy")), 
				this.campoDescripcion.getText(), 
				(Integer) this.duracionMeses.getValue(), 
				Double.parseDouble(this.fianza.getText())))
			gui.mensajeInfo("La oferta se ha aniadido con exito", "Oferta aniadida", JOptionPane.INFORMATION_MESSAGE);
		}catch (NumberFormatException e) {
			gui.mensajeInfo("El precio introducido es invalido", "Precio invalido", JOptionPane.ERROR_MESSAGE);
		}catch (DateTimeParseException e) {
			gui.mensajeInfo("La fecha no tiene un formato correcto", "Fecha invalido", JOptionPane.ERROR_MESSAGE);
		}
		
		gui.getPanelOfertante().getPanelPublicar().showPanelBotones();
	}

}
