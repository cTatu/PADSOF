/**
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
 * Test para Reserva.
 */
public class ReservaTest {
	
	Reserva r1, r2;
	Oferta o1, o2;
	Cliente c1, c2;
	Demandante d1;
	Ofertante ofertante;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
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
		ofertante = new Ofertante();
		c1 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante, null);
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
	 * Comprobamos el correcto funcionamiento del override del equals implementado
	 * Test method for {@link reserva.Reserva#compareTo(reserva.Reserva)}.
	 */
	@Test
	public void testEquals() {
		ofertante = new Ofertante();		
		c1 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante, null);
		
		FechaSimulada.fijarFecha(1, 06, 2015);
		o1 = new OfertaVacacional(800.0, FechaSimulada.getHoy(), "descripcion", FechaSimulada.getHoy().plusDays(15), c1);
		FechaSimulada.fijarFecha(1, 03, 2015);
		r1 = new ReservaVacacional(o1);
		
		FechaSimulada.fijarFecha(1, 07, 2015);
		o2 = new OfertaVacacional(800.0, FechaSimulada.getHoy(), "descripcion", FechaSimulada.getHoy().plusDays(10), c2);
		FechaSimulada.fijarFecha(1, 03, 2015); // Ambas reservas en la misma fecha
		r2 = new ReservaVacacional(o2);
		
		assertFalse(r1.equals(r2));
		
		FechaSimulada.fijarFecha(1, 04, 2015); // Reserva r2 despues que la r1
		((Reserva) r2).setFechaReserva(FechaSimulada.getHoy());
		
		assertFalse(r1.equals(r2));
		
		FechaSimulada.fijarFecha(1, 01, 2015); // Reserva r2 antes que la r1
		((Reserva) r2).setFechaReserva(FechaSimulada.getHoy());
		
		assertFalse(r1.equals(r2));
	}

	/**
	 * Con la ayuda de fecha simulada comprobamos que se contrata correctamente
	 * Test method for {@link reserva.Reserva#contratar(cliente.Cliente)}.
	 */
	@Test
	public void testContratar() {
		ofertante = new Ofertante();		
		d1 = new Demandante();
		c1 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante, d1);
		
		FechaSimulada.fijarFecha(1, 06, 2015);
		o1 = new OfertaVacacional(800.0, FechaSimulada.getHoy(), "descripcion", FechaSimulada.getHoy().plusDays(15), c1);
		FechaSimulada.fijarFecha(1, 03, 2015);
		r1 = new ReservaVacacional(o1);
		
		assertTrue(r1.contratar(c1));
	}
}
