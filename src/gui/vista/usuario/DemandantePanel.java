package gui.vista.usuario;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import gui.vista.Gui;
import gui.vista.busqueda.BusquedaPanel;
import gui.vista.gerente.CambiarTarjetaPanel;
import gui.vista.gerente.OfertasPendientesPanel;
import gui.vista.oferta.DetallesPanelOferta;
import gui.vista.usuario.demandante.DetallesPanelComentario;
import gui.vista.usuario.demandante.DetallesPanelOfertaDemandante;

public class DemandantePanel extends UsuarioPanel implements ActionListener{

	/*protected Tabs del Super*/
	
	private JTable tablaMisOfertas;
	private JScrollPane scroll;
	private DetallesPanelOfertaDemandante panelOfertaDemandante;
	protected GridBagConstraints c = new GridBagConstraints();
	
	public DemandantePanel(Gui gui) {
		super(gui);
		
		Gui.panelBusquedaOfertas.setVisibleUsuarioRegistrado();
		
		tablaMisOfertas = new JTable(new DefaultTableModel(
				new Object[]{"Fecha Inicio", "Precio", "Tipo", "Disponibilidad"}, 0));
		
		tablaMisOfertas.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int fila = tablaMisOfertas.rowAtPoint(evt.getPoint());
			    gui.getControlador().showInfoOferta(fila);
			 }
			});
		
		super.botonAtras.setText("Atras");
		
		super.tabsUsuario.addTab("Busqueda", Gui.panelBusquedaOfertas);
		scroll = new JScrollPane(tablaMisOfertas);
		scroll.setPreferredSize(new Dimension(500, 100));
		super.tabsUsuario.addTab("Mis Ofertas", scroll);
	}

	@Override
	public void showInfoOferta(String atributoUnico, Object[] detallesInmueble, Object... detallesOferta) {
		this.remove(1);
		
		panelOfertaDemandante = new DetallesPanelOfertaDemandante(gui, atributoUnico, detallesInmueble, detallesOferta);
		c.gridx = 0; c.anchor = GridBagConstraints.CENTER; c.gridy = 1;
		this.add(panelOfertaDemandante, c);
		this.repaint();
		this.revalidate();
		this.gui.getControlador().showComentariosOferta();
	}

	@Override
	public void limpiarTabla() {
		// TODO Auto-generated method stub
		
	}

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
	
	public void addMisOfertas(Object... ofertas) {
		DefaultTableModel model = (DefaultTableModel) tablaMisOfertas.getModel();
		model.addRow(ofertas);
	}
	
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
	
	public void showInfoComentario(Object[] detallesComentario) {
		panelOfertaDemandante.addComentario(detallesComentario);
	}
}
