package gui.vista.publicar;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.vista.Gui;
import gui.vista.gerente.CaracteristicasPanel;

public class PublicarInmueblePanel extends JPanel implements ActionListener, DocumentListener{
	
	private JLabel CP = new JLabel("Codigo Postal: ");
	private JTextField campoCP = new JTextField();
	
	private JLabel localizacion = new JLabel("Localizacion: ");
	private JTextField campoLocalizacion = new JTextField();
	
	private CaracteristicasPanel panelcaracteristicas;
	private Map<JTextField, JTextField> rectificaciones = new HashMap<>();
	private JButton nuevaLinea = new JButton("Agregar Linea");
	
	private JLabel hueco = new JLabel(" ");
	protected JButton botonGuardar = new JButton("\nGuardar");
	
	protected Gui gui;
	private GridBagConstraints c = new GridBagConstraints();
	
	public PublicarInmueblePanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridLayout(0,2));	
		
		panelcaracteristicas = new CaracteristicasPanel(gui, rectificaciones);
	
		campoCP.setPreferredSize( new Dimension( 24, 24 ) );
		campoLocalizacion.setPreferredSize( new Dimension( 24, 50 ) );
		
		this.add(CP);
		this.add(campoCP);
		this.add(localizacion);
		this.add(campoLocalizacion);
		
		c.gridx = 0; c.gridy = 2;
		this.add(panelcaracteristicas, c);
		c.gridx = 0; c.gridy = 3;
		this.add(nuevaLinea, c);
		
		this.add(hueco);
		this.add(botonGuardar);
		
		botonGuardar.addActionListener( this );
		
		nuevaLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcaracteristicas.aniadirFila(PublicarInmueblePanel.this);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.gui.getControlador().aniadirInmueble( Integer.parseInt(this.campoCP.getText()), 
				this.campoLocalizacion.getText(), 
				Map.of("cambiar","esto"));
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		rectificar.setVisible(true);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {}
	@Override
	public void removeUpdate(DocumentEvent e) {}

}
