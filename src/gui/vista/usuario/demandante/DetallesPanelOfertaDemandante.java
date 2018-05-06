package gui.vista.usuario.demandante;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.vista.Gui;
import gui.vista.gerente.DetallesPanelOfertaGerente;
import gui.vista.gerente.PanelRectificaciones;
import gui.vista.oferta.DetallesPanelOferta;
import gui.vista.oferta.PanelComentarios;

public class DetallesPanelOfertaDemandante extends DetallesPanelOferta{
	
private GridBagConstraints c = new GridBagConstraints();
	
	private JButton contratar = new JButton("Contratar");
	private JButton reservar = new JButton("Reservar");
	
	private PanelComentarios panelComentarios;

	public DetallesPanelOfertaDemandante(Gui gui, String atributoUnico, Object... detallesOferta) {
		super(gui, atributoUnico, detallesOferta);
		
		panelComentarios = new PanelComentarios(gui);

		contratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.getControlador().contratarOferta();				
			}
		});
		
		reservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.getControlador().reservarOferta();				
			}
		});
		
		c.gridx = 0; c.gridy = 2;
		this.add(panelComentarios, c);
		
		c.gridx = 1; c.gridy = 1;
		this.add(contratar, c);
		c.gridx = 1; c.gridy = 2;
		this.add(reservar, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
