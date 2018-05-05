package gui.vista.gerente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.vista.Gui;

public class DetallesPanelOferta extends JPanel implements ActionListener{
	
	private JButton aceptar = new JButton("Aceptar");
	private JButton rechazar = new JButton("Rechazar");
	private JButton rectificar = new JButton("Enviar Rectificacion");
	
	private Map<JTextField, JTextField> rectificaciones = new HashMap<>();
	
	private Gui gui;
	
	public DetallesPanelOferta(Gui gui, Object... detallesOferta) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
