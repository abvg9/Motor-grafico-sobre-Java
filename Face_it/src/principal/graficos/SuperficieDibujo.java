package principal.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import principal.control.GestorControles;
import principal.control.Raton;
import principal.maquinaestado.GestorEstados;

public class SuperficieDibujo extends Canvas {

	private static final long serialVersionUID = 1L;
	
	private int ancho;
	private int alto;
	private Raton raton;
	
	public SuperficieDibujo(final int ancho,final int alto){
		this.ancho = ancho;
		this.alto = alto;
		this.raton = new Raton(this);
		setIgnoreRepaint(true);
		setCursor(raton.getCursor());		
		setPreferredSize(new Dimension(ancho,alto));
		this.addKeyListener(GestorControles.teclado);
		setFocusable(true);
		requestFocus();
	}
	
	public void actualizar(){
		
		raton.actualizar(this);
		
	}
	
	
	public void Dibujar(final GestorEstados ge){
		BufferStrategy buffer = getBufferStrategy();
		
		if(buffer == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ancho, alto);
		ge.dibujar(g);
		Toolkit.getDefaultToolkit().sync();	
		g.dispose();
		buffer.show();
	}
	public int getAncho(){
		return ancho;
	}
	
	public int getAlto(){
		return alto;
	}

}
