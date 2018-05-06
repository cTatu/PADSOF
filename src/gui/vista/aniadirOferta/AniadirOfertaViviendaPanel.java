package gui.vista.aniadirOferta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import gui.vista.Gui;

public class AniadirOfertaViviendaPanel extends AniadirOfertaPanel implements ActionListener{
	
	private JLabel etiquetaDuracionMeses = new JLabel("Duracion Meses: ");
	private JSpinner duracionMeses = new JSpinner();
	
	private JLabel etiquetaFianza = new JLabel("Fianza: (Euros) ");
	private JTextField fianza = new JTextField();
	
	public AniadirOfertaViviendaPanel(Gui gui) {
		super(gui);
		
		this.add(etiquetaDuracionMeses, 10);
		this.add(duracionMeses, 11);
		this.add(etiquetaFianza, 12);
		this.add(fianza, 13);
		
		botonGuardar.addActionListener( this );
	}
	
	public void actionPerformed(ActionEvent ev) {
		
		this.gui.getControlador().aniadirOfertaVivienda( Double.parseDouble(this.campoPrecio.getText()), 
				LocalDate.parse(super.fechaInicio.getText(), DateTimeFormatter.ofPattern("d/MM/yyyy")), 
				this.campoDescripcion.getText(), 
				(Integer) this.duracionMeses.getValue(), 
				Integer.parseInt(this.campoID.getText()),
				Double.parseDouble(this.fianza.getText()));
	}

}
