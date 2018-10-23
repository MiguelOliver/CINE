package Vistas;

import javax.swing.JPanel;

import AppService.AppServiceInterfaz;


/**
 * Clase que indica que metodos tiene que implementar los paneles para poder ser utilizados, y devolver los datos al controlador
 * @author MIGUEL
 *
 */
@SuppressWarnings("serial")
public abstract class PanelGenerico extends JPanel{
	
	/**
	 * Devuelve los datos necesitados por el controlador. 
	 * Cada datos estara en una posicion concreta especificada en la documentacion de cada panel.
	 * @return un array Object con los datos
	 */
	public abstract Object[] obtenerDatos();
	
	/**
	 * Limpia los campos de panel para que no se queden los datos guardados
	 */
	public abstract void limpiarPanel();

	/**
	 * Metodo que actualiza el panel dependiendo del evento producido
	 * @param modelo - el modelo acutal (Admin, Socio, NoLoggeado)
	 */
	public abstract void update(AppServiceInterfaz modelo, Object arg1);

}
