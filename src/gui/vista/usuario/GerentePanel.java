/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.usuario;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;
import gui.vista.gerente.*;

public class GerentePanel extends UsuarioPanel{

	private OfertasPendientesPanel ofertasPendientes;
	private CambiarTarjetaPanel cambiarTarjetaPanel;

	/**
	 * Instantiates a new gerente panel.
	 *
	 * @param gui
	 *            the gui
	 */
	public GerentePanel(Gui gui) {
		super(gui);
	
		ofertasPendientes = new OfertasPendientesPanel(gui);
		cambiarTarjetaPanel = new CambiarTarjetaPanel(gui);
		
		tabsUsuario.addTab("Ofertas Pendiente", ofertasPendientes);
		tabsUsuario.addTab("Cambiar Tarjeta", cambiarTarjetaPanel);
	}
	
	/**
	 * Adds the usuarios tarejta tabla.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 */
	public void addUsuariosTarejtaTabla(Object... tarjetas) {
		cambiarTarjetaPanel.addUsuariosTarejtaTabla(tarjetas);
	}

	/* (non-Javadoc)
	 * @see gui.vista.usuario.UsuarioPanel#showInfoOferta(java.lang.String, java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public void showInfoOferta(String atributoUnico, Object[] detallesCliente, Object... detallesOferta) {
		botonAtras.setText("Atras");
		ofertasPendientes.showInfoOferta(atributoUnico, detallesCliente, detallesOferta);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!ofertasPendientes.atras())
			gui.getControlador().cerrarSesion(true);
		else
			botonAtras.setText("Cerrar Sesion");
	}
	
	/**
	 * Adds the tarjetas tabla.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 */
	public void addTarjetasTabla(Object... tarjetas) {
		cambiarTarjetaPanel.addUsuariosTarejtaTabla(tarjetas);		
	}

	/**
	 * Adds the ofertas tabla.
	 *
	 * @param ofertas
	 *            the ofertas
	 */
	public void addOfertasTabla(Object... ofertas) {
		ofertasPendientes.addOfertaPendienteTabla(ofertas);		
	}

	/* (non-Javadoc)
	 * @see gui.vista.usuario.UsuarioPanel#limpiarTablaOfertas()
	 */
	@Override
	public void limpiarTablaOfertas() {
		ofertasPendientes.atras();
		botonAtras.setText("Cerrar Sesion");
		ofertasPendientes.limpiarTabla();
	}

	/* (non-Javadoc)
	 * @see gui.vista.usuario.UsuarioPanel#isDemandante()
	 */
	@Override
	public boolean isDemandante() {
		return false;
	}
}
