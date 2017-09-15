package cl.scripts.CommentReload;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Comenta las recargas de escenarios en un proyecto especificado, para los
 * JUnits con cierto patron de nombre y que contingan ciertos codigos.
 * @author llizamab
 */
public class CommentReload {

	/**
	 * Array de codigos de error/texto a buscar.
	 */
	private static String[] CODES = {"orgaz.ws.buscarPuestos.errorFormatoDatos", "produ.codigo"};

	/**
	 * patron de junits a buscar.
	 */
	private static String PATRON_NOM = "OPB6.+TestCase\\.java";

	/**
	 * Ruta proyecto
	 */
	private static String PROYECTO = "CC001";

	/**
	 * patron de recargarEscenario.
	 */
	private static final String PATRON_REC = "^(?!.*//)(.+\\.recargarEscenario\\(.+)";

	/**
	 * Trazas.
	 */
	private static final boolean TRAZA = Boolean.TRUE;

	/**
	 * Comenta el recargar escenario de los JUnits con algun texto en ellos.
	 * @param args
	 */
	public static void main(String[] args) {

		// Leer input del usuario

		// Buscar en el proyecto
		File[] files = new File("R:\\desarrollo\\workspace\\" + PROYECTO).listFiles();
		// Itero los ficheros
		procesarJunits(files);
	}
	
	/**
	 * Itera los ficheros del proyecto para el proceso.
	 * @param files ficheros.
	 */
	public static void procesarJunits(File[] files) {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            procesarJunits(file.listFiles());
	        } else {
	            // Si coindice con el patron de nombre
	            if (file.getName().matches(PATRON_NOM)) {
	            	// Proceso el fichero
	            	debug("File: " + file.getName());
	            	buscarCodigo(file);
	            }
	        }
	    }
	}

	/**
	 * Busca en el fichero si existen los codigos a buscar.
	 * @param file fichero procesado.
	 */
	public static void buscarCodigo(File file) {
		// Indicador si existe recarga de escenario
		Boolean flagExisteRecarga = Boolean.FALSE;
		// Indicador si existe el codigo
		Boolean flagExisteCodigo = Boolean.FALSE;
		// Texto para el recargar
		String recarga = null;
		try {
			// Abro el archivo
			FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				// Si coincide con el recargar escenario
				if (strLine.matches(PATRON_REC)) {
					// indico en el flag
					flagExisteRecarga = Boolean.TRUE;
					debug(strLine);
					// guardo la linea para luego comentarla
					recarga = strLine;
				}
				// Busco si existe coincidencia con algun codigo
				for (String code : CODES) {
					// si coincide
					if (strLine.matches(".+\""+ code +"\".+")) {
						flagExisteCodigo = Boolean.TRUE;
						debug(strLine);
					}
				}
			}
			in.close();
			// si existe
			if (flagExisteRecarga && flagExisteCodigo) {
				// comento el recarga escenario
				muestra("Se comentara para: " + file.getName());
				StringBuffer archivo = readFileAsString(file.getAbsolutePath());
				archivo.insert(archivo.lastIndexOf(recarga), "//");
				FileWriter writer = new FileWriter(file);
				writer.write(archivo.toString());
				writer.close();
			}

		} catch(IOException ex) {
			debug(ex.getLocalizedMessage());
			debug(ex.getStackTrace().toString());
		} 
	}

	/**
	 * Lee el texto de un archivo.
	 * @param filePath ruta del fichero
	 * @return string con el texto del archivo
	 * @throws IOException error de IO
	 */
	public static StringBuffer readFileAsString(String filePath) throws IOException {
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData;
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
}
