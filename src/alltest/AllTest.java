package alltest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.BusquedaTest;
import test.ClienteTest;
import test.ComentarioTest;
import test.CompararDisponibilidadTest;
import test.CompararFechaTest;
import test.CompararPrecioTest;
import test.CompararValoracionTest;
import test.DemandanteTest;
import test.FechaSimuladaTest;
import test.OfertaTest;
import test.OfertanteTest;
import test.ReservaTest;
import test.ValoracionTest;


/**
 * La clase AllTests lanza todas las clases de prueba de la aplicacion
 */
@RunWith(Suite.class)
@SuiteClasses({ BusquedaTest.class,
				ClienteTest.class,
				ComentarioTest.class,
				CompararDisponibilidadTest.class,
				CompararFechaTest.class,
				CompararPrecioTest.class,
				CompararValoracionTest.class,	
				DemandanteTest.class,
				FechaSimuladaTest.class,
				OfertanteTest.class,
				OfertaTest.class,
				ReservaTest.class,
				ValoracionTest.class
	})

public class AllTest {

}
