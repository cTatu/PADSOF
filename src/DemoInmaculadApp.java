import java.util.*;

import fechasimulada.FechaSimulada;

/**
 * 
 */
public class DemoInmaculadApp{

	/**
	 * 
	 *
	 * @param args 
	 */
	public static void main(String[] args) {
		InmaculadApp app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		
		System.out.println(app.iniciarSesion("51999111X", "pezEspada")); // como ofertante
		Map<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Soleado", "Mucho");
		caracteristicas.put("Superfiice", "50m2");
		caracteristicas.put("Habitaciones", "3");
		
		System.out.println(app.crearInmueble(28845, "Barcelona", caracteristicas));
		
		FechaSimulada.fijarFecha(11, 05, 2015);
		
		System.out.println(app.añadirOfertaVacacional(550.0, FechaSimulada.getHoy().minusDays(5),
							"Perfecto para vacaciones", 
							FechaSimulada.getHoy().plusDays(2), 1));
		
		System.out.println(app.añadirOfertaVivienda(800.0, FechaSimulada.getHoy().minusDays(30), 
							"Perfecto para vivir", 24, 1));
		
		System.out.println(app.getInmuebles().size());

		app.cerrarSesion(false);
		app.iniciarSesion("", "BD911"); 	// como gerente
		
		System.out.println(app.getOfertasPendientes().size());
		
		System.out.println(app.aprobarOferta(app.getOfertasPendientes().get(0)));
		
		System.out.println(app.getOfertasPendientes().size());
		
		System.out.println(app.rechazarOferta(app.getOfertasPendientes().get(0)));
		
		app.cerrarSesion(false);
		app.iniciarSesion("", "BD911");  // como demandante
		
		
	}

}
