package cl.pymap.pymapwar.util;

import java.util.ArrayList;

public class RemoverDuplicadosUtil {
	
	public <T> ArrayList<T> removerDuplicadosEnListas(ArrayList<T> listaDuplicada) {
        ArrayList<T> listaNoDuplicada = new ArrayList<T>();
  
        for (T elemento : listaDuplicada) {
            if (!listaNoDuplicada.contains(elemento)) {
            	listaNoDuplicada.add(elemento);
            }
        }

        return listaNoDuplicada;
    }  
}
