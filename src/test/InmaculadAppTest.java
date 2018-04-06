/*
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import app.InmaculadApp;
import busqueda.Busqueda;
import busqueda.BusquedaVacacional;
import cliente.Cliente;
import fechasimulada.FechaSimulada;
import tipos.TipoDisponibilidad;
import tipos.TipoOrdenar;

/**
 * Test para la app InmaculadApp.
 */
public class InmaculadAppTest {
	
	/** The app. */
	private InmaculadApp app;
	
	/**
	 * Inicializamos los atributos que usaremos para los tests.
	 */
	@Before
	public void inicializar() {
		app = InmaculadApp.getInstancia("Recursos\\ClientesEjemplo.txt", "BD911");
		
		app.iniciarSesion("51999111X", "pezEspada"); // como ofertante
	}
	
	/**
	 * Probar crear un inmueble con el cliente logeado.
	 */
	@Test
	public void crearInmuebleTest() {
		Map<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Terreno", "accidentado");
		
		assertTrue(app.crearInmueble(2884, "Gibraltar", caracteristicas));
		app.cerrarSesion(false);
	}
	
	/**
	 * Probar crear un inmueble con el rol de demandante.
	 */
	@Test
	public void crearInmuebleDemandanteTest() {
		app.iniciarSesion("55555111Z", "NoSeSaBe");  // como demandante
		
		Map<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Agua", "rodeando");
		
		assertFalse(app.crearInmueble(2884, "Baleares", caracteristicas));
		app.cerrarSesion(false);
	}
	
	/**
	 * Hacer una transaccion a un cliente.
	 */
	@Test
	public void transaccionAClienteTest() {
		Cliente cliente = new Cliente("Don","X123456", "Prueba", "password", "1234567898765432",
				null, null);
		
		assertTrue(app.transaccionACliente(600.0, cliente));
		
		app.cerrarSesion(false);
	}
	
	/**
	 * Intentar modificar la tarjeta de credito.
	 */
	@Test
	public void modficarTarjetaTest() {
		app.iniciarSesion("55555111Z", "NoSeSaBe");  // como demandante
		
		Cliente cliente = app.clienteConectado();
		
		assertFalse(app.modificarTarjetaCredito(app.clienteConectado(), "777441"));
		
		app.cerrarSesion(false);
		app.iniciarSesion("", "BD911"); 	// como gerente

		assertTrue(app.modificarTarjetaCredito(cliente, "777441"));
		
		app.cerrarSesion(false);
	}
	
	/**
	 * Agregar una nueva oferta.
	 */
	@Test
	public void addOfertaTest() {
		app.iniciarSesion("55555111Z", "NoSeSaBe");  // como demandante
		FechaSimulada.fijarFecha(22, 06, 2155);
		
		assertFalse(app.añadirOfertaVacacional(320.0, 
				FechaSimulada.getHoy(), "El coche volador aparca en la azotea", 
				FechaSimulada.getHoy().plusDays(50), 1));
		
		app.cerrarSesion(false);
		app.iniciarSesion("", "BD911"); 	// como gerente
		
		assertFalse(app.añadirOfertaVacacional(320.0, 
				FechaSimulada.getHoy(), "El coche volador aparca en la azotea", 
				FechaSimulada.getHoy().plusDays(50), 1));
		
		app.cerrarSesion(false);
		app.iniciarSesion("51999111X", "pezEspada"); // como ofertante

		assertTrue(app.añadirOfertaVacacional(320.0, 
				FechaSimulada.getHoy(), "El coche volador aparca en la azotea", 
				FechaSimulada.getHoy().plusDays(50), 1));
		
		assertTrue(app.getOfertas().size() > 0);
		
		app.cerrarSesion(false);
	}
	
	/**
	 * Intentar agregar una rectificacion desde diferentes tipos de usuarios.
	 */
	@Test
	public void addRectificacionTest() {
		app.iniciarSesion("51999111X", "pezEspada"); // como ofertante
		FechaSimulada.fijarFecha(22, 06, 2155);
		
		assertTrue(app.añadirOfertaVacacional(880.0, 
				FechaSimulada.getHoy(), "Buena vista", 
				FechaSimulada.getHoy().plusDays(22), 1));
		
		assertNull(app.getOfertasPendientes());
		
		app.cerrarSesion(false);
		app.iniciarSesion("", "BD911"); 	// como gerente
		
		assertTrue(app.addRectificacion(app.getOfertasPendientes().get(0), 
				new HashMap<String, String>(Map.of("FechaInicio", "Muy avanzada"))));
		
		app.cerrarSesion(false);
	}
	
	/**
	 * Intentar contratar una oferta con diferentes usuarios.
	 */
	@Test
	public void contratarOfertaTest() {
		app.iniciarSesion("51999111X", "pezEspada"); // como ofertante
		FechaSimulada.fijarFecha(22, 06, 2155);
		
		assertFalse(app.contratarOferta(app.getOfertas().get(0)));
		
		app.cerrarSesion(false);
		app.iniciarSesion("55555111Z", "NoSeSaBe");  // como demandante
		
		// False por haberle cambiado la tarjeta anteriormente a un numero inválido
		assertFalse(app.contratarOferta(app.getOfertas().get(0)));
		
		app.cerrarSesion(false);
		app.iniciarSesion("54444111D", "olvidame");
		
		assertTrue(app.contratarOferta(app.getOfertas().get(0)));
		assertFalse(app.contratarOferta(app.getOfertas().get(0)));
		
		app.cerrarSesion(false);
	}
	
	/**
	 * Reservar una oferta.
	 */
	@Test
	public void reservarOfertaTest() {
		app.iniciarSesion("51999111X", "pezEspada"); // como ofertante
		FechaSimulada.fijarFecha(22, 06, 2155);
		
		Map<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Terreno", "accidentado");
		
		assertTrue(app.crearInmueble(2884, "Gibraltar", caracteristicas));
		
		assertTrue(app.añadirOfertaVacacional(120.0, 
				FechaSimulada.getHoy(), "Oferta limitada", 
				FechaSimulada.getHoy().plusDays(10), 1));
		
		assertFalse(app.reservarOferta(app.getOfertas().get(0)));
		
		app.cerrarSesion(false);
		app.iniciarSesion("", "BD911"); 	// como gerente
		
		app.aprobarOferta(app.getOfertas().get(0));
		
		app.cerrarSesion(false);
		app.iniciarSesion("55555111Z", "NoSeSaBe");  // como demandante
		
		assertTrue(app.reservarOferta(app.getOfertas().get(0)));
		
		FechaSimulada.avanzar(10);
		
		assertFalse(app.reservarOferta(app.getOfertas().get(0)));
		
		app.cerrarSesion(false);
	}
	
	/**
	 * Test de busqueda probando diferentes criterios y usuarios.
	 */
	@Test
	public void busquedaOfertaTest() {
		FechaSimulada.fijarFecha(22, 06, 2155);
		
		Busqueda criterios = new BusquedaVacacional(2884, 0.0, 
				FechaSimulada.getHoy().minusDays(2), 
				FechaSimulada.getHoy().plusDays(2), 
				TipoDisponibilidad.DISPONIBLE, FechaSimulada.getHoy().plusDays(10));

		app.iniciarSesion("51999111X", "pezEspada"); // como ofertante
		
		Map<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Terreno", "accidentado");
		
		assertTrue(app.crearInmueble(2884, "Gibraltar", caracteristicas));
		
		assertTrue(app.añadirOfertaVacacional(120.0, 
				FechaSimulada.getHoy(), "Oferta limitada", 
				FechaSimulada.getHoy().plusDays(10), 1));
		
		assertEquals(
				null, app.buscarOfertas(criterios, TipoOrdenar.DISPONIBILIDAD));
		
		app.cerrarSesion(false);
		app.iniciarSesion("", "BD911"); 	// como gerente
		app.aprobarOferta(app.getOfertas().get(0));
		
		app.cerrarSesion(false);
		app.iniciarSesion("55555111Z", "NoSeSaBe");  // como demandante
		
		assertTrue(app.buscarOfertas(criterios, TipoOrdenar.DISPONIBILIDAD).size() > 0);
		
		app.cerrarSesion(false);
	}
	
	/**
	 * Comprobar la rutina de inicio de sesion con parametros del fichero
	 * de usuarios y aleatorios.
	 */
	@Test
	public void iniciarSesionTest() {
		assertFalse(app.iniciarSesion("77896544X", "pezEspada")); // como ofertante
		assertFalse(app.iniciarSesion("", "pezEspada")); // como gerente
		assertFalse(app.iniciarSesion("77896544X", ""));
		
		assertTrue(app.iniciarSesion("", "BD911"));		// como gerente
		
		app.cerrarSesion(false);
		
		assertTrue(app.iniciarSesion("55555111Z", "NoSeSaBe")); // como demandante
		
		app.cerrarSesion(false);
	}
	
	/**
	 * Al cerrar la sesion el sistema deberia guardar todo el estado
	 * actual en un fichero para su posterior recuperacion.
	 */
	@Test
	public void cerrarSesionTest() {
		assertTrue(app.cerrarSesion(true));
		assertTrue(new File("APP.bin").exists());
		
		new File("APP.bin").delete();
	}

}
