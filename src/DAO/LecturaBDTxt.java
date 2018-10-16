package DAO;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import AppDataProgramFilms.Utilidades;

/**
 * Lee el usuario y contraseña de mysql del txt
 * Tiene un atributo que es un array de strings donde guarda el usuario y la contraseña
 * @author Manuel
 *
 */

public class LecturaBDTxt {
	
	private String[] usuarioContr;
	
	/**
	 * constructor por defecto que inicializa el array a vacio
	 */
	public LecturaBDTxt() {
		this.usuarioContr = new String[2];
		this.usuarioContr[0] = "";
		this.usuarioContr[1] = "";
	}
	
	/**
	 * get para devolver la primera posicion del array donde se encuentra el usuario
	 * @return -String usuario
	 */
	public String getUsuario0() {
		return this.usuarioContr[0];
	}
	
	/**
	 * get para devolver la segunda posicion del array donde se encuentra la contraseña
	 * @return -String contraseña
	 */
	public String getUsuario1() {
		return this.usuarioContr[1];
	}
	
	/**
	 * procedimiento que se encarga de la lectura del fichero <br>
	 * si no encuentra el fichero lo inicializa por defecto <br>
	 * La inicializacion por defecto es el usuario "root" y la contraseña vacia ""
	 * @throws IOException
	 */

	public void loadBD(FileReader fichero) throws IOException {
		
		Scanner input = new Scanner(fichero);
		String linea;
		try {
			if(input.hasNext()){
				linea = input.nextLine();
				this.usuarioContr[0] = this.parser(linea);
				if(!input.hasNext()){
					throw new IOException("Formato erroneo del fichero"); 
				}
				linea = input.nextLine();
				this.usuarioContr[1] = this.parser(linea);
			}
			else {
				this.usuarioContr[0] = "root";
				this.usuarioContr[1] = "";
			}	
		}
		catch(IOException e){
			throw new IOException(e.getMessage());
		}
		finally {
			input.close();
		}
	}

	private String parser(String linea) {
		String vacio = "";
		String[] aux = linea.split(Utilidades.comillas.toString());
		
		if(aux.length > 1) {
			return aux[1];
		}
		else return vacio;
	}
}
