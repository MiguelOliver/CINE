/*
 * Casting de un tipo de lista a otro, para pablo.
 * 
 * (List<PeliculaTransfer>) (List<?>)this.daoPelicula.listar();
 * Cast de List<ObjetoTransfer> a List<Pelicula Transfer>
 */

package AppService;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

import Transfer.PeliculaTransfer;
import Transfer.SalaTransfer;
import Transfer.SesionTransfer;
import Transfer.SocioTransfer;
import Controlador.Controlador;
import DAO.DAOPelicula;
import DAO.DAOSala;
import DAO.DAOSesion;
import DAO.DAOSocio;
import DAO.Exceptions.AlreadyExists;
import DAO.Exceptions.ConnectionFailure;
import DAO.Exceptions.DatabaseError;

/**
 * 
 * @author AppDataProgramFilms
 *	
 * Las funciones devuelven null si no se ha encontrado lo que se buscaba o si ocurrio algun tipo de problema 
 * en la lectura
 */
public class AppServiceNoLoggeado extends Observable implements AppServiceInterfaz{
	
	//ATRIBUTOS
	
	private static DAOPelicula daoPelicula = new DAOPelicula();
	private static DAOSesion daoSesion = new DAOSesion();
	private static DAOSocio daoSocio = new DAOSocio();
	private static DAOSala daoSala =  new DAOSala();
	
	//METODOS
	
	//PELICULA
	
	@SuppressWarnings("unchecked")
	public List<PeliculaTransfer> listarPeliculas() {

		try {
			
			return (List<PeliculaTransfer>)(List<?>)AppServiceNoLoggeado.daoPelicula.listar();
			
		} catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		} catch (DatabaseError e) {
			Controlador.mensajeError("Error en la base de datos, contante con los tecnicos");
		} catch (IOException e) {
			Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
	}
	
	@Override
	public PeliculaTransfer buscarPelicula(String id){
		
		try {
			
			return (PeliculaTransfer) AppServiceNoLoggeado.daoPelicula.buscar(id);
		
		} catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		} catch (DatabaseError e) {
			Controlador.mensajeError("Error en la base de datos, contante con los tecnicos");
		} catch (IOException e) {
			Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
		
	}
	
	//SESIONES
	
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
					
			return (List<SesionTransfer>)(List<?>)AppServiceNoLoggeado.daoSesion.listarPorString("TITULO_PELICULA", tituloPelicula);
			
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
			
			AppServiceNoLoggeado.daoSocio.alta(socio);
			Controlador.mensajeAviso("Dado de alta correctamente");
			
		} catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		} catch (AlreadyExists e) {
			Controlador.mensajeError("NIF existente");
		}
		
	}
	
	//SALA
	

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
	
	

}
