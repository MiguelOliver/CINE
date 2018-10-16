package Transfer;


import AppDataProgramFilms.Utilidades;
import Enumerados.Genero;

/**
 * clase que hereda de objeto transfer y a�ade los parametros de una pelicula <br>
 * a�ade todos los campos propios de una pelicula tales como:
 * <ul>
 * <li> un int estatico que sirve para identificar cada pelicula </li>
 * <li> un string que es el titulo de la pelicula </li>
 * <li> un genero (tipo Genero enumerado) al cual pertenece la pelicula </li>
 * <li> un string sinopsis que contiene un breve resumen de la trama de la pelicula </li>
 * <li> un String(tipo definido en Java) que contiene la fecha del estreno </li>
 * <li> un string actores con los nombres de los principales actores de la pelicula </li>
 * <li> un string director con el nombre del director de la pelicula </li>
 * <li> un int duracion con el numero de minutos de la pelicula </li>
 * <li> un int valoracion que tiene un numero entero del 1 al 10 con la valoracion media de los usuarios </li> 
 * </ul>
 * @author administrador
 */

public class PeliculaTransfer extends ObjetoTransfer {

	//ATRIBUTOS
	private String titulo;
	private Genero genero;
	private String synopsis;
	private String fechaEstreno;
	private String actores;
	private String director;
	private int duracion;
	private int valoracion;

	
	
	//CONSTRUCTORES

	/**
	 * constructor por defecto que inicializa la pelicula vacia (tipo genero desconocido)
	 */
	public PeliculaTransfer(){
		super();
		this.titulo = null;
		this.actores = null;
		this.director = null;
		this.fechaEstreno = null;
		this.genero = null;
		this.synopsis = null;
		this.titulo = null;
		this.valoracion = 0;
	}
	
	/**
	 * constructor que recibe los parametros actualizandolos
	 * @param id -int id caracteristico de la pelicula
	 * @param titulo -string titulo de la pelicula
	 * @param genero -Genero enumerado con el genero
	 * @param synopsis -String resumen de la pelicula
	 * @param fecha -String con la fecha del estreno
	 * @param actores -String nombres de los principales actores
	 * @param director -String director el director de la pelicula
	 * @param duracion -int total de minutos de la pelicula
	 * @param valoracion -int (1 al 10) valoracion media de la pelicula
	 */
	public PeliculaTransfer(String titulo, Genero genero, String synopsis, String fecha, 
							String actores, String director, int duracion, int valoracion) {
		super();
		this.titulo = titulo;
		this.actores = actores;
		this.director = director;
		this.fechaEstreno = fecha;
		this.genero = genero;
		this.synopsis = synopsis;
		this.duracion = duracion;
		this.valoracion = valoracion;
	}
	
	//GETTERS Y SETTERS

	/**
	 * get que devuelve el titulo de la pelicula
	 * @return -string titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * set para actualizar el campo titulo de la pelicula
	 * @param titulo -string nuevo titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 * get que devuelve el genero de la pelicula
	 * @return -genero
	 */
	public Genero getGenero() {
		return genero;
	}
	
	/**
	 * set para sobreescribir el genero de la pelicula
	 * @param -genero
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	/**
	 * get para obtener la sinopsis de la pelicula
	 * @return -String sinopsis de la pelicula
	 */
	public String getSynopsis() {
		return synopsis;
	}
	
	/**
	 * set para actualizar el campo sinopsis de la pelicula
	 * @param synopsis -String 
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	/**
	 * get para obtener la fecha del estreno de la pelicula
	 * @return -String la fecha del estreno
	 */
	public String getFechaEstreno() {
		return fechaEstreno;
	}
	
	/**
	 * set para actualizar la fecha del estreno de la pelicula
	 * @param fechaEstreno -String
	 */
	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}
	
	/**
	 * get para devolver los nombres de los actores principales
	 * @return -String con los nombres de los actores
	 */
	public String getActores() {
		return actores;
	}
	
	/**
	 * set para sobreescribir el nombre d elos actores principales d ela pelicula
	 * @param actores -String
	 */
	public void setActores(String actores) {
		this.actores = actores;
	}
	
	/**
	 * get para obtener el nombre del director de la pelicula
	 * @return -String el nombre del director
	 */
	public String getDirector() {
		return director;
	}
	
	/**
	 * set para actualizar el nombre del director de la pelicula
	 * @param director -String
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	
	/**
	 * get que devuelve la duracion en minutos de la pelicula
	 * @return -int la duracion(minutos)
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * set para sobreescribir la duracion de la pelicula
	 * @param duracion -int
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * get para obtener la valoracion actual (media) de la pelicula
	 * @return -int la valoracion actual
	 */
	public int getValoracion() {
		return valoracion;
	}

	/**
	 * set para actualizar la valoracion de la pelicula
	 * @param valoracion -int
	 */
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	
	@Override
	public String toString() {
		return "Titulo: " + this.titulo + Utilidades.LINE_SEPARATOR   
				+ "Fecha de Estreno: " + fechaEstreno + Utilidades.LINE_SEPARATOR
				+ "Duracion: " + this.duracion + Utilidades.LINE_SEPARATOR
				+ "Valoracion: " + this.valoracion + Utilidades.LINE_SEPARATOR
				+ "Director: " + this.director + Utilidades.LINE_SEPARATOR
				+ "Actores Principales: " + this.actores + Utilidades.LINE_SEPARATOR
				+ "Sinopsis: " + this.synopsis;	
	}
}
