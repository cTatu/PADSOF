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

public class CompararValoracionTest {
	private Oferta oferta1, oferta2;
	private CompararValoracion comp;
	private Cliente cliente;
	
	@Before
	public void inicializar() {
		comp = new CompararValoracion();
		FechaSimulada.fijarFecha(1, 1, 2000);
		cliente = new Cliente("Don","X123456", "Prueba", "password", "1234567898765432",
				null, null);
		oferta1 = new OfertaVacacional(250.0,FechaSimulada.getHoy().plusDays(20),"Descripcion", FechaSimulada.getHoy(),cliente);
		oferta2 = new OfertaVacacional(525.0,FechaSimulada.getHoy().plusDays(15),"Descripcion2", FechaSimulada.getHoy().minusDays(5),cliente);
	
		oferta1.añadirOpinion(new Valoracion(cliente.rolDemandante, 3.2));
		oferta1.añadirOpinion(new Valoracion(cliente.rolDemandante, 4.5));
	}
	
	@Test
	public void CompValoracionIguales() {
		assertFalse(comp.compare(oferta1, oferta2) == 0);
	}
	
	@Test
	public void CompValoracionO1Mayor() {
		assertTrue(comp.compare(oferta1, oferta2) < 0);
	}

	@Test
	public void CompValoracionO2Mayor() {
		oferta2.añadirOpinion(new Valoracion(cliente.rolDemandante, 4.8));
		oferta2.añadirOpinion(new Valoracion(cliente.rolDemandante, 5.0));
		
		assertTrue(comp.compare(oferta1, oferta2) > 0);
	}
}
