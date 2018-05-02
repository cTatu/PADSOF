package gui.controlador;

import app.InmaculadApp;
import busqueda.BusquedaVacacional;
import gui.vista.Gui;


public class Controlador {
	
	private Gui gui;
	private InmaculadApp app;
	
	public Controlador(Gui gui, InmaculadApp app) {
		this.gui = gui; this.app = app;
	}
	
	public void login(String NIF, String password) {
		this.gui.loginResult( this.app.iniciarSesion( NIF, password ) );
	}
	
	public void cerrarSesion(boolean GuardarNoGuardar) {
		this.gui.cerrarSesionResult( this.app.cerrarSesion( true ));
	}

	public void buscar(BusquedaVacacional criterios) {
		//this.gui.		
	}

}
