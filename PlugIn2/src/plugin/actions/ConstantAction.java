package plugin.actions;

import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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

import plugin.actions.GeneraConstante.Tipos;

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
	
	private JComboBox list;
	private DefaultComboBoxModel model;
	
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

    	txtIdentificador = new JTextField("", 40);
        
        txtNombre = new JTextField(40);
        
        txtValor = new JTextField(40);
        
        txtRuta = new JTextField(40);
        
        model = new DefaultComboBoxModel();
        
        list = new JComboBox(model);
        
//        for (Tipos tipo : Tipos.values()) {
//            model.addElement(tipo);
//        }
        model.addElement(Tipos.String);
        model.addElement(Tipos.Integer);
        model.addElement(Tipos.Double);
        model.addElement(Tipos.Long);
        
        
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
				
				btnGnrValorClick(arg0);
			}
		});
        btnGnrMasivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				btnGnrMasivoClick(arg0);
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
            		.addComponent(list))
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
				.addComponent(list))
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
        grupo.linkSize(SwingConstants.HORIZONTAL, txtNombre, list,
        		txtIdentificador, txtValor, txtRuta);
        grupo.linkSize(SwingConstants.VERTICAL, txtNombre, list,
        		txtIdentificador, txtValor, txtRuta);

        grupo.setAutoCreateGaps(true);
        grupo.setAutoCreateContainerGaps(true);
        setLocationByPlatform(true);
    }
    
    
    private void btnGnrNombreClick(ActionEvent arg0) {
    	// Al generar por nombre
    	// obligatorio ruta, nombre y apost
    	String identif = txtIdentificador.getText();
    	String nombreVar = txtNombre.getText();
    	String ruta = txtRuta.getText();
    	
    	// Valido
    	if (identif == null || identif.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar un Identificador", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (nombreVar == null || nombreVar.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar un Nombre", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (ruta == null || ruta.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar la ruta del fichero de Constante", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	
    	// muestro confirmacion
    	int response = JOptionPane.showConfirmDialog(null, "Seguro que desea generar la Constante?", "",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	
		if (response == JOptionPane.YES_OPTION) {

			try {
		    	// llamo a la clase que genera
				final GeneraConstante obj = new GeneraConstante(ruta, identif, nombreVar, null, Tipos.String);
		    	// despliego resultado
				obj.generarConstante();
				
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al generar la constante", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
    }
    
    
    private void btnGnrValorClick(ActionEvent arg0) {
    	// Al generar por valor
    	// obligatorio ruta, nombre, valor, tipo y apost
    	String identif = txtIdentificador.getText();
    	String nombreVar = txtNombre.getText();
    	String ruta = txtRuta.getText();
    	String valor = txtValor.getText();
    	// pendiente
    	Tipos tipo = (Tipos) list.getSelectedItem();
    	
    	// Valido
    	if (identif == null || identif.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar un Identificador", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (nombreVar == null || nombreVar.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar un Nombre", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (ruta == null || ruta.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar la ruta del fichero de Constante", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (valor == null) {
    		JOptionPane.showMessageDialog(null, "Debe especificar algun valor", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	
    	// muestro confirmacion
    	int response = JOptionPane.showConfirmDialog(null, "Seguro que desea generar la Constante?", "",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	
		if (response == JOptionPane.YES_OPTION) {

			try {
		    	// llamo a la clase que genera
				final GeneraConstante obj = new GeneraConstante(ruta, identif, nombreVar, valor, tipo);
		    	// despliego resultado
				obj.generarConstante();
				
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al generar la constante", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
    }
    
    private void btnGnrMasivoClick(ActionEvent arg0) {
    	// Al generar por valor
    	// obligatorio ruta, txtArea y apost
    	String identif = txtIdentificador.getText();
    	String ruta = txtRuta.getText();
    	String codigos = txtCodigos.getText();
    	
    	// Valido
    	if (identif == null || identif.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar un Identificador", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (codigos == null || codigos.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar algun elemento", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	if (ruta == null || ruta.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Debe especificar la ruta del fichero de Constante", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	
    	// muestro confirmacion
    	int response = JOptionPane.showConfirmDialog(null, "Seguro que desea generar la Constante?", "",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	
		if (response == JOptionPane.YES_OPTION) {
			try {
				// recorro el textArea
				final String s[] = codigos.split("\\r?\\n");
			    final ArrayList<String> arrList = new ArrayList(Arrays.asList(s));
			    // por cada
			    for (final String constante : arrList) {
			    	// si viene algun valor
			    	if (constante != null && !constante.trim().isEmpty()) {
				    	// llamo a la clase que genera
						final GeneraConstante obj = new GeneraConstante(ruta, identif, constante.trim(), null, Tipos.String);
				    	// despliego resultado
						obj.generarConstante();
			    	}
			    }
				
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al generar la constante", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
    }
    
    private void btnLimpiarClick(ActionEvent arg0) {
    	txtIdentificador.setText("");
    	txtNombre.setText("");
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
