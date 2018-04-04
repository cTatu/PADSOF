/**
 *
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.fail;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import cliente.Demandante;
import opinion.*;


/**
 * 
 *
 */
public class ComentarioTest {
	
	Demandante demandate = new Demandante();
	Opinion o1, o2, o3, o4;
	List<Opinion> opiniones;
	Comentario comentario;
	
	/**
     * Se ejecuta antes de cada test unitario
     */
	@Before
	public void setUp() throws Exception {
		
	}    	    	
        
	/**
	 * Test method for {@link Comentario#opinar(Opinion)}.
	 */
	@Test
	public void testOpinar() {
		o1.opinar(o2, o3);
	}

	/**
	 * Test method for {@link Comentario#Comentario(Demandante, java.lang.String)}.
	 */
	@Test
	public void testComentario() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Comentario#calcularMedia()}.
	 */
	@Test
	public void testCalcularMedia() {
		fail("Not yet implemented");
	}

}
