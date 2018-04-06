/*
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import fechasimulada.FechaSimulada;

/**
 * Test FechaSimulada.
 */
public class FechaSimuladaTest {
	
	/**
	 * Inicializar.
	 */
	@Before
	public void inicializar() {
		FechaSimulada.fijarFecha(11, 9, 2011);
	}
	
	/**
	 * Test set.
	 */
	@Test
	public void testSet() {
		assertTrue(FechaSimulada.getHoy().isEqual(LocalDate.of(2011, 9, 11)));
	}

	/**
	 * Avanzar dia.
	 */
	@Test
	public void avanzarDia() {
		FechaSimulada.avanzar(5);
		
		assertTrue(FechaSimulada.getHoy().isEqual(LocalDate.of(2011, 9, 11).plusDays(5)));
	}
	
	/**
	 * Retroceder dia.
	 */
	@Test
	public void retrocederDia() {
		FechaSimulada.avanzar(-10);
		
		assertTrue(FechaSimulada.getHoy().isEqual(LocalDate.of(2011, 9, 11).minusDays(10)));
	}
	
	/**
	 * Reset.
	 */
	@Test
	public void reset() {
		FechaSimulada.restablecerHoyReal();
		
		assertTrue(FechaSimulada.getHoy().isEqual(LocalDate.now()));
	}

}
