package DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Transfer.ObjetoTransfer;
import Transfer.PeliculaTransfer;
import Transfer.ValoracionTransfer;

import DAO.Exceptions.AlreadyExists;
import DAO.Exceptions.ConnectionFailure;
import DAO.Exceptions.DatabaseError;
import Enumerados.Genero;

/**
 *  Esta clase se encarga de realizar todas las conexxiones que hagan falta entre un usuario administrador y
 * la tabla administrador de la base de datos.
 * Los nombres de las variables en la base de datos y su relación con las bariables del programa son las siguientes:
 * 
 * BD --> TITULO (VARCHAR(100)) PK <--> titulo <-- programa
 * BD --> GENERO (VARCHAR(100) NN <--> genero <-- programa
 * BD --> SYNOPSIS (VARCHAR(100)) NN <--> synopsis <-- programa
 * BD --> FECHA_ESTRENO (VARCHAR(100)) NN <--> fechaEstreno <-- programa
 * BD --> ACTORES (VARCHAR(100)) NN <-->  actores <-- programa
 * BD --> DIRECTOR (VARCHAR(100)) NN <--> director <-- programa
 * BD--> DURACION (INT) NN <--> duracion <-- programa
 * 
 * 
 * @author MIGUEL
 *
 */
public class DAOPelicula extends DAO {

	/**
	 * Esta función se encarga de dar de alta a una pelicula por medio del objeto Transfer que se le pasa como parámetro, y que
	 * sera transformado en un objeto de tipo PELICULATransfer dentro de la misma.
	 * 
	 * Esta funcion puede generar dos tipos de excepciones, una ConnectionFailure, causada por un fallo de conexión con la base de datos y
	 * otra AlredyExists causada porque el administrados ya se encuentre en la base de datos como tal.
	 * 
	 * @param o
	 * @throws ConnectionFailure
	 * @throws AlreadyExists
	 */
	@Override
	public void alta(ObjetoTransfer o) throws ConnectionFailure, AlreadyExists{
		
		PreparedStatement sentencia;

		try {
			sentencia = DAO.getConnection().prepareStatement("INSERT INTO peliculas "
						+ "(TITULO, GENERO, SYNOPSIS, FECHA_ESTRENO, ACTORES, DIRECTOR, DURACION)" +
						" VALUES (?,?,?,?,?,?,?)");
		}
		catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			PeliculaTransfer pelicula = (PeliculaTransfer)o;
			
			sentencia.setString(1, pelicula.getTitulo());
			sentencia.setString(2, pelicula.getGenero().toString());
			sentencia.setString(3, pelicula.getSynopsis());
			sentencia.setString(4, pelicula.getFechaEstreno());
			sentencia.setString(5, pelicula.getActores());
			sentencia.setString(6, pelicula.getDirector());
			sentencia.setInt(7,  pelicula.getDuracion());
			
			sentencia.executeUpdate();
			sentencia.close();
		}
		catch (SQLException e) {
			throw new AlreadyExists("Pelicula existente", e.getCause());
		}
	}
	
	/**
	 * Esta funcion se encarga de dar de baja a una pelicula, recibe como parametro de entrada un id (String),
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
		}
		catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}

		try {
			sentencia.executeUpdate("DELETE FROM peliculas WHERE TITULO=" + DAO.comillas + id + DAO.comillas);
			sentencia.close();
		}
		catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}
	}

	/**
	 * Esta función se encarga de modificar la informacion de una pelicula que se encuentra en la base de datos, recibe como 
	 * parametro de entrada la pelicula con las modificaciones que se le quiera realizar, ese parametro sera de 
	 * tipo ObjetoTransfer, y luego dentro de esta función se transformara dicho parametro a ButacaTransfer.
	 * 
	 * Las excepciones que puede lanzar son ConnectionFailure, causada por un herror de conexión con la base de datos y
	 * DatabaseError causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador.

	 * @param o
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 */
	@Override
	public void modificar(ObjetoTransfer o, Object idActual) throws ConnectionFailure, DatabaseError {
		Statement sentencia;

		try {
			sentencia = DAO.getConnection().createStatement();
		}
		catch (SQLException e) {
			throw new ConnectionFailure("Error al establecer la conexion", e.getCause());
		}

		try {
			PeliculaTransfer pelicula = (PeliculaTransfer)o;
			
			sentencia.executeUpdate("UPDATE peliculas SET " +
									"TITULO =" + DAO.comillas + pelicula.getTitulo() + DAO.comillas +
									", GENERO =" + DAO.comillas + pelicula.getGenero() + DAO.comillas +
									", DURACION = '" + pelicula.getDuracion() +
									"', DIRECTOR =" + DAO.comillas + pelicula.getDirector() + DAO.comillas +
									", ACTORES =" + DAO.comillas + pelicula.getActores() + DAO.comillas +
									", FECHA_ESTRENO = " + DAO.comillas + pelicula.getFechaEstreno() + DAO.comillas +
									", SYNOPSIS =" + DAO.comillas + pelicula.getSynopsis() + DAO.comillas +
									" WHERE TITULO=" + DAO.comillas + idActual + DAO.comillas);
			sentencia.close();
		}
		catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}	
	}
	
	/**
	 * Esta funcion se encarga de buscar a una pelicula en la base de datos, recibe la id (tipo String) del mismo por parametro y 
	 * devuelve el objeto de tipo PeliculaTransfer creado con los datos que se han obtenido de la base de datos
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
			String titulo, synopsis, actores, director;
			String fechaEstreno;
			Genero genero;
			int duracion, valoracion;
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM peliculas WHERE TITULO=" + DAO.comillas + id + DAO.comillas);
		
			if(resultado.next()) {
				
				titulo = resultado.getString("TITULO");
				genero = Genero.valueOf(resultado.getString("GENERO"));
				synopsis =resultado.getString("SYNOPSIS");
				fechaEstreno = resultado.getString("FECHA_ESTRENO");
				actores = resultado.getString("ACTORES");
				director = resultado.getString("DIRECTOR");
				duracion = resultado.getInt("DURACION");
				valoracion = obtenerValoracion((String) id);
				
				sentencia.close();
				resultado.close();
				
				return new PeliculaTransfer(titulo, genero, synopsis, fechaEstreno, actores, director, duracion, valoracion);
			}
			
			sentencia.close();
			resultado.close();
			
			return null;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	/**
	 * Esta funcion se encarga de generar una lista de todas las peliculas ordenadas por la duracion, reciben como
	 * parametro un campo por el cual las vamos a buscar y el valor de dicho campo.
	 * 
	 * Se podran dar tres tipos de excepciones: ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causado por un herror en la base de datos bien por culpa del usuario o bin por culpa del programador
	 * y IOException causadas por errores con las entradas y salidas
	 * 
	 * Esta función no es necesaria por ahora porque el unico campo int en la tabla de peliculas es la duracion, y no se realizan
	 * busquedas por medio de la duracion
	 * 
	 * @param campo
	 * @param valor
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	/**
	 * no sense porque el unico campo int en la tabla pelicula  es duracion
	 */
	@Override
	public List<ObjetoTransfer> listarPorInt(String campo, Object valor)
			throws ConnectionFailure, DatabaseError, IOException {
		/*Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			String titulo, synopsis, actores, director;
			String fechaEstreno;
			Genero genero;
			int duracion, id, valoracion;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM peliculas WHERE " + campo +"='" + valor + "'");
			while(resultado.next()){
				titulo = resultado.getString("TITULO");
				genero = Genero.valueOf(resultado.getString("GENERO"));
				synopsis =resultado.getString("SYNOPSIS");
				fechaEstreno = Utilidades.dateToCalendar(resultado.getString("FECHA_ESTRENO"));
				actores = resultado.getString("ACTORES");
				director = resultado.getString("DIRECTOR");
				duracion = resultado.getInt("DURACION");
				valoracion = obtenerValoracion(id);
									
				lista.add(new PeliculaTransfer(titulo, genero, synopsis, fechaEstreno, actores, director, duracion, valoracion));
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
	 * Esta funcion se encarga de generar una lista de todas las peliculas ordenadas por un String, reciben como parametro un 
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
	public List<ObjetoTransfer> listarPorString(String campo, Object valor)
			throws ConnectionFailure, DatabaseError, IOException {
		Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			String titulo, synopsis, actores, director;
			String fechaEstreno;
			Genero genero;
			int duracion, valoracion;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM peliculas WHERE " + campo +"=" + DAO.comillas + valor + DAO.comillas);
			while(resultado.next()){
				titulo = resultado.getString("TITULO");
				genero = Genero.valueOf(resultado.getString("GENERO"));
				synopsis =resultado.getString("SYNOPSIS");
				fechaEstreno = resultado.getString("FECHA_ESTRENO");
				actores = resultado.getString("ACTORES");
				director = resultado.getString("DIRECTOR");
				duracion = resultado.getInt("DURACION");
				valoracion = obtenerValoracion(titulo);
									
				lista.add(new PeliculaTransfer(titulo, genero, synopsis, fechaEstreno, actores, director, duracion, valoracion));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	/**
	 * Esta funcion se encarga de generar una lista de todas las peliculas ordenadas, reciben como parametro un 
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
			String titulo, synopsis, actores, director;
			String fechaEstreno;
			Genero genero;
			int duracion, valoracion;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM peliculas");
			while(resultado.next()){
				titulo = resultado.getString("TITULO");
				genero = Genero.valueOf(resultado.getString("GENERO"));
				synopsis =resultado.getString("SYNOPSIS");
				fechaEstreno = resultado.getString("FECHA_ESTRENO");
				actores = resultado.getString("ACTORES");
				director = resultado.getString("DIRECTOR");
				duracion = resultado.getInt("DURACION");
				valoracion = obtenerValoracion(titulo);
									
				lista.add(new PeliculaTransfer(titulo, genero, synopsis, fechaEstreno, actores, director, duracion, valoracion));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	/**
	 * Esta funcion se utiliza para obtener la valoracion de una pelicula, se le pasa como parametro el titulo de la 
	 * misma y se buscan todas las valoraciones que haya con ese titulo, despues se hacen todas la operaciones necesarias 
	 * para obtener la valoracion media de esa pelicula.
	 * 
	 * Se podran dar tres tipos de excepciones: ConnectionFailure, causada por un error de conexion con la base de datos,
	 * DatabaseError, causada por errores en la base de datos y IOException causadas por errores con las entradas y salidas
	 * 
	 * @param titulo
	 * @return
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	private int obtenerValoracion(String titulo) throws ConnectionFailure, DatabaseError, IOException{
		
			int numValores = 0, valorAcumulado = 0;
			List<ObjetoTransfer> lista = new DAOValoracion().listarPorString("PELICULAS_TITULO", titulo);
			Iterator<ObjetoTransfer> itr = lista.iterator();
			
			while (itr.hasNext()){
				ValoracionTransfer v = (ValoracionTransfer)itr.next();
				valorAcumulado += v.getValor();
				numValores++;			
			}
			if(numValores == 0){
				return 0;
			}
			return valorAcumulado/numValores;
	}
}
