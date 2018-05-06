package gui.vista.busqueda;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import busqueda.BusquedaVacacional;
import gui.vista.Gui;
import tipos.TipoDisponibilidad;

public class BusquedaVacacionalPanel extends BusquedaPanelBasico {

	private JLabel etiquetaFechaFin = new JLabel("Fecha Fin: (d/MM/YYY)");
	private JTextField fechaFin = new JTextField();
	
	public BusquedaVacacionalPanel(Gui gui, boolean usuarioRegistrado) {
		super(gui, usuarioRegistrado);
		GridBagConstraints c = new GridBagConstraints();
		
		super.tablaOfertas.setModel(new DefaultTableModel(
				new Object[]{"Fecha Inicio", "Descripcion", "Fecha Fin", "Precio"}, 0));
		tablaOfertas.getColumnModel().getColumn(0).setPreferredWidth(1);
		tablaOfertas.getColumnModel().getColumn(2).setPreferredWidth(1);
		tablaOfertas.getColumnModel().getColumn(3).setPreferredWidth(1);
		
		this.fechaFin.setPreferredSize( new Dimension( 250, 24 ) );
		
		c.gridx = 0; c.gridy = 5; 
		this.add(etiquetaFechaFin, c);
		c.gridx = 1; c.gridy = 5; 
		this.add(fechaFin, c);
	}

	@Override
	protected void rellenarCampos() {
		super.fechaFin = this.fechaFin.getText();
		super.tipoOferta = "vacacional";
	}

}
