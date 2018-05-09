/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.usuario;

import java.awt.event.ActionEvent;

import gui.vista.Gui;
import gui.vista.gerente.*;

/**
 * Clase panel que soporta e instancia a todas las demas que usa un usuario gerente 
 */
public class GerentePanel extends UsuarioPanel{

	private static final long serialVersionUID = 1039636624570442052L;
	
	private OfertasPendientesPanel ofertasPendientes;
	private CambiarTarjetaPanel cambiarTarjetaPanel;

	/**
	 * Constructor
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
	 * Aniade una tarjeta a la tabla de tarjetas
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
	 * Aniade una tarjeta a la tabla de tarjetas
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
