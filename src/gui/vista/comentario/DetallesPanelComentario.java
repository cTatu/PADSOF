/**
 * @author David Pascual y Cristian Tatu
 */
package gui.vista.comentario;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.vista.Gui;
import gui.vista.usuario.demandante.DetallesPanelOfertaInmueble;

/**
 * Clase que contiene los detalles de un comentario
 */
public class DetallesPanelComentario extends JPanel implements ActionListener{

	private static final long serialVersionUID = -4940410480155977952L;
	
	private Gui gui;
	private GridBagConstraints c = new GridBagConstraints();
	private JLabel texto = new JLabel();
	private JLabel valoracion = new JLabel();
	
	private JLabel respuestaLabel = new JLabel("Respuesta:");
	private JTextArea respueta = new JTextArea();
	private JLabel valoracionLabel = new JLabel("Valoracion:");
	private JTextField valorar = new JTextField();
	
	private JButton responder = new JButton("Responder");
	private JButton subComentarios = new JButton("Ver Comentarios");
	
	private Integer comentarioID ;
	private DetallesPanelOfertaInmueble panelPadre;

	/**
	 * Constructor
	 *
	 * @param gui
	 *            the gui
	 * @param detallesComentario
	 *             detalles comentario a mostrar
	 * @param detallesPanelOfertaDemandante
	 *            the detalles panel oferta demandante
	 */
	public DetallesPanelComentario(Gui gui, Object[] detallesComentario, DetallesPanelOfertaInmueble detallesPanelOfertaDemandante) {
		this.gui = gui;
		this.panelPadre = detallesPanelOfertaDemandante;
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEtchedBorder());
		
		texto.setText("Opinion: " + String.valueOf(detallesComentario[0]));
		valoracion.setText("Valoracion: " + String.valueOf(detallesComentario[1]));
		comentarioID = (Integer) detallesComentario[2];
		
		subComentarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.getControlador().showSubComentarios(comentarioID);
			}
		});
		
		responder.addActionListener(this);
		
		respueta.setPreferredSize(new Dimension(150, 40));
		respueta.setVisible(false);
		valorar.setPreferredSize(new Dimension(50, 20));
		valorar.setVisible(false);
		respuestaLabel.setVisible(false);
		valoracionLabel.setVisible(false);
		
		c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;
		c.gridwidth = 4; c.gridheight = 1;
		this.add(texto, c);
		
		c.gridx = 0; c.gridy = 2; c.gridwidth = 1;
		this.add(valoracion, c);
		
		c.gridx = 0; c.gridy = 3;
		c.gridheight = 1;
		this.add(respuestaLabel, c);
		
		c.gridx = 0; c.gridy = 4;
		c.gridheight = 2;
		this.add(respueta, c);
		
		c.gridx = 1; c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER; 
		c.gridheight = 1; c.gridwidth = 1;
		this.add(valoracionLabel, c);
		
		c.gridx = 1; c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER; 
		c.gridheight = 1; c.gridwidth = 1;
		this.add(valorar, c);
		
		c.gridx = 3; c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		this.add(responder, c);
		
		c.gridx = 3; c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		this.add(subComentarios, c);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!respueta.isVisible() && !valorar.isVisible()) {
			respueta.setVisible(true);
			valorar.setVisible(true);
			respuestaLabel.setVisible(true);
			valoracionLabel.setVisible(true);
			return;
		}
		panelPadre.limpiarComentarios();
		gui.getControlador().addComentario(comentarioID, respueta.getText(), valorar.getText());
	}

}
