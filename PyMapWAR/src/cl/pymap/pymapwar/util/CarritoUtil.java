package cl.pymap.pymapwar.util;

import java.util.List;

public class CarritoUtil {
	public int obtenerTotal(List<Object[]> carrito) {
		int total = 0;
		
		for (int i = 0; i < carrito.size(); i++) {
			total+=(int)((double)carrito.get(i)[1] * (int)carrito.get(i)[2]);
		}
		return total;
	}
}
