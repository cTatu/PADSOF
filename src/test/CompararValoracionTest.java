/*
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import comparator.CompararValoracion;
import fechasimulada.FechaSimulada;
import oferta.Oferta;
import oferta.OfertaVacacional;
import opinion.Valoracion;

// TODO: Auto-generated Javadoc
/**
 * The Class CompararValoracionTest.
 */
public class CompararValoracionTest {
	
	/** The oferta 2. */
	private Oferta oferta1, oferta2;
	
	/** The comp. */
	private CompararValoracion comp;
	
	/** The cliente. */
	private Cliente cliente;
	
	/**
	 * Inicializar.
	 */
	@Before
	public void inicializar() {
		comp = new CompararValoracion();
		FechaSimulada.fijarFecha(1, 1, 2000);
		cliente = new Cliente("Don","X123456", "Prueba", "password", "1234567898765432",
				null, null);
		oferta1 = new OfertaVacacional(250.0,FechaSimulada.getHoy().plusDays(20),"Descripcion", FechaSimulada.getHoy(),cliente);
		oferta2 = new OfertaVacacional(525.0,FechaSimulada.getHoy().plusDays(15),"Descripcion2", FechaSimulada.getHoy().minusDays(5),cliente);
	
		oferta1.a�adirOpinion(new Valoracion(cliente.rolDemandante, 3.2));
		oferta1.a�adirOpinion(new Valoracion(cliente.rolDemandante, 4.5));
	}
	
	/**
	 * Comp valoracion iguales.
	 */
	@Test
	public void CompValoracionIguales() {
		assertFalse(comp.compare(oferta1, oferta2) == 0);
	}
	
	/**
	 * Comp valoracion O 1 mayor.
	 */
	@Test
	public void CompValoracionO1Mayor() {
		assertTrue(comp.compare(oferta1, oferta2) < 0);
	}

	/**
	 * Comp valoracion O 2 mayor.
	 */
	@Test
	public void CompValoracionO2Mayor() {
		oferta2.a�adirOpinion(new Valoracion(cliente.rolDemandante, 4.8));
		oferta2.a�adirOpinion(new Valoracion(cliente.rolDemandante, 5.0));
		
		assertTrue(comp.compare(oferta1, oferta2) > 0);
	}
}
