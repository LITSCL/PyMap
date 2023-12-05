package cl.pymap.pymapwar.util;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.Part;

public class ArchivoUtil {
	CadenaUtil cadenaUtil = new CadenaUtil();
	
	public String guardarArchivo(Part archivo, File rutaArchivoDestino) {
		String nombreArchivo = "";	
		try {
			Path rutaArchivoOrigen = Paths.get(archivo.getSubmittedFileName());
			nombreArchivo = cadenaUtil.cadenaAleatoria(15) + "-" + rutaArchivoOrigen.getFileName().toString();
			InputStream archivoSerializado = archivo.getInputStream();
			
			if (archivoSerializado != null) {
				File fi = new File(rutaArchivoDestino, nombreArchivo);
				Files.copy(archivoSerializado, fi.toPath());
			}
		} catch (Exception ex) {
			
		}
		return nombreArchivo;
	}
	
	public boolean validarFormato(String nombreImagen, String[] formatosSoportados) {
		for (String formato : formatosSoportados) {
			if (nombreImagen.toLowerCase().endsWith(formato)) {
				return true;
			}
		}
		return false;
	}	
}
