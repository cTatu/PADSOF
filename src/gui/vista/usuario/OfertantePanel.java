/**
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.usuario;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;
import gui.vista.inmueble.DetallesPanelInmueble;
import gui.vista.publicar.PublicarPanel;
import gui.vista.usuario.demandante.DetallesPanelOfertaInmueble;

/**
 * Clase soporte y que instancia los paneles que usa un usuario ofertante *
 */
public class OfertantePanel extends UsuarioPanel implements ChangeListener, ActionListener {

	private static final long serialVersionUID = -8986027446295986698L;
	private PublicarPanel panelPublicar;
	private JScrollPane panelMisOfertas;
	private JScrollPane panelMisInmuebles;
	private JTable tablaMisOfertas;
	private JTable tablaMisInmuebles;
	private DefaultTableModel model;
	private DetallesPanelOfertaInmueble panelOfertaOfertante;
	private DetallesPanelInmueble panelInfoInmueble;
	
	/**
	 * Constructor
	 *
	 * @param gui
	 *            the gui
	 */
	public OfertantePanel(Gui gui) {
		super(gui);
		
		panelPublicar = new PublicarPanel(gui, this);
		
		tablaMisOfertas = new JTable(new DefaultTableModel(
				new Object[]{"Fecha Inicio", "Precio", "Tipo", "Disponibilidad"}, 0));
		
		super.tabsUsuario.addChangeListener(this);
		
		super.botonAtras.removeActionListener(this);
		super.botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (botonAtras.getText().equals("Cerrar Sesion"))
					gui.getControlador().cerrarSesion(true);
				else {
					remove(1);
					add(tabsUsuario, c);
					botonAtras.setText("Cerrar Sesion");
					revalidate();
					repaint();
				}					
			}
		});
		
		tablaMisOfertas.addMouseListener(new java.awt.event.MouseAdapter() {
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int fila = tablaMisOfertas.rowAtPoint(evt.getPoint());
			    gui.getControlador().showInfoOferta(fila);
			    botonAtras.setText("Atras");
			 }
			});
		
		tablaMisInmuebles = new JTable(new DefaultTableModel(
				new Object[]{"Codigo Postal", "Localizacion"}, 0));
		
		tablaMisInmuebles.addMouseListener(new java.awt.event.MouseAdapter() {
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int fila = tablaMisInmuebles.rowAtPoint(evt.getPoint());
			    if (gui.getControlador().isPublicandoOferta()) {
			    	gui.getControlador().setInmuebleSeleccionado(fila);
			    	tabsUsuario.setSelectedComponent(panelPublicar);
			    	panelPublicar.getBotonOferta().doClick();
			    	gui.getControlador().setPublicarOferta(false);
			    }else
			    	gui.getControlador().showInfoInmueble(fila);
			    botonAtras.setText("Atras");
			 }
			});
		
		super.tabsUsuario.addTab("Publicar", panelPublicar);
		panelMisOfertas = new JScrollPane(tablaMisOfertas);
		panelMisOfertas.setPreferredSize(new Dimension(500, 100));
		super.tabsUsuario.addTab("Mis Ofertas", panelMisOfertas);
		
		panelMisInmuebles = new JScrollPane(tablaMisInmuebles);
		panelMisInmuebles.setPreferredSize(new Dimension(500, 100));
		super.tabsUsuario.addTab("Mis inmuebles", panelMisInmuebles);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (gui.getControlador().isPublicandoOferta()) {
			panelPublicar.showPublicarOferta();
		}else {
			limpiarTablaInmuebles();
			if(this.gui.getControlador().rellenarMisInmuebles()) {
				super.tabsUsuario.setSelectedComponent(panelMisInmuebles);
				gui.getControlador().setPublicarOferta(true);
			}else
				gui.mensajeInfo("Todavia no tienes ningun inmueble creado", "Sin inmuebles", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Aniade los detalles de una oferta a la tabla
	 *
	 * @param ofertas
	 *            caracteristicas de oferta
	 */
	public void addMisOfertas(Object... ofertas) {
		model = (DefaultTableModel) tablaMisOfertas.getModel();
		model.addRow(ofertas);
	}

	/**
	 * Aniade los detalles de un inmuebles a la tabla .
	 *
	 * @param inmuebles
	 *            caracteristicas de inmuebles
	 */
	public void addMisInmuebles(Object... inmuebles) {
		model = (DefaultTableModel) tablaMisInmuebles.getModel();
		model.addRow(inmuebles);
	}

	/**
	 * Gets the panel publicar.
	 *
	 * @return the panel publicar
	 */
	public PublicarPanel getPanelPublicar() {
		return panelPublicar;
	}

	/* (non-Javadoc)
	 * @see gui.vista.usuario.UsuarioPanel#showInfoOferta(java.lang.String, java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public void showInfoOferta(String atributoUnico, Object[] detallesInmueble, Object... detallesOferta) {
		this.remove(1);
		
		panelOfertaOfertante = new DetallesPanelOfertaInmueble(gui, atributoUnico, false,detallesInmueble, detallesOferta);
		c.gridx = 0; c.anchor = GridBagConstraints.CENTER; c.gridy = 1;
		this.add(panelOfertaOfertante, c);
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * Muestra la info del inmueble, caracteristicas como el codigo
	 * postal, la descripcion, las caracteristicas
	 *
	 * @param detallesInmueble
	 *            lista con carcateristicas del inmueble
	 */
	public void showInfoInmueble(Object... detallesInmueble) {
		this.remove(1);
		
		panelInfoInmueble = new DetallesPanelInmueble(gui, detallesInmueble);
		c.gridx = 0; c.anchor = GridBagConstraints.CENTER; c.gridy = 1;
		this.add(panelInfoInmueble, c);
		this.repaint();
		this.revalidate();
	}

	/* (non-Javadoc)
	 * @see gui.vista.usuario.UsuarioPanel#limpiarTablaOfertas()
	 */
	public void limpiarTablaOfertas() {
		model = (DefaultTableModel) tablaMisOfertas.getModel();
		model.setRowCount(0);
	}
	
	/**
	 * Limpiar tabla inmuebles.
	 */
	public void limpiarTablaInmuebles() {
		model = (DefaultTableModel) tablaMisInmuebles.getModel();
		model.setRowCount(0);
	}

	/* (non-Javadoc)
	 * @see gui.vista.usuario.UsuarioPanel#isDemandante()
	 */
	@Override
	public boolean isDemandante() {
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent ev) {
		if (super.tabsUsuario.getSelectedComponent().equals(panelMisOfertas)) {
			limpiarTablaOfertas();
			if(!this.gui.getControlador().rellenarMisOfertas()) {
				gui.getControlador().checkRectificaciones();
				super.tabsUsuario.setSelectedIndex(0);
				gui.mensajeInfo("Todavia no tienes ninguna oferta creada", "Sin ofertas", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if (super.tabsUsuario.getSelectedComponent().equals(panelMisInmuebles)) {
			limpiarTablaInmuebles();
			if(!this.gui.getControlador().rellenarMisInmuebles()) {
				super.tabsUsuario.setSelectedIndex(0);
				gui.mensajeInfo("Todavia no tienes ningun inmueble creado", "Sin inmuebles", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
