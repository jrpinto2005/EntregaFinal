package interfaz;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MenuEstudiante extends JFrame {
    public MenuEstudiante() {
        setTitle("Menu Estudiante");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Crear botones
        JButton btnInscribirLearningPath = new JButton("Inscribir LearningPath");
        JButton btnHacerActividad = new JButton("Hacer Actividad");
        JButton btnEditarPerfil = new JButton("Editar Perfil");
        JButton btnConsultarProgreso = new JButton("Consultar Progreso LearningPath");
        JButton btnActividadesSugeridas = new JButton("Actividades Sugeridas");
        JButton btnSalir = new JButton("Salir");

        // Acción del botón Salir
        btnSalir.addActionListener(e -> {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setVisible(true);
            this.dispose();
        });

        // Añadir botones al frame
        add(btnInscribirLearningPath);
        add(btnHacerActividad);
        add(btnEditarPerfil);
        add(btnConsultarProgreso);
        add(btnActividadesSugeridas);
        add(btnSalir);
    }
}
