package principal.interfazUsuario;

import java.awt.Color;
import java.awt.Graphics;

import main.Constantes;

public class InterfazUsuario {

	public static void dibujarBarraResistencia(Graphics g,int resistencia){
		
		int ancho = 100 * resistencia / Constantes.RECUPERACION_MAXIMA;
		
		g.drawString("RESISTENCA", 18, 48);
		g.setColor(Color.white);
		g.drawRect(19, 49, 102, 11);
		
		g.setColor(Color.blue);
		g.fillRect(20,50,ancho,10);
	}
	
	public static void dibujarBarraVida(Graphics g,int vida){
		
		g.setColor(Color.yellow);
		g.drawString("VIDA", 18, 98);
		g.setColor(Color.white);
		g.drawRect(19, 98, 102, 12);
		
		g.setColor(Color.RED);
		g.fillRect(20,100,vida,10);
		
		
	}
	
}
