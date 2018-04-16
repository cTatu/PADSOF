/*
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import fechasimulada.FechaSimulada;
import inmueble.Inmueble;
import oferta.Oferta;
import oferta.OfertaVacacional;

/**
 * Test para Inmueble.
 */
public class InmuebleTest {
	
	/** The inmueble. */
	private Inmueble inmueble;
	
	/** The cliente. */
	private Cliente cliente;
	
	/** The oferta 1. */
	private Oferta oferta1;
	
	/**
	 * Inicializamos los atributos que usaremos para los tests.
	 */
	@Before
	public void inicializar() {
		Map<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Calor", "49 grados");
		caracteristicas.put("Terraza", "si");
		caracteristicas.put("Mascotas", "no");
		
		inmueble = new Inmueble(15986, 
				"Playa Benidorm", caracteristicas);
		
		cliente = new Cliente("Don","X123456", "Prueba", "password", "1234567898765432",
				null, null);
		
		oferta1 = new OfertaVacacional(250.0,FechaSimulada.getHoy().plusDays(20),
				"Descripcion", FechaSimulada.getHoy(),cliente);
	}
	
	/**
	 * Comprobar si la generacion del ID es la adecuada.
	 */
	@Test
	public void InmuebleIDTest() {
		// ID=2 por que en los tests anteriores se han ido creando otros inmuebles	
		assertEquals(4, inmueble.getID().intValue());
	}
	
	/**
	 * Test para la agregacion de nuevas ofertas a la lista.
	 */
	@Test
	public void addOfertaTest() {
		inmueble.addOfertas(oferta1);
		assertEquals(1, inmueble.getOfertas().size()); 
	}
	
	/**
	 * Test para la agregacion de nuevas ofertas a la lista.
	 */
	@Test
	public void InmuebleAprobarTest() {
		inmueble.addOfertas(oferta1);
		assertTrue(inmueble.aprobarOferta(oferta1));
		assertTrue(oferta1.getAprobada()); 
	}
	
	/**
	 * Test para la agregacion de rectificaciones por parte del gerente.
	 */
	@Test
	public void InmuebleRectificacionTest() {
		inmueble.addOfertas(oferta1);
		
		Map<String,String> rectifiaciones = new HashMap<String, String>(Map.of("Precio", "Muy elevado"));
		
		assertTrue(inmueble.addRectificacion(oferta1, rectifiaciones));
	}
	
	/**
	 * Test para rechazar una oferta en funcion de su estado de aprobacion.
	 */
	@Test
	public void InmuebleRechazarTest() {
		inmueble.addOfertas(oferta1);
		
		assertTrue(inmueble.rechazarOferta(oferta1));
		
		inmueble.aprobarOferta(oferta1);
		
		assertFalse(inmueble.rechazarOferta(oferta1));
	}
	
	/**
	 * Hacer pasar el tiempo para que la oferta aniadida expire y asi borrarla.
	 */
	@Test
	public void InmuebleEliminarExpiradasTest() {
		inmueble.addOfertas(oferta1);

		FechaSimulada.avanzar(300);
		
		assertTrue(inmueble.eliminarOfertasExpiradas() > 0);
		assertTrue(inmueble.getOfertas().size() == 0);
	}

}
