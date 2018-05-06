package gui.vista.publicar;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.vista.Gui;

public class PublicarInmueblePanel extends JPanel implements ActionListener{
	
	private JLabel CP = new JLabel("Codigo Postal: ");
	private JTextField campoCP = new JTextField();
	
	private JLabel localizacion = new JLabel("Localizacion: ");
	private JTextField campoLocalizacion = new JTextField();
	
	private JLabel caracteristicas = new JLabel("Caracteristicas: ");
	private JTextField campoCaracteristicas = new JTextField();
	
	private JLabel hueco = new JLabel(" ");
	protected JButton botonGuardar = new JButton("\nGuardar");
	
	protected Gui gui;
	
	public PublicarInmueblePanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridLayout(0,2));	
	
		campoCP.setPreferredSize( new Dimension( 24, 24 ) );
		campoLocalizacion.setPreferredSize( new Dimension( 24, 50 ) );
		campoCaracteristicas.setPreferredSize( new Dimension( 24, 24 ) );
		
		this.add(CP);
		this.add(campoCP);
		this.add(localizacion);
		this.add(campoLocalizacion);
		this.add(caracteristicas);
		this.add(campoCaracteristicas);
		this.add(hueco);
		this.add(botonGuardar);
		
		botonGuardar.addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.gui.getControlador().aniadirInmueble( Integer.parseInt(this.campoCP.getText()), 
				this.campoLocalizacion.getText(), 
				Map.of("cambiar","esto"));
	}

}
