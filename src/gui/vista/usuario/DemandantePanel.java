package gui.vista.usuario;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.tree.DefaultMutableTreeNode;

import gui.vista.Gui;
import gui.vista.busqueda.BusquedaPanel;
import gui.vista.gerente.CambiarTarjetaPanel;
import gui.vista.gerente.OfertasPendientesPanel;
import gui.vista.oferta.DetallesPanelOferta;
import gui.vista.usuario.demandante.DetallesPanelOfertaDemandante;

public class DemandantePanel extends UsuarioPanel implements ActionListener{

	private MisOfertasPanel misOfertasPanel;
	private DetallesPanelOfertaDemandante panelOfertaDemandante;

	public DemandantePanel(Gui gui) {
		super(gui);
		
		Gui.panelBusquedaOfertas.setVisibleUsuarioRegistrado();
		
		misOfertasPanel = new MisOfertasPanel(gui);
		
		tabsUsuario.addTab("Busqueda", Gui.panelBusquedaOfertas);
		tabsUsuario.addTab("Mis Ofertas", this.misOfertasPanel);
	}

	@Override
	public void showInfoOferta(String atributoUnico, Object[] detallesInmueble, Object... detallesOferta) {
		this.remove(1);
		
		panelOfertaDemandante = new DetallesPanelOfertaDemandante(gui, atributoUnico, gui.getControlador().getComentariosOferta(), detallesInmueble,detallesOferta);
		this.add(panelOfertaDemandante);
		this.repaint();
		this.revalidate();
		this.gui.getControlador().getComentariosOferta();
	}

	@Override
	public void limpiarTabla() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.getControlador().cerrarSesion(true);
	}
}
