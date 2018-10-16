package Vistas;

import java.util.EventListener;

import javax.swing.JPanel;

import Vistas.Administrador.*;
import Vistas.NoLoggeado.PanelPeliculasAnonimo;
import Vistas.NoLoggeado.PanelPrincipalAnonimo;
import Vistas.NoLoggeado.PanelSesionesAnonimo;
import Vistas.Usuario.*;

public class Selector {

	//private static int usuario;	// 0 = no loggeado , 1 = administrador , 2 = socio
	//private Stack<JPanel> pilaVolver;
	
	// PANELES
	private PanelInicial panelInicial;
	private PanelLogin panelLogin;
	//PANELES ADMINISTRADOR
	private PanelAltaPelicula panelAltaPelicula;
	private PanelAltaSala panelAltaSala;
	private PanelAltaSesion panelAltaSesion;
	private PanelAltaSocio panelAltaSocio;
	private PanelBajaPelicula panelBajaPelicula;
	private PanelBajaSala panelBajaSala;
	private PanelBajaSesion panelBajaSesion;
	private PanelBajaSocio panelBajaSocio;
	private PanelBuscarPelicula panelBuscarPelicula;
	private PanelBuscarSesionesSala panelBuscarSesionSala;
	private PanelBuscarSesionesPelicula panelBuscarSesionPelicula;
	private PanelBuscarSocio panelBuscarSocio;
	private PanelEditarAdmin panelEditarAdmin;
	private PanelEditarPelicula panelEditarPelicula;
	private PanelEditarSala panelEditarSala;
	//private PanelEditarSesion panelEditarSesion;
	private PanelEditarSocio panelEditarSocio;
	private PanelOpcionesPelicula panelOpcionesPelicula;
	private PanelOpcionesSala panelOpcionesSala;
	private PanelOpcionesSesion panelOpcionesSesion;
	private PanelOpcionesSocio panelOpcionesSocio;
	private PanelPrincipalAdmin panelPrincipalAdmin;
	// PANELES USUARIO
	private PanelAnularReserva panelAnularReserva;
	private PanelPeliculas panelPeliculas;
	private PanelPeliculasAnonimo panelPeliculasAnonimo;
	private PanelPerfil panelPerfil;
	private PanelPrincipalUsuario panelPrincipalUsuario;
	private PanelRegistrarse panelRegistrarse;
	private PanelReserva panelReserva;
	private PanelReservasActuales panelReservasActuales;
	private PanelSesiones panelSesiones;
	private PanelValorar panelValorar;
	private PanelPrincipalAnonimo panelPrincipalAnonimo;
	private PanelSesionesAnonimo panelSesionesAnonimo;
	
	
	
	// CONTRUCTOR
	
	public Selector(EventListener controlador) {
		this.panelInicial = new PanelInicial(controlador);
		this.panelLogin = new PanelLogin(controlador);
		// admin
		this.panelAltaPelicula = new PanelAltaPelicula(controlador);
		this.panelAltaSala = new PanelAltaSala(controlador);
		this.panelAltaSesion = new PanelAltaSesion(controlador);
		this.panelAltaSocio = new PanelAltaSocio(controlador);
		this.panelBajaPelicula = new PanelBajaPelicula(controlador);
		this.panelBajaSala = new PanelBajaSala(controlador);
		this.panelBajaSesion = new PanelBajaSesion(controlador);
		this.panelBajaSocio = new PanelBajaSocio(controlador);
		this.panelBuscarPelicula = new PanelBuscarPelicula(controlador);
		this.panelBuscarSesionSala = new PanelBuscarSesionesSala(controlador);
		this.panelBuscarSesionPelicula = new PanelBuscarSesionesPelicula(controlador);
		this.panelBuscarSocio = new PanelBuscarSocio(controlador);
		this.panelEditarAdmin = new PanelEditarAdmin(controlador);
		this.panelEditarPelicula = new PanelEditarPelicula(controlador);
		this.panelEditarSala = new PanelEditarSala(controlador);
		this.panelEditarSocio = new PanelEditarSocio(controlador);
		this.panelOpcionesPelicula = new PanelOpcionesPelicula(controlador);
		this.panelOpcionesSala = new PanelOpcionesSala(controlador);
		this.panelOpcionesSesion = new PanelOpcionesSesion(controlador);
		this.panelOpcionesSocio = new PanelOpcionesSocio(controlador);
		this.panelPrincipalAdmin = new PanelPrincipalAdmin(controlador);
		// usuario
		this.panelAnularReserva = new PanelAnularReserva(controlador);
		this.panelPeliculas = new PanelPeliculas(controlador);
		this.panelPeliculasAnonimo = new PanelPeliculasAnonimo(controlador);
		this.panelPerfil = new PanelPerfil(controlador);
		this.panelPrincipalUsuario = new PanelPrincipalUsuario(controlador);
		this.panelRegistrarse = new PanelRegistrarse(controlador);
		this.panelReserva = new PanelReserva(controlador);
		this.panelReservasActuales = new PanelReservasActuales(controlador);
		this.panelSesiones = new PanelSesiones(controlador);
		this.panelValorar = new PanelValorar(controlador);
		this.panelPrincipalAnonimo = new PanelPrincipalAnonimo(controlador);
		this.panelSesionesAnonimo = new PanelSesionesAnonimo(controlador);
	}
	
	
	// METODOS
	
/*
	 * Devuelve el ultimo panel
	 * @param panelActual
	 * @return
	 
	public JPanel volverAtras(JPanel panelActual) {
		return this.pilaVolver.pop();
	}
*/	
	/**
	 * Funcion que devuelve el panel inicial del programa
	 * @return el panelInicial
	 */
	public JPanel panelInicial(){
		return this.panelInicial;
	}
	/**
	 * Comprueba cual es el siguiente panel que debera verse en la aplicacion 
	 * @param evento - nombre del  que causo el evento
	 * @param panelActual - el panel actualmente visible
	 * @return el siguiente panel segun de acuerdo al panelActual y al evento
	 */
	public JPanel panelSiguiente(String evento, JPanel panelActual) {
		//this.pilaVolver.push(panelActual);
		
		if(panelActual.getName().equalsIgnoreCase("PanelInicial")) {
			if(evento.equalsIgnoreCase("jButtonContinuarSinLogin"))
				return this.panelPrincipalAnonimo;
			if(evento.equalsIgnoreCase("jButtonLogin"))
				return this.panelLogin;
			if(evento.equalsIgnoreCase("jButtonRegistrarseLogin"))
				return this.panelRegistrarse;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelLogin")) {
			if(evento.equalsIgnoreCase("jButtonAceptarLoginSocio"))
				return this.panelPrincipalUsuario;
			if(evento.equalsIgnoreCase("jButtonAceptarLoginAdmin"))
				return this.panelPrincipalAdmin;
			if(evento.equalsIgnoreCase("jButtonVolver"))
				return this.panelInicial;
		}
/////////////
//ANONIMO:
/////////////	
		
		else if(panelActual.getName().equalsIgnoreCase("PanelPrincipalAnonimo")) {
			if(evento.equalsIgnoreCase("jButtonBuscarPeliculasAnonimo"))
				return this.panelPeliculasAnonimo;
			if(evento.equalsIgnoreCase("jButtonBuscarSesionesAnonimo"))
				return this.panelSesionesAnonimo;
			if(evento.equalsIgnoreCase("jButtonVolver"))
					return this.panelInicial;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelPeliculasAnonimo")) {
			if(evento.equalsIgnoreCase("jButtonVolver"))
					return this.panelPrincipalAnonimo;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelSesionesAnonimo")) {
			if(evento.equalsIgnoreCase("jButtonVolver"))
					return this.panelPrincipalAnonimo;
		}
		
/////////////
// USUARIO:
/////////////
		
		else if(panelActual.getName().equalsIgnoreCase("PanelAnularReserva")) {
			//aqui da igual que boton sea ya que volvera al mismo panel
			if(evento.equalsIgnoreCase("jButtonConfirmar"))
				return this.panelPerfil;
			if(evento.equalsIgnoreCase("jButtonVolver"))
				return this.panelPerfil;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelPeliculas")) {
			return this.panelPrincipalUsuario;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelPeliculasVistas")) {
			return this.panelPerfil;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelPerfil")) {
			if(evento.equalsIgnoreCase("jButtonAnularReserva"))
				return this.panelAnularReserva;
			if(evento.equalsIgnoreCase("jButtonReservasAct"))
				return this.panelReservasActuales;
			if(evento.equalsIgnoreCase("jButtonValorarPelicula"))
				return this.panelValorar;
			if(evento.equalsIgnoreCase("jButtonVolver"))
				return this.panelPrincipalUsuario;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelPrincipalUsuario")) {
			
			if(evento.equalsIgnoreCase("jButtonBuscarSocioPerfil"))
				return this.panelPerfil;
			if(evento.equalsIgnoreCase("jButtonSocioReservar"))
				return this.panelReserva;
			if(evento.equalsIgnoreCase("jButtonBuscarSocioSesiones"))
				return this.panelSesiones;
			if(evento.equalsIgnoreCase("jButtonBuscarSocioPeliculas"))
				return this.panelPeliculas;
			if(evento.equalsIgnoreCase("jButtonLogout"))
				return this.panelLogin;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelRegistrarse")) {
			if(evento.equalsIgnoreCase("jButtonConfirmar"))
				return this.panelLogin;			
			if(evento.equalsIgnoreCase("jButtonVolver"))
				return this.panelInicial;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelReserva")) {
			if(evento.equalsIgnoreCase("jButtonConfirmacion"))
				return this.panelPrincipalUsuario;
			if(evento.equalsIgnoreCase("jButtonVolver"))
				return this.panelPrincipalUsuario;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelReservasActuales")) {
			return this.panelPerfil;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelSesiones")) {
			return this.panelPrincipalUsuario;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelValorar")) {
			return this.panelPerfil; // aunque confirme o de a volver, siempre volvera al mismo panel (perfil)
		}
		
////////////
// ADMIN:
////////////
		
		else if(panelActual.getName().equalsIgnoreCase("PanelAltaPelicula") && evento.contains("Volver")) {
			return this.panelOpcionesPelicula;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelAltaSala") && evento.contains("Volver")) {
			return this.panelOpcionesSala;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelAltaSesion") && evento.contains("Volver")) {
			return this.panelOpcionesSesion;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelBuscarSesionesPelicula") && evento.contains("Volver")) {
			return this.panelOpcionesSesion;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelBuscarSesionesSala") && evento.contains("Volver")) {
			return this.panelOpcionesSesion;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelAltaSocio") && evento.contains("Volver")) {
			return this.panelOpcionesSocio;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelBajaPelicula") && evento.contains("Volver")) {
			return this.panelOpcionesPelicula;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelBajaSala") && evento.contains("Volver")) {
			return this.panelOpcionesSala;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelBajaSesion") && evento.contains("Volver")) {
			return this.panelOpcionesSesion;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelBajaSocio") && evento.contains("Volver")) {
			return this.panelOpcionesSocio;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelBuscarPelicula") && evento.contains("Volver")) {
			return this.panelOpcionesPelicula;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelBuscarSesion") && evento.contains("Volver")) {
			return this.panelOpcionesSesion;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelBuscarSocio") && evento.contains("Volver")) {
			return this.panelOpcionesSocio;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelEditarAdmin") && evento.contains("Volver")) {
			return this.panelPrincipalAdmin;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelEditarPelicula") && evento.contains("Volver")) {
			return this.panelOpcionesPelicula;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelEditarSala") && evento.contains("Volver")) {
			return this.panelOpcionesSala;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelEditarSocio") && evento.contains("Volver")) {
			return this.panelOpcionesSocio;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelOpcionesPelicula")) {
			if(evento.equalsIgnoreCase("jButtonBuscarPelicula"))
				return this.panelBuscarPelicula;
			if(evento.equalsIgnoreCase("jButtonDarAltaPelicula"))
				return this.panelAltaPelicula;
			if(evento.equalsIgnoreCase("jButtonEditarPelicula"))
				return this.panelEditarPelicula;
			if(evento.equalsIgnoreCase("jButtonEliminarPelicula"))
				return this.panelBajaPelicula;
			if(evento.equalsIgnoreCase("jButtonVolver"))
				return this.panelPrincipalAdmin;				
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelOpcionesSala")) {
			if(evento.equalsIgnoreCase("jButtonDarAltaSala"))
				return this.panelAltaSala;
			if(evento.equalsIgnoreCase("jButtonEditarSala"))
				return this.panelEditarSala;
			if(evento.equalsIgnoreCase("jButtonEliminarSala"))
				return this.panelBajaSala;
			if(evento.equalsIgnoreCase("jButtonVolver"))
				return this.panelPrincipalAdmin;
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelOpcionesSesion")) {
			if(evento.equalsIgnoreCase("jButtonBuscarSesionPelicula"))
				return this.panelBuscarSesionPelicula;
			if(evento.equalsIgnoreCase("jButtonBuscarSesionSala"))
				return this.panelBuscarSesionSala;
			if(evento.equalsIgnoreCase("jButtonDarAltaSesion"))
				return this.panelAltaSesion;
			if(evento.equalsIgnoreCase("jButtonEliminarSesion"))
				return this.panelBajaSesion;
			if(evento.equalsIgnoreCase("jButtonVolver"))
				return this.panelPrincipalAdmin;				
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelOpcionesSocio")) {
			if(evento.equalsIgnoreCase("jButtonBuscarSocio"))
				return this.panelBuscarSocio;
			if(evento.equalsIgnoreCase("jButtonDarAltaSocio"))
				return this.panelAltaSocio;
			if(evento.equalsIgnoreCase("jButtonEditarSocio"))
				return this.panelEditarSocio;
			if(evento.equalsIgnoreCase("jButtonEliminarSocio"))
				return this.panelBajaSocio;
			if(evento.equalsIgnoreCase("jButtonVolver"))
				return this.panelPrincipalAdmin;				
		}
		else if(panelActual.getName().equalsIgnoreCase("PanelPrincipalAdmin")) {
			if(evento.equalsIgnoreCase("jButtonSeleccionAdminEditarAdmin"))
				return this.panelEditarAdmin;
			if(evento.equalsIgnoreCase("jButtonSeleccionAdminPelicula"))
				return this.panelOpcionesPelicula;
			if(evento.equalsIgnoreCase("jButtonSeleccionAdminSala"))
				return this.panelOpcionesSala;
			if(evento.equalsIgnoreCase("jButtonSeleccionAdminSesion"))
				return this.panelOpcionesSesion;
			if(evento.equalsIgnoreCase("jButtonSeleccionAdminSocio"))
				return this.panelOpcionesSocio;
			if(evento.equalsIgnoreCase("jButtonLogout"))
				return this.panelLogin;	
		}
		
		return panelActual;	// si no es ninguno que no cambie
	}
}
