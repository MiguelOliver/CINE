package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import DAO.Connector;
import Transfer.ObjetoTransfer;
import DAO.Exceptions.AlreadyExists;
import DAO.Exceptions.ConnectionFailure;
import DAO.Exceptions.DatabaseError;

/**
 * Clase abstracta que encapsula un objeto de tipo DAO. Puede dar de alta, baja, modificar, buscar y listar (sgun un parametro o todos)
 * un objeto transfer.
 * Cuando se realiza una accion que tiene repercusiones en mas de una de las tablas de la BD se hara en todas dado que toda FK /PK 
 * tiene to_delete_cascade y to_update_cascade
 * El objeto DAO es el encargado de establecer la conexion.
 */
public abstract class DAO {


	public static final char comillas = '"';
	
	public static Connection getConnection() {
		return Connector.getConexion() ;
	}
	
	/**
	 * Da de alta un objeto en la base de datos, en el caso de que este no exista
	 * @param o - Objeto a introducir en la base de datos
	 * @throws ConnectionFailure - Si no se puede conectar con la base de datos
	 * @throws AlreadyExists - Si ya existe un objeto con el mismo nombre(titulo, usuario, DNI, ids) en la base de datos
	 */
	public abstract void alta(ObjetoTransfer o) throws ConnectionFailure, AlreadyExists;
	
	/**
	 * Da de baja el objeto, cuya id coincida con el parametro dado, borrandolo de la base de datos
	 * @param id - id del objeto a dar de baja
	 * @throws ConnectionFailure - Si no se puede conectar con la base de datos
	 * @throws DatabaseError - Si se produce un error con la base de datos
	 */
	public abstract void baja(Object id) throws ConnectionFailure, DatabaseError;
	
	/**
	 * modigica el objeto recibido como parametro
	 * @param o - Objeto a modificar
	 * @throws ConnectionFailure - Si no se puede conectar con la base de datos
	 * @throws DatabaseError - Si se produce un error con la base de datos
	 */
	public abstract void modificar(ObjetoTransfer o, Object idActual) throws ConnectionFailure, DatabaseError;
	
	/**
	 * Busca un objeto, cuyo id coincida con el parametro, en la base de datos
	 * @param id - Id del parametro a buscar
	 * @return el objeto buscado, si no existe devuelve null
	 * @throws ConnectionFailure - Si no se puede conectar con la base de datos
	 * @throws DatabaseError - Si se produce un error con la base de datos
	 * @throws IOException
	 */
	public  abstract ObjetoTransfer buscar(Object id) throws ConnectionFailure, DatabaseError, IOException;
	
	/**
	 * Lista los objetos de una tabla de la base de datos segun un campo de tipo string en BD (")y un valor
	 * @param campo - campo segun el cual se queire listar
	 * @param valor - valor del campo 
	 * @return la lista de objetos encontrados, puede estar vacia
	 * @throws ConnectionFailure - Si no se puede conectar con la base de datos
	 * @throws DatabaseError - Si se produce un error con la base de datos
	 * @throws IOException
	 */
	public abstract List<ObjetoTransfer> listarPorString(String campo, Object valor) throws ConnectionFailure, DatabaseError, IOException;
	
	/**
	 * Lista los objetos de una tabla de la base de datos segun un campo de tipo int en BD (')y un valor
	 * @param campo - campo segun el cual se queire listar
	 * @param valor - valor del campo 
	 * @return la lista de objetos encontrados, puede estar vacia
	 * @throws ConnectionFailure - Si no se puede conectar con la base de datos
	 * @throws DatabaseError - Si se produce un error con la base de datos
	 * @throws IOException
	 */
	public abstract List<ObjetoTransfer> listarPorInt(String campo, Object valor) throws ConnectionFailure, DatabaseError, IOException;
	
	
	/**
	 * Lista todos los objetos de una tabla de la base de datos
	 * @return la lista de objetos de dicha tabla, puede esta vacia
	 * @throws ConnectionFailure - Si no se puede conectar con la base de datos
	 * @throws DatabaseError - Si se produce un error con la base de datos
	 * @throws IOException
	 */
	public abstract List<ObjetoTransfer> listar() throws ConnectionFailure, DatabaseError, IOException;

	public static char getComillas() {
		return comillas;
	}
}
