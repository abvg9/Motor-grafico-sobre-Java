package principal.maquinaestado;

import java.awt.Graphics;

import principal.maquinaestado.estados.inventario.GestorInventario;
import principal.maquinaestado.estados.juego.GestorJuego;
import principal.maquinaestado.estados.menuJuego.GestorMenu;

public class GestorEstados {

	private EstadoJuego[] estados;
	private EstadoJuego estadoActual;
	
	public GestorEstados(){
		inciarEstados();
		inciarEstadoActual();
	}

	private void inciarEstadoActual() {
		estadoActual = estados[0];	
	}

	private void inciarEstados() {
		estados = new EstadoJuego[3];
		estados[0] = new GestorJuego();
		estados[1] = new GestorMenu();
		estados[2] = new GestorInventario();
		
		//añadir e iniciar los demas estados a medida que los creemos
	}
	
	public void actializar(){
		estadoActual.actualizar();
	}
	
	public void dibujar(final Graphics g){
		estadoActual.dibujar(g);
	}
	
	public void cambiarEstadoActual(final int nuevoestado){
		estadoActual = estados[nuevoestado];
	}
	
	public EstadoJuego getEstadoActual(){
		return estadoActual;
	}
}
