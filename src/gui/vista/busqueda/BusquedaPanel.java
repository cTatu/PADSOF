/**
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.busqueda;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import gui.vista.Gui;

/**
 * Panel contenedor de busqueda, contiene botones para tipo de busqueda, y Busqueda basica
 */
public class BusquedaPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 9158073980294784853L;
	
	private GridBagConstraints c = new GridBagConstraints();
	private ButtonGroup grupoRadioButton = new ButtonGroup();
	
	private JRadioButton OpcionBusquedaVivienda = new JRadioButton("Vivienda");
	private JRadioButton OpcionBusquedaVacacional = new JRadioButton("Vacacional");
	
	protected BusquedaPanelBasico panelActivo;
	protected static Gui gui;

	private JButton botonBuscar = new JButton("\nBuscar");
	private boolean usuarioRegistrado;

	/**
	 * Constructor
	 *
	 * @param gui
	 *            the gui
	 * @param usuarioRegistrado
	 *            usuario que busca
	 */
	public BusquedaPanel(Gui gui, boolean usuarioRegistrado) {
		BusquedaPanel.gui = gui;
		this.usuarioRegistrado = usuarioRegistrado;
		this.setLayout(new GridBagLayout());
		
		c.gridx = 0;

		panelActivo = new BusquedaVacacionalPanel(gui, usuarioRegistrado);
		
		grupoRadioButton.add(OpcionBusquedaVacacional);
		grupoRadioButton.add(OpcionBusquedaVivienda);
		
		grupoRadioButton.setSelected(OpcionBusquedaVacacional.getModel(), true);
		
		OpcionBusquedaVacacional.addActionListener(this);
		OpcionBusquedaVivienda.addActionListener(this);
		
		botonBuscar.addActionListener(new ListenerBusqueda(panelActivo));
		
		JPanel panelRadioBotones = new JPanel(new GridLayout(0,2));
			panelRadioBotones.add(OpcionBusquedaVacacional);
			panelRadioBotones.add(OpcionBusquedaVivienda);

		this.add(panelRadioBotones, c);
		this.add(panelActivo, c);	
		this.add(botonBuscar, c);
	}
	
	/**
	 * Sets visible usuario registrado.
	 *
	 * @param visible
	 *            the new visible usuario registrado
	 */
	public void setVisibleUsuarioRegistrado(boolean visible) {
		panelActivo.setVisibleUsuarioRegistrado(visible);
		panelActivo.tablaOfertas.setVisible(true);
	}
	
	/**
	 * Adds the ofertas tabla.
	 *
	 * @param oferta
	 *            the oferta
	 */
	public void addOfertasTabla(Object... oferta) {
		panelActivo.addOfertasTabla(oferta);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.remove(1);
		
		if(grupoRadioButton.getSelection().equals(OpcionBusquedaVacacional.getModel()))
			this.add(new BusquedaVacacionalPanel(gui, usuarioRegistrado), c, 1);
		else
			this.add(new BusquedaViviendaPanel(gui, usuarioRegistrado), c, 1);
		
		panelActivo = (BusquedaPanelBasico) this.getComponent(1);
		
		this.revalidate();
		this.repaint();
	}

}

/**
 * ActionListener para buscar
 */
class ListenerBusqueda implements ActionListener{

	private BusquedaPanelBasico panelActivo;

	public ListenerBusqueda(BusquedaPanelBasico panelActivo) {
		this.panelActivo = panelActivo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panelActivo.tablaOfertas.setVisible(true);
    	if (panelActivo.usuarioRegistrado())
    		panelActivo.rellenarCamposDemandante();
    	panelActivo.rellenarCampos();

    	panelActivo.limpiarTabla();
    	
    	BusquedaPanel.gui.getControlador().buscar((Integer)panelActivo.campoCP.getValue(), 
    			panelActivo.fechaInicio1.getText(), panelActivo.fechaInicio2.getText(), 
    			panelActivo.fechaFin, panelActivo.duracionMeses, 
    			panelActivo.tipoDisponibilidad,
    			panelActivo.tipoOferta, panelActivo.valoracion);
    	
    	panelActivo.scroll.setVisible(true);
    	
    	panelActivo.revalidate();
    	panelActivo.repaint();
	}
	
}
