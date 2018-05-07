package gui.vista.oferta;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.vista.Gui;
import gui.vista.gerente.PanelRectificaciones;

public abstract class DetallesPanelOferta extends JPanel implements ActionListener{
	
	private GridBagConstraints c = new GridBagConstraints();	
	protected Gui gui;
	
	public DetallesPanelOferta(Gui gui, String atributoUnico, Object... detallesOferta) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());
		c.gridx = 0;
		
	
		JPanel detallesOfertaPanel = new JPanel(new GridLayout(0, 2));
			detallesOfertaPanel.setBorder(BorderFactory.createTitledBorder("Oferta"));
			
			detallesOfertaPanel.add(new JLabel("Fecha Inicio:"));
			detallesOfertaPanel.add(new JLabel(String.valueOf(detallesOferta[0])));
			
			detallesOfertaPanel.add(new JLabel(atributoUnico + ":"));
			detallesOfertaPanel.add(new JLabel(String.valueOf(detallesOferta[1])));
			
			detallesOfertaPanel.add(new JLabel("Precio:"));
			detallesOfertaPanel.add(new JLabel(String.valueOf(detallesOferta[2])));
			
			detallesOfertaPanel.add(new JLabel("Tipo:"));
			detallesOfertaPanel.add(new JLabel(String.valueOf(detallesOferta[3])));
			
			detallesOfertaPanel.add(new JLabel("Descripcion:"));
			detallesOfertaPanel.add(new JLabel(String.valueOf(detallesOferta[4])));
			
		c.gridx = 0; c.gridy = 1;
		this.add(detallesOfertaPanel, c);
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);
	

	
}
