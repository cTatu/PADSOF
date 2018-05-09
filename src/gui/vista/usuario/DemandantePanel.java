/*
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.usuario;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import gui.vista.Gui;
import gui.vista.busqueda.BusquedaPanel;
import gui.vista.gerente.CambiarTarjetaPanel;
import gui.vista.gerente.OfertasPendientesPanel;
import gui.vista.oferta.DetallesPanelOferta;
import gui.vista.usuario.demandante.DetallesPanelOfertaInmueble;

public class DemandantePanel extends UsuarioPanel implements ChangeListener {

	/*protected Tabs del Super*/
	
	private JTable tablaMisOfertas;
	private JScrollPane scroll;
	private DetallesPanelOfertaInmueble panelOfertaDemandante;
	protected GridBagConstraints c = new GridBagConstraints();
	
	/**
	 * Instantiates a new demandante panel.
	 *
	 * @param gui
	 *            the gui
	 */
	public DemandantePanel(Gui gui) {
		super(gui);
		
		Gui.panelBusquedaOfertas.setVisibleUsuarioRegistrado(true);
		
		super.tabsUsuario.addChangeListener(this);
		
		tablaMisOfertas = new JTable(new DefaultTableModel(
				new Object[]{"Fecha Inicio", "Precio", "Tipo", "Disponibilidad"}, 0));
		
		tablaMisOfertas.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int fila = tablaMisOfertas.rowAtPoint(evt.getPoint());
			    gui.getControlador().showInfoOferta(fila);
			 }
			});
		
		super.tabsUsuario.addTab("Busqueda", Gui.panelBusquedaOfertas);
		scroll = new JScrollPane(tablaMisOfertas);
		scroll.setPreferredSize(new Dimension(500, 100));
		super.tabsUsuario.addTab("Mis Ofertas", scroll);
	}

	/* (non-Javadoc)
	 * @see gui.vista.usuario.UsuarioPanel#showInfoOferta(java.lang.String, java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public void showInfoOferta(String atributoUnico, Object[] detallesInmueble, Object... detallesOferta) {
		this.remove(1);
		
		panelOfertaDemandante = new DetallesPanelOfertaInmueble(gui, atributoUnico, true,detallesInmueble, detallesOferta);
		c.gridx = 0; c.anchor = GridBagConstraints.CENTER; c.gridy = 1;
		this.add(panelOfertaDemandante, c);
		this.repaint();
		this.revalidate();
		this.gui.getControlador().showComentariosOferta();
	}

	/* (non-Javadoc)
	 * @see gui.vista.usuario.UsuarioPanel#limpiarTablaOfertas()
	 */
	@Override
	public void limpiarTablaOfertas() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (botonAtras.getText().equals("Atras")) {
			this.remove(1);
			c.gridx = 0; c.anchor = GridBagConstraints.CENTER; c.gridy = 1;
			this.add(tabsUsuario, c);
			
			this.repaint();
			this.revalidate();
		}if (botonAtras.getText().equals("Cerrar Sesion"))
			gui.getControlador().cerrarSesion(true);
	}
	
	/**
	 * Adds the mis ofertas.
	 *
	 * @param ofertas
	 *            the ofertas
	 */
	public void addMisOfertas(Object... ofertas) {
		DefaultTableModel model = (DefaultTableModel) tablaMisOfertas.getModel();
		model.addRow(ofertas);
	}
	
	/**
	 * Show mis ofertas.
	 */
	public void showMisOfertas() {
		this.remove(1);
		
		gui.getControlador().rellenarMisOfertas();
		c.gridy = 1;
		this.add(tabsUsuario, c);
		tabsUsuario.setSelectedIndex(1);
		
		this.repaint();
		this.revalidate();
		
		scroll.repaint();
		scroll.revalidate();
	}
	
	/**
	 * Show info comentario.
	 *
	 * @param detallesComentario
	 *            the detalles comentario
	 */
	public void showInfoComentario(Object[] detallesComentario) {
		panelOfertaDemandante.addComentario(detallesComentario);
	}

	/* (non-Javadoc)
	 * @see gui.vista.usuario.UsuarioPanel#isDemandante()
	 */
	@Override
	public boolean isDemandante() {
		return true;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent ev) {
		if (super.tabsUsuario.getSelectedIndex() == 1) {
			if(!this.gui.getControlador().rellenarMisOfertas()) {
				super.tabsUsuario.setSelectedIndex(0);
				gui.mensajeInfo("Todavia no tienes ninguna oferta contratada o reservada", "Sin ofertas", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
