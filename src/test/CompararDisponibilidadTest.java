/*
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import comparator.CompararDisponibilidad;
import fechasimulada.FechaSimulada;
import oferta.Oferta;
import oferta.OfertaVacacional;

// TODO: Auto-generated Javadoc
/**
 * The Class CompararDisponibilidadTest.
 */
public class CompararDisponibilidadTest {
	
	/** The oferta 2. */
	private Oferta oferta1, oferta2;
	
	/** The comp. */
	private CompararDisponibilidad comp;
	
	/**
	 * Inicializar.
	 */
	@Before
	public void inicializar() {
		comp = new CompararDisponibilidad();
		FechaSimulada.fijarFecha(1, 1, 2000);
		Cliente ofertante = new Cliente("Don","X123456", "Prueba", "password", "1234567898765432",
				null, null);
		oferta1 = new OfertaVacacional(250.0,FechaSimulada.getHoy().plusDays(20),"Descripcion", FechaSimulada.getHoy(),ofertante);
		oferta2 = new OfertaVacacional(525.0,FechaSimulada.getHoy().plusDays(15),"Descripcion2", FechaSimulada.getHoy().minusDays(5),ofertante);
	}
	
	/**
	 * Comp disp iguales.
	 */
	@Test
	public void CompDispIguales() {
		assertTrue(comp.compare(oferta1, oferta2) == 0);
	}
	
	/**
	 * Comp disp mayor.
	 */
	@Test
	public void CompDispMayor() {
		oferta1.setReservada(true);
		assertTrue(comp.compare(oferta1, oferta2) > 0);
	}
	
	/**
	 * Comp disp menor.
	 */
	@Test
	public void CompDispMenor() {
		oferta2.setReservada(true);
		assertTrue(comp.compare(oferta1, oferta2) < 0);
	}
}
