package gui.vista.gerente;

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

import gui.vista.Gui;

public class DetallesPanelOferta extends JPanel implements ActionListener{
	
	private GridBagConstraints c = new GridBagConstraints();
	
	private JButton aceptar = new JButton("Aceptar");
	private JButton rechazar = new JButton("Rechazar");
	private JButton rectificar = new JButton("Enviar Rectificacion");
	
	private JPanel panelRectificaciones;
	
	private Map<JTextField, JTextArea> rectificaciones = new HashMap<>();
	
	private Gui gui;
	
	public DetallesPanelOferta(Gui gui, Object... detallesOferta) {
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
			
		panelRectificaciones = new PanelRectificaciones(rectificaciones);
		
		rectificar.addActionListener(this);
		
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
		
		this.add(detallesCliente, c);
		this.add(detallesOfertaPanel, c);
		this.add(panelRectificaciones, c);
		
		this.add(aceptar, c);
		this.add(rechazar, c);
		this.add(rectificar, c);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		this.gui.getControlador().enviarRectificacion(getRectificaciones());
	}
	
	protected Map<String, String> getRectificaciones() {
		return rectificaciones.entrySet().stream().collect(Collectors.toMap(a -> ((JTextField) a).getText(), o -> ((JTextArea) o).getText()));
	}
	
}


class PanelRectificaciones extends JPanel implements ActionListener{
	
	private GridBagConstraints c = new GridBagConstraints();
	
	JButton nuevaLinea = new JButton("Agregar Linea");
	private Map<JTextField, JTextArea> rectificaciones;
	JScrollPane scrollRectificaciones = new JScrollPane();
	
	public PanelRectificaciones(Map<JTextField, JTextArea> rectificaciones) {
		this.setLayout(new GridBagLayout());
		this.rectificaciones = rectificaciones;
		
		this.setBorder(BorderFactory.createTitledBorder("Rectificaciones"));
		
		c.gridx = 0; c.gridy = 0;
		this.add(new JLabel("Asunto"));
		c.gridx = 1; c.gridy = 0;
		this.add(new JLabel("Observacion"));
		
		c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
		this.add(scrollRectificaciones);

		nuevaLinea.addActionListener(this);
		
		c.gridx = 0; c.gridy = 2; c.gridwidth = 1;
		this.add(nuevaLinea, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField asunto = new JTextField();
		JTextArea observacion = new JTextArea();
		
		c.gridx = 0; c.gridy = 1;
		scrollRectificaciones.add(asunto);
		c.gridx = 1; c.gridy = 1;
		scrollRectificaciones.add(observacion);
		
		scrollRectificaciones.revalidate();
		scrollRectificaciones.repaint();
		
		rectificaciones.put(asunto, observacion);
		
		this.revalidate();
		this.repaint();
	}
	
}
