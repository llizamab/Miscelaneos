package plugin.actions;

import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class ConstantAction extends JFrame implements IWorkbenchWindowActionDelegate {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 2L;
	private IWorkbenchWindow window;
	
	private JLabel lblIdentificador;
	private JTextField txtIdentificador;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblRuta;
	private JTextField txtRuta;
	private JLabel lblTipo;
	private JTextField txtTipo;

	private JTextArea txtCodigos;
	
	/**
     * Crea los componentes visuales para ejecutar el programa.
     */
    private void createAndShowGUI() {
    	setTitle("Constants Generator TX-3000");
    	
    	lblIdentificador = new JLabel("Identificador (*): ");
    	lblIdentificador.setToolTipText("Obligatorio: Identificador, por ejemplo XST_");
    	
    	lblNombre = new JLabel("Nombre:");
    	lblNombre.setToolTipText("Opcional: Nombre de la variable");
    	
    	lblValor = new JLabel("Valor:");
    	lblValor.setToolTipText("Opcional: Valor de la constante");
    	
    	lblRuta = new JLabel("Ruta del fichero (*):");
    	lblRuta.setToolTipText("Obligatorio: Fichero de constante");
    	
    	lblTipo = new JLabel("Tipo:");
    	lblTipo.setToolTipText("Listado de Tipos");

    	txtIdentificador = new JTextField("", 10);
        
        txtNombre = new JTextField(100);
        
        txtValor = new JTextField(100);
        
        txtRuta = new JTextField(500);
        
        txtTipo = new JTextField(20);
        
        txtCodigos = new JTextArea();
        

        JButton btnLimpiar = new JButton();
        btnLimpiar.setText("Limpiar");
        JButton btnGnrNombre = new JButton();
        btnGnrNombre.setText("Generar por nombre");
        JButton btnGnrValor = new JButton();
        btnGnrValor.setText("Generar por valor");
        JButton btnGnrMasivo = new JButton();
        btnGnrMasivo.setText("Generar masivo");

        btnLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnLimpiarClick(arg0);
			}
		});
        btnGnrNombre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnGnrNombreClick(arg0);
			}
		});
        btnGnrValor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// pendiente
			}
		});
        btnGnrMasivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// pendiente
			}
		});
    	
    	GroupLayout grupo = new GroupLayout(getContentPane());
        setLayout(grupo);
        
        JScrollPane scroll = new JScrollPane(txtCodigos);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll);
        
        grupo.setHorizontalGroup(grupo.createParallelGroup()
        	.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblIdentificador)
            		.addComponent(txtIdentificador))
    		.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblNombre)
            		.addComponent(txtNombre))
            .addGroup(grupo.createSequentialGroup()
        			.addComponent(lblRuta)
            		.addComponent(txtRuta))
    		.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblValor)
            		.addComponent(txtValor))
    		.addGroup(grupo.createSequentialGroup()
        			.addComponent(lblTipo)
            		.addComponent(txtTipo))
			.addGroup(grupo.createSequentialGroup()
        			.addComponent(btnGnrNombre)
            		.addComponent(btnGnrValor)
            		.addComponent(btnLimpiar)
            		.addComponent(btnGnrMasivo))
    		.addGroup(grupo.createSequentialGroup()
        			.addComponent(scroll))
		);
        
        grupo.setVerticalGroup(grupo.createSequentialGroup()
    		.addGroup(grupo.createParallelGroup()
				.addComponent(lblIdentificador)
				.addComponent(txtIdentificador))
			.addGroup(grupo.createParallelGroup()
				.addComponent(lblNombre)
				.addComponent(txtNombre))
			.addGroup(grupo.createParallelGroup()
				.addComponent(lblRuta)
				.addComponent(txtRuta))
			.addGroup(grupo.createParallelGroup()
				.addComponent(lblValor)
				.addComponent(txtValor))
			.addGroup(grupo.createParallelGroup()
				.addComponent(lblTipo)
				.addComponent(txtTipo))
			.addGroup(grupo.createParallelGroup()
				.addComponent(btnGnrNombre)
				.addComponent(btnGnrValor)
				.addComponent(btnLimpiar)
				.addComponent(btnGnrMasivo))
			.addGroup(grupo.createParallelGroup()
				.addComponent(scroll))
		);

        Dimension size = new Dimension(700, 700);
        setMinimumSize(size);
        setMaximumSize(size);
        txtCodigos.setMinimumSize(size);
        grupo.linkSize(SwingConstants.HORIZONTAL, btnLimpiar, btnGnrNombre, btnGnrValor, btnGnrMasivo);
        grupo.linkSize(SwingConstants.HORIZONTAL, lblNombre,
        		lblTipo, lblIdentificador, lblValor, lblRuta);
        grupo.linkSize(SwingConstants.HORIZONTAL, txtNombre, txtTipo,
        		txtIdentificador, txtValor, txtRuta);
        grupo.linkSize(SwingConstants.VERTICAL, txtNombre, txtTipo,
        		txtIdentificador, txtValor, txtRuta);

        grupo.setAutoCreateGaps(true);
        grupo.setAutoCreateContainerGaps(true);
        setLocationByPlatform(true);
    }
    
    
    private void btnGnrNombreClick(ActionEvent arg0) {
    	// Obtengo los datos
    	String manager = txtIdentificador.getText();
    	String bean_manager = txtNombre.getText();
    	String directorios = txtRuta.getText();
    	String operacion = txtValor.getText();
    	String excepciones = txtTipo.getText();
    	// Valido
    	if (manager == null || manager.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar un Manager", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (bean_manager == null || bean_manager.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar un Bean ID", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	/*if (directorios == null || directorios.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar los directorios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}*/
    	if (operacion == null || operacion.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar la operación", "Error", JOptionPane.ERROR_MESSAGE);
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
			StringBuffer buff = null;
			try {
		    	// llamo a la clase que genera
				
				
		    	// despliego resultado
				txtCodigos.setText(buff.toString());
				txtCodigos.select(0, 0);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al generar el TestDummy", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
    }
    
    private void btnLimpiarClick(ActionEvent arg0) {
    	txtIdentificador.setText("");
    	txtNombre.setText("");
    	txtTipo.setText("");
    	txtValor.setText("");
    	txtRuta.setText("");
    	txtCodigos.setText("");
    
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
