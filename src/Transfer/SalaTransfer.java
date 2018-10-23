package Transfer;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende ObjetoTransfer y contiene los datos necesarios para representar una sala de un cine ademas del id heredado <br>
 * Tiene el numero de filas y de columnas de la sala, su aforo y una lista con todas las sesiones de la sala
 * encapsula toda la informacion de una sala para poder trabajar con ella
 * @author MIGUEL
 *
 */
public class SalaTransfer extends ObjetoTransfer {
	
	//ATRIBUTOS
	
	//El id es el superior
	private int filas;
	private int columnas;
	private int aforo;
	private List<SesionTransfer> listaSesiones;

	//CONSTRUCTORES
	
	/**
	 * constructor por defecto que inicializa todo a 0 y la lista a null
	 */
	public SalaTransfer(){
		super();
		this.aforo = 0;
		this.filas = 0;
		this.columnas = 0;
		this.listaSesiones = null;
	}
	
	/**
	 * constructor que recibe la id de la sala, las filas y las columnas. el foro sera la multiplicacion de ambas
	 * crea un array dinamico vacio con 10 posiciones iniciales (ArrayList)
	 * @param id -int
	 * @param filas -int
	 * @param columnas -int
	 */
	public SalaTransfer(int id, int filas, int columnas){
		super(id);
		this.filas = filas;
		this.columnas = columnas;
		this.aforo = filas*columnas;
		this.listaSesiones = new ArrayList<SesionTransfer>();
		
	}
	
	/**
	 * constructor con todos los campos pasados por parametros, incluida la lista de las sesiones de la sala
	 * @param id -int
	 * @param filas -int
	 * @param columnas -int
	 * @param lista -List
	 */
	public SalaTransfer(int id, int filas, int columnas, List<SesionTransfer> lista){
		super(id);
		this.filas = filas;
		this.columnas = columnas;
		this.aforo = filas*columnas;
		this.listaSesiones = lista;
	}
		
	//GETTERS Y SETTERS
	
	/**
	 * get que devuelve las filas de una sala
	 * @return -int filas
	 */
	public int getFilas() {
		return filas;
	}
	
	/**
	 * set para actualizar el numero de filas de una sala
	 * @param filas -int
	 */
	public void setFilas(int filas) {
		this.filas = filas;
	}
	
	/**
	 * get que devuelve las columnas de una sala
	 * @return -int columnas
	 */
	public int getColumnas() {
		return columnas;
	}
	
	/**
	 * set para actualizar el numero de columnas de una sala
	 * @param columnas -int
	 */
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	/**
	 * get para devolver de manera rapida el aforo de una sala determinada
	 * @return -int el aforo
	 */
	public int getAforo() {
		return aforo;
	}
	
	/**
	 * funcion elemental que actualiza el valor del aforo de una sala <br>
	 * no recomendable su uso ya que no modifica las filas y columnas, rompiendo asi su coherencia
	 * @param aforo -int
	 */
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	
	/**
	 * get para obtener la lista de sesiones de una sala determinada
	 * @return -List listaSesiones
	 */
	public List<SesionTransfer> getListaSesiones() {
		return listaSesiones;
	}
	
	/**
	 * set para actualizar la lista de sesiones de una sala
	 * @param listaSesiones -List
	 */
	public void setListaSesiones(List<SesionTransfer> listaSesiones) {
		this.listaSesiones = listaSesiones;
	}

	
}
