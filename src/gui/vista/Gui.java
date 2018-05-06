package gui.vista;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import gui.controlador.Controlador;
import gui.vista.busqueda.BusquedaPanel;
import gui.vista.oferta.ConsultanteOferta;
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
	
	private ConsultanteOferta panelActivo;
	
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
		setVisiblePaneles(tabsInvitado);

		// mostrar this, en otros ejemplos era ventana, ahora this
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,250,600,400); 
		this.setVisible(true);	
	}

	private void setVisiblePaneles(Component panel) {
		for (Component component : this.getComponents()) {
			if (component.equals(panel)) {
				component.setVisible(true);
				if (component instanceof ConsultanteOferta)
					panelActivo = (ConsultanteOferta) component;
			}else
				component.setVisible(false);
		}
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
				setVisiblePaneles(panelGerente);
				controlador.rellenarTablaTarjetas();
				controlador.rellenarTablaOfertasPendientes();
			}
			else if( this.controlador.isClienteDual() )
				setVisiblePaneles(panelClienteDual);	
			else if( this.controlador.isDemandante() ) {
				setVisiblePaneles(panelDemandante);	
				panelBusquedaOfertas.setVisibleUsuarioRegistrado(true);
			}
			else
				setVisiblePaneles(panelOfertante);	
		} else {
			this.mensajeInfo("NIF o contraseña incorrectos", "Login error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cerrarSesionResult(boolean cerrarSesionOK) {
		if (cerrarSesionOK)
			setVisiblePaneles(tabsInvitado);
	}
	
	public void addOfertaTablaBusqueda(Object... ofertas) {
		this.panelBusquedaOfertas.addOfertasTabla(ofertas);
	}


	public void cambiarTarjetaResult(boolean modificarTarjetaCredito, String nuevaTarjeta) {
		if (modificarTarjetaCredito)
			this.mensajeInfo("La nueva tarjeta de credito es: " + nuevaTarjeta, "Cambio Correcto", JOptionPane.INFORMATION_MESSAGE);
	}

	public void addElementosTablaGerente(Object... elementos) {
		panelGerente.addElementosTabla(elementos);
	}

	public void showInfoOferta(String atributoUnico, Object... detallesOferta) {
		if (controlador.isDemandante() || controlador.isGerente())
			panelActivo.showInfoOferta(atributoUnico, detallesOferta);
		else {
			tabsInvitado.setSelectedIndex(0);
			this.mensajeInfo("Tienes que iniciar sesion para ver los detalles de la oferta", "Login Necesario", JOptionPane.INFORMATION_MESSAGE);
		}
	}


	public void moderarStatus(boolean aprobarOferta) {
		if (aprobarOferta) {
			panelGerente.limpiarTabla();
			controlador.rellenarTablaOfertasPendientes();
		}
	}

	public void limpiarTabla(DefaultTableModel model) {
		model.setRowCount(0);
	}

	public void mensajeInfo(String descripcion, String asunto, int tipo) {
		JOptionPane.showMessageDialog(this, descripcion, asunto, tipo);
	}
}
