package Transfer;

import java.util.ArrayList;
import java.util.List;

import AppDataProgramFilms.Utilidades;

/**
 * Clase que extiende a ObjetoTransfer y se encarga de encapsular toda la informacion de un socio <br>
 * Tendra un campo estatico entero que indicara el tipo de usuario (2) para distinguirlo del administrador(1) <br>
 * Sus campos seran strings con el nombre, apellidos, correo, NIF y contrase�a y dos listas con las valoraciones y reservas <br>
 * No tiene implementado el metodo toString, por defecto, en caso de que se use, se utilizara el heredado de object
 * Como no se muestran mensajes por consola y todo se muestra mediante una interfaz grafica (GUI) no es necesario
 * @author Manuel
 *
 */
public class SocioTransfer extends ObjetoTransfer {

	//ATRIBUTOS
	private final static int TIPO_USUARIO = 2;
	private String nombre;
	private String apellidos;
	private String correo;
	private String NIF;
	private String constrasenya;
	private List<ReservaTransfer> listaReservas;
	private List<ValoracionTransfer> listaValoraciones;
	
	
	//CONSTRUCTORES
	
	/**
	 * constructor por defecto que inicializa todos los campos a null
	 */
	public SocioTransfer(){
		super();
		this.nombre = null;
		this.apellidos = null;
		this.constrasenya = null;
		this.correo = null;
		this.NIF = null;
		this.listaReservas = null;
		this.listaValoraciones = null;
	}
	
	/**
	 * constructor que recibe los campos por parametros menos las listas 
	 * Crea las listas de reservas y valoraciones vacias (arrayList)
	 * @param nombre -String
	 * @param apellidos -String
	 * @param correo -String
	 * @param NIF -String
	 * @param contrasenya -String
	 */
	public SocioTransfer(String nombre, String apellidos, String correo, String NIF, String contrasenya){
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.constrasenya = contrasenya;
		this.correo = correo;
		this.NIF = NIF;
		this.listaReservas = new ArrayList<ReservaTransfer>();
		this.listaValoraciones = new ArrayList<ValoracionTransfer>();
	}
	
	/**
	 * constructor que recibe todos los atributos por parametros actualizandolos
	 * @param nombre -String
	 * @param apellidos -String
	 * @param correo -String
	 * @param NIF -String
	 * @param contrasenya -String
	 * @param listaReservas -String
	 * @param listaValoraciones -String
	 */
	public SocioTransfer(String nombre, String apellidos, String correo, String NIF, String contrasenya, List<ReservaTransfer> listaReservas, List<ValoracionTransfer> listaValoraciones){
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.constrasenya = contrasenya;
		this.correo = correo;
		this.NIF = NIF;
		this.listaReservas = listaReservas;
		this.listaValoraciones = listaValoraciones;
	}

	//GETTER Y SETTERS
	
	/**
	 * get para obtener la lista de valoraciones del socio
	 * @return -List listaValoraciones
	 */
	public List<ValoracionTransfer> getListaValoraciones() {
		return listaValoraciones;
	}
	
	/**
	 * set para sobreescribir la lista de valoraciones del socio
	 * @param listaValoraciones -List
	 */
	public void setListaValoraciones(List<ValoracionTransfer> listaValoraciones) {
		this.listaValoraciones = listaValoraciones;
	}
	
	/**
	 * get que devuelve la lista de reservas del socio
	 * @return -List listaReservas
	 */
	public List<ReservaTransfer> getListaReservas() {
		return listaReservas;
	}
	
	/**
	 * set para actualizar la lista de reservas
	 * @param listReservas -List
	 */
	public void setListaReservas(List<ReservaTransfer> listReservas) {
		this.listaReservas = listReservas;
	}
	
	/**
	 * get que devuelve la contrasenya del socio
	 * @return -String contrasenya
	 */
	public String getConstrasenya() {
		return constrasenya;
	}
	
	/**
	 * set que inicializa la contrasenya en caso de que haya que cambiarla
	 * @param constrasenya -String
	 */
	public void setConstrasenya(String constrasenya) {
		this.constrasenya = constrasenya;
	}
	
	/**
	 * get para conseguir el NIF del socio concreto
	 * @return -String NIF
	 */
	public String getNIF() {
		return NIF;
	}
	
	/**
	 * set para actualizar el NIF de un socio
	 * @param NIF
	 */
	public void setNIF(String NIF) {
		this.NIF = NIF;
	}
	
	/**
	 * get para obtener el correo de un socio
	 * @return -String correo
	 */
	public String getCorreo() {
		return correo;
	}
	
	/**
	 * set para actualizar el correo de un determinado socio
	 * @param correo -String
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	/**
	 * get para obtener los apellidos de un socio
	 * @return -String apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	
	/**
	 * set para inicializar los apellidos de un socio
	 * @param apellidos -String
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * get para obtener el nombre de un determiando socio
	 * @return -String nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * set para actualizar el nombre d eun socio
	 * @param nombre -String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * funcion estatica que devuelve el tipo de usuario, en este caso el socio (TIPO_USUARIO = 2)
	 * @return -int TIPO_USUARIO
	 */
	public static int getTipoUsuario() {
		return TIPO_USUARIO;
	}
	
	public String toString() {
		return "Nombre: " + this.nombre + Utilidades.LINE_SEPARATOR 
				+ "Apellidos: " + this.apellidos + Utilidades.LINE_SEPARATOR
				+ "NIF: " + this.NIF + Utilidades.LINE_SEPARATOR
				+ "Correo: " + this.correo + Utilidades.LINE_SEPARATOR
				+ "Password: " + this.constrasenya + Utilidades.LINE_SEPARATOR
				+ "Lista reservas: " + this.listaValoraciones + Utilidades.LINE_SEPARATOR;
				
	}
	
}
