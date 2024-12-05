package interfaz;

import javax.swing.*;

import exceptions.ActivdadNoEcontradaException;
import persistencia.CentralPersistencia;
import persistencia.UsuariosPersistencia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;



@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	
	private static final File DATOS1 = new File("datos/datoslp");
	private static final File DATOS2 = new File("datos/usuarios");
	private boolean guardado ;
	
    public VentanaPrincipal()
    {
    	guardado=false;
        setTitle("Bienvenido");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        // Panel para botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton btnIngresar = new JButton("Ingresar");
        JButton btnCrearUsuario = new JButton("Crear Usuario");
        JButton btnGuardarInformacion = new JButton("Guardar Información");
        JButton btnSalir = new JButton("Salir");

        // Añadir botones al panel
        panelBotones.add(btnIngresar);
        panelBotones.add(btnCrearUsuario);
        panelBotones.add(btnGuardarInformacion);
        panelBotones.add(btnSalir);

        // Acción de los botones
        btnIngresar.addActionListener(e -> {
            VentanaLogin ventanaLogin = new VentanaLogin();
            ventanaLogin.setVisible(true);
            this.dispose();
        });
        btnGuardarInformacion.addActionListener(e -> {

    		try {
    			CentralPersistencia.guardarSistema(DATOS1);
    		} catch (NumberFormatException | IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		try {
    			UsuariosPersistencia.guardarSistema(DATOS2);
    		} catch (NumberFormatException | IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		guardado=true;
    		JOptionPane.showMessageDialog(this, "Guardado con exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
            
        });

        btnCrearUsuario.addActionListener(e -> {
            VentanaCrearUsuario ventanaCrearUsuario = new VentanaCrearUsuario();
            ventanaCrearUsuario.setVisible(true);
            this.dispose();
        });

        btnSalir.addActionListener(e -> {
        	if (guardado)
        	System.exit(0);
        	int opcion = JOptionPane.showConfirmDialog(
                    VentanaPrincipal.this,
                    "¿Desea guardar antes de salir?",
                    "Confirmación",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                // Lógica para guardar la información
                JOptionPane.showMessageDialog(VentanaPrincipal.this, "Información guardada.");
                System.exit(0);
            } else if (opcion == JOptionPane.NO_OPTION) {
                System.exit(0);
            };
        	});

        // Añadir componentes
        add(new JLabel(new ImageIcon("./imagenes/libreria.png")));
        add(panelBotones);
        add(new JLabel(new ImageIcon("./imagenes/uniandes.png")));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
            try {
    			CentralPersistencia.cargarSistema(DATOS1);
    		} catch (NumberFormatException | IOException | ParseException | ActivdadNoEcontradaException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		try {
    			UsuariosPersistencia.cargarSistema(DATOS2);
    		} catch (NumberFormatException | IOException | ParseException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        });
    }

}