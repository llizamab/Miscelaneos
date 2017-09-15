package plugin.actions;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Clase que genera de 
 * forma automatica
 * Constantes con un maximo de 17 caracteres
 * y la guarda automaticamente
 * en el fichero especificado.
 * 
 * @author llizamab
 *
 */
public class GeneraConstante {

	/**
	 * Ruta del fichero de constante.
	 */
	private String rutaFichero;
	/**
	 * Apostrofe.
	 */
	private String identificador;
	/**
	 * Nombre de la key.
	 */
	private String valor;
	/**
	 * Nombre del parametro.
	 */
	private String nombreParam;
	/**
	 * Tipo de dato.
	 */
	private Tipos tipo;

	/**
	 * Tipos disponibles.
	 */
	public enum Tipos {
		String,
		Integer,
		Long,
		Date,
		Double,
		Character
	};
	
	/**
	 * Constructor de la clase.
	 * @param rutaFichero rutaFichero
	 * @param identificador identificador
	 * @param valor valor
	 * @param tipo tipo
	 * @param nombreParam nombreParam
	 */
	public GeneraConstante(final String rutaFichero, final String identificador, 
			final String nombreParam, final String valor, Tipos tipo) {
		super();
		this.rutaFichero = rutaFichero;
		this.identificador = identificador;
		this.nombreParam = nombreParam;
		this.valor = valor;
		this.tipo = tipo;
	}

	/**
	 * @return the rutaFichero
	 */
	public String getRutaFichero() {
		return rutaFichero;
	}

	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * @return the nombreParam
	 */
	public String getNombreParam() {
		return nombreParam;
	}

	/**
	 * Metodo que genera la constante.
	 */
	@SuppressWarnings("static-access")
	public void generarConstante() {
		try {
			// veo si existe el fichero
			final File files = new File(getRutaFichero());
			if (files == null || !files.exists() || !files.isFile()) {
				// no existe fichero
				System.out.println("Error No existe el fichero: " + getRutaFichero());
				return;
			}
			// valido si ya existe una cosntante con el mismo valor
			if (!existeConstanteValor()) {
				// genero la constante
				String constante = this.generarConstante(getIdentificador(), 
						getNombreParam(), null);
				// veo si ya existe
				while (existeConstante(constante)) {
					// si ya existe la creo de nuevo
					constante = this.generarConstante(getIdentificador(), 
							getNombreParam(), constante);
				}
				// si no existe la guardo en el fichero
				final String linea = generarLinea(constante);
				// generar doc
				guardarLinea(linea);
			}
			System.out.println("Proceso finalizado correctamente");
			
		} catch (final Exception excp) {
			// error
			System.out.println("Ha ocurrido un error inesperado: " + excp.getLocalizedMessage());
			excp.printStackTrace();
		}
	}
	
	/**
	 * Guarda la constante generada en el fichero.
	 * @param linea linea
	 * @throws IOException excp
	 */
	private void guardarLinea(final String linea) throws IOException {
		// guardar en el fichero
		System.out.println("Guardando linea");
		System.out.println(linea);
		
		final File file = new File(getRutaFichero());
		
		final StringBuilder archivo = readFileAsString(file.getAbsolutePath());
		
		final StringBuilder constante = new StringBuilder();
		constante
		.append("\t/**\n")
		.append("\t * Constante parámetro " + getNombreParam() + ".\n")
		.append("\t */\n")
		.append("\t" + linea + "\n\n");
		
		archivo.insert(archivo.lastIndexOf("}"), constante.toString());
		
		final FileWriter writer = new FileWriter(file);
		writer.write(archivo.toString());
		writer.close();
	}
	
	/** Generar la linea a insertar al fichero.
	 * @param constante constante
	 * @return String
	 */
	private String generarLinea(final String constante) {
		// nombreParam
		final String nombre = getNombreParam();
		// valor
		final String valor = getValor();
		// resultado
		String result = "public static final " + this.tipo.toString()
				+ " " + constante + " = ";
		// si es por valor
		if (valor != null) {
			// evaluo el tipo de dato
			if (this.tipo.equals(Tipos.String)) {
				result += "\"" + valor + "\"";
			} else if (this.tipo.equals(Tipos.Double)) {
				result +=  valor + "d";
			} else if (this.tipo.equals(Tipos.Long)) {
				result +=  valor + "l";
			} else if (this.tipo.equals(Tipos.Integer)) {
				result +=  valor;
			} else if (this.tipo.equals(Tipos.Character)) {
				result += "\'" + valor + "\'";
			}
		} else {
			// si es por nombre
			if (this.tipo.equals(Tipos.String)) {
				result += "\"" + nombre + "\"";
			} else if (this.tipo.equals(Tipos.Double)) {
				result +=  nombre + "d";
			} else if (this.tipo.equals(Tipos.Long)) {
				result +=  nombre + "l";
			} else if (this.tipo.equals(Tipos.Integer)) {
				result +=  nombre;
			} else if (this.tipo.equals(Tipos.Character)) {
				result += "\'" + nombre + "\'";
			}
		}
		// retorno
		return result + ";";
	}
	
	/**
	 * Evalua si ya existe la constante
	 * en el fichero.
	 * @param constante constante
	 * @return true/false
	 * @throws IOException 
	 */
	private boolean existeConstante(final String constante) throws IOException {
		System.out.println("Validando si ya existe en el fichero");
		// buscar en el fichero
		final File file = new File(getRutaFichero());
		// retrono
		boolean existe = false;
		// Abro el archivo
		final FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
		final DataInputStream in = new DataInputStream(fstream);
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			// Si coincide con el recargar escenario
			if (strLine.contains(constante)) {
				existe = true;
				break;
			}
		}
		br.close();
		// return
		return existe;
	}
	
	/**
	 * Evalua si ya existe la constante
	 * en el fichero.
	 * @param constante constante
	 * @return true/false
	 * @throws IOException 
	 */
	private boolean existeConstanteValor() throws IOException {
		System.out.println("Validando si ya existe el valor");
		// buscar en el fichero
		final File file = new File(getRutaFichero());
		// retrono
		boolean existe = false;
		// Abro el archivo
		final FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
		final DataInputStream in = new DataInputStream(fstream);
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			// Si coincide con el recargar escenario
			if (existeValor(strLine, getNombreParam(), getValor(), this.tipo)) {
				existe = true;
				break;
			}
		}
		br.close();
		System.out.println("Existe: " + existe);
		// return
		return existe;
	}
	
	/** Busco si ya existe.
	 * @param strLine strLine
	 * @param nombreParam nombreParam
	 * @param valor valor
	 * @param tipo tipo
	 * @return true/false
	 */
	private static boolean existeValor(final String strLine, final String nombreParam, 
			final String valor, Tipos tipo) {
		// existe
		boolean existe = false;
		// si el valor es distinto de null
		if (valor != null) {
			// busco si ya existe el valor
			if (tipo.equals(Tipos.String)) {
				if (strLine.contains("\"" + valor + "\";")) {
					existe = true;
				}
			} else if (tipo.equals(Tipos.Double)) {				
				if (strLine.contains(valor + "d;")) {
					existe = true;
				}
			} else if (tipo.equals(Tipos.Long)) {
				if (strLine.contains(valor + "l;")) {
					existe = true;
				}
			} else if (tipo.equals(Tipos.Integer)) {
				if (strLine.contains(valor + ";")) {
					existe = true;
				}
			} else if (tipo.equals(Tipos.Character)) {
				if (strLine.contains("\'" + valor + "\';")) {
					existe = true;
				}
			}
		} else {
			// si no busco por nombre de constante
			if (strLine.contains("\"" + nombreParam + "\";")) {
				existe = true;
			}
		}
		// retorno
		return existe;
	}
	
	/** Genera el nombre de la constante.
	 * @param nombreConst nombreConst
	 * @param identificador identificador
	 * @param valor valor
	 * @param constanteAnt constanteAnt
	 * @return String
	 */
	private static String generarConstante(final String identificador, 
		final String valor, final String constanteAnt) {
		System.out.println("Generando constante");
		// longitud maxima = 17 (menos lo ya usado por el ident_)
		int longMax = 17 - identificador.length() -1;
		// algorito para generar idnt
		String constante = "";
		// si es distinto de null es porque ya lo genere
		if (constanteAnt != null) {
			// cambio el ultimo caracter por otro cualquiera
			return constanteAnt.substring(0, constanteAnt.length() -1) + randomCaracter();
		}
		// guardo tal cual
		constante += valor;
		// si supera la longitud maxima
		if (identificador.length() + 1 + valor.length() > 17) {
			final Random random = new Random();
			// si la supera, entonces genero abreviatura
			while (constante.length() > longMax) {
				final StringBuilder sbd = new StringBuilder(constante);
				constante = sbd.deleteCharAt(random.nextInt(sbd.length() -1)).toString();
			}
		}
		final String retonro = identificador.toUpperCase() + "_" 
				+ constante.toUpperCase();
		System.out.println("Valor generado: " + retonro);
		// retorno
		return retonro;
	}
	
	/**
	 * caracteres.
	 */
	private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	/** Genera un caracter cualquiera.
	 * @return String
	 */
	private static String randomCaracter() {
		final Random random = new Random();
	    return String.valueOf(alphabet.charAt(random.nextInt(alphabet.length())));
	}
	
	/**
	 * Lee el texto de un archivo.
	 * @param filePath ruta del fichero
	 * @return string con el texto del archivo
	 * @throws IOException error de IO
	 */
	public static StringBuilder readFileAsString(String filePath) throws IOException {
		final StringBuilder fileData = new StringBuilder();
		final BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData;
	}
}
