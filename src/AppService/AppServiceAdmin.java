package AppService;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

import Controlador.Controlador;
import DAO.DAOAdmin;
import DAO.DAOPelicula;
import DAO.DAOSala;
import DAO.DAOSesion;
import DAO.DAOSocio;
import DAO.Exceptions.AlreadyExists;
import DAO.Exceptions.ConnectionFailure;
import DAO.Exceptions.DatabaseError;
import Transfer.AdminTransfer;
import Transfer.PeliculaTransfer;
import Transfer.SalaTransfer;
import Transfer.SesionTransfer;
import Transfer.SocioTransfer;

/**
 * Clase correspondiente al AppService del administrador, es decir, contiene las funcionalidades que puede realizar el
 * administador. Implementa AppServiceInterfaz, la interfaz de la aplicacion de servicios
 * @author AppDataProgramFilms
 */
public class AppServiceAdmin extends Observable implements AppServiceInterfaz{

	//ATRIBUTOS
	
	private static DAOPelicula daoPelicula = new DAOPelicula();
	private static DAOSesion daoSesion = new DAOSesion();
	private static DAOSocio daoSocio = new DAOSocio();
	private static DAOSala daoSala = new DAOSala();
	private static DAOAdmin daoAdmin = new DAOAdmin();
	private static AdminTransfer admin;
	
	//GETTER Y SETTERS
	
	public static AdminTransfer getAdmin() {
		return admin;
	}

	public static void setAdmin(AdminTransfer admin) {
		AppServiceAdmin.admin = admin;
	}
	
	
	//METODOS

	//LOGIN
	/**
	 * Comprueba si existe el administrador dado por los argumentos
	 * @param id - la id del administrador
	 * @param usuario - el nombre de usuario del administrador
	 * @param password - la contrasenya
	 * @return true si se ha conectado un administrador y false en caso contrario
	 */
	public boolean comprobarAdmin(String id, char[] password){
	
		AdminTransfer admin;
		try {
			admin = (AdminTransfer)AppServiceAdmin.daoAdmin.buscar(id);
			
			if(admin != null && password(admin.getContrasenya().toCharArray(), password)){
				AppServiceAdmin.setAdmin(admin);
				return true;
			}
			else
				return false;
			
			} 
		
			catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
			} 
			catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
			} 
			catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
			}
		
		return false;
	}

	private static boolean password( char[] adminPass, char[] inPass){
		if(adminPass.length != inPass.length) return false;
		
		for(int i=0; i<adminPass.length; ++i){
			if(adminPass[i] != inPass[i]) return false;
		}
		return true;
	}
	
	//PELICULAS
	
	/**
	 * Da de alta una pelicula. En caso de que ya exista, no tendra comportamiento
	 * @param peli - el transfer de la pelicula que se quiere anyadir
	 */
	public void altaPelicula(PeliculaTransfer peli) {

		try {
			
			AppServiceAdmin.daoPelicula.alta(peli);
			Controlador.mensajeAviso("Pelicula dada de alta correctamente");
			
		} 
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (AlreadyExists e) {
			   Controlador.mensajeError("Pelicula existente");
		}
		
	}

	
	/**
	 * Da de baja una pelicula. En caso de que no exista, no tendra comportamiento
	 * @param titulo - el titulo de la pelicula que se quiera dar de baja
	 */
	public void bajaPelicula(String titulo) {

		try {
			
			PeliculaTransfer p = (PeliculaTransfer) AppServiceAdmin.daoPelicula.buscar(titulo);
			if(p != null){
				AppServiceAdmin.daoPelicula.baja(p.getTitulo());
				Controlador.mensajeAviso("Pelicula eliminada correctamente");
			}
			else {
				Controlador.mensajeError("Error, pelicula no encontrada");
			}
			
		} 
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		
	}


	/**
	 * Modifica la pelicula que recibe como parametro. En caso de que un campo no se rellene, no se modificara dicho campo
	 * @param peli - el transfer de la pelicula que se desee modificar
	 */
	public void modificarPelicula(PeliculaTransfer peli, String tituloActual) {

		try {
			PeliculaTransfer p = (PeliculaTransfer)daoPelicula.buscar(tituloActual);
			if(p!=null){
				AppServiceAdmin.daoPelicula.modificar(peli, tituloActual);
				Controlador.mensajeAviso("Modificacion realizada, compruebe que los cambios son correctos");
			}
			else{
				Controlador.mensajeError("Error, pelicula no encontrada");
			}
		} 
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		
	}
	
	
	/**
	 * Crea una lista con todas las peliculas
	 * @return la lista de tranfers de las peliculas
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PeliculaTransfer> listarPeliculas() {

		try {
			
			return (List<PeliculaTransfer>)(List<?>)AppServiceAdmin.daoPelicula.listar();
		
		} 
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
		
	}
	
	
	/**
	 * Busca y devuelve la pelicula con el id que recibe como parametro
	 * @param id - la id de la pelicula a buscar
	 * @return el transfer de la pelicula si ha sido encontrada y null en otro caso
	 */
	@Override
	public PeliculaTransfer buscarPelicula(String id){
		
		try {
			
			return (PeliculaTransfer) AppServiceAdmin.daoPelicula.buscar(id);

		} 
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
		
	}

	//SOCIO
	
	/**
	 * Da de alta el socio que recibe como parametro. En caso de que ya exista, no tendra comportamiento
	 * @param socio - el transfer del socio que se desee dar de alta
	 */
	public void altaSocio(SocioTransfer socio) {
		
		try {
			
			AppServiceAdmin.daoSocio.alta(socio);
			Controlador.mensajeAviso("Dado de alta correctamente");
			
		} 
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (AlreadyExists e) {
			   Controlador.mensajeError("Socio existente (NIF existente)");
		}
		
	}
	
	
	/**
	 * Da de baja el socio con el NIF que recibe como parametro. En caso de que no exista no tendra comportamiento
	 * @param NIF - nif del socio que se desee dar de baja
	 */
	public void bajaSocio(String NIF) {
		try {
			
			SocioTransfer s = (SocioTransfer) AppServiceAdmin.daoSocio.buscar(NIF);
			if(s != null){
				AppServiceAdmin.daoSocio.baja(s.getNIF());
				Controlador.mensajeAviso("Socio eliminado correctamente");
			}
			else{

				Controlador.mensajeAviso("Socio no encontrado");
			}

		} 
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
	}


	/**
	 * Modifica la informacion del socio que recibe como parametro
	 * @param socio - el transfer del socio que se quiera modificar 
	 */
	public void modificarSocio(SocioTransfer socio, String NIFActual) {
		
		try {
			AppServiceAdmin.daoSocio.modificar(socio,  NIFActual);
			Controlador.mensajeAviso("Modificacion realizada, compruebe que los cambios son correctos");
			
		} 
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		}
		
	}


	public SocioTransfer buscarSocio(String NIF) {

		try {
			SocioTransfer s = (SocioTransfer)daoSocio.buscar(NIF);
			if(s != null){
				return s;
			}
			else{
				Controlador.mensajeAviso("Socio no encontrado");
			}
		} 
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SocioTransfer> listarSocios() {

		try {
			return (List<SocioTransfer>)(List<?>)daoSocio.listar();
		} 	catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
	}
	//SALA
	
	/**
	 * Da de alta la sala que recibe como parametro. En caso de que ya exista, no tendra comportamiento
	 * @param sala - el transfer de la sala que se quiere dar de alta
	 */
	public void altaSala(SalaTransfer sala) {
		
		try {
			
			AppServiceAdmin.daoSala.alta(sala);
			Controlador.mensajeAviso("Sala dada de alta correctamente");
			
		} 
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (AlreadyExists e) {
			   Controlador.mensajeError("Sala existente");
		}
	}

	
	/**
	 * Da de baja la sala con el id que recibe como parametro. En caso de que no exista no tendra comportamiento
	 * @param id - la id de la sala que se quiere dar de baja
	 */
	public void bajaSala(int id) {
		try {
			SalaTransfer s = (SalaTransfer) daoSala.buscar(id);
			if(s != null){
				AppServiceAdmin.daoSala.baja(id);
				Controlador.mensajeAviso("Sala eliminada correctamente");
			}
			else{

				Controlador.mensajeAviso("Sala no encontrada");
			}
			
		}
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
	}

	public SalaTransfer buscarSala(int id) {
		
		SalaTransfer s;
		try {
			s = (SalaTransfer)daoSala.buscar(id);
			if(s != null){
				return s;
			}
			else{
				Controlador.mensajeAviso("Error, sala no encontrada");
			}
		}
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		
		return null;
	}

	/**
	 * Modifica la sala de acuerdo a los parametros que recibe siempre que exista, sino no tendra comportamiento
	 * @param id - la id de la sala a modificar
	 * @param filas - el nuevo numero de filas de la sala
	 * @param columnas - el nuevo numero de columnas de la sala
	 */
	public void modificarSala(int id, int filas, int columnas, int sActual) {
		
		try {
			SalaTransfer s = (SalaTransfer) AppServiceAdmin.daoSala.buscar(sActual);
			if(s != null){
				s.setColumnas(columnas);
				s.setFilas(filas);
				s.setAforo(columnas*filas);
				s.setId(id);
				AppServiceAdmin.daoSala.modificar(s, sActual);
				Controlador.mensajeAviso("Modificacion realizada, compruebe que los cambios son correctos");
			}
			else{
				Controlador.mensajeError("Sala no encontrada");
			}

		} catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalaTransfer> listarSalas() {
		try {
			return (List<SalaTransfer>)(List<?>)daoSala.listar();
		} catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
	}

	//SESION

	/**
	 * Da de alta una nueva sesion de acuerdo a los parametros que recibe. Si existe no tendra comportamiento
	 * @param idSala - la id de la sala a la que haga referencia la sesion
	 * @param tituloPeli - el titulo de la pelicula que se emitira en la sesion
	 * @param horario - el horario que tendra la sesion
	 */
	public void altaSesion(SesionTransfer sesion) {

		try {
			
			AppServiceAdmin.daoSesion.alta(sesion);
			Controlador.mensajeAviso("Sesion dada de alta correctamente");
			
		}
		catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		}catch (AlreadyExists e) {
			   Controlador.mensajeError("Sesion existente");
		}
	}


	/**
	 * Da de baja una sesion. En caso de que no exista no tendra comportamiento
	 * @param id - la id de la sesion que se desea dar de baja
	 */
	public void bajaSesion(SesionTransfer sesion) {
		try {
			
			SesionTransfer s =  (SesionTransfer)daoSesion.buscar(sesion);
			if(s != null){
				AppServiceAdmin.daoSesion.baja(sesion);
				Controlador.mensajeAviso("Sesion dada de baja correctamente");
			}
			else{
				Controlador.mensajeAviso("Sesion no encontrada");
			}
			
			
		} 
		catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} catch (IOException e) {
			Controlador.mensajeError("Error, vuelva a intentarlo");
		}
	}

	/**
	 * Devuelve una lista con todas las sesiones de la pelicula cuyo titulo recibe como parametro. Si la pelicula no existe
	 * retornara null
	 * @param tituloPelicula - el titulo de la pelicula de la que se desean saber todas sus sesiones
	 * @return una lista con todas las sesiones de la pelicula o null si la pelicula no existe
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SesionTransfer> listarSesiones(String tituloPelicula) {
		try {
					
			return (List<SesionTransfer>)(List<?>)AppServiceAdmin.daoSesion.listarPorString("TITULO_PELICULA", tituloPelicula);
			
		}
		catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
		
	}

	/**
	 * Devuelve una lista con todas las sesiones de la sala que se recibe como paremetroa.
	 * @param Id de la sala - el id de la sala de la que se desean saber todas sus sesiones
	 * @return una lista con todas las sesiones de la sala o null si la sala no existe
	 */
	@SuppressWarnings("unchecked")
	public List<SesionTransfer> listarSesionesSala(int id) {
		try {
					
			return (List<SesionTransfer>)(List<?>)AppServiceAdmin.daoSesion.listarPorInt("ID_SALA", id);
			
		}
		catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
		
	}
	
	//ADMIN 
	
	public void modificarAdmin(String user, String pass) {
		
		try {
			
			AdminTransfer a = (AdminTransfer) AppServiceAdmin.daoAdmin.buscar(user);
			if(a!=null){	
				a.setContrasenya(pass);
				AppServiceAdmin.daoAdmin.modificar(a, AppServiceAdmin.admin.getId());
				Controlador.mensajeAviso("Administrador modificado correctamente");
			}
			else{
				Controlador.mensajeError("Nombre de usuario incorrecto");
			}
		} 
		catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		}
		catch (IOException e) {
			Controlador.mensajeError("Error, vuelva a intentarlo");
		} 
		
	}

}
