/**
 * Panel en donde tiene un formulario para aniadir una oferta de tipo vacacional
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
import javax.swing.JTextField;

import gui.vista.Gui;

public class AniadirOfertaVacacionalPanel extends AniadirOfertaPanel implements ActionListener {
	
	private static final long serialVersionUID = 3570677287642394641L;
	
	private JLabel etiquetaFechaFin = new JLabel("Fecha Fin: (DD/MM/YYYY)");
	private JTextField fechaFin = new JTextField();
	
	protected JButton botonGuardar = new JButton("\nGuardar");
	
	/**
	 * Constructor Oferta Vacacional
	 *
	 * @param gui
	 *            
	 */
	public AniadirOfertaVacacionalPanel(Gui gui) {
		super(gui);
		
		this.add(etiquetaFechaFin, 6);
		this.add(fechaFin, 7);
		this.add(botonGuardar);
		
		botonGuardar.addActionListener( this );
	}
	
	/**
	 * Envia los datos del formulario para aniadir una nueva oferta vacacional
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ev) {
		
		try {
		if(super.gui.getControlador().aniadirOfertaVacacional( Double.parseDouble(this.campoPrecio.getText()), 
				LocalDate.parse(super.fechaInicio.getText(), DateTimeFormatter.ofPattern("d/MM/yyyy")), 
				this.campoDescripcion.getText(), 
				LocalDate.parse(this.fechaFin.getText(), DateTimeFormatter.ofPattern("d/MM/yyyy"))))
			gui.mensajeInfo("La oferta se ha aniadido con exito", "Oferta aniadida", JOptionPane.INFORMATION_MESSAGE);
		}catch (NumberFormatException e) {
			gui.mensajeInfo("El precio introducido es invalido", "Precio invalido", JOptionPane.ERROR_MESSAGE);
		}catch (DateTimeParseException e) {
			gui.mensajeInfo("La fecha no tiene un formato correcto", "Fecha invalido", JOptionPane.ERROR_MESSAGE);
		}
		
		gui.getPanelOfertante().getPanelPublicar().showPanelBotones();
	}

}
