/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.gerente;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import gui.vista.Gui;

public class CambiarTarjetaPanel extends JPanel implements TableModelListener{
	
	protected JTable tablaUsuariosTarjeta;
	private String tarjetaVieja;
	
	Gui gui;
	
	/**
	 * Instantiates a new cambiar tarjeta panel.
	 *
	 * @param gui
	 *            the gui
	 */
	public CambiarTarjetaPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new FlowLayout());
		
		tablaUsuariosTarjeta = new JTable(new DefaultTableModel(
				new Object[]{"NIF", "Nombre", "Tarjeta"}, 5));
		
		tablaUsuariosTarjeta.getModel().addTableModelListener(this);
		
		tablaUsuariosTarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int row = tablaUsuariosTarjeta.rowAtPoint(evt.getPoint());
			    int col = tablaUsuariosTarjeta.columnAtPoint(evt.getPoint());
			    TableModel model = (TableModel) tablaUsuariosTarjeta.getModel();
			    tarjetaVieja = String.valueOf(model.getValueAt(row, col));
			 }
			});
		
		JScrollPane scroll = new JScrollPane(tablaUsuariosTarjeta);
		scroll.setPreferredSize(new Dimension(500, 100));
		scroll.setVisible(true);
		this.add(scroll);
	}
	
	/**
	 * Adds the usuarios tarejta tabla.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 */
	public void addUsuariosTarejtaTabla(Object... tarjetas) {
		DefaultTableModel model = (DefaultTableModel) tablaUsuariosTarjeta.getModel();
		model.insertRow(0,tarjetas);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        String columnName = model.getColumnName(column);
        tablaUsuariosTarjeta.getModel().removeTableModelListener(this);
        if (columnName.equals("Tarjeta")) {
        	String nuevaTarjeta = String.valueOf(model.getValueAt(row, column));
        	String usuarioNIF = String.valueOf(model.getValueAt(row, 0));
        	String nombre = String.valueOf(model.getValueAt(row, 1));
        	if (!nuevaTarjeta.equals(tarjetaVieja)) {
        		int respuesta = JOptionPane.showConfirmDialog(null, "Cambiar la tarjeta del \"" + nombre + "\" a: " + nuevaTarjeta + " ?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            	if (respuesta == JOptionPane.YES_OPTION)
            		this.gui.getControlador().cambiarTarjeta(usuarioNIF, nuevaTarjeta);
            	else
            		model.setValueAt(tarjetaVieja, row, column);
        	}
        	else
        		model.setValueAt(tarjetaVieja, row, column);        	
        }
        tablaUsuariosTarjeta.getModel().addTableModelListener(this);
	}


}
