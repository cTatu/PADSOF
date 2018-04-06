/**
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cliente.Cliente;
import cliente.Demandante;

/**
 * Test para Cliente.
 */
public class ClienteTest {

	/**
	 * Testea el cambio de tarjeta
	 * Test method for {@link cliente.Cliente#cambiarTartjeta(java.lang.String)}.
	 */
	@Test
	public void testCambiarTartjeta() {
		Cliente cliente = new Cliente("Pepe", "00011122A", "ap1 ap2", "clave",
				"1111222233334444", null, new Demandante());
		
		assertEquals("1111222233334444", cliente.getTarjetaCredito());
		
		cliente.setBloqueado(true);
		assertTrue(cliente.isBloqueado());
		
		cliente.cambiarTarjeta("5555666677778888");
		assertFalse(cliente.isBloqueado());
		assertEquals("5555666677778888", cliente.getTarjetaCredito());	
	}

	

}
