package gui.vista.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.vista.Gui;
import gui.vista.aniadirOferta.AniadirOfertaPanel;
import gui.vista.aniadirOferta.AniadirOfertaVacacionalPanel;
import gui.vista.aniadirOferta.AniadirOfertaViviendaPanel;

public class ClienteDualPanel extends JPanel implements ChangeListener, ActionListener {
	
	private AniadirOfertaPanel panelAniadirOfertaVacacional;
	private AniadirOfertaPanel panelAniadirOfertaVivienda;
	private JTabbedPane tabsCliente = new JTabbedPane();
	private JButton cerrarSesion = new JButton("Cerrar Sesion\n");
	
	private Gui gui;
	
	public ClienteDualPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new BorderLayout());
		
		panelAniadirOfertaVacacional = new AniadirOfertaVacacionalPanel(gui);
		panelAniadirOfertaVivienda = new AniadirOfertaViviendaPanel(gui);
		
		tabsCliente.addTab("Nueva Oferta vacacional", panelAniadirOfertaVacacional);
		tabsCliente.addTab("Nueva Oferta vivienda", panelAniadirOfertaVivienda);
		

		JPanel panelBotonCerrarSesion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			panelBotonCerrarSesion.add(cerrarSesion);
			
		this.add(panelBotonCerrarSesion,BorderLayout.NORTH);
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