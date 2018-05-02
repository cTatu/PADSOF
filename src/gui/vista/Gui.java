package gui.vista;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.InmaculadApp;
import gui.controlador.Controlador;


public class Gui extends JFrame implements ChangeListener {
	private BusquedaPanel panelBusquedaOfertas;
	private LoginPanel panelLogin;
	private AniadirOfertaPanel panelAniadirOferta;
	private JTabbedPane tabsInvitado = new JTabbedPane();
	private JTabbedPane tabsCliente = new JTabbedPane();
	private Controlador controlador;
	
	public Gui(String titulo) {
		super(titulo); // antes: JFrame ventana = new JFrame("Mi GUI");
		
		// obtener contenedor, asignar layout
		Container contenedor = this.getContentPane(); // antes: ventana.getContentPane();
		contenedor.setLayout(new FlowLayout());
		
		// crear componentes
		panelLogin = new LoginPanel(this);
		panelBusquedaOfertas = new BusquedaPanel(this);
		panelAniadirOferta = new AniadirOfertaPanel(this);
		
		tabsInvitado.addTab("Login", panelLogin);
		tabsInvitado.addTab("Buscar",  panelBusquedaOfertas);
		
		tabsCliente.addTab("Oferta", panelAniadirOferta);
		
		// aniadir componentes al contenedor
		contenedor.add(tabsInvitado);
		contenedor.add(tabsCliente);
		
		// visibilidad inicial
		tabsInvitado.setVisible( true );
		tabsCliente.setVisible( false );
				
		
		
		// Propuesta: PERMITIR REGRESAR A PANEL LOGIN DESDE CUALQUIER PESTAniA
		// Proposed work: ALLOW RETURN TO PANEL LOGIN FROM ANY TAB
		
		// Para realizar acciones al cambiar de tabs
		tabsInvitado.addChangeListener( this );
		tabsCliente.addChangeListener(this);

		// mostrar this, en otros ejemplos era ventana, ahora this
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,400); // remove and uncomment this.pack above
		this.setVisible(true);	
	}

	
	public void setControlador(Controlador c) {
		this.controlador = c;
	}
	public Controlador getControlador() {
		return this.controlador;
	}
	
	
	@Override
	public void stateChanged(ChangeEvent ev) {

	}

	public void loginResult(boolean loginOK) {
		if (loginOK) { 
			tabsInvitado.setVisible( false );
			tabsCliente.setVisible( true );
		} else {
			this.panelLogin.setError("login incorrecto");
		}
	}
}
