package principal.edificios;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public abstract class Edificio {
	
	protected int id;
	protected tipoEdificio tipoEdificio;
	protected double beneficios;
	String dueño;
	protected HojaSprites hs;
	protected int[] plano;
	protected ArrayList<Rectangle> areasColision = new ArrayList<Rectangle>();
	protected int numeroTiposSprites;
	protected Sprite[] paleta; // todos los sprites que vamos a usar para hacer el mapa
	protected boolean[] colisiones;
	protected int diaDeCobro;
	
	void diaDeCobro(){
	    Date hoy=new Date();
	    Calendar cal= Calendar.getInstance();
	    cal.setTime(hoy);
	    int numeroDia=cal.get(Calendar.DAY_OF_WEEK);
	}
	
}
