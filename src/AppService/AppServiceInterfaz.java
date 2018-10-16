package AppService;

import Transfer.*;

import java.util.List;
/**
 * Implementa las funciones que pueden ser realizadas por todos los tipos de usuario
 * incluidos los no logeados. Es el equivalente al observable
 * @author AppDataProgramFilms
 */
public interface AppServiceInterfaz{
	/**
	 * Genera una lista de peliculas con todas las peliculas en la base de datos
	 * @return Las peliculas del cine o null si ocurri� algun problema con la base da datos
	 */
	public List<PeliculaTransfer> listarPeliculas();
		
	/**
	 * Dada la id de una pelicula la busca en la base de datos
	 * @param id La id de la pelicula buscada
	 * @return La pelicula buscada o null si no la ha encontrado o ha habido un problema con la base de datos
	 */
	public PeliculaTransfer buscarPelicula(String id);
	
	/**
	 * Genera una lista con todas las sesiones disponibes en el cine para una pelicula
	 * @return Las sesiones del cine o null si ocurri� algun problema con la base da datos
	 */
	public List<SesionTransfer> listarSesiones(String tituloPelicula);
	
	/**
	 * Genera una lista con todas las salas del cine
	 * @return Las sesiones del cine o null si ocurri� algun problema con la base da datos
	 */
	public List<SalaTransfer> listarSalas();
	
}
