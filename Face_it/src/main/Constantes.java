package main;

public class Constantes {

	public static final int LADO_SPRITE = 32;
	public static int ANCHO_VENTANA = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int ALTO_VENTANA  = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	
	
	public static int CENTRO_VENTANA_X = ANCHO_VENTANA/2;
	public static int CENTRO_VENTANA_Y = ALTO_VENTANA/2;
	/*
	 * RUTAS DE ARCHIVOS LOCALES
	 * 
	 */
	public static String RUTA_MAPA = "/imagenes/hojasTexturas/Prueba.png";
	public static String RUTA_ICONO_RATON = "/imagenes/iconos/Cursor.gif";
	public static String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/jugador.png";
	public static String RUTA_ICONO_VENTANA = "/imagenes/iconos/icono.png";
	//public static String RUTA_ARMAS = "/imagenes/hojasArmas/armas.png";
	public static final int RECUPERACION_MAXIMA = 480;
	public static final String RUTA_SONIDO_DISPARO = "C:\\Users\\velas\\Desktop\\Sprites\\disparoPistola.mp3";
	
	
	/*Cosas de los socket
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static final int puertoServidor = 2000;
	public static final String ipServidor = "localhost";
}
