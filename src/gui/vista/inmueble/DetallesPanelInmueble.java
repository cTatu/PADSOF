package gui.vista.inmueble;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.vista.Gui;

public class DetallesPanelInmueble extends JPanel implements ActionListener{

	private Gui gui;
	private GridBagConstraints c = new GridBagConstraints();
	
	private JLabel hueco = new JLabel(" ");
	private JButton seleccionar = new JButton("Seleccionar");
	
	public DetallesPanelInmueble(Gui gui, Object[] detallesInmueble) {
		this.gui = gui;
		this.setLayout(new GridLayout(0,2));	
		
		this.add(new JLabel("Codigo Postal: "));
		this.add(new JLabel(String.valueOf(detallesInmueble[0])));
		
		this.add(new JLabel("Localizacion: "));
		this.add(new JLabel(String.valueOf(detallesInmueble[1])));
		
		
		
		this.add(hueco);
		this.add(seleccionar);
		
		seleccionar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.gui.getControlador().seleccionarInmueble();		
	}

}
