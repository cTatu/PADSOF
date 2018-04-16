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
import cliente.Ofertante;
import oferta.Oferta;
import oferta.OfertaVacacional;
import oferta.OfertaVivienda;

/**
 * Test para Ofertante.
 */
public class OfertanteTest {
	
	Ofertante ofertante = new Ofertante();
	Cliente cliente = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", ofertante, null);
	Oferta o1, o2, o3;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Comprueba que no se aniadan ofertas duplicadas y que se aniadan correctamente
	 * Test method for {@link cliente.Ofertante#aniadirOfertas(oferta.OfertaVivienda)}.
	 */
	@Test
	public void testAniadirOfertas() {
		o1 = o2 = new OfertaVacacional( 500.0,  LocalDate.of(2018,6,1), "descripcion", LocalDate.of(2018,6,15), cliente);
		o3 = new OfertaVivienda( 500.0, LocalDate.of(2018,10,1), "descripcion 2", 20, cliente, 1000.0); 
		
		ofertante.aniadirOfertas(o1);
		ofertante.aniadirOfertas(o3);
		ofertante.aniadirOfertas(o2);
		
		assertEquals( 2, ofertante.getOfertas().size());
		assertEquals( o1, ofertante.getOfertas().get(0));
		assertEquals( o3, ofertante.getOfertas().get(1));
	}

	/**
	 * Comprueba salida correcta, que se ha cancelado correctamente, y tamanio de la lista
	 * Test method for {@link cliente.Ofertante#cancelarOferta(java.time.LocalDate)}.
	 */
	@Test
	public void testCancelarOferta() {
		o1 = new OfertaVacacional( 500.0,  LocalDate.of(2018,6,1), "descripcion", LocalDate.of(2018,6,15), cliente);
		o2 = new OfertaVivienda( 500.0, LocalDate.of(2018,10,1), "descripcion 2", 20, cliente, 1000.0); 
		
		ofertante.aniadirOfertas(o1);
		ofertante.aniadirOfertas(o2);
		
		assertTrue(ofertante.cancelarOferta(LocalDate.of(2018,6,1)));
		assertFalse(ofertante.getOfertas().contains(o1));
		assertEquals( 1, ofertante.getOfertas().size());
	}

}
