import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class InmaculadApp {
	
	List<Usuario> usuarios; 
	
	public void cargarUsuario(String filename) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			br.readLine();
			String sCurrentLine = null;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine.split(";"));
			}
		} catch (IOException  e) {
			
			e.printStackTrace();
			
		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
	}
	
	
	public static void main(String... args) {
		InmaculadApp iaApp = new InmaculadApp();
		
		iaApp.cargarUsuario("Recursos\\ClientesEjemplo.txt");
	}
}









