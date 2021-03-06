/*
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import comparator.CompararFecha;
import fechasimulada.FechaSimulada;
import oferta.Oferta;
import oferta.OfertaVacacional;

/**
 * Test para CompararFecha.
 */
public class CompararFechaTest {
	
	private Oferta oferta1, oferta2;
	private CompararFecha comp;
	
	/**
	 * Inicializar.
	 */
	@Before
	public void inicializar() {
		comp = new CompararFecha();
		FechaSimulada.fijarFecha(1, 1, 2000);
		Cliente ofertante = new Cliente("Don","X123456", "Prueba", "password", "1234567898765432",
				null, null);
		oferta1 = new OfertaVacacional(250.0,FechaSimulada.getHoy().plusDays(20),"Descripcion", FechaSimulada.getHoy(),ofertante);
		oferta2 = new OfertaVacacional(525.0,FechaSimulada.getHoy().plusDays(15),"Descripcion2", FechaSimulada.getHoy().minusDays(5),ofertante);
	}
	
	/**
	 * Comp fecha iguales.
	 */
	@Test
	public void CompFechaIguales() {
		assertFalse(comp.compare(oferta1, oferta2) == 0);
	}
	
	/**
	 * Comp fecha mayor.
	 */
	@Test
	public void CompFechaMayor() {
		assertTrue(comp.compare(oferta1, oferta2) > 0);
	}
	
	/**
	 * Comp fecha menor.
	 */
	@Test
	public void CompFechaMenor() {
		assertTrue(comp.compare(oferta2, oferta1) < 0);
	}
}
