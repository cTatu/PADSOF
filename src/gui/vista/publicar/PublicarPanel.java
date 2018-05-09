/* 
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.publicar;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.vista.Gui;

/*
 *  Panel que contiene a PublicarInmueble, PublicarOferta y los botones para acceder a ellos
 */
public class PublicarPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 4432762035716472238L;
	private JButton botonInmueble = new JButton("Publicar inmueble\n");;
	private JButton botonOferta = new JButton("Publicar oferta\n");
	private JPanel panelBotones;
	private CrearOfertaPanel panelCrearOferta;
	private PublicarInmueblePanel panelPublicarInmueble;
	
	@SuppressWarnings("unused")
	private Gui gui;
	
	/**
	 * Constructor
	 *
	 * @param gui
	 *
	 * @param alPadre, actionListener para boton CrearOferta
	 *            
	 */
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

	/**
	 * ActionListener para botonInmueble
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	    panelBotones.setVisible( false );
	    panelPublicarInmueble.setVisible( true );
	    
	}

	/**
	 * Gets the panel crear oferta.
	 *
	 * @return the panel crear oferta
	 */
	public CrearOfertaPanel getPanelCrearOferta() {
		return panelCrearOferta;
	}

	/**
	 * Gets the boton oferta.
	 *
	 * @return the boton oferta
	 */
	public AbstractButton getBotonOferta() {
		return botonOferta;
	}

	/**
	 * Cambia panel visible a crear oferta
	 */
	public void showPublicarOferta() {
		panelBotones.setVisible(false);
		panelCrearOferta.setVisible(true);
	}
	
	/**
	 * Cambia panel visible a los botones
	 */
	public void showPanelBotones() {
		panelBotones.setVisible( true );
		panelCrearOferta.setVisible(false);
		panelPublicarInmueble.setVisible( false );
	}
}
