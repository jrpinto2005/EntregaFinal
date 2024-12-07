package interfaz;

import javax.swing.*;

import constructores.ControladorEnvios;
import usuario.Estudiante;
import learningPaths.Examen;
import envios.Pregunta;
import exceptions.ActivdadNoEcontradaException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class VentanaHacerExamen extends JFrame {
    private int indicePreguntaActual; // Índice para la pregunta actual
    private Examen examen;
    private Estudiante estudiante;
    private VentanaSeleccionActividad ventanaSeleccionActividad;
    private List<String> respuestas;

    private JLabel lblPregunta;
    private JTextField txtRespuesta;
    private JButton btnSiguiente;
    private JButton btnVolver;

    public VentanaHacerExamen(Examen examen, Estudiante estudiante, VentanaSeleccionActividad ventanaSeleccionActividad) {
        this.examen = examen;
        this.estudiante = estudiante;
        this.ventanaSeleccionActividad = ventanaSeleccionActividad;
        this.indicePreguntaActual = 0;
        this.respuestas= new ArrayList<String>();


        // Configuración inicial de la ventana
        setTitle("Hacer Examen");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel para mostrar pregunta y respuesta
        JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
        lblPregunta = new JLabel("", SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        panelContenido.add(lblPregunta, BorderLayout.NORTH);

        txtRespuesta = new JTextField();
        txtRespuesta.setFont(new Font("Arial", Font.PLAIN, 14));
        panelContenido.add(txtRespuesta, BorderLayout.CENTER);

        add(panelContenido, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnVolver = new JButton("Volver");
        btnSiguiente = new JButton("Siguiente");

        panelBotones.add(btnVolver);
        panelBotones.add(btnSiguiente);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón "Volver"
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ventanaSeleccionActividad.setVisible(true);
                dispose();
            }
        });

        // Acción del botón "Siguiente"
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                respuestas.add(txtRespuesta.getText());
                if (indicePreguntaActual < examen.getPreguntas().size() - 1) {
                    indicePreguntaActual++;
                    mostrarPregunta();
                } else {
                    finalizarExamen();
                }
            }
        });

        // Mostrar la primera pregunta
        mostrarPregunta();
    }

    /**
     * Muestra la pregunta actual en la ventana.
     */
    private void mostrarPregunta() {
        Pregunta preguntaActual = examen.getMapaPreguntas().get(indicePreguntaActual+1);
        lblPregunta.setText("<html>" + (indicePreguntaActual ) + ". " + preguntaActual.getTextoPregunta() + "</html>");
        txtRespuesta.setText(""); // Limpiar el campo de texto
    }

    /**
     * Guarda la respuesta actual en la pregunta correspondiente.
     */


    /**
     * Finaliza el examen, mostrando un mensaje de confirmación y regresando a la ventana de selección.
     */
    private void finalizarExamen() {
        ControladorEnvios env= new ControladorEnvios();
        try {
			env.hacerExamen(estudiante, examen.getId() , respuestas);
		} catch (ActivdadNoEcontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JOptionPane.showMessageDialog(
                this,
                "Examen completado. Gracias por responder.",
                "Examen Finalizado",
                JOptionPane.INFORMATION_MESSAGE
        );
        ventanaSeleccionActividad.setVisible(true);
        dispose();
    }
}
