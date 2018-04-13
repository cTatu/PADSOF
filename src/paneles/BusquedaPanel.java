package paneles;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;


public class BusquedaPanel extends JPanel implements ActionListener {

	// componentes privados del panel, pero aún NO AÑADIDOS
	private JLabel etiquetaCP = new JLabel("Codigo Postal:");
	private JSpinner campoCP = new JSpinner();
	
	private JLabel etiquetaValoracion = new JLabel("Valoracion:");
	private JSpinner campoValoracion = new JSpinner();	
	
	private JLabel etiquetaFechaInicio1 = new JLabel("Fecha Inicio 1:");
	
	 // No existe esa clase
	JDatePickerImpl campoFechaInicio2 = new JDatePickerImpl(datePanel);
	
	private JButton botonBuscar = new JButton("Buscar");
	
	public BusquedaPanel() {
		this.setLayout(new FlowLayout());

		// asociar acciones a componentes
		botonBuscar.addActionListener( this );
		
		// añadir componentes a "this" panel
		this.add(etiqueta);
		this.add(campo);
		this.add(boton);
	}

	// En el futuro, puede ser util tener getters como este:
	// public String getText() {    return this.campo.getText();  }
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		  JOptionPane.showMessageDialog(null, this.campo.getText());
	}

}
