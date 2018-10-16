package DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.Exceptions.AlreadyExists;
import DAO.Exceptions.ConnectionFailure;
import DAO.Exceptions.DatabaseError;
import AppDataProgramFilms.Asiento;
import Transfer.ButacaTransfer;
import Transfer.ObjetoTransfer;
import Transfer.SesionTransfer;

/**
 * Esta clase se encarga del tratamiento de las butacas de una de las salas del cine en una determinada sesion en relacion 
 * con la base de datos.
 * 
 * Los nombres de las variables en la base de datos y su relación con las bariables del programa son las siguientes:
 * 
 * BD --> ID_RESERVA (INT) FK <--> idReserva <-- programa
 * BD --> FILA (INT) PK <--> fila <-- programa
 * BD --> COLUMNA (INT) PK <--> Columna <-- programa
 * SESION
 * BD --> SESION_FECHA_HORA (DATATIME) PK <--> fecha <-- programa
 * BD --> SESION_ID_SALA (INT) PK <-->  idSala <-- programa
 * BD --> SESION_TITULO_PELICULA (VARCHAR(100)) PK <--> tituloPelicula <-- programa
 * 
 * 
 * @author MIGUEL
 *
 */
public class DAOButaca extends DAO{
	

	
	
	/**
	 * Esta función se encarga de dar de alta a una butaca por medio del objeto Transfer que se le pasa como parámetro, y que
	 * sera transformado en un objeto de tipo ButacaTransfer dentro de la misma.
	 * 
	 * Esta funcion puede generar dos tipos de excepciones, una ConnectionFailure, causada por un fallo de conexión con la base de datos y
	 * otra AlredyExists causada porque el administrados ya se encuentre en la base de datos como tal.
	 * 
	 * @param o
	 * @throws ConnectionFailure
	 * @throws AlreadyExists
	 */
	@Override
	public void alta(ObjetoTransfer o) throws ConnectionFailure, AlreadyExists {
		
		
		PreparedStatement sentencia;
		try 
		{
			//Establecemos la conexion
			sentencia = DAO.getConnection().prepareStatement("INSERT INTO butacas " +
					"(ID_RESERVA, FILA, COLUMNA, SESION_FECHA_HORA, SESION_ID_SALA, SESION_TITULO_PELICULA) VALUES (?,?,?,?,?,?)");
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try 
		{	
			ButacaTransfer butaca = (ButacaTransfer)o;
			sentencia.setInt(1, butaca.getIdReserva());
			sentencia.setInt(2, butaca.getLugar().getFila());
			sentencia.setInt(3, butaca.getLugar().getColumna());
			sentencia.setString(4,butaca.getSesion().getFecha());
			sentencia.setInt(5, butaca.getSesion().getIdSala());
			sentencia.setString(6, butaca.getSesion().getTituloPelicula());
			
			
			sentencia.executeUpdate();
			sentencia.close();
			
		} catch (SQLException e) {
			throw new AlreadyExists("Esa butaca ya esta ocupada",e.getCause());
		}
		
	}
	
	/**
	 * Esta funcion se encarga de dar de baja a una butaca, recibe como parametro de entrada un id,
	 * y dentro de la funcion se elimina de la base de datos toda la información sobre el mismo.
	 * 
	 * Esta funcion puede generar dos tipos de excepciones, una ConnectionFailure, causada por un fallo de conexión con la base de datos y
	 * otra DatabaseError causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador.
	 * 
	 * Esta funcion no se implementa ya que la base de datos tiene todas las FK con delete on cascade y update on cascade.
	 * Por lo que al borrar una reserva se borraran las butacas asociadas a ella
	 * 
	 * @param id
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 */
	@Override
	public void baja(Object id) throws ConnectionFailure, DatabaseError {
		/*
		Statement sentencia;
		int aux;
		try {
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {	
			aux = (int)id;
			sentencia.executeUpdate("DELETE FROM butacas WHERE ID_RESERVA='" + aux + "'");		
			sentencia.close();
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}
		*/
	}
	
	/**
	 * Esta función se encarga de modificar la informacion de una butaca que se encuentra en la base de datos, recibe como 
	 * parametro de entrada la butaca con las modificaciones que se le quiera realizar, ese parametro sera de tipo ObjetoTransfer,
	 * y luego dentro de esta función se transformara dicho parametro a ButacaTransfer.
	 * 
	 * Las excepciones que puede lanzar son ConnectionFailure, causada por un herror de conexión con la base de datos y
	 * DatabaseError causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador.
	 * 
	 * Esta funcion no tiene ningun uso en esta clase, puesto que en ningun momento vas a poder modificar una butaca la cual 
	 * a sido reservada.
	 * @param o
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 */
	@Override
	public void modificar(ObjetoTransfer o, Object idActual) throws ConnectionFailure, DatabaseError {
		
		/*Statement sentencia;
		try {
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			ButacaTransfer toUpdate = (ButacaTransfer)o;
			sentencia.executeUpdate("UPDATE butacas " +
					"SET ID_SESION='" + toUpdate.getIdSesion() + "', ID_RESREVA='"+toUpdate.getIdReserva()+"'," +
					"WHERE ID_BUTACA='" + toUpdate.getId() + "'");
			sentencia.close();			
			
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}*/
	}

	/**
	 * Esta funcion se encarga de buscar a una butaca en la base de datos, recibe la id del mismo por parametro y 
	 * devuelve el objeto de tipo ButacaTransfer creado con los datos que se han obtenido de la base de datos
	 *
	 * Puede lanzar tres tipos de excepciones ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador
	 * y IOException causadas por errores con las entradas y salidas
	 * 
	 * Esta clase con este tipo de objeto por ahora no tiene sentido, porque nunca vamos a buscar una unica butaca, 
	 * lo que vamos ha hacer es buscar por el numero de la reserva, lo que nos devolverá una lista con todas las entradas 
	 * con ese numero de reserva o en su defecto el ID de la sala en una sesion, lo que nos devolvera los asientos 
	 * reservados en esas sala durante esa sesion
	 * 
	 * @param o
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	@Override
	public ObjetoTransfer buscar(Object id) throws ConnectionFailure, DatabaseError, IOException {
		/*
		Statement sentencia;
		try {			
 			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e){			
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());			
		}
		
		try {
			int idSesion, idReserva, fila, columna, aux;
			aux = (int)id;
			Asiento asiento;
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM butacas WHERE ID_BUTACA='" + aux + "'");
		
			if(resultado.next()) {
				idSesion = resultado.getInt("ID_SESION");
				idReserva = resultado.getInt("ID_RESERVA");
				fila = resultado.getInt("FILA");
				columna = resultado.getInt("COLUMNA");
				asiento = new Asiento(fila, columna);
				
				sentencia.close();
				resultado.close();

				return new ButacaTransfer(columna, idSesion, idReserva, asiento);
			}
			
			sentencia.close();
			resultado.close();
			
			return null;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}*/
		return null;
	}
	
	/**
	 * Esta funcion se encarga de generar una lista de todas las butacas ordenadas por el id de la reserva, reciben como parametro un 
	 * campo por el cual las vamos a buscar y el valor de dicho campo.
	 * 
	 * Se podran dar tres tipos de excepciones: ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador
	 * y IOException causadas por errores con las entradas y salidas
	 * 
	 * La unica utilidad de esta funcion es listar por id de reserva, para saber que butacas tiene reservadas.
	 * 
	 * @param campo
	 * @param valor
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	@Override
	public List<ObjetoTransfer> listarPorInt(String campo, Object valor) throws ConnectionFailure, DatabaseError, IOException {
		
		Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			int idReserva, fila, columna, idSala;
			String tituloPelicula;
			String fechaHora;
			Asiento asiento;
			SesionTransfer sesion;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM butacas WHERE " + campo +"='" + valor + "'");
			while(resultado.next()){
				
				idReserva = resultado.getInt("ID_RESERVA");
				fila = resultado.getInt("FILA");
				columna = resultado.getInt("COLUMNA");
				idSala = resultado.getInt("SESION_ID_SALA");
				fechaHora = resultado.getString("SESION_FECHA_HORA");
				tituloPelicula = resultado.getString("SESION_TITULO_PELICULA");
				
				sesion = new SesionTransfer(idSala, tituloPelicula, fechaHora);
				asiento = new Asiento(fila, columna);
									
				lista.add(new ButacaTransfer(sesion, idReserva, asiento));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	/**
	 * Esta funcion se encarga de generar una lista de todas las butacas ordenadas por el String de la reserva, reciben como parametro un 
	 * campo por el cual las vamos a buscar y el valor de dicho campo.
	 * 
	 * Se podran dar tres tipos de excepciones: ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador
	 * y IOException causadas por errores con las entradas y salidas
	 * 
	 * Esta funcion no tienen ninguna utilidad, se implementa por la interfaz, por ello devuelve null.
	 * 
	 * @param campo
	 * @param valor
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	@Override
	public List<ObjetoTransfer> listarPorString(String campo, Object valor) throws ConnectionFailure, DatabaseError, IOException {
		Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			int idReserva, fila, columna, idSala;
			String tituloPelicula;
			String fechaHora;
			Asiento asiento;
			SesionTransfer sesion;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM butacas WHERE " + campo +"=" + DAO.comillas + valor + DAO.comillas);
			while(resultado.next()){
				
				idReserva = resultado.getInt("ID_RESERVA");
				fila = resultado.getInt("FILA");
				columna = resultado.getInt("COLUMNA");
				idSala = resultado.getInt("SESION_ID_SALA");
				fechaHora = resultado.getString("SESION_FECHA_HORA");
				tituloPelicula = resultado.getString("SESION_TITULO_PELICULA");
				
				sesion = new SesionTransfer(idSala, tituloPelicula, fechaHora);
				asiento = new Asiento(fila, columna);
									
				lista.add(new ButacaTransfer(sesion, idReserva, asiento));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	/**
	 * Esta funcion se encarga de generar una lista de todas las butacas ordenadas, reciben como parametro un 
	 * campo por el cual las vamos a buscar y el valor de dicho campo.
	 * 
	 * Se podran dar tres tipos de excepciones: ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causada por errores en la base de datos y IOException causadas por errores con las entradas y salidas
	 * 
	 * Esta funcion no tienen ninguna utilidad, puesto que por ahora nunca nos va a interesar listar todas butacas reservadas
	 * en todas las salas durante todas sesiones, por ello devuelve null.
	 * 
	 * @param campo
	 * @param valor
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	@Override
	public List<ObjetoTransfer> listar() throws ConnectionFailure, DatabaseError, IOException {
		/*Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			int idSesion, idReserva, id, fila, columna;
			Asiento asiento;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM butacas");
			while(resultado.next()){
				id = resultado.getInt("ID_BUTACA");
				idSesion = resultado.getInt("ID_SESION");
				idReserva = resultado.getInt("ID_RESERVA");
				fila = resultado.getInt("FILA");
				columna = resultado.getInt("COLUMNA");
				asiento = new Asiento(fila, columna);
									
				lista.add(new ButacaTransfer(id, idSesion, idReserva, asiento));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}*/
		return null;
	}
	
	

}
