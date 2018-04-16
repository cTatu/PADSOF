/*
 * @author David Pascual y Cristian Tatu
 */
package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import busqueda.Busqueda;
import busqueda.BusquedaVacacional;
import busqueda.BusquedaVivienda;
import cliente.Cliente;
import fechasimulada.FechaSimulada;
import oferta.Oferta;
import oferta.OfertaVacacional;
import oferta.OfertaVivienda;
import opinion.Valoracion;
import tipos.TipoDisponibilidad;

/**
 * Test de busqueda
 */
public class BusquedaTest {

	private Busqueda busquedaV;
	private Cliente cliente;
	private Oferta oferta1;
	
	/**
	 * Inicializar.
	 */
	@Before
	public void inicializar() {
		FechaSimulada.fijarFecha(6, 12, 1980);
		busquedaV = new BusquedaVacacional(26650, 3.4, 
				FechaSimulada.getHoy().minusDays(2), FechaSimulada.getHoy().plusDays(5), 
				TipoDisponibilidad.RESERVADO, FechaSimulada.getHoy().plusMonths(3));
		cliente = new Cliente("Don","X123456", "Prueba", "password", "1234567898765432",
				null, null);
		oferta1 = new OfertaVacacional(250.0,FechaSimulada.getHoy(),
				"Descripcion", FechaSimulada.getHoy().plusMonths(3),cliente);
	}
	
	/**
	 * Comprobar oferta vacacional test.
	 */
	@Test
	public void ComprobarOfertaVacacionalTest() {

		oferta1.aniadirOpinion(new Valoracion(cliente.rolDemandante, 5.0));
		
		assertFalse(busquedaV.comprobarOferta(oferta1));
		
		oferta1.setReservada(true);
		
		assertTrue(busquedaV.comprobarOferta(oferta1));
	}
	
	/**
	 * Comprobar oferta vivienda test.
	 */
	@Test
	public void ComprobarOfertaViviendaTest() {
		oferta1 = new OfertaVivienda(3200.0, FechaSimulada.getHoy().plusDays(3), 
				"Oferta Vivienda", 4, cliente, 400.0);
		
		assertFalse(busquedaV.comprobarOferta(oferta1));
		
		busquedaV = new BusquedaVivienda(26650, 0.0, 
				FechaSimulada.getHoy().minusDays(2), FechaSimulada.getHoy().plusDays(5), 
				TipoDisponibilidad.RESERVADO, 4);
		
		oferta1.setReservada(true);
		
		assertTrue(busquedaV.comprobarOferta(oferta1));
	}
	
	/**
	 * Test get codigo postal.
	 */
	@Test
	public void testGetCodigoPostal() {
		assertTrue(busquedaV.getCodigoPostal().equals(26650));
	}
	
	/**
	 * Test get valoracion.
	 */
	@Test
	public void testGetValoracion() {
		assertTrue(busquedaV.getValoracion().equals(3.4));
	}
	
	/**
	 * Test get fecha inicio 1.
	 */
	@Test
	public void testGetFechaInicio1() {
		assertTrue(busquedaV.getFechaInicio1().isEqual(
				LocalDate.of(1980, 12, 4)));
	}
	
	/**
	 * Test get fecha inicio 2.
	 */
	@Test
	public void testGetFechaInicio2() {
		assertTrue(busquedaV.getFechaInicio2().isEqual(
				LocalDate.of(1980, 12, 11)));
	}
	
	/**
	 * Test get tipo disp.
	 */
	@Test
	public void testGetTipoDisp() {
		assertTrue(busquedaV.getTipoDisponibilidad().equals(TipoDisponibilidad.RESERVADO));
	}

}
