package Enumerados;

/**
 * Listado de los posibles generos de una pelicula
 * @author Administrador
 *
 */
public enum Genero {
	
	COMEDIA, TERROR, DRAMA, ACCION, AVENTURA, THRILLER, CIENCIA_FICCION, ANIMACION, MUSICAL;
	
	/**
	 * 
	 * @param gen - genero que queremos mostrar
	 * @return El string del nombre del genero
	 */
	public String toString(Genero gen) {
		return gen.toString();
	}

	/**
	 * Devuelve un array con los generos en String
	 */
	public static String[] valuesString(){
		String[] ret = new String[9];
		Genero[] genArray = Genero.values();
		
		for(int i = 0; i < 9; ++i){
			ret[i] = genArray[i].toString();
		}
		
		return ret;
	}
}
