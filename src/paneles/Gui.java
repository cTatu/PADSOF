package paneles;
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
import sun.security.provider.certpath.ldap.JdkLDAP;


public class Gui extends JFrame implements ChangeListener, ActionListener {
	private JPanel panelBusquedaOfertas;
	private LoginPanel panelLogin;
	private JTabbedPane pesta�as = new JTabbedPane();
	private InmaculadApp app;
	
	private JButton botonSesion = new JButton("Iniciar Sesion");
	protected JLabel rolEtiqueta = new JLabel("Invitado");
	
	public Gui(String titulo) {
		super(titulo); // antes: JFrame ventana = new JFrame("Mi GUI");
		
		// obtener contenedor, asignar layout
		Container contenedor = this.getContentPane(); // antes: ventana.getContentPane();
		contenedor.setLayout(new FlowLayout());
		
		// crear componentes
		panelBusquedaOfertas = new BusquedaTablaPanel();
		
		contenedor.add(botonSesion);
		contenedor.add(rolEtiqueta);
		
		pesta�as.addTab("Buscar",  panelBusquedaOfertas);
		//pesta�as.setSelectedIndex(0); // 0 means first
		
		// a�adir componentes al contenedor
		contenedor.add(pesta�as);
		// this.pack();

		
		// visibilidad inicial
		pesta�as.setVisible( true );
		
		// Propuesta: PERMITIR REGRESAR A PANEL LOGIN DESDE CUALQUIER PESTA�A
		// Proposed work: ALLOW RETURN TO PANEL LOGIN FROM ANY TAB
		
		// Para realizar acciones al cambiar de pesta�as
		pesta�as.addChangeListener( this );
		
		botonSesion.addActionListener(this);

		// mostrar this, en otros ejemplos era ventana, ahora this
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,400); // remove and uncomment this.pack above
		this.setVisible(true);	
	}

	public void setApp(InmaculadApp app) {
		this.app = app;
	}
	public InmaculadApp getApp() {
		return this.app;
	}
	
	public JButton getBotonSesion() {
		return this.botonSesion;
	}
	
	
	@Override
	public void stateChanged(ChangeEvent ev) {
     // solamente a efectos de seguimiento del programa
   	 System.out.println( pesta�as.getSelectedIndex() );
   	 System.out.println( pesta�as.getSelectedComponent() );
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (!app.isSesionIniciada()) {
			panelLogin = new LoginPanel(this);
			panelLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			panelLogin.setVisible(true);
		}else {
			app.cerrarSesion(false);
			botonSesion.setText("Iniciar Sesion");
			rolEtiqueta.setText("Invitado");
		}
	}

	public void loginResult(boolean loginOK) {
		/*if (loginOK) { 
			panelLogin.setVisible( false );
			pesta�as.setVisible( true );
		} else {
			this.panelLogin.setError("login incorrecto");
		}*/
	}
}
