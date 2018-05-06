package gui.vista.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gui.vista.Gui;
import gui.vista.busqueda.BusquedaPanel;
import gui.vista.cliente.PerfilPanel;
import gui.vista.cliente.PublicarPanel;

public class ClienteDualPanel extends JPanel implements ActionListener {
	
	private BusquedaPanel panelBusqueda;
	private PublicarPanel panelPublicar;
	private PerfilPanel panelPerfil;
	private JTabbedPane tabsCliente = new JTabbedPane();
	private JButton cerrarSesion = new JButton("Cerrar Sesion\n");
	
	private Gui gui;
	
	public ClienteDualPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new BorderLayout());
		
		panelBusqueda = new BusquedaPanel(gui, true);
		panelPublicar = new PublicarPanel(gui);
		panelPerfil = new PerfilPanel(gui);
		
		tabsCliente.addTab("Busqueda", panelBusqueda);
		tabsCliente.addTab("Publicar", panelPublicar);
		tabsCliente.addTab("Perfil", panelPerfil);
		
		JPanel panelBotonCerrarSesion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBotonCerrarSesion.add(cerrarSesion);
			
		this.add(panelBotonCerrarSesion,BorderLayout.NORTH);
		this.add(tabsCliente);

		cerrarSesion.addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.getControlador().cerrarSesion( true );
	}

}
