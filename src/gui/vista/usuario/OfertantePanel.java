package gui.vista.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gui.vista.Gui;
import gui.vista.busqueda.BusquedaPanel;
import gui.vista.cliente.PerfilPanel;
import gui.vista.cliente.PublicarPanel;

public class OfertantePanel extends UsuarioPanel{

	private BusquedaPanel panelBusqueda;
	private PublicarPanel panelPublicar;
	private PerfilPanel panelPerfil;
	
	public OfertantePanel(Gui gui) {
		super(gui);
		this.setLayout(new BorderLayout());
		
		panelBusqueda = new BusquedaPanel(gui, true);
		panelPublicar = new PublicarPanel(gui);
		panelPerfil = new PerfilPanel(gui);
		
		tabsUsuario.addTab("Busqueda", panelBusqueda);
		tabsUsuario.addTab("Publicar", panelPublicar);
		tabsUsuario.addTab("Perfil", panelPerfil);
		
		JPanel panelBotonCerrarSesion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBotonCerrarSesion.add(botonAtras);
			
		this.add(panelBotonCerrarSesion,BorderLayout.NORTH);
		this.add(tabsUsuario);

		botonAtras.addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.getControlador().cerrarSesion(true);
	}

	public void addInmueblesTabla(Object[] inmuebles) {
		panelPublicar.getPanelCrearOferta().getPanelSeleccionarInmueble().addInmueblesTabla(inmuebles);
	}

	public PublicarPanel getPanelPublicar() {
		return panelPublicar;
	}

	@Override
	public void showInfoOferta(String atributoUnico, Object[] detallesExtra, Object... detallesOferta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limpiarTabla() {
		// TODO Auto-generated method stub
		
	}

}
