package principal;
import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaestado.GestorEstados;

public class GestorPrincipal {

	private boolean enFuncionamiento = false;
	private String titulo;
	private int alto;
	private int ancho;
	private SuperficieDibujo sd;
	private Ventana ventana;
	private GestorEstados ge;

	public GestorPrincipal(final String titulo, int ancho, int alto) {
		this.titulo = titulo;
		this.ancho = ancho;
		this.alto = alto;
		System.setProperty("sun.java2d.opengl","true");
		iniciar();

	}
	
	private void iniciar(){
		iniciarJuego();
		inciarBuclePrincipal();
	}
	
	
	
	private void inciarBuclePrincipal() {

		int actualizacionesAcumuladas = 0;
		int framesAcumulados = 0;

		// cuantos nanos hay en un segundo
		final int Nanos_por_segundo = 1000000000;
		// cuantas actualizaciones queremos por segundo
		final byte actualizaciones_por_segundo = 80;
		// cuantos nanos pasan por actualizacion
		final double Nanos_por_actualizacion = Nanos_por_segundo / actualizaciones_por_segundo;

		long refetenciaActualizacion = System.nanoTime();
		double tiempoTranscurrido;
		long referenciaContador = System.nanoTime();

		// delta es la cantidad de tiempo que ha transcurrido hasta que se ha
		// realizado una actualizacion
		double delta = 0;

		while (enFuncionamiento) {
			final long incioBucle = System.nanoTime();

			// diferencia en nanos entre que creamos long
			// referenciaActualizacion y cuando emepzamos el bucle
			tiempoTranscurrido = incioBucle - refetenciaActualizacion;

			// actualizamos el tiempo de referencia
			refetenciaActualizacion = incioBucle;

			delta += tiempoTranscurrido / Nanos_por_actualizacion;

			while (delta >= 1) {
				actualizar();
				actualizacionesAcumuladas++;
				delta--;
			}

			dibujar();
			framesAcumulados++;

			if (System.nanoTime() - referenciaContador > Nanos_por_segundo) {
				
				ventana.setTitle(titulo + " || APS: " + actualizacionesAcumuladas + " || FPS:" + framesAcumulados);
				framesAcumulados = 0;
				actualizacionesAcumuladas = 0;
				referenciaContador = System.nanoTime();
			}

		}

	}

	private void dibujar() {
		sd.Dibujar(ge);
		
	}

	private void actualizar() {
		

		if(GestorControles.teclado.getOpciones()){	
			ge.cambiarEstadoActual(1);//opciones	
		}else{
			
			if(GestorControles.teclado.getInventario()){
				ge.cambiarEstadoActual(2);//inventario
			}else{
				ge.cambiarEstadoActual(0);//juego
			}
		}
		GestorControles.teclado.actualizar();
		ge.actializar();
		sd.actualizar();
	
	}

	private void inicializar() {
		sd = new SuperficieDibujo(ancho, alto);
		ventana = new Ventana(titulo,sd);
		ge = new GestorEstados();
	}

	private void iniciarJuego() {
		enFuncionamiento = true;
		inicializar();
	}
}
