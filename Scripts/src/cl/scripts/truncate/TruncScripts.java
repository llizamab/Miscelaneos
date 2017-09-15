package cl.scripts.truncate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TruncScripts {

	/**
	 * Ruta del archivo de agregación.
	 */
	private static final String DELETE_SQL =
			"R:\\desarrollo\\workspace\\Scripts\\scripts\\delete.sql";

	/**
	 * Ruta del archivo de inserciones.
	 */
	private static final String DATA_SQL = 
			"R:\\desarrollo\\workspace\\Scripts\\scripts\\data.sql";

	/**
	 * Trazas.
	 */
	private static final boolean TRAZA = false;

	/**
	 * Clase que limpia el borra.sql a travez del script de carga.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String agrega = "";
		String borra = "";

		try {
			agrega = readFileAsString(DATA_SQL);
			borra = readFileAsString(DELETE_SQL);

		} catch (IOException e) {
			debug(e.getLocalizedMessage());
			return;
		}

		final Pattern patron = Pattern.compile("(into|INTO)(\\s+\r?\n|\\s+)([a-zA-Z0-9_]+)");
		final Matcher matcher = patron.matcher(agrega);
		debug("Buscando las tablas");
		final Set<String> tablas = new LinkedHashSet<String>();
		while (matcher.find()) {
			debug("match:" + matcher.group(3));
			tablas.add(matcher.group(3));
		}
		debug("Marco el borrar");
		// en el borrar los marco
		// con este patron tambien marca los alter disable/enable pero eso falla en algunos casos
		//borra = borra.replaceAll(";", "#;");
		
		final Pattern patron3 = Pattern.compile("(TRUNCATE.+)(;)");
		final Matcher matcher3 = patron3.matcher(borra);
		
		while (matcher3.find()) {
			debug("\t\tmatch2:" + matcher3.group());
			borra = borra.replaceAll(matcher3.group(), matcher3.group().replace(";", "#;"));
		}

		debug(borra);
		debug("Itero el Set de tablas");
		for (String tabla : tablas) {
			debug("\ttabla:" + tabla);
			// Con este patron tambien elimina los alter disable/enable constraints, pero eso da error en algunos casos
			// Pattern patron2 = Pattern.compile("(.+)(\\s" + tabla + ")(\\s.+#;|#;)");
			Pattern patron2 = Pattern.compile("(.+)(\\s" + tabla + ")(#;)");
			Matcher matcher2 = patron2.matcher(borra);
			debug("\tcoincidencias:");
			while (matcher2.find()) {
				debug("\t\tmatch2:" + matcher2.group());
				borra = borra.replaceAll(matcher2.group(), matcher2.group().replace("#", ""));
			}
		}
		debug("borrar desmarcado");
		debug(borra);
		// luego busco todos los marcados
		debug("Elimino los marcados");
		borra = borra.replaceAll(".+#;", "");
		debug("Elimino las lineas vacias");
		borra = borra.replaceAll("(?m)^[ \t]*\r?\n", "");
		// agrego un commit
		muestra(borra);
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
