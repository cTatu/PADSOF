package gui.vista.busqueda;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JTextField;

import busqueda.BusquedaVacacional;
import gui.vista.Gui;
import tipos.TipoDisponibilidad;

public class BusquedaVacacionalPanel extends BusquedaPanelDemandante {

	private JLabel etiquetaFechaFin = new JLabel("Fecha Fin: (YYYY-MM-DD)");
	private JTextField fechaFin = new JTextField();
	
	public BusquedaVacacionalPanel(Gui gui) {
		super(gui);
		
		this.add(etiquetaFechaFin, 8);
		this.add(fechaFin, 9);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		BusquedaVacacional criterios = new BusquedaVacacional((Integer)super.campoCP.getValue(), 
															Double.parseDouble(super.campoValoracion.getText()), 
															LocalDate.parse(super.fechaInicio1.getText(), DateTimeFormatter.ISO_LOCAL_DATE), 
															LocalDate.parse(super.fechaInicio2.getText(), DateTimeFormatter.ISO_LOCAL_DATE), 
															(TipoDisponibilidad)super.disponibilidad.getSelectedItem(), 
															LocalDate.parse(this.fechaFin.getText(), DateTimeFormatter.ISO_LOCAL_DATE));
		super.gui.getControlador().buscar(criterios);
	}
}
