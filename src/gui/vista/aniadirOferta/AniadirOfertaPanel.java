package gui.vista.aniadirOferta;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.vista.Gui;

public class AniadirOfertaPanel extends JPanel {
	
	private JLabel precio = new JLabel("Precio: (Euros)");
	protected JTextField campoPrecio = new JTextField();
	
	private JLabel descripcion = new JLabel("Descripcion: ");
	protected JTextField campoDescripcion = new JTextField();		
	
	private JLabel etiquetaFechaInicio = new JLabel("Fecha Inicio: (DD/MM/YYYY) ");
	protected JTextField fechaInicio = new JTextField();
	
	protected Gui gui;
	
	public AniadirOfertaPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridLayout(0,2));	

		fechaInicio.setPreferredSize( new Dimension( 24, 24 ) );
		descripcion.setPreferredSize( new Dimension( 24, 24 ) );
		precio.setPreferredSize( new Dimension( 24, 24 ) );
		
		this.add(precio);
		this.add(campoPrecio);
		this.add(descripcion);
		this.add(campoDescripcion);
		this.add(etiquetaFechaInicio);
		this.add(fechaInicio);
	}
		
}
