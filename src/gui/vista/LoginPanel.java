package gui.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JFrame implements ActionListener {

	private JLabel etiquetaNIF = new JLabel("NIF:");
	private JTextField campoNIF = new JTextField();
	
	private JLabel etiquetaContrasenia = new JLabel("Contraseña");
	private JTextField campoContrasenia = new JPasswordField();
	
	private JButton botonLogin = new JButton("Login");
	
	private Gui gui;
	
	public LoginPanel(Gui gui) {
		this.gui = gui;
		//this.setLayout(new FlowLayout());
		this.setLayout(new GridLayout(0,2));
		
		this.setSize(400, 150);
			
		// aniadir componentes a "this" panel
		this.getContentPane().add(etiquetaNIF);
		this.getContentPane().add(campoNIF);
						
		this.getContentPane().add(etiquetaContrasenia);
		this.getContentPane().add(campoContrasenia);
		
		JPanel pb = new JPanel();
		pb.add(botonLogin);
		this.add(pb, BorderLayout.CENTER);

		// asociar acciones a componentes
		botonLogin.addActionListener( this );
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (gui.getApp().setSesionIniciada(this.gui.getApp().iniciarSesion(campoNIF.getText()
												, 
										campoContrasenia.getText()))) {
			
			gui.rolEtiqueta.setText(gui.getApp().clienteConectado().getNombres() 
									+ ": " + 
									gui.getApp().clienteConectado().toStringRol());
			gui.getBotonSesion().setText("Cerrar Sesion");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else
			JOptionPane.showMessageDialog(null, "Error Login", "Los datos introducidos no son correctos", JOptionPane.ERROR_MESSAGE);
	}


	public void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}

}
