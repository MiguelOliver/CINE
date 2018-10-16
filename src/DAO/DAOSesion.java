package DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DAO.Exceptions.AlreadyExists;
import DAO.Exceptions.ConnectionFailure;
import DAO.Exceptions.DatabaseError;
import Transfer.ButacaTransfer;
import Transfer.SesionTransfer;
import Transfer.ObjetoTransfer;

public class DAOSesion extends DAO{

	

	/*
	 * TABLA sesiones
	 * ID_SALA (INT) PK
	 * TITULO_PELICULA (VARCHAR(100)) PK
	 * FECHA_HORA (DATETIME) PK
	 * La lista de butacas ocupadas se obtiene haciendo una query a la tabla de butacas donde ID_SESION es el id de la sesion 
	 */
	
	@Override
	public void alta(ObjetoTransfer o) throws ConnectionFailure, AlreadyExists {
		PreparedStatement sentencia;
		try {
			//Establecemos la conexion
			sentencia = DAO.getConnection().prepareStatement("INSERT INTO sesiones " +
					"(ID_SALA, TITULO_PELICULA, FECHA_HORA) VALUES (?,?,?)");
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {	
			SesionTransfer sesion = (SesionTransfer)o;
			sentencia.setInt(1, sesion.getIdSala());
			sentencia.setString(2, sesion.getTituloPelicula());
			sentencia.setString(3, sesion.getFecha());
			
			sentencia.executeUpdate();
			sentencia.close();
			
		} 
		catch (SQLException e) {
			throw new AlreadyExists("Sesion ya existente",e.getCause());
		}
		
		
	}

	/**
	 * Recibe la sesion
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
			SesionTransfer toDelete = (SesionTransfer)id;
			sentencia.executeUpdate("DELETE FROM sesiones WHERE ID_SALA='" + toDelete.getIdSala() + "'" +
													"AND TITULO_PELICULA = " + DAO.comillas + toDelete.getTituloPelicula() + DAO.comillas +
													"AND FECHA_HORA = '" + toDelete.getFecha() + "'");		
			sentencia.close();
		} 
		catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}		
	}

	@Override
	public void modificar(ObjetoTransfer o, Object idActual) throws ConnectionFailure, DatabaseError {
		
		Statement sentencia;
		try {
			sentencia = DAO.getConnection().createStatement();
		} 
		catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			SesionTransfer toUpdate = (SesionTransfer)o;
			SesionTransfer actual = (SesionTransfer)idActual;
			sentencia.executeUpdate("UPDATE sesiones " +
					"SET ID_SALA='" + toUpdate.getIdSala() + "', TITULO_PELICULA='"+toUpdate.getTituloPelicula()+"'," +
					"FECHA_HORA='" + toUpdate.getFecha() + "'" +
					"WHERE ID_SALA='" + actual.getIdSala() + "'" +
						"AND TITULO_PELICULA = " + DAO.comillas + actual.getTituloPelicula() + DAO.comillas +
						"AND FECHA_HORA = '" + actual.getFecha() + "'");
			
			sentencia.close();			
			
		} 
		catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}
	}

	/**
	 * No se usa porque en nigun momento cogemos una sesion en concreto
	 */
	@Override
	public ObjetoTransfer buscar(Object id) throws ConnectionFailure, DatabaseError, IOException {
		Statement sentencia;
		try {			
 			sentencia = DAO.getConnection().createStatement();
		} 
		catch (SQLException e){			
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());			
		}
		
		try {
			int sala;
			String pelicula;
			String fecha;
			List<ButacaTransfer> butacasOcupadas;
			SesionTransfer actual = (SesionTransfer)id;
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM sesiones WHERE ID_SALA='" + actual.getIdSala() + "'" +
						"AND TITULO_PELICULA = " + DAO.comillas + actual.getTituloPelicula() + DAO.comillas +
						"AND FECHA_HORA = '" + actual.getFecha() + "'");
		
			if(resultado.next()) {
				sala = resultado.getInt("ID_SALA");
				pelicula = resultado.getString("TITULO_PELICULA");
				fecha = resultado.getString("FECHA_HORA");
				butacasOcupadas = obtenerButacasOcupadas((SesionTransfer) id);
				
				sentencia.close();
				resultado.close();
				
				return new SesionTransfer(sala, pelicula, fecha, butacasOcupadas);
			}
			
			sentencia.close();
			resultado.close();
			
			return null;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}


	
	@Override
	public List<ObjetoTransfer> listarPorInt(String campo, Object valor) throws ConnectionFailure, DatabaseError, IOException {
		Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} 
		catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			int idSala;
			String tituloPelicula;
			String fecha;
			List<ButacaTransfer> butacasOcupadas;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM sesiones WHERE " + campo +"='" + valor + "'");
		
			while(resultado.next()){
			
				idSala = resultado.getInt("ID_SALA");
				tituloPelicula = resultado.getString("TITULO_PELICULA");
				fecha = resultado.getString("FECHA_HORA");
				butacasOcupadas = obtenerButacasOcupadas(new SesionTransfer(idSala, tituloPelicula, fecha));
				
				lista.add(new SesionTransfer(idSala, tituloPelicula, fecha, butacasOcupadas));
			
			}
			
			sentencia.close();
			resultado.close();
			
			return lista;
		} 
		catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	/**
	 * Return null porque no tiene ningun campo de tipo String
	 */
	@Override
	public List<ObjetoTransfer> listarPorString(String campo, Object valor) throws ConnectionFailure, DatabaseError, IOException {
		Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} 
		catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			int idSala;
			String tituloPelicula;
			String fecha;
			List<ButacaTransfer> butacasOcupadas;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM sesiones WHERE " + campo +"=" + DAO.comillas + valor + DAO.comillas);
		
			while(resultado.next()){
			
				idSala = resultado.getInt("ID_SALA");
				tituloPelicula = resultado.getString("TITULO_PELICULA");
				fecha = resultado.getString("FECHA_HORA");
				butacasOcupadas = obtenerButacasOcupadas(new SesionTransfer(idSala, tituloPelicula, fecha));
				
				lista.add(new SesionTransfer(idSala, tituloPelicula, fecha, butacasOcupadas));
			
			}
			
			sentencia.close();
			resultado.close();
			
			return lista;
		} 
		catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	@Override
	public List<ObjetoTransfer> listar() throws ConnectionFailure,
			DatabaseError, IOException {
		Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} 
		catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			int idSala;
			String tituloPelicula;
			String fecha;
			List<ButacaTransfer> butacasOcupadas;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM sesiones");
		
			while(resultado.next()){

				idSala = resultado.getInt("ID_SALA");
				tituloPelicula = resultado.getString("TITULO_PELICULA");
				fecha = resultado.getString("FECHA_HORA");
				butacasOcupadas = obtenerButacasOcupadas(new SesionTransfer(idSala, tituloPelicula, fecha));
				
				lista.add(new SesionTransfer(idSala, tituloPelicula, fecha, butacasOcupadas));
			
			}
			
			sentencia.close();
			resultado.close();
			
			return lista;
		} 
		catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<ButacaTransfer> obtenerButacasOcupadas(SesionTransfer s) throws ConnectionFailure, DatabaseError, IOException{
		
		List<ButacaTransfer> lista = (List<ButacaTransfer>)(List<?>)new DAOButaca().listarPorString("SESION_FECHA_HORA", 
																			s.getFecha());
		Iterator<ButacaTransfer> itr = lista.iterator();
		List<ButacaTransfer> listaRet = new ArrayList<ButacaTransfer>();
		
		while (itr.hasNext()){
			ButacaTransfer b = itr.next();
			
			int i = s.getIdSala();
			int j = b.getSesion().getIdSala();
			String a= b.getSesion().getTituloPelicula();
			String c = s.getTituloPelicula();
			
			if((i == j) && (a.equals(c))){
				listaRet.add(b);
			}
		}
		
		return listaRet;
	}
}


