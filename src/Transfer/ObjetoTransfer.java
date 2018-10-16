package Transfer;

/**
 * Clase generica, a la que extendera cada tipo de transfer. <br>
 * Contendra un campo id que identifica cada objeto. <br>
 * <br>
 * Transfer es la encargada de transformar la informacion de la base de datos en un objeto.
 * @author administrador
 */

public class ObjetoTransfer {

	//ATRIBUTOS
	
	private Object identificador;

	//CONSTRUCTORES
	
	/**
	 * Constructor por defecto. Inicializa la id a 0
	 */
	public ObjetoTransfer() {
		this(null);
	}
	
	/**
	 * Constructor que recibe la id del objeto
	 * @param id -int La id del transfer.
	 */
	public ObjetoTransfer(Object id) {
		this.identificador = id;
	}

	//GETTERS Y SETTERS
	
	/**
	 * Get que devuelve el id del objeto
	 * @return -int id del objeto
	 */
	public Object getId() {
		return identificador;
	}

	/**
	 * Set para sobreescribir el valor de la id
	 * @param id -int id del objeto
	 */
	public void setId(Object id) {
		this.identificador = id;
	}
}
