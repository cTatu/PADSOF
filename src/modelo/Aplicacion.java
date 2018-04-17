package modelo;

public class Aplicacion {
	
	public boolean par(Integer num) {
		return num % 2 == 0;
	}
	
	public boolean pal(String str) {
		String revStr = new StringBuilder( str ).reverse().toString(); 
		return str.equals(  revStr );
	}

	public boolean loginOK(String name, String passwd) {
		return name.length() > 3 && passwd.equals(name);
	}

}
