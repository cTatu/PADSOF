package gui.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ClientePanel extends JPanel implements ChangeListener, ActionListener {
	
	private AniadirOfertaPanel panelAniadirOfertaVacacional;
	private AniadirOfertaPanel panelAniadirOfertaVivienda;
	private JTabbedPane tabsCliente = new JTabbedPane();
	private JButton cerrarSesion = new JButton("Cerrar Sesion\n");
	
	private Gui gui;
	
	public ClientePanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new FlowLayout());
		
		panelAniadirOfertaVacacional = new AniadirOfertaVacacionalPanel(gui);
		panelAniadirOfertaVivienda = new AniadirOfertaViviendaPanel(gui);
		
		tabsCliente.addTab("Nueva Oferta vacacional", panelAniadirOfertaVacacional);
		tabsCliente.addTab("Nueva Oferta vivienda", panelAniadirOfertaVivienda);
		
		// aniadir componentes al contenedor.
		this.add(cerrarSesion);
		this.add(tabsCliente);

		cerrarSesion.addChangeListener(this);
		cerrarSesion.addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.getControlador().cerrarSesion( true );
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		
	}

}
