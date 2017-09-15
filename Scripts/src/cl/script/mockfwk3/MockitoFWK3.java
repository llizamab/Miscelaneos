package cl.script.mockfwk3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;






import javax.xml.datatype.XMLGregorianCalendar;


public class MockitoFWK3 {
	
	
//	public static void main(String[] args) {
//		final Request req = new Request();
//		
//		Input input = new Input();
//		
//		input.setCampo("campo");
//		
//		input.setLocal(TdLocale.ES);
//		
//		input.setCodigo(100L);
//		
//		Cosa cosa = new Cosa();
//		
//		cosa.setAtrib("atrb");
//		cosa.setCod(null);
//		
//		input.getCosas().add(cosa);
//		
//		input.getCosas().add(new Cosa());
//		
//		req.setInput(input);
//		
//		Response resp = new Response();
//		
//		Output out = new Output();
//		out.setCodError("codErr");
//		out.setDesError("des");
//		Cosa cosa1 = new Cosa();
//		
//		cosa1.setAtrib("atrccb");
//		cosa1.setCod(100);
//		out.getCosas().add(cosa1);
//		
//		out.setCosa(cosa1);
//		resp.setOut(out);
//		
//		final MockitoFWK3 mock = new MockitoFWK3();
//		// generate test 
//		mock.generarJunit(req, resp, 
//			"ExtraerIdentificadoresDeAplicacion",  
//			"extraerIdentificadoresDeAplicacion");
//		// fin
//	}
	
	private static final String RUTA_FICHERO_PROPERTIES = "R:/JunitAutomatizados/constantesCreacionJunitFwk3.properties";
	private static final String RUTA_FICHEROS_GENERADOS = "R:/JunitAutomatizados/";
	private static final String finFichero = "TestCase.java";
	
	private final StringBuffer buff = new StringBuffer();
	private final StringBuffer valores = new StringBuffer();
	private final StringBuffer asserts = new StringBuffer();
	private final StringBuffer test = new StringBuffer();
	private final StringBuffer imports = new StringBuffer();
	private final Set<String> importsSet = new HashSet<String>();
	private final StringBuffer anotations = new StringBuffer();
	private final StringBuffer nombre = new StringBuffer();
	private final StringBuffer war = new StringBuffer();
	private final StringBuffer inyecc = new StringBuffer();
	
	
	public void generarJunit(final Object input, final Object output, final String metodo) {
		
		final String rutaFichero = RUTA_FICHERO_PROPERTIES;
		String rutaSalidaTest = RUTA_FICHEROS_GENERADOS;
		// si no existe el fichero
		final File pro = new File(rutaFichero);
		if (!pro.exists() && !pro.isFile()) {
			System.out.println("No existe el fichero " + rutaFichero);
			return;
		}
		
		final Properties properties = leerFicheroPropiedades(rutaFichero);
		final String nombreServicio = properties.getProperty("nombreServicio");
		
		final String interfazComp = properties.getProperty("interfaz").trim();
		
		if (interfazComp == null || interfazComp.trim().isEmpty()) {
			System.out.println("Indicar la interfaz ");
			return;
		}
		final String interfaz = interfazComp.substring(interfazComp.lastIndexOf(".") + 1);
		
		String nombreClaseMetodo = nombreServicio + interfaz;
		
		final String ficheroABuscar = rutaSalidaTest + nombreClaseMetodo + finFichero;
		
		/* Comprobamos si ya existe este test en la ruta*/
		final File file = new File(ficheroABuscar);
		if (file.exists()){
			nombreClaseMetodo = generarNombreFicheroClase(rutaSalidaTest, nombreClaseMetodo, finFichero);
			rutaSalidaTest += nombreClaseMetodo + finFichero;
		} else {
			rutaSalidaTest = ficheroABuscar;
		}
		/* Valido rutas absolutas */
		final String recBbdd = properties.getProperty("recargaBBDD").trim();
		final String sqlData = properties.getProperty("escenario").trim();
		final String packg = properties.getProperty("packageUnit").trim();
		
		
		// generar valores de entrada
		obtenerInput(input);
		// generar valores de salida
		if (output != null) {
			obtenerOutput(output);
		}
		// generate package
		generarPackage(packg);
		// generate anotaciones
		generarAnotations();
		// generate nombre de test
		generarNombreClase(nombreClaseMetodo + "TestCase");
		// generate war
		generarWar(Boolean.valueOf(recBbdd), sqlData);
		// generate inyecciones
		generarInyecciones(interfaz, interfazComp);
		// generar test
		generarTest(metodo, output, Boolean.valueOf(recBbdd));
		// generate imports
		generarImports(Boolean.valueOf(recBbdd));
		// concateno todo
		buff.append(imports)
		.append(anotations)
		.append(nombre)
		.append(war)
		.append(inyecc)
		.append(test)
		.append("\n}\n");
		
//		System.out.println(buff.toString());
		
		try {

			if (buff.length() > 0){
				final BufferedWriter out = new BufferedWriter(new FileWriter(rutaSalidaTest));
				String outText = buff.toString();
				out.write(outText);
				out.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void obtenerInput(final Object input) {
		// obtengo valores de entrada
		beanToString("req", 0, input);
	}
	
	private void obtenerOutput(final Object output) {
		// pendiente generar asserts
		beanToStringAsserts("resp", 0, output);
	}
	
	private void generarTest(String metodo, Object resp, boolean recarga) {
		
		test.append("\t@Test\n");
		if (recarga) {
			// @RecargarEscenario(value = sql)
			test.append("\t@RecargarEscenario(value = sql)\n");
		}
		
		test.append("\tpublic void test() {\n\n")
		.append("\t\tlogger.info(\"Test - Started\");\n\n")
		.append(valores)
		.append("\n\t\t");
		
		if (asserts.length() > 0 && resp != null) {
			Class<?> clazz = resp.getClass();
			test.append(clazz.getSimpleName())
			.append(" resp0 = ");
		}
		
		test.append("service.")
		.append(metodo + "(req0);\n\n");
		
		if (asserts.length() > 0) {
			test.append(asserts);
		}
		
		test.append("\n\t\tlogger.info(\"Test - Finished\");\n\t}\n");
	}
	
	private void generarInyecciones(final String interfaz, final String interfazComp) {
		
		importsSet.add(interfazComp);
		
		inyecc.append("\n\t@Inject\n")
		.append("\tprivate org.slf4j.Logger logger;\n\n");
		inyecc.append("\t@Inject\n");
		inyecc.append("\t" + interfaz + " service;\n\n");
	}
	
	private void generarWar(boolean recarga, final String sqlData) {
		
		if (recarga) {
			war.append("\tprivate final static String sql = ");
			war.append("\"")
			.append(sqlData)
			.append("\";\n");
		}
		
		war.append("\n\t@Deployment\n")
		.append("\tpublic static WebArchive crearWebArchive() {\n\n")
		.append("\t\tWebArchive war = createTestArchive();\n\n");
		
		// pendiente determinar dependencias
		
		
		if (recarga) {
			war.append("\t\twar.addAsResource(sql);\n");
		}
		
		war.append("\t\treturn war;\n\t}\n");
	}
	
	private void generarNombreClase(final String nombreClase) {
		nombre.append("public class ")
		.append(nombreClase)
			.append(" extends BaseTestCase {\n\n");
	}
	
	private void generarAnotations() {
		
		anotations.append("@RunWith(Arquillian.class)\n");
		anotations.append("@Logable\n");
		anotations.append("@Local\n");
		anotations.append("@Stateless\n");
		anotations.append("@TransactionManagement(TransactionManagementType.BEAN)\n");
		anotations.append("@TransactionAttribute(TransactionAttributeType.REQUIRED)\n");
	}
	
	private void generarPackage(final String packageStr) {
		buff.append("package ")
		.append(packageStr)
		.append(";\n\n");
	}
	
	private void generarImports(final boolean recarga) {
		// fijo
		imports.append("import javax.ejb.Local;\n");
		imports.append("import javax.ejb.Stateless;\n");
		imports.append("import javax.ejb.TransactionAttribute;\n");
		imports.append("import javax.ejb.TransactionAttributeType;\n");
		imports.append("import javax.ejb.TransactionManagement;\n");
		imports.append("import javax.ejb.TransactionManagementType;\n");
		imports.append("import javax.inject.Inject;\n\n");
		
		imports.append("import org.jboss.arquillian.container.test.api.Deployment;\n");
		imports.append("import org.jboss.arquillian.junit.Arquillian;\n");
		imports.append("import org.jboss.shrinkwrap.api.spec.WebArchive;\n");
		imports.append("import org.junit.Assert;\n");
		imports.append("import org.junit.Test;\n");
		imports.append("import org.junit.runner.RunWith;\n\n");
		
		imports.append("import es.mercadona.fwk.core.logger.Logable;\n");
		imports.append("import es.mercadona.test.common.BaseTestCase;\n");
		if (recarga) {
			imports.append("import es.mercadona.test.common.RecargarEscenario;\n");
		}
		imports.append("import es.mercadona.test.common.TestUtils;\n\n");
		// por cada
		for (final String imp : importsSet) {
			imports.append("import ").append(imp).append(";\n");
		}
		imports.append("\n");
	}
	

	@SuppressWarnings({ "rawtypes", "unused" })
	private String beanToString(final String nombrePadre, int cont, final Object... beans) {

		final StringBuffer params = new StringBuffer();
		
		final String obj = nombrePadre + cont;
		
		for (final Object bean : beans) {
				
			if (bean != null) {
				Class<?> clazz = bean.getClass();
				final Field[] fields = clazz.getDeclaredFields();
				
				importsSet.add(clazz.getPackage().getName() + "." + clazz.getSimpleName());

				params.append("\t\t")
				.append(clazz.getSimpleName()).append(" " + obj + " = new ")
				.append(clazz.getSimpleName())
				.append("();\n");
				
				AccessibleObject.setAccessible(fields, true);
				for (final Field field : fields) {
					try {
						final Object fieldValue = field.get(bean);
						if (fieldValue instanceof Collection) {
							final Iterator it = ((Collection) fieldValue).iterator();
							if (it.hasNext()) {
								Object fo = it.next();
								if ((fo instanceof Number)
										|| (fo instanceof String)
										|| (fo instanceof Date)
										|| (fo instanceof XMLGregorianCalendar)
										|| (fo instanceof Enum)
										|| (fo instanceof Boolean)) {
									if (fieldValue == null) {
										
										params.append("\t\t")
										.append(obj + ".set" + primerMayus(field.getName()))
										.append("(").append(fieldValue)
										.append(");\n");
	
									} else {
	
										crearSegunTipoDato(fo, params, field, fieldValue, obj);
									}
	
								} else {
	
								try {
									for (Iterator iterator = ((Collection) fieldValue)
											.iterator(); iterator.hasNext();) {

										final String nombreObj = beanToString(null, cont, iterator.next());
										
										params.append("\t\t")
										.append(obj + ".get" + primerMayus(field.getName()))
										.append("().add(" + nombreObj + ");\n");
										
										cont++;
									}
								} catch (Exception ex) {
									ex.printStackTrace();
									System.out.println(ex.getLocalizedMessage());
								}
	
								}
							} else {
								
								params.append("\t\t")
								.append(obj + ".set" + primerMayus(field.getName()))
								.append("(").append(fieldValue.toString())
								.append(");\n");
							}

						} else {
							if (fieldValue == null) {
								
								params.append("\t\t")
								.append(obj + ".set" + primerMayus(field.getName()))
								.append("(").append(fieldValue)
								.append(");\n");

							} else {
								
								if ((fieldValue instanceof Number)
										|| (fieldValue instanceof String)
										|| (fieldValue instanceof Date)
										|| (fieldValue instanceof XMLGregorianCalendar)
										|| (fieldValue instanceof Enum)
										|| (fieldValue instanceof Boolean)) {
									if (fieldValue == null) {
										params.append("\t\t")
										.append(obj + ".set" + primerMayus(field.getName()))
										.append("(").append(fieldValue)
										.append(");\n");

									} else {

										crearSegunTipoDato(null, params, field, fieldValue, obj);
									}

								} else {
									
						
									final String nombreObj = beanToString(obj, cont, fieldValue);
									
									params.append("\t\t")
									.append(obj + ".set" + primerMayus(field.getName()))
									.append("(" + nombreObj + ");\n");
									
									cont++;
								}
							}
						}

					} catch (final IllegalAccessException ex) {
						throw new InternalError(
								"Unexpected IllegalAccessException: "
										+ ex.getMessage());
					}
				}

			}
		}
		
//		System.out.println(params.toString());
		valores.append(params.toString());
	
		return obj;
	}


	private void crearSegunTipoDato(Object fo, StringBuffer params,
			Field field, Object fieldValue, String obj) {
		
		if (fieldValue instanceof BigDecimal) {
			params.append("\t\t")
			.append(obj + ".set" + primerMayus(field.getName()))
			.append("(new BigDecimal(\"").append(fieldValue.toString())
			.append("\"));\n");
			
			importsSet.add("java.math.BigDecimal");
		}
		if (fieldValue instanceof Integer) {
			
			params.append("\t\t")
			.append(obj + ".set" + primerMayus(field.getName()))
			.append("(").append(fieldValue.toString())
			.append(");\n");
		}
		if (fieldValue instanceof Double) {
			
			params.append("\t\t")
			.append(obj + ".set" + primerMayus(field.getName()))
			.append("(").append(fieldValue.toString())
			.append("d);\n");
		}
		if (fieldValue instanceof Long) {
			
			params.append("\t\t")
			.append(obj + ".set" + primerMayus(field.getName()))
			.append("(").append(fieldValue.toString())
			.append("l);\n");
		}
		if (fieldValue instanceof String) {
			
			params.append("\t\t")
			.append(obj + ".set" + primerMayus(field.getName()))
			.append("(\"").append(fieldValue.toString())
			.append("\");\n");
		}
		if (fieldValue instanceof Date) {
			params.append("\t\t")
			.append(obj + ".set" + primerMayus(field.getName()))
			.append("(new Date());\n");
			
			importsSet.add("java.util.Date");
		}
		if (fieldValue instanceof XMLGregorianCalendar) {
			params.append("\t\t")
			.append(obj + ".set" + primerMayus(field.getName()))
			.append("(getDateXML());\n");

		}
		if (fieldValue instanceof Enum) {
			
			Class<?> clazz = fieldValue.getClass();
			
			params.append("\t\t")
			.append(obj + ".set" + primerMayus(field.getName()))
			.append("(")
			.append(clazz.getSimpleName())
			.append(".")
			.append(fieldValue.toString())
			.append(");\n");
			
			importsSet.add(clazz.getPackage().getName() + "." + clazz.getSimpleName());
		}
		if (fieldValue instanceof Boolean) {
			params.append("\t\t")
			.append(obj + ".set" + primerMayus(field.getName()))
			.append("(").append(Boolean.valueOf(fieldValue.toString()))
			.append(");\n");
		}

	}
	
	private String primerMayus(final String campo) {
		
		return campo.substring(0, 1).toUpperCase() + campo.substring(1);
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private String beanToStringAsserts(final String nombrePadre, int cont, final Object... beans) {

		final String obj = nombrePadre + cont;
		
		for (final Object bean : beans) {
				
			if (bean != null) {
				Class<?> clazz = bean.getClass();
				final Field[] fields = clazz.getDeclaredFields();
				
				importsSet.add(clazz.getPackage().getName() + "." + clazz.getSimpleName());

				asserts.append("\t\tAssert.assertNotNull(")
				.append(obj + ");\n");
				
				AccessibleObject.setAccessible(fields, true);
				for (final Field field : fields) {
					try {
						final Object fieldValue = field.get(bean);
						if (fieldValue instanceof Collection) {
							final Iterator it = ((Collection) fieldValue).iterator();
							if (it.hasNext()) {
								Object fo = it.next();
								if ((fo instanceof Number)
										|| (fo instanceof String)
										|| (fo instanceof Date)
										|| (fo instanceof XMLGregorianCalendar)
										|| (fo instanceof Enum)
										|| (fo instanceof Boolean)) {
									if (fieldValue == null) {
										
										asserts.append("\t\tAssert.assertEquals(null, ")
										.append(obj + ".get" + primerMayus(field.getName()))
										.append("());\n");
	
									} else {
	
										crearSegunTipoDatoAssert(fo, asserts, field, fieldValue, obj);
									}
	
								} else {
	
								try {
									
									asserts.append("\t\t").append(fieldValue.getClass().getSimpleName())
									.append(" " + String.valueOf(obj + 1) + " = ")
									.append(obj)
									.append(".get" + primerMayus(field.getName()) + "();\n");
									
									asserts.append("\t\tAssert.assertNotNull(")
									.append(String.valueOf(obj + 1) + ");\n");
									
									asserts.append("\t\tAssert.assertEquals(")
									.append(((Collection) fieldValue).size())
									.append(", ")
									.append(String.valueOf(obj + 1) + ".size());\n");
									
								
								} catch (Exception ex) {
									ex.printStackTrace();
									System.out.println(ex.getLocalizedMessage());
								}
	
								}
							} else {
								
								asserts.append("\t\tAssert.assertEquals(")
								.append(fieldValue.toString())
								.append(", ")
								.append(obj + ".get" + primerMayus(field.getName()))
								.append("());\n");
							}

						} else {
							if (fieldValue == null) {
								
								asserts.append("\t\tAssert.assertEquals(")
								.append("null")
								.append(", ")
								.append(obj + ".get" + primerMayus(field.getName()))
								.append("());\n");

							} else {
								
								if ((fieldValue instanceof Number)
										|| (fieldValue instanceof String)
										|| (fieldValue instanceof Date)
										|| (fieldValue instanceof XMLGregorianCalendar)
										|| (fieldValue instanceof Enum)
										|| (fieldValue instanceof Boolean)) {
									if (fieldValue == null) {
										asserts.append("\t\tAssert.assertEquals(")
										.append("null")
										.append(", ")
										.append(obj + ".get" + primerMayus(field.getName()))
										.append("());\n");

									} else {

										crearSegunTipoDatoAssert(null, asserts, field, fieldValue, obj);
									}

								} else {
									
									// getter del atributo
									asserts.append("\t\t").append(fieldValue.getClass().getSimpleName())
									.append(" " + String.valueOf(obj + 1) + " = ")
									.append(obj)
									.append(".get" + primerMayus(field.getName()) + "();\n");
									
									cont++;
									
									// pendiente
									final String nombreObj = beanToStringAsserts(obj, cont, fieldValue);

									
								}
							}
						}

					} catch (final IllegalAccessException ex) {
						throw new InternalError(
								"Unexpected IllegalAccessException: "
										+ ex.getMessage());
					}
				}

			}
		}
		
//		System.out.println(params.toString());
//		asserts.append(params.toString());
	
		return obj;
	}

	private void crearSegunTipoDatoAssert(Object object,
			StringBuffer params, Field field, Object fieldValue, String obj) {
		// TODO Auto-generated method stub
		if (fieldValue instanceof BigDecimal) {
			params.append("\t\tAssert.assertEquals(")
			.append("new BigDecimal(\"").append(fieldValue.toString())
			.append("\"), ")
			.append(obj + ".get" + primerMayus(field.getName()))
			.append("());\n");
			
			importsSet.add("java.math.BigDecimal");
		}
		if (fieldValue instanceof Integer) {
						
			params.append("\t\tAssert.assertEquals(")
			.append(fieldValue.toString())
			.append(", ")
			.append(obj + ".get" + primerMayus(field.getName()))
			.append("());\n");
		}
		if (fieldValue instanceof Double) {
			
			params.append("\t\tAssert.assertEquals(")
			.append(fieldValue.toString())
			.append("d, ")
			.append(obj + ".get" + primerMayus(field.getName()))
			.append("());\n");
		}
		if (fieldValue instanceof Long) {
			
			params.append("\t\tAssert.assertEquals(")
			.append(fieldValue.toString())
			.append("l, ")
			.append(obj + ".get" + primerMayus(field.getName()))
			.append("());\n");
		}
		if (fieldValue instanceof String) {
			
			params.append("\t\tAssert.assertEquals(\"")
			.append(fieldValue.toString())
			.append("\", ")
			.append(obj + ".get" + primerMayus(field.getName()))
			.append("());\n");
		}
		if (fieldValue instanceof Date) {
			
			params.append("\t\tAssert.assertNotNull(")
			.append(obj + ".get" + primerMayus(field.getName()))
			.append("());\n");
			
			importsSet.add("java.util.Date");
		}
		if (fieldValue instanceof XMLGregorianCalendar) {
			params.append("\t\tAssert.assertNotNull(")
			.append(obj + ".get" + primerMayus(field.getName()))
			.append("());\n");

		}
		if (fieldValue instanceof Enum) {
			
			Class<?> clazz = fieldValue.getClass();
			
			params.append("\t\tAssert.assertNotNull(")
			.append(obj + ".get" + primerMayus(field.getName()))
			.append("());\n");
			
			importsSet.add(clazz.getPackage().getName() + "." + clazz.getSimpleName());
		}
		if (fieldValue instanceof Boolean) {
			params.append("\t\tAssert.assertEquals(")
			.append(Boolean.valueOf(fieldValue.toString()))
			.append(", ")
			.append(obj + ".get" + primerMayus(field.getName()))
			.append("());\n");
		}
	}
	
	/**
	 * Metodo que lee las entradas de un fichero properties
	 * 
	 * @param rutaFichero
	 * @return codigo a cargar en el fichero JUnit
	 */
	private Properties leerFicheroPropiedades(String rutaFichero) {
		Properties properties = new Properties();
	    InputStream input = null;
	    try {
	    	
			input = new FileInputStream(rutaFichero);
			properties.load(input);
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		return properties;
	}
	
	private String generarNombreFicheroClase(String rutaSalidaTest, String nombreClaseMetodo, String finFichero){
		// cogemos la carpeta donde se generan los junit
		File dir = new File(rutaSalidaTest);
		// listamos todos los ficheros que hay en la carpeta
		String[] ficheros = dir.list();
		String archivo = "";
		List<Integer> numerosFichero = new ArrayList<Integer> ();
		// recorremos la lista de ficheros de la carpeta
	    if (ficheros != null && ficheros.length > 0){
	    	
	    	for (int x = 0 ; x < ficheros.length; x++){
	    		
	    		archivo = ficheros[x].toString();
  		
	    		// solo comprobamos los ficheros que sean .java
	    		if(archivo.endsWith(".java")){

	    			if(archivo.contains(nombreClaseMetodo)){
	    				
	    				// si el archivo se llama igual, comprobamos si lleva numero en el nombre
	    				// para ello cortamos el nombre del fichero, le quitamos la coletilla TestCase.java
	    				String nombreFicheroJava = "";
	    				int fin = archivo.length() - finFichero.length();
	    				nombreFicheroJava = archivo.substring(0, fin);
	
	    				// si no son iguales, el nombre del fichero Java lleva algun numero
	    				if(!nombreFicheroJava.equalsIgnoreCase(nombreClaseMetodo)){
	    					// obtenemos el numero del fichero
    						String numeroFichero;
	    					numeroFichero = nombreFicheroJava.substring(nombreClaseMetodo.length(), nombreFicheroJava.length());
	    					// comprobamos que el numeroFichero sea numerico
	    					if(isNumeric(numeroFichero)){
	    						
	    						numerosFichero.add(Integer.valueOf(numeroFichero));
	    					}
	    				}
		    		}
	    		}
	    		
	        }
	    	int valor = 0;
	    	int acumulado = 0;
	    	// comprobamos en la lista de numeros, cual es el mayor numero
	    	// nos quedamos con este e incrementamos su valor en 1
	    	for(int y = 0; y < numerosFichero.size(); y++){
	    		
	    		valor = numerosFichero.get(y);
	    		if(valor >= acumulado){
	    			acumulado = valor;
	    		}
	    	}
	    	acumulado++;
	    	nombreClaseMetodo += String.valueOf(acumulado);
	    }
		
		return nombreClaseMetodo;
	}
	
	 private boolean isNumeric(String cadena){
        try {
        	Integer.parseInt(cadena);
        	return true;
        } catch (NumberFormatException nfe){
        	return false;
        }
    }
}
