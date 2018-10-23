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
import Transfer.ButacaTransfer;
import Transfer.ReservaTransfer;
import Transfer.ObjetoTransfer;

/**
 *  Esta clase se encarga de realizar todas las conexxiones que hagan falta entre un usuario administrador y
 * la tabla administrador de la base de datos.
 * Los nombres de las variables en la base de datos y su relación con las bariables del programa son las siguientes:
 * 
 * BD --> ID_RESERVA <--> contReservas --> programa (es autogenerado, por lo que lo mas parecido a una bariable en el 
 * 													 progrma es el propio contador)
 * BD --> SOCIO_NIF <--> nifSocio --> programa
 * @author MIGUEL
 *
 */
public class DAOReserva extends DAO{
	
	/**
	 * Esta función se encarga de dar de alta una reserva por medio del objeto Transfer que se le pasa como parámetro, y que
	 * sera transformado en un objeto de tipo ReservaTransfer dentro de la misma.
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
		
		ReservaTransfer reserva = (ReservaTransfer)o;
		PreparedStatement sentencia;
		try 
		{
			//Establecemos la conexion
			sentencia = DAO.getConnection().prepareStatement("INSERT INTO reservas " +
					"(ID_RESERVA, SOCIO_NIF) VALUES (?,?)");
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try 
		{	
			sentencia.setString(2, reserva.getNifSocio());
			sentencia.setInt(1, (int) reserva.getId());
			
			
			sentencia.executeUpdate();
			sentencia.close();
			
		} catch (SQLException e) {
			throw new AlreadyExists("Esa butaca ya esta ocupada",e.getCause());
		}
		
	}

	/**
	 * Esta funcion se encarga de dar de baja una reserva, recibe como parametro de entrada un id (String),
	 * y dentro de la funcion se elimina de la base de datos toda la información sobre el mismo.
	 * 
	 * Esta funcion puede generar dos tipos de excepciones, una ConnectionFailure, causada por un fallo de conexión con la base de datos y
	 * otra DatabaseError causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador.
	 * 
	 * @param id
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 */
	@Override
	public void baja(Object id) throws ConnectionFailure, DatabaseError {
		
		Statement sentencia;
		try {
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {			
			sentencia.executeUpdate("DELETE FROM reservas WHERE ID_RESERVA='" + id + "'");		
			sentencia.close();
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}
		
	}

	/**
	 * Esta función se encarga de modificar la informacion de una reserva que se encuentra en la base de datos, recibe como 
	 * parametro de entrada la reserva con las modificaciones que se le quiera realizar, ese parametro sera de 
	 * tipo ObjetoTransfer, y luego dentro de esta función se transformara dicho parametro a ReservaTransfer.
	 * 
	 * Las excepciones que puede lanzar son ConnectionFailure, causada por un herror de conexión con la base de datos y
	 * DatabaseError causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador.
	 *
	 * No se pude modificar una resrva solo cnacelar
	 * 
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
			ReservaTransfer toUpdate = (ReservaTransfer)o;
			sentencia.executeUpdate("UPDATE reservas " +
					"SET ID_SOCIO='" + toUpdate.getIdSocio() + "', NUM_BUTACAS='"+toUpdate.getListaButacas().size()+"'," +
					"WHERE ID_RESERVAS='" + toUpdate.getId() + "'");
			sentencia.close();			
			
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}*/
	}

	/**
	 * Esta funcion se encarga de buscar una reserva en la base de datos, recibe la id del mismo por parametro y 
	 * devuelve el objeto de tipo ReservaTransfer creado con los datos que se han obtenido de la base de datos
	 *
	 * Puede lanzar tres tipos de excepciones ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador
	 * y IOException causadas por errores con las entradas y salidas
	 * 
	 * @param id
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	@Override
	public ObjetoTransfer buscar(Object id) throws ConnectionFailure, DatabaseError, IOException {
		
		Statement sentencia;
		try {			
 			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e){			
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());			
		}
		
		try {
			String idSocio;
			List<ButacaTransfer> listaButacas = new ArrayList <ButacaTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM reservas WHERE ID_RESERVA='" + id + "'");
		
			if(resultado.next()) {
				idSocio = resultado.getString("SOCIO_NIF");
				listaButacas = this.obtenerButacas((int) id);
				
				sentencia.close();
				resultado.close();
				
				return new ReservaTransfer((int) id, idSocio, listaButacas);
			}
			
			sentencia.close();
			resultado.close();
			
			return null;
			
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}

	/**
	 * Esta funcion se encarga de generar una lista de todas las reservas ordenadas por la id de la reserva, reciben como
	 * parametro un campo por el cual las vamos a buscar y el valor de dicho campo.
	 * 
	 * Se podran dar tres tipos de excepciones: ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador
	 * y IOException causadas por errores con las entradas y salidas
	 * 
	 * Esta función no es necesaria por ahora porque por ahora no tenemos ningun campo int.
	 * 
	 * @param campo
	 * @param valor
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	@Override
	public List<ObjetoTransfer> listarPorInt(String campo, Object valor) throws ConnectionFailure, DatabaseError, IOException {
		
		/*Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			String idSocio;
			List<ButacaTransfer> listaButacas = new ArrayList <ButacaTransfer>();
			int id;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM reservas WHERE " + campo +"='" + valor + "'");
			while(resultado.next()){
				id = resultado.getInt("ID_RESERVAS");
				idSocio = resultado.getString("ID_SOCIO");
				listaButacas = this.obtenerButacas(id);
									
				lista.add(new ReservaTransfer(id, idSocio, listaButacas));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}*/
		return null;
	}
	
	/**
	 * Esta funcion se encarga de generar una lista de todas las reservas ordenadas por un String, reciben como parametro un 
	 * campo por el cual las vamos a buscar y el valor de dicho campo.
	 * 
	 * Se podran dar tres tipos de excepciones: ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador
	 * y IOException causadas por errores con las entradas y salidas
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
			String idSocio;
			List<ButacaTransfer> listaButacas = new ArrayList <ButacaTransfer>();
			int id;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM reservas WHERE " + campo +"=" + DAO.comillas + valor + DAO.comillas);
			while(resultado.next()){
				id = resultado.getInt("ID_RESERVA");
				idSocio = resultado.getString("SOCIO_NIF");
				listaButacas = this.obtenerButacas(id);
									
				lista.add(new ReservaTransfer(id, idSocio, listaButacas));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	/**
	 * Esta funcion se encarga de generar una lista de todas las reservas ordenadas, reciben como parametro un 
	 * campo por el cual las vamos a buscar y el valor de dicho campo.
	 * 
	 * Se podran dar tres tipos de excepciones: ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causada por errores en la base de datos y IOException causadas por errores con las entradas y salidas
	 * 
	 * @param campo
	 * @param valor
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	@Override
	public List<ObjetoTransfer> listar() throws ConnectionFailure,
			DatabaseError, IOException {
		Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			String idSocio;
			List<ButacaTransfer> listaButacas = new ArrayList <ButacaTransfer>();
			int id;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM reservas ORDER BY ID_RESERVA");
			while(resultado.next()){
				id = resultado.getInt("ID_RESERVA");
				idSocio = resultado.getString("SOCIO_NIF");
				listaButacas = this.obtenerButacas(id);
									
				lista.add(new ReservaTransfer(id, idSocio, listaButacas));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
		
	/**
	 * Funcion auxiliar utilizada para obtener una lista con todas las butacas reservadas utilizando un iterador
	 * @param id
	 * @return List<ButacaTransfer> - devuelve una lista con todas las butacas reservadas
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private List<ButacaTransfer> obtenerButacas (int id) throws ConnectionFailure, DatabaseError, IOException{
		List<ButacaTransfer> lista = (List<ButacaTransfer>)(List<?>)new DAOButaca().listarPorInt("ID_RESERVA", id);
		/*List<ButacaTransfer> listaButacas = new ArrayList <ButacaTransfer>();
		Iterator<ObjetoTransfer> it = lista.iterator();
		
		while (it.hasNext()) {
			listaButacas.add((ButacaTransfer)it.next());
		}*/
		return lista;
	}


}
