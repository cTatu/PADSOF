package gui.vista;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.controlador.Controlador;
import gui.vista.busqueda.BusquedaPanel;


public class Gui extends JFrame implements ChangeListener {
	private BusquedaPanel panelBusquedaOfertas;
	private LoginPanel panelLogin;
	private ClientePanel panelCliente;
	private JTabbedPane tabsInvitado = new JTabbedPane();
	private Controlador controlador;
	
	public Gui(String titulo) {
		super(titulo); // antes: JFrame ventana = new JFrame("Mi GUI");
		
		// obtener contenedor, asignar layout
		Container contenedor = this.getContentPane(); 
		contenedor.setLayout(new FlowLayout());
		
		// crear componentes
		panelLogin = new LoginPanel(this);
		panelBusquedaOfertas = new BusquedaPanel(this);
		panelCliente = new ClientePanel(this);
		
		tabsInvitado.addTab("Login", panelLogin);
		tabsInvitado.addTab("Buscar",  panelBusquedaOfertas);
		
		// aniadir componentes al contenedor
		contenedor.add(tabsInvitado);
		contenedor.add(panelCliente);
		
		// visibilidad inicial
		tabsInvitado.setVisible( true );
		panelCliente.setVisible( false );
		
		// Para realizar acciones al cambiar de tabs
		tabsInvitado.addChangeListener(this);

		// mostrar this, en otros ejemplos era ventana, ahora this
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,250,600,400); 
		this.setVisible(true);	
	}

	
	public void setControlador(Controlador c) {
		this.controlador = c;
	}
	public Controlador getControlador() {
		return this.controlador;
	}

	public void loginResult(boolean loginOK) {
		if (loginOK) { 
			tabsInvitado.setVisible( false );
			panelCliente.setVisible( true );
		} else {
			JOptionPane.showMessageDialog(this, "NIF o contraseña incorrectos", "Login error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cerrarSesionResult(boolean cerrarSesionOK) {
		if (cerrarSesionOK) {
			tabsInvitado.setVisible( true );
			panelCliente.setVisible( false );
		} 
	}
	
	@Override
	public void stateChanged(ChangeEvent ev) {

	}
}
