/*
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import cliente.Demandante;
import cliente.Ofertante;
import fechasimulada.FechaSimulada;
import oferta.OfertaVacacional;
import reserva.Reserva;
import reserva.ReservaVacacional;

/**
 * The Class OfertaVacacionalTest.
 *
 * @author David
 */
public class OfertaVacacionalTest {

	Cliente c1, c2;
	OfertaVacacional ov;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		c1 = new Cliente("Juan", "56677888B", "ap_1 ap_2", 
				"contraseña", "4444555566667777", new Ofertante(), null);
		ov = new OfertaVacacional( 900.0, LocalDate.of(2018,9,1), "descripcion", LocalDate.of(2018,9,10), c1);
		c2 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", null, new Demandante());
	}

	/**
	 * Comprueba que se calcule bien la comision
	 * Test method for {@link oferta.OfertaVacacional#calcularComision()}.
	 */
	@Test
	public void testCalcularComision() {
		assertEquals( 900.0*OfertaVacacional.COMISION, ov.calcularComision(), .1);
	}

	/**
	 * Test para asegurar que expirar funciona bien
	 * Test method for {@link oferta.OfertaVacacional#expirar()}.
	 */
	@Test
	public void testExpirar() {
		FechaSimulada.fijarFecha( 1, 7, 2018); // Antes del comienzo de la oferta
		assertFalse(ov.expirar());
		
		FechaSimulada.fijarFecha( 3, 9, 2018); // Durante la oferta
		assertFalse(ov.expirar());
		
		FechaSimulada.fijarFecha( 10, 9, 2018); // Ultimo dia de la oferta
		assertFalse(ov.expirar());
		
		FechaSimulada.fijarFecha( 1, 10, 2018); // Despues de la oferta
		assertTrue(ov.expirar());
	}

	/**
	 * Test que comprueba que se reserve bien y que no se pueda reservar 2 veces
	 * Test method for {@link oferta.OfertaVacacional#reservar(reserva.Reserva, cliente.Cliente)}.
	 */
	@Test
	public void testReservar() {
		Reserva r1, r2;
		r1 = new ReservaVacacional(ov);	
		r2 = new ReservaVacacional(ov);	
		
		assertTrue(ov.reservar(r1, c2));
		assertTrue(ov.getReservada());
		assertEquals( c2, ov.getDemandante());
		
		assertFalse(ov.reservar(r2, c2)); 
	}

	/**
	 * Test para asegurar el funcionamiento de cancelarReserva y que no se pueda cancelar una oferta no reservada
	 * Test method for {@link oferta.OfertaVacacional#cancelarReserva()}.
	 */
	@Test
	public void testCancelarReserva() {
		Reserva r1 = new ReservaVacacional(ov);
		
		ov.reservar(r1, c2);		
		assertTrue(ov.cancelarReserva());
		assertNull(ov.getDemandante());
		assertNull(ov.getReserva());
		assertFalse(ov.getReservada());
		
		assertFalse(ov.cancelarReserva());
	}

}
