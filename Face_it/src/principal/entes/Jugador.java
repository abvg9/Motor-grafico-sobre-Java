package principal.entes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Constantes;
import principal.control.GestorControles;
import principal.mapas.Mapa;
import principal.sprites.HojaSprites;


public class Jugador {

	private double posicionX;
	private double posicionY;	
	private HojaSprites hs;
	private Direccion dir;
	private BufferedImage imagenActual;
	private boolean enMovimiento;
	private int animacion;
	private Mapa mapa;
	private double velocidad;
	private int resistencia;
	private int recuperacion; //numero de actualizaciones que debemos esperar para que el jugador se recupere
	private boolean recuperado;
	private boolean puedeCorrer;
	//esto dependera del tamaño de nuestro personaje(ajustar con el personaje definitivo)	
	private final int ANCHO_JUGADOR = 16;
	private final int ALTO_JUGADOR = 16;
	private final Rectangle[] espacioVital;
	private ArrayList<Disparo> disparos;
	private HebraSonido sonido;
	private int vida;
	

	
	//habilidades del arma
	private int tiempoDeRecarga = 0;
	
	public Jugador(Mapa mapa){
	
		espacioVital = new Rectangle[4];
		
		espacioVital[0] = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR/2+1,
		Constantes.CENTRO_VENTANA_Y-1,ANCHO_JUGADOR-1,1);
		
		espacioVital[1] = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR/2+1,
		Constantes.CENTRO_VENTANA_Y + ALTO_JUGADOR,ANCHO_JUGADOR-1,1);
		
		espacioVital[2] = new Rectangle(Constantes.CENTRO_VENTANA_X + ANCHO_JUGADOR/2+1,
		Constantes.CENTRO_VENTANA_Y,1,ALTO_JUGADOR); 
		
		espacioVital[3] = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR/2-1,
		Constantes.CENTRO_VENTANA_Y,1,ALTO_JUGADOR); 
		
		puedeCorrer = true;
		recuperado = true;
		vida = 100;
		this.resistencia = Constantes.RECUPERACION_MAXIMA;
		enMovimiento = false;
		this.posicionX = mapa.getPosicionInicial().getX();
		this.posicionY = mapa.getPosicionInicial().getY();
		dir = Direccion.SUR;
		hs = new HojaSprites(Constantes.RUTA_PERSONAJE,Constantes.LADO_SPRITE,false);
		this.mapa = mapa;
		imagenActual = hs.getSprite(0).getImagen();
		disparos = new ArrayList<Disparo>();
		velocidad = 1;
	}
	
	
	public void actualizar(){

		boolean mov = false;
		
		if (animacion < 32767) {
			animacion++;
		} else {
			animacion = 0;
		}
		
		//si salimos del juego decimos que estamos desconectados(esto ira en otro lado,de momento estara aqui)
		if(GestorControles.teclado.e){			
			System.exit(0);
		}
		
		if(GestorControles.teclado.getDisparar() && tiempoDeRecarga == 35){

			sonido = new HebraSonido(Constantes.RUTA_SONIDO_DISPARO);
			sonido.start();
			tiempoDeRecarga = 0;
			int x = 0;
			int y = 0;
			switch(dir){
			case ESTE:
				x =1;
				break;
			case NORESTE:
				x =1;
				y=-1;
				break;
			case NOROESTE:
				x=-1;
				y=-1;
				break;
			case NORTE:
				y=-1;
				break;
			case OESTE:
				x=-1;
				break;
			case SUR:
				y=1;
				break;
			case SURESTE:
				x=1;
				y=1;
				break;
			case SUROESTE:
				y=1;
				x=-1;
				break;		
			}		
			disparos.add(new Disparo((int)posicionX,(int)posicionY,x,y));
		}
		

		if(GestorControles.teclado.getCorrer() && resistencia > 0){
			velocidad = 1.5;
			recuperado = false;
			recuperacion = 0;
			puedeCorrer = true;
		}else{
			puedeCorrer = false;
			velocidad = 1;
			//200 es el tiempo en actualizaciones que tienen que pasar para que empiece a sumar resistencia
			if(!recuperado && recuperacion < 200){
				recuperacion++;
			}
			if(recuperacion == 200 && resistencia < Constantes.RECUPERACION_MAXIMA){
				resistencia++;
			}
			if(resistencia == Constantes.RECUPERACION_MAXIMA){
				recuperado = true;
			}
		}
		if(GestorControles.teclado.getArriba()){

			if(!Colisiona(espacioVital[0])){
				dir = Direccion.NORTE;
				posicionY -=  velocidad;
				mov = true;
				if(puedeCorrer){
					resistencia--;	
				}
			}
		}
		if(GestorControles.teclado.getAbajo()){
				
			
			if(!Colisiona(espacioVital[1])){
				dir = Direccion.SUR;
				posicionY += velocidad;
				mov = true;
				if(puedeCorrer){
					resistencia--;	
				}
			}
		}
		if(GestorControles.teclado.getIzquierda()){
						
			
			if(!Colisiona(espacioVital[3])){
				dir = Direccion.OESTE;
				posicionX -= velocidad;
				mov = true;
				if(puedeCorrer){
					resistencia--;	
				}
			}
		}
		if(GestorControles.teclado.getDerecha()){

			
			if(!Colisiona(espacioVital[2])){
				dir = Direccion.ESTE;
				posicionX += velocidad;
				mov = true;
				if(puedeCorrer){
					resistencia--;	
				}
			}
		}
		
		//combinadas
		if(GestorControles.teclado.getDerecha() && GestorControles.teclado.getArriba()){
			

			if(!Colisiona(espacioVital[0]) && !Colisiona(espacioVital[2])){
				dir = Direccion.NORESTE;
				posicionY -=  velocidad/10;
				posicionX += velocidad/10;
				mov = true;
				if(puedeCorrer){
					resistencia--;	
				}
			}
		}
		if(GestorControles.teclado.getIzquierda() && GestorControles.teclado.getArriba()){
			
			
			
			if(!Colisiona(espacioVital[0]) && !Colisiona(espacioVital[3])){
				dir = Direccion.NOROESTE;
				posicionY -=  velocidad/10;
				posicionX -= velocidad/10;
				mov = true;
				if(puedeCorrer){
					resistencia--;	
				}
			}
		}
		if(GestorControles.teclado.getIzquierda() && GestorControles.teclado.getAbajo()){
				
			
			if(!Colisiona(espacioVital[1]) && !Colisiona(espacioVital[3])){
				dir = Direccion.SUROESTE;
				posicionY += velocidad/10;
				posicionX -= velocidad/10;
				mov = true;
				if(puedeCorrer){
					resistencia--;	
				}
			}
		}
		if(GestorControles.teclado.getDerecha() && GestorControles.teclado.getAbajo()){
			
			if(!Colisiona(espacioVital[1]) && !Colisiona(espacioVital[2])){
				dir = Direccion.SURESTE;
				posicionY += velocidad/10;
				posicionX -= velocidad/10;
				mov = true;
				if(puedeCorrer){
					resistencia--;	
				}
			}
		}
		
		if(tiempoDeRecarga < 35){
			tiempoDeRecarga++;
		}
		
		enMovimiento = mov;
		animar();		
	}

	
	private boolean Colisiona(Rectangle r){
		
		int i = 0;
		boolean intersectado = false;
		//si no estamos fuera del mapa

		if(r.intersects(mapa.getBordes((int)posicionX, (int)posicionY))){
			//si no chocamos con ninguna pared
						
			while(i < mapa.getAreasColisiones().size() && !intersectado){				
				if(r.intersects(mapa.getAreasColisiones().get(i))){
					intersectado = true;
				}
				i++;
			}

			return intersectado;
		}
			
		return true;
	}
	
	private void animar(){
		switch(dir){
		
		case NORTE:
			imagenActual = hs.getSprite(3, 0).getImagen();
			if (enMovimiento) {
				if (animacion % 30 > 15) {
					imagenActual = hs.getSprite(4, 0).getImagen();
				} else {
					imagenActual = hs.getSprite(5, 0).getImagen();
				}

			}
			break;
		case NORESTE:
			imagenActual = hs.getSprite(4, 1).getImagen();
			if(enMovimiento){
				if (animacion % 30 > 15) {
					imagenActual = hs.getSprite(5, 1).getImagen();
				} else {
					imagenActual = hs.getSprite(6, 1).getImagen();
				}
			}
			break;
		case NOROESTE:
			imagenActual = hs.getSprite(1, 1).getImagen();
			if(enMovimiento){
				if (animacion % 30 > 15) {
					imagenActual = hs.getSprite(2, 1).getImagen();
				} else {
					imagenActual = hs.getSprite(3, 1).getImagen();
				}
			}
			break;
		case SUR:
			imagenActual = hs.getSprite(0, 0).getImagen();
			if(enMovimiento){	
				if (animacion % 30 > 15) {
					imagenActual = hs.getSprite(1, 0).getImagen();
				} else {
					imagenActual = hs.getSprite(2, 0).getImagen();
				}
			}
			break;
		case SURESTE:
			imagenActual = hs.getSprite(7, 1).getImagen();
			if(enMovimiento){
				if (animacion % 30 > 15) {
					imagenActual = hs.getSprite(8, 1).getImagen();
				} else {
					imagenActual = hs.getSprite(9, 1).getImagen();
				}
			}
			break;
		case SUROESTE:
			imagenActual = hs.getSprite(0, 2).getImagen();
			if(enMovimiento){
				if (animacion % 30 > 15) {
					imagenActual = hs.getSprite(1, 2).getImagen();
				} else {
					imagenActual = hs.getSprite(2, 2).getImagen();
				}
			}
			break;
		case ESTE:
			imagenActual = hs.getSprite(6, 0).getImagen();
			if(enMovimiento){
				if (animacion % 30 > 15) {
					imagenActual = hs.getSprite(7, 0).getImagen();
				} else {
					imagenActual = hs.getSprite(8, 0).getImagen();
				}
			}
			break;
		case OESTE:
			imagenActual = hs.getSprite(9, 0).getImagen();
			if(enMovimiento){
				if (animacion % 30 > 15) {
					imagenActual = hs.getSprite(10, 0).getImagen();
				} else {
					imagenActual = hs.getSprite(0, 1).getImagen();
				}
			}
			break;
		default:
		
		}
	}

	public void dibujar(Graphics g){
		

		final int centroX = (Constantes.CENTRO_VENTANA_X) - Constantes.LADO_SPRITE/2;
		final int centroY = (Constantes.CENTRO_VENTANA_Y) - Constantes.LADO_SPRITE/2;
		g.drawImage(imagenActual,centroX,centroY,null);
		g.setColor(Color.BLUE);
		if(!disparos.isEmpty()){
			for(int i = 0; i < disparos.size();i++){
				disparos.get(i).actualizaBala();
				if((Colisiona(disparos.get(i).getBala()))){
					disparos.remove(disparos.get(disparos.size()-1));	
					for(int b = 0; b < disparos.size()-1;b++){
						if(!disparos.get(b).equals(null)){
							disparos.set(b, disparos.get(b+1));
						}
					}
				}else{
					disparos.get(i).avanza();
					g.drawRect(disparos.get(i).getBala().x,
					disparos.get(i).getBala().y,
					disparos.get(i).getBala().width,
					disparos.get(i).getBala().height);
				}
			}

		}
	}
	public void setPosicionX(double posicionX){
		this.posicionX = posicionX;
	}
	
	public double getPosicionX(){
		return this.posicionX;
	}
	public double getPosicionY(){
		return this.posicionY;
	}
	public int getResistencia(){
		return this.resistencia;
	}
	public Rectangle[] getEspacioVital(){
		return espacioVital;
	}

	public void setResistencia(int resistencia){
		this.resistencia = resistencia;
	}
	
	public Direccion getDireccion(){
		return dir;
	}


	public int getVida() {
		return vida;
	}
}
