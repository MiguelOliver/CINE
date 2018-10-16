package DAO;

import DAO.Exceptions.AlreadyExists;
import DAO.Exceptions.DatabaseError;
import DAO.Exceptions.ConnectionFailure;

import Transfer.ObjetoTransfer;
import Transfer.SalaTransfer;
import Transfer.SesionTransfer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOSala extends DAO{

	

	/*
	 * TABLA salas
	 * ID_SALA (INT) PK AI
	 * FILAS (INT) NN
	 * COLUMNAS (INT) NN
	 * La lista de sesiones se obtiene haciendo la query sobre la tabla de sesiones donde ID_SALA sea el id de la sala.
	 */
	

	@Override
	public void alta(ObjetoTransfer o) throws ConnectionFailure, AlreadyExists {
		PreparedStatement sentencia;
		try 
		{
			//Establecemos la conexion
			sentencia = DAO.getConnection().prepareStatement("INSERT INTO salas " +
					"(ID_SALA, FILAS, COLUMNAS) VALUES (?,?,?)");
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try 
		{	
			SalaTransfer toInsert = (SalaTransfer)o;
			sentencia.setInt(1, (int) toInsert.getId());
			sentencia.setInt(2, toInsert.getFilas());
			sentencia.setInt(3, toInsert.getColumnas());
			
			sentencia.executeUpdate();
			sentencia.close();
			
		} catch (SQLException e) {
			throw new AlreadyExists("Sala existente",e.getCause());
		}
	}

	@Override
	public void baja(Object id) throws ConnectionFailure, DatabaseError {
		Statement sentencia;
		try {
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {			
			sentencia.executeUpdate("DELETE FROM salas WHERE ID_SALA='" + id + "'");		
			sentencia.close();
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}
	}

	@Override
	public void modificar(ObjetoTransfer o, Object idActual) throws ConnectionFailure,
			DatabaseError {
		Statement sentencia;
		try {
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			SalaTransfer toUpdate = (SalaTransfer)o;
			int i = (int)idActual;
			int j = (int)o.getId();
			sentencia.executeUpdate("UPDATE salas SET" +
					" FILAS = '" + toUpdate.getFilas() + "' , COLUMNAS = '"+toUpdate.getColumnas()+"' " +
					", ID_SALA = '" + j + "'" +
					" WHERE ID_SALA = '" + i + "'");
			sentencia.close();			
			
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}	}

	@Override
	public ObjetoTransfer buscar(Object id) throws ConnectionFailure,
			DatabaseError, IOException {
		Statement sentencia;
		try {			
 			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e){			
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());			
		}
		
		try {
			int filas, columnas;
			List<SesionTransfer>listaSesiones;
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM salas WHERE ID_SALA='" + id + "'");
		
			if(resultado.next()) {
				filas = resultado.getInt("FILAS");
				columnas = resultado.getInt("COLUMNAS");
				listaSesiones = obtenerSesiones((int) id);
				
				sentencia.close();
				resultado.close();
				
				return new SalaTransfer((int) id, filas, columnas, listaSesiones);
			}
			
			sentencia.close();
			resultado.close();
			
			return null;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}

	@Override
	public List<ObjetoTransfer> listarPorInt(String campo, Object valor)
			throws ConnectionFailure, DatabaseError, IOException {
		Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			int filas, columnas, id;
			List<SesionTransfer>listaSesiones;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM salas WHERE " + campo +"='" + valor + "'");
			while(resultado.next()){
				id = resultado.getInt("ID_SALA");
				filas = resultado.getInt("FILAS");
				columnas = resultado.getInt("COLUMNAS");
				listaSesiones = obtenerSesiones(id);
									
				lista.add(new SalaTransfer(id, filas, columnas, listaSesiones));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	/**
	 * Return null porque no tiene nigun campo de tipo String en la BD
	 */
	@Override
	public List<ObjetoTransfer> listarPorString(String campo, Object valor)
			throws ConnectionFailure, DatabaseError, IOException{
		return null;
	}

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
			int filas, columnas, id;
			List<SesionTransfer>listaSesiones;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM salas");
			while(resultado.next()){
				id = resultado.getInt("ID_SALA");
				filas = resultado.getInt("FILAS");
				columnas = resultado.getInt("COLUMNAS");
				listaSesiones = obtenerSesiones(id);
									
				lista.add(new SalaTransfer(id, filas, columnas, listaSesiones));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<SesionTransfer> obtenerSesiones(int id) throws ConnectionFailure, DatabaseError, IOException {

		List<SesionTransfer> lista = (List<SesionTransfer>)(List<?>)new DAOSesion().listarPorInt("ID_SALA", id);
		/*List<SesionTransfer> listaSesiones = new ArrayList<SesionTransfer>();
		Iterator<ObjetoTransfer> itr = lista.iterator();
		
		while (itr.hasNext()){
			listaSesiones.add((SesionTransfer)itr.next());				
		}*/
		
		return lista;

	}
	

}
