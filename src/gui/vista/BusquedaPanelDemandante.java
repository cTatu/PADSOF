package gui.vista;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tipos.TipoDisponibilidad;


public abstract class BusquedaPanelDemandante extends JPanel implements ActionListener {

	// componentes privados del panel, pero anin NO AniADIDOS
	private JLabel etiquetaCP = new JLabel("Codigo Postal:");
	protected JTextField campoCP = new JTextField();
	
	
	private JLabel etiquetaValoracion = new JLabel("Valoracion:");
	protected JTextField campoValoracion = new JTextField();	
	
	private JLabel etiquetaFechaInicio1 = new JLabel("Fecha Inicio 1: (YYYY-MM-DD)");
	protected JTextField fechaInicio1 = new JTextField();
	
	private JLabel etiquetaFechaInicio2 = new JLabel("Fecha Inicio 2: (YYYY-MM-DD)");
	protected JTextField fechaInicio2 = new JTextField();
	
	private JLabel etiquetaDisponibilidad = new JLabel("Tipo de Disponibilidad: ");
	protected JComboBox<TipoDisponibilidad> disponibilidad = new JComboBox<TipoDisponibilidad>(TipoDisponibilidad.values());
	
	private JButton botonBuscar = new JButton("\nBuscar");
	
	protected JTable tablaOfertas = new JTable(5, 3);
	
	protected Gui gui;
	
	
	public BusquedaPanelDemandante(Gui gui) {
		this.gui = gui;
		
		//this.setLayout(new FlowLayout());
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new GridLayout(0,2));

		fechaInicio1.setPreferredSize( new Dimension( 70, 24 ) );
		fechaInicio2.setPreferredSize( new Dimension( 70, 24 ) );
		campoValoracion.setPreferredSize( new Dimension( 24, 24 ) );
		campoCP.setPreferredSize( new Dimension( 24, 24 ) );
		
		
		
		// asociar acciones a componentes
		botonBuscar.addActionListener( this );
		
		// aniadir componentes a "this" panel
		this.add(etiquetaCP);
		this.add(campoCP);
		this.add(etiquetaValoracion);
		this.add(campoValoracion);
		
		this.add(etiquetaFechaInicio1);
		this.add(fechaInicio1);
		
		this.add(etiquetaFechaInicio2);
		this.add(fechaInicio2);
		
		this.add(etiquetaDisponibilidad);
		this.add(disponibilidad);
		
		this.add(botonBuscar);
	}

	// En el futuro, puede ser util tener getters como este:
	// public String getText() {    return this.campo.getText();  }
	
	@Override
	public abstract void actionPerformed(ActionEvent e);


}
