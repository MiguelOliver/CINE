package Controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Transfer.PeliculaTransfer;
import Transfer.SalaTransfer;
import Transfer.SesionTransfer;
import Transfer.SocioTransfer;
import Vistas.PanelGenerico;
import Vistas.Vista;
import Vistas.Usuario.PanelReserva;

import AppDataProgramFilms.Asiento;
import AppDataProgramFilms.Utilidades;
import AppService.AppServiceAdmin;
import AppService.AppServiceInterfaz;
import AppService.AppServiceNoLoggeado;
import AppService.AppServiceSocio;
import Enumerados.Genero;

//FAlata eventos de controlador

public class Controlador implements ActionListener{
	
	//ATRIBUTOS
	
	private Vista vista;
	private AppServiceInterfaz modelo;
	//True se logea como ese tipo de usuario
	private static boolean admin; 
	private static boolean socio; 
	private static boolean noLoggeado; 
	
	
		
	//CONSTRUCTORES
	
	public Controlador() {
		this.setModelo(new AppServiceNoLoggeado());
		Controlador.setNoLoggeado(true);
		Controlador.setSocio(false);
		Controlador.setAdmin(false);
		this.setVista(null);
	}
	
	//GETTERS Y SETTERS
	/**
	 * Setea el modelo, que obtiene como parametro
	 * @param m - modelo
	 */
	public void setModelo(AppServiceInterfaz m){
		this.modelo = m;
	}
	
	/**
	 * Setea la vista, que obtiene como parametro
	 * @param v - vista
	 */
	public void setVista(Vista v){
		this.vista = v;
	}
	
	private static void setAdmin(boolean b){
		Controlador.admin = b;
	}
	private static void setSocio(boolean b){
		Controlador.socio = b;
	}
	private static void setNoLoggeado(boolean b){
		Controlador.noLoggeado = b;
	}
	
	//METODOS
	
	
	/**
	 * Recibe un evento de boton o comobo box que contiene la palabra login.
	 * @param evento - Action event
	 */
	private void eventoLogin(String evento){
		
		if(evento.equalsIgnoreCase("jButtonLogin")){
			this.vista.actualizarPanel(evento);
		}
		
		else if(evento.equalsIgnoreCase("jButtonAceptarLogin")){

			/*
			  En Object[0] el usuario (String)
			 * En Object[1] el password (String)
			 * En Object[2] el estado del check box como entero. 1 para el administrador y 2 para el socio
			 * 
			 */
			Object[] datos = this.vista.obtenerDatos();
			if((int)datos[2] == 1){
								
				this.modelo = new AppServiceAdmin();
				Controlador.setAdmin(true);
				Controlador.setNoLoggeado(false);
				Controlador.setSocio(false);
				if(!datos[0].toString().isEmpty()){
					if(!((AppServiceAdmin) this.modelo).comprobarAdmin(datos[0].toString(), (char[])datos[1])){
						Vista.mensajeError("Administrador no encontrado");
					}
					else{
						this.vista.actualizarPanel("jButtonAceptarLoginAdmin");
					}
				}
				else{
					Vista.mensajeError("error de formato de identificador" );
				}
			}
			
			else if((int)datos[2] == 2){
								
				Controlador.setSocio(true);
				Controlador.setAdmin(false);
				Controlador.setNoLoggeado(false);
				if(Utilidades.isNumberNIF(datos[0].toString())){
					if(!(AppServiceSocio.comprobarSocio((String)datos[0], (char[]) datos[1]))){
						Vista.mensajeError("Socio no encontrado");
					}
					else{
						this.modelo = new AppServiceSocio(AppServiceSocio.getSocio());
						this.vista.actualizarPanel("jButtonAceptarLoginSocio");
					}
				}
				else{
					Vista.mensajeError("error de formato de identificador" );
				}
				
			}
		}
				
		else if(evento.equalsIgnoreCase("jButtonContinuarSinLogin")){	
			//Continuar la aplicacion como usuario no logeado
			this.vista.actualizarPanel(evento);
			
		}
		
		else if(evento.equalsIgnoreCase("jButtonRegistrarseLogin")){
			//Dar de alta el usuario
			
			//Continuar la aplicacion como usuario no logeado
			this.vista.actualizarPanel(evento);
			
		}
	}

	private void eventoAlta(String evento){
	
		if(evento.equalsIgnoreCase("jButtonConfirmarAltaSocio")){
			
			/*
			 * Object [0] el NIF del usuario (STRING)
			 * Object[1] el nombre del usuario (STRING)
			 * Object[2] el apellido del usuario (STRING)
			 * Object[3] el correo electronico del usuario (STRING)
			 * Object[4] el password del usuario (STRING)
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			String password ="";
			char[] inPass = (char[])datos[4];
			for(int i=0; i < inPass.length ; ++i){
				password += inPass[i];
			}
			if ( !( (String)datos[0] ).isEmpty() && !( (String)datos[1] ).isEmpty() && !( (String)datos[2] ).isEmpty() && !( (String)datos[3] ).isEmpty() && (!password.isEmpty() )) {

				if(Utilidades.isNumberNIF((String)datos[0])){
					SocioTransfer socio = new SocioTransfer((String)datos[1], (String)datos[2], (String)datos[3], (String)datos[0], password);
					if(noLoggeado){
						((AppServiceNoLoggeado)this.modelo).altaSocio(socio);
					}
					else if(admin){
						((AppServiceAdmin)this.modelo).altaSocio(socio);
					}
					
				}
				else
					Vista.mensajeError("NIF no valido");
			}
			else
				Vista.mensajeAviso("Error de formato, revise los campos");
		}
		
		else if(evento.equalsIgnoreCase("jButtonConfirmarAltaSesion")){
			
			
			/*
			 * Object[0] titulo de la pelicula (STRING)
			 * Object[1] id de la sala (STRING)
			 * Object[2] Horario (yyyy-mm-dd hh:mm:00)
			 */
			Object[] datos = this.vista.obtenerDatos();
			if((datos[0].toString() != "Seleccione pelicula") && (datos[1].toString() != "Seleccione sala") && !datos[2].toString().isEmpty() &&
				    Utilidades.isCalendar(datos[2].toString())) {
				if(!datos[2].toString().isEmpty() && Utilidades.isCalendar(datos[2].toString())){
					
					SesionTransfer sesion = new SesionTransfer(Integer.parseInt(datos[1].toString()), datos[0].toString(), datos[2].toString());
					((AppServiceAdmin)this.modelo).altaSesion(sesion);
				}
				else{
					Vista.mensajeError("Error de formato, revise los campos");
				}
			}
			else{
				Vista.mensajeError("Error de formato, revise los campos");
				
			}

		}
		
		else if(evento.equalsIgnoreCase("jButtonConfirmarAltaSala")){
			/*
			 * Object[0] el id de la sala
			 * Object[1] el numero de filas
			 * Object[2] el numero de butacas por fila
			 */
			Object[] datos = this.vista.obtenerDatos();
		
			if(Utilidades.isNumber((String)datos[0].toString()) &&
				Utilidades.isNumber((String)datos[1].toString()) &&
				Utilidades.isNumber((String)datos[2].toString()) &&
				!datos[0].toString().isEmpty() &&
				!datos[1].toString().isEmpty() &&
				!datos[2].toString().isEmpty()){
				
				SalaTransfer sala = new SalaTransfer(Integer.parseInt(datos[0].toString()), Integer.parseInt(datos[1].toString()), Integer.parseInt(datos[2].toString()));
				((AppServiceAdmin)this.modelo).altaSala(sala);
			}
			
			else{
				Vista.mensajeError("Error de formato, revise los campos");
			}
		}		

		
		else if(evento.equalsIgnoreCase("jButtonConfirmarAltaPelicula")){
			
			/*
			 * Object[0] el titulo de la pelicula(STRING)
			 * Object[1] el genero (String)
			 * Object[2] la synopsis(String)
			 * Object[3] la fechaHora (String)
			 * Object[4] los actores (String)
			 * Object[5] el director (String)
			 * Object[6] la duracion (int)
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			if(Utilidades.isNumber((String)datos[6].toString()) && !((String)datos[0]).isEmpty() && !((String)datos[1]).isEmpty()
					&& !((String)datos[2]).isEmpty() && !((String)datos[3]).isEmpty() && !((String)datos[4]).isEmpty()
					&& !((String)datos[5]).isEmpty() && Utilidades.isFecha(datos[3].toString())){
				
				PeliculaTransfer pelicula = new PeliculaTransfer((String)datos[0],Genero.valueOf((String)datos[1]),(String)datos[2],
						(String)datos[3], (String)datos[4], (String)datos[5], Integer.parseInt(datos[6].toString()), 0);
				
				((AppServiceAdmin)this.modelo).altaPelicula(pelicula);
			}
			else{
				Vista.mensajeError("Error de formato, revise los campos");
			}
		}
		else{
			this.vista.actualizarPanel(evento);
		}
	}
	
	private void eventoBaja(String evento){
		
		if(evento.equalsIgnoreCase("jButtonConfirmarBajaPelicula")){
			
			/*
			 * Object [0] el titulo de la pelicula
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			((AppServiceAdmin)this.modelo).bajaPelicula((String)datos[0]);	
		}
		
		else if(evento.equalsIgnoreCase("jButtonConfirmarBajaSesion")){
			/*
			 * Object[0] el titulo de la pelicula
			 * Object[1] la sala y horario
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			String[] line = ((String)datos[1]).split(" ");
			((AppServiceAdmin)this.modelo).bajaSesion(new SesionTransfer(Integer.parseInt(line[1]), datos[0].toString(), line[3]+ " " + line[4]));
		}
		
		else if(evento.equalsIgnoreCase("jButtonConfirmarBajaSala")){
			/*
			 * Object [0] string de la seleccion del comboBox con: idSala
			 */
			Object[] datos = this.vista.obtenerDatos();
			if(Utilidades.isNumber(datos[0].toString())){
				((AppServiceAdmin)this.modelo).bajaSala(Integer.valueOf((datos[0]).toString()));
			}
			else{
				Vista.mensajeError("Error, sala no encontrada");
			}
		}		

		else if(evento.equalsIgnoreCase("jButtonConfirmarBajaReserva")){
			/*
			 * Object [0] el id de la resreva
			 */
			Object[] datos = this.vista.obtenerDatos();
			String[] aux = datos[0].toString().split(" ");
			
			((AppServiceSocio)this.modelo).cancelarReserva(Integer.parseInt(aux[0].toString()));
		}
		
		else if(evento.equalsIgnoreCase("jButtonConfirmarBajaSocio")){
			/*
			 * Object [0] el nif del socio
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			((AppServiceAdmin)this.modelo).bajaSocio((datos[0]).toString());
		}
		else if(evento.equalsIgnoreCase("jComboBoxBajaPeliSesiones")){
			/*
			 * Object [0] peli del combo box que me da el nombre de la pelicula
			 */
			Object[] datos = this.vista.obtenerDatos();
			PeliculaTransfer s = ((AppServiceAdmin)this.modelo).buscarPelicula(datos[0].toString());
			
			notifyObservers(s);
		}
		else{
			this.vista.actualizarPanel(evento);
		}
	}

	private void eventoActualizar(String evento){
		this.vista.actualizarPanel(evento);
		notifyObservers(null);
	}

	private void eventoBuscar(String evento){
		//IMPORTANTE: falta poner los nombres a los combo box para que llegue hasta aqui 
		
		if(evento.equalsIgnoreCase("jComboBoxBuscarPeliculas") || evento.equalsIgnoreCase("jComboBoxBuscarPeliculasSocio")  || evento.equalsIgnoreCase("jComboBoxBuscarSesionesSocio")
				|| evento.equalsIgnoreCase("jComboBoxBuscarSesionesAnonimo") || evento.equalsIgnoreCase("jComboBoxBuscarSesionesPelicula") ||
				evento.equalsIgnoreCase("jComboBoxBuscarPeliculaReserva")){
			/*
			 * Object [0] string de la seleccion del comboBox con: tituloPelicula
			 */
			Object[] datos = this.vista.obtenerDatos();
			PeliculaTransfer pelicula;
			
			if(noLoggeado)
				pelicula = ((AppServiceNoLoggeado)this.modelo).buscarPelicula(((String)datos[0]).toString());
			else if(socio)
				pelicula = ((AppServiceSocio)this.modelo).buscarPelicula(((String)datos[0]).toString());
			else
				pelicula = ((AppServiceAdmin)this.modelo).buscarPelicula(((String)datos[0]).toString());
			
			notifyObservers(pelicula);
		}
		
		else if(evento.equalsIgnoreCase("jComboBoxBuscarSocio")){
			/*
			 * Object [0] string del textField con el usuario que que se desea buscar
			 */
			Object[] datos = this.vista.obtenerDatos();
			SocioTransfer s = ((AppServiceAdmin)this.modelo).buscarSocio((String)datos[0]);
			
			notifyObservers(s);
		}

		
		else if(evento.equalsIgnoreCase("jComboBoxBuscarSesionesSala")){
			/*
			 * Object [0] string del textField con el usuario que que se desea buscar
			 */
			Object[] datos = this.vista.obtenerDatos();
			SalaTransfer s = ((AppServiceAdmin)this.modelo).buscarSala(Integer.parseInt(datos[0].toString()));
			
			notifyObservers(s);
		}
		
		
		
		else if(evento.equalsIgnoreCase("jButtonBuscarPelicula") || evento.equalsIgnoreCase("jButtonBuscarSocio") ||
				evento.equalsIgnoreCase("jButtonBuscarPeliculasAnonimo") || evento.equalsIgnoreCase("jButtonBuscarSesionesAnonimo")
				|| evento.equalsIgnoreCase("jButtonBuscarSesionPelicula") || evento.equalsIgnoreCase("jButtonBuscarSesionSala")
				|| evento.equalsIgnoreCase("jButtonBuscarSocioPeliculas") || evento.equalsIgnoreCase("jButtonBuscarSocioSesiones")){
			this.vista.actualizarPanel(evento);
			notifyObservers(null);
		}
		
		else{
			this.vista.actualizarPanel(evento);
		}
	}
	
	private void eventoEditar(String evento){
		//IMPORTANTE: Verificar nombre de los confrimar
		
		if(evento.equalsIgnoreCase("jButtonConfirmarEditarPelicula")){
			/*
			 * Object[0] el titulo de la pelicula a modificar
			 * Object[1] el titulo de la pelicula(STRING)
			 * Object[2] el genero (String)
			 * Object[3] la synopsis(String)
			 * Object[4] la fechaHora (String)
			 * Object[5] los actores (String)
			 * Object[6] el director (String)
			 * Object[7] la duracion (int)
			 */
			Object[] datos = this.vista.obtenerDatos();
			if(Utilidades.isNumber((String)datos[7].toString()) 
					&& !(datos[0]).toString().isEmpty() 
					&& !(datos[1]).toString().isEmpty()
					&& !(datos[2]).toString().isEmpty() 
					&& !(datos[3]).toString().isEmpty() 
					&& !(datos[4]).toString().isEmpty()
					&& !(datos[5]).toString().isEmpty()
					&& !(datos[6]).toString().isEmpty()
					&& !(datos[7]).toString().isEmpty()
					&& Utilidades.isFecha(datos[4].toString())){
				
				PeliculaTransfer peli = new PeliculaTransfer(datos[1].toString(), Genero.valueOf(datos[2].toString()), datos[3].toString(),
						datos[4].toString(), datos[5].toString(), datos[6].toString(), Integer.parseInt(datos[7].toString()), 0);
				((AppServiceAdmin)this.modelo).modificarPelicula(peli, datos[0].toString());
				
			}
			else{
				Vista.mensajeError("Error de formato, revise los campos");
			}
			
		}
		
		else if(evento.equalsIgnoreCase("jButtonConfirmarEditarSocio")){
			/*
			 * Object[0] el usuario a modificar
			 * Object[1] el NIF
			 * Object[2] el nombre
			 * Object[3] los apellidos
			 * Object[4] el correo electronico
			 * Object[5] la password
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			String password ="";
			char[] inPass = (char[])datos[5];
			for(int i=0; i < inPass.length ; ++i){
				password += inPass[i];
			}
			
			if ( !( (String)datos[0] ).isEmpty() && !( (String)datos[1] ).isEmpty() && !( (String)datos[2] ).isEmpty() && !( (String)datos[3] ).isEmpty()
					&& (!password.isEmpty() )) {
				if(Utilidades.isNumberNIF(datos[1].toString())){	
					
					SocioTransfer socioTr = new SocioTransfer(datos[2].toString(), datos[3].toString(), datos[4].toString(),
							datos[1].toString(), password);
					((AppServiceAdmin)this.modelo).modificarSocio(socioTr, datos[0].toString());
					
				}
				else{
					Vista.mensajeError("NIF incorrecto");
				}
			}
			else{
				Vista.mensajeError("Error de formato, revise los campos");
			}
			
		}
		
		else if(evento.equalsIgnoreCase("jButtonConfirmarEditarSala")){
			/*
			 * Object[0] la sala a modificar
			 * Object[1] el nuevo numero de la sala
			 * Object[2] el nuevo numero de filas de la sala
			 * Object[3] el nuevo numero de butacas de las filas
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			if(Utilidades.isNumber(datos[0].toString()) && Utilidades.isNumber(datos[1].toString()) 
					&& Utilidades.isNumber(datos[2].toString()) && Utilidades.isNumber(datos[3].toString()) &&
					!datos[1].toString().isEmpty() && !datos[2].toString().isEmpty() &&
					!datos[3].toString().isEmpty()) {
				
				((AppServiceAdmin)this.modelo).modificarSala(Integer.valueOf((datos[1]).toString()), Integer.valueOf((datos[2]).toString()),
						 Integer.valueOf((datos[3]).toString()), Integer.valueOf((datos[0]).toString()));
			}
			else{
				Vista.mensajeAviso("Error de formato, revise los campos");
			}
			
		}		

		
		else if(evento.equalsIgnoreCase("jButtonConfirmarEditarAdmin")){
			/*
			 * Object[0] el nuevo nombre de administrador
			 * Object[1] la nueva password
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			String password ="";
			char[] inPass = (char[])datos[1];
			for(int i=0; i < inPass.length ; ++i){
				password += inPass[i];
			}
			
			
			if(!datos[0].toString().isEmpty() && ! datos[1].toString().isEmpty()){			
				((AppServiceAdmin)this.modelo).modificarAdmin(datos[0].toString(), password);
			}
			else{
				Vista.mensajeAviso("Error de formato, revise los campos");
			}
		}
		
		else if(evento.equalsIgnoreCase("jComboBoxEditarPeliculas")){
			Object[] datos = this.vista.obtenerDatos();
			PeliculaTransfer pelicula = ((AppServiceAdmin)this.modelo).buscarPelicula((datos[0]).toString());
			notifyObservers(pelicula);
		}
		
		else if(evento.equalsIgnoreCase("jComboBoxEditarSalas")){
			Object[] datos = this.vista.obtenerDatos();
			SalaTransfer sala = ((AppServiceAdmin)this.modelo).buscarSala(Integer.valueOf((datos[0]).toString()));
			notifyObservers(sala);
		}
		else if(evento.equalsIgnoreCase("jComboBoxEditarSocio")){
			Object[] datos = this.vista.obtenerDatos();
			SocioTransfer socio = ((AppServiceAdmin)this.modelo).buscarSocio((datos[0]).toString());
			notifyObservers(socio);
		}
		else if(evento.equalsIgnoreCase("jButtonEditarPelicula") || evento.equalsIgnoreCase("jButtonEditarSala") ||
				evento.equalsIgnoreCase("jButtonEditarSocio")){
			this.vista.actualizarPanel(evento);
			notifyObservers(null);
		}
		else{
			this.vista.actualizarPanel(evento);
		}
	}
			
	@SuppressWarnings("unchecked")
	private void eventoReserva(String evento){
		
		if(evento.equalsIgnoreCase("jButtonReservaAsientos")){
			PanelReserva.selAsientos();
		}
		
		else if(evento.equalsIgnoreCase("jButtonConfirmarReserva")){
			
			/*
			 * Object[0] Pelicula seleccionada
			 * Object[1] Sesion seleccionada
			 * Object[2] Numero de entradas
			 * Object[3] Lista de butacas
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			if(!datos[0].toString().isEmpty() && datos[0].toString() != "Seleccione pelicula" &&
				!datos[1].toString().isEmpty() && datos[1].toString() != "Seleccione horario"	&&
				datos[3] != null && ((List<Asiento>)datos[3]).size() == Integer.parseInt(datos[2].toString())){
				

				String[] aux = datos[1].toString().split(" ");
				
				
				((AppServiceSocio)this.modelo).reservar(datos[0].toString(), new SesionTransfer(Integer.parseInt(aux[0].toString()), datos[0].toString() 
						,aux[1].toString() + " " + aux[2].toString()), (List<Asiento>)datos[3]);
				
			}
			else{
				Vista.mensajeError("Error, clicke volver y vuelva a intentarlo");
			}
			
		}
		
		else if(evento.equalsIgnoreCase("jComboBoxSesionReserva")){
			
			/*
			 * Object[0] Pelicula seleccionada
			 * Object[1] Sesion seleccionada
			 * Object[2] Numero de entradas
			 * Object[3] Lista de butacas
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			if(!datos[0].toString().isEmpty() && datos[0].toString() != "Seleccione pelicula" &&
				!datos[1].toString().isEmpty() && datos[1].toString() != "Seleccione horario"	){
			String[] aux = datos[1].toString().split(" ");
			
			SalaTransfer sala = ((AppServiceSocio)this.modelo).buscarSala(Integer.parseInt(aux[0].toString()));
			
			notifyObservers(sala);
			
			SesionTransfer sesion = ((AppServiceSocio)this.modelo).buscarSesion(new SesionTransfer(Integer.parseInt(aux[0].toString()), datos[0].toString() 
					,aux[1].toString() + " " + aux[2].toString()));
			
			notifyObservers(sesion);
			}
			
			
		}
	}
	
	private void eventoValorar(String evento) {
		
		if(evento.equalsIgnoreCase("jButtonConfirmarValorarPelicula")){
			/*
			 * Object [0] la pelicula
			 * Object [1] la valoracion (1---5)
			 */
			Object[] datos = this.vista.obtenerDatos();
			
			((AppServiceSocio)this.modelo).valorarPelicula(Integer.parseInt(datos[1].toString()), datos[0].toString());
		}		
	}
	

	private void eventoAsiento(String name) {
		String[] aux1 = name.split(" ");
		String[] aux2 = aux1[1].split(",");
		PanelGenerico p = this.vista.obtenerPanelActual();
		
		((PanelReserva)p).addAsiento( Integer.parseInt(aux2[0].toString()),Integer.parseInt(aux2[1].toString()));
		
	}
	

	public static void mensajeError(String error){
		Vista.mensajeError(error);
	}
	
	public static void mensajeAviso(String aviso){
		Vista.mensajeAviso(aviso);
	}

	private void notifyObservers(Object arg1) {
		this.vista.update(this.modelo, arg1);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
	
		String name  = (String) ((Component) evento.getSource()).getName();
		
		if(name.contains("Login")){
			eventoLogin(name);
		}
		else if(name.contains("Eliminar") || name.equalsIgnoreCase("jButtonDarAltaSesion")
				|| name.equalsIgnoreCase("jButtonValorarPelicula") || name.equalsIgnoreCase("jButtonReservasAct") 
				|| name.equalsIgnoreCase("jButtonAnularReserva") || name.equalsIgnoreCase("jButtonBuscarSocioPerfil")
				|| name.equalsIgnoreCase("jButtonSocioReservar")){
			eventoActualizar(name);
		}
		else if(name.contains("Alta")){
			eventoAlta(name);
		}
		
		else if(name.contains("Valora")){
			eventoValorar(name);
		}
		
		else if(name.contains("Baja")){
			eventoBaja(name);
		}
		else if(name.contains("Editar")){
			eventoEditar(name);
		}
		
		else if(name.contains("Buscar")){
			eventoBuscar(name);
		}
		
		else if(name.contains("Seleccion")){
			this.vista.actualizarPanel(name);
		}
		
		else if(name.contains("Reserva")){
			eventoReserva(name);
		}
		
		else if(name.contains("Asiento")){
			eventoAsiento(name);
		}
		

		else if(name.contains("Volver")){
			this.vista.limpiarPanel();
			this.vista.actualizarPanel(name);	
		}
		else if(name.contains("Logout")){
			this.vista.actualizarPanel(name);	
			this.vista.limpiarPanel();
		}
	}

}
