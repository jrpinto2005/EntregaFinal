package interfaz;
import javax.swing.*;

import envios.Envio;
import envios.EnvioExamen;
import learningPaths.Actividad;
import learningPaths.Encuesta;
import learningPaths.Examen;
import learningPaths.LearningPath;
import learningPaths.Lectura;
import learningPaths.RecursoEducativo;
import learningPaths.Tarea;
import usuario.Estudiante;
import usuario.Profesor;
import usuario.Sistema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class VentanaSeleccionEnvio extends JFrame {
    private JList<String> listaEnvios;
    private JButton btnSeleccionar;
    private JLabel lblResultado;

    public VentanaSeleccionEnvio(Profesor profesor, MenuProfesor menu) {
        // Usar un conjunto para evitar duplicados
        List<String> envios = new ArrayList<>();
        
        for (Envio envio: profesor.getEnviosPorCalificar())
        {
        	envios.add(envio.getIdEstudiante() + envio.getActividad().getId());
        }

        // Configuración de la ventana
        setTitle("Seleccionar Envio");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar el layout
        setLayout(new BorderLayout(10, 10));

        // Etiqueta superior
        JLabel lblTitulo = new JLabel("Seleccione un envio para calificar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        // Crear lista de envios
        listaEnvios = new JList<>(envios.toArray(new String[0]));
        listaEnvios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaEnvios);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botón y resultado
        JPanel panelInferior = new JPanel(new BorderLayout(10, 10));
        btnSeleccionar = new JButton("Seleccionar");
        lblResultado = new JLabel("", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Arial", Font.ITALIC, 14));
        lblResultado.setForeground(Color.BLUE);

        // Acción del botón
        btnSeleccionar.addActionListener(e-> {
                String envioSeleccionado = listaEnvios.getSelectedValue();
                int indice=listaEnvios.getSelectedIndex();
                EnvioExamen envio=profesor.getEnviosPorCalificar().get(indice);
                VentanaCorregirExamen ventanaCorregir= new VentanaCorregirExamen(envio, menu);
                ventanaCorregir.setVisible(true);
            
        });

        // Añadir componentes al panel inferior
        panelInferior.add(btnSeleccionar, BorderLayout.NORTH);
        panelInferior.add(lblResultado, BorderLayout.CENTER);

        add(panelInferior, BorderLayout.SOUTH);
    }
}