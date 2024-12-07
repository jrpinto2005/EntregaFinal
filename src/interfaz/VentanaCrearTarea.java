package interfaz;

import java.awt.GridLayout;
import javax.swing.*;
import learningPaths.Lectura;
import learningPaths.RecursoEducativo;
import learningPaths.SitioWeb;
import learningPaths.Tarea;
import learningPaths.Video;
import usuario.Sistema;

public class VentanaCrearTarea extends JFrame {
    
    private JTextField txtContenido;
    private JButton btnTerminado;

    public VentanaCrearTarea(MenuProfesor menu, Tarea tarea) {
        setTitle("Crear Tarea");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));


        JLabel lblContenido = new JLabel("Contenido de la tarea:");
        txtContenido = new JTextField();
        add(lblContenido);
        add(txtContenido);


        // Botón Terminado
        btnTerminado = new JButton("Terminado");
        add(btnTerminado);

        btnTerminado.addActionListener(e -> {
            String contenido = txtContenido.getText();

            if (contenido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El contenido no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Tarea creada con éxito.");
            this.dispose();
            menu.setVisible(true);
        });
    }
}
