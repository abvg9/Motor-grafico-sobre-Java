package vistas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dataconexion.DataConexion;

public class PanelLauncherCentro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel arriba; // panel Tablero
	private JPanel centro; // panel aleatorio
	private JPanel abajo;
	private JButton salir;
	private JButton conectar;
	private JTextField usuario;
	private JTextField contrasena;
	private JLabel us;
	private JLabel contr;
	private VistaLauncher lnch;

	public PanelLauncherCentro(VistaLauncher lnch) {
		this.lnch = lnch;
		inicializaPanel();
	}

	private void inicializaPanel() {

		// PANEL ARRIBA
		arriba = new JPanel();
		arriba.setLayout(new BorderLayout());

		// PANEL CENTRO(primero creo los elementos y luego los agrego quiera
		// mostrarlos)
		centro = new JPanel();
		centro.setLayout(new BorderLayout());

		// usuario y contraseña
		us = new JLabel("Usuario");
		contr = new JLabel("Contraseña");
		usuario = new JTextField("                    ");
		contrasena = new JTextField("                    ");

		// boton salir
		salir = new JButton("SALIR");
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// boton conectar
		conectar = new JButton("CONECTAR");
		conectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String aux_usuario = usuario.getText().replaceAll(" ", "");
				String aux_contrasena = contrasena.getText().replaceAll(" ", "");
				DataConexion con = new DataConexion();

				if (!aux_usuario.equalsIgnoreCase("") && !aux_contrasena.equalsIgnoreCase("")) {

								DataConexion.user = aux_usuario;
								DataConexion.pass = aux_contrasena;
								String[] nombreCont = new String[2];
								nombreCont[0] = aux_usuario;
								nombreCont[1] = aux_contrasena;
								try {
									ArrayList<String> ls = DataConexion.Consulta("*", "usuarios", "usuario", " WHERE usuarios.usuario = '"+aux_usuario+"' AND "
									+ "usuarios.contrasena = '"+aux_contrasena+"'");
										
									
									if(ls.size() == 0){
										
										JOptionPane.showMessageDialog(null, "Usuario y/o contraseña no validos");
										
									}else{
										lnch.avanzo = true;
										lnch.cerrarLauncher();				
									}
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}			
								

				} else {
					// si no mete ningun valor

					JOptionPane.showMessageDialog(null, "Usuario y/o contraseña no validos");
				}

			}
		});

		// insertamos los elementos en el oanel

		JPanel panel_aux = new JPanel();
		panel_aux.add(us);
		panel_aux.add(usuario);
		panel_aux.add(contr);
		panel_aux.add(contrasena);
		panel_aux.add(conectar);
		panel_aux.add(salir);

		centro.add(panel_aux);

		// PANEL ABAJO
		abajo = new JPanel();
		abajo.setLayout(new BorderLayout());

		// AÑADIMOS LOS PANELES A LA CLASE

		this.add(arriba, BorderLayout.NORTH);
		this.add(centro, BorderLayout.CENTER);
		this.add(abajo, BorderLayout.SOUTH);

	}

}
