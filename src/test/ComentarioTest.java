/**
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cliente.Demandante;
import opinion.Comentario;
import opinion.Opinion;
import opinion.Valoracion;


/**
 * 
 * Test JUnit para comentario
 */
public class ComentarioTest {
	
	Demandante d1, d2, d3;
	Comentario c1;
	Opinion o1, o2, o3;
	List<Opinion> opiniones;
	Comentario comentario;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		d1 = new Demandante();
		c1 = new Comentario(d1, "prueba");
	}    	    	
    

	/**
	 * Test method for {@link Comentario#Comentario(Demandante, java.lang.String)}.
	 */
	@Test
	public void testComentario() {
		assertEquals(c1.getTexto(), "prueba");
	}
	
	/**
	 * Test method for {@link Comentario#opinar(Opinion)}.
	 */
	@Test
	public void testOpinar() {
		d2 = new Demandante();
		d3 = new Demandante();
		o1 = new Valoracion(d2, 4);
		o2 = new Comentario(d3,"texto");
		c1.opinar(o1);
		c1.opinar(o2);
		
		assertEquals(c1.getOpiniones().get(0), o1);
		assertEquals(c1.getOpiniones().get(1), o2);		
	}

	/**
	 * Test method for {@link Comentario#calcularMedia()}.
	 */
	@Test
	public void testCalcularMedia() {
		o1 = new Valoracion(d2, 5);
		o2 = new Valoracion(d2, 4);
		o3 = new Valoracion(d2, 2);
		c1.opinar(o1);
		c1.opinar(o2);
		c1.opinar(o3);

		assertEquals((5+4+2)/3.0, c1.calcularMedia(), .1);
	}
}
