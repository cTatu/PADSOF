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

	public GerentePanel(Gui gui) {
		super(gui);
	
		ofertasPendientes = new OfertasPendientesPanel(gui);
		cambiarTarjetaPanel = new CambiarTarjetaPanel(gui);
		
		tabsUsuario.addTab("Ofertas Pendiente", ofertasPendientes);
		tabsUsuario.addTab("Cambiar Tarjeta", cambiarTarjetaPanel);
	}
	
	public void addUsuariosTarejtaTabla(Object... tarjetas) {
		cambiarTarjetaPanel.addUsuariosTarejtaTabla(tarjetas);
	}

	@Override
	public void showInfoOferta(String atributoUnico, Object[] detallesCliente, Object... detallesOferta) {
		botonAtras.setText("Atras");
		ofertasPendientes.showInfoOferta(atributoUnico, detallesCliente, detallesOferta);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!ofertasPendientes.atras())
			gui.getControlador().cerrarSesion(true);
		else
			botonAtras.setText("Cerrar Sesion");
	}
	
	public void addTarjetasTabla(Object... tarjetas) {
		cambiarTarjetaPanel.addUsuariosTarejtaTabla(tarjetas);		
	}

	public void addOfertasTabla(Object... ofertas) {
		ofertasPendientes.addOfertaPendienteTabla(ofertas);		
	}

	@Override
	public void limpiarTablaOfertas() {
		ofertasPendientes.atras();
		botonAtras.setText("Cerrar Sesion");
		ofertasPendientes.limpiarTabla();
	}

	@Override
	public boolean isDemandante() {
		return false;
	}
}
