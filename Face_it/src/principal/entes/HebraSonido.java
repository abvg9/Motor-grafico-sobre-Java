package principal.entes;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class HebraSonido extends Thread {
	
	//sonidos del jugador
	private Player player;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public HebraSonido(String ruta){
		
		try {
			//si nos traemos el sonido desde el principio,seria mejor para que fuese mas rapido el sonido
			fis = new  FileInputStream(ruta);
			bis= new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}

	}
	
	public void run() {

		try {
			player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		
		

	}

}
