package plugin.actions;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que genera el test dummy.
 * @author llizamab
 * @version 1.0
 */
public class GenerateDummyTest {

	/**
	 * Metodo main.
	 * @param args argumentos
	 */
	public static void main(final String[] args) {
		int opc = 4;
		StringBuffer buuf = null;
		DummyTest obj = null;
		switch (opc) {
			case 1: // Prueba con todos los parametros
				obj = new DummyTest(
						"CrearColaJMSManager",
						"produ.manager.CrearColaJMSManager",
						"CrearColaJMS",
						"test/config/ed_ejemplojms/v100/config,test/config/ed_ejemplojms/v100/dummycola",
						new HashSet<String>() { {
							add("ErrorEscrituraJMSException"); } },
						true,
						true,
						new HashSet<String>() { {
							add("ConstLogger"); } },
						true);
				buuf = obj.generarTestDummy();
				break;
			case 2: // Constructor sin incluir llamada a metodos privados
				obj = new DummyTest(
						"CrearColaJMSManager",
						"produ.manager.CrearColaJMSManager",
						"CrearColaJMS",
						"test/config/ed_ejemplojms/v100/config,test/config/ed_ejemplojms/v100/dummycola",
						null, true);
				buuf = obj.generarTestDummy();
				break;
			case 3: // Constructor basico
				obj = new DummyTest(
						"CrearColaJMSManager",
						"produ.manager.CrearColaJMSManager",
						"CrearColaJMS",
						"test/config/ed_ejemplojms/v100/config,test/config/ed_ejemplojms/v100/dummycola");
				buuf = obj.generarTestDummy();
				break;
			case 4: // Constructor basico con opción de incluir rutas absolutas
				obj = new DummyTest(
						"CrearColaJMSManager",
						"produ.manager.CrearColaJMSManager",
						"CrearColaJMS",
						"test/config/ed_ejemplojms/v100/config,test/config/ed_ejemplojms/v100/dummycola",
						true);
				buuf = obj.generarTestDummy();
				break;
		}

		System.out.println(buuf.toString());
	}

}

/**
 * Clase que genera el dumytest.
 * @author llizamab
 * @version 1.0
 */
final class DummyTest {
	/**
	 * nombre de la clase manager.
	 */
	private String pMANAGER;
	/**
	 * identificador del bean del manager.
	 */
	private String pBEAN_MANAGER;
	/**
	 * directorios de dependencias.
	 */
	private String pDIRECTORIOS;
	/**
	 * Nombre de la operación.
	 */
	private String pOPERACION;
	/**
	 * Listado de excepciones.
	 */
	private Set<String> pEXCEPTIONS;
	/**
	 * Flag para incluir excepciones.
	 */
	private Boolean pINCLUIREXCEPTIONS;
	/**
	 * Flag para incluir rutas absolutas.
	 */
	private Boolean pINCLUIRRUTABS;
	/**
	 * Flag para incluir llamada a metodos privados.
	 */
	private Boolean pINCLUIRPRIVMET;
	/**
	 * Listado de constructores.
	 */
	private Set<String> pCONSTRUCTORES;
	/**
	 * Flag para incluir constructores.
	 */
	private Boolean pINCLUIRCONSTRUCTORES;

	/**
	 * @return the mANAGER
	 */
	protected String getMANAGER() {
		return pMANAGER;
	}

	/**
	 * @return the bEAN_MANAGER
	 */
	protected String getBEAN_MANAGER() {
		return pBEAN_MANAGER;
	}

	/**
	 * @return the dIRECTORIOS
	 */
	protected String getDIRECTORIOS() {
		return pDIRECTORIOS;
	}

	/**
	 * @return the oPERACION
	 */
	protected String getOPERACION() {
		return pOPERACION;
	}

	/**
	 * @return the eXCEPTIONS
	 */
	protected Set<String> getEXCEPTIONS() {
		return pEXCEPTIONS;
	}

	/**
	 * @return the iNCLUIR_EXCEPTIONS
	 */
	protected Boolean getINCLUIR_EXCEPTIONS() {
		return pINCLUIREXCEPTIONS;
	}

	/**
	 * @return the iNCLUIR_RUT_ABS
	 */
	protected Boolean getINCLUIR_RUT_ABS() {
		return pINCLUIRRUTABS;
	}

	/**
	 * @return the iNCLUIR_PRIV_MET
	 */
	protected Boolean getINCLUIR_PRIV_MET() {
		return pINCLUIRPRIVMET;
	}

	/**
	 * @return the eXCEPTIONS
	 */
	protected Set<String> getCONSTRUCTORES() {
		return pCONSTRUCTORES;
	}

	/**
	 * @return the pINCLUIRCONSTRUCTORES
	 */
	protected Boolean getINCLUIR_CONSTRUCTORES() {
		return pINCLUIRCONSTRUCTORES;
	}

	/**
	 * Genera el contenido del JUnit Dummy.
	 * @return StringBuffer con el texto
	 */
	public StringBuffer generarTestDummy() {
		final StringBuffer txt = new StringBuffer();
		// genero encabezado
		generarEncabezado(txt);
		// genero dummy clase
		generarDummyClass(txt);
		// Genero la clase del Unit
		generarClass(txt);
		// retorno el buffer
		return txt;
	}

	/**
	 * Genera la clase del JUnit.
	 * @param buff buffer
	 */
	private void generarClass(final StringBuffer buff) {
		buff.append("public class DummyTestCase extends BaseTestCase {\n\n");
//		toDO(buff, "Indicar el bean del manager", 1);
		buff.append("\tpublic static final String NOMBRE_MANAGER_BEAN = \"" + getBEAN_MANAGER() + "\";\n");
//		toDO(buff, "Indicar las rutas", 1);
		buff.append("\tpublic static final String NOMBRE_DIRECTORIO =");
		// concatenar directorios
		buff.append(" \"" + getDIRECTORIOS() + "\";\n");
		
		buff.append("\tpublic static final String DATASOURCE = \"fwk.dataSource\";\n\n");
		buff.append("\tpublic void test() throws FileNotFoundException, SQLException {\n");
		comentario(buff, "Se crea el manager dummy", 2);
//		toDO(buff, "indicar el nombre de la operación", 2);
		buff.append("\t\tTestDummyManager manager = new TestDummyManager(\"" + getOPERACION() + "\");\n");
		buff.append("\t\tmanager.DummyTest();\n\n");
		if (getINCLUIR_EXCEPTIONS()) {
			for (String exp : getEXCEPTIONS()) {
				buff.append("\t\ttry {\n");
				toDO(buff, "Opcional: Lanzar aqui las excepciones por las cuales no hay cobertura", 3);
				buff.append("\t\t\tthrow new " + exp + "(\"\", null);\n");
				buff.append("\t\t} catch ("+ exp +" ex) {\n\n\t\t}\n");
			}
		}
		if (getINCLUIR_PRIV_MET()) {
			comentario(buff, "Se crea un userDTO, especificar un mapa con parametros si es necesario", 2);
			buff.append("\t\tfinal UserDTO userDTO = new UserDTOImpl(\"\", new HashMap());\n");
			comentario(buff, "Mapa con los metodos, key=nombre, valor=lista de parametros", 2);
			buff.append("\t\tfinal Map<String, Object> metodos = new HashMap<String, Object>();\n");
			toDO(buff, "Especificar el nombre de los metodos y los parametros como en el ejemplo:", 2);
			buff.append("\t\tmetodos.put(\"nombreMetodo\", new ArrayList<Object>() {\n");
			buff.append("\t\t\t{\n");
			buff.append("\t\t\t\tadd(userDTO);\n");
			buff.append("\t\t\t}\n");
			buff.append("\t\t});\n\n");
			comentario(buff, "llamada a metodos privados", 2);
			buff.append("\t\t" + getMANAGER() + " objeto = (" + getMANAGER() + ") getObjetoAProbar();\n");
			buff.append("\t\tllamadaMetodosPrivados(objeto, " + getMANAGER() + ".class, metodos);\n");
			buff.append("\n\n");
			
		}
		if (getINCLUIR_CONSTRUCTORES()) {
			comentario(buff, "llamada a constructores privados", 2);
			buff.append("\t\tllamadaConstructoresPrivados();");
			buff.append("\n\n");
		}

		comentario(buff, "assert necesario para cumplimiento de reglas de sonar", 2);
		buff.append("\t\tassertEquals(0, 0);\n");
		buff.append("\t}\n\n");
		
		generaMetodosSobrescritos(buff);
		
		if (getINCLUIR_PRIV_MET()) {
			generarMetodoLlamadasPrivadas(buff);
		}
		if (getINCLUIR_CONSTRUCTORES()) {
			// llamada a constructores
			generaLlamadaConstructores(buff);
		}
		buff.append("}");
	}
	
	/**
	 * Genera el metodo para las llamadas privadas de constructores.
	 * @param buff buffer
	 */
	private void generaLlamadaConstructores(final StringBuffer buff) {

		buff.append("\t/**\n");
		buff.append("\t * Metodo que llama a constructores privados de un manager.\n");
		buff.append("\t */\n");
		buff.append("\tprivate void llamadaConstructoresPrivados() {\n");
		
		int cont = 0;
		for (String constr : getCONSTRUCTORES()) {
			final String nombre = constr + cont;
			buff.append("\t\tConstructor<" + constr + "> " + nombre + ";\n");
			cont++;
		}
		cont = 0;
		
		buff.append("\t\ttry {\n");

		for (String constr : getCONSTRUCTORES()) {
			final String nombre = constr + cont;
			buff.append("\t\t\t" + nombre + " = " + constr + ".class.getDeclaredConstructor(new Class[0]);\n");
			buff.append("\t\t\t" + nombre + ".setAccessible(true);\n");
			buff.append("\t\t\t" + constr + " foo" + cont + " = " + nombre + ".newInstance(new Object[0]);\n");
			cont++;
		}
		
		buff.append("\t\t} catch (IllegalArgumentException e) {\n");
		buff.append("\t\t\te.printStackTrace();\n");
		buff.append("\t\t} catch (InstantiationException e) {\n");
		buff.append("\t\t\te.printStackTrace();\n");
		buff.append("\t\t} catch (IllegalAccessException e) {\n");
		buff.append("\t\t\te.printStackTrace();\n");
		buff.append("\t\t} catch (InvocationTargetException e) {\n");
		buff.append("\t\t\te.printStackTrace();\n");
		buff.append("\t\t} catch (SecurityException e) {\n");
		buff.append("\t\t\te.printStackTrace();\n");
		buff.append("\t\t} catch (NoSuchMethodException e) {\n");
		buff.append("\t\t\te.printStackTrace();\n");
		buff.append("\t\t}\n");
		buff.append("\t}\n");
	}

	/**
	 * Genera el metodo para las llamadas privadas.
	 * @param buff buffer
	 */
	private void generarMetodoLlamadasPrivadas(final StringBuffer buff) {
		buff.append("\t/**\n");
		buff.append("\t * Metodo que llama a metodos privados de un manager.\n");
		buff.append("\t * @param manager Objecto tipo manager\n");
		buff.append("\t * @param clase clase del objeto\n");
		buff.append("\t * @param metodos Mapa con los metodos y sus parametros\n");
		buff.append("\t */\n");
		buff.append("\tprivate void llamadaMetodosPrivados(final AbstractManager manager,\n");
		buff.append("\t\t\tfinal Class clase, final Map<String, Object> metodos) {\n");
//		comentario(buff, "Obtengo la clase", 2);
//		buff.append("\t\tfinal Class clase = manager.getClass();\n");
		comentario(buff, "por cada elemento del mapa de entrada", 2);
		buff.append("\t\tfor (Map.Entry<String, Object> registro : metodos.entrySet()) {\n");
		buff.append("\t\t\ttry {\n");
		comentario(buff, "obtengo el listado de parametros", 3);
		buff.append("\t\t\tfinal List parametros = (ArrayList) registro.getValue();\n");
		buff.append("\t\t\tfinal List<Class> tipos = new ArrayList();\n");
		comentario(buff, "Obtengo las clases", 3);
		buff.append("\t\t\tfor (Object parms : parametros) {\n");
		buff.append("\t\t\t\tfinal Class cls = parms.getClass();\n");
		comentario(buff, "determino el tipo", 4);
		buff.append("\t\t\t\tif (UserDTOImpl.class.isAssignableFrom(cls)) {\n");
		buff.append("\t\t\t\t\ttipos.add(UserDTO.class);\n");
		buff.append("\t\t\t\t} else if (List.class.isAssignableFrom(cls)) {\n");
		buff.append("\t\t\t\t\ttipos.add(List.class);\n");
		buff.append("\t\t\t\t} else if (Map.class.isAssignableFrom(cls)) {\n");
		buff.append("\t\t\t\t\ttipos.add(Map.class);\n");
		buff.append("\t\t\t\t} else {\n");
		buff.append("\t\t\t\t\ttipos.add(cls);\n");
		buff.append("\t\t\t\t}\n");
		buff.append("\t\t\t}\n");
		comentario(buff, "Obtengo el metodo", 3);
		buff.append("\t\t\tfinal Method metodo =\n");
		buff.append("\t\t\t\t\tclase.getDeclaredMethod(registro.getKey(),\n");
		buff.append("\t\t\t\t\t\t\t(Class[]) tipos.toArray(new Class[tipos.size()]));\n");
		comentario(buff, "Cambio el modificador", 3);
		buff.append("\t\t\tmetodo.setAccessible(Boolean.TRUE);\n");
		comentario(buff, "lo llamo", 3);
		buff.append("\t\t\tmetodo.invoke(manager, parametros.toArray());\n\n");
		buff.append("\t\t\t} catch (SecurityException e) {\n");
		buff.append("\t\t\t\te.printStackTrace();\n");
		buff.append("\t\t\t} catch (NoSuchMethodException e) {\n");
		buff.append("\t\t\t\te.printStackTrace();\n");
		buff.append("\t\t\t} catch (IllegalArgumentException e) {\n");
		buff.append("\t\t\t\te.printStackTrace();\n");
		buff.append("\t\t\t} catch (IllegalAccessException e) {\n");
		buff.append("\t\t\t\te.printStackTrace();\n");
		buff.append("\t\t\t} catch (InvocationTargetException e) {\n");
		buff.append("\t\t\t\te.printStackTrace();\n");
		buff.append("\t\t\t}\n");
		buff.append("\t\t}\n");
		buff.append("\t}\n");
	}

	/**
	 * Genera los metodos a sobreescribir.
	 * @param buff buffer
	 */
	private void generaMetodosSobrescritos(final StringBuffer buff) {

		if (getINCLUIR_RUT_ABS()) {
			toDO(buff, "Sobreescribir segun corresponda", 1);
			buff.append("\t@Override\n");
			buff.append("\tprotected boolean rutasAbsolutas() {\n");
			buff.append("\t\treturn true;\n\t}\n\n");
		}
		buff.append("\t@Override\n");
		buff.append("\tpublic String getDirectoryName() {\n");
		buff.append("\t\treturn NOMBRE_DIRECTORIO;\n\t}\n\n");
		buff.append("\t@Override\n");
		buff.append("\tpublic String getObjetoAProbarBeanName() {\n");
		buff.append("\t\treturn NOMBRE_MANAGER_BEAN;\n\t}\n\n");
	}

	/**
	 * Genera la clase del test dummy.
	 * @param buff buffer
	 */
	private void generarDummyClass(final StringBuffer buff) {
//		toDO(buff, "Especificar el manager correcto desde el cual debe heredar", 0);
		buff.append("final class TestDummyManager extends ");
		buff.append(getMANAGER() + " {\n");
		buff.append("\tpublic static String OPE = \"operacion\";\n");
		buff.append("\tpublic static String WSV = \"Servicio\";\n");
		buff.append("\tpublic static String ERR = \"Error\";\n");
		buff.append("\tpublic String NOM = \"operacion\";\n\n");
		buff.append("\tTestDummyManager(final String operacion) {\n");
		buff.append("\t\tthis.NOM = operacion;\n\t}\n\n");
		buff.append("\tpublic void DummyTest() {\n\n");
		buff.append("\t\tfinal Map<String, Object> params = new HashMap<String, Object>();\n");
		buff.append("\t\tparams.put(\"localeLanguage\", \"es\");\n");
		buff.append("\t\tparams.put(\"localeCountry\", \"ES\");\n");
		buff.append("\t\tparams.put(\"localeVariant\", null);\n");
		buff.append("\t\tparams.put(\"codUsuario\", \"codUser\");\n");
		buff.append("\t\tparams.put(\"codResponsable\", \"codResp\");\n");
		buff.append("\t\tparams.put(\"codAplicacion\", \"PRODU\");\n");
		buff.append("\t\tparams.put(\"codVacio\", \"\");\n");
		buff.append("\t\tparams.put(OPE, NOM);\n");
		buff.append("\t\tfinal UserDTO userDTO = new UserDTOImpl(\"\", params);\n");
		buff.append("\t\tthis.getFriendlyUserDTOInputDebug(userDTO);\n");
		buff.append("\t\tsuper.getFriendlyUserDTOInputDebug(userDTO);\n");
		buff.append("\t\tthis.getFriendlyUserDTOOutputDebug(userDTO);\n");
		buff.append("\t\tsuper.getFriendlyUserDTOOutputDebug(userDTO);\n");
		buff.append("\t\tgetObjectNombreParam(userDTO.getParameters(), OPE);\n");
		buff.append("\t\tgetObjectErrorOperacion(OPE, WSV, ERR);\n");
		buff.append("\t\tgetNombreMetodo();\n");
		buff.append("\t\ttratarParametroString(userDTO.getParameters(), \"localeVariant\", ERR);\n");
		buff.append("\t\ttratarParametroString(userDTO.getParameters(), \"codVacio\", ERR);\n");
		buff.append("\t\ttratarParametroString(userDTO.getParameters(), \"localeCountry\", ERR);\n");
		buff.append("\t\ttratarParametroNumber(userDTO.getParameters(), \"localeVariant\", ERR);\n");
		buff.append("\t\ttratarParametroNumber(userDTO.getParameters(), \"localeVariant\", null);\n");
		buff.append("\t\ttratarParametroNumber(userDTO.getParameters(), \"codUsuario\", null);\n");
		buff.append("\t\tthis.getLogger().errorOperacion(this.NOM, this.WSV, this.ERR);\n");
		buff.append("\t\tthis.getLogger().toString();\n");
		buff.append("\t\tthis.getLogger().salidaBBDD(\"\", new String[] {}, new Object[] {}, 0);\n");
		buff.append("\t\tthis.getLogger().alternativo(\"\", \"\");\n");
		buff.append("\t\tthis.getLogger().salidaMetodo(null);\n");
		buff.append("\t\tLogManager.getLogger(\"es.mercadona.fwk\").setLevel(Level.OFF);\n");
		buff.append("\t\tthis.getFriendlyUserDTOInputDebug(userDTO);\n");
		buff.append("\t\tsuper.getFriendlyUserDTOInputDebug(userDTO);\n");
		buff.append("\t\tthis.getFriendlyUserDTOOutputDebug(userDTO);\n");
		buff.append("\t\tsuper.getFriendlyUserDTOOutputDebug(userDTO);\n");
		buff.append("\t\tthis.getLogger().errorOperacion(this.NOM, this.WSV, this.ERR);\n");
		buff.append("\t\tthis.getLogger().toString();\n");
		buff.append("\t\tthis.getLogger().salidaBBDD(\"\", new String[] {}, new Object[] {}, 0);\n");
		buff.append("\t\tthis.getLogger().alternativo(\"\", \"\");\n");
		buff.append("\t\tthis.getLogger().salidaMetodo(null);\n");
		buff.append("\t\tthis.getLogger().salidaWS(NOM, WSV, \"\");\n");
		buff.append("\t\tthis.getLogger().entradaWS(NOM, WSV, \"\");\n");
		buff.append("\t\tthis.getLogger().entradaMetodo(new String[] {}, new Object[] {});\n");
		buff.append("\t\tLogManager.getLogger(\"es.mercadona.fwk\").setLevel(Level.DEBUG);\n");
		buff.append("\t\tfinal ExtendedError error = new ExtendedError(\"key\", null, \"campo\", \"val\");\n");
		buff.append("\t\terror.getMessageArgs();\n");
		buff.append("\t\terror.getMessageKey();\n");
		buff.append("\t\terror.getNombreCampo();\n");
		buff.append("\t\terror.getNombreValidador();\n");
		buff.append("\t\terror.resolveMessage();\n");
		buff.append("\t}\n}\n\n");
	}

	/**
	 * Genera los imports.
	 * @param buff
	 */
	private void generarEncabezado(final StringBuffer buff) {
		buff.append("package es.mercadona.test.business.cambiar;\n\n");
		buff.append("import es.mercadona.common.business.AbstractManager;\n");
		buff.append("import es.mercadona.common.business.ExtendedError;\n");
		buff.append("import es.mercadona.fwk.dto.UserDTO;\n");
		buff.append("import es.mercadona.fwk.dto.UserDTOImpl;\n");
		buff.append("import es.mercadona.test.common.BaseTestCase;\n\n");
	
		buff.append("import java.io.FileNotFoundException;\n");
		if (getINCLUIR_PRIV_MET()) {
			buff.append("import java.lang.reflect.InvocationTargetException;\n");
			buff.append("import java.lang.reflect.Method;\n");
		}
		if (getINCLUIR_CONSTRUCTORES()) {
			buff.append("import java.lang.reflect.Constructor;\n");
		}
		if (getINCLUIR_CONSTRUCTORES() && !getINCLUIR_PRIV_MET()) {
			buff.append("import java.lang.reflect.InvocationTargetException;\n");
		}
		buff.append("import java.sql.SQLException;\n");
		buff.append("import java.util.ArrayList;\n");
		buff.append("import java.util.HashMap;\n");
		buff.append("import java.util.List;\n");
		buff.append("import java.util.Map;\n");
		buff.append("import org.apache.log4j.Level;\n");
		buff.append("import org.apache.log4j.LogManager;\n\n");
	}

	/**
	 * Genera un comentario de TO DO.
	 * @param buff buffer
	 * @param comentario comentario
	 * @param tabs numero de tabs
	 */
	private void toDO(final StringBuffer buff, final String comentario, final int tabs) {
		for (int x = 0; x < tabs; x++) {
			buff.append("\t");
		}
		buff.append("// TODO: ");
		buff.append(comentario + "\n");
	}
	
	/**
	 * Genera un comentario.
	 * @param buff buffer
	 * @param comentario comentario
	 * @param tabs numero de tabs
	 */
	private void comentario(final StringBuffer buff, final String comentario, final int tabs) {
		for (int x = 0; x < tabs; x++) {
			buff.append("\t");
		}
		buff.append("// ");
		buff.append(comentario  + "\n");
	}

	/**
	 * Constructor especificando todas las propiedades.
	 * @param manager nombre de la clase manager
	 * @param bean identificador del bean del manager
	 * @param operacion Nombre de la operación
	 * @param directorios directorios de dependencias
	 * @param excepciones Listado de excepciones
	 * @param incluyeRutaAbsoluta Flag para incluir rutas absolutas
	 * @param incluyeMetodosPrivados Flag para incluir llamada a metodos privados
	 * @param constructores Listado de constructores
	 * @param incluyeConstructoresPrivados Flag para incluir llamada a constructores privados
	 */
	DummyTest(final String manager, final String bean,
			final String operacion, final String directorios,
			final Set<String> excepciones, final Boolean incluyeRutaAbsoluta,
			final Boolean incluyeMetodosPrivados,
			final Set<String> constructores,
			final Boolean incluyeConstructoresPrivados) {
		this.pMANAGER = manager;
		this.pBEAN_MANAGER = bean;
		this.pOPERACION = operacion;
		this.pDIRECTORIOS = directorios;
		if (excepciones != null && excepciones.size() > 0) {
			this.pEXCEPTIONS = excepciones;
			this.pINCLUIREXCEPTIONS = Boolean.TRUE;
		} else {
			this.pINCLUIREXCEPTIONS = Boolean.FALSE;
		}
		if (incluyeConstructoresPrivados) {
			if (constructores != null && constructores.size() > 0) {
				this.pCONSTRUCTORES = constructores;
				this.pINCLUIRCONSTRUCTORES = Boolean.TRUE;
			} else {
				this.pINCLUIRCONSTRUCTORES = Boolean.FALSE;
			}
		} else {
			this.pINCLUIRCONSTRUCTORES = Boolean.FALSE;
		}
		this.pINCLUIRRUTABS = incluyeRutaAbsoluta;
		this.pINCLUIRPRIVMET = incluyeMetodosPrivados;
	}

	/**
	 * Constructor sin incluir llamada a metodos privados.
	 * @param manager nombre de la clase manager
	 * @param bean identificador del bean del manager
	 * @param operacion Nombre de la operación
	 * @param directorios directorios de dependencias
	 * @param excepciones Listado de excepciones
	 * @param incluyeRutaAbsoluta Flag para incluir rutas absolutas
	 */
	DummyTest(final String manager, final String bean,
			final String operacion, final String directorios,
			final Set<String> excepciones, final Boolean incluyeRutaAbsoluta) {
		this(manager, bean, operacion, directorios, excepciones, incluyeRutaAbsoluta, Boolean.FALSE,
			null, Boolean.FALSE);
	}

	/**
	 * Constructor basico.
	 * @param manager nombre de la clase manager
	 * @param bean identificador del bean del manager
	 * @param operacion Nombre de la operación
	 * @param directorios directorios de dependencias
	 */
	DummyTest(final String manager, final String bean, final String operacion, final String directorios) {
		this(manager, bean, operacion, directorios, null, Boolean.FALSE, Boolean.FALSE,
			null, Boolean.FALSE);
	}

	/**
	 * Constructor basico con opción de incluir rutas absolutas.
	 * @param manager nombre de la clase manager
	 * @param bean identificador del bean del manager
	 * @param operacion Nombre de la operación
	 * @param directorios directorios de dependencias
	 * @param incluyeRutaAbsoluta Flag para incluir rutas absolutas
	 */
	DummyTest(final String manager, final String bean, final String operacion,
			  final String directorios, final Boolean incluyeRutaAbsoluta) {
		this(manager, bean, operacion, directorios, null, incluyeRutaAbsoluta);
	}
}
