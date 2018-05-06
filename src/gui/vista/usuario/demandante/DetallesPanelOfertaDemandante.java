package gui.vista.usuario.demandante;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
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
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import gui.vista.Gui;
import gui.vista.gerente.DetallesPanelOfertaGerente;
import gui.vista.gerente.CaracteristicasPanel;
import gui.vista.oferta.DetallesPanelOferta;

public class DetallesPanelOfertaDemandante extends DetallesPanelOferta implements TreeSelectionListener{
	
private GridBagConstraints c = new GridBagConstraints();
	
	private JButton contratar = new JButton("Contratar");
	private JButton reservar = new JButton("Reservar");
	
	private JTree treeComentarios;

	public DetallesPanelOfertaDemandante(Gui gui, String atributoUnico, 
			DefaultMutableTreeNode raiz, Object[] detallesInmueble, Object... detallesOferta) {
		
		super(gui, atributoUnico, detallesOferta);
		
		treeComentarios = new JTree(raiz);
		treeComentarios.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		treeComentarios.addTreeSelectionListener(this);
		
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
			
	
		reservar.setEnabled(gui.getControlador().isOfertaReservable());
			
		c.gridx = 0; c.gridy = 0;
		this.add(detallesInmueblePanel, c);
		
		c.gridx = 0; c.gridy = 2;
		JScrollPane scroll = new JScrollPane(treeComentarios);
		scroll.setPreferredSize(new Dimension(250, 100));
		this.add(scroll, c);
		
		c.gridx = 1; c.gridy = 1;
		this.add(contratar, c);
		c.gridx = 1; c.gridy = 2;
		this.add(reservar, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeComentarios.getLastSelectedPathComponent();

	    if (node == null)
	    	return;

	    gui.getControlador().showInfoComentario(node.getUserObject());	    
	}
}
