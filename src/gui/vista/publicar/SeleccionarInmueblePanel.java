package gui.vista.publicar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TabableView;

import gui.vista.Gui;
import gui.vista.inmueble.DetallesPanelInmueble;
import gui.vista.oferta.DetallesPanelOferta;

public class SeleccionarInmueblePanel extends JPanel{

	private JTable tablaSeleccionarInmueble;
	private JScrollPane scroll;
	private DefaultTableModel model;
	
	private Gui gui;
	
	public SeleccionarInmueblePanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridLayout(0,1));
		
		tablaSeleccionarInmueble = new JTable(new DefaultTableModel(
				new Object[]{"Codigo Postal", "Localizacion"}, 0));
		
		this.model = (DefaultTableModel) tablaSeleccionarInmueble.getModel();
		tablaSeleccionarInmueble.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int fila = tablaSeleccionarInmueble.rowAtPoint(evt.getPoint());
			    gui.getControlador().showInfoInmueble(fila);
			 }
			});
		
		scroll = new JScrollPane(tablaSeleccionarInmueble);
		scroll.setPreferredSize(new Dimension(500, 100));
		scroll.setVisible(true);
		this.add(scroll);
	}

	public void showInfoInmueble(Object... detallesInmueble) {
		this.remove(0);
		
		this.add(new DetallesPanelInmueble(gui, detallesInmueble));
		this.repaint();
		this.revalidate();
	}

	public void addInmueblesTabla(Object... inmuebles) {
		 model.addRow(inmuebles);
	}
	
	public boolean atras() {
		boolean atras = false;
		if (!this.getComponent(0).equals(scroll)) {
			this.remove(0);
			this.add(scroll);
			atras = true;
		}
		
		this.revalidate();
		this.repaint();
		return atras;			
	}

	public void limpiarTabla() {
		gui.limpiarTabla(model);
	}
}

