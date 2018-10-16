package Vistas.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Transfer.ButacaTransfer;



@SuppressWarnings("serial")
public class PanelSelAsientos extends JFrame {
		
	private Container container;
	private JPanel cinePanel = new JPanel();
	private CeldaButaca[][] butaca;
	private int fila = 0;
	private int columna = 0;
	private EventListener cnt;
	
	
	/**
	 * Constructor
	 * @param controlador - el controlador
	 */
	public PanelSelAsientos(EventListener controlador) {
		this.cnt = controlador;
		this.inicializaCinePanel();
	}



	public void setFila(int f){
		this.fila = f;
	}
	
	public void setColumna(int c){
		this.columna = c;
	}
	
	
	/**
	 * Inicializa el panel
	 */
	private void inicializaCinePanel() {
		this.container = this.getContentPane();
		this.container.setLayout(new BorderLayout());
		this.setName("PanelSelAsientos");	
		this.container.add(this.cinePanel, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(700,700));
		this.setVisible(false);
		this.pack();
	}
	
	
	/**
	 * Crea el mapa del cine
	 */
	public void crearPanelSala() {
		
		
		this.cinePanel = new JPanel();
		this.cinePanel.setBorder(new TitledBorder("City Map"));
		this.cinePanel.setLayout(new GridLayout(fila,columna,0,0));
		this.butaca = new CeldaButaca[fila][columna];
		
		// creamos el tablero anyadiendo a cada posicion un PlaceCell
		for(int i=0; i<fila; i++) {
			for(int j=0; j<columna; j++) {
				this.butaca[i][j] = new CeldaButaca();
				this.cinePanel.add(this.butaca[i][j]);
				this.butaca[i][j].setText((i+1) + "," + (j+1));
				this.butaca[i][j].setName("Asiento " + i + "," + j);
				this.butaca[i][j].setBackground(Color.GREEN);				
			}
		}
		

		this.cinePanel.setPreferredSize(new Dimension(700,700));
		this.container.add(this.cinePanel, BorderLayout.CENTER);
		this.fijarControlador(cnt);
	}
	
	
	public void setButacasocupadas(List<ButacaTransfer> butacasOcupadas) {
		for(int i=0; i<butacasOcupadas.size(); ++i){
			int f = butacasOcupadas.get(i).getLugar().getFila();
			int c = butacasOcupadas.get(i).getLugar().getColumna();
			
			this.butaca[f][c].setBackground(Color.RED);
		}
	}
	
	/**
     * Establece el controlador de cada celda
     * @param controlador - el controlador
     */
	public void fijarControlador(EventListener controlador) {
		
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				this.butaca[i][j].addActionListener((ActionListener)controlador);
	}
	
	public void ocuped(int f, int c){
		this.butaca[f][c].setBackground(Color.RED);
	}

	public void arranca(){
	       EventQueue.invokeLater(new Runnable(){
	       	public void run() {
	       		setVisible(true);
	       	}
	       });	
	}
}
