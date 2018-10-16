/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Administrador;

import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

import AppService.AppServiceInterfaz;
import Transfer.PeliculaTransfer;
import Vistas.PanelGenerico;

/**
 *
 * @author SkyLanes
 */
@SuppressWarnings("serial")
public class PanelBajaPelicula extends PanelGenerico {
	
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxPeliculas;
    private javax.swing.JLabel jLabelSelPelicula;
    private javax.swing.JLabel jLabelTitulo;
    

    /**
     * Creates new form PanelBajaPelícula
     */
    public PanelBajaPelicula(EventListener controlador) {
        initComponents();
        this.fijarControlador(controlador);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	this.setName("PanelBajaPelicula");

        jLabelTitulo = new javax.swing.JLabel();
        jButtonVolver = new javax.swing.JButton();
        jButtonConfirmar = new javax.swing.JButton();
        jLabelSelPelicula = new javax.swing.JLabel();
        jComboBoxPeliculas = new javax.swing.JComboBox<String>();

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitulo.setText("Dar de baja pelicula");

        jButtonVolver.setText("Volver");
        jButtonVolver.setName("jButtonVolver");

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.setName("jButtonConfirmarBajaPelicula");

        jLabelSelPelicula.setText("Seleccione pelicula:");

        jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione pelicula" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButtonVolver)
                        .addGap(91, 91, 91)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(224, 224, 224)
                                .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelSelPelicula)
                                .addGap(102, 102, 102)))
                        .addComponent(jComboBoxPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButtonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSelPelicula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
    }// </editor-fold>//GEN-END:initComponents


	 /**
     * Metodo que se encarga de fijar el controlador en los elementos del panel de forma adecuada
     * @param controlador contiene el controlador encargado del panel. Tiene que escuchar ActionListener e ItemListener.
     */
	public void fijarControlador(EventListener controlador) {
		this.jButtonConfirmar.addActionListener((ActionListener) controlador);
		this.jButtonVolver.addActionListener((ActionListener) controlador);
	}

	/**
	 * Object[0] titulo de l apelicula (String)
	 */
	@Override
	public Object[] obtenerDatos() {
		
		Object[] datos = new Object[1];
		
		datos[0] = this.jComboBoxPeliculas.getSelectedItem();
		
		return datos;
	}

	@Override
	public void limpiarPanel() {
		jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione pelicula" }));
	}

	@Override
	public void update(AppServiceInterfaz modelo, Object arg1) {
		
		List<PeliculaTransfer> lp = modelo.listarPeliculas();
		
		if(lp != null){
			String[] ls = new String[lp.size() +1 ];
			ls[0] = "Seleccione pelicula";
			for(int i = 0; i < lp.size(); ++i){
				ls[i+1] = lp.get(i).getTitulo();
			}
			jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(ls));
		}
		else{
			jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "No existen peliculas" }));
		}
	}
}
