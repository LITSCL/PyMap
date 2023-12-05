package cl.pymap.pymapwar.util;

import java.util.concurrent.ThreadLocalRandom;

public class CadenaUtil {
	
	public String cadenaAleatoria(int longitud) {
	    String caracteresPermitidos = "0123456789abcdefghijklmnopqrstuvwxyz";
	    String cadenaAleatoria = "";
	    
	    for (int i = 0; i < longitud; i++) {
	        int indiceAleatorio = numeroAleatorioEnRango(0, caracteresPermitidos.length() - 1);
	        char caracterAleatorio = caracteresPermitidos.charAt(indiceAleatorio);
	        cadenaAleatoria+=caracterAleatorio;
	    }
	    
	    return cadenaAleatoria;
	}
	
    protected int numeroAleatorioEnRango(int minimo, int maximo) {
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }
    
}
