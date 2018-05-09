/**
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.usuario.demandante;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import gui.vista.Gui;
import gui.vista.comentario.AddComentarioPanel;
import gui.vista.comentario.DetallesPanelComentario;
import gui.vista.inmueble.DetallesPanelInmueble;
import gui.vista.oferta.DetallesPanelOferta;

/**
 * Panel que muestra los detalles de un inmueble dado
 */
public class DetallesPanelOfertaInmueble extends DetallesPanelOferta{
	
	private static final long serialVersionUID = -8574447427528427389L;
	
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel panelComentarios = new JPanel(new GridLayout(0, 1));
	private JButton contratar = new JButton("Contratar");
	private JButton reservar = new JButton("Reservar");
	private JTabbedPane comentariosTabs = new JTabbedPane();
	private JPanel addComentario;

	/**
	 * Constructor
	 *
	 * @param gui
	 *            the gui
	 * @param atributoUnico
	 *            el atributo propio del tipo de oferta, fecha fin o duracion meses
	 * @param isDemandante
	 *            si el panel es para un demandante
	 * @param detallesInmueble
	 *            las caracteristicas del inmueble
	 * @param detallesOferta
	 *            las caracteristicas de la oferta
	 */
	public DetallesPanelOfertaInmueble(Gui gui, String atributoUnico, boolean isDemandante, 
			              			   Object[] detallesInmueble, Object... detallesOferta) {
		
		super(gui, atributoUnico, detallesOferta);
		
		Rectangle rect = this.gui.getBounds();
		this.gui.setBounds(new Rectangle(rect.x, rect.y, rect.width, rect.height+150));
		
		contratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro/a de querer contrar esta oferta?", "Confirmacion Contratar", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION)
					gui.getControlador().contratarOferta();				
			}
		});
		
		reservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro/a de querer reservar esta oferta?", "Confirmacion Reserva", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION)
					gui.getControlador().reservarOferta();				
			}
		});
				
		JPanel detallesInmueblePanel = new DetallesPanelInmueble(gui, detallesInmueble);
			
		addComentario = new AddComentarioPanel(gui, this);
		
		JPanel contratarBoton = new JPanel();
			contratarBoton.add(contratar);
		contratarBoton.setVisible(gui.getControlador().isOfertaContratable());
		JPanel reservarBoton = new JPanel();
			reservarBoton.add(reservar);
		reservarBoton.setVisible(gui.getControlador().isOfertaReservable());
			
		c.gridx = 0; c.gridy = 0;
		this.add(detallesInmueblePanel, c);
		
	
		JScrollPane scroll = new JScrollPane(panelComentarios);
		scroll.setPreferredSize(new Dimension(400, 100));
		
		if (isDemandante) {
			comentariosTabs.addTab("Comentar", addComentario);
			reservarBoton.setVisible(false);
			contratarBoton.setVisible(false);
		}comentariosTabs.addTab("Comentarios", scroll);
		
		c.gridx = 0; c.gridy = 2;	
		this.add(comentariosTabs, c);
		
		c.gridx = 1; c.gridy = 1;
		this.add(contratarBoton, c);
		c.gridx = 1; c.gridy = 2;
		this.add(reservarBoton, c);
	}

	/* (non-Javadoc)
	 * @see gui.vista.oferta.DetallesPanelOferta#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	/**
	 * Limpiar comentarios.
	 */
	public void limpiarComentarios() {
		panelComentarios.removeAll();
		
		panelComentarios.revalidate();
		panelComentarios.repaint();
	}

	/**
	 * Aniade un comentario a la lista de comentarios del panel
	 *
	 * @param detallesComentario
	 *            caracteristicas del comentario como la valoracion y el texto
	 */
	public void addComentario(Object... detallesComentario) {
		panelComentarios.add(new DetallesPanelComentario(gui, detallesComentario, this));
		
		panelComentarios.revalidate();
		panelComentarios.repaint();
		
		this.revalidate();
		this.repaint();
	}

	/**
	 * Show comentarios.
	 */
	public void showComentarios() {
		comentariosTabs.setSelectedIndex(1);
		this.gui.getControlador().showComentariosOferta();
	}
}
