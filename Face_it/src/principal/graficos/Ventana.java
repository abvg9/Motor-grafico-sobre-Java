package principal.graficos;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import main.Constantes;
import principal.herramientas.CargadorRecursos;

public class Ventana extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private String titulo;
	private final ImageIcon icono;
	
	public Ventana(final String titulo,final SuperficieDibujo sd){
		this.titulo = titulo;
		this.icono = new ImageIcon(CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_ICONO_VENTANA));
		condigurarVentana(sd);
	}

	private void condigurarVentana(final SuperficieDibujo sd) {
		
		setTitle(titulo);
		setIconImage(icono.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);		
		setLayout(new BorderLayout());
		add(sd,BorderLayout.CENTER);
		//setUndecorated(true); //->eliminar bordes de la ventana
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
