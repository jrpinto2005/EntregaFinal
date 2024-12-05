package interfaz;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import learningPaths.LearningPath;
import usuario.Estudiante;
import usuario.Sistema;

@SuppressWarnings("serial")
public class MenuEstudiante extends JFrame {
    public MenuEstudiante(Estudiante estudiante) {
        setTitle("Menu Estudiante");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Crear botones
        JButton btnInscribirLearningPath = new JButton("Inscribir LearningPath");
        JButton btnHacerActividad = new JButton("Hacer Actividad");
        JButton btnEditarPerfil = new JButton("Editar Perfil");
        JButton btnConsultarProgreso = new JButton("Consultar Progreso LP");
        JButton btnActividadesSugeridas = new JButton("Actividades Sugeridas");
        JButton btnSalir = new JButton("Salir");

        // Acción del botón Salir
        btnSalir.addActionListener(e -> {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setVisible(true);
            this.dispose();
        });
        btnInscribirLearningPath.addActionListener(e -> {
        	String respuesta= JOptionPane.showInputDialog(this, "Nombre LP", "Inscribir LP", JOptionPane.QUESTION_MESSAGE);
        	LearningPath lp =Sistema.getInstancia().encontrarLP(respuesta);
        	if (estudiante.inscribirseEnLearningPath(lp)==false)
    		{
        		JOptionPane.showMessageDialog(null, "Ya estaba inscrito en este LP", "Error!", JOptionPane.ERROR_MESSAGE);
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(null, "Fue inscirto correctamente a " + respuesta, "Exito!", JOptionPane.INFORMATION_MESSAGE);
    		}
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
