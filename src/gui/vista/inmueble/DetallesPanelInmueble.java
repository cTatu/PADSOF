package gui.vista.inmueble;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import gui.vista.Gui;

public class DetallesPanelInmueble extends JPanel implements ActionListener{

	private Gui gui;
	protected GridBagConstraints c = new GridBagConstraints();
	private JButton seleccionar = new JButton("Seleccionar inmueble");
	
	public DetallesPanelInmueble(Gui gui, Object[] detallesInmueble) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());	
		this.gui.setPreferredSize(new Dimension(600, 400));
		
		JPanel detallesInmueblePanel = new JPanel(new GridLayout(0, 2));
		detallesInmueblePanel.setBorder(BorderFactory.createTitledBorder("Detalles del Inmueble"));
	
		detallesInmueblePanel.add(new JLabel("CP:"));
		detallesInmueblePanel.add(new JLabel(String.valueOf(detallesInmueble[0])));
			
		detallesInmueblePanel.add(new JLabel("Localizacion:"));
		detallesInmueblePanel.add(new JLabel(String.valueOf(detallesInmueble[1])));
			
		detallesInmueblePanel.add(new JLabel("Caracteristicas:"));
		detallesInmueblePanel.add(new JPanel());
		for (int i = 2; i < detallesInmueble.length; i++) {
			detallesInmueblePanel.add(new JLabel(String.valueOf(detallesInmueble[i])));
		}
		detallesInmueblePanel.setPreferredSize( new Dimension( 250, 140 ) );
		
		JPanel panelSeleccionar = new JPanel(new FlowLayout());
		panelSeleccionar.add(seleccionar);
		
		c.gridheight = 2; c.gridx = 0;
		this.add(detallesInmueblePanel, c);
		c.gridheight = 1;
		this.add(panelSeleccionar, c);
	
		seleccionar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.gui.getControlador().seleccionarInmueble();		
	}

}
