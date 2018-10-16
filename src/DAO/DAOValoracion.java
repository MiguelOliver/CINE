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
import Transfer.ObjetoTransfer;
import Transfer.ValoracionTransfer;

public class DAOValoracion extends DAO{
	
	

	/*
	 * TABLA valoraciones
	 * PELICULA_TITULO (VARCHAR(100)) PK
	 * SOCIO_NIF(VARCHAR(8)) PK
	 * VALOR (INT) NN
	 */
	
	@Override
	public void alta(ObjetoTransfer o) throws ConnectionFailure, AlreadyExists {
		
		PreparedStatement sentencia;
		try 
		{
			//Establecemos la conexion
			sentencia = DAO.getConnection().prepareStatement("INSERT INTO valoraciones " +
					"(PELICULAS_TITULO, SOCIO_NIF, VALOR) VALUES (?,?,?)");
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try 
		{	
			ValoracionTransfer toInsert = (ValoracionTransfer)o;
			sentencia.setString(1, toInsert.getIdPelicula());
			sentencia.setString(2, toInsert.getNifSocio());
			sentencia.setInt(3, toInsert.getValor());	
			
			sentencia.executeUpdate();
			sentencia.close();
			
		} catch (SQLException e) {
			throw new AlreadyExists("Pelicula ya valorada",e.getCause());
		}		
	}

	/**
	 * No puedes cancelar la valoracion
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
			sentencia.executeUpdate("DELETE FROM valoraciones WHERE SOCIO_NIF='" + id + "'");		
			sentencia.close();
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}
	}

	/**
	 * No puedes modificarla 
	 */
	@Override
	public void modificar(ObjetoTransfer o, Object idActual) throws ConnectionFailure,
			DatabaseError {
		/*Statement sentencia;
		try {
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			ValoracionTransfer toUpdate = (ValoracionTransfer)o;
			sentencia.executeUpdate("UPDATE valoraciones " +
					"SET ID_SOCIO='" + toUpdate.getDniSocio() + "', ID_PELICULA='"+toUpdate.getIdPelicula()+"'," +
					"SET VALOR='" + toUpdate.getValor() + "'" + 
					"WHERE ID_VALORACION='" + toUpdate.getId() + "'");
			sentencia.close();			
			
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}*/
	}

	/**
	 * No tiene sentido
	 */
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
			int valor;
			String idPelicula, idSocio;
			
			ValoracionTransfer v = (ValoracionTransfer)id;
			
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM valoraciones WHERE SOCIO_NIF= " + DAO.comillas + v.getNifSocio() + DAO.comillas + " AND PELICULAS_TITULO= " 
					+ DAO.comillas + v.getIdPelicula() + DAO.comillas);
		
			if(resultado.next()) {
				idSocio = resultado.getString("SOCIO_NIF");
				idPelicula = resultado.getString("PELICULAS_TITULO");
				valor = resultado.getInt("VALOR");
				
				sentencia.close();
				resultado.close();
				
				return new ValoracionTransfer(idSocio, idPelicula, valor);
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
			int puntuacion;
			String idSocio, tituloPelicula;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM valoraciones WHERE " + campo +"='" + valor + "'");
			while(resultado.next()){
				idSocio = resultado.getString("SOCIO_NIF");
				tituloPelicula = resultado.getString("PELICULAS_TITULO");
				puntuacion = resultado.getInt("VALOR");
									
				lista.add(new ValoracionTransfer(idSocio, tituloPelicula, puntuacion));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	
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
			int puntuacion;
			String idSocio, tituloPelicula;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM valoraciones WHERE " + campo +"=" + 
															DAO.comillas + valor + DAO.comillas);
			while(resultado.next()){
				
				idSocio = resultado.getString("SOCIO_NIF");
				tituloPelicula = resultado.getString("PELICULAS_TITULO");
				puntuacion = resultado.getInt("VALOR");
								
				lista.add(new ValoracionTransfer(idSocio, tituloPelicula, puntuacion));
		
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}

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
			int idPelicula, puntuacion, id;
			String idSocio;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM valoraciones");
			while(resultado.next()){
				id = resultado.getInt("ID_VALORACION");
				idSocio = resultado.getString("ID_SOCIO");
				idPelicula = resultado.getInt("ID_PELICULA");
				puntuacion = resultado.getInt("VALOR");
									
				lista.add(new ValoracionTransfer(id, idSocio, idPelicula, puntuacion));
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
