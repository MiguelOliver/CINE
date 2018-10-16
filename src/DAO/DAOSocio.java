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
import Transfer.ReservaTransfer;
import Transfer.SocioTransfer;
import Transfer.ValoracionTransfer;

public class DAOSocio extends DAO{

	

	/*
	 * TABLA socios
	 * NOMBRE (VARCHAR(70)) NN
	 * APELLIDOS (VARCHAR(70)) NN
	 * CORREO (VARCHAR(70)) NN
	 * NIF (VARCHAR(8)) PK
	 * PASSWORD (VARCHAR(45)) NN
	 * La lista de reservas se obtiene haciendo una query a la tabla resrvas donde ID_SOCIO sea la id del socio
	 * La lista de valiraciones se obtiene haciendo una query a la tabla de valoraciones donde ID_SOCIO sea la id del socio
	 */
	
	@Override
	public void alta(ObjetoTransfer o) throws ConnectionFailure, AlreadyExists {
		PreparedStatement sentencia;
		try 
		{
			//Establecemos la conexion
			sentencia = DAO.getConnection().prepareStatement("INSERT INTO socios " +
					"(NOMBRE, APELLIDOS, CORREO, NIF, PASSWORD) VALUES (?,?,?,?,?)");
		} 
		catch (SQLException e) 
		{
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try 
		{	
			SocioTransfer toInsert = (SocioTransfer)o;
			sentencia.setString(1, toInsert.getNombre());
			sentencia.setString(2, toInsert.getApellidos());
			sentencia.setString(3, toInsert.getCorreo());	
			sentencia.setString(4, toInsert.getNIF());	
			sentencia.setString(5, toInsert.getConstrasenya());	
	
			
			sentencia.executeUpdate();
			sentencia.close();
			
		} catch (SQLException e) {
			throw new AlreadyExists("Socio existente",e.getCause());
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
			sentencia.executeUpdate("DELETE FROM socios WHERE NIF=" +DAO.comillas + id + DAO.comillas);		
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
			SocioTransfer toUpdate = (SocioTransfer)o;
			sentencia.executeUpdate("UPDATE socios " +
					"SET NOMBRE=" + DAO.comillas + toUpdate.getNombre() + DAO.comillas +
					", APELLIDOS=" + DAO.comillas + toUpdate.getApellidos() + DAO.comillas +
					", CORREO=" + DAO.comillas + toUpdate.getCorreo() + DAO.comillas +
					", NIF=" + DAO.comillas + toUpdate.getNIF() + DAO.comillas +
					", PASSWORD=" + DAO.comillas + toUpdate.getConstrasenya() + DAO.comillas + 
					" WHERE NIF='" + idActual + "'");
			sentencia.close();			
			
		} catch (SQLException e) {
			throw new DatabaseError("Error en la base de datos", e.getCause());
		}
	}

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
			String nombre, apellidos, correo, NIF, password;
			List<ReservaTransfer> listaReservas;
			List<ValoracionTransfer> listaValoraciones;
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM socios WHERE NIF=" + DAO.comillas + id + DAO.comillas);
		
			if(resultado.next()) {
				nombre = resultado.getString("NOMBRE");
				apellidos = resultado.getString("APELLIDOS");
				correo = resultado.getString("CORREO");
				NIF = resultado.getString("NIF");
				password = resultado.getString("PASSWORD");
				listaReservas = obtenerReservas((String) id);
				listaValoraciones = obtenerValoraciones((String) id);
				
				sentencia.close();
				resultado.close();
				
				return new SocioTransfer(nombre, apellidos, correo, NIF, password, listaReservas, listaValoraciones);
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
		/*Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			String nombre, apellidos, correo, NIF, password;
			List<ReservaTransfer> listaReservas;
			List<ValoracionTransfer> listaValoraciones;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM socios WHERE " + campo +"='" + valor + "'");
			while(resultado.next()){
				nombre = resultado.getString("NOMBRE");
				apellidos = resultado.getString("APELLIDOS");
				correo = resultado.getString("CORREO");
				NIF = resultado.getString("NIF");
				password = resultado.getString("PASSWORD");
				listaReservas = obtenerReservas(NIF);
				listaValoraciones = obtenerValoraciones(NIF);
									
				lista.add(new SocioTransfer(nombre, apellidos, correo, NIF, password, listaReservas, listaValoraciones));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}*/
		return null;
	}

	@Override
	public List<ObjetoTransfer> listarPorString(String campo, Object valor)
			throws ConnectionFailure, DatabaseError, IOException {
		/*Statement sentencia;
		try {			
			sentencia = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			throw new ConnectionFailure("La conexion no se pudo realizar con exito", e.getCause());
		}
		
		try {
			String nombre, apellidos, correo, NIF, password;
			List<ReservaTransfer> listaReservas;
			List<ValoracionTransfer> listaValoraciones;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM socios WHERE " + campo +"=" + DAO.comillas + valor + DAO.comillas);
			while(resultado.next()){
				nombre = resultado.getString("NOMBRE");
				apellidos = resultado.getString("APELLIDOS");
				correo = resultado.getString("CORREO");
				NIF = resultado.getString("NIF");
				password = resultado.getString("PASSWORD");
				listaReservas = obtenerReservas(NIF);
				listaValoraciones = obtenerValoraciones(NIF);
									
				lista.add(new SocioTransfer(nombre, apellidos, correo, NIF, password, listaReservas, listaValoraciones));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}*/
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
			String nombre, apellidos, correo, NIF, password;
			List<ReservaTransfer> listaReservas;
			List<ValoracionTransfer> listaValoraciones;
			List<ObjetoTransfer> lista = new ArrayList<ObjetoTransfer>();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM socios");
			while(resultado.next()){
				nombre = resultado.getString("NOMBRE");
				apellidos = resultado.getString("APELLIDOS");
				correo = resultado.getString("CORREO");
				NIF = resultado.getString("NIF");
				password = resultado.getString("PASSWORD");
				listaReservas = obtenerReservas(NIF);
				listaValoraciones = obtenerValoraciones(NIF);
									
				lista.add(new SocioTransfer(nombre, apellidos, correo, NIF, password, listaReservas, listaValoraciones));
			}

			
			sentencia.close();
			resultado.close();
			
			return lista;
		} catch(SQLException e) {			
			throw new DatabaseError("Error en la base de datos", e.getCause());			
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private List<ReservaTransfer> obtenerReservas(String id) throws ConnectionFailure, DatabaseError, IOException{
		
		List<ReservaTransfer> lista = (List<ReservaTransfer>)(List<?>) new DAOReserva().listarPorInt("SOCIO_NIF", id);
		/*List<ReservaTransfer> listaReservas = new ArrayList<ReservaTransfer>();
		Iterator<ObjetoTransfer> itr = lista.iterator();
		
		while (itr.hasNext()){
			
			listaReservas.add((ReservaTransfer)itr.next());
			
		}*/
		
		return lista;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ConnectionFailure
	 * @throws DatabaseError
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private List<ValoracionTransfer> obtenerValoraciones(String id) throws ConnectionFailure, DatabaseError, IOException{
		
		List<ValoracionTransfer> lista = (List<ValoracionTransfer>)(List<?>)new DAOValoracion().listarPorInt("SOCIO_NIF", id);
		/*List<ValoracionTransfer> listaValoraciones = new ArrayList<ValoracionTransfer>();
		Iterator<ObjetoTransfer> itr = lista.iterator();
		
		while (itr.hasNext()){
			listaValoraciones.add((ValoracionTransfer)itr.next());				
		}*/
		
		return lista;
	}

}
