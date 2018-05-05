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

import gui.vista.Gui;

public class OfertasPendientesPanel extends JPanel implements ActionListener{

	private JTable tablaOfertasPendientes;

	
	private JButton atras = new JButton("Atras");
	
	Gui gui;
	
	public OfertasPendientesPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridLayout(0,1));
		
		tablaOfertasPendientes = new JTable(new DefaultTableModel(
				new Object[]{"Ofertante", "Fecha", "Precio", "Tipo"}, 5));
		
		tablaOfertasPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int fila = tablaOfertasPendientes.rowAtPoint(evt.getPoint());
			    gui.getControlador().showInfoOferta(fila);
			 }
			});
		
		atras.addActionListener(this);
		
		JScrollPane scroll = new JScrollPane(tablaOfertasPendientes);
		scroll.setPreferredSize(new Dimension(500, 100));
		scroll.setVisible(true);
		this.add(scroll);
	}

	public void showInfoOferta(Object... detallesOferta) {
		this.remove(0);
		
		this.add(new DetallesPanelOferta(gui, detallesOferta));
		this.repaint();
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.remove(0);
		this.add(this);
		this.repaint();
		this.revalidate();
	}

	public void addOfertaPendienteTabla(Object... ofertas) {
		DefaultTableModel model = (DefaultTableModel) tablaOfertasPendientes.getModel();
		model.insertRow(0,ofertas);
	}

}
