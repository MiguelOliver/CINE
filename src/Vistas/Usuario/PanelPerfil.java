package Vistas.Usuario;

import java.awt.event.ActionListener;
import java.util.EventListener;

import AppService.AppServiceInterfaz;
import AppService.AppServiceSocio;
import Vistas.PanelGenerico;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AppDataProgramFilms
 */
@SuppressWarnings("serial")
public class PanelPerfil extends PanelGenerico {
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnularReserva;
    private javax.swing.JButton jButtonReservasAct;
    private javax.swing.JButton jButtonValorarPelicula;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPaneInfo;
    // End of variables declaration//GEN-END:variables
    

    /**
     * Creates new form PanelPerfil
     */
    public PanelPerfil(EventListener controlador) {
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
	
    	this.setName("PanelPerfil");

        jButtonVolver = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        jButtonReservasAct = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneInfo = new javax.swing.JTextPane();
        jButtonAnularReserva = new javax.swing.JButton();
        jButtonValorarPelicula = new javax.swing.JButton();

        jButtonVolver.setText("Volver");
        jButtonVolver.setName("jButtonVolver");

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Perfil");

        jButtonReservasAct.setText("Mis reservas");		
        jButtonReservasAct.setName("jButtonReservasAct");
        jButtonReservasAct.setToolTipText("");
        jButtonReservasAct.setPreferredSize(new java.awt.Dimension(100, 23));

        jScrollPane1.setViewportView(jTextPaneInfo);

        jButtonAnularReserva.setText("Anular reservas");
        jButtonAnularReserva.setName("jButtonAnularReserva");

      
        jButtonValorarPelicula.setText("Valorar pelicula");
        jButtonValorarPelicula.setToolTipText("");
        jButtonValorarPelicula.setName("jButtonValorarPelicula");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonValorarPelicula)
                                .addGap(23, 23, 23)
                                .addComponent(jButtonReservasAct, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jButtonAnularReserva)
                                .addGap(63, 63, 63))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonVolver)
                        .addGap(97, 97, 97)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonVolver)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonValorarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonReservasAct, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAnularReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
	 /**
     * Metodo que se encarga de fijar el controlador en los elementos del panel de forma adecuada
     * @param controlador contiene el controlador encargado del panel. Tiene que escuchar ActionListener e ItemListener.
     */
	public void fijarControlador(EventListener controlador) {
		this.jButtonAnularReserva.addActionListener((ActionListener) controlador);
		this.jButtonVolver.addActionListener((ActionListener) controlador);
		this.jButtonReservasAct.addActionListener((ActionListener) controlador);
		this.jButtonValorarPelicula.addActionListener((ActionListener) controlador);
	}

	@Override
	public Object[] obtenerDatos() {
		return null;
	}

	@Override
	public void limpiarPanel() {
		this.jTextPaneInfo.setText("");
		
	}

	@Override
	public void update(AppServiceInterfaz modelo, Object arg1) {
		this.jTextPaneInfo.setText(AppServiceSocio.getSocio().toString());		
	}
}
