package Vistas.Administrador;

import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

import AppService.AppServiceInterfaz;
import Transfer.PeliculaTransfer;
import Transfer.SesionTransfer;
import Vistas.PanelGenerico;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MIGUEL
 */
@SuppressWarnings("serial")
public class PanelBuscarSesionesPelicula extends PanelGenerico {
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxPeliculas;
    private javax.swing.JLabel jLabelListaSesiones;
    private javax.swing.JLabel jLabelSelPelicula;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPaneSesiones;
    // End of variables declaration//GEN-END:variables
    

    /**
     * Creates new form PanelSesiones
     */
    public PanelBuscarSesionesPelicula(EventListener controlador) {
        initComponents();
        fijarControlador(controlador);
    }


	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	this.setName("PanelBuscarSesionesPelicula");
    	
        jButtonVolver = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelSelPelicula = new javax.swing.JLabel();
        jComboBoxPeliculas = new javax.swing.JComboBox<String>();
        jLabelListaSesiones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneSesiones = new javax.swing.JTextPane();

        jButtonVolver.setText("Volver");
        jButtonVolver.setName("jButtonVolver");

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitulo.setText("Sesiones");

        jLabelSelPelicula.setText("Seleccione pelicula");

        jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione pelicula" }));
        jComboBoxPeliculas.setName("jComboBoxBuscarSesionesPelicula");
        
        jLabelListaSesiones.setText("Lista de sesiones:");
        
        jTextPaneSesiones.setEditable(false);
        jScrollPane1.setViewportView(jTextPaneSesiones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButtonVolver)
                .addGap(150, 150, 150)
                .addComponent(jLabelTitulo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSelPelicula)
                    .addComponent(jLabelListaSesiones))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButtonVolver)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSelPelicula)
                    .addComponent(jComboBoxPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelListaSesiones)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void fijarControlador(EventListener controlador) {
    	this.jComboBoxPeliculas.addActionListener((ActionListener) controlador);
		this.jButtonVolver.addActionListener((ActionListener) controlador);		
	}


    /**
	 * Devuelve en el array datos[0] el titulo de la pelicula seleccionada en el comboBox para ver su lista de sesiones
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
        jTextPaneSesiones.setText("");
	}


	@Override
	public void update(AppServiceInterfaz modelo, Object arg1) {
		if(arg1 != null){
			List<SesionTransfer> ls= modelo.listarSesiones(((PeliculaTransfer)arg1).getTitulo());
			if(ls != null){
				String s = "";
				for(int i = 0; i< ls.size(); ++i){
					s += ls.get(i).toString();
				}
				this.jTextPaneSesiones.setText(s);	
			}
			else{
				this.jTextPaneSesiones.setText("No existen sesiones para esa pelicula");
			}
		}
		else{
			List<PeliculaTransfer> lp = modelo.listarPeliculas();
			
			if(lp != null){
				String[] ls = new String[lp.size() +1 ];

				ls[0] = "Seleccione pelicula";
				for(int i = 0; i < lp.size(); ++i){
					ls[i+1] = lp.get(i).getTitulo();
				}
				this.jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(ls));
			}
			else{

		        jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "No existen peliculas" }));
			}
		}
	}
}
