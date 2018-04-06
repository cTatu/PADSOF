package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import comparator.CompararDisponibilidad;
import fechasimulada.FechaSimulada;
import oferta.Oferta;
import oferta.OfertaVacacional;

public class CompararDisponibilidadTest {
	private Oferta oferta1, oferta2;
	
	@Before
	public void inicializar() {
		FechaSimulada.fijarFecha(1, 1, 2000);
		Cliente ofertante = new Cliente("Don","X123456", "Prueba", "password", "1234567898765432",
				null, null);
		Oferta oferta1 = new OfertaVacacional(250.0,FechaSimulada.getHoy().plusDays(20),"Descripcion", FechaSimulada.getHoy(),ofertante);
		Oferta oferta2 = new OfertaVacacional(525.0,FechaSimulada.getHoy().plusDays(15),"Descripcion2", FechaSimulada.getHoy().minusDays(5),ofertante);
	}
	
	@Test
	public void comparar() {
		CompararDisponibilidad comp = new CompararDisponibilidad();
		assertTrue(comp.compare(oferta1, oferta2) > 1);
	}
}
