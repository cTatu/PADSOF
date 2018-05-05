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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class GerentePanel extends JPanel implements ActionListener{
	
	private JTabbedPane tabsGerente = new JTabbedPane();
	
	private OfertasPendientesPanel ofertasPendientes;
	private CambiarTarjetaPanel cambiarTarjetaPanel;
	private JButton botonAtras = new JButton("Cerrar Sesion");
	
	Gui gui;
	
	public GerentePanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		ofertasPendientes = new OfertasPendientesPanel(gui);
		cambiarTarjetaPanel = new CambiarTarjetaPanel(gui);
		
		tabsGerente.addTab("Ofertas Pendiente", ofertasPendientes);
		tabsGerente.addTab("Cambiar Tarjeta", cambiarTarjetaPanel);
		
		botonAtras.addActionListener(this);
		
		JPanel panelBotonAtras = new JPanel(new FlowLayout());
			panelBotonAtras.add(botonAtras);
		
		c.gridx = 0; c.anchor = GridBagConstraints.WEST;
		this.add(panelBotonAtras, c);
		this.add(tabsGerente, c);		
	}
	
	public void addUsuariosTarejtaTabla(Object... tarjetas) {
		cambiarTarjetaPanel.addUsuariosTarejtaTabla(tarjetas);
	}

	public void showInfoOferta(Object... detallesOferta) {
		botonAtras.setText("Atras");
		ofertasPendientes.showInfoOferta(detallesOferta);
	}

	public void addOfertaPendienteTabla(Object... ofertas) {
		ofertasPendientes.addOfertaPendienteTabla(ofertas);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!ofertasPendientes.atras())
			gui.getControlador().cerrarSesion(false);
		else
			botonAtras.setText("Cerrar Sesion");
	}

	public void limpiarTabla() {
		ofertasPendientes.atras();
		botonAtras.setText("Cerrar Sesion");
		ofertasPendientes.limpiarTabla();
	}
}
