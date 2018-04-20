package gui;
import controlador.*;
import paneles.BusquedaPanel;
import paneles.BusquedaVacacionalPanel;
import paneles.BusquedaViviendaPanel;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;


public class Gui extends JFrame implements ChangeListener {
	private JFrame panelBusqueda;
	private JTabbedPane pestañas = new JTabbedPane();
	private Controlador controlador;
	
	public Gui(String titulo) {
		super(titulo); // antes: JFrame ventana = new JFrame("Mi GUI");
		
		// obtener contenedor, asignar layout
		Container contenedor = this.getContentPane(); // antes: ventana.getContentPane();
		contenedor.setLayout(new FlowLayout());
		
		// crear componentes
		panelBusqueda = new BusquedaViviendaPanel();
		panelBusqueda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelBusqueda.setVisible(true);
		
		//pestañas.addTab("Buscar",  panelBusqueda);
		//pestañas.setSelectedIndex(0); // 0 means first
		
		// añadir componentes al contenedor
		contenedor.add(pestañas);
		// this.pack();
		
		// visibilidad inicial
		pestañas.setVisible( false );
		
		// Propuesta: PERMITIR REGRESAR A PANEL LOGIN DESDE CUALQUIER PESTAÑA
		// Proposed work: ALLOW RETURN TO PANEL LOGIN FROM ANY TAB
		
		// Para realizar acciones al cambiar de pestañas
		pestañas.addChangeListener( this );

		// mostrar this, en otros ejemplos era ventana, ahora this
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,400); // remove and uncomment this.pack above
		this.setVisible(true);	
	}

	public void setControlador(Controlador c) {
		this.controlador = c;
	}
	public Controlador getControlador() {
		return this.controlador;
	}
	
	
	@Override
	public void stateChanged(ChangeEvent ev) {
     // solamente a efectos de seguimiento del programa
   	 System.out.println( pestañas.getSelectedIndex() );
   	 System.out.println( pestañas.getSelectedComponent() );
	}

	public void loginResult(boolean loginOK) {
		/*if (loginOK) { 
			panelLogin.setVisible( false );
			pestañas.setVisible( true );
		} else {
			this.panelLogin.setError("login incorrecto");
		}*/
	}
}
