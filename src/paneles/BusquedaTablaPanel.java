package paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.TableModel;

public class BusquedaTablaPanel extends JPanel implements ActionListener{
	
	private JTable tablaOfertas = new JTable();
	private JButton botonBuscar = new JButton("Nueva Busqueda");
	
	public BusquedaTablaPanel() {

		this.add(tablaOfertas);
		this.add(botonBuscar);
		
		botonBuscar.addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		  BusquedaPanel busqueda = new BusquedaPanel();
		  busqueda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  busqueda.setVisible(true);
		  
	}
}
