/**
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.usuario;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;
import gui.vista.usuario.demandante.DetallesPanelOfertaInmueble;

/**
 * Clase panel que contiene el soporte para los demas paneles de demandante
 */
public class DemandantePanel extends UsuarioPanel implements ChangeListener {
	private static final long serialVersionUID = -3000666218379715271L;
	
	/*protected Tabs del Super*/
	
	private JTable tablaMisOfertas;
	private JScrollPane scroll;
	private DetallesPanelOfertaInmueble panelOfertaDemandante;
	protected GridBagConstraints c = new GridBagConstraints();
	
	/**
	 * Constructor
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

	/*
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
	 * Aniade las cracteristicas de una oferta a la tabla
	 *
	 * @param ofertas
	 *            lista de caracteristicas
	 */
	public void addMisOfertas(Object... ofertas) {
		DefaultTableModel model = (DefaultTableModel) tablaMisOfertas.getModel();
		model.addRow(ofertas);
	}
	
	/**
	 * Ensenia la pestania de las ofertas
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
	 * Muestra los detalles de los comentarios como
	 * responder o valoraciones
	 *
	 * @param detallesComentario
	 *            informacion del comentario, como el texto y la valoracion
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
