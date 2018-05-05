package gui.vista.busqueda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;

public abstract class BusquedaPanelBasico extends JPanel {

	
	private JLabel etiquetaCP = new JLabel("Codigo Postal:");
	protected JSpinner campoCP = new JSpinner();

	private JLabel etiquetaFechaInicio1 = new JLabel("Fecha Inicio 1: (DD/MM/YYYY)");
	protected JTextField fechaInicio1 = new JTextField();
	
	private JLabel etiquetaFechaInicio2 = new JLabel("Fecha Inicio 2: (DD/MM/YYYY)");
	protected JTextField fechaInicio2 = new JTextField();
	
	/*** Usuario Registrado ***/
	private JLabel etiquetaDisponibilidad = new JLabel("Tipo de Disponibilidad: ");
	protected JComboBox<String> disponibilidad = new JComboBox<String>();
	
	private JLabel etiquetaValoracion = new JLabel("Valoracion:");
	protected JTextField campoValoracion = new JTextField();	
	/**************************/
	
	protected Gui gui;
	protected JTable tablaOfertas;

	protected Optional<Integer> duracionMeses = Optional.empty();
	protected Optional<Double> valoracion = Optional.empty();
	protected Optional<String> fechaFin = Optional.empty(),
							   tipoDisponibilidad = Optional.empty(), 
							   tipoOferta = Optional.empty();

	public BusquedaPanelBasico(Gui gui, boolean visible) {
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			tablaOfertas = new JTable();

			fechaInicio1.setPreferredSize( new Dimension( 250, 24 ) );
			fechaInicio2.setPreferredSize( new Dimension( 250, 24 ) );
			campoCP.setPreferredSize( new Dimension( 250, 24 ) );
			
			disponibilidad.setVisible(visible);
			etiquetaDisponibilidad.setVisible(visible);
			disponibilidad.addItem("CONTRATADO");
			disponibilidad.addItem("RESERVADO");
			disponibilidad.addItem("DISPONIBLE");
			
			etiquetaValoracion.setVisible(visible);
			campoValoracion.setVisible(visible);
			
			c.gridx = 0; c.gridy = 0;  
			this.add(etiquetaCP, c);
			c.gridx = 1; c.gridy = 0;
			this.add(campoCP, c);
			
			c.gridx = 0; c.gridy = 1; 
			this.add(etiquetaFechaInicio1, c);
			c.gridx = 1; c.gridy = 1; 
			this.add(fechaInicio1, c);
			
			c.gridx = 0; c.gridy = 2;  
			this.add(etiquetaFechaInicio2, c);
			c.gridx = 1; c.gridy = 2;
			this.add(fechaInicio2, c);
			
			c.gridx = 0; c.gridy = 3; 
			this.add(etiquetaDisponibilidad, c);
			c.gridx = 1; c.gridy = 3;
			this.add(disponibilidad, c);
			
			c.gridx = 0; c.gridy = 4;
			this.add(etiquetaValoracion, c);
			c.gridx = 1; c.gridy = 4; 
			this.add(campoValoracion, c);
			
			c.gridx = 0; c.gridy = 6;
			c.gridwidth = 2;
			JScrollPane scroll = new JScrollPane(tablaOfertas);
				scroll.setPreferredSize(new Dimension(500, 100));
			scroll.setVisible(false);
			this.add(scroll, c);
	}
	
	protected boolean isDemandate() {
		return disponibilidad.isVisible();
	}
	
	public void setVisibleUsuarioRegistrado(boolean visible) {
		disponibilidad.setVisible(visible);
		etiquetaDisponibilidad.setVisible(visible);
		
		campoValoracion.setVisible(visible);
		etiquetaValoracion.setVisible(visible);
	}
	
	public void addOfertasTabla(Object... oferta) {
		DefaultTableModel model = (DefaultTableModel) tablaOfertas.getModel();
		model.insertRow(0,oferta);
	}
	
	protected void rellenarCamposDemandante() {
		tipoDisponibilidad = Optional.of(String.valueOf(disponibilidad.getSelectedItem()));
		valoracion = Optional.of(Double.parseDouble(campoValoracion.getText()));
	}
	
	protected abstract void rellenarCampos();

}
