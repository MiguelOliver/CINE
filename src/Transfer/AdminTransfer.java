package Transfer;

/**
 * Clase que extiende a objeto transfer y añade los campos tipo de usuario (administrador = 1),
 * usuario y contraseña.
 * @author administrador
 */
public class AdminTransfer extends ObjetoTransfer{
	
	//ATRIBUTOS
	private final static int TIPO_USUARIO = 1;
	private String usuario;
	private String contrasenya;

	//CONSTRUCTORES
	
	/**
	 * Constructor por defecto que llama al constructor por defecto de objeto transfer e
	 * inicializa el usuario y la contraseña a null
	 */
	public AdminTransfer(){
		super();
		this.usuario = null;
		this.contrasenya = null;
	}
	
	/**
	 * Constructor que recibe todos los parametros para inicializar correctamente el administrador
	 * @param id -int el id del administrador
	 * @param u -string usuario
	 * @param c -string contrasenya
	 */
	public AdminTransfer(int id, String u, String c){
		super(id);
		this.contrasenya = c;
		this.usuario = u;
	}
	
	//GETTERS Y SETTERS
	
	/**
	 * Get que devuelve el usuario
	 * @return -string usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Set que cambia el usuario del administrador
	 * @param usuario -string el nuevo usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Get que devuelve la contraseña
	 * @return -string la contraseña del administrador
	 */
	public String getContrasenya() {
		return contrasenya;
	}
	
	/**
	 * Set que actualiza la contrasenya.
	 * @param contrasenya -string la nueva contrasenya
	 */
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	/**
	 * Funcion estatica que devuelve el tipo estatico de usuario (1 para administrador)
	 * @return -int el tipo de usuario
	 */
	public static int getTipoUsuario() {
		return TIPO_USUARIO;
	}
}
