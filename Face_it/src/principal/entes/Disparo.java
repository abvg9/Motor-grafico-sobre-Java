package principal.entes;
import java.awt.Rectangle;

import main.Constantes;

public class Disparo {
	
	private int posicionDisparoX;
	private int posicionDisparoY;
	private int avanceX;
	private int avanceY;
	private Rectangle bala;
	private int velocidad = 10;
	
	public Disparo(int posicionDisparoX, int posicionDisparoY,int avanceX,int avanceY){
		this.posicionDisparoX = posicionDisparoX;
		this.posicionDisparoY = posicionDisparoY;
		this.avanceX = avanceX;
		this.avanceY = avanceY;
	}
	
	
	public void avanza(){

		posicionDisparoX += (avanceX * velocidad);
		posicionDisparoY += (avanceY * velocidad);
		
	}
	
	
	public double getposicionDisparoX(){
		return posicionDisparoX;
	}
	
	public double getposicionDisparoY(){
		return posicionDisparoY;
	}
	
	public void actualizaBala(){
		final int centroX = (Constantes.CENTRO_VENTANA_X) - Constantes.LADO_SPRITE/4;
		final int centroY = (Constantes.CENTRO_VENTANA_Y) - Constantes.LADO_SPRITE/4;
		bala = new Rectangle(centroX+(int)posicionDisparoX/6,centroY+(int)posicionDisparoY/6,1,1);
	}
	
	public Rectangle getBala(){
		return bala;
	}

}
