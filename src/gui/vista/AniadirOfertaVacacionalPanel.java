package gui.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AniadirOfertaVacacionalPanel extends AniadirOfertaPanel implements ActionListener {
	
	private JLabel etiquetaFechaFin = new JLabel("Fecha Fin: (DD.MM.YYYY)");
	private JTextField fechaFin = new JTextField();
	
	public AniadirOfertaVacacionalPanel(Gui gui) {
		super(gui);
		
		this.add(etiquetaFechaFin, 8);
		this.add(fechaFin, 9);
		
		botonGuardar.addActionListener( this );
	}
	
	public void actionPerformed(ActionEvent ev) {
		/*String mensaje;
		mensaje = "Tu oferta se ha guardado, en espera de ser aprobada";
		JOptionPane.showMessageDialog(null, mensaje);
		
		String mensaje;
		if ( this.gui.getControlador().aniadirOfertaVacacional( this.campoPrecio.getText(), this.fechaInicio.getText(), 
				this.campoDescripcion.getText(), this.fechaFin.getText(), this.ID ) )
		     mensaje = "Yes, " + this.campo.getText() + " es palíndromo";
		else mensaje = "No es palídromo. Try again ...";
		JOptionPane.showMessageDialog(null, mensaje);*/
	}

}
