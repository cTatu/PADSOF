package gui.vista.aniadirOferta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.vista.Gui;

public class AniadirOfertaVacacionalPanel extends AniadirOfertaPanel implements ActionListener {
	
	private JLabel etiquetaFechaFin = new JLabel("Fecha Fin: (DD/MM/YYYY)");
	private JTextField fechaFin = new JTextField();
	
	protected JButton botonGuardar = new JButton("\nGuardar");
	
	public AniadirOfertaVacacionalPanel(Gui gui) {
		super(gui);
		
		this.add(etiquetaFechaFin, 6);
		this.add(fechaFin, 7);
		this.add(botonGuardar);
		
		botonGuardar.addActionListener( this );
	}
	
	public void actionPerformed(ActionEvent ev) {
		
		super.gui.getControlador().aniadirOfertaVacacional( Double.parseDouble(this.campoPrecio.getText()), 
				LocalDate.parse(super.fechaInicio.getText(), DateTimeFormatter.ofPattern("d/MM/yyyy")), 
				this.campoDescripcion.getText(), 
				LocalDate.parse(this.fechaFin.getText(), DateTimeFormatter.ofPattern("d/MM/yyyy")));
	}

}
