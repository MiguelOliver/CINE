package DAO;

import java.io.IOException;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
import java.util.List;

import DAO.Exceptions.AlreadyExists;
import DAO.Exceptions.ConnectionFailure;
import DAO.Exceptions.DatabaseError;
import Transfer.AdminTransfer;
import Transfer.ObjetoTransfer;


/**
 * Esta clase se encarga de realizar todas las conexxiones que hagan falta entre un usuario administrador y
 * la tabla administrador de la base de datos.
 * Los nombres de las variables en la base de datos y su relación con las bariables del programa son las siguientes:
 * 
 * BD --> ID_ADMIN (INT) PK <--> id <-- programa
 * BD --> PASSWORD (VARCHAR(45)) NN <--> contrasenya <-- programa
 * BD --> USUARIO (VARCHAR(45)) NN <--> Usuario <-- programa
 * 
 * @author MIGUEL
 *
 */
public class DAOAdmin extends DAO{
	

	/**
	 * Esta función se encarga de dar de alta a un administrador por medio del objeto Transfer que se le pasa como parámetro, y que
	 * sera transformado en un objeto de tipo AdminTransfer dentro de la misma.
	 * 
	 * Esta funcion puede generar dos tipos de excepciones, una ConnectionFailure, causada por un fallo de conexión con la base de datos y
	 * otra AlredyExists causada porque el administrados ya se encuentre en la base de datos como tal.
	 * 
	 * En esta clase dicha funcion no tiene utilidad por ahora puesto que limitamos el numero de administradores a uno.
	 * 
	 * @param o
	 * @throws ConnectionFailure
	 * @throws AlreadyExists
	 */
	@Override
	public void alta(ObjetoTransfer o) throws ConnectionFailure, AlreadyExists {
		
		/*PreparedStatement sentencia;
		try 
		{
			//Establecemos la conexion
			sentencia = DAO.getConnection().prepareStatement("INSERT INTO administradores " +
					"(ID_ADMIN, PASSWORD, USUARIO) VALUES (?,?,?)");
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try 
		{	
			AdminTransfer admin = (AdminTransfer)o;
			sentencia.setInt(1, (int)admin.getId());
			sentencia.setString(2, admin.getContrasenya());
			sentencia.setString(3, admin.getUsuario());
			
			sentencia.executeUpdate();
			sentencia.close();
			
		} catch (SQLException e) {
			throw new AlreadyExists("Nombre de administrador ya existente",e.getCause());
		}*/
	}

	/**
	 * Esta funcion se encarga de dar de baja a un administrador, recibe como parametro de entrada el id del administrador,
	 * y dentro de la funcion se elimina de la base de datos toda la información sobre el mismo.
	 * 
	 * Esta funcion puede generar dos tipos de excepciones, una ConnectionFailure, causada por un fallo de conexión con la base de datos y
	 * otra DatabaseError //(revisar bien que es lo que hacey porque se lanza)//
	 * 
	 * Como por ahora solo tenemos un unico administrador no hace falta la función de eliminar un administrador, puesto que el
	 * el unico que hay es el que tiene el control del programa, y sin él el programa no seria del todo funcional.
	 * 
	 * @param o
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 */
	@Override
	public void baja(Object id) throws ConnectionFailure, DatabaseError {
		
		/*Statement sentencia;
		try {
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			
			
			sentencia.executeUpdate("DELETE FROM administradores WHERE ID_ADMIN='" + id + "'");		
			sentencia.close();
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}*/		
	}

	/**
	 * Esta función se encarga de modificar la informacion de un administrador que se encuentra en la base de datos, recibe como 
	 * parametro de entrada el administrador con las modificaciones que se le quiera realizar, ese parametro sera de tipo ObjetoTransfer,
	 * y luego dentro de esta función se transformara dicho parametro a AdminTransfer.
	 * 
	 * Las excepciones que puede lanzar son ConnectionFailure, causada por un herror de conexión con la base de datos y
	 * DatabaseError //(revisar bien que es lo que hacey porque se lanza)//
	 * 
	 * @param o
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 */
	@Override
	public void modificar(ObjetoTransfer o, Object idActual) throws ConnectionFailure, DatabaseError {
		
		Statement sentencia;
		try {
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			AdminTransfer toUpdate = (AdminTransfer)o;
			sentencia.executeUpdate("UPDATE administradores " +
					"SET USUARIO=" + DAO.comillas + toUpdate.getUsuario() + DAO.comillas + 
					",PASSWORD=" + DAO.comillas + toUpdate.getContrasenya() + DAO.comillas +
					"WHERE ID_ADMIN='" + idActual + "'");
			sentencia.close();			
			
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}
	}

	/**
	 * Esta funcion se encarga de buscar a un administrador en la base de datos, recibe la id del mismo por parametro y 
	 * devuelve el objeto de tipo AdminTransfer creado con los datos que se han obtenido de la base de datos
	 *
	 * Puede lanzar tres tipos de excepciones ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causada por //(revisar bien que es lo que hacey porque se lanza)//
	 * 
	 * @param o
	 * @throws ConnectionFailure
	 * @throws DatabaseError
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
			String usuario, pass;
			int id_admin;
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM administradores WHERE USUARIO=" + DAO.comillas + id + DAO.comillas);
		
			if(resultado.next()) {
				usuario = resultado.getString("USUARIO");
				pass = resultado.getString("PASSWORD");
				id_admin = resultado.getInt("ID_ADMIN");
				
				sentencia.close();
				resultado.close();
				
				return new AdminTransfer(id_admin, usuario, pass);
			}
			
			sentencia.close();
			resultado.close();
			
			return null;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	/**
	 * Esta funcion se encarga de generar una lista de todos los administradores ordenada por el id de los distintos administradores
	 * 
	 * Return null porque el unico campo int del administrador es ID y no puede repetirse por lo que seria lo mismo que buscar
	 */
	@Override
	public List<ObjetoTransfer> listarPorInt(String campo, Object valor) throws ConnectionFailure, DatabaseError, IOException {
		return null;
	}
	
	/**
	 * Retorna NULL ya que solo habra un administrador, ademas nunca se podra sacar una lista de administradores hasta mejorar la seguridad
	 */
	@Override
	public List<ObjetoTransfer> listarPorString(String campo, Object valor) throws ConnectionFailure, DatabaseError, IOException {
		
		/*Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			String usuario, pass;
			int id;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM administradores WHERE " + campo + "=" + 
			DAO.comillas + valor + DAO.comillas);
		
			while(resultado.next()){
			
				id = resultado.getInt("ID_ADMIN");
				usuario = resultado.getString("USUARIO");
				pass = resultado.getString("PASSWORD");
									
				lista.add(new AdminTransfer(id, usuario, pass));
			
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
	 * Retorna NULL ya que solo habra un administrador, ademas nunca se podra sacar una lista de administradores hasta mejorar la seguridad
	 */
	@Override
	public List<ObjetoTransfer> listar() throws ConnectionFailure,
			DatabaseError, IOException {
		/*Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			String usuario, pass;
			int id;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM administradores");
		
			while(resultado.next()){
			
				id = resultado.getInt("ID_ADMIN");
				usuario = resultado.getString("USUARIO");
				pass = resultado.getString("PASSWORD");
									
				lista.add(new AdminTransfer(id, usuario, pass));
			
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
