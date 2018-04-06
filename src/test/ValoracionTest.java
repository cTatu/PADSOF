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

/**
 * Test JUnit para valoracion.
 */
public class ValoracionTest {
	
	Demandante d1 = new Demandante();
	Valoracion v = new Valoracion(d1,4.0);

	/**
	 * Test para comprobar que se valora corretamente
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
	 * Test getter
	 * Test method for {@link opinion.Valoracion#getNumero()}.
	 */
	@Test
	public void testGetNumero() {
		assertEquals( 4, v.getNumero(), 0);
	}

}
