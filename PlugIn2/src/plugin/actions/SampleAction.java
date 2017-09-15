package plugin.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.io.File;
import javax.swing.JTextField;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;


/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class SampleAction extends JFrame implements IWorkbenchWindowActionDelegate {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private IWorkbenchWindow window;

	private JLabel lblProyecto; 
	private JTextField txtProyecto;
	private JLabel lblCodigo; 
	private JTextField txtCodigo;
	private JLabel lblPatronNombre; 
	private JTextField txtPatronNombre;
	private Set<String> codigos = new HashSet<String>();

	/**
     * Crea los componentes visuales para ejecutar el programa.
     */
    private void createAndShowGUI() {
    	setTitle("Comentar recarga de escenario");
        lblProyecto = new JLabel("Proyecto: ");
        lblProyecto.setToolTipText("Código del proyecto");
        lblCodigo = new JLabel("Código(s): ");
        lblCodigo.setToolTipText("Separados por pipe | ");
        lblPatronNombre = new JLabel("Junits: ");
        lblPatronNombre.setToolTipText("Patron de nombre sin incluir el TestCase.java, por ejemplo 'Consult*' ");
        txtProyecto = new JTextField(40);
        txtProyecto.setToolTipText("Código del proyecto");
        txtProyecto.setText("R:\\desarrollo\\workspace\\");
        txtCodigo = new JTextField(20);
        txtCodigo.setToolTipText("Separados por pipe | ");
        txtPatronNombre = new JTextField(20);
        txtPatronNombre.setToolTipText("Patron de nombre sin incluir el TestCase.java, por ejemplo 'Consult*' ");
        txtPatronNombre.setText("*");
        JButton btnLimpiar = new JButton();
        btnLimpiar.setText("Limpiar");
        JButton btnAnalizar = new JButton();
        btnAnalizar.setText("Ejecutar");

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
 
        GroupLayout grupo = new GroupLayout(getContentPane());
        setLayout(grupo);
        
        grupo.setHorizontalGroup(grupo.createParallelGroup()
        	.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblProyecto)
            		.addComponent(txtProyecto)
            		.addComponent(btnLimpiar))
    		.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblCodigo)
            		.addComponent(txtCodigo)
            		.addComponent(btnAnalizar))
            .addGroup(grupo.createSequentialGroup()
        			.addComponent(lblPatronNombre)
            		.addComponent(txtPatronNombre))
    		);
        
        grupo.setVerticalGroup(grupo.createSequentialGroup()
    		.addGroup(grupo.createParallelGroup()
				.addComponent(lblProyecto)
				.addComponent(txtProyecto)
				.addComponent(btnLimpiar))
			.addGroup(grupo.createParallelGroup()
    				.addComponent(lblCodigo)
    				.addComponent(txtCodigo)
    				.addComponent(btnAnalizar))
			.addGroup(grupo.createParallelGroup()
    				.addComponent(lblPatronNombre)
    				.addComponent(txtPatronNombre))
			);
        Dimension size = new Dimension(650, 150);
        setMinimumSize(size);
        setMaximumSize(size);
        grupo.linkSize(SwingConstants.HORIZONTAL, btnLimpiar, btnAnalizar);
        grupo.linkSize(SwingConstants.HORIZONTAL, lblProyecto, lblCodigo, lblPatronNombre);
        grupo.linkSize(SwingConstants.HORIZONTAL, txtProyecto, txtCodigo, txtPatronNombre);
        grupo.linkSize(SwingConstants.VERTICAL, txtProyecto, txtCodigo, txtPatronNombre);
        grupo.setAutoCreateGaps(true);
        grupo.setAutoCreateContainerGaps(true);
        setLocationByPlatform(true);
    }

    private void btnAnalizarClick(ActionEvent arg0) {
    	String code = txtCodigo.getText();
    	String proy = txtProyecto.getText();
    	String patr = txtPatronNombre.getText();
    	if (code != null && !code.trim().equals("") && proy != null && !proy.trim().equals("") && !code.contains("*")) {
    		// validar patron
    		if (patr == null || patr.trim().equals("")) {
    			JOptionPane.showMessageDialog(null, "Debe especificar un patron de nombre de Junits a buscar", "Error", JOptionPane.ERROR_MESSAGE);
    			return;
    		} else {
    			patr = patr.replace("*", ".+");
    		}
    		if (".".equals(proy) || "..".equals(proy)) {
    			JOptionPane.showMessageDialog(null, "No se puede analizar a partir de las rutas '.' a '..'", "Error", JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    		// validar si existe el proyecto
    		File f = new File(proy);
    		if (f.exists() && f.isDirectory()) { 
    			// seccionar codigos
    			String[] codes = code.split("\\|");
    			for (String codigo : codes) {
    				if (!codigo.equals("")) {
    					codigos.add(codigo);
    				}
    			}
    			// mostrar confirmacion
    			int response = JOptionPane.showConfirmDialog(null, "Seguro que desea comentar la recarga de escenario?", "",
    			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    			if (response == JOptionPane.YES_OPTION) {
    				response = JOptionPane.showConfirmDialog(null, "Seguro seguro?", "",
        			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    				if (response == JOptionPane.YES_OPTION) {
    					response = JOptionPane.showConfirmDialog(null, "Seguro seguro que está seguro?", "",
            			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        				if (response == JOptionPane.YES_OPTION) {

	    					int ret = CommentReload.ejecutar(proy, patr, codigos);
	    					if (ret > -1) {
	    						JOptionPane.showMessageDialog(null, "Se ha comentado la recarga de escenario en " + ret + " JUnits.", "", JOptionPane.INFORMATION_MESSAGE);
	    					} else {
	    						JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ejecutar el proceso.", "", JOptionPane.INFORMATION_MESSAGE);
	    					}
        				} else if (response == JOptionPane.NO_OPTION) {
        					JOptionPane.showMessageDialog(null, "No que estaba tan seguro!", "", JOptionPane.INFORMATION_MESSAGE);
        				}
    				} else if (response == JOptionPane.NO_OPTION) {
    					JOptionPane.showMessageDialog(null, "No estaba seguro entonces", "", JOptionPane.INFORMATION_MESSAGE);
    				}
    			}
    		} else {
    			JOptionPane.showMessageDialog(null, "No existe el proyecto '" + proy + "'", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    	} else {
    		JOptionPane.showMessageDialog(null, "Debe especificar el proyecto y algun código", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	codigos.clear();
    }
 

    private void btnLimpiarClick(ActionEvent arg0) {
    	txtProyecto.setText("");
    	txtCodigo.setText("");
    	txtPatronNombre.setText("");
    	codigos.clear();
    }

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		this.setVisible(true);

		/*
		MessageDialog.openInformation(
			window.getShell(),
			"PlugIn",
			"Hello, Eclipse world");*/
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

	/**
	 * The constructor.
	 */
	public SampleAction() {
	}
}