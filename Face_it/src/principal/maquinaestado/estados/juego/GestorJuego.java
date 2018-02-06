package principal.maquinaestado.estados.juego;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;

import dataconexion.DataConexion;
import principal.entes.Jugador;
import principal.interfazUsuario.InterfazUsuario;
import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;


public class GestorJuego implements EstadoJuego {

	private Mapa mapa;
	private Jugador jugador;
	
	public GestorJuego(){
		String mapa = null;
		try {
			mapa = DataConexion.Consulta("*", "caracteristicas", "idMapa", " WHERE caracteristicas.nombre = " + "'"+
			DataConexion.user+"'").get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		iniciarMapa(mapa);
		iniciarJugador("Soy calimero,blanco y negro");
	}
	
	private void recargarJuego(String nombreMapa,final String nombreJugador){
		
		iniciarMapa(nombreMapa);
		iniciarJugador(nombreJugador);
	}
	
	
	
	public void actualizar() {
		
		if(mapa.getZonaSalida().intersects(jugador.getEspacioVital()[0])
		|| mapa.getZonaSalida().intersects(jugador.getEspacioVital()[1])
		|| mapa.getZonaSalida().intersects(jugador.getEspacioVital()[2])
		|| mapa.getZonaSalida().intersects(jugador.getEspacioVital()[3])){
			
			if(mapa.getIdMapa().equalsIgnoreCase("MapaPrincipal")){
				recargarJuego("MapaSecundario","CALIMERO");
				DataConexion.UpDateMapa("MapaSecundario");
			}else{
				recargarJuego("MapaPrincipal","CALIMERO");
				DataConexion.UpDateMapa("MapaPrincipal");
			}
			
		}
		
		
		jugador.actualizar();
		mapa.actualizar((int)jugador.getPosicionX(), (int)jugador.getPosicionY());
		
	}
	private void iniciarMapa(final String nombreMapa){	
		mapa = new Mapa(nombreMapa);	
	}
	private void iniciarJugador(final String nombreJugador){
		//llegado el momento esto tendra su sentido
		jugador = new Jugador(mapa);
	}

	public void dibujar(Graphics g) {
		mapa.dibujar(g,(int)jugador.getPosicionX(),(int)jugador.getPosicionY());	
		jugador.dibujar(g);
		
		
		g.setColor(Color.YELLOW);
		g.drawString("X = " + (int)jugador.getPosicionX(),20,20);
		g.drawString("Y = " + (int)jugador.getPosicionY(),20,30);
		InterfazUsuario.dibujarBarraResistencia(g,jugador.getResistencia());
		InterfazUsuario.dibujarBarraVida(g,jugador.getVida());
		
		g.setColor(Color.ORANGE);
		g.drawString("Coordenadas salida X = " + mapa.getSalida().getX() + ", Y = " + mapa.getSalida().getY(),20,80);
		g.drawRect((int)mapa.getZonaSalida().getX(), (int)mapa.getZonaSalida().getY(), 
				mapa.getZonaSalida().width, mapa.getZonaSalida().height);
	}
		

}
