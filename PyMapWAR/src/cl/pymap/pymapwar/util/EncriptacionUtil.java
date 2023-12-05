package cl.pymap.pymapwar.util;

import java.security.MessageDigest;

public class EncriptacionUtil {
	public String encriptarClave(String clave) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			
			md.update(clave.getBytes());
			byte[] claveEnArrayDeBytes = md.digest();
		
			StringBuilder sb = new StringBuilder();
			for (byte b : claveEnArrayDeBytes) {
				sb.append(String.format("%02x", b));
			}
			
			String claveEncriptada = sb.toString();
			
			return claveEncriptada;
		} catch (Exception ex) {
			return "";
		}
	}
	
	public boolean validarClave(String claveSinEncriptar, String claveEncriptada) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			
			md.update(claveSinEncriptar.getBytes());
			byte[] claveEnArrayDeBytes = md.digest();
		
			StringBuilder sb = new StringBuilder();
			for (byte b : claveEnArrayDeBytes) {
				sb.append(String.format("%02x", b));
			}
			
			String encriptacion = sb.toString();
			
			if (encriptacion.equals(claveEncriptada)) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}
}
