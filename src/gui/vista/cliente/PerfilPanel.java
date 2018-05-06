package gui.vista.cliente;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.vista.Gui;
import gui.vista.perfil.InmueblesPanel;
import gui.vista.perfil.MisDatosPanel;
import gui.vista.perfil.OfertasPanel;
import gui.vista.perfil.OpinarPanel;
import gui.vista.perfil.ReservasPanel;

public class PerfilPanel extends JPanel implements ActionListener{
	
	private JButton botonMisDatos = new JButton("Mis datos\n");;
	private JButton botonInmuebles = new JButton("Inmuebles\n");
	private JButton botonOfertas = new JButton("Ofertas\n");
	private JButton botonReservas = new JButton("Reservas\n");
	private JButton botonOpinar = new JButton("Opinar\n");
	private JPanel panelBotones;
	private MisDatosPanel panelMisDatos;
	private InmueblesPanel panelInmuebles;
	private OfertasPanel panelOfertas;
	private ReservasPanel panelReservas;
	private OpinarPanel panelOpinar;
	
	private Gui gui;
	
	public PerfilPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new FlowLayout());
		
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		
		panelBotones.add(botonMisDatos);
		panelBotones.add(botonInmuebles);
		panelBotones.add(botonOfertas);
		panelBotones.add(botonReservas);
		panelBotones.add(botonOpinar);
		
		panelMisDatos = new MisDatosPanel(gui);
		panelMisDatos.setVisible(false);
		panelInmuebles = new InmueblesPanel(gui);
		panelInmuebles.setVisible(false);
		panelOfertas = new OfertasPanel(gui);
		panelOfertas.setVisible(false);
		panelReservas = new ReservasPanel(gui);
		panelReservas.setVisible(false);
		panelOpinar = new OpinarPanel(gui);
		panelOpinar.setVisible(false);
		
		this.add(panelBotones);
		this.add(panelMisDatos);
		this.add(panelInmuebles);
		this.add(panelOfertas);
		this.add(panelReservas);
		this.add(panelOpinar);
		
		botonMisDatos.addActionListener(this);
		botonInmuebles.addActionListener(this);
		botonOfertas.addActionListener(this);
		botonReservas.addActionListener(this);
		botonOpinar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == botonMisDatos) {
	    	panelBotones.setVisible( false );
	    	panelMisDatos.setVisible( true );
	    }
	    else if (e.getSource() == botonInmuebles) {
	    	panelBotones.setVisible( false );
	    	panelInmuebles.setVisible( true );
	    }
	    else if (e.getSource() == botonOfertas) {
	    	panelBotones.setVisible( false );
	    	panelOfertas.setVisible( true );
	    }
	    else if (e.getSource() == botonReservas) {
	    	panelBotones.setVisible( false );
	    	panelReservas.setVisible( true );
	    }
	    else if (e.getSource() == botonOpinar) {
	    	panelBotones.setVisible( false );
	    	panelOpinar.setVisible( true );
	    }
	}

}
