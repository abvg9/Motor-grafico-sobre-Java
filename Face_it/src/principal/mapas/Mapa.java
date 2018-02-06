package principal.mapas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import main.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Mapa {
	

	private Rectangle zonaSalida;
	private int ancho;
	private int alto;
	private int numeroTiposSprites;
	private Sprite[] paleta; // todos los sprites que vamos a usar para hacer el mapa
	private char[] informacionEncriptada;
	private final int MARGEN_X = Constantes.ANCHO_VENTANA/2 - Constantes.LADO_SPRITE/2;
	private final int MARGEN_Y = Constantes.ALTO_VENTANA/2 - Constantes.LADO_SPRITE/2;
	private boolean[] colisiones;
	private ArrayList<Rectangle> areasColision = new ArrayList<Rectangle>();
	private String idMapa;
	private Point posicionSalida;
	private Point posicionInicial;

	
	public Mapa(final String nombreMapa){	
		desencriptarMapa(nombreMapa);
	}
	
	private void desencriptarMapa(final String nombreMapa){

		posicionInicial = new Point();
		zonaSalida = new Rectangle();
		idMapa = new String();
		informacionEncriptada = CargadorRecursos.leerMapaBaseDatos(nombreMapa);
		ancho = informacionEncriptada[0] - 48;
		alto = informacionEncriptada[1] - 48;	
		numeroTiposSprites = informacionEncriptada[2] -48;	
		colisiones = new boolean[ancho*alto];
		for(int i = 0;i < ancho*alto;i++){
			if(informacionEncriptada[i+3+ancho*alto] == '0'){
				colisiones[i] = false;
			}else{
				colisiones[i] = true;
			}
		}
		posicionSalida = new Point();
		int numeroCifras = informacionEncriptada[3+2*ancho*alto] -48;
		int unidades = 1;
		for(int i = 0; i < numeroCifras-1;i++){		
			unidades = unidades * 10;		
		}
		for(int i = 0; i < numeroCifras;i++){		
			posicionSalida.x += (informacionEncriptada[4+i+2*ancho*alto]-48) * unidades;	
			posicionSalida.y += (informacionEncriptada[numeroCifras+4+i+2*ancho*alto]-48) * unidades;
			unidades = unidades/10;
		}
		for(int i = 7+numeroCifras+2*ancho*alto; i < informacionEncriptada.length;i++){
			idMapa += informacionEncriptada[i];
		}
		
		//hay que pasarle la cantidad de tipos de sprites que tiene el mapa
		paleta = new Sprite[numeroTiposSprites];
		//32 -> tamaño lado de cada sprite(si son no cuadradas,llamariamos al otro constructor)
		HojaSprites hoja = new HojaSprites(Constantes.RUTA_MAPA, 32, false);
		for(int i = 0;i < numeroTiposSprites;i++){		
			paleta[i] = hoja.getSprite(i, 0);
		}
		posicionInicial.setLocation(ancho, alto);
	}
	
	public void actualizar(final int posicionX,final int posicionY){
		actualizarAreasColisiones(posicionX,posicionY);
		actualizarSalidas(posicionX,posicionY);
		
	}
	
	private void actualizarAreasColisiones(final int posicionX,final int posicionY){

		areasColision.clear();	
		for(int y = 0; y < this.alto;y++){
			for(int x = 0; x < this.ancho;x++){
				
				int puntoX = x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
				int puntoY = y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;
				
				if(!colisiones[x+y * this.ancho]){
					areasColision.add(new Rectangle(puntoX,puntoY,Constantes.LADO_SPRITE,Constantes.LADO_SPRITE));

				}	
			}
		}
	}
	
	private void actualizarSalidas(final int posicionX,final int posicionY){
		
		int puntoX = (int) posicionSalida.getX()  - posicionX + MARGEN_X;
		int puntoY = (int) posicionSalida.getY() - posicionY + MARGEN_Y;
		
		zonaSalida = new Rectangle(puntoX-50,puntoY-50,Constantes.LADO_SPRITE/2,Constantes.LADO_SPRITE/2);
	}
	
	public void dibujar(Graphics g,final int posicionX,final int posicionY){
		
		g.setColor(Color.CYAN);

	    int indice = 0;
		for(int y = 0; y < this.alto;y++){
			for(int x = 0; x < this.ancho;x++){
				
				int puntoX = x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
				int puntoY = y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;
				g.drawImage(paleta[seleccionImagenPaleta(indice)].getImagen(),puntoX,puntoY, null);
				indice++;			
			}
		}
	}
		
	private int seleccionImagenPaleta(int indice){
		
		switch(informacionEncriptada[indice + 3]){
		case 'B':
			return 0;
		case 'P':
			return 2;
		case 'S':
			return 1;
		default :
			return -1;		
		}		
	}
	
	public int getAncho(){
		return this.ancho;
	}
	
	public int getAlto(){
		return this.alto;
	}
	
	public Rectangle getBordes(int posicionX,int posicionY){
		
		int x = MARGEN_X - posicionX;
		int y = MARGEN_Y - posicionY;
		int ancho = this.ancho * Constantes.LADO_SPRITE;
		int alto = this.alto * Constantes.LADO_SPRITE;
		
		return new Rectangle(x,y,ancho,alto);	
	}
	
	public ArrayList<Rectangle> getAreasColisiones(){
		
		return areasColision;
	}
	public Point getPosicionInicial(){
		return posicionInicial;
	}
	
	public Point getSalida(){	
		return posicionSalida;
	}
	
	public Rectangle getZonaSalida(){
		return zonaSalida;
	}
	
	public String getIdMapa(){	
		return idMapa;
	}
	
	public Sprite[] getPaleta(){
		return paleta;
	}

}
