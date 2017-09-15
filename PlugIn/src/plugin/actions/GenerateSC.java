package plugin.actions;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class GenerateSC extends JFrame implements IWorkbenchWindowActionDelegate {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private IWorkbenchWindow window;
	/**
	 * Label entrada.
	 */
	private JLabel lblGenerar;
	/**
	 * Textbox entrada.
	 */
	private JTextArea txtInput;
	/**
	 * Textbox soap.
	 */
	private JTextArea txtSoap;
	/**
	 * Textbox java.
	 */
	private JTextArea txtJava;

	/**
     * Crea los componentes visuales para ejecutar el programa.
     */
    private void createAndShowGUI() {
    	setTitle("Generar Sanity Check");
    	lblGenerar = new JLabel("Ingresar definción de la operación:");
    	txtInput = new JTextArea("xxxxx");
    	txtSoap = new JTextArea("yyyyy");
    	txtJava = new JTextArea("zzzzzz");
    	final JButton btnGenerar = new JButton();
    	btnGenerar.setText("Generar");
    	final JButton btnSoap = new JButton();
    	btnSoap.setText("Copiar Soap");
    	final JButton btnJava = new JButton();
    	btnJava.setText("Copiar Java");
    	final JButton btnClean = new JButton();
    	btnClean.setText("Limpiar");
    	
    	btnGenerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				btnGenerarClick(arg0);
			}
		});
    	btnSoap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				final StringSelection ss = new StringSelection(txtSoap.getText());
			    clipboard.setContents(ss, null);
			}
		});
    	btnJava.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				final StringSelection ss = new StringSelection(txtJava.getText());
			    clipboard.setContents(ss, null);
			}
		});
    	btnClean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				txtInput.setText("");
				txtSoap.setText("");
				txtJava.setText("");
			}
		});
    	
    	final GroupLayout grupo = new GroupLayout(getContentPane());
        setLayout(grupo);
        // contenedor 
        final JScrollPane scrollInput = new JScrollPane(txtInput);
        scrollInput.setVerticalScrollBarPolicy(
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollInput);
        // contenedor 
        final JScrollPane scrollSoap = new JScrollPane(txtSoap);
        scrollSoap.setVerticalScrollBarPolicy(
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollSoap);
        // contenedor 
        final JScrollPane scrollJava = new JScrollPane(txtJava);
        scrollJava.setVerticalScrollBarPolicy(
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollJava);
        
        // agrupacion horizontal
        grupo.setHorizontalGroup(grupo.createSequentialGroup()
    		.addGroup(grupo.createParallelGroup()
				.addComponent(lblGenerar)
				.addComponent(scrollInput)
			)
    		.addGroup(grupo.createParallelGroup()
				.addComponent(btnGenerar)
				.addComponent(btnSoap)
				.addComponent(btnJava)
				.addComponent(btnClean)
			)
			.addGroup(grupo.createParallelGroup()
				.addComponent(scrollSoap)
				.addComponent(scrollJava)
			)
		);
        // agrupacion vertical
        grupo.setVerticalGroup(grupo.createSequentialGroup()
    		.addGroup(grupo.createParallelGroup()
				.addGroup(grupo.createSequentialGroup()
					.addComponent(lblGenerar)
					.addComponent(scrollInput)
					)
				.addGroup(grupo.createSequentialGroup()
					.addComponent(btnGenerar)
    				.addComponent(btnSoap)
    				.addComponent(btnJava)
    				.addComponent(btnClean)
					)
				.addGroup(grupo.createSequentialGroup()
					.addComponent(scrollSoap)
    				.addComponent(scrollJava)
					)
		));
        
        final Dimension size = new Dimension(1000, 600);
        setMinimumSize(size);
        setMaximumSize(size);
        
        // tamaños ...
        grupo.linkSize(SwingConstants.HORIZONTAL, btnGenerar, btnJava,
        		btnSoap, btnClean);
        grupo.linkSize(SwingConstants.HORIZONTAL, scrollInput, txtInput, lblGenerar);
        grupo.linkSize(SwingConstants.HORIZONTAL, scrollJava, scrollSoap);
        
        grupo.setAutoCreateGaps(true);
        grupo.setAutoCreateContainerGaps(true);
        setLocationByPlatform(true);
        
        
    }
    
    /**
     * Evento click del boton generar.
     * @param arg0 arg0.
     */
    private void btnGenerarClick(final ActionEvent arg0) {
    	// logica
    	
    }
    

	@Override
	public final void run(IAction arg0) {
		this.setVisible(true);
	}

	@Override
	public final void selectionChanged(final IAction arg0, final ISelection arg1) {
	}

	@Override
	public final void init(final IWorkbenchWindow arg0) {
		this.window = window;
		createAndShowGUI();
	}

}
