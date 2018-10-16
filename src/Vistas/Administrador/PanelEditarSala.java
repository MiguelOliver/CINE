/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Administrador;

import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

import AppService.AppServiceInterfaz;
import Transfer.SalaTransfer;
import Vistas.PanelGenerico;

/**
 *
 * @author AppDataProgramFilms
 */
@SuppressWarnings("serial")
public class PanelEditarSala extends PanelGenerico {
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxSalas;
    private javax.swing.JLabel jLabelNumButacas;
    private javax.swing.JLabel jLabelNumFilas;
    private javax.swing.JLabel jLabelNumSala;
    private javax.swing.JLabel jLabelSelSala;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JTextField jTextFieldNumButacas;
    private javax.swing.JTextField jTextFieldNumFilas;
    private javax.swing.JTextField jTextFieldNumSala;
    // End of variables declaration//GEN-END:variables
    

    /**
     * Creates new form PanelEditarSala
     */
    public PanelEditarSala(EventListener controlador) {
        initComponents();
        this.fijarControlador(controlador);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {
    	this.setName("PanelEditarSala");

        jLabelTitulo = new javax.swing.JLabel();
        jButtonVolver = new javax.swing.JButton();
        jButtonConfirmar = new javax.swing.JButton();
        jLabelSelSala = new javax.swing.JLabel();
        jComboBoxSalas = new javax.swing.JComboBox<String>();
        jLabelNumFilas = new javax.swing.JLabel();
        jLabelNumButacas = new javax.swing.JLabel();
        jLabelNumSala = new javax.swing.JLabel();
        jTextFieldNumFilas = new javax.swing.JTextField();
        jTextFieldNumButacas = new javax.swing.JTextField();
        jTextFieldNumSala = new javax.swing.JTextField();

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitulo.setText("Editar una sala");

        jButtonVolver.setText("Volver");
        jButtonVolver.setName("jButtonVolver");

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.setName("jButtonConfirmarEditarSala");

        jLabelSelSala.setText("Seleccione sala:");

        jComboBoxSalas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione sala" }));
        jComboBoxSalas.setName("jComboBoxEditarSalas");
        
        jLabelNumFilas.setText("Numero de filas:");

        jLabelNumButacas.setText("Numero de butacas:");

        jLabelNumSala.setText("Numero de sala:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButtonVolver)
                        .addGap(124, 124, 124)
                        .addComponent(jLabelTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelNumButacas)
                                .addComponent(jLabelNumFilas)
                                .addComponent(jLabelSelSala))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelNumSala)
                                .addGap(21, 21, 21)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxSalas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldNumFilas)
                            .addComponent(jTextFieldNumButacas)
                            .addComponent(jTextFieldNumSala)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(177, 228, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabelTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButtonVolver)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSelSala)
                    .addComponent(jComboBoxSalas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumSala)
                    .addComponent(jTextFieldNumSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumFilas)
                    .addComponent(jTextFieldNumFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumButacas)
                    .addComponent(jTextFieldNumButacas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
	 /**
     * Metodo que se encarga de fijar el controlador en los elementos del panel de forma adecuada
     * @param controlador contiene el controlador encargado del panel. Tiene que escuchar ActionListener e ItemListener.
     */
	public void fijarControlador(EventListener controlador) {
		this.jButtonConfirmar.addActionListener((ActionListener) controlador);
		this.jButtonVolver.addActionListener((ActionListener) controlador);
		this.jComboBoxSalas.addActionListener((ActionListener) controlador);
	}

	/**
	 * Object[0] la sala a modificar
	 * Object[1] el nuevo numero de la sala
	 * Object[2] el nuevo numero de filas de la sala
	 * Object[3] el nuevo numero de butacas de las filas
	 */
	@Override
	public Object[] obtenerDatos() {
		Object[] datos = new Object[4];
		
		if(this.jComboBoxSalas.getSelectedIndex() == 0){
			datos[0] = -1;
		}
		else{
			datos[0] = this.jComboBoxSalas.getSelectedItem();
		}
		
		datos[1] = this.jTextFieldNumSala.getText();
		datos[2] = this.jTextFieldNumFilas.getText();
		datos[3] = this.jTextFieldNumButacas.getText();

		return datos;
	}

	@Override
	public void limpiarPanel() {
		jComboBoxSalas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione sala" }));
		this.jTextFieldNumButacas.setText("");
		this.jTextFieldNumFilas.setText("");
		this.jTextFieldNumSala.setText("");
	}

	@Override
	public void update(AppServiceInterfaz modelo, Object arg1) {
		if(arg1 != null){
			SalaTransfer s  = (SalaTransfer)arg1;
			
			this.jTextFieldNumButacas.setText(Integer.valueOf(s.getColumnas()).toString());
			this.jTextFieldNumFilas.setText(Integer.valueOf(s.getFilas()).toString());
			this.jTextFieldNumSala.setText(Integer.valueOf((int)s.getId()).toString());
		}
		else{
			List<SalaTransfer> ls = modelo.listarSalas();
			String[] l = new String[ls.size() +1 ];
			
			l[0] = "Seleccione sala";
			for(int i = 0; i < ls.size(); ++i){
				l[i+1] = Integer.valueOf((int)ls.get(i).getId()).toString();
			}
			this.jComboBoxSalas.setModel(new javax.swing.DefaultComboBoxModel<String>(l));
		}
	}
}
