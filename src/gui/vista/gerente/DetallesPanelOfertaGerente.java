package gui.vista.gerente;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.vista.Gui;
import gui.vista.oferta.DetallesPanelOferta;

public class DetallesPanelOfertaGerente extends DetallesPanelOferta implements DocumentListener{
	
	private GridBagConstraints c = new GridBagConstraints();
	
	private JButton aceptar = new JButton("Aceptar");
	private JButton rechazar = new JButton("Rechazar");
	private JButton rectificar = new JButton("Enviar Rectificacion");
	private JButton nuevaLinea = new JButton("Agregar Linea");
	
	private PanelRectificaciones panelRectificaciones;
	
	private Map<JTextField, JTextField> rectificaciones = new HashMap<>();

	public DetallesPanelOfertaGerente(Gui gui, String atributoUnico, Object[] detallesOferta) {
		super(gui, atributoUnico, detallesOferta);
		
		panelRectificaciones = new PanelRectificaciones(gui, rectificaciones);

		
		rectificar.addActionListener(this);
		rectificar.setVisible(false);
		
		nuevaLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRectificaciones.aniadirFila(DetallesPanelOfertaGerente.this);
			}
		});
		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.getControlador().aceptarOferta(true);
			}
		});
		
		rechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.getControlador().aceptarOferta(false);				
			}
		});
		
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
		if (rectificaciones.size() != 0)
			super.gui.getControlador().enviarRectificacion(getRectificaciones());
		else
			super.gui.mensajeInfo("Los campos de rectificar no pueden estar vacios", "Campos vacios", JOptionPane.ERROR_MESSAGE);
	}
		
	private Map<String, String> getRectificaciones() {
		Map<String, String> mapaRectificaciones = new HashMap<>();
		
		for (JTextField field : rectificaciones.keySet())
			mapaRectificaciones.put(field.getText(), rectificaciones.get(field).getText());
		
		return mapaRectificaciones;
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
