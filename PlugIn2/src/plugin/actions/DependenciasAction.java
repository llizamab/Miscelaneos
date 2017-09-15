package plugin.actions;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * Genera las exclusiones de sonar.
 * @author llizamab
 */
public class DependenciasAction extends JFrame implements IWorkbenchWindowActionDelegate  {
	/**
	 * Clase package-info.
	 */
	private static final String PACKAGES_INFO_JAVA = "/package-info.java";
	/**
	 * Ruta del proyecto.
	 */
	private static String DIR_PROYECTO = "";
	/**
	 * Fichero de constantes del fwk.
	 */
	private static final String CONST_LOGGER = "ConstLogger";
	/**
	 * Patron de fichero de constantes.
	 */
	private static final String PTR_FICH_CONST = "(Const[a-zA-Z_0-9]+)(\\.|;)";
	/**
	 * Dir punto svn.
	 */
	private static final String TXT_SVN = ".svn";
	/**
	 * Patron fin imports.
	 */
	private static final String PTR_END_IMPRT = ")(;)";
	/**
	 * Patron inicio imports.
	 */
	private static final String PTR_IMPORT_S = "(import\\s)(.+";
	/**
	 * Fichero ac-Dao.
	 */
	private static final String AC_DAO_XML = "\\ac-Dao.xml";
	/**
	 * Dir generico dao.
	 */
	private static final String DIR_SRC_DAO = "\\src\\dao\\";
	/**
	 * Dir generico business.
	 */
	private static final String DIR_SRC_BUSINESS = "\\src\\business\\";
	/**
	 * Dir fichero ac-Managers.
	 */
	private static final String AC_MANAGERS_XML2 = "\\ac-Managers.xml";
	/**
	 * Fichero ac-Managers.
	 */
	private static final String AC_MANAGERS_XML = "ac-Managers.xml";
	/**
	 * Patron de implements de interfacez.
	 */
	private static final String PTR_IMPL_INTRF = "(implements\\s)(.+)(\\s\\{)";
	/**
	 * Patron de imports de excepciones.
	 */
	private static final String PTR_IMPRT_EXC = "(import\\s)(.+Exception)(;)";
	/**
	 * Patron de clase en ficheros de inyección.
	 */
	private static final String PTR_CLASE = "(class=\")(.+)(\")";
	/**
	 * Constante package fwk.
	 */
	private static final String TXT_PCK_FWK = "es.mercadona.fwk.";
	/**
	 * Texto fin exclusion.
	 */
	private static final String TXT_END_EXCLU = ".java,\\";
	/**
	 * Texto salto de linea.
	 */
	private static final String TXT_NEW_LINE = "\n";
	/**
	 * Texto inicio exclusion. 
	 */
	private static final String TXT_INI_EXCL = "**/";
	/**
	 * Constante .java.
	 */
	private static final String PNT_JAVA = ".java";
	/**
	 * Texto error.
	 */
	private static final String TXT_ERROR = "Error";
	/**
	 * Constante con la ruta de templates.
	 */
	private static final String TEMPLATES = "\\templates\\config\\common\\internal\\war\\classes\\";
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * componente visual.
	 */
	private IWorkbenchWindow window;
	/**
	 * Label proyecto.
	 */
	private JLabel lblProyecto;
	/**
	 * textbox proyecto.
	 */
	private JTextField txtProyecto;
	/**
	 * Label directorios.
	 */
	private JLabel lblDirs;
	/**
	 * Listado directorios.
	 */
	private JList lstDirs;
	/**
	 * Textbox resultado.
	 */
	private JTextArea txtResult;
	/**
	 * Mapa de directorios.
	 */
	private Map<String, Object> directorios;
	/**
	 * Set de resultados.
	 */
	private Set<String> resultados;
	
	/**
     * Crea los componentes visuales para ejecutar el programa.
     */
    private void createAndShowGUI() {
    	setTitle("Dependencias fuentes");
    	directorios = new HashMap<String, Object>();
    	resultados = new LinkedHashSet<String>();
    	lblProyecto = new JLabel("Directorio de la petición");
    	lblDirs = new JLabel("Seleccione directorio de la petición");
    	txtProyecto = new JTextField("R:\\desarrollo\\workspace\\");
    	txtResult = new JTextArea();
    	final DefaultListModel lstModel = new DefaultListModel();
    	lstDirs = new JList(lstModel);
    	
    	final JButton btnBuscar = new JButton();
    	btnBuscar.setText("Buscar");
    	final JButton btnGnrSel = new JButton();
    	btnGnrSel.setText("<html><center>Generar<br />dependencias</center></html>");
    	
    	final JButton btnCopy = new JButton();
    	btnCopy.setText("<html><center>Copiar al<br />portapapeles</center></html>");
    	final JButton btnLimpiar = new JButton();
    	btnLimpiar.setText("Limpiar");
    	final JButton btnLimpAll = new JButton();
    	btnLimpAll.setText("Limpiar Todo");
    	
    	// addActionListener  ...
    	btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				btnBuscarClick(arg0);
			}
		});

    	btnGnrSel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				btnGnrSelClick(arg0);
			}
		});

    	btnCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				final StringSelection ss = new StringSelection(txtResult.getText());
			    clipboard.setContents(ss, null);
			}
		});
    	
    	btnLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				txtResult.setText("");
				resultados = new LinkedHashSet<String>();
			}
		});
    	
    	btnLimpAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				txtResult.setText("");
				txtProyecto.setText("R:\\desarrollo\\workspace\\");
				directorios.clear();
				resultados = new LinkedHashSet<String>();
				final DefaultListModel listModel = (DefaultListModel) lstDirs.getModel();
				listModel.removeAllElements();
			}
		});
    	// genero interfaz
    	generarInterfaz(btnBuscar, btnGnrSel,
			btnCopy, btnLimpiar, btnLimpAll);
    }

	/**
	 * Genera la interfaz.
	 * @param btnBuscar btn buscar.
	 * @param btnGnrSel btn sel.
	 * @param btnCopy btn copy.
	 * @param btnLimpiar btn clean.
	 * @param btnLimpAll btn clean all.
	 */
	private void generarInterfaz(final JButton btnBuscar, final JButton btnGnrSel,
		final JButton btnCopy, final JButton btnLimpiar,
		final JButton btnLimpAll) {
		final GroupLayout grupo = new GroupLayout(getContentPane());
        setLayout(grupo);
        // contenedor del listado de directorios
        final JScrollPane scrollDirs = new JScrollPane(lstDirs);
        scrollDirs.setVerticalScrollBarPolicy(
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollDirs);
        // contenedor del resultado final
        final JScrollPane scrollRst = new JScrollPane(txtResult);
        scrollRst.setVerticalScrollBarPolicy(
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollRst);
        // agrupacion horizontal
        grupo.setHorizontalGroup(grupo.createSequentialGroup()
    		.addGroup(grupo.createParallelGroup()
    			.addComponent(lblProyecto)
    			.addGroup(grupo.createSequentialGroup()
					.addComponent(txtProyecto)
					.addComponent(btnBuscar))
				.addComponent(lblDirs)
				.addGroup(grupo.createSequentialGroup()
					.addComponent(scrollDirs)
					.addGroup(grupo.createParallelGroup()
						.addComponent(btnGnrSel)
						.addComponent(btnCopy)
						.addComponent(btnLimpiar)
						.addComponent(btnLimpAll))
				))
    		.addComponent(scrollRst)
        );
        
        // agrupacion vertical
        grupo.setVerticalGroup(grupo.createSequentialGroup()
    		.addGroup(grupo.createParallelGroup()
				.addGroup(grupo.createSequentialGroup()
					.addComponent(lblProyecto)
					.addGroup(grupo.createParallelGroup()
						.addComponent(txtProyecto)
						.addComponent(btnBuscar))
					.addComponent(lblDirs)
					.addGroup(grupo.createParallelGroup()
						.addComponent(scrollDirs)
						.addGroup(grupo.createSequentialGroup()
							.addComponent(btnGnrSel)
							.addComponent(btnCopy)
							.addComponent(btnLimpiar)
							.addComponent(btnLimpAll)))
					)
				.addComponent(scrollRst))
    		);
		
        final Dimension size = new Dimension(1000, 600);
        setMinimumSize(size);
        setMaximumSize(size);
        
        // tamaños ...
        grupo.linkSize(SwingConstants.HORIZONTAL, btnBuscar, btnGnrSel,
        		btnCopy, btnLimpiar, btnLimpAll);
        grupo.linkSize(SwingConstants.HORIZONTAL, txtProyecto, scrollDirs);
        
        grupo.linkSize(SwingConstants.VERTICAL, txtProyecto, btnBuscar);
        
        grupo.linkSize(SwingConstants.VERTICAL, btnGnrSel,
        		btnCopy, btnLimpiar, btnLimpAll);
        
        grupo.setAutoCreateGaps(true);
        grupo.setAutoCreateContainerGaps(true);
        setLocationByPlatform(true);
	}
   
    /**
     * Evento click del boton buscar.
     * @param arg0 arg0.
     */
    private void btnBuscarClick(final ActionEvent arg0) {
    	// valido texto
    	final String dir = txtProyecto.getText();
    	if (dir != null && !dir.trim().isEmpty()) {
    		// busco que exista el dir
    		final File f = new File(dir);
    		if (f.exists() && f.isDirectory()) {
    			// voy a buscar los directorios 
    			if (!buscarDirectorios(dir)) {
    				// error
    				JOptionPane.showMessageDialog(null, 
	    				"No se han encontrado ficheros de inyección en " + dir
	    				+ TEMPLATES, TXT_ERROR, 
	    				JOptionPane.ERROR_MESSAGE);  
    			}
    		} else {
        		// error
        		JOptionPane.showMessageDialog(null, 
    				"No existe el proyecto: " + dir, TXT_ERROR, 
    				JOptionPane.ERROR_MESSAGE);    			
    		}
    	} else {
    		// error
    		JOptionPane.showMessageDialog(null, 
				"Debe especificar el directorio de la petición", TXT_ERROR, 
				JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    /**
     * Busca los ficheros de inyección para cargar el listBox.
     * @param proyecto Ruta del proyecto
     * @return FALSE=no encontro directorios, TRUE=encontro directorios.
     */
    private boolean buscarDirectorios(final String proyecto) {
    	Boolean flag = Boolean.FALSE;
		// me muevo a la ruta TEMPLATES
    	final String rutaCompleta = proyecto + TEMPLATES;
    	final File f = new File(rutaCompleta);
    	if (f.exists() && f.isDirectory()) {
    		final DefaultListModel listModel = (DefaultListModel) lstDirs.getModel();
    		listModel.removeAllElements();
    		directorios.clear();
    		resultados.clear();
    		txtResult.setText("");
    		DIR_PROYECTO = txtProyecto.getText();
    		// busco ficheros de inyeccion
    		final File[] files = f.listFiles();
    		// por cada ruta encontrada
    		for (File file : files) {
    	        if (file.isDirectory() && !TXT_SVN.equals(file.getName())) {
    	        	Boolean esDirInyeccion = Boolean.FALSE;
    	        	for (File xmls : file.listFiles()) {
    	        		// si existe un ac-Managers.xml
    	        		if (AC_MANAGERS_XML.equals(xmls.getName())) {
    	        			esDirInyeccion = Boolean.TRUE;
    	        			break;
    	        		}
    	        	}
    	        	// si encontro inyeccion
    	        	if (esDirInyeccion) {
    	        		flag = Boolean.TRUE;
    	        		// la agrego al listbox
    					listModel.addElement(file.getName());
    	        		// la agrego al mapa(carpeta, ruta)
    					directorios.put(file.getName(), file);
    	        	}
    	        }
    		}
    	}
    	return flag;
    }
    
    /**
     * Lee los ficheros de inyeccion de un dir.
     * @param directorio directorio.
     */
    private void procesarDirectorio(final File directorio) {
    	// agrego encabezado
    	resultados.add("## INI Dependencias del servicio: "+ directorio.getName() +"  ##" + TXT_NEW_LINE);
    	final List<String> operaciones = new LinkedList<String>();
		final List<String> id_operaciones = new LinkedList<String>();
		
    	// leo el ac-WebServicesDefinitions.xml
    	final File acWeb = new File(directorio.getAbsolutePath() + "\\ac-WebServicesDefinitions.xml");
    	if (acWeb.exists()) {
    		try {
    			final String acWebTxt = readFileAsString(
    					acWeb.getAbsolutePath());
    			// obtengo las operaciones
    			Pattern patron = Pattern.compile("(entry key=\")(.+)(\")");
    			Matcher matcher = patron.matcher(acWebTxt);
    			while (matcher.find()) {
    				operaciones.add(matcher.group(2));
    			}
    			// obtengo id
    			patron = Pattern.compile("(ref bean=\")(.+)(\")");
    			matcher = patron.matcher(acWebTxt);
    			while (matcher.find()) {
    				id_operaciones.add(matcher.group(2));
    			}
    		} catch (IOException e) {
    			e.getLocalizedMessage();
    			return;
    		}
    	}
    	// leo el BusinessOperationsDirectory.xml
    	String acBusinTxt = null;
    	final File acBusin = new File(directorio.getAbsolutePath() + "\\BusinessOperationsDirectory.xml");
    	if (acBusin.exists()) {
    		try {
    			acBusinTxt = readFileAsString(
					acBusin.getAbsolutePath());
    		} catch (IOException e) {
    			e.getLocalizedMessage();
    			return;
    		}
    	}
    	// leo al ac-Managers.xml
    	String acManTxt = null;
    	final File acMan = new File(directorio.getAbsolutePath() + "\\ac-Managers.xml");
    	if (acMan.exists()) {
    		try {
    			acManTxt = readFileAsString(
					acMan.getAbsolutePath());
    		} catch (IOException e) {
    			e.getLocalizedMessage();
    			return;
    		}
    	}
    	// leo el ac-Dao.xml
    	String acDaoTxt = null;
    	final File acDao = new File(directorio.getAbsolutePath() + "\\ac-Dao.xml");
    	if (acDao.exists()) {
    		try {
    			acDaoTxt = readFileAsString(
					acDao.getAbsolutePath());
    		} catch (IOException e) {
    			e.getLocalizedMessage();
    			return;
    		}
    	}
    	// recorro las operaciones
    	int cont = 0;
    	for (cont = 0; cont < operaciones.size(); cont++) {
    		// obtengo oper
    		final String operacion = operaciones.get(cont);
    		// obtengo id
    		final String idOpe = id_operaciones.get(cont);
    		// concateno
    		resultados.add("Operación: " + operacion + TXT_NEW_LINE);
    		resultados.add("Id: " + idOpe + TXT_NEW_LINE);
    		// busco el manager en el business
    		String manager = null;
    		Pattern patron = Pattern.compile("(id=\"" + idOpe + "\"(?s).{0,10}manager=\"#)(.+)(\")");
			Matcher matcher = patron.matcher(acBusinTxt);
			while (matcher.find()) {
				manager = matcher.group(2);
				break;
			}
			if (manager != null) {
				String claseManager = null;
	    		// busco en el ac-Managers.xml
				patron = Pattern.compile("(bean id=\"" + manager + "\"(?s).{0,100})(class=\")(.+)(\")");
				matcher = patron.matcher(acManTxt);
				while (matcher.find()) {
					// obtengo la clase
					claseManager = matcher.group(3);
					break;
				}
				resultados.add(manager + " - " + claseManager + TXT_NEW_LINE);
				// obtengo las properties
				
				patron = Pattern.compile("(bean id=\"" + manager + "\"(?s).{0,100})(class=\")(.+)(\")");
				matcher = patron.matcher(acManTxt);
				while (matcher.find()) {
					
				}
				
				
			}
    	}
    	// agrego pie
    	resultados.add("## FIN Dependencias del servicio: "+ directorio.getName() +"  ##" + TXT_NEW_LINE  + TXT_NEW_LINE);
    }

    /**
     * Evento click del boton generar para seleccionados.
     * @param arg0 arg.
     */
    private void btnGnrSelClick(final ActionEvent arg0) {
    	// limpio resultados
    	resultados.clear();
    	// Obtengo los seleccionados
    	final Object[] dirs = lstDirs.getSelectedValues();
    	if (dirs.length > 0) {
	    	for (Object dir : dirs) {
	    		// Obtengo el file
	    		final File file = (File) directorios.get(dir);
	    		// procesar
	    		procesarDirectorio(file);
	    	}
	    	// genero el texto
	    	generarResultado();
    	} else {
    		// error
    		JOptionPane.showMessageDialog(null, 
				"No se ha seleccionado ningun elemento", TXT_ERROR, 
				JOptionPane.ERROR_MESSAGE);
    	}
    }

    /**
     * Genera el result.
     */
    private void generarResultado() {
    	final StringBuffer buffer = new StringBuffer();
    	txtResult.setText("");
    	for (String result : resultados) {
    		buffer.append(result);
    	}
    	txtResult.setText(buffer.toString());
    }

	/**
	 * Lee el texto de un archivo.
	 * @param filePath ruta del fichero
	 * @return string con el texto del archivo
	 * @throws IOException error de IO
	 */
	public static String readFileAsString(final String filePath) throws IOException {
		final StringBuffer fileData = new StringBuffer();
		final BufferedReader reader = new BufferedReader(new FileReader(filePath));
		final char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			final String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}

	@Override
	public final void run(final IAction arg0) {
		this.setVisible(Boolean.TRUE);
	}

	@Override
	public final void selectionChanged(final IAction arg0, final ISelection arg1) {
	}

	@Override
	public final void init(final IWorkbenchWindow arg0) {
		this.window = arg0;
		createAndShowGUI();
	}

}
