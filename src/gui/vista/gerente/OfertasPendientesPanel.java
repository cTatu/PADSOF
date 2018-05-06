package gui.vista.gerente;

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
import gui.vista.oferta.DetallesPanelOferta;

public class OfertasPendientesPanel extends JPanel{

	private JTable tablaOfertasPendientes;
	private JScrollPane scroll;
	private DefaultTableModel model;
	
	Gui gui;
	
	public OfertasPendientesPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridLayout(0,1));
		
		
		tablaOfertasPendientes = new JTable(new DefaultTableModel(
				new Object[]{"Ofertante", "Fecha", "Precio", "Tipo"}, 0));
		
		this.model = (DefaultTableModel) tablaOfertasPendientes.getModel();
		tablaOfertasPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int fila = tablaOfertasPendientes.rowAtPoint(evt.getPoint());
			    gui.getControlador().showInfoOferta(fila);
			 }
			});
		
		scroll = new JScrollPane(tablaOfertasPendientes);
		scroll.setPreferredSize(new Dimension(500, 100));
		scroll.setVisible(true);
		this.add(scroll);
	}

	public void showInfoOferta(String atributoUnico, Object... detallesOferta) {
		this.remove(0);
		
		this.add(new DetallesPanelOfertaGerente(gui, atributoUnico, detallesOferta));
		this.repaint();
		this.revalidate();
	}

	public void addOfertaPendienteTabla(Object... ofertas) {
		 model.addRow(ofertas);
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
