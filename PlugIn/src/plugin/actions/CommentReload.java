package plugin.actions;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * Comenta las recargas de escenarios en un proyecto especificado, para los
 * JUnits con cierto patron de nombre y que contingan ciertos codigos.
 * @author llizamab
 */
public class CommentReload {

	/**
	 * Array de codigos de error/texto a buscar.
	 */
	//private static String[] CODES = {"orgaz.ws.buscarPuestos.errorFormatoDatos", "produ.codigo"};
	private static Set<String> CODES;

	/**
	 * patron de junits a buscar.
	 */
	private static String PATRON_NOM = "OPB6.+TestCase\\.java";

	/**
	 * Ruta proyecto.
	 */
	private static String PROYECTO = "CC001";

	/**
	 * patron de recargarEscenario.
	 */
	private static final String PATRON_REC = "^(?!.*//)(.+\\.recargarEscenario\\(.+)";

	/**
	 * Contador.
	 */
	private static int CONT = 0;

	/**
	 * Trazas.
	 */
	private static final boolean TRAZA = Boolean.TRUE;

	/**
	 * Comenta el recargar escenario de los JUnits con algun texto en ellos.
	 * @param args
	 */
	public static int ejecutar(String proyecto, String patron, Set<String> codigos) {

		PROYECTO = proyecto;
		PATRON_NOM = patron + "TestCase\\.java";
		CODES = codigos;
		CONT = 0;
		muestra("************************************************");
		muestra("Comienza el analisis con los datos: ");
		muestra("\tProyecto : " + PROYECTO);
		muestra("\tJunits   : " + PATRON_NOM);
		muestra("\tCodigos  : " + CODES.toString());
		muestra("************************************************");
		muestra("Buscando Junits en la ruta: \n\t" + PROYECTO);
		// Buscar en el proyecto
		try {
			File[] files = new File(PROYECTO).listFiles();
			// Itero los ficheros
			procesarJunits(files);
			
			muestra("************************************************");
			muestra("Finalizado el analisis\nSe ha comentado la recarga en " + CONT + " JUnits");
			muestra("************************************************");
		} catch (Exception ex) {
			muestra("************************************************");
			muestra("Ha ocurrido un error:");
			debug(ex.getLocalizedMessage());
			debug(ex.getStackTrace().toString());
			CONT = -1;
			muestra("************************************************");
		}
		return CONT;
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
	        	if (file.getName().endsWith(".java")) {
	        		debug("Fichero: " + file.getName());
	        	}
	            // Si coindice con el patron de nombre
	            if (file.getName().matches(PATRON_NOM)) {
	            	// Proceso el fichero
	            	debug(file.getName() + " coincide con el patron de nombre: " + PATRON_NOM);
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
					//debug(strLine);
					// guardo la linea para luego comentarla
					recarga = strLine;
				}
				// Busco si existe coincidencia con algun codigo
				for (String code : CODES) {
					// si coincide
					if (strLine.matches(".+\""+ code +"\".+")) {
						flagExisteCodigo = Boolean.TRUE;
						debug("Coincidencia: código<"+code+"> linea <" + strLine + ">");
						break;
					}
				}
			}
			in.close();
			// si existe
			if (flagExisteRecarga && flagExisteCodigo) {
				// comento el recarga escenario
				muestra("Se comentara la recarga para: " + file.getName());
				StringBuffer archivo = readFileAsString(file.getAbsolutePath());
				archivo.insert(archivo.lastIndexOf(recarga), "//");
				FileWriter writer = new FileWriter(file);
				writer.write(archivo.toString());
				writer.close();
				CONT++;
			}
			if (!flagExisteRecarga) {
				debug("El Junit: " + file.getName() + " no tiene recarga de escenario.");
			}
			if (!flagExisteCodigo) {
				debug("El Junit: " + file.getName() + " no tiene ninguno de los códigos: " + CODES.toString());
			}
		} catch(IOException ex) {
			muestra("************************************************");
			muestra("Ha ocurrido un error:");
			debug(ex.getLocalizedMessage());
			debug(ex.getStackTrace().toString());
			CONT = -1;
			muestra("************************************************");
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
		//System.out.println("---------------------------");
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
