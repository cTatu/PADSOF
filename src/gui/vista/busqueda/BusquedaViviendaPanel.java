package gui.vista.busqueda;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;

public class BusquedaViviendaPanel extends BusquedaPanelBasico {
	
	private JLabel etiquetaMeses = new JLabel("Duracion Meses:");
	private JSpinner duracionMeses = new JSpinner();
	
	public BusquedaViviendaPanel(Gui gui, boolean visible) {
		super(gui, visible);
		GridBagConstraints c = new GridBagConstraints();
		
		super.tablaOfertas.setModel(new DefaultTableModel(
				new Object[]{"Fecha Inicio", "Descripcion", "Duracion Meses", "Precio"}, 0));
		tablaOfertas.getColumnModel().getColumn(0).setPreferredWidth(1);
		tablaOfertas.getColumnModel().getColumn(2).setPreferredWidth(1);
		tablaOfertas.getColumnModel().getColumn(3).setPreferredWidth(1);
		
		this.duracionMeses.setPreferredSize( new Dimension( 250, 24 ) );
		
		c.gridx = 0; c.gridy = 5; 
		this.add(etiquetaMeses, c);
		c.gridx = 1; c.gridy = 5; 
		this.add(duracionMeses, c);
	}

	@Override
	protected void rellenarCampos() {
		super.duracionMeses = Optional.of((Integer)duracionMeses.getValue());
		super.tipoOferta = Optional.of("vivienda");
	}

}
