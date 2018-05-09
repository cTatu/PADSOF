/**
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.gerente;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;

/**
 * Panel donde esta la tabla de ofertas pendientes de ser moderadas
 */
public class OfertasPendientesPanel extends JPanel{

	private static final long serialVersionUID = -4512296898435005115L;
	
	private JTable tablaOfertasPendientes;
	private JScrollPane scroll;
	private DefaultTableModel model;
	
	Gui gui;
	
	/**
	 * Constructor
	 *
	 * @param gui
	 *            the gui
	 */
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

	/**
	 * Show info oferta.
	 *
	 * @param atributoUnico
	 *            the atributo unico
	 * @param detallesCliente
	 *            the detalles cliente
	 * @param detallesOferta
	 *            the detalles oferta
	 */
	public void showInfoOferta(String atributoUnico,Object[] detallesCliente, Object... detallesOferta) {
		this.remove(0);
		
		this.add(new DetallesPanelOfertaGerente(gui, atributoUnico, detallesCliente, detallesOferta));
		this.repaint();
		this.revalidate();
	}

	/**
	 * Aniade una oferta pendiente de moderarse a la tabla
	 *
	 * @param ofertas
	 *            the ofertas
	 */
	public void addOfertaPendienteTabla(Object... ofertas) {
		 model.addRow(ofertas);
	}
	
	/**
	 * Atras.
	 *
	 * @return true, if successful
	 */
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

	/**
	 * Limpiar tabla.
	 */
	public void limpiarTabla() {
		gui.limpiarTabla(model);
	}
}
