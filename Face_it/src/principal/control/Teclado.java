package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener {
	
	
	private final static int numeroTeclas = 120;
	private final boolean[] teclas = new boolean[numeroTeclas];
	
	private boolean arriba;
	private boolean abajo;
	private boolean izquierda;
	private boolean derecha;
	private boolean opciones;
	private boolean correr;
	private boolean disparar;
	private boolean inventario;
	public boolean e; // boton auxiliar,en el futuro hay que quitarlo
	
	public void actualizar(){
				
	
		arriba = teclas[KeyEvent.VK_W];
		abajo = teclas[KeyEvent.VK_S];
		izquierda = teclas[KeyEvent.VK_A];
		derecha = teclas[KeyEvent.VK_D];
		correr = teclas[KeyEvent.VK_SHIFT];
		disparar = teclas[KeyEvent.VK_P];
		e = teclas[KeyEvent.VK_E];
	}

	//cuando pulsas la tecla
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()){	
		case KeyEvent.VK_ESCAPE:
			if(!inventario) // para que no pueda abrir el inventario si esta en el menu(abajo es lo mismo pero al reves)
				opciones = !opciones;
			break;
		case KeyEvent.VK_I:
			if(!opciones)
				inventario = !inventario;
			break;
		default:
			teclas[e.getKeyCode()] = true;
			break;
		}			
	}

	//cuando dejas de pulsar la tecla
	public void keyReleased(KeyEvent e) {
		
		teclas[e.getKeyCode()] = false;
		
		
	}

	public void keyTyped(KeyEvent arg0) {
		
	}

	public boolean getArriba() {
		return arriba;
	}

	public boolean getAbajo() {
		return abajo;
	}

	public boolean getIzquierda() {
		return izquierda;
	}

	public boolean getDerecha() {
		return derecha;
	}
	public boolean getCorrer() {
		return correr;
	}
	
	public boolean getOpciones(){
		return opciones;
	}
	
	public boolean getDisparar(){
		return disparar;
	}
	
	public boolean getInventario(){
		return inventario;
	}
}
