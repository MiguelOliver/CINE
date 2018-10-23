package Vistas.NoLoggeado;

import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

import AppService.AppServiceInterfaz;
import Transfer.PeliculaTransfer;
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
public class PanelPeliculasAnonimo extends PanelGenerico {
	
	 // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxPeliculas;
    private javax.swing.JLabel jLabelInfo;
    private javax.swing.JLabel jLabelSelecPelicula;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPaneInfo;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form PanelPeliculas
     */
    public PanelPeliculasAnonimo(EventListener controlador) {
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
    	this.setName("PanelPeliculasAnonimo");

    	 jComboBoxPeliculas = new javax.swing.JComboBox<String>();
         jLabelSelecPelicula = new javax.swing.JLabel();
         jButtonVolver = new javax.swing.JButton();
         jLabelTitulo = new javax.swing.JLabel();
         jScrollPane1 = new javax.swing.JScrollPane();
         jTextPaneInfo = new javax.swing.JTextPane();
         jLabelInfo = new javax.swing.JLabel();

          setPreferredSize(new java.awt.Dimension(600, 400));

          jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione pelicula" }));
          jComboBoxPeliculas.setName("jComboBoxBuscarPeliculas");
          
          jLabelSelecPelicula.setText("Seleccione pelicula");

          jButtonVolver.setText("Volver");
          jButtonVolver.setName("jButtonVolver");

          jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
          jLabelTitulo.setText("Peliculas");
          
          jTextPaneInfo.setEditable(false);
          jScrollPane1.setViewportView(jTextPaneInfo);

          jLabelInfo.setText("Informacion:");
          
          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                  .addGap(22, 22, 22)
                  .addComponent(jButtonVolver)
                  .addGap(156, 156, 156)
                  .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(0, 0, Short.MAX_VALUE))
              .addGroup(layout.createSequentialGroup()
                  .addGap(44, 44, 44)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                          .addComponent(jLabelInfo)
                          .addGap(0, 0, Short.MAX_VALUE))
                      .addGroup(layout.createSequentialGroup()
                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGroup(layout.createSequentialGroup()
                                  .addComponent(jLabelSelecPelicula)
                                  .addGap(58, 58, 58)
                                  .addComponent(jComboBoxPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                          .addContainerGap(58, Short.MAX_VALUE))))
          );
          layout.setVerticalGroup(
              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                          .addGap(22, 22, 22)
                          .addComponent(jButtonVolver)
                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE))
                      .addGroup(layout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(jComboBoxPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addComponent(jLabelSelecPelicula))
                  .addGap(13, 13, 13)
                  .addComponent(jLabelInfo)
                  .addGap(18, 18, 18)
                  .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(35, 35, 35))
          );
      }// </editor-fold>//GEN-END:initComponents
      
      
  	 /**
       * Metodo que se encarga de fijar el controlador en los elementos del panel de forma adecuada
       * @param controlador contiene el controlador encargado del panel. Tiene que escuchar ActionListener e ItemListener.
       */
  	public void fijarControlador(EventListener controlador) {
  		this.jComboBoxPeliculas.addActionListener((ActionListener) controlador);
  		this.jButtonVolver.addActionListener((ActionListener) controlador);
  	}

  	/**
  	 * Devuelve en el array datos[0] el titulo de la pelicula seleccionada en el comboBox
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
  		jTextPaneInfo.setText("");
  	}

  	@Override
  	public void update(AppServiceInterfaz modelo, Object arg1) {
  		if(arg1 != null){
  			this.jTextPaneInfo.setText(((PeliculaTransfer)arg1).toString())	;	
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
  				jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "No existen peliculas"}));
  			}
  		}
  	}
}
