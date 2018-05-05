package gui.vista.gerente;

import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Rectangle;

import gui.vista.Gui;

public class DetallesPanelOferta extends JPanel implements ActionListener{
	
	private GridBagConstraints c = new GridBagConstraints();
	
	private JButton aceptar = new JButton("Aceptar");
	private JButton rechazar = new JButton("Rechazar");
	private JButton rectificar = new JButton("Enviar Rectificacion");
	private JButton nuevaLinea = new JButton("Agregar Linea");
	
	private PanelRectificaciones panelRectificaciones;
	
	private Map<JTextField, JTextField> rectificaciones = new HashMap<>();
	
	private Gui gui;
	
	public DetallesPanelOferta(Gui gui, Object... detallesOferta) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());
		c.gridx = 0;
		
		JPanel detallesCliente = new JPanel(new GridLayout(0, 2));
			detallesCliente.setBorder(BorderFactory.createTitledBorder("Cliente"));

			detallesCliente.add(new JLabel("NIF:"));
			detallesCliente.add(new JLabel(String.valueOf(detallesOferta[0])));
			
			detallesCliente.add(new JLabel("Nombre:"));
			detallesCliente.add(new JLabel(String.valueOf(detallesOferta[1])));
			
		JPanel detallesOfertaPanel = new JPanel(new GridLayout(0, 2));
			detallesOfertaPanel.setBorder(BorderFactory.createTitledBorder("Oferta"));
			
			detallesOfertaPanel.add(new JLabel("Fecha Inicio:"));
			detallesOfertaPanel.add(new JLabel(String.valueOf(detallesOferta[2])));
			
			detallesOfertaPanel.add(new JLabel("Precio:"));
			detallesOfertaPanel.add(new JLabel(String.valueOf(detallesOferta[3])));
			
			detallesOfertaPanel.add(new JLabel("Tipo:"));
			detallesOfertaPanel.add(new JLabel(String.valueOf(detallesOferta[4])));
			
			detallesOfertaPanel.add(new JLabel("Descripcion:"));
			detallesOfertaPanel.add(new JLabel(String.valueOf(detallesOferta[5])));
			
		panelRectificaciones = new PanelRectificaciones(gui, rectificaciones);
		
		
		rectificar.addActionListener(this);
		
		nuevaLinea.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelRectificaciones.aniadirFila();
			}
		});
		
		aceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.getControlador().aceptarOferta(true);
			}
		});
		
		rechazar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.getControlador().aceptarOferta(false);				
			}
		});
		
		c.gridx = 0; c.gridy = 0;
		this.add(detallesCliente, c);
		c.gridx = 0; c.gridy = 1;
		this.add(detallesOfertaPanel, c);
		c.gridx = 0; c.gridy = 2;
		this.add(panelRectificaciones, c);
		c.gridx = 0; c.gridy = 3;
		this.add(nuevaLinea, c);
		
		c.gridx = 1; c.gridy = 0;
		this.add(aceptar, c);
		c.gridx = 1; c.gridy = 1;
		this.add(rechazar, c);
		c.gridx = 1; c.gridy = 2;
		this.add(rectificar, c);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		this.gui.getControlador().enviarRectificacion(getRectificaciones());
	}
	
	protected Map<String, String> getRectificaciones() {
		Map<String, String> mapaRectificaciones = new HashMap<>();
		
		for (JTextField field : rectificaciones.keySet())
			mapaRectificaciones.put(field.getText(), rectificaciones.get(field).getText());
		
		return mapaRectificaciones;
	}
	
}


class PanelRectificaciones extends JPanel{
	
	private GridBagConstraints c = new GridBagConstraints();
	
	private Map<JTextField, JTextField> rectificaciones;
	private Gui gui;
	
	public PanelRectificaciones(Gui gui, Map<JTextField, JTextField> rectificaciones) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());
		this.rectificaciones = rectificaciones;
		
		this.setBorder(BorderFactory.createTitledBorder("Rectificaciones"));
		
		c.gridx = 0; c.gridy = 0;
		this.add(new JLabel("Asunto"));
		c.gridx = 1; c.gridy = 0;
		this.add(new JLabel("Observacion"));
		
		c.gridx = 0; c.gridy = 2; c.gridwidth = 1;
	}

	public void aniadirFila() {
		JTextField asunto = new JTextField();
		asunto.setPreferredSize(new Dimension(150, 25));
		JTextField observacion = new JTextField();
		observacion.setPreferredSize(new Dimension(150, 25));
		
		Rectangle rect = this.gui.getBounds();
		this.gui.setBounds(new Rectangle(rect.x, rect.y, rect.width, rect.height+25)); 
		
		c.gridx = 0; c.gridy = this.getComponentCount()-1;
		this.add(asunto, c);
		c.gridx = 1; c.gridy = this.getComponentCount()-2;
		this.add(observacion, c);
		
		this.revalidate();
		this.repaint();
		
		rectificaciones.put(asunto, observacion);
		
		this.revalidate();
		this.repaint();
	}
	
}
