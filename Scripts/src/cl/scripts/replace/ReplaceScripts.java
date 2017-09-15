package cl.scripts.replace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceScripts {

	/**
	 * Ruta del archivo de agregación.
	 */
	private static final String DELETE_SQL =
			"R:\\desarrollo\\workspace\\Scripts\\scripts\\delete.sql";

	/**
	 * Ruta del archivo de inserciones.
	 */
	private static final String DATA_SQL = 
			"R:\\desarrollo\\workspace\\Scripts\\scripts\\ETR_OP2.csv";

	/**
	 * Trazas.
	 */
	private static final boolean TRAZA = true;

	/**
	 * Clase que limpia el borra.sql a travez del script de carga.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Muestra la salida por consola.
	 * @param texto string a mostrar.
	 */
	public static void muestra(String texto) {
		System.out.println("---------------------------");
		System.out.println(texto);
	}

	/**
	 * Muestra por consola cuando traza esta en true.
	 * @param texto string a mostrar.
	 */
	public static void debug(String texto) {
		if (TRAZA) {
			muestra(texto);
		}
	}

	/**
	 * Lee el texto de un archivo.
	 * @param filePath ruta del fichero
	 * @return string con el texto del archivo
	 * @throws IOException error de IO
	 */
	public static String readFileAsString(String filePath) throws IOException {
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}

}
