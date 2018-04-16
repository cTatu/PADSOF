package paneles;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;


public class BusquedaPanel extends JPanel implements ActionListener {

	// componentes privados del panel, pero anin NO AniADIDOS
	private JLabel etiquetaCP = new JLabel("Codigo Postal:");
	private JSpinner campoCP = new JSpinner();
	
	private JLabel etiquetaValoracion = new JLabel("Valoracion:");
	private JSpinner campoValoracion = new JSpinner();	
	
	private JLabel etiquetaFechaInicio1 = new JLabel("Fecha Inicio 1: (DD.MM.YYYY)");
	private JTextField fechaInicio1 = new JTextField();
	
	private JLabel etiquetaFechaInicio2 = new JLabel("Fecha Inicio 2: (DD.MM.YYYY)");
	private JTextField fechaInicio2 = new JTextField();
	
	private JButton botonBuscar = new JButton("Buscar");
	
	public BusquedaPanel() {
		this.setLayout(new FlowLayout());

		// asociar acciones a componentes
		botonBuscar.addActionListener( this );
		
		// aniadir componentes a "this" panel
		this.add(etiquetaCP);
		this.add(campoCP);
		this.add(etiquetaValoracion);
		this.add(campoValoracion);
		
		this.add(etiquetaFechaInicio1);
		this.add(fechaInicio1);
		
		this.add(etiquetaFechaInicio2);
		this.add(fechaInicio2);
	}

	// En el futuro, puede ser util tener getters como este:
	// public String getText() {    return this.campo.getText();  }
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		  //JOptionPane.showMessageDialog(null, this.campo.getText());
	}

}
