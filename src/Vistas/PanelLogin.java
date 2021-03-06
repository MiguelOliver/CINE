package Vistas;

import java.awt.event.ActionListener;
import java.util.EventListener;

import AppService.AppServiceInterfaz;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MIGUEL
 */
@SuppressWarnings("serial")
public class PanelLogin  extends PanelGenerico{
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonVolverLogin;
    private javax.swing.JCheckBox jCheckBoxAdmin;
    private javax.swing.JLabel jLabelContrasena;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
    
    
    /**
     * Creates new form PanelLogin
     */
    public PanelLogin(EventListener controlador) {
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
    	this.setName("PanelLogin");

        jButtonVolverLogin = new javax.swing.JButton();
        jLabelContrasena = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jLabelUsuario = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jButtonAceptar = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        jCheckBoxAdmin = new javax.swing.JCheckBox();

        jButtonVolverLogin.setText("Volver");
        jButtonVolverLogin.setName("jButtonVolver");

        jLabelContrasena.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelContrasena.setText("Password");

        jLabelUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelUsuario.setText("Identificador");

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setName("jButtonAceptarLogin");

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabelTitulo.setText("Login");

        jCheckBoxAdmin.setText("Administrador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addComponent(jButtonVolverLogin))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(80, 80, 80)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                        .addComponent(jLabelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGap(33, 33, 33)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBoxAdmin))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jButtonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonVolverLogin)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordField)
                    .addComponent(jLabelContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxAdmin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jButtonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
	 /**
     * Metodo que se encarga de fijar el controlador en los elementos del panel de forma adecuada
     * @param controlador contiene el controlador encargado del panel. Tiene que escuchar ActionListener e ItemListener.
     */
	public void fijarControlador(EventListener controlador) {
		this.jButtonAceptar.addActionListener((ActionListener) controlador);
		this.jButtonVolverLogin.addActionListener((ActionListener) controlador);
	}


	/**
	 * El panel de login debe devolver al controlador el id, el password, y si es administrador o socio.
	 * Devuelve:
	 * En Object[0] el usuario (String)
	 * En Object[1] el password (String)
	 * En Object[2] el estado del check box como entero. 1 para el administrador y 2 para el socio
	 * 
	 * @return datos - el array con los datos
	 */
	@Override
	public Object[] obtenerDatos() {
		
		Object[] datos  =  new Object[3];
		
		datos[0] = this.jTextFieldUsuario.getText();
		datos[1] = this.jPasswordField.getPassword();

		if(this.jCheckBoxAdmin.isSelected()){
			datos[2] = 1;
		}
		else{
			datos[2] = 2;
		}
		
		return datos;
	}

	@Override
	public void limpiarPanel() {
		this.jTextFieldUsuario.setText("");
		this.jPasswordField.setText("");
		this.jCheckBoxAdmin.setSelected(false);
	}

	@Override
	public void update(AppServiceInterfaz modelo, Object arg1) {}
}
