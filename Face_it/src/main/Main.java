package main;
import dataconexion.DataConexion;
import principal.GestorPrincipal;
import vistas.VistaLauncher;

public class Main {
	
	public static void main(java.lang.String[] args){
		
		
		//En su momento esto cambiara y el el que se conectara sera el admin
		DataConexion.getInstance();
		VistaLauncher vl = new VistaLauncher();
		
		while(!vl.avanzo){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		new GestorPrincipal("Face it", Constantes.ANCHO_VENTANA, Constantes.ALTO_VENTANA);
		
		
	}
	


}