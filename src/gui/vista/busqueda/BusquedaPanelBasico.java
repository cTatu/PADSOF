/**
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.busqueda;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;

/**
 * Clase panel que contiene formulario de busqueda
 */
public abstract class BusquedaPanelBasico extends JPanel{

	private static final long serialVersionUID = 3452292749938534955L;
	
	private JLabel etiquetaCP = new JLabel("Codigo Postal:");
	protected JSpinner campoCP = new JSpinner();

	private JLabel etiquetaFechaInicio1 = new JLabel("Fecha Inicio 1: (d/MM/YYY)");
	protected JTextField fechaInicio1 = new JTextField();
	
	private JLabel etiquetaFechaInicio2 = new JLabel("Fecha Inicio 2: (d/MM/YYY)");
	protected JTextField fechaInicio2 = new JTextField();
	
	/*** Usuario Registrado ***/
	private JLabel etiquetaDisponibilidad = new JLabel("Tipo de Disponibilidad: ");
	protected JComboBox<String> disponibilidad = new JComboBox<String>();
	
	private JLabel etiquetaValoracion = new JLabel("Valoracion:");
	protected JTextField campoValoracion = new JTextField();	
	/**************************/
	private boolean usuarioRegistrado;
	
	protected Gui gui;
	protected JTable tablaOfertas;
	protected JScrollPane scroll;

	protected Integer duracionMeses;
	protected String valoracion;
	protected String fechaFin; 
	protected Object tipoDisponibilidad, tipoOferta;

	/**
	 * Constructor
	 *
	 * @param gui
	 *            the gui
	 * @param usuarioRegistrado
	 *            usuario que busca
	 */
	public BusquedaPanelBasico(Gui gui, boolean usuarioRegistrado) {
			this.gui = gui;
			this.usuarioRegistrado = usuarioRegistrado;
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			tablaOfertas = new JTable();
			
			tablaOfertas.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
			    int fila = tablaOfertas.rowAtPoint(evt.getPoint());
			    gui.getControlador().showInfoOferta(fila);
			 }
			});

			fechaInicio1.setPreferredSize( new Dimension( 250, 24 ) );
			fechaInicio2.setPreferredSize( new Dimension( 250, 24 ) );
			campoCP.setPreferredSize( new Dimension( 250, 24 ) );
			
			disponibilidad.setVisible(usuarioRegistrado);
			etiquetaDisponibilidad.setVisible(usuarioRegistrado);
			disponibilidad.addItem("CONTRATADO");
			disponibilidad.addItem("RESERVADO");
			disponibilidad.addItem("DISPONIBLE");
			disponibilidad.setPreferredSize( new Dimension( 250, 24 ) );
			
			etiquetaValoracion.setVisible(usuarioRegistrado);
			campoValoracion.setVisible(usuarioRegistrado);
			campoValoracion.setPreferredSize( new Dimension( 250, 24 ) );
			
			c.gridx = 0; c.gridy = 0;  
			this.add(etiquetaCP, c);
			c.gridx = 1; c.gridy = 0;
			this.add(campoCP, c);
			
			c.gridx = 0; c.gridy = 1; 
			this.add(etiquetaFechaInicio1, c);
			c.gridx = 1; c.gridy = 1; 
			this.add(fechaInicio1, c);
			
			c.gridx = 0; c.gridy = 2;  
			this.add(etiquetaFechaInicio2, c);
			c.gridx = 1; c.gridy = 2;
			this.add(fechaInicio2, c);
			
			c.gridx = 0; c.gridy = 3; 
			this.add(etiquetaDisponibilidad, c);
			c.gridx = 1; c.gridy = 3;
			this.add(disponibilidad, c);
			
			c.gridx = 0; c.gridy = 4;
			this.add(etiquetaValoracion, c);
			c.gridx = 1; c.gridy = 4; 
			this.add(campoValoracion, c);
			
			c.gridx = 0; c.gridy = 6;
			c.gridwidth = 2;
			scroll = new JScrollPane(tablaOfertas);
				scroll.setPreferredSize(new Dimension(500, 100));
			scroll.setVisible(false);
			this.add(scroll, c);
	}
	
	/**
	 * getter Usuario registrado.
	 *
	 * @return true, if successful
	 */
	protected boolean usuarioRegistrado() {
		return usuarioRegistrado;
	}
	
	/**
	 * Sets the visible usuario registrado.
	 *
	 * @param visible
	 *            the new visible usuario registrado
	 */
	public void setVisibleUsuarioRegistrado(boolean visible) {
		disponibilidad.setVisible(visible);
		etiquetaDisponibilidad.setVisible(visible);
		
		campoValoracion.setVisible(visible);
		etiquetaValoracion.setVisible(visible);

		usuarioRegistrado = visible;
	}
	
	/**
	 * Adds the ofertas tabla.
	 *
	 * @param oferta
	 *            the oferta
	 */
	public void addOfertasTabla(Object... oferta) {
		scroll.setVisible(true);
		DefaultTableModel model = (DefaultTableModel) tablaOfertas.getModel();
		model.addRow(oferta);
	}

	/**
	 * Rellenar campos demandante.
	 */
	protected void rellenarCamposDemandante() {
		tipoDisponibilidad = disponibilidad.getSelectedItem();
		valoracion = campoValoracion.getText();

	}
	
	/**
	 * Rellenar campos.
	 */
	protected abstract void rellenarCampos();

	/**
	 * Limpiar tabla.
	 */
	public void limpiarTabla() {
		DefaultTableModel model = (DefaultTableModel) tablaOfertas.getModel();
		gui.limpiarTabla(model);
	}

}
