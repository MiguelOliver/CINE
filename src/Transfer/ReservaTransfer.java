package Transfer;

import java.util.ArrayList;
import java.util.List;

import AppDataProgramFilms.Utilidades;

/**
 * Clase que extiende a ObjetoTransfer y contiene ademas del id heredado los siguientes campos: <br>
 * tendra un entero estatico que sera un contador que ira incrementando de uno en uno para contar todas las reservas <br>
 * un entero con el id del socio correspondiente a la reserva y una lista de todas las butacas reservadas
 * Esta clase encapsula todo lo relacionado con una reserva de un socio determinado
 * @author MIGUEL
 *
 */
public class ReservaTransfer extends ObjetoTransfer{

	//ATRIBUTOS
	public static int contReservas = 0;
	private String nifSocio;
	private List<ButacaTransfer> listaButacas;

	
	
	public static void setCont(int c){
		ReservaTransfer.contReservas = c;
	}
	//CONSTRUCTORES
	
	/**
	 * constructor por defecto que inicializa los campos a 0 y la lista a null
	 */
	public ReservaTransfer(){
		super();
		this.nifSocio = null;
		this.listaButacas = null;
	}
	
	/**
	 * constructor que recibe la id de la reserva y el id del socio (enteros) <br>
	 * El constructor incrementara el contador de reservas cada vez que se cree una de ellas para su control <br>
	 * Crea la lista vacia con un hueco inicial de 10 posiciones (Array dinamico- ArrayList) <br>
	 * @param id -int
	 * @param idSocio -int
	 */
	public ReservaTransfer(String nifSocio){
		super(ReservaTransfer.contReservas++);
		ReservaTransfer.contReservas++;
		this.nifSocio = nifSocio;
		this.listaButacas = new ArrayList<ButacaTransfer>();
	}
	
	/**
	 * constructor que recibe la id, la id del socio y la lista de butacas <br>
	 * El constructor incrementara el contador de reservas cada vez que se cree una de ellas para su control <br>
	 * @param idSocio -int
	 * @param lista -List<ButacaTransfer>
	 */
	public ReservaTransfer(String nifSocio, List<ButacaTransfer> lista){
		super(ReservaTransfer.contReservas++);
		ReservaTransfer.contReservas++;
		this.nifSocio = nifSocio;
		this.listaButacas = lista;
	}
	
	/**
	 * constructor que recibe la id, la id del socio y la lista de butacas <br>
	 * El constructor no incrementara el contador de reservas <br>
	 * @param id -int
	 * @param idSocio -int
	 * @param lista -List<ButacaTransfer>
	 */
	public ReservaTransfer(int id, String nifSocio, List<ButacaTransfer> lista){
		super(id);
		this.nifSocio = nifSocio;
		this.listaButacas = lista;
	}
	
	//GETTERS Y SETTERS	
	
	/**
	 * get que devuelve la id del socio de la reserva
	 * @return -int idSocio
	 */
	public String getNifSocio() {
		return nifSocio;
	}
	
	/**
	 * set para actualizar el id del socio de la reserva
	 * @param idSocio -int
	 */
	public void setIdSocio(String nifSocio) {
		this.nifSocio = nifSocio;
	}
	
	/**
	 * get para obtener toda la lista de butacas reservadas
	 * @return -List listabutacas
	 */
	public List<ButacaTransfer> getListaButacas() {
		return listaButacas;
	}
	
	/**
	 * set para inicializar la lista de butacas con una lista recibida por parametro
	 * @param listaButacas -List
	 */
	public void setListaButacas(List<ButacaTransfer> listaButacas) {
		this.listaButacas = listaButacas;
	}
	
	public String toString() {
		  return "NIF: " + this.getNifSocio() + Utilidades.LINE_SEPARATOR +
		    "Butacas reservadas: " +  this.listaButacas; 
		  }
}
