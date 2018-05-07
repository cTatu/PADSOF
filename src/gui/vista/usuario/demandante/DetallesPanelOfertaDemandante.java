package gui.vista.usuario.demandante;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import gui.vista.Gui;
import gui.vista.comentario.AddComentarioPanel;
import gui.vista.comentario.DetallesPanelComentario;
import gui.vista.gerente.DetallesPanelOfertaGerente;
import gui.vista.gerente.PanelRectificaciones;
import gui.vista.oferta.DetallesPanelOferta;

public class DetallesPanelOfertaDemandante extends DetallesPanelOferta{
	
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel panelComentarios = new JPanel(new GridLayout(0, 1));
	private JButton contratar = new JButton("Contratar");
	private JButton reservar = new JButton("Reservar");
	private JTabbedPane comentariosTabs = new JTabbedPane();
	private JPanel addComentario;

	public DetallesPanelOfertaDemandante(Gui gui, String atributoUnico, Object[] detallesInmueble, Object... detallesOferta) {
		
		super(gui, atributoUnico, detallesOferta);
		
		contratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro/a de querer contrar esta oferta?", "Confirmacion Contratar", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION)
					gui.getControlador().contratarOferta();				
			}
		});
		
		reservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro/a de querer reservar esta oferta?", "Confirmacion Reserva", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION)
					gui.getControlador().reservarOferta();				
			}
		});
				
		JPanel detallesInmueblePanel = new JPanel(new GridLayout(0, 2));
			detallesInmueblePanel.setBorder(BorderFactory.createTitledBorder("Inmueble"));
		
			detallesInmueblePanel.add(new JLabel("CP:"));
			detallesInmueblePanel.add(new JLabel(String.valueOf(detallesInmueble[0])));
				
			detallesInmueblePanel.add(new JLabel("Localizacion:"));
			detallesInmueblePanel.add(new JLabel(String.valueOf(detallesInmueble[1])));
				
			detallesInmueblePanel.add(new JLabel("Caracteristicas:"));
			detallesInmueblePanel.add(new JPanel());
			for (int i = 2; i < detallesInmueble.length; i++) {
				detallesInmueblePanel.add(new JLabel(String.valueOf(detallesInmueble[i])));
			}
			
		addComentario = new AddComentarioPanel(gui, this);
		addComentario.setVisible(false);
		
		reservar.setEnabled(gui.getControlador().isOfertaReservable());
			
		c.gridx = 0; c.gridy = 0;
		this.add(detallesInmueblePanel, c);
		
	
		JScrollPane scroll = new JScrollPane(panelComentarios);
		scroll.setPreferredSize(new Dimension(400, 100));
		
		comentariosTabs.addTab("Comentar", addComentario);
		comentariosTabs.addTab("Comentarios", scroll);
		
		c.gridx = 0; c.gridy = 2;	
		this.add(comentariosTabs, c);
		
		c.gridx = 1; c.gridy = 1;
		this.add(contratar, c);
		c.gridx = 1; c.gridy = 2;
		this.add(reservar, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void limpiarComentarios() {
		panelComentarios.removeAll();
		
		panelComentarios.revalidate();
		panelComentarios.repaint();
	}

	public void addComentario(Object... detallesComentario) {
		panelComentarios.add(new DetallesPanelComentario(gui, detallesComentario, this));
		
		panelComentarios.revalidate();
		panelComentarios.repaint();
		
		this.revalidate();
		this.repaint();
	}

	public void showComentarios() {
		comentariosTabs.setSelectedIndex(1);;
	}
}
