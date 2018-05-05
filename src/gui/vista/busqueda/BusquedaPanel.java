package gui.vista.busqueda;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gui.vista.Gui;
import oferta.Oferta;

public class BusquedaPanel extends JPanel implements ActionListener{

	private GridBagConstraints c = new GridBagConstraints();
	private ButtonGroup grupoRadioButton = new ButtonGroup();
	
	private JRadioButton OpcionBusquedaVivienda = new JRadioButton("Vivienda");
	private JRadioButton OpcionBusquedaVacacional = new JRadioButton("Vacacional");
	
	protected BusquedaPanelBasico panelActivo;

	private JButton botonBuscar = new JButton("\nBuscar");
	private boolean usuarioRegistrado;
	
	protected Gui gui;
	
	public BusquedaPanel(Gui gui) {
		this.gui = gui;
		this.setLayout(new GridBagLayout());
		
		c.gridx = 0;

		panelActivo = new BusquedaVacacionalPanel(gui, false);
		
		grupoRadioButton.add(OpcionBusquedaVacacional);
		grupoRadioButton.add(OpcionBusquedaVivienda);
		
		grupoRadioButton.setSelected(OpcionBusquedaVacacional.getModel(), true);
		
		OpcionBusquedaVacacional.addActionListener(this);
		OpcionBusquedaVivienda.addActionListener(this);
		
		botonBuscar.addActionListener(new ListenerBusqueda(panelActivo, gui));
		
		JPanel panelRadioBotones = new JPanel(new GridLayout(0,2));
			panelRadioBotones.add(OpcionBusquedaVacacional);
			panelRadioBotones.add(OpcionBusquedaVivienda);
			
		
			
		this.add(panelRadioBotones, c);
		this.add(panelActivo, c);	
		this.add(botonBuscar, c);
	}
	
	public void setVisibleUsuarioRegistrado(boolean visible) {
		usuarioRegistrado = visible;
		panelActivo.setVisibleUsuarioRegistrado(usuarioRegistrado);
		panelActivo.tablaOfertas.setVisible(true);
	}
	
	public void addOfertasTabla(Object... oferta) {
		panelActivo.addOfertasTabla(oferta);
	}
	
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


class ListenerBusqueda implements ActionListener{
	
	private BusquedaPanelBasico panelActivo;
	private Gui gui;
	
	public ListenerBusqueda(BusquedaPanelBasico panelActivo, Gui gui) {
		this.panelActivo = panelActivo;
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LocalDate fecha1 = null, fecha2 = null, fechafin = null;
		String fechaError = null;
		
		panelActivo.tablaOfertas.setVisible(true);
    	if (panelActivo.isDemandate())
    		panelActivo.rellenarCamposDemandante();
    	panelActivo.rellenarCampos();
    	
    	
    	try {
    		fechaError = "Fecha Inicio 1";
    		fecha1 = LocalDate.parse(panelActivo.fechaInicio1.getText(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
    		fechaError = "Fecha Inicio 2";
    		fecha2 = LocalDate.parse(panelActivo.fechaInicio2.getText(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
    		fechaError = "Fecha Fin";
    		fechafin = LocalDate.parse(panelActivo.fechaFin.orElse(""), DateTimeFormatter.ofPattern("d/MM/yyyy"));
    	}catch (DateTimeParseException exception) {
    		JOptionPane.showMessageDialog(null, fechaError + " no tienen un formato valido", "Fecha Incorrecta", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	gui.getControlador().buscar((Integer)panelActivo.campoCP.getValue(), 
    			fecha1, fecha2, fechafin, panelActivo.duracionMeses.orElse(0), 
				panelActivo.tipoDisponibilidad.orElse("DISPONIBLE"),
				panelActivo.tipoOferta.orElse("VACACIONAL"), panelActivo.valoracion.orElse(0.0));
    	
	}
	
}
