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
public class ExclusionsAction extends JFrame implements IWorkbenchWindowActionDelegate  {
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
    	setTitle("Sonar Exclusiones");
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
    	btnGnrSel.setText("<html><center>Generar para<br />el<br />seleccionado</center></html>");
    	final JButton btnGnrNoSel = new JButton();
    	btnGnrNoSel.setText("<html><center>Generar para<br />no<br />seleccionados</center></html>");
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

    	btnGnrNoSel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				btnGnrNoSelClick(arg0);
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
    	generarInterfaz(btnBuscar, btnGnrSel, btnGnrNoSel,
			btnCopy, btnLimpiar, btnLimpAll);
    }

	/**
	 * Genera la interfaz.
	 * @param btnBuscar btn buscar.
	 * @param btnGnrSel btn sel.
	 * @param btnGnrNoSel btn no sel.
	 * @param btnCopy btn copy.
	 * @param btnLimpiar btn clean.
	 * @param btnLimpAll btn clean all.
	 */
	private void generarInterfaz(final JButton btnBuscar, final JButton btnGnrSel,
		final JButton btnGnrNoSel, final JButton btnCopy, final JButton btnLimpiar,
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
						.addComponent(btnGnrNoSel)
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
							.addComponent(btnGnrNoSel)
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
        		btnGnrNoSel, btnCopy, btnLimpiar, btnLimpAll);
        grupo.linkSize(SwingConstants.HORIZONTAL, txtProyecto, scrollDirs);
        
        grupo.linkSize(SwingConstants.VERTICAL, txtProyecto, btnBuscar);
        
        grupo.linkSize(SwingConstants.VERTICAL, btnGnrSel,
        		btnGnrNoSel, btnCopy, btnLimpiar, btnLimpAll);
        
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
    	// leo el ac-Managers.xml
    	final File acManagers = new File(directorio.getAbsolutePath() + AC_MANAGERS_XML2);
    	if (acManagers.exists()) {
    		try {
    			final String acManagerTxt = readFileAsString(
    					acManagers.getAbsolutePath());
		    	// busco las clases
    			final Pattern patron = Pattern.compile(PTR_CLASE);
    			final Matcher matcher = patron.matcher(acManagerTxt);
    			final Set<String> clases = new LinkedHashSet<String>();
    			final Set<String> managers = new LinkedHashSet<String>();
    			final Set<String> packsInf = new LinkedHashSet<String>();
    			while (matcher.find()) {
    				final String clase = matcher.group(2).replace('.', '/');
    				clases.add(TXT_INI_EXCL + clase + PNT_JAVA);
    				managers.add(DIR_PROYECTO + DIR_SRC_BUSINESS 
    						+ matcher.group(2).replace('.', '\\') + PNT_JAVA);
    				// add dir para los packages-info
    				packsInf.add(TXT_INI_EXCL + clase.substring(0, clase.lastIndexOf('/'))
						+ PACKAGES_INFO_JAVA);
    			}
    			// agrego a resultados las clases
    			for (String text : clases) {
    				resultados.add(text);
    			}
    			// agrego a resultados los info
    			for (String text : packsInf) {
    				resultados.add(text);
    			}
    			packsInf.clear();
    			// set de excepciones
    			final Set<String> exceptions = new LinkedHashSet<String>();
    			// set de fichero constantes incluido en cada manager
    			final Set<String> fichConst = new HashSet<String>();
		    	// busco las excepciones
    			for (String manager : managers) {
    				final File mangr = new File(manager);
    				if (mangr.exists()) {
    					// leo la clase
    					final String clase = readFileAsString(
    							mangr.getAbsolutePath());
    					// busco las excepciones
    					final Pattern patronExc = Pattern.compile(PTR_IMPRT_EXC);
    	    			final Matcher matcherExc = patronExc.matcher(clase);
    	    			
    	    			while (matcherExc.find()) {
    	    				if (!matcherExc.group(2).contains(TXT_PCK_FWK)) {
    	    					final String excepcion = matcherExc.group(2).replace('.', '/');
    	    					exceptions.add(TXT_INI_EXCL + excepcion + PNT_JAVA);
    	    					// add dir para los packages-info
    	        				packsInf.add(TXT_INI_EXCL + excepcion.substring(0, excepcion.lastIndexOf('/'))
    	    						+ PACKAGES_INFO_JAVA);
    	    				}
    	    			}
    	    			// buscar ficheros de constantes
    	    			final Pattern patronConst = Pattern.compile(PTR_FICH_CONST);
    	    			final Matcher matcherConst = patronConst.matcher(clase);
    	    			// obtengo la ruta
    	    			final String rutaPck = mangr.getParent().replace(
    	    					DIR_PROYECTO + DIR_SRC_BUSINESS, "").replace(
    							'\\', '/') + '/';
    	    			// elimino la primera parte de la ruta
    	    			
    	    			while (matcherConst.find()) {
    	    				// excluyo el constloger
    	    				if (!CONST_LOGGER.equals(matcherConst.group(1))) {
	    	    				// agrego para evitar duplicidad
	    	    				fichConst.add(TXT_INI_EXCL
		    						+ rutaPck + matcherConst.group(1) + PNT_JAVA);
	    	    				// quiebro el ciclo para evitar que iterete por cada inclusion
	    	    				break;
    	    				}
    	    			}
    				}
    			}
    			// agrego a resultados las excepciones
    			for (String text : exceptions) {
    				resultados.add(text);
    			}
    			// agrego a resultados los info
    			for (String text : packsInf) {
    				resultados.add(text);
    			}
    			packsInf.clear();
    			// agrego a resultados las constantes
    			for (String text : fichConst) {
    				resultados.add(text);
    			}

    		} catch (IOException e) {
    			e.getLocalizedMessage();
    			return;
    		}
    	}
    	// leo el ac-Dao.xml
    	final File acDaos = new File(directorio.getAbsolutePath() + AC_DAO_XML);
    	if (acDaos.exists()) {
    		try {
    			final String acDaoTxt = readFileAsString(
    					acDaos.getAbsolutePath());
		    	// busco las clases
    			final Pattern patron = Pattern.compile(PTR_CLASE);
    			final Matcher matcher = patron.matcher(acDaoTxt);
    			final Set<String> clases = new LinkedHashSet<String>();
    			final Set<String> daos = new LinkedHashSet<String>();
    			final Set<String> packsInf = new LinkedHashSet<String>();
    			while (matcher.find()) {
    				final String clase = matcher.group(2).replace('.', '/');
    				clases.add(TXT_INI_EXCL + clase + PNT_JAVA);
    				daos.add(DIR_PROYECTO + DIR_SRC_DAO 
    						+ matcher.group(2).replace('.', '\\') + PNT_JAVA);
    				// add dir para los packages-info
    				packsInf.add(TXT_INI_EXCL + clase.substring(0, clase.lastIndexOf('/'))
						+ PACKAGES_INFO_JAVA);
    			}
    			// add
    			for (String text : clases) {
    				resultados.add(text);
    			}
    			// agrego a resultados los info
    			for (String text : packsInf) {
    				resultados.add(text);
    			}
    			packsInf.clear();
    			// busco las interfacez
    			final Set<String> interfacez = new LinkedHashSet<String>();
    			for (String dao : daos) {
    				final File daoFile = new File(dao);
    				if (daoFile.exists()) {
    					// leo la clase
    					final String clase = readFileAsString(
    							daoFile.getAbsolutePath());
    					// busco las excepciones
    					final Pattern patronExc = Pattern.compile(PTR_IMPL_INTRF);
    	    			final Matcher matcherExc = patronExc.matcher(clase);
    	    			while (matcherExc.find()) {
    	    				final String inter = matcherExc.group(2);
    	    				final Pattern patronInt = Pattern.compile(PTR_IMPORT_S + inter + PTR_END_IMPRT);
        	    			final Matcher matcherInt = patronInt.matcher(clase);
        	    			while (matcherInt.find()) {
        	    				final String claseDao = matcherInt.group(2).replace('.', '/');
        	    				interfacez.add(TXT_INI_EXCL + claseDao + PNT_JAVA);
        	    				// add dir para los packages-info
        	    				packsInf.add(TXT_INI_EXCL + claseDao.substring(0, claseDao.lastIndexOf('/'))
        							+ PACKAGES_INFO_JAVA);
        	    			}
    	    			}
    				}
    			}
    			// agrego a resultados
    			for (String text : interfacez) {
    				resultados.add(text);
    			}
    			// agrego a resultados los info
    			for (String text : packsInf) {
    				resultados.add(text);
    			}
    			packsInf.clear();

    		} catch (IOException e) {
    			e.getLocalizedMessage();
    			return;
    		}
    	}
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
     * Evento click del boton generar para no seleccionados.
     * @param arg0 arg.
     */
    private void btnGnrNoSelClick(final ActionEvent arg0) {
    	// Obtengo todo
    	final DefaultListModel listModel = (DefaultListModel) lstDirs.getModel();
    	final Object[] items = listModel.toArray();
    	// obtengo los seleccionados
    	final Object[] dirs = lstDirs.getSelectedValues();
    	// lista temp para los no seleccionados
    	final List<Object> lista = new ArrayList<Object>();
    	for (Object item : items) {
    		boolean flag = Boolean.TRUE;
    		// busco si esta seleccionado
    		for (Object dir : dirs) {
    			if (item.equals(dir)) {
    				flag = Boolean.FALSE;
    				break;
    			}
    		}
    		// si no está seleccionado
    		if (flag) {
    			// lo agrego
    			lista.add(item);
    		}
    	}
    	// proceso los no seleccionados
    	if (!lista.isEmpty()) {
    		// limpio resultados
        	resultados.clear();
        	// foreach
	    	for (Object dir : lista) {
	    		// Obtengo el file
	    		final File file = (File) directorios.get(dir);
	    		// procesar
	    		procesarDirectorio(file);
	    	}
	    	generarResultado();
    	} else {
    		// error
    		JOptionPane.showMessageDialog(null, 
				"No hay deseleccionado ningun elemento", TXT_ERROR, 
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
    		buffer.append(result.replace(PNT_JAVA, TXT_END_EXCLU))
    		.append(TXT_NEW_LINE);
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
