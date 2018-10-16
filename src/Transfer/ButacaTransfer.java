package Transfer;

import AppDataProgramFilms.Asiento;
import AppDataProgramFilms.Utilidades;

/**
 * Clase que extiende de objeto transfer y a�ade los atributos propios de una butaca. <br>
 * Contendra un idsesion (int), un idreserva (int), y y un asiento (tipo asiento)<br>
 * El tipo asiento unicamene consta de la fila y la columna en la que esta situada la butaca.
 * @author administrador
 */
public class ButacaTransfer extends ObjetoTransfer {
	
	//ATRIBUTOS
	private SesionTransfer sesion;
	private int idReserva;
	private Asiento lugar;
	
	//CONTRUCTORES
	
	/**
	 * Constructor por defecto que inicializa los campos a 0 y crea un asiento vacio.
	 */
	public ButacaTransfer(){
		super();
		setIdReserva(0);
		setSesion(null);
		setLugar(new Asiento());
	}
	
	/**
	 * Constructor parametrizado que inicializa una determinada butaca.
	 * @param idSesion -int id de la sesion a la que pertenece esta butaca
	 * @param idReserva -int el id de la reserva para asociar esta butaca al usuario
	 * @param asiento -Asiento el asiento concreto de la butaca (fila, columna)
	 */
	public ButacaTransfer(SesionTransfer sesion, int idReserva, Asiento asiento){
		super();
		this.setSesion(sesion);
		this.setIdReserva(idReserva);
		this.setLugar(asiento);
	}
		
	
	//GETTERS Y SETTERS

	/**
	 * Get que devuelve el id de la sesion perteneciente a la butaca
	 * @return -int la id de la sesion a la que pertenece la butaca
	 */
	public SesionTransfer getSesion() {
		return sesion;
	}

	/**
	 * Set para actualizar el id de la sesion a la que pertenece la butaca
	 * @param idSesion -int id de la sesion correspondiente
	 */
	public void setSesion(SesionTransfer sesion) {
		this.sesion = sesion;
	}

	/**
	 * Get que devuelve la id de la reserva realizada por el usuario
	 * @return -int la id de la reserva
	 */
	public int getIdReserva() {
		return idReserva;
	}

	/**
	 * Set que modifica la id de la reserva
	 * @param idReserva -int id de la nueva reserva
	 */
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	/**
	 * Get que devuelve el lugar en el que se encuentra el asiento
	 * @return -Asiento lugar exacto de la butaca
	 */
	public Asiento getLugar() {
		return lugar;
	}

	/**
	 * Set que cambia la posicion del asiento
	 * @param lugar -Asiento (fila, columna)
	 */
	public void setLugar(Asiento lugar) {
		this.lugar = lugar;
	}
	
	public String toString() {
		  return "Identificador de la reserva: " + this.getIdReserva() + Utilidades.LINE_SEPARATOR+
		    "Sesion: " + this.getSesion().toString() + Utilidades.LINE_SEPARATOR +
		    "Butaca: " + this.getLugar().toString(); 
		 }

}
