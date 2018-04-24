package paneles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javafx.scene.shape.Box;
import tipos.TipoDisponibilidad;


public class BusquedaPanel extends JFrame implements ActionListener {

	// componentes privados del panel, pero anin NO AniADIDOS
	private JLabel etiquetaCP = new JLabel("Codigo Postal:");
	private JTextField campoCP = new JTextField();
	
	private JLabel etiquetaValoracion = new JLabel("Valoracion:");
	private JTextField campoValoracion = new JTextField();	
	
	private JLabel etiquetaFechaInicio1 = new JLabel("Fecha Inicio 1: (DD.MM.YYYY)");
	private JTextField fechaInicio1 = new JTextField();
	
	private JLabel etiquetaFechaInicio2 = new JLabel("Fecha Inicio 2: (DD.MM.YYYY)");
	private JTextField fechaInicio2 = new JTextField();
	
	private JLabel etiquetaDisponibilidad = new JLabel("Tipo de Disponibilidad: ");
	private JComboBox<TipoDisponibilidad> disponibilidad = new JComboBox<TipoDisponibilidad>(TipoDisponibilidad.values());
	
	private JButton botonBuscar = new JButton("\nBuscar");
	
	public BusquedaPanel() {
		//this.setLayout(new FlowLayout());
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new GridLayout(0,2));

		fechaInicio1.setPreferredSize( new Dimension( 70, 24 ) );
		fechaInicio2.setPreferredSize( new Dimension( 70, 24 ) );
		campoValoracion.setPreferredSize( new Dimension( 24, 24 ) );
		campoCP.setPreferredSize( new Dimension( 24, 24 ) );
		
		this.setSize(400, 200);
		
		// asociar acciones a componentes
		botonBuscar.addActionListener( this );
		
		// aniadir componentes a "this" panel
		this.getContentPane().add(etiquetaCP);
		this.getContentPane().add(campoCP);
		this.getContentPane().add(etiquetaValoracion);
		this.getContentPane().add(campoValoracion);
		
		this.getContentPane().add(etiquetaFechaInicio1);
		this.getContentPane().add(fechaInicio1);
		
		this.getContentPane().add(etiquetaFechaInicio2);
		this.getContentPane().add(fechaInicio2);
		
		this.getContentPane().add(etiquetaDisponibilidad);
		this.getContentPane().add(disponibilidad);
		
		this.getContentPane().add(botonBuscar);
	}

	// En el futuro, puede ser util tener getters como este:
	// public String getText() {    return this.campo.getText();  }
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		  //JOptionPane.showMessageDialog(null, this.campo.getText());
	}

}
