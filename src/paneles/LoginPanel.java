package paneles;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel implements ActionListener {

	private JLabel etiquetaNIF = new JLabel("NIF:");
	private JTextField campoNIF = new JTextField();
	
	private JLabel etiquetaContrasenia = new JLabel("Fecha Inicio 2: (DD.MM.YYYY)");
	private JTextField campoContrasenia = new JTextField();
	
	private JButton botonLogin = new JButton("\nLogin");
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//this.setLayout(new FlowLayout());
		this.setLayout(new GridLayout(0,2));
		
		// asociar acciones a componentes
		botonLogin.addActionListener( this );
				
		// aniadir componentes a "this" panel
		this.add(etiquetaNIF);
		this.add(campoNIF);
				
		this.add(etiquetaContrasenia);
		this.add(campoContrasenia);

		this.add(botonLogin);
	}

}
