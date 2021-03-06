/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Administrador;

import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

import AppService.AppServiceAdmin;
import AppService.AppServiceInterfaz;
import Transfer.SocioTransfer;
import Vistas.PanelGenerico;

/**
 *
 * @author MIGUEL
 */
@SuppressWarnings("serial")
public class PanelEditarSocio extends PanelGenerico {
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxSelSocio;
    private javax.swing.JLabel jLabelApellidos;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JLabel jLabelNIF;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelSelSocio;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldNIF;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JPasswordField jPasswordField;
    // End of variables declaration//GEN-END:variables
    

    /**
     * Creates new form PanelEditarSocio
     */
    public PanelEditarSocio(EventListener controlador) {
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
    	this.setName("PanelEditarSocio");

        jLabelTitulo = new javax.swing.JLabel();
        jButtonVolver = new javax.swing.JButton();
        jButtonConfirmar = new javax.swing.JButton();
        jLabelNombre = new javax.swing.JLabel();
        jLabelApellidos = new javax.swing.JLabel();
        jLabelCorreo = new javax.swing.JLabel();
        jLabelSelSocio = new javax.swing.JLabel();
        jComboBoxSelSocio = new javax.swing.JComboBox<String>();
        jLabelNIF = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldApellidos = new javax.swing.JTextField();
        jTextFieldCorreo = new javax.swing.JTextField();
        jTextFieldNIF = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitulo.setText("Editar un socio");

        jButtonVolver.setText("Volver");
        jButtonVolver.setName("jButtonVolver");

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.setName("jButtonConfirmarEditarSocio");

        jLabelNombre.setText("Nombre:");

        jLabelApellidos.setText("Apellidos:");

        jLabelCorreo.setText("Correo electronico:");

        jLabelSelSocio.setText("Seleccione socio:");

        jComboBoxSelSocio.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione socio" }));
        jComboBoxSelSocio.setName("jComboBoxEditarSocio");
        
        jLabelNIF.setText("NIF:");

        jLabelPassword.setText("Password:");


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButtonVolver)
                .addGap(123, 123, 123)
                .addComponent(jLabelTitulo)
                .addContainerGap(228, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSelSocio)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelApellidos)
                    .addComponent(jLabelCorreo)
                    .addComponent(jLabelNIF)
                    .addComponent(jLabelPassword))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxSelSocio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldApellidos)
                    .addComponent(jTextFieldNombre)
                    .addComponent(jTextFieldCorreo)
                    .addComponent(jTextFieldNIF)
                    .addComponent(jPasswordField))
                .addGap(41, 41, 41)
                .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonVolver)
                    .addComponent(jLabelTitulo))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelSelSocio)
                    .addComponent(jComboBoxSelSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelApellidos)
                            .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCorreo)
                            .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNIF)
                            .addComponent(jTextFieldNIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
	 /**
     * Metodo que se encarga de fijar el controlador en los elementos del panel de forma adecuada
     * @param controlador contiene el controlador encargado del panel. Tiene que escuchar ActionListener e ItemListener.
     */
	public void fijarControlador(EventListener controlador) {
		this.jButtonConfirmar.addActionListener((ActionListener) controlador);
		this.jButtonVolver.addActionListener((ActionListener) controlador);
		this.jComboBoxSelSocio.addActionListener((ActionListener) controlador);
	}

	/**
	 * Object[0] el usuario a modificar
	 * Object[1] el NIF
	 * Object[2] el nombre
	 * Object[3] los apellidos
	 * Object[4] el correo electronico
	 * Object[5] la password
	 */
	@Override
	public Object[] obtenerDatos() {
		Object[] datos = new Object[6];
		
		datos[0] = this.jComboBoxSelSocio.getSelectedItem();
		datos[1] = this.jTextFieldNIF.getText();
		datos[2] = this.jTextFieldNombre.getText();
		datos[3] = this.jTextFieldApellidos.getText();
		datos[4] = this.jTextFieldCorreo.getText();
		datos[5] = this.jPasswordField.getPassword();

		return datos;
	}

	@Override
	public void limpiarPanel() {
        
		jComboBoxSelSocio.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione socio" }));
		this.jTextFieldNIF.setText("");
		this.jTextFieldNombre.setText("");
		this.jTextFieldApellidos.setText("");
		this.jTextFieldCorreo.setText("");
		this.jPasswordField.setText("");
		
	}

	@Override
	public void update(AppServiceInterfaz modelo, Object arg1) {
		if(arg1 != null){
			SocioTransfer s = (SocioTransfer)arg1;
			this.jTextFieldNIF.setText(s.getNIF());
			this.jTextFieldNombre.setText(s.getNombre());
			this.jTextFieldApellidos.setText(s.getApellidos());
			this.jTextFieldCorreo.setText(s.getCorreo());
			this.jPasswordField.setText(s.getConstrasenya());
		}
		else{
			List<SocioTransfer> ls = ((AppServiceAdmin)modelo).listarSocios();
			String[] l = new String[ls.size() +1 ];
			
			l[0] = "Seleccione socio";
			for(int i = 0; i < ls.size(); ++i){
				l[i+1] = ls.get(i).getNIF();
			}
			jComboBoxSelSocio.setModel(new javax.swing.DefaultComboBoxModel<String>(l));
			
		}
		
	}
}
