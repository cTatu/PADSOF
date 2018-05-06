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
	
	public UsuarioPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());
		JPanel panelBotonAtras = new JPanel(new FlowLayout());
			panelBotonAtras.add(botonAtras);
	
		c.gridx = 0; c.anchor = GridBagConstraints.WEST;
		this.add(panelBotonAtras, c);
		this.add(tabsUsuario, c);	
		
		botonAtras.addActionListener(this);	
	}
	
	public abstract void showInfoOferta(String atributoUnico, Object[] detallesExtra, Object... detallesOferta);
	public abstract void limpiarTabla();

}
