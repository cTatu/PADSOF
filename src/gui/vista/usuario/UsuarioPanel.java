/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.usuario;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gui.vista.Gui;

public abstract class UsuarioPanel extends JPanel implements ActionListener{
	
	protected JTabbedPane tabsUsuario = new JTabbedPane();
	protected JButton botonAtras = new JButton("Cerrar Sesion");
	protected GridBagConstraints c = new GridBagConstraints();
	protected Gui gui;
	
	/**
	 * Instantiates a new usuario panel.
	 *
	 * @param gui
	 *            the gui
	 */
	public UsuarioPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());
		JPanel panelBotonAtras = new JPanel(new FlowLayout());
			panelBotonAtras.add(botonAtras);
	
		c.gridx = 0; c.anchor = GridBagConstraints.WEST; c.gridy = 0;
		this.add(panelBotonAtras, c);
		c.gridy = 1;
		this.add(tabsUsuario, c);	
		
		botonAtras.addActionListener(this);	
	}
	
	/**
	 * Show info oferta.
	 *
	 * @param atributoUnico
	 *            the atributo unico
	 * @param detallesExtra
	 *            the detalles extra
	 * @param detallesOferta
	 *            the detalles oferta
	 */
	public abstract void showInfoOferta(String atributoUnico, Object[] detallesExtra, Object... detallesOferta);
	
	/**
	 * Limpiar tabla ofertas.
	 */
	public abstract void limpiarTablaOfertas();
	
	/**
	 * Checks if is demandante.
	 *
	 * @return true, if is demandante
	 */
	public abstract boolean isDemandante();

}
