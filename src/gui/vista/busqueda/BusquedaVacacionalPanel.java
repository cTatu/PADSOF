/**
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.busqueda;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;

/**
 * Clase panel que contiene formulario de busqueda vacacional
 */
public class BusquedaVacacionalPanel extends BusquedaPanelBasico {

	private static final long serialVersionUID = -2265957649276708106L;
	
	private JLabel etiquetaFechaFin = new JLabel("Fecha Fin: (d/MM/YYY)");
	private JTextField fechaFin = new JTextField();
	
	/**
	 * Constructor
	 *
	 * @param gui
	 *            the gui
	 * @param usuarioRegistrado
	 *            usuario que busca
	 */
	public BusquedaVacacionalPanel(Gui gui, boolean usuarioRegistrado) {
		super(gui, usuarioRegistrado);
		GridBagConstraints c = new GridBagConstraints();
		
		super.tablaOfertas.setModel(new DefaultTableModel(
				new Object[]{"Fecha Inicio", "Descripcion", "Fecha Fin", "Precio"}, 0));
		tablaOfertas.getColumnModel().getColumn(0).setPreferredWidth(1);
		tablaOfertas.getColumnModel().getColumn(2).setPreferredWidth(1);
		tablaOfertas.getColumnModel().getColumn(3).setPreferredWidth(1);
		
		this.fechaFin.setPreferredSize( new Dimension( 250, 24 ) );
		
		c.gridx = 0; c.gridy = 5; 
		this.add(etiquetaFechaFin, c);
		c.gridx = 1; c.gridy = 5; 
		this.add(fechaFin, c);
	}

	/* (non-Javadoc)
	 * @see gui.vista.busqueda.BusquedaPanelBasico#rellenarCampos()
	 */
	@Override
	protected void rellenarCampos() {
		super.fechaFin = this.fechaFin.getText();
		super.tipoOferta = "vacacional";
	}

}
