/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

/**
 * @author David
 *
 */
public class DemandanteTest {
	
	Demandante demandante;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		demandante = new Demandante();
	}

	/**
	 * Test method for {@link cliente.Demandante#añadirOfertaContratada(oferta.Oferta)}.
	 */
	@Test
	public void testAñadirOfertaContratada() {
		Oferta o1, o2, o3;
		
		Ofertante ofertante1 = new Ofertante();
		Cliente c1 = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante1, null);
		o1 = o3 = new OfertaVacacional( 900.0, LocalDate.of(2018,8,1), "descripcion", LocalDate.of(2018,8,15), c1);
		
		Ofertante ofertante2 = new Ofertante();
		Cliente c2 = new Cliente("Juan", "56677888B", "ap_1 ap_2", 
				"contraseña", "4444555566667777", ofertante2, null);
		o2 = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 16, c2, 600.0);
		
		demandante.añadirOfertaContratada(o1);
		demandante.añadirOfertaContratada(o2);
		demandante.añadirOfertaContratada(o3);
		
		assertEquals( 2, demandante.getOfertasContratadas().size()); // No se agrega el duplicado
		assertEquals( o1, demandante.getOfertasContratadas().get(0));
		assertEquals( o2, demandante.getOfertasContratadas().get(1));
	}

	
	/**
	 * Test method for {@link cliente.Demandante#añadirReserva(reserva.Reserva)}.
	 */
	@Test
	public void testAñadirReserva() {
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
				"contraseña", "4444555566667777", ofertante2, null);
		o2 = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 16, c2, 600.0);
		r2 = new ReservaVivienda(o2);
		
		demandante.añadirReserva(r1);
		demandante.añadirReserva(r2);
		demandante.añadirReserva(r3);
		
		assertEquals( 2, demandante.getHistorialReservas().size()); // No se agrega el duplicado
		assertEquals( r1, demandante.getHistorialReservas().get(0));
		assertEquals( r2, demandante.getHistorialReservas().get(1));
	}
	
	/**
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
				"contraseña", "4444555566667777", ofertante2, null);
		o2 = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 16, c2, 600.0);
		r2 = new ReservaVivienda(o2);
		
		Ofertante ofertante3 = new Ofertante();
		Cliente c3 = new Cliente("Pedro", "87777888B", "ap_1_ ap_2_", 
				"contraseñaa", "4444444466667777", ofertante2, null);
		o4 = new OfertaVivienda( 700.0, LocalDate.of(2018,11,8), "texto", 20, c3, 850.0);
		r4 = new ReservaVivienda(o4);

		demandante.añadirReserva(r1);
		demandante.añadirReserva(r2);
		
		assertFalse(demandante.puedeReservar(r3));
		
		demandante.eliminarReserva(TipoOferta.VIVIENDA);
		assertTrue(demandante.puedeReservar(r4));
	}

	/**
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
				"contraseña", "4444555566667777", ofertante2, null);
		o2 = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 16, c2, 600.0);
		r2 = new ReservaVivienda(o2);
		
		demandante.añadirReserva(r1);
		demandante.añadirReserva(r2);
		
		assertNotNull(demandante.getrVacacional());
		assertNotNull(demandante.getrVivienda());
	}

	/**
	 * Test method for {@link cliente.Demandante#getStatusVacacional()}.
	 */
	@Test
	public void testGetStatusVacacional() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link cliente.Demandante#puedeContratar(oferta.Oferta)}.
	 */
	@Test
	public void testPuedeContratar() {
		fail("Not yet implemented");
	}

}
