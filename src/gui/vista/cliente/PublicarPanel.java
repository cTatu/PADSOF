package gui.vista.cliente;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.vista.Gui;
import gui.vista.publicar.CrearOfertaPanel;
import gui.vista.publicar.PublicarInmueblePanel;

public class PublicarPanel extends JPanel implements ActionListener{
	
	private JButton botonInmueble = new JButton("Publicar inmueble\n");;
	private JButton botonOferta = new JButton("Publicar oferta\n");
	private JPanel panelBotones;
	private CrearOfertaPanel panelCrearOferta;
	private PublicarInmueblePanel panelPublicarInmueble;
	
	private Gui gui;
	
	public PublicarPanel(Gui gui, ActionListener alPadre) {
		this.gui = gui;
		this.setLayout(new FlowLayout());
		
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.add(botonInmueble);
		panelBotones.add(botonOferta);
		
		panelCrearOferta = new CrearOfertaPanel(gui);
		panelCrearOferta.setVisible(false);
		panelPublicarInmueble = new PublicarInmueblePanel(gui);
		panelPublicarInmueble.setVisible(false);
		
		this.add(panelBotones);
		this.add(panelCrearOferta);
		this.add(panelPublicarInmueble);
		
		botonInmueble.addActionListener(this);
		botonOferta.addActionListener(alPadre);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    panelBotones.setVisible( false );
	    panelPublicarInmueble.setVisible( true );
	    
	}

	public CrearOfertaPanel getPanelCrearOferta() {
		return panelCrearOferta;
	}

	public AbstractButton getBotonOferta() {
		return botonOferta;
	}

	public void showPublicarOferta() {
		panelBotones.setVisible(false);
		panelCrearOferta.setVisible(true);
	}
}
