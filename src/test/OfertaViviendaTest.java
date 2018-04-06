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
import oferta.OfertaVivienda;
import reserva.Reserva;
import reserva.ReservaVivienda;

// TODO: Auto-generated Javadoc
/**
 * The Class OfertaViviendaTest.
 *
 * @author David
 */
public class OfertaViviendaTest {
	
	/** The c 2. */
	Cliente c1, c2;
	
	/** The ov. */
	OfertaVivienda ov;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		c1 = new Cliente("Juan", "56677888B", "ap_1 ap_2", 
				"contraseña", "4444555566667777", new Ofertante(), null);
		ov = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 20, c1, 1000.0);
		c2 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", null, new Demandante());
	}

	/**
	 * Test method for {@link oferta.OfertaVivienda#calcularComision()}.
	 */
	@Test
	public void testCalcularComision() {
		assertEquals( 900.0*OfertaVivienda.COMISION, ov.calcularComision(), .1);
	}

	/**
	 * Test method for {@link oferta.OfertaVivienda#reservar(reserva.Reserva, cliente.Cliente)}.
	 */
	@Test
	public void testReservar() {
		Reserva r1, r2;
		r1 = new ReservaVivienda(ov);	
		r2 = new ReservaVivienda(ov);	
		
		assertTrue(ov.reservar(r1, c2));
		assertTrue(ov.getReservada());
		assertEquals( c2, ov.getDemandante());
		
		assertFalse(ov.reservar(r2, c2)); 
	}

	/**
	 * Test method for {@link oferta.OfertaVivienda#cancelarReserva()}.
	 */
	@Test
	public void testCancelarReserva() {
		Reserva r1 = new ReservaVivienda(ov);
		
		ov.reservar(r1, c2);		
		assertTrue(ov.cancelarReserva());
		assertNull(ov.getDemandante());
		assertNull(ov.getReserva());
		assertFalse(ov.getReservada());
		
		assertFalse(ov.cancelarReserva());
	}

}
