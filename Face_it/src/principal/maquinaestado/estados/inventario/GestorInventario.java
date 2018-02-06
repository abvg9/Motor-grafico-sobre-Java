package principal.maquinaestado.estados.inventario;

import java.awt.Graphics;

import principal.maquinaestado.EstadoJuego;

public class GestorInventario implements EstadoJuego {

	private EstructuraInventario estructuraInventario;
	
	public GestorInventario(){
		estructuraInventario = new EstructuraInventario();
	}
	
	
	public void actualizar() {

	}

	public void dibujar(Graphics g) {
		estructuraInventario.dibujar(g);
	}

}
