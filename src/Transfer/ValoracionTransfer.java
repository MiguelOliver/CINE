package Transfer;

import AppDataProgramFilms.Utilidades;

/**
 * Clase que extiende ObjetoTransfer y encapsula toda la informacion para llevar la valoracion de una pelicula
 * Tendra un campo estatico de tipo entero que ira incrementando para llevar un control del contador de valoraciones
 * LLevara un campo valor, el id de la pelicula y el dni del socio
 * @author Manuel
 *
 */
public class ValoracionTransfer extends ObjetoTransfer{
	//ATRIBUTOS
	
	private int valor;
	private String idPelicula;
	private String nifSocio;
	
	//CONSTRUCTORES
	
	/**
	 * constructor por defecto que inicializa los campos a null y a 0
	 */
	public ValoracionTransfer(){
		super();
		this.valor = 0;
		this.setIdPelicula(null);
		this.setNifSocio(null);
	}
	
	/**
	 * constructor que recibe por parametros el id, el dni del socio, el de la pelicula, y el valor
	 * Este constructor ira incrementando cada vez que se cree una valoracion el contador estatico
	 * @param dniSocio -String
	 * @param idPelicula -int
	 * @param valor -int
	 */
	public ValoracionTransfer(String dniSocio, String idPelicula, int valor){
		super();
		this.idPelicula = idPelicula;
		this.setNifSocio(dniSocio);
		this.valor = valor;
	}
	
	//GETTERS Y SETTERS
	
	/**
	 * get que devuelve el valor de la valoracion
	 * @return -int valor
	 */
	public int getValor() {
		return valor;
	}
	
	/**
	 * set para actualizar el valor de la valoracion
	 * @param valor -int
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	/**
	 * get para obtener el id de la pelicula
	 * @return -int
	 */
	public String getIdPelicula() {
		return idPelicula;
	}
	
	/**
	 * set para actualizar el id de la pelicula
	 * @param idPelicula -int
	 */
	public void setIdPelicula(String idPelicula) {
		this.idPelicula = idPelicula;
	}

	/**
	 * get para obtener el DNI del socio
	 * @return -String dniSocio
	 */
	public String getNifSocio() {
		return nifSocio;
	}

	/**
	 * set para inicializar el dni del socio
	 * @param dniSocio -String
	 */
	public void setNifSocio(String nifSocio) {
		this.nifSocio = nifSocio;
	}
	
	public String toString() {
		  return  Utilidades.LINE_SEPARATOR + "Pelicua: " + this.getIdPelicula() + "  " +
		    "Valor: " + this.getValor() + Utilidades.LINE_SEPARATOR;
		 }
	
}
