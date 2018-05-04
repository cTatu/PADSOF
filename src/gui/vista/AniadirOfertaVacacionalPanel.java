package gui.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		String mensaje;
		
		/*if ( this.gui.getControlador().aniadirOfertaVacacional( Double.parseDouble(this.campoPrecio.getText()), 
				LocalDate.parse(super.fechaInicio.getText(), DateTimeFormatter.ISO_LOCAL_DATE), 
				this.campoDescripcion.getText(), 
				LocalDate.parse(this.fechaFin.getText(), DateTimeFormatter.ISO_LOCAL_DATE), 
				Integer.parseInt(Integer.parseInt(this.campoID.getText()) ))){
					
					mensaje = "Tu oferta se ha guardado, en espera de ser aprobada";
					JOptionPane.showMessageDialog(null, mensaje);
		}*/
	}

}
