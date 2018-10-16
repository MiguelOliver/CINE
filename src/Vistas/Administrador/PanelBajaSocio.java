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
 * @author AppDataProgramFilms
 */
@SuppressWarnings("serial")
public class PanelBajaSocio extends PanelGenerico{
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxSocios;
    private javax.swing.JLabel jLabelSelSocio;
    private javax.swing.JLabel jLabelTitulo;
    // End of variables declaration//GEN-END:variables
    

    /**
     * Creates new form PanelBajaSocio
     */
    public PanelBajaSocio(EventListener controlador) {
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
    	this.setName("PanelBajaSocio");

        jButtonConfirmar = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();
        jLabelSelSocio = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jComboBoxSocios = new javax.swing.JComboBox<String>();

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.setName("jButtonConfirmarBajaSocio");

        jButtonVolver.setText("Volver");
        jButtonVolver.setName("jButtonVolver");

        jLabelSelSocio.setText("Seleccione socio:");

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitulo.setText("Dar de baja un socio");

        jComboBoxSocios.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione socio" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jButtonVolver)
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelSelSocio)
                                .addGap(51, 51, 51)
                                .addComponent(jComboBoxSocios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelTitulo)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(196, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButtonVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabelTitulo)
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSocios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSelSocio))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
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
	 * Object[0] el nif del socio (STRING)
	 */
	@Override
	public Object[] obtenerDatos() {
		Object[] datos = new Object[1];
		
		datos[0] = this.jComboBoxSocios.getSelectedItem();
		
		return datos;
	}

	@Override
	public void limpiarPanel() {
		jComboBoxSocios.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione socio" }));
	}

	@Override
	public void update(AppServiceInterfaz modelo, Object arg1) {
		List<SocioTransfer> ls = ((AppServiceAdmin)modelo).listarSocios();
		
		if(ls != null){
			String[] l = new String[ls.size() +1 ];
			l[0] = "Seleccione socio";
			for(int i = 0; i < ls.size(); ++i){
				l[i+1] = ls.get(i).getNIF();
			}
			jComboBoxSocios.setModel(new javax.swing.DefaultComboBoxModel<String>(l));
		}
		else{
			jComboBoxSocios.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "No existen socios" }));
		}
		
	}
}