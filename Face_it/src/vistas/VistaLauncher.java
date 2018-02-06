package vistas;


import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class VistaLauncher extends JFrame {

	private static final long serialVersionUID = 1L;
	private Container panelPrincipal;
	private PanelLauncherCentro  plc;
	public boolean avanzo = false;

	public VistaLauncher(){
		
		plc = new PanelLauncherCentro(this);
		this.setTitle("Launcher");
		panelPrincipal = this.getContentPane();
		panelPrincipal.setLayout(new GridLayout(1,2));	
		panelPrincipal.add(plc);
		
		this.setLocation(50, 100);
		this.setSize(1000, 800);
		pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void cerrarLauncher(){
		dispose();
	}
	

	
}

