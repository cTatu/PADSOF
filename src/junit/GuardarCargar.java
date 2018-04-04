package junit;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import InmaculadApp;

public class GuardarCargar {

	@Test
	public void test() {
		InmaculadApp app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		
		try {
			app.guardarApp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		
		assertTrue(app != null);
		
	}

}
