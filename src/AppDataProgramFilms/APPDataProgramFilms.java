package AppDataProgramFilms;

import java.io.IOException;
import java.util.List;

import Transfer.ReservaTransfer;
import Vistas.Vista;


import Controlador.Controlador;
import DAO.Connector;
import DAO.DAO;
import DAO.DAOReserva;
import DAO.Exceptions.ConnectionFailure;
import DAO.Exceptions.DatabaseError;



/**
 *
 * @author SkyLanes
 */
public class APPDataProgramFilms {

    /**
     * Establece la conexion con la base de datos e inicializa 
     * el programa(ontrolador, vista, modelo...)
     * @param args the command line arguments
     * @throws DatabaseError 
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
	public static void main(String[] args){
			
    	//Establecemos conexion
    	Connector.start();
    	
    	// Actualizamos el numero de reservas segun la BD
    	DAO d = new DAOReserva();
    	try {
    		List<ReservaTransfer> l = (List<ReservaTransfer>)(List<?>)d.listar();
    		int next = (int) l.get(l.size()-1).getId();
			ReservaTransfer.setCont(next +1);
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
    	
		//crear el controlador
    	Controlador cnt = new Controlador();
    
    	//crear la vista
    	Vista v = new Vista(cnt);
    	
    	//Setear la vista al controlador
    	cnt.setVista(v);
    	
    	//Arrancar la vista
    	v.arranca();
    }
}