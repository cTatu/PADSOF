/*
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import cliente.Demandante;
import cliente.Ofertante;
import oferta.Oferta;
import oferta.OfertaVivienda;
import opinion.Comentario;
import opinion.Opinion;
import opinion.Valoracion;

/**
 * Test para Oferta.
 */
public class OfertaTest {
	
	Ofertante ofertante;
	Cliente c;
	Oferta oferta;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		ofertante = new Ofertante();
		c = new Cliente("Juan", "56677888B", "ap_1 ap_2", 
				"contraseña", "4444555566667777", ofertante, null);
		oferta = new OfertaVivienda( 900.0, LocalDate.of(2018,9,1), "descripcion", 16, c, 600.0);
	}

	/**
	 * Test method for {@link oferta.Oferta#añadirRectificacion(java.util.Map)}.
	 */
	@Test
	public void testAñadirRectificacion() {
		assertTrue(oferta.añadirRectificacion(
				new HashMap<String, String>(Map.of("Luz", "Muy poca dentro de las habitaciones"))
					));
	}

	/**
	 * Comprueba que se modifican los campos correctamente y que la contratación se agregó en la clase demandante
	 * Test method for {@link oferta.Oferta#contratar(cliente.Cliente)}.
	 */
	@Test
	public void testContratar() {		
		Demandante d = new Demandante();
		Cliente demandante = new Cliente("Pepe", "12233444A", "ap1 ap2", 
				"clave", "0000111122223333", null, d);
		
		assertFalse(oferta.getContratada());
		assertNull(oferta.getDemandante());
		
		oferta.contratar(demandante);
		
		assertTrue(oferta.getContratada());
		assertNotNull(oferta.getDemandante());
		assertEquals( oferta, demandante.getRolDemandante().getOfertasContratadas().get(0));
	}

	/**
	 * Comprueba que se añaden las opiniones a la lista correctamente
	 * Test method for {@link oferta.Oferta#añadirOpinion(opinion.Opinion)}.
	 */
	@Test
	public void testAñadirOpinion() {		
		Demandante d1 = new Demandante();
		Opinion comentario = new Comentario( d1, "Comentario");
		Demandante d2 = new Demandante();
		Opinion valoracion = new Valoracion( d2, 5.0);
		
		oferta.añadirOpinion(comentario);
		oferta.añadirOpinion(valoracion);
		
		assertEquals( comentario, oferta.getOpiniones().get(0));
		assertEquals( valoracion, oferta.getOpiniones().get(1));
	}

	/**
	 * Comprueba que se hace la media de las valoraciones correctamente
	 * Test method for {@link oferta.Oferta#calcularMedia()}.
	 */
	@Test
	public void testCalcularMedia() {
		Opinion o1 = new Valoracion(new Demandante(), 5.0);
		Opinion o2 = new Valoracion(new Demandante(), 4.0);
		Opinion o3 = new Valoracion(new Demandante(), 2.0);
		Opinion o4 = new Comentario(new Demandante(), "comentario"); // Sirve para comprobar que distingue las valoraciones de los comentarios para hacer la media
		
		oferta.añadirOpinion(o1);
		oferta.añadirOpinion(o2);
		oferta.añadirOpinion(o3);
		oferta.añadirOpinion(o4);

		assertEquals((5+4+2)/3.0, oferta.calcularMedia(), .1);
	}

}
