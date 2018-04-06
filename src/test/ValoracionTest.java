/**
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import cliente.Demandante;
import opinion.Comentario;
import opinion.Opinion;
import opinion.Valoracion;

// TODO: Auto-generated Javadoc
/**
 * Test JUnit para valoracion.
 */
public class ValoracionTest {
	
	/** The d 1. */
	Demandante d1 = new Demandante();
	
	/** The v. */
	Valoracion v = new Valoracion(d1,4.0);

	/**
	 * Test method for {@link opinion.Valoracion#opinar(opinion.Opinion)}.
	 */
	@Test
	public void testOpinar() {
		Demandante d2 = new Demandante();
		Opinion o1 = new Comentario(d2,"texto");
		Opinion o2 = new Valoracion(d2,5.0);
		
		assertFalse(v.opinar(o1));
		assertFalse(v.opinar(o2));
	}

	/**
	 * Test method for {@link opinion.Valoracion#getNumero()}.
	 */
	@Test
	public void testGetNumero() {
		assertEquals( 4, v.getNumero(), 0);
	}

}
