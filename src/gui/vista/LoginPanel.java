/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel implements ActionListener {

	private JLabel etiquetaNIF = new JLabel("NIF:");
	private JTextField campoNIF = new JTextField(10);
	
	private JLabel etiquetaContrasenia = new JLabel("Contraseña");
	private JTextField campoContrasenia = new JPasswordField(10);
	
	private JButton botonLogin = new JButton("Login");
	
	private Gui gui;
	
	/**
	 * Instantiates a new login panel.
	 *
	 * @param gui
	 *            the gui
	 */
	public LoginPanel(Gui gui) {
		this.gui = gui;

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		JPanel panelNIF = new JPanel(new GridLayout(0, 2));
			panelNIF.add(etiquetaNIF);
			panelNIF.add(campoNIF);
		
						
		JPanel panelPasswd = new JPanel(new GridLayout(0, 2));
			panelPasswd.add(etiquetaContrasenia);
			panelPasswd.add(campoContrasenia);
		
		JPanel panelBoton = new JPanel();
		panelBoton.add(botonLogin);
		
		this.add(panelNIF, c);
		this.add(panelPasswd, c);
		this.add(panelBoton, c);		
		
		botonLogin.addActionListener( this );
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.getControlador().login( campoNIF.getText(), campoContrasenia.getText() );
	}
	
	// Propuesta: CAMBIA LA FORMA/LUGAR/MECANISMO PARA MOSTRAR ERROR
	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the new error
	 */
	// Proposed work: CHANGE THE WAY/PLACE/MECHANISM TO DISPLAY ERROR
	public void setError(String error) {	
		campoContrasenia.setText(error);
		campoContrasenia.setForeground(java.awt.Color.red);
	}

}
