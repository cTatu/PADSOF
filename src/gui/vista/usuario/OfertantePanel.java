package gui.vista.usuario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;
import gui.vista.busqueda.BusquedaPanel;
import gui.vista.cliente.PerfilPanel;
import gui.vista.cliente.PublicarPanel;
import gui.vista.inmueble.DetallesPanelInmueble;
import gui.vista.usuario.demandante.DetallesPanelOfertaInmueble;

public class OfertantePanel extends UsuarioPanel implements ChangeListener {

	private PublicarPanel panelPublicar;
	private JScrollPane panelMisOfertas;
	private JScrollPane panelMisInmuebles;
	private JTable tablaMisOfertas;
	private JTable tablaMisInmuebles;
	private DefaultTableModel model;
	private DetallesPanelOfertaInmueble panelOfertaOfertante;
	private DetallesPanelInmueble panelInfoInmueble;
	
	public OfertantePanel(Gui gui) {
		super(gui);
		
		panelPublicar = new PublicarPanel(gui);
		
		tablaMisOfertas = new JTable(new DefaultTableModel(
				new Object[]{"Fecha Inicio", "Precio", "Tipo", "Disponibilidad"}, 0));
		
		super.tabsUsuario.addChangeListener(this);
		
		tablaMisOfertas.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int fila = tablaMisOfertas.rowAtPoint(evt.getPoint());
			    gui.getControlador().showInfoOferta(fila);
			 }
			});
		
		tablaMisInmuebles = new JTable(new DefaultTableModel(
				new Object[]{"Codigo Postal", "Localizacion"}, 0));
		
		tablaMisInmuebles.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int fila = tablaMisInmuebles.rowAtPoint(evt.getPoint());
			    gui.getControlador().showInfoInmueble(fila);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.getControlador().cerrarSesion(true);
	}
	
	public void addMisOfertas(Object... ofertas) {
		model = (DefaultTableModel) tablaMisOfertas.getModel();
		model.addRow(ofertas);
	}

	public void addMisInmuebles(Object... inmuebles) {
		model = (DefaultTableModel) tablaMisOfertas.getModel();
		model.addRow(inmuebles);
	}

	public PublicarPanel getPanelPublicar() {
		return panelPublicar;
	}

	@Override
	public void showInfoOferta(String atributoUnico, Object[] detallesInmueble, Object... detallesOferta) {
		this.remove(1);
		
		panelOfertaOfertante = new DetallesPanelOfertaInmueble(gui, atributoUnico, false,detallesInmueble, detallesOferta);
		c.gridx = 0; c.anchor = GridBagConstraints.CENTER; c.gridy = 1;
		this.add(panelOfertaOfertante, c);
		this.repaint();
		this.revalidate();
	}
	
	public void showInfoInmueble(Object... detallesInmueble) {
		this.remove(1);
		
		panelInfoInmueble = new DetallesPanelInmueble(gui, detallesInmueble);
		c.gridx = 0; c.anchor = GridBagConstraints.CENTER; c.gridy = 1;
		this.add(panelInfoInmueble, c);
		this.repaint();
		this.revalidate();
	}

	@Override
	public void limpiarTablaOfertas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDemandante() {
		return false;
	}

	@Override
	public void stateChanged(ChangeEvent ev) {
		if (super.tabsUsuario.getSelectedIndex() == 2) {
			if(!this.gui.getControlador().rellenarMisOfertas()) {
				super.tabsUsuario.setSelectedIndex(0);
				gui.mensajeInfo("Todavia no tienes ninguna oferta creada", "Sin ofertas", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if (super.tabsUsuario.getSelectedIndex() == 1) {
			if(!this.gui.getControlador().rellenarMisInmuebles()) {
				super.tabsUsuario.setSelectedIndex(0);
				gui.mensajeInfo("Todavia no tienes ningun inmueble creado", "Sin inmuebles", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
