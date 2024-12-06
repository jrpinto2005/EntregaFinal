package interfaz;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import usuario.Profesor;

public class MenuProfesor extends JFrame {
    public MenuProfesor(Profesor profesor) {
        setTitle("Menu Profesor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Crear botones
        JButton btnCrearLearningPath = new JButton("Crear LearningPath");
        JButton btnEditarLearningPath = new JButton("Editar LearningPath");
        JButton btnCrearActividad = new JButton("Crear Actividad");
        JButton btnClonarActividad = new JButton("Clonar Actividad");
        JButton btnEditarPerfil = new JButton("Editar Perfil");
        JButton btnSalir = new JButton("Salir");

        // Acción del botón Salir
        btnSalir.addActionListener(e -> {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setVisible(true);
            this.dispose();
        });
        
        btnEditarPerfil.addActionListener( e ->{
        	VentanaEditarPerfilProfesor ventanaEditar= new VentanaEditarPerfilProfesor(profesor, this);
        	ventanaEditar.setVisible(true);
        	this.setVisible(false);
        });
        
        btnCrearLearningPath.addActionListener( e ->{
        	VentanaCrearLP ventanaCrearLP= new VentanaCrearLP(this, profesor);
        	ventanaCrearLP.setVisible(true);
        	this.setVisible(false);
        });
        
        btnClonarActividad.addActionListener( e ->{
        	VentanaClonarActividad ventanaClonar= new VentanaClonarActividad(this);
        	ventanaClonar.setVisible(true);
        	this.setVisible(false);
        });


        // Añadir botones al frame
        add(btnCrearLearningPath);
        add(btnEditarLearningPath);
        add(btnCrearActividad);
        add(btnClonarActividad);
        add(btnEditarPerfil);
        add(btnSalir);
    }
}

