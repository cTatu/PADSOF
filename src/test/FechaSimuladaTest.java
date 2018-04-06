package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import fechasimulada.FechaSimulada;

public class FechaSimuladaTest {
	
	@Before
	public void inicializar() {
		FechaSimulada.fijarFecha(11, 9, 2011);
	}
	
	@Test
	public void testSet() {
		assertTrue(FechaSimulada.getHoy().isEqual(LocalDate.of(2011, 9, 11)));
	}

	@Test
	public void avanzarDia() {
		FechaSimulada.avanzar(5);
		
		assertTrue(FechaSimulada.getHoy().isEqual(LocalDate.of(2011, 9, 11).plusDays(5)));
	}
	
	@Test
	public void retrocederDia() {
		FechaSimulada.avanzar(-10);
		
		assertTrue(FechaSimulada.getHoy().isEqual(LocalDate.of(2011, 9, 11).minusDays(10)));
	}
	
	@Test
	public void reset() {
		FechaSimulada.restablecerHoyReal();
		
		assertTrue(FechaSimulada.getHoy().isEqual(LocalDate.now()));
	}

}
