/**
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import cliente.Demandante;
import cliente.Ofertante;
import oferta.Oferta;
import oferta.OfertaVacacional;
import oferta.OfertaVivienda;
import reserva.Reserva;
import reserva.ReservaVacacional;
import reserva.ReservaVivienda;
import tipos.TipoOferta;

// TODO: Auto-generated Javadoc
/**
 * Test para Demandante.
 */
public class DemandanteTest {
	
	/** The demandante. */
	Demandante demandante;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		demandante = new Demandante();
	}

	/**
	 * Comprobamos que se a�adan correctamente las ofertas contratadas y que no se agreguen duplicados
	 * Test method for {@link cliente.Demandante#a�adirOfertaContratada(oferta.Oferta)}.
	 */
	@Test
	public void testA�adirOfertaContratada() {
		Oferta o1, o2, o3;
		
		Ofertante ofertante1 = new Ofertante();
		Cliente c1 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante1, null);
		o1 = o3 = new OfertaVacacional( 900.0, LocalDate.of(2018,8,1), "descripcion", LocalDate.of(2018,8,15), c1);
		
		Ofertante ofertante2 = new Ofertante();
		Cliente c2 = new Cliente("Juan", "56677888B", "ap_1 ap_2", 
				"contrase�a", "4444555566667777", ofertante2, null);
		o2 = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 16, c2, 600.0);
		
		demandante.a�adirOfertaContratada(o1);
		demandante.a�adirOfertaContratada(o2);
		demandante.a�adirOfertaContratada(o3);
		
		assertEquals( 2, demandante.getOfertasContratadas().size()); // No se agrega el duplicado
		assertEquals( o1, demandante.getOfertasContratadas().get(0));
		assertEquals( o2, demandante.getOfertasContratadas().get(1));
	}

	
	/**
	 * Comprobamos que se a�adan correctamente las ofertas reservadas y que no se agreguen duplicados
	 * Test method for {@link cliente.Demandante#a�adirReserva(reserva.Reserva)}.
	 */
	@Test
	public void testA�adirReserva() {
		Oferta o1, o2, o3;
		Reserva r1, r2, r3;
		
		Ofertante ofertante1 = new Ofertante();
		Cliente c1 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante1, null);
		o1 =  o3 = new OfertaVacacional( 900.0, LocalDate.of(2018,8,1), "descripcion", LocalDate.of(2018,8,15), c1);
		r1 = new ReservaVacacional(o1);
		r3 = new ReservaVacacional(o3);
		
		Ofertante ofertante2 = new Ofertante();
		Cliente c2 = new Cliente("Juan", "56677888B", "ap_1 ap_2", 
				"contrase�a", "4444555566667777", ofertante2, null);
		o2 = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 16, c2, 600.0);
		r2 = new ReservaVivienda(o2);
		
		demandante.a�adirReserva(r1);
		demandante.a�adirReserva(r2);
		demandante.a�adirReserva(r3);
		
		assertEquals( 2, demandante.getHistorialReservas().size()); // No se agrega el duplicado
		assertEquals( r1, demandante.getHistorialReservas().get(0));
		assertEquals( r2, demandante.getHistorialReservas().get(1));
	}
	
	/**
	 * Comprobamos que una oferta que ya fue reservada no se puede volver a reservar,
	 * y una oferta que nunca se ha reservado se pueda hacer
	 * Test method for {@link cliente.Demandante#puedeReservar(reserva.Reserva)}.
	 */
	@Test
	public void testPuedeReservar() {
		Oferta o1, o2, o3, o4;
		Reserva r1, r2, r3, r4;
		
		Ofertante ofertante1 = new Ofertante();
		Cliente c1 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante1, null);
		o1 =  o3 = new OfertaVacacional( 900.0, LocalDate.of(2018,8,1), "descripcion", LocalDate.of(2018,8,15), c1);
		r1 = new ReservaVacacional(o1);
		r3 = new ReservaVacacional(o3);
		
		Ofertante ofertante2 = new Ofertante();
		Cliente c2 = new Cliente("Juan", "56677888B", "ap_1 ap_2", 
				"contrase�a", "4444555566667777", ofertante2, null);
		o2 = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 16, c2, 600.0);
		r2 = new ReservaVivienda(o2);
		
		Ofertante ofertante3 = new Ofertante();
		Cliente c3 = new Cliente("Pedro", "87777888B", "ap_1_ ap_2_", 
				"contrase�aa", "4444444466667777", ofertante3, null);
		o4 = new OfertaVivienda( 700.0, LocalDate.of(2018,11,8), "texto", 20, c3, 850.0);
		r4 = new ReservaVivienda(o4);

		demandante.a�adirReserva(r1);
		demandante.a�adirReserva(r2);
		
		assertFalse(demandante.puedeReservar(r3));
		
		demandante.eliminarReserva(TipoOferta.VIVIENDA);
		assertTrue(demandante.puedeReservar(r4));
	}

	/**
	 * Comprobamos que despues de tener reservas estas se eliminan correctamente
	 * Test method for {@link cliente.Demandante#eliminarReserva(tipos.TipoOferta)}.
	 */
	@Test
	public void testEliminarReserva() {
		Oferta o1, o2;
		Reserva r1, r2;
		
		Ofertante ofertante1 = new Ofertante();
		Cliente c1 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante1, null);
		o1 = new OfertaVacacional( 900.0, LocalDate.of(2018,8,1), "descripcion", LocalDate.of(2018,8,15), c1);
		r1 = new ReservaVacacional(o1);
		
		Ofertante ofertante2 = new Ofertante();
		Cliente c2 = new Cliente("Juan", "56677888B", "ap_1 ap_2", 
				"contrase�a", "4444555566667777", ofertante2, null);
		o2 = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 16, c2, 600.0);
		r2 = new ReservaVivienda(o2);
		
		demandante.a�adirReserva(r1);
		demandante.a�adirReserva(r2);
		
		assertTrue(demandante.getStatusVacacional());
		assertTrue(demandante.getStatusVivienda());
		
		demandante.eliminarReserva(TipoOferta.VIVIENDA);
		demandante.eliminarReserva(TipoOferta.VACACIONAL);
		
		assertFalse(demandante. getStatusVacacional());
		assertFalse(demandante.getStatusVivienda());
	}

	/**
	 * Comprobamos que una oferta que ya fue contratada no se puede volver a contratar,
	 * y una oferta que nunca se ha contratado se pueda hacer
	 * Test method for {@link cliente.Demandante#puedeContratar(oferta.Oferta)}.
	 */
	@Test
	public void testPuedeContratar() {
		Oferta o1, o2, o3, o4;
		
		Ofertante ofertante1 = new Ofertante();
		Cliente c1 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante1, null);
		o1 = o3 = new OfertaVacacional( 900.0, LocalDate.of(2018,8,1), "descripcion", LocalDate.of(2018,8,15), c1);
		
		Ofertante ofertante2 = new Ofertante();
		Cliente c2 = new Cliente("Juan", "56677888B", "ap_1 ap_2", 
				"contrase�a", "4444555566667777", ofertante2, null);
		o2 = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 16, c2, 600.0);
		
		Ofertante ofertante3 = new Ofertante();
		Cliente c3 = new Cliente("Pedro", "87777888B", "ap_1_ ap_2_", 
				"contrase�aa", "4444444466667777", ofertante3, null);
		o4 = new OfertaVivienda( 700.0, LocalDate.of(2018,11,8), "texto", 20, c3, 850.0);
		
		demandante.a�adirOfertaContratada(o1);
		demandante.a�adirOfertaContratada(o2);
		
		assertFalse(demandante.puedeContratar(o3));
		assertTrue(demandante.puedeContratar(o4));		
	}

}
