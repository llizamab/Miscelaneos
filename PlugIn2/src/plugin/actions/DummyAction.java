package plugin.actions;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class DummyAction extends JFrame implements IWorkbenchWindowActionDelegate {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 2L;
	private IWorkbenchWindow window;
	
	private JLabel lblManager;
	private JTextField txtManager;
	private JLabel lblBeanManager;
	private JTextField txtBeanManager;
	private JLabel lblOperacion;
	private JTextField txtOperacion;
	private JLabel lblRutas;
	private JTextField txtRutas;
	private JLabel lblExcepciones;
	private JTextField txtExcepciones;
	private JLabel lblConstructores;
	private JTextField txtConstructores;
	private JCheckBox chkIncluyeRutas;
	private JCheckBox chkIncluyeMetodosPrivados;
	private JCheckBox chkIncluyeConstructoresPrivados;
	private JTextArea txtCodigo;
	
	/**
     * Crea los componentes visuales para ejecutar el programa.
     */
    private void createAndShowGUI() {
    	setTitle("Generar TestDummy");
    	lblManager = new JLabel("Manager (*): ");
    	lblManager.setToolTipText("Obligatorio: Nombre de la clase manager");
    	lblBeanManager = new JLabel("Bean ID (*):");
    	lblBeanManager.setToolTipText("Obligatorio: Id del Bean de la clase manager");
    	lblOperacion = new JLabel("Operación (*):");
    	lblOperacion.setToolTipText("Nombre de la operación");
    	lblRutas = new JLabel("Directorios (*):");
    	lblRutas.setToolTipText("Obligatorio: Directorios de dependecias");
    	lblExcepciones = new JLabel("Excepciones:");
    	lblExcepciones.setToolTipText("Opcional: Listado de excepciones separadas por pipe | ");
    	chkIncluyeRutas = new JCheckBox("Incluye rutas absolutas");
    	chkIncluyeRutas.setSelected(Boolean.FALSE);
    	chkIncluyeMetodosPrivados = new JCheckBox("Incluye llamada a metodos privados");
    	chkIncluyeMetodosPrivados.setSelected(Boolean.FALSE);
    	lblConstructores = new JLabel("Constructores:");
    	lblConstructores.setToolTipText("Opcional: Listado de constructores separadas por pipe | ");
    	
    	chkIncluyeConstructoresPrivados = new JCheckBox("Incluye llamada a constructores privados");
    	chkIncluyeConstructoresPrivados.setSelected(Boolean.FALSE);
    	// Version sin black magic
    	//chkIncluyeMetodosPrivados.setVisible(Boolean.FALSE);
    	txtManager = new JTextField("", 50);
        txtManager.setToolTipText("Nombre de la clase manager");
        txtBeanManager = new JTextField(20);
        txtBeanManager.setToolTipText("Id del Bean de la clase manager");
        txtOperacion = new JTextField(20);
        txtOperacion.setToolTipText("Nombre de la operación");
        txtRutas = new JTextField(20);
        txtRutas.setToolTipText("Directorios de dependecias");
        txtExcepciones = new JTextField(20);
        txtExcepciones.setToolTipText("Listado de excepciones separadas por pipe | ");
        txtConstructores = new JTextField(20);
        txtConstructores.setText("ConstLogger");
        txtConstructores.setToolTipText("Listado de constructores separadas por pipe | ");
        txtCodigo = new JTextArea();
        

        JButton btnLimpiar = new JButton();
        btnLimpiar.setText("Limpiar");
        JButton btnAnalizar = new JButton();
        btnAnalizar.setText("Generar");
        JButton btnCopiar = new JButton();
        btnCopiar.setText("Copiar como texto");
        JButton btnCopiarAsFile = new JButton();
        btnCopiarAsFile.setText("Copiar como archivo");

        btnLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnLimpiarClick(arg0);
			}
		});
        btnAnalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAnalizarClick(arg0);
			}
		});
        btnCopiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				StringSelection ss = new StringSelection(txtCodigo.getText());
			    clipboard.setContents(ss, null);
			}
		});
        btnCopiarAsFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				
				try {
					File proxy_temp = File.createTempFile("tempdir",".dir",null);
					File temp = new File(proxy_temp.getParent(),"DummyTestCase.java");
					FileOutputStream out = new FileOutputStream(temp);
					out.write(txtCodigo.getText().getBytes());
					out.close();
					clipboard.setContents(new TextFileTransferable(temp), null);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
    	
    	GroupLayout grupo = new GroupLayout(getContentPane());
        setLayout(grupo);
        
        JScrollPane scroll = new JScrollPane(txtCodigo);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll);
        
        grupo.setHorizontalGroup(grupo.createParallelGroup()
        	.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblManager)
            		.addComponent(txtManager))
    		.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblBeanManager)
            		.addComponent(txtBeanManager))
            .addGroup(grupo.createSequentialGroup()
        			.addComponent(lblRutas)
            		.addComponent(txtRutas))
    		.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblOperacion)
            		.addComponent(txtOperacion))
    		.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblExcepciones)
            		.addComponent(txtExcepciones))
    		.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblConstructores)
            		.addComponent(txtConstructores))
        	.addGroup(grupo.createSequentialGroup()
        			.addComponent(chkIncluyeRutas))	
			.addGroup(grupo.createSequentialGroup()
        			.addComponent(chkIncluyeMetodosPrivados))
			.addGroup(grupo.createSequentialGroup()
        			.addComponent(chkIncluyeConstructoresPrivados))
			.addGroup(grupo.createSequentialGroup()
        			.addComponent(btnAnalizar)
            		.addComponent(btnCopiar)
            		.addComponent(btnLimpiar)
            		.addComponent(btnCopiarAsFile))
    		.addGroup(grupo.createSequentialGroup()
        			.addComponent(scroll))
		);
        
        grupo.setVerticalGroup(grupo.createSequentialGroup()
    		.addGroup(grupo.createParallelGroup()
				.addComponent(lblManager)
				.addComponent(txtManager))
			.addGroup(grupo.createParallelGroup()
				.addComponent(lblBeanManager)
				.addComponent(txtBeanManager))
			.addGroup(grupo.createParallelGroup()
				.addComponent(lblRutas)
				.addComponent(txtRutas))
			.addGroup(grupo.createParallelGroup()
				.addComponent(lblOperacion)
				.addComponent(txtOperacion))
			.addGroup(grupo.createParallelGroup()
				.addComponent(lblExcepciones)
				.addComponent(txtExcepciones))
			.addGroup(grupo.createParallelGroup()
				.addComponent(lblConstructores)
				.addComponent(txtConstructores))
			.addGroup(grupo.createParallelGroup()
				.addComponent(chkIncluyeRutas))
			.addGroup(grupo.createParallelGroup()
				.addComponent(chkIncluyeMetodosPrivados))
			.addGroup(grupo.createParallelGroup()
				.addComponent(chkIncluyeConstructoresPrivados))
			.addGroup(grupo.createParallelGroup()
				.addComponent(btnAnalizar)
				.addComponent(btnCopiar)
				.addComponent(btnLimpiar)
				.addComponent(btnCopiarAsFile))
			.addGroup(grupo.createParallelGroup()
				.addComponent(scroll))
		);

        Dimension size = new Dimension(700, 700);
        setMinimumSize(size);
        setMaximumSize(size);
        txtCodigo.setMinimumSize(size);
        grupo.linkSize(SwingConstants.HORIZONTAL, btnLimpiar, btnAnalizar, btnCopiar, btnCopiarAsFile);
        grupo.linkSize(SwingConstants.HORIZONTAL, lblBeanManager,
        		lblExcepciones, lblManager, lblOperacion, lblRutas, lblConstructores);
        grupo.linkSize(SwingConstants.HORIZONTAL, txtBeanManager, txtExcepciones,
        		txtManager, txtOperacion, txtRutas, txtConstructores);
        grupo.linkSize(SwingConstants.VERTICAL, txtBeanManager, txtExcepciones,
        		txtManager, txtOperacion, txtRutas, txtConstructores);

        grupo.setAutoCreateGaps(true);
        grupo.setAutoCreateContainerGaps(true);
        setLocationByPlatform(true);
    }
    
    private void btnAnalizarClick(ActionEvent arg0) {
    	// Obtengo los datos
    	String manager = txtManager.getText();
    	String bean_manager = txtBeanManager.getText();
    	String directorios = txtRutas.getText();
    	String operacion = txtOperacion.getText();
    	String excepciones = txtExcepciones.getText();
    	String constructores = txtConstructores.getText();
    	// Valido
    	if (manager == null || manager.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar un Manager", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (bean_manager == null || bean_manager.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar un Bean ID", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (directorios == null || directorios.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar los directorios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (operacion == null || operacion.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar la operación", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (chkIncluyeConstructoresPrivados.isSelected() 
			&& (constructores == null || constructores.trim().isEmpty())) {
    		JOptionPane.showMessageDialog(null, "Debe especificar los constructores", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	// muestro confirmacion
    	int response = JOptionPane.showConfirmDialog(null, "Seguro que desea generar el TestDummy?", "",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			// Si excepciones esta informado
			Set<String> exceptiones = null;
			if (excepciones != null && !excepciones.trim().isEmpty() ) {
				exceptiones = new HashSet<String>(); 
				String[] codes = excepciones.split("\\|");
    			for (String codigo : codes) {
    				if (!codigo.trim().equals("")) {
    					exceptiones.add(codigo);
    				}
    			}
			}
			// si constructores esta informado
			Set<String> constructoresSet = null;
			if (constructores != null && !constructores.trim().isEmpty() ) {
				constructoresSet = new HashSet<String>(); 
				String[] codes = constructores.split("\\|");
    			for (String codigo : codes) {
    				if (!codigo.trim().equals("")) {
    					constructoresSet.add(codigo);
    				}
    			}
			}
			
			StringBuffer buff = null;
			try {
		    	// llamo a la clase que genera
				DummyTest obj = new DummyTest(
						manager,
						bean_manager,
						operacion,
						directorios,
						exceptiones,
						chkIncluyeRutas.isSelected(),
						chkIncluyeMetodosPrivados.isSelected(),
						constructoresSet,
						chkIncluyeConstructoresPrivados.isSelected());
				
				buff = obj.generarTestDummy();
		    	// despliego resultado
				txtCodigo.setText(buff.toString());
				txtCodigo.select(0, 0);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al generar el TestDummy", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
    }
    
    private void btnLimpiarClick(ActionEvent arg0) {
    	txtManager.setText("");
    	txtBeanManager.setText("");
    	txtExcepciones.setText("");
    	txtOperacion.setText("");
    	txtRutas.setText("");
    	txtCodigo.setText("");
    	txtConstructores.setText("");
    	chkIncluyeMetodosPrivados.setSelected(Boolean.FALSE);
    	chkIncluyeRutas.setSelected(Boolean.FALSE);
    	chkIncluyeConstructoresPrivados.setSelected(Boolean.FALSE);
    }

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		this.setVisible(true);
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
		createAndShowGUI();
	}

}

class TextFileTransferable implements Transferable { 
	File temp;

	public TextFileTransferable(File temp) throws IOException {
		this.temp = temp;
	}

	public Object getTransferData(DataFlavor flavor) {
		List list = new ArrayList( );
		list.add(temp);
		return list;

	}	

	public DataFlavor[] getTransferDataFlavors( ) {
		DataFlavor[] df = new DataFlavor[1];
		df[0] = DataFlavor.javaFileListFlavor;
		return df;

	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if(flavor == DataFlavor.javaFileListFlavor) {
			return true;
		}
		return false;

	} 
}
