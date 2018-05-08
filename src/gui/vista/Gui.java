package gui.vista;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import cliente.Cliente;
import gui.controlador.Controlador;
import gui.vista.busqueda.BusquedaPanel;
import gui.vista.busqueda.BusquedaPanelBasico;
import gui.vista.busqueda.BusquedaVacacionalPanel;
import gui.vista.busqueda.BusquedaViviendaPanel;
import gui.vista.usuario.DemandantePanel;
import gui.vista.usuario.GerentePanel;
import gui.vista.usuario.OfertantePanel;
import gui.vista.usuario.UsuarioPanel;
import opinion.Comentario;


public class Gui extends JFrame implements WindowListener, ActionListener{
	public static BusquedaPanel panelBusquedaOfertas;
	private LoginPanel panelLogin;
	private GerentePanel panelGerente;
	private DemandantePanel panelDemandante;
	private OfertantePanel panelOfertante;
	private JTabbedPane tabsInvitado = new JTabbedPane();
	private Controlador controlador;
	private Container contenedor;
	
	private UsuarioPanel panelActivo;
	
	private ButtonGroup grupoRadioButton = new ButtonGroup();
	private JRadioButton OpcionDemandante = new JRadioButton("Demandante");
	private JRadioButton OpcionOfertante = new JRadioButton("Ofertante");
	
	private JPanel panelRadioBotonesRol = new JPanel(new GridLayout(0,2));
	
	public Gui(String titulo) {
		super(titulo); // antes: JFrame ventana = new JFrame("Mi GUI");
		
		// obtener contenedor, asignar layout
		contenedor = this.getContentPane(); 
		contenedor.setLayout(new FlowLayout());
		
		grupoRadioButton.add(OpcionDemandante);
		grupoRadioButton.add(OpcionOfertante);
		
		OpcionDemandante.addActionListener(this);
		OpcionOfertante.addActionListener(this);
		
		panelRadioBotonesRol.setVisible(false);
			panelRadioBotonesRol.add(OpcionDemandante);
			panelRadioBotonesRol.add(OpcionOfertante);
			
		grupoRadioButton.setSelected(OpcionOfertante.getModel(), true);
		
		// crear componentes
		panelLogin = new LoginPanel(this);
		panelBusquedaOfertas = new BusquedaPanel(this, false);
		
		
		this.addWindowListener(this);
		
		tabsInvitado.addTab("Login", panelLogin);
		tabsInvitado.addTab("Buscar",  panelBusquedaOfertas);
		
		// aniadir componentes al contenedor
		contenedor.add(panelRadioBotonesRol);
		contenedor.add(tabsInvitado);

		/***********QUITAR************/
		/*panelOfertante = new OfertantePanel(this);
		contenedor.add(panelOfertante);
		setVisiblePaneles(panelOfertante);*/
		/***********QUITAR************/
		
		// visibilidad inicial
		setVisiblePaneles(tabsInvitado);

		// mostrar this, en otros ejemplos era ventana, ahora this
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setBounds(400,250,600,400); 
		this.setVisible(true);	
	}

	private void setVisiblePaneles(Component panel) {
		for (Component component : this.getContentPane().getComponents()) {
			if (component.equals(panel)) {
				component.setVisible(true);
				if (component instanceof UsuarioPanel)
					panelActivo = (UsuarioPanel) component;
			}else if (!component.equals(panelRadioBotonesRol))
				component.setVisible(false);
		}
	}
	
	public void setControlador(Controlador c) {
		this.controlador = c;
		/***********QUITAR************/
		/*this.controlador.addTodasOfertas();*/
		controlador.login("X1130055", "secreta"); // OD
		//controlador.login("55555111Z", "NoSeSaBe"); // D
		//controlador.login("51999111X", "pezEspada"); // O
		/***********QUITAR************/
	}
	public Controlador getControlador() {
		return this.controlador;
	}

	public void loginResult(boolean loginOK) {
		if (loginOK) { 
			tabsInvitado.setVisible( false );
			if( this.controlador.isGerente() ) {
				panelGerente = new GerentePanel(this);
				contenedor.add(panelGerente);
				setVisiblePaneles(panelGerente);
				controlador.rellenarTablaTarjetas();
				controlador.rellenarTablaOfertasPendientes();
			}if( this.controlador.isDemandante() ) {
				panelDemandante = new DemandantePanel(this);
				contenedor.add(panelDemandante);
				setVisiblePaneles(panelDemandante);	
			}if( this.controlador.isOfertante() ){
				panelOfertante = new OfertantePanel(this);
				contenedor.add(panelOfertante);
				setVisiblePaneles(panelOfertante);					
			}if (this.controlador.isClienteDual()) {
				panelRadioBotonesRol.setVisible(true);
				setVisiblePaneles(panelOfertante);
			}
		} else
			this.mensajeInfo("NIF o contraseña incorrectos", "Login error", JOptionPane.ERROR_MESSAGE);
		
		contenedor.revalidate();
		contenedor.repaint();
	}
	
	public void cerrarSesionResult(boolean cerrarSesionOK) {
		if (cerrarSesionOK) {
			if (panelRadioBotonesRol.isVisible() || panelDemandante.isVisible()) {
				tabsInvitado.addTab("Buscar",  panelBusquedaOfertas);
				Gui.panelBusquedaOfertas.setVisibleUsuarioRegistrado(false);
			}if (panelRadioBotonesRol.isVisible())
				panelRadioBotonesRol.setVisible(false);
			setVisiblePaneles(tabsInvitado);			

			this.revalidate();
			this.repaint();
		}
	}
	
	public void addOfertaTablaBusqueda(Object... ofertas) {
		panelBusquedaOfertas.addOfertasTabla(ofertas);
	}

	public void cambiarTarjetaResult(boolean modificarTarjetaCredito, String nuevaTarjeta) {
		if (modificarTarjetaCredito)
			this.mensajeInfo("La nueva tarjeta de credito es: " + nuevaTarjeta, "Cambio Correcto", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void addMisOfertasDemandante(Object... ofertas) {
		this.panelDemandante.addMisOfertas(ofertas);
	}
	
	public void addMisOfertasOfertante(Object... ofertas) {
		this.panelOfertante.addMisOfertas(ofertas);
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
			panelGerente.limpiarTablaOfertas();
			controlador.rellenarTablaOfertasPendientes();
		}
	}

	public void limpiarTabla(DefaultTableModel model) {
		model.setRowCount(0);
	}

	public void mensajeInfo(String descripcion, String asunto, int tipo) {
		JOptionPane.showMessageDialog(this, descripcion, asunto, tipo);
	}
	
	public void addMisInmuebles(Object... inmuebles) {
		panelOfertante.addMisInmuebles(inmuebles);		
	}
	
	public void showInfoInmueble(Object... infoInmueble) {
		panelOfertante.showInfoInmueble(infoInmueble);
	}

	public void aniadirOfertaViviendaResult(boolean statusOK) {
		if( statusOK ) {
			mensajeInfo("Oferta guardada, a espera de ser moderada.", "Oferta Agregada", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			mensajeInfo("Hubo algun error al guardar la oferta (ej. Caracteres invalidos, inmueble no existe, etc)", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void aniadirOfertaVacacionalResult(boolean statusOK) {
		if( statusOK ) {
			mensajeInfo("Oferta guardada, a espera de ser moderada para publicarse", "Oferta Agregada", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			mensajeInfo("Hubo algun error al guardar la oferta (ej. Campos invalidos, inmueble no existe, etc)", "Error", JOptionPane.ERROR_MESSAGE);
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
	
	public void contratarResult(boolean contratarOferta) {
		if (contratarOferta) {
			mensajeInfo("La oferta se ha contratado exitosamente. La podras encontrar en el tab 'Mis Ofertas'", "Oferta Contratada", JOptionPane.INFORMATION_MESSAGE);
			panelDemandante.showMisOfertas();
		}else {
			mensajeInfo("Hubo un error al contratar la oferta", "Contratar Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void reservarResult(boolean reservarOferta) {
		if (reservarOferta) {
			mensajeInfo("La oferta se ha reservado exitosamente. La podras encontrar en el tab 'Mis Ofertas'", "Oferta Contratada", JOptionPane.INFORMATION_MESSAGE);
			panelDemandante.showMisOfertas();
		}else {
			mensajeInfo("Hubo un error al reservar la oferta", "Contratar Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void showInfoComentario(Object... detallesComentario) {
		panelDemandante.showInfoComentario(detallesComentario);
	}

	public void comentarioOfertaResult(boolean status) {
		if (!status)
			mensajeInfo("La oferta seleccionada no tiene comentarios", "Oferta sin comentarios", JOptionPane.INFORMATION_MESSAGE);
	}

	public void subComentariosResult(boolean status) {
		if (!status)
			mensajeInfo("El comentario seleccionado no tiene respuestas", "Comentario sin respuestas", JOptionPane.ERROR_MESSAGE);
	}
	
	public UsuarioPanel getPanelActivo() {
		return panelActivo;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(grupoRadioButton.getSelection().equals(OpcionDemandante.getModel())) {
			setVisiblePaneles(panelDemandante);
			panelActivo = panelDemandante;
		}else {
			setVisiblePaneles(panelOfertante);
			panelActivo = panelOfertante;
		}
	
		this.revalidate();
		this.repaint();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int result = JOptionPane.showConfirmDialog(this,"Quieres guardar al salir?","Confirmar Guardar", JOptionPane.YES_NO_CANCEL_OPTION);

		if(result == JOptionPane.YES_OPTION){
			controlador.cerrarSesion(true); 	 
			System.exit(0);
		}else if(result == JOptionPane.NO_OPTION)
			System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
