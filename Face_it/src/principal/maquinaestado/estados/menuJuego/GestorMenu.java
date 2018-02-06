package principal.maquinaestado.estados.menuJuego;
import java.awt.Graphics;

import principal.maquinaestado.EstadoJuego;

public class GestorMenu implements EstadoJuego {

	private final EstructuraMenu estructuraMenu;
	
	
	public GestorMenu(){
		estructuraMenu = new EstructuraMenu();
	}
	
	
	public void actualizar() {
		
	}

	
	public void dibujar(final Graphics g) {	
		estructuraMenu.dibujar(g);
	}

}
