package principal.herramientas;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import dataconexion.DataConexion;


public class CargadorRecursos {
	
	
	public static BufferedImage cargarImagenCompatibleOpaca(final String ruta){
		
		
		Image imagen = null;
		
		try {
			imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
		} catch (IOException e) {
			e.printStackTrace();
		}

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
				                   getDefaultScreenDevice().getDefaultConfiguration();
		
		BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null),
										imagen.getHeight(null),Transparency.OPAQUE);
		Graphics g = imagenAcelerada.getGraphics();
		g.drawImage(imagen,0,0,null);
		g.dispose();
		
		return imagenAcelerada;
	}
	
	public static BufferedImage cargarImagenCompatibleTranslucida(final String ruta){
		
		Image imagen = null;
		
		try {
			imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
		} catch (IOException e) {
			e.printStackTrace();
		}

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
				                   getDefaultScreenDevice().getDefaultConfiguration();
		
		BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), 
										imagen.getHeight(null),Transparency.TRANSLUCENT);
		Graphics g = imagenAcelerada.getGraphics();
		g.drawImage(imagen,0,0,null);
		g.dispose();
		
		return imagenAcelerada;
	
	}
	
	public static char[] leerMapaBaseDatos(final String nombreMapa){
		
		char[] aux = null;

		try {
			
			//hacemos 3 consultas(guardamos alto,ancho y el mapa que es un array de strings)
			DataConexion.getInstance();
			ArrayList<String> ls = new ArrayList<String>();
			String volcar;
			ls.add(DataConexion.Consulta("*","mapa","ancho"," where idMapa = '"+nombreMapa+"'").get(0));
			ls.add(DataConexion.Consulta("*","mapa","alto"," where idMapa = '"+nombreMapa+"'").get(0));
			ls.add(DataConexion.Consulta("*","mapa","tiposDeSprite"," where idMapa = '"+nombreMapa+"'").get(0));
			ls.add(DataConexion.Consulta("*","mapa","mapaSprites"," where idMapa = '"+nombreMapa+"'").get(0));
			ls.add(DataConexion.Consulta("*","mapa","mapaColisiones"," where idMapa = '"+nombreMapa+"'").get(0));
			ls.add(DataConexion.Consulta("*","mapa","numeroCifrasCoordenadas"," where idMapa = '"+nombreMapa+"'").get(0));
			ls.add(DataConexion.Consulta("*","mapa","coordenadasSalidaX"," where idMapa = '"+nombreMapa+"'").get(0));
			ls.add(DataConexion.Consulta("*","mapa","coordenadasSalidaY"," where idMapa = '"+nombreMapa+"'").get(0));
			ls.add(DataConexion.Consulta("*","mapa","idMapa"," where idMapa = '"+nombreMapa+"'").get(0));
			//como nuestro programa solo devuelve un array de chars lo que hemos hecho
			//ha sido coger el array y meterlo en el string y luego hemos ido
			//poniendo los caracteres en un array de chars
			volcar = ls.get(0) + ls.get(1) + ls.get(2) +  ls.get(3) + ls.get(4)+ ls.get(5)+ ls.get(6) + ls.get(7)
			+ ls.get(8);
			aux = volcar.toCharArray();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aux;
	}

}
