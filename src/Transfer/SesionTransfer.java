package Transfer;

import java.util.ArrayList;
import java.util.List;

import AppDataProgramFilms.Utilidades;

/**
 * Clase que extiende ObjetoTransfer y es la encargada de encapsular todos los atributos relacionados con una sesion <br>
 * Contiene un entero estatico que se ira incrementando para llevar el recuento del numero de sesiones <br<
 * un id de la sala a la que pertenece esta sesion, la fecha de la sesion, el id de la pelicula de la sesion <br>
 * tendr� adem�s una lista con todas las butacas ocupadas en la sesion
 * @author MIGUEL
 *
 */
public class SesionTransfer extends ObjetoTransfer{

	//ATRIBUTOS
	
	private int idSala;
	private String fecha;
	private String tituloPelicula;
	private List<ButacaTransfer> butacasOcupadas;
	
	//CONSTRUCTORES
	
	/**
	 * constructor por defecto que inicializa todos los campos a null o a 0 en caso de enteros
	 */
	public SesionTransfer(){
		super();
		this.tituloPelicula = null;
		this.idSala = 0;
		this.fecha = null;
		this.butacasOcupadas = null;
	}
	
	/**
	 * constructor que recibe la id de la sala y de la pelicula y la fecha de la sesion(hora) <br>
	 * cada vez que se cree una sesion se incrementara el contador estatico de sesiones para actualizar el numero de sesiones
	 * Crea uan lista dinamica de butacas ocupadas vacia con hueco inicial de 10 posiciones (arrayList)
	 * @param idSala -int
	 * @param idPelicula -int
	 * @param f -String
	 */
	public SesionTransfer(int idSala, String pelicula, String f){
		super();
		this.tituloPelicula = pelicula;
		this.idSala = idSala;
		this.fecha = f;
		this.butacasOcupadas = new ArrayList<ButacaTransfer>();
	}

	/**
	 * constructor que recibe por parametros todos los campos de la sesion. <br>
	 * @param idSala -int
	 * @param idPelicula -int
	 * @param f -String
	 * @param lista -List
	 */
	public SesionTransfer(int idSala, String pelicula, String f, List<ButacaTransfer> lista){
		super();
		this.tituloPelicula = pelicula;
		this.idSala = idSala;
		this.fecha = f;
		this.butacasOcupadas = lista;
	}
	
	
	//GETTERS Y SETTERS

	/**
	 * get para obtener la lista de las butacas ocupadas de la sesion
	 * @return -List butacasOcupadas
	 */
	public List<ButacaTransfer> getButacasOcupadas() {
		return butacasOcupadas;
	}
	
	/**
	 * set para actualizar la lista de las butacas ocupadas de la sesion
	 * @param butacasOcupadas -List
	 */
	public void setButacasOcupadas(List<ButacaTransfer> butacasOcupadas) {
		this.butacasOcupadas = butacasOcupadas;
	}
	
	/**
	 * get que devuelve la id de la sala correspondiente a la sala donde se proyecta la pelicula
	 * @return -int idSala
	 */
	public int getIdSala() {
		return idSala;
	}
	
	/**
	 * set que actualiza el id de la sala a la que pertenece la sesion
	 * @param idSala -int
	 */
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	/**
	 * get que devuelve la fecha (dia y hora) de la sesion correspondiente
	 * @return -String fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * set para actualizar la fecha de la sesion
	 * @param fecha -String
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * get para obtener el id de la pelicula que se proyecta
	 * @return -int idPelicula
	 */
	public String getTituloPelicula() {
		return tituloPelicula;
	}
	
	/**
	 * set que actualiza el id de la pelicula de la sesion
	 * @param idPelicula -int
	 */
	public void setTituloPelicula(String titulo) {
		this.tituloPelicula = titulo;
	}
	
	public String toString() {
		  return "Titulo de la pelicula: " + this.getTituloPelicula() + Utilidades.LINE_SEPARATOR +
		    "Sala: " + this.getIdSala() + Utilidades.LINE_SEPARATOR +
		    "Fecha: " + fecha + Utilidades.LINE_SEPARATOR +
		    "Butaca ocupadas: " + this.butacasOcupadas.toString() + Utilidades.LINE_SEPARATOR;
		 }
	
	public String toStringReducido() {
		  return "Sala: " + this.getIdSala() + " " + Utilidades.LINE_SEPARATOR +
		    "Fecha: " + fecha;
		 }
}