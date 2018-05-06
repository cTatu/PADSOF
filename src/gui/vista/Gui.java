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
import javax.swing.tree.DefaultMutableTreeNode;

import gui.controlador.Controlador;
import gui.vista.busqueda.BusquedaPanel;
import gui.vista.usuario.ClienteDualPanel;
import gui.vista.usuario.DemandantePanel;
import gui.vista.usuario.GerentePanel;
import gui.vista.usuario.OfertantePanel;
import gui.vista.usuario.UsuarioPanel;
import opinion.Comentario;


public class Gui extends JFrame {
	public static BusquedaPanel panelBusquedaOfertas;
	private LoginPanel panelLogin;
	private GerentePanel panelGerente;
	private ClienteDualPanel panelClienteDual;
	private DemandantePanel panelDemandante;
	private OfertantePanel panelOfertante;
	private JTabbedPane tabsInvitado = new JTabbedPane();
	private Controlador controlador;
	private Container contenedor;
	
	private UsuarioPanel panelActivo;
	
	public Gui(String titulo) {
		super(titulo); // antes: JFrame ventana = new JFrame("Mi GUI");
		
		// obtener contenedor, asignar layout
		contenedor = this.getContentPane(); 
		contenedor.setLayout(new FlowLayout());
		
		// crear componentes
		panelLogin = new LoginPanel(this);
		panelBusquedaOfertas = new BusquedaPanel(this, false);
		panelGerente = new GerentePanel(this);
		
		
		tabsInvitado.addTab("Login", panelLogin);
		tabsInvitado.addTab("Buscar",  panelBusquedaOfertas);
		
		// aniadir componentes al contenedor
		contenedor.add(tabsInvitado);
		contenedor.add(panelGerente);
		
		/***********QUITAR************/
		panelDemandante = new DemandantePanel(this);
		contenedor.add(panelDemandante);
		setVisiblePaneles(panelDemandante);
		/***********QUITAR************/
		
		// visibilidad inicial
		//setVisiblePaneles(tabsInvitado);

		// mostrar this, en otros ejemplos era ventana, ahora this
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,250,600,400); 
		this.setVisible(true);	
	}

	private void setVisiblePaneles(Component panel) {
		for (Component component : this.getContentPane().getComponents()) {
			if (component.equals(panel)) {
				component.setVisible(true);
				if (component instanceof UsuarioPanel)
					panelActivo = (UsuarioPanel) component;
			}else
				component.setVisible(false);
		}
	}
	
	public void setControlador(Controlador c) {
		this.controlador = c;
		/***********QUITAR************/
		this.controlador.addTodasOfertas();
		controlador.login("55555111Z", "NoSeSaBe");
		/***********QUITAR************/
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
			else if( this.controlador.isClienteDual() ) {
				panelClienteDual = new ClienteDualPanel(this);
				contenedor.add(panelClienteDual);
				setVisiblePaneles(panelClienteDual);	
			}else if( this.controlador.isDemandante() ) {
				panelDemandante = new DemandantePanel(this);
				contenedor.add(panelDemandante);
				setVisiblePaneles(panelDemandante);	
			}else {
				panelOfertante = new OfertantePanel(this);
				contenedor.add(panelOfertante);
				setVisiblePaneles(panelOfertante);					
			}
		} else {
			this.mensajeInfo("NIF o contraseña incorrectos", "Login error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cerrarSesionResult(boolean cerrarSesionOK) {
		if (cerrarSesionOK)
			setVisiblePaneles(tabsInvitado);
	}
	
	public void addOfertaTablaBusqueda(Object... ofertas) {
		panelBusquedaOfertas.addOfertasTabla(ofertas);
	}

	public void cambiarTarjetaResult(boolean modificarTarjetaCredito, String nuevaTarjeta) {
		if (modificarTarjetaCredito)
			this.mensajeInfo("La nueva tarjeta de credito es: " + nuevaTarjeta, "Cambio Correcto", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void addOfertasTablaGerente(Object... ofertas) {
		panelGerente.addOfertasTabla(ofertas);
	}

	public void addTarjetasTablaGerente(Object... tarjetas) {
		panelGerente.addTarjetasTabla(tarjetas);
	}

	public void showInfoOferta(String atributoUnico, Object[] detallesExtra, Object... detallesOferta) {
		if (controlador.isDemandante() || controlador.isGerente())
			panelActivo.showInfoOferta(atributoUnico, detallesExtra, detallesOferta);
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
	
	public void aniadirOfertaViviendaResult(boolean statusOK) {
		if( statusOK ) {
			JOptionPane.showMessageDialog(this, "Oferta guardada, a espera de ser moderada.");
		}
		else {
			JOptionPane.showMessageDialog(this, "Hubo algun error al guardar la oferta (ej. Caracteres invalidos, inmueble no existe, etc)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void aniadirOfertaVacacionalResult(boolean statusOK) {
		if( statusOK ) {
			JOptionPane.showMessageDialog(this, "Oferta guardada, a espera de ser moderada para publicarse");
		}
		else {
			JOptionPane.showMessageDialog(this, "Hubo algun error al guardar la oferta (ej. Campos invalidos, inmueble no existe, etc)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	public void aniadirInmuebleResult(boolean statusOK) {
		if( statusOK ) {
			JOptionPane.showMessageDialog(this, "Inmueble creado");
		}
		else {
			JOptionPane.showMessageDialog(this, "Hubo algun error al crear el inmueble (ej. Carmpos invalidos, etc)", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}

}
