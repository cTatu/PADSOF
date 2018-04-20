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
	private JTabbedPane pesta�as = new JTabbedPane();
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
		
		//pesta�as.addTab("Buscar",  panelBusqueda);
		//pesta�as.setSelectedIndex(0); // 0 means first
		
		// a�adir componentes al contenedor
		contenedor.add(pesta�as);
		// this.pack();
		
		// visibilidad inicial
		pesta�as.setVisible( false );
		
		// Propuesta: PERMITIR REGRESAR A PANEL LOGIN DESDE CUALQUIER PESTA�A
		// Proposed work: ALLOW RETURN TO PANEL LOGIN FROM ANY TAB
		
		// Para realizar acciones al cambiar de pesta�as
		pesta�as.addChangeListener( this );

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
   	 System.out.println( pesta�as.getSelectedIndex() );
   	 System.out.println( pesta�as.getSelectedComponent() );
	}

	public void loginResult(boolean loginOK) {
		/*if (loginOK) { 
			panelLogin.setVisible( false );
			pesta�as.setVisible( true );
		} else {
			this.panelLogin.setError("login incorrecto");
		}*/
	}
}
