package principal.maquinaestado.estados.inventario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Constantes;

public class EstructuraInventario {

	
	private Color color_Fondo;
	private Rectangle fondo;
	

	public EstructuraInventario(){
		
		color_Fondo = new Color(0,88,135);
		fondo = new Rectangle(Constantes.ANCHO_VENTANA/4,Constantes.ALTO_VENTANA/4,
				Constantes.ANCHO_VENTANA/4 + 180,Constantes.ALTO_VENTANA/2);
		
	}
	
	public void actualizar(){
		
	}
	
	public void dibujar(Graphics g){
		
		
		g.setColor(color_Fondo);
		g.fillRect((int)fondo.getX(),(int)fondo.getY(),(int)fondo.getWidth(),(int)fondo.getWidth());
		g.setColor(Color.white);
		g.drawString("INVENTARIO", Constantes.ANCHO_VENTANA/4,Constantes.ALTO_VENTANA/4+10);
	}
}
