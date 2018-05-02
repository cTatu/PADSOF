package gui.vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AniadirOfertaPanel extends JPanel implements ActionListener{
	
	private JLabel precio = new JLabel("Precio: (Euros)");
	private JTextField campoPrecio = new JTextField();
	
	private JLabel fianza = new JLabel("Precio: (Euros)");
	private JTextField campoFianza = new JTextField();
	
	private JLabel descripcion = new JLabel("Descripcion: ");
	private JTextField campoDescripcion = new JTextField();		
	
	private JLabel etiquetaFechaInicio = new JLabel("Fecha Inicio : (DD.MM.YYYY)");
	private JTextField fechaInicio = new JTextField();
	
	private JButton botonBuscar = new JButton("\nGuardar");
	
	private Gui gui;
	
	public AniadirOfertaPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridLayout(0,2));	

		
		// this.setPreferredSize(new Dimension(400, 200));
		fechaInicio.setPreferredSize( new Dimension( 70, 24 ) );
		fianza.setPreferredSize( new Dimension( 70, 24 ) );
		descripcion.setPreferredSize( new Dimension( 70, 50 ) );
		precio.setPreferredSize( new Dimension( 24, 24 ) );
		
		botonBuscar.addActionListener( this );
		
		// añadir componentes al panel
		this.add(precio);
		this.add(campoPrecio);
		this.add(fianza);
		this.add(campoFianza);
		this.add(descripcion);
		this.add(campoDescripcion);
		this.add(etiquetaFechaInicio);
		this.add(fechaInicio);
		this.add(botonBuscar);
	}

	public void actionPerformed(ActionEvent ev) {
		String mensaje;
		mensaje = "Tu oferta se ha guardado, en espera de ser aprobada";
		JOptionPane.showMessageDialog(null, mensaje);
	}
		
}
