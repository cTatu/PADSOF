package gui.vista;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.controlador.Controlador;
import gui.vista.busqueda.BusquedaPanel;
import gui.vista.usuario.ClienteDualPanel;
import gui.vista.usuario.DemandantePanel;
import gui.vista.usuario.GerentePanel;
import gui.vista.usuario.OfertantePanel;


public class Gui extends JFrame {
	private BusquedaPanel panelBusquedaOfertas;
	private LoginPanel panelLogin;
	private GerentePanel panelGerente;
	private ClienteDualPanel panelClienteDual;
	private DemandantePanel panelDemandante;
	private OfertantePanel panelOfertante;
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
		panelGerente = new GerentePanel(this);
		panelClienteDual = new ClienteDualPanel(this);
		panelOfertante = new OfertantePanel(this);
		panelDemandante = new DemandantePanel(this);
		
		tabsInvitado.addTab("Login", panelLogin);
		tabsInvitado.addTab("Buscar",  panelBusquedaOfertas);
		
		// aniadir componentes al contenedor
		contenedor.add(tabsInvitado);
		contenedor.add(panelGerente);
		contenedor.add(panelClienteDual);
		contenedor.add(panelOfertante);
		contenedor.add(panelDemandante);
		
		// visibilidad inicial
		tabsInvitado.setVisible( true );
		panelGerente.setVisible( false );
		panelClienteDual.setVisible( false );
		panelOfertante.setVisible( false );
		panelDemandante.setVisible( false );

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
			if( this.controlador.isGerente() ) {
				panelGerente.setVisible( true );
				controlador.rellenarTablaTarjetas();
				controlador.rellenarTablaOfertasPendientes();
			}
			else if( this.controlador.isClienteDual() ) {
				panelClienteDual.setVisible( true );	
			}
			else if( this.controlador.isDemandante() ) {
				panelDemandante.setVisible( true );
				panelBusquedaOfertas.setVisibleUsuarioRegistrado(true);
			}
			else{
				panelOfertante.setVisible( true );
			}
			
		} else {
			JOptionPane.showMessageDialog(this, "NIF o contraseña incorrectos", "Login error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cerrarSesionResult(boolean cerrarSesionOK) {
		if (cerrarSesionOK) {
			tabsInvitado.setVisible( true );
			panelClienteDual.setVisible( false );
			panelGerente.setVisible(false);
		} 
	}
	
	public void addOfertaTablaBusqueda(Object... ofertas) {
		this.panelBusquedaOfertas.addOfertasTabla(ofertas);
	}


	public void cambiarTarjetaResult(boolean modificarTarjetaCredito, String nuevaTarjeta) {
		if (modificarTarjetaCredito)
			JOptionPane.showMessageDialog(this, "La nueva tarjeta de credito es: " + nuevaTarjeta, "Cambio Correcto", JOptionPane.INFORMATION_MESSAGE);
	}


	public void addTarjetaTabla(Object... tarjetas) {
		panelGerente.addUsuariosTarejtaTabla(tarjetas);
	}

	public void addOfertaPendienteTabla(Object... ofertas) {
		panelGerente.addOfertaPendienteTabla(ofertas);
	}

	public void showInfoOferta(Object... detallesOferta) {
		panelGerente.showInfoOferta(detallesOferta);
	}


	public void moderarStatus(boolean aprobarOferta) {
		if (aprobarOferta) {
			panelGerente.limpiarTabla();
			controlador.rellenarTablaOfertasPendientes();
		}
	}


	public void avisoBusquedaVacia() {
		JOptionPane.showMessageDialog(this, "La busqueda no ha tenido ningun resultado", "Busqueda Fallida", JOptionPane.INFORMATION_MESSAGE);
	}
}
