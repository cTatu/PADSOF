package gui.vista.comentario;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import gui.vista.Gui;
import gui.vista.usuario.demandante.DetallesPanelOfertaDemandante;

public class AddComentarioPanel extends JPanel implements ActionListener{

	private Gui gui;
	private GridBagConstraints c = new GridBagConstraints();
	
	private JTextArea textoComentario = new JTextArea();
	private JButton enviarComentario = new JButton("Comentar");
	private DetallesPanelOfertaDemandante panelPadre;

	public AddComentarioPanel(Gui gui, DetallesPanelOfertaDemandante panelPadre) {
		this.gui = gui;
		this.panelPadre = panelPadre;
		
		enviarComentario.addActionListener(this);
		textoComentario.
		
		c.gridx = 0; c.gridy = 0; 
		c.gridwidth = 3;
		this.add(textoComentario, c);
		
		c.gridx = 0; c.gridy = 1; 
		c.gridwidth = 1; c.anchor = GridBagConstraints.WEST;	
		JPanel panelEnviarComment = new JPanel();
			panelEnviarComment.add(enviarComentario);
		this.add(panelEnviarComment, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro/a de querer enviar este comentario?", "Confirmacion Comentario", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			panelPadre.limpiarComentarios();
			gui.getControlador().addComentario(-1, textoComentario.getText());
			panelPadre.showComentarios();
		}	
	}

}