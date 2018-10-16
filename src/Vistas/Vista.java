package Vistas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import AppService.AppServiceInterfaz;

@SuppressWarnings("serial")
public class Vista extends JFrame {

	private Selector selector;
	private Container container;
	private PanelGenerico panelActual;
	
	
	/**
	 * Constructor
	 */
	public Vista(EventListener controlador) {
		this.selector = new Selector(controlador);
		this.inicializarVista();
	}
	
	public PanelGenerico obtenerPanelActual(){
		return panelActual;
	}
	/**
	 * Inicializa y configura la vista
	 */
	private void inicializarVista() {
		this.container = this.getContentPane();
		this.container.setLayout(new BorderLayout());
		
		this.panelActual = (PanelGenerico) this.selector.panelInicial();
		this.container.add(this.panelActual);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo.gif"));
	    this.setIconImage(icon);
	       
		
		this.setSize(620, 420);
		this.setVisible(true);
		this.pack();
	}
	
	public void actualizarPanel(String nombreEvento){
		
		JPanel aux = this.panelActual;
		this.panelActual.setVisible(false);
		this.container.remove(this.panelActual);
		JPanel nuevoPanel = this.selector.panelSiguiente(nombreEvento, aux);
		this.panelActual = (PanelGenerico) nuevoPanel;
		this.container.add(nuevoPanel);
		this.panelActual.setVisible(true);	
	}
	
	static public void mensajeError(String error){
		JOptionPane.showMessageDialog(null, error, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	static public void mensajeAviso(String aviso){
		JOptionPane.showMessageDialog(null, aviso, "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public Object[] obtenerDatos() {
		return this.panelActual.obtenerDatos();		
	}
	
	public void limpiarPanel(){
		this.panelActual.limpiarPanel();
	}
	
	public void update(AppServiceInterfaz modelo, Object arg1) {
		this.panelActual.update(modelo, arg1);
		
	}

	
	public void arranca(){
	       EventQueue.invokeLater(new Runnable(){
	       	public void run() {
	       		setVisible(true);
	       	}
	       });		
		}	
}
