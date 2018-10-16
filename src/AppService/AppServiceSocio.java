package AppService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import AppDataProgramFilms.Asiento;
import Controlador.Controlador;
import DAO.DAOButaca;
import DAO.DAOPelicula;
import DAO.DAOReserva;
import DAO.DAOSala;
import DAO.DAOSesion;
import DAO.DAOSocio;
import DAO.DAOValoracion;
import DAO.Exceptions.AlreadyExists;
import DAO.Exceptions.ConnectionFailure;
import DAO.Exceptions.DatabaseError;
import Transfer.ButacaTransfer;
import Transfer.PeliculaTransfer;
import Transfer.ReservaTransfer;
import Transfer.SalaTransfer;
import Transfer.SesionTransfer;
import Transfer.SocioTransfer;
import Transfer.ValoracionTransfer;
import Vistas.Vista;

/**
 * 
 * @author 
 *
 */
public class AppServiceSocio extends Observable implements AppServiceInterfaz{
	
	//ATRIBUTOS
	
	private static SocioTransfer socio;
	private static DAOPelicula daoPelicula = new DAOPelicula();
	private static DAOSocio daoSocio = new DAOSocio();
	private static DAOSesion daoSesion = new DAOSesion();
	private static DAOReserva daoReserva = new DAOReserva();
	private static DAOValoracion daoValoracion = new DAOValoracion();
	private static DAOButaca daoButaca = new DAOButaca();
	private static DAOSala daoSala = new DAOSala();
	
	//CONTRUCTORES
	
	public AppServiceSocio(SocioTransfer socio) {
		AppServiceSocio.setSocio(socio);
	}
	
	
	//GETTERS Y SETTERS
	
	public static SocioTransfer getSocio() {
		return socio;
	}

	public static void setSocio(SocioTransfer socio) {
		AppServiceSocio.socio = socio;
	}
	
	//METODOS 
	
	public static boolean comprobarSocio(String NIF, char[] password) {
		
		SocioTransfer socio;
		try {
			socio = (SocioTransfer)AppServiceSocio.daoSocio.buscar(NIF);
			
			if(socio != null){
				if(socio.getNIF().equals(NIF) && password(socio.getConstrasenya().toCharArray(), password)){
					AppServiceSocio.setSocio(socio);
					return true;
				}
				else
					return false;
			}
			else
				return false;
		} 
		catch (ConnectionFailure e) {
			   Vista.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Vista.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			   Vista.mensajeError("Error, vuelva a intentarlo");
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
	
	//PELICULA
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PeliculaTransfer> listarPeliculas() {

		try {
			
			return (List<PeliculaTransfer>)(List<?>)AppServiceSocio.daoPelicula.listar();
		
		} 
		catch (ConnectionFailure e) {
			   Vista.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Vista.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			   Vista.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
		
	}
	
	@Override
	public PeliculaTransfer buscarPelicula(String id){
		
		try {
			
			return (PeliculaTransfer) AppServiceSocio.daoPelicula.buscar(id);
		
		} 
		catch (ConnectionFailure e) {
			   Vista.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			   Vista.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			   Vista.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
		
	}
	
	public void valorarPelicula(int valoracion, String tituloPeli) {
		
		try {
			
			if(AppServiceSocio.daoValoracion.buscar(new ValoracionTransfer(AppServiceSocio.socio.getNIF(), tituloPeli , valoracion)) == null){
				AppServiceSocio.daoValoracion.alta(new ValoracionTransfer(AppServiceSocio.socio.getNIF(), tituloPeli , valoracion));
			}
			else{
				Controlador.mensajeError("Usted ya ha valorado esta pelicula");
			}
			
		}
		catch (ConnectionFailure e) {
			Vista.mensajeError("Error al conectar con la base de datos");
		} 
		catch (AlreadyExists e) {
			   Vista.mensajeError("NIF existente");
		}
		catch (DatabaseError e) {
			   Vista.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} 
		catch (IOException e) {
			   Vista.mensajeError("Error, vuelva a intentarlo");
		} 
		
		
	}
	
	//RESERVAS
	
	public void reservar(String tituloPeli, SesionTransfer sesion, List<Asiento> listaAsientos) {
		
		List<ButacaTransfer> listaButacas = new ArrayList<ButacaTransfer>();
		ReservaTransfer r = new ReservaTransfer(AppServiceSocio.socio.getNIF());
		
		try {
			AppServiceSocio.daoReserva.alta(r);
			for(int i=0; i<listaAsientos.size(); i++){
			
					ButacaTransfer b = new ButacaTransfer((SesionTransfer)sesion,(int) r.getId(), listaAsientos.get(i));
					listaButacas.add(b);
					AppServiceSocio.daoButaca.alta(b);
			}

			Controlador.mensajeAviso("Reserva realizada");
		} 
		catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (AlreadyExists e) {
			Controlador.mensajeError("Butaca ocupada");
		}
		
	}
	
	public void cancelarReserva(int reserva) {
		
		try {
			
			AppServiceSocio.daoReserva.baja(reserva);
			Controlador.mensajeAviso("Reserva candelada con exito");
			
		}
		catch (ConnectionFailure e) {
			Controlador.mensajeError("Error al conectar con la base de datos");
		} 
		catch (DatabaseError e) {
			Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		}
		
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
					
			return (List<SesionTransfer>)(List<?>)AppServiceSocio.daoSesion.listarPorString("TITULO_PELICULA", tituloPelicula);
			
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


	@SuppressWarnings("unchecked")
	public List<ReservaTransfer> listarReservas(String nif) {
		
		try {
			return (List<ReservaTransfer>)(List<?>)AppServiceSocio.daoReserva.listarPorString("SOCIO_NIF", nif);
		}
		catch (ConnectionFailure e) {
			   Controlador.mensajeError("Error al conectar con la base de datos");
		} catch (DatabaseError e) {
			   Controlador.mensajeError("Error en la base de datos, contacte con los tecnicos");
		} catch (IOException e) {
			   Controlador.mensajeError("Error, vuelva a intentarlo");
		}
		return null;
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


	public SesionTransfer buscarSesion(SesionTransfer sesionTransfer) {
		
		SesionTransfer s;
		try {
			s = (SesionTransfer)daoSesion.buscar(sesionTransfer);
			if(s != null){
				return s;
			}
			else{
				Controlador.mensajeAviso("Error, sesion no encontrada");
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

}