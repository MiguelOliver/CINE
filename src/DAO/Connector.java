package DAO;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase es la encargada de realizar la conexion con la base de datos <br>
 * Tiene dos atributos una conexion y un fichero del que leer el usuario y la contraseña <br>
 * @author MIGUEL
 *
 */
public class Connector {
	
	private static Connection conexion;
	private static  LecturaBDTxt fichero;
		
	/**get que devuelve la conexion
	 * @return -Connection conexion
	 */
	public static Connection getConexion() {
		return conexion;
	}

	/**
	 * Inicializa el conector
	 */
	
	public static void start(){
		try {
			
			FileReader archivo =  new FileReader("baseDeDatos.txt");
			Connector.fichero = new LecturaBDTxt();
			Connector.fichero.loadBD(archivo);
			Class.forName("com.mysql.jdbc.Driver"); // Seleccionamos el Driver que nos permitirï¿½ realizar la conexiï¿½n.
			Connector.setConexion(DriverManager.getConnection("jdbc:mysql://localhost:3306/cine",fichero.getUsuario0(),fichero.getUsuario1()));
		} catch (SQLException e) {
			e.printStackTrace();
			//Ventana de error al acceder a la base de datos
		} catch (IOException e) {
			e.printStackTrace();
			//ventana de error al leer el fichero
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * set para actualizar la conexion
	 * @param conexion -Connection
	 */
	public static void setConexion(Connection conexion) {
		Connector.conexion = conexion;
	}
	
}
