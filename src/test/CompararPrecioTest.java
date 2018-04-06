package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import comparator.CompararPrecio;
import fechasimulada.FechaSimulada;
import oferta.Oferta;
import oferta.OfertaVacacional;

public class CompararPrecioTest {
	private Oferta oferta1, oferta2;
	private CompararPrecio comp;
	
	@Before
	public void inicializar() {
		comp = new CompararPrecio();
		FechaSimulada.fijarFecha(1, 1, 2000);
		Cliente ofertante = new Cliente("Don","X123456", "Prueba", "password", "1234567898765432",
				null, null);
		oferta1 = new OfertaVacacional(250.0,FechaSimulada.getHoy().plusDays(20),"Descripcion", FechaSimulada.getHoy(),ofertante);
		oferta2 = new OfertaVacacional(525.0,FechaSimulada.getHoy().plusDays(15),"Descripcion2", FechaSimulada.getHoy().minusDays(5),ofertante);
	}
	
	@Test
	public void CompPrecioMayor() {
		assertTrue(comp.compare(oferta1, oferta2) > 0);
	}
	
	@Test
	public void CompPrecioMenor() {
		assertTrue(comp.compare(oferta2, oferta1) < 0);
	}
	
	@Test
	public void CompPrecioIguales() {
		assertFalse(comp.compare(oferta1, oferta2) == 0);
		
		oferta2.setPrecio(250.0);
		
		assertTrue(comp.compare(oferta1, oferta2) == 0);
	}
}
