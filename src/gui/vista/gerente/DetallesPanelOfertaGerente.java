/**
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.gerente;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.vista.Gui;
import gui.vista.oferta.DetallesPanelOferta;

/**
 * Panel donde el gerente puede ver la oferta y aceptarla, rechazarla o enviar modificaciones
 */
public class DetallesPanelOfertaGerente extends DetallesPanelOferta implements DocumentListener{
	
	private static final long serialVersionUID = 7977344500008889383L;

	private GridBagConstraints c = new GridBagConstraints();
	
	private JButton aceptar = new JButton("Aceptar");
	private JButton rechazar = new JButton("Rechazar");
	private JButton rectificar = new JButton("Enviar Rectificacion");
	private JButton nuevaLinea = new JButton("Agregar Linea");
	
	private PanelRectificaciones panelRectificaciones;
	
	private Map<JTextField, JTextField> rectificaciones = new HashMap<>();

	/**
	 * Constructor
	 *
	 * @param gui
	 *            the gui
	 * @param atributoUnico
	 *            atributo unico de los tipos de oferta
	 * @param detallesClientes
	 *            detalles de los clientes a mostrar
	 * @param detallesOferta
	 *            detalles de la oferta a mostrar
	 */
	public DetallesPanelOfertaGerente(Gui gui, String atributoUnico, Object[] detallesClientes , Object... detallesOferta) {
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
		
		JPanel detallesCliente = new JPanel(new GridLayout(0, 2));
			detallesCliente.setBorder(BorderFactory.createTitledBorder("Cliente"));
	
			detallesCliente.add(new JLabel("NIF:"));
			detallesCliente.add(new JLabel(String.valueOf(detallesClientes[0])));
			
			detallesCliente.add(new JLabel("Nombre:"));
			detallesCliente.add(new JLabel(String.valueOf(detallesClientes[1])));
		
		c.gridx = 0; c.gridy = 0;
		this.add(detallesCliente, c);
		
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
	

	/* (non-Javadoc)
	 * @see gui.vista.oferta.DetallesPanelOferta#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (rectificaciones.size() != 0)
			super.gui.getControlador().enviarRectificacion(getRectificaciones());
		else
			super.gui.mensajeInfo("Los campos de rectificar no pueden estar vacios", "Campos vacios", JOptionPane.ERROR_MESSAGE);
	}
		
	/**
	 * Recoge las rectificaciones introducidas
	 *
	 * @return the rectificaciones
	 */
	private Map<String, String> getRectificaciones() {
		Map<String, String> mapaRectificaciones = new HashMap<>();
		
		for (JTextField field : rectificaciones.keySet())
			mapaRectificaciones.put(field.getText(), rectificaciones.get(field).getText());
		
		return mapaRectificaciones;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		rectificar.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {}
	
	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {}

}
