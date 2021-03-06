/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Usuario;

import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

import AppService.AppServiceInterfaz;
import Transfer.PeliculaTransfer;
import Vistas.PanelGenerico;

/**
 *
 * @author MIGUEL
 */
@SuppressWarnings("serial")
public class PanelValorar extends PanelGenerico {

	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxPeliculas;
    private javax.swing.JComboBox<Integer> jComboBoxValoraciones;
    private javax.swing.JLabel jLabelSelPelicula;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelValoracion;
 // End of variables declaration//GEN-END:variables
    
    /**
     * Creates new form PanelBajaSala
     * @return 
     */
    public PanelValorar(EventListener controlador) {
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
    	this.setName("PanelValorar");

        jButtonVolver = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelSelPelicula = new javax.swing.JLabel();
        jComboBoxPeliculas = new javax.swing.JComboBox<String>();
        jButtonConfirmar = new javax.swing.JButton();
        jLabelValoracion = new javax.swing.JLabel();
        jComboBoxValoraciones = new javax.swing.JComboBox<Integer>();
        this.jComboBoxValoraciones.addItem(1);
        this.jComboBoxValoraciones.addItem(2);
        this.jComboBoxValoraciones.addItem(3);
        this.jComboBoxValoraciones.addItem(4);
        this.jComboBoxValoraciones.addItem(5);

        jButtonVolver.setText("Volver");
        jButtonVolver.setName("jButtonVolver");

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitulo.setText("Valorar");

        jLabelSelPelicula.setText("Seleccione pelicula:");

        jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Seleccione pelicula" }));

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.setName("jButtonConfirmarValorarPelicula");

        jLabelValoracion.setText("Valoracion:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButtonVolver)
                        .addGap(161, 161, 161)
                        .addComponent(jLabelTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelValoracion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxValoraciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelSelPelicula)
                        .addGap(75, 75, 75)
                        .addComponent(jComboBoxPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(239, 239, 239))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButtonVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabelTitulo)))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSelPelicula))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValoracion)
                    .addComponent(jComboBoxValoraciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    /**
     * Metodo que se encarga de fijar el controlador en los elementos del panel de forma adecuada
     * @param controlador contiene el controlador encargado del panel. Tiene que escuchar ActionListener e ItemListener.
     */
	public void fijarControlador(EventListener controlador) {
		this.jButtonVolver.addActionListener((ActionListener) controlador);
		this.jButtonConfirmar.addActionListener((ActionListener) controlador);
	}

	/**
	 * Object[0] la pelicula a valorar
	 * Objecto[1] la valoracion
	 */
	@Override
	public Object[] obtenerDatos() {
		Object[] datos = new Object[2];
		
		datos[0] = this.jComboBoxPeliculas.getSelectedItem();
		datos[1] = this.jComboBoxValoraciones.getSelectedItem();
		
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
			this.jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(ls));
			}
		else{
			jComboBoxPeliculas.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "No existen peliculas" }));
			
		}
	}
}
