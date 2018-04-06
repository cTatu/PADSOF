/**
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import cliente.Demandante;
import cliente.Ofertante;
import fechasimulada.FechaSimulada;
import oferta.Oferta;
import oferta.OfertaVacacional;
import reserva.Reserva;
import reserva.ReservaVacacional;

/**
 * Test para Reserva
 */
public class ReservaTest {
	
	Reserva r1, r2;
	Oferta o1, o2;
	Cliente c1, c2;
	Demandante d1;
	Ofertante ofertante1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Comprobamos que la comprobacion de si una fecha esta expirada funcione bien con 3 fechas distintas
	 * Test method for {@link reserva.Reserva#expirada()}.
	 */
	@Test
	public void testExpirada() {
		ofertante1 = new Ofertante();
		c1 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante1, null);
		FechaSimulada.fijarFecha(1, 06, 2015);
		o1 = new OfertaVacacional(800.0, FechaSimulada.getHoy(), "descripcion", FechaSimulada.getHoy().plusDays(15), c1);
		
		FechaSimulada.fijarFecha(1, 04, 2015);
		r1 = new ReservaVacacional(o1);
		assertFalse(r1.expirada());
		
		FechaSimulada.avanzar(5);
		assertFalse(r1.expirada());
		
		FechaSimulada.avanzar(1);
		assertTrue(r1.expirada());		
	}

	/**
	 * Test method for {@link reserva.Reserva#compareTo(reserva.Reserva)}.
	 */
	@Test
	public void testCompareTo() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link reserva.Reserva#contratar(cliente.Cliente)}.
	 */
	@Test
	public void testContratar() {
		fail("Not yet implemented"); // TODO
	}
}
